package com.valueservice.djs.controller.system;
import com.alibaba.fastjson.JSON;
import com.valueservice.djs.bean.UpfileRecordVO;
import com.valueservice.djs.db.entity.system.FileParsingRepBean;
import com.valueservice.djs.db.entity.system.SplitFileBean;
import com.valueservice.djs.db.dao.system.UpfileRecordDOMapper;
import com.valueservice.djs.db.entity.system.UpfileRecordDO;
import com.valueservice.djs.util.BeanUtils;
import com.valueservice.djs.util.OfficeConvert;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@Controller
@RequestMapping("/import")
public class ImportController {

    private static final Logger logger = LoggerFactory.getLogger(ImportController.class);

    @Value("${file.path}")
    private String filePath;

    @Value("${context.path}")
    private String contextPath;

    @Resource
    private UpfileRecordDOMapper upfileRecordDOMapper;

    @GetMapping("/toUp")
    public String toUp(){
        return "system/import/import";
    }

    /**
     * 上传文件使用,其中minifile映射为静态资源地址
     * TODO 出现异常回滚硬盘生成的文件或文件夹
     * @param toConvertPic 将文件转化相应图片开关
     * @param rootId 根目录
     * @param files 文件组
     * @return 资源服务器http地址
     */
    @PostMapping("/up/{rootId}")
    public @ResponseBody List<FileParsingRepBean> up(
                                        @PathVariable String rootId,
                                        @RequestParam("file") MultipartFile[] files,
                                        @RequestParam(required=false) Boolean toConvertPic){
        if(Objects.isNull(rootId) || Objects.isNull(files)){
            logger.error("The required parameter is null..");
            return null;
        }
        try {
            File userFile = new File(String.format("%s/%s", filePath,rootId));
            if (!userFile.exists()){
                userFile.mkdir();
            }
            String userFilePath = String.format("%s%s",userFile.getPath(),"/");
            List<FileParsingRepBean> fileParsingRepBeans = processFile(rootId, files,userFilePath,toConvertPic);
            //保存文件上传记录
            saveToFileRecord(fileParsingRepBeans);
            return fileParsingRepBeans;

        }catch (Exception e){
            logger.error("",e);
        }
        return null;
    }

    /**
     * 维护文件记录表
     * @param fileParsingRepBeans 文件解析返回对象
     */
    private void saveToFileRecord(List<FileParsingRepBean> fileParsingRepBeans) {
        fileParsingRepBeans.forEach(x->{
            UpfileRecordDO upfileRecordDO = new UpfileRecordDO();
            upfileRecordDO.setActualFilePath(x.getActualFilePath());
            upfileRecordDO.setHttpFilePath(x.getFilePath());
            if(x.getSplitFileList() != null && !x.getSplitFileList().isEmpty()){
                upfileRecordDO.setSplitFiles(JSON.toJSONString(x.getSplitFileList()));
            }
            upfileRecordDO.setCreatorId(-1);
            upfileRecordDO.setCreateTime(new Date());
            upfileRecordDO.setRemark("小程序用户上传");
            upfileRecordDOMapper.insertSelective(upfileRecordDO);
            x.setInsertId(upfileRecordDO.getId());
        });
    }

    /**
     * 将文件转换为pdf,再利用openoffice转为为图片，返回http访问地址
     * @param userFilePath
     * @return
     */
    private void processSplitFile(String httpPathForRoot,File localFile,
                                  String userFilePath,FileParsingRepBean fileParsingRepBean) {

        String fileName = localFile.getName();
        String suffix = fileName.substring(fileName.indexOf("."));
        String prefix = fileName.substring(0, fileName.length() - suffix.length());

        File pdfFile;
        String pdfFilePath;
        if(suffix.equals(".pdf")){
            pdfFilePath = localFile.getPath();
            pdfFile = new File(pdfFilePath);
        }else{
            String pdfFileName = fileName.replace(suffix,".pdf");
            pdfFilePath = String.format("%s%s/%s",userFilePath,prefix,pdfFileName);
            String sourceFilePath = String.format("%s%s",userFilePath,fileName);
            OfficeConvert.officeToPdf(sourceFilePath,pdfFilePath);
            pdfFile = new File(pdfFilePath);
        }
        if(!pdfFile.isFile()){
            throw new RuntimeException("Not a PDF file.");
        }
        List<String> pics = OfficeConvert.pdfToIamge(1.2f,
                                pdfFilePath,String.format("%s%s",userFilePath,prefix));

        List<SplitFileBean> spiltFiles = new ArrayList<>();
        for(int i = 0;i<pics.size();i++){
            SplitFileBean splitFileBean = new SplitFileBean();
            splitFileBean.setForeshow(false);
            String acturlPicPath = pics.get(i);
            String httpPathForFile = String.format("%s/%s%s",httpPathForRoot,prefix,
                    acturlPicPath.substring(acturlPicPath.lastIndexOf("/"),acturlPicPath.length()));
            splitFileBean.setSplitFilePath(httpPathForFile);
            splitFileBean.setActualSplitFilePath(acturlPicPath);
            spiltFiles.add(splitFileBean);
            fileParsingRepBean.setSplitFileList(spiltFiles);
        }
    }

