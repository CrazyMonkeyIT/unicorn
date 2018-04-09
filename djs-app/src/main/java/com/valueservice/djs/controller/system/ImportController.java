package com.valueservice.djs.controller.system;
import com.valueservice.djs.db.entity.system.FileParsingRepBean;
import com.valueservice.djs.db.entity.system.SplitFileBean;
import com.valueservice.djs.db.dao.system.UpfileRecordDOMapper;
import com.valueservice.djs.db.entity.system.UpfileRecordDO;
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
                upfileRecordDO.setSplitFiles(x.getSplitFileList());
            }
            upfileRecordDO.setCreatorId(-1);
            upfileRecordDO.setCreateTime(new Date());
            upfileRecordDO.setRemark("小程序用户上传");
            upfileRecordDOMapper.insertSelective(upfileRecordDO);
            UpfileRecordDO a = upfileRecordDOMapper.selectByPrimaryKey(4L);
            a.getSplitFiles().forEach(v->{
                System.out.println(v.getSplitFilePath());
            });
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
        String pdfFileName = fileName.replace(suffix,".pdf");
        String pdfFilePath = String.format("%s%s/%s",userFilePath,prefix,pdfFileName);
        String sourceFilePath = String.format("%s%s",userFilePath,fileName);
        OfficeConvert.officeToPdf(sourceFilePath,pdfFilePath);
        File pdfFile = new File(pdfFilePath);
        if(!pdfFile.isFile()){
            throw new RuntimeException("Not a PDF file.");
        }
        List<String> pics = OfficeConvert.pdfToIamge(1.2f,
                                pdfFilePath,String.format("%s%s",userFilePath,prefix));

        List<SplitFileBean> spiltFiles = new ArrayList<>();
        for(int i = 0;i<pics.size();i++){
            SplitFileBean splitFileBean = new SplitFileBean();
            if(i == 0){
                splitFileBean.setForeshow(true);
            }
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
                    if(toConvertPic){
                        processSplitFile(httpPathForRoot,localFile,userFilePath, fileParsingRepBean);
                    }
                    lists.add(fileParsingRepBean);
                }
            }
        });
        return lists;
    }

    public static void main(String[] args) {
        Boolean r = null;
        if(r){
            System.out.println(1);
        }
    }

    @PostMapping("/getFileList/{roomId}")
    public @ResponseBody Vector<String> getFileList(@PathVariable String roomId){
        File file = new File(String.format("%s/%s", filePath,roomId));
        Vector<String> vecFile = new Vector<>();
        File[] tempList = file.listFiles();
        if(tempList != null){
            for (int i = 0; i < tempList.length; i++) {
                if (tempList[i].isFile()) {
                    if(tempList[i].getName().equals(".DS_Store")){
                        continue;
                    }
                    vecFile.add(tempList[i].getName());
                }
            }
        }
        return vecFile;
    }
}
