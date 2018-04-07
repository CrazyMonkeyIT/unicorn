package com.valueservice.djs.controller.system;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/toUp")
    public String toUp(HttpServletRequest request){

//        OfficeConvert.docToPdf("/Users/Bill/Desktop/座位图.pptx",
//                "/Users/Bill/Desktop/file/logback.pdf");
        return "system/import/import";
    }

    /**
     * 上传文件使用,其中minifile映射为静态资源地址
     * toConvertPic = true 为将文件转化相应图片
     * @param rootId 跟目录
     * @param files 文件组
     * @param files 文件组
     * @return 资源服务器http地址 return->[{filePath:'http://localhost:8080/minifile/login.jpg'}]
     */
    @PostMapping("/up/{rootId}")
    public @ResponseBody List<Map<String,String>> up(
                                        @PathVariable String rootId,
                                        @RequestParam("file") MultipartFile[] files,
                                        @RequestParam(required=false) Boolean toConvertPic){

        if(Objects.isNull(rootId) || Objects.isNull(files)){
            logger.error("The required parameter is null..");
            return null;
        }
        List<Map<String,String>> lists = new ArrayList<>();
        try {
            File userFile = new File(String.format("%s/%s", filePath,rootId));
            if (!userFile.exists()){
                userFile.mkdir();
            }
            if(toConvertPic){
                //TODO 将文件转为若干图片
            }
            String userFilePath = String.format("%s%s",userFile.getPath(),"/");
            String httpPathForRoot  = String.format("%s%s%s",contextPath,"minifile/",rootId);
            Stream.of(files).forEach(file->{
                    if (file != null) {
                        String myFileName = file.getOriginalFilename();
                        if (StringUtils.isNotBlank(myFileName.trim())) {
                            Map<String, String> map = new HashMap<>();
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
                                map.put("filePath", null);
                                logger.error("转换失败",e);
                            }
                            String httpPathForFile = String.format("%s/%s%s",httpPathForRoot,prefixName,suffixName);
                            map.put("filePath", httpPathForFile);
                            lists.add(map);
                        }
                    }

            });
        }catch (Exception e){
            logger.error("",e);
        }
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
        Vector<String> vecFile = new Vector<String>();
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