    /**
     * 上传文件
     * @param rootId
     * @param files
     * @param userFilePath
     */
    private List<FileParsingRepBean> processFile(
            String rootId,MultipartFile[] files,String userFilePath,Boolean toConvertPic) {


        List<FileParsingRepBean> lists = new ArrayList<>();
        String httpPathForRoot  = String.format("%s%s%s",contextPath,"minifile/",rootId);
        Stream.of(files).forEach(file->{
            if (file != null) {
                String myFileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(myFileName.trim())) {
                    FileParsingRepBean fileParsingRepBean = new FileParsingRepBean();
                    String fileAllName = file.getOriginalFilename();
                    String prefixName = fileAllName.substring(0, fileAllName.indexOf("."));
                    String suffixName = fileAllName.substring(fileAllName.indexOf("."), fileAllName.length());
                    prefixName += "(" + System.currentTimeMillis() + ")";
                    String currentFilePath = String.format("%s%s%s", userFilePath, prefixName, suffixName);
                    File localFile = new File(currentFilePath);
                    localFile.setReadable(true);
                    try {
                        file.transferTo(localFile);
                    } catch (IOException e) {
                        logger.error("转换失败",e);
                    }
                    String httpPathForFile = String.format("%s/%s%s",httpPathForRoot,prefixName,suffixName);
                    fileParsingRepBean.setFilePath(httpPathForFile);
                    fileParsingRepBean.setActualFilePath(currentFilePath);
                    if(Objects.nonNull(toConvertPic) && toConvertPic){
                        processSplitFile(httpPathForRoot,localFile,userFilePath, fileParsingRepBean);
                    }
                    lists.add(fileParsingRepBean);
                }
            }
        });
        return lists;
    }

    /**
     * 根据ID获取上传后的结果
     * @param id
     * @return
     */
    @PostMapping("/fileRecord/get/{id}")
    public @ResponseBody UpfileRecordVO getFileRecord(@PathVariable Long id){
        UpfileRecordVO upfileRecordVO = new UpfileRecordVO();
        UpfileRecordDO upfileRecordDO = upfileRecordDOMapper.selectByPrimaryKey(id);
        BeanUtils.copyNotNullFields(upfileRecordDO,upfileRecordVO);
        String jsonStr = upfileRecordDO.getSplitFiles();
        if(!jsonStr.isEmpty()){
            upfileRecordVO.setSplitFiles(JSON.parseArray(jsonStr,SplitFileBean.class));
        }
        return upfileRecordVO;
    }

    /**
     * 用户设置房间的预告文件
     * import 记录不应该跟房间有耦合行为，设置未过期
     * @use  联系夏鑫
     * @param id
     * @param jsonSplitStr
     * @return
     */
    @Deprecated
    @PostMapping("/fileRecord/update/{id}")
    public @ResponseBody boolean updateFileRecord(@PathVariable Long id,@RequestParam String jsonSplitStr){
        UpfileRecordDO upfileRecordDO = new UpfileRecordDO();
        upfileRecordDO.setId(id);
        upfileRecordDO.setSplitFiles(jsonSplitStr);
        return upfileRecordDOMapper.updateByPrimaryKeySelective(upfileRecordDO)>0?true:false;
    }


    /**
     * 获取房间下设置为预告的图片地址
     * import 记录不应该跟房间有耦合行为，设置未过期
     * @use  联系夏鑫
     * @param id
     * @return
     */
    @Deprecated
    @PostMapping("/courseware/getpic/{id}")
    public @ResponseBody List<String> getPicPath(@PathVariable Long id){
        UpfileRecordDO upfileRecordDO = upfileRecordDOMapper.selectByPrimaryKey(id);
        List<SplitFileBean> list = JSON.parseArray(upfileRecordDO.getSplitFiles(),SplitFileBean.class);
        List<String> picpaths = new ArrayList<>();
        list.forEach(x->{
            if(x.getForeshow()){
                picpaths.add(x.getSplitFilePath());
            }
        });
        return picpaths;
    }
}
