package com.valueservice.djs.controller.system;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.stream.Stream;

@Controller
public class ImportController {

    private static final Logger logger = LoggerFactory.getLogger(ImportController.class);

    @Value("${file.path}")
    private String filePath;

    @GetMapping("/toUp")
    public String toUp(HttpServletRequest request){
        return "system/import/import";
    }

    @PostMapping("/up/{roomId}")
    public @ResponseBody List<Map<String,String>> up(@PathVariable String roomId,@RequestParam("file") MultipartFile[] files){
        if(Objects.isNull(roomId) || Objects.isNull(files)){
            logger.error("The required parameter is null.");
            return null;
        }
        List<Map<String,String>> lists = new ArrayList<>();
        File userFile = new File(String.format("%s/%s", filePath,roomId));
        if (!userFile.exists()){
            userFile.mkdir();
        }
        String userFilePath = String.format("%s%s",userFile.getPath(),"\\");
        Stream.of(files).forEach(file->{
            try {
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
                        file.transferTo(localFile);
                        map.put("filePath", currentFilePath);
                        lists.add(map);
                    }
                }
            }catch (Exception e){
                    logger.error("",e);
            }
        });
        return lists;
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
