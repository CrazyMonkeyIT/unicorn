package com.valueservice.djs.controller.system;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

@Controller
public class ImportController {

    private static final Logger logger = LoggerFactory.getLogger(ImportController.class);

    @Value("${file.path}")
    private String filePath;

    @RequestMapping("/toUp")
    public String toUp(HttpServletRequest request){
        return "system/import/import";
    }

    @RequestMapping("/up")
    public String up(@RequestParam("file") MultipartFile[] files, Model model){
        for(MultipartFile file : files){
            if(file != null){
                String myFileName = file.getOriginalFilename();
                if(StringUtils.isNotBlank(myFileName.trim())){
                    String fileName = file.getOriginalFilename();
                    String filepath = filePath + fileName;
                    File localFile = new File(filepath);
                    try {
                        file.transferTo(localFile);
                    } catch (IllegalStateException | IOException e) {
                        logger.error("",e);
                        model.addAttribute("msg",e);
                        return "system/import/import";
                    }
                }
            }
        }
        model.addAttribute("msg","上传成功");
        return "system/import/import";
    }

    @RequestMapping("/getFileList")
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

    @RequestMapping("/delete")
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
