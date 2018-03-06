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
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

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
        try {
            File userFile = new File(String.format("%s/%s", filePath,roomId));
            if (!userFile.exists()){
                userFile.mkdir();
            }
            String userFilePath = String.format("%s%s",userFile.getPath(),"\\");
            for(MultipartFile file : files){
                if(file != null){
                    String myFileName = file.getOriginalFilename();
                    if(StringUtils.isNotBlank(myFileName.trim())){
                        Map<String,String> map = new HashMap<>();
                        String fileAllName = file.getOriginalFilename();
                        String prefixName = fileAllName.substring(0,fileAllName.indexOf("."));
                        String suffixName = fileAllName.substring(fileAllName.indexOf("."),fileAllName.length());
                        prefixName += "("+System.currentTimeMillis()+")";
                        String currentFilePath = String.format("%s%s%s",userFilePath,prefixName,suffixName);
                        File localFile = new File(currentFilePath);
                        file.transferTo(localFile);
                        map.put("filePath",currentFilePath);
                        lists.add(map);
                    }
                }
            }
        } catch (IllegalStateException | IOException e){
            logger.error("",e);
        }
        return lists;
    }

    @GetMapping("/getFileList")
    public @ResponseBody Vector<String> getFileList(){
        File file = new File(filePath);
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

    @DeleteMapping("/delete")
    public String delete(@RequestParam String name,Model model){
        File file = new File(filePath + name);
        if(file.exists()){
            file.delete();
            model.addAttribute("msg","delete success!");
        }else{
            model.addAttribute("msg","not exists!");
        }
        return "system/import/import";
    }

}
