package com.valueservice.djs.controller.lecturer;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.controller.BaseController;
import com.valueservice.djs.db.entity.grade.LecturerGradeDO;
import com.valueservice.djs.db.entity.lecturer.LecturerRegisterDO;
import com.valueservice.djs.service.grade.LecturerGradeService;
import com.valueservice.djs.service.lecturer.LecturerRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * 讲师审核
 * @author shawn
 * @date 2018-03-17
 *
 */
@Controller
@RequestMapping("/lecturer/register")
public class LecturerRegisterController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerRegisterController.class);

    @Resource
    LecturerRegisterService lecturerRegisterService;

    @Resource
    LecturerGradeService lecturerGradeService;

    @Value("${file.path}")
    private String filePath;

    /**
     * 讲师注册列表
     * @param modelMap
     * @param lecturerName
     * @param phone
     * @param pageIndex
     * @return
     */
    @RequestMapping("/list")
    public String list(ModelMap modelMap, String lecturerName, String phone, Integer pageIndex){
        PageInfo<LecturerRegisterDO> page =  lecturerRegisterService.selectList(lecturerName,phone,pageIndex);
        List<LecturerGradeDO> gradeList = lecturerGradeService.selectLecturerGradeList();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("gradeList",gradeList);
        return "/lecturer/lecturerRegister";
    }

    /**
     * 审核讲师
     * @param id
     * @param examineResult
     * @return
     */
    @RequestMapping("/examine")
    public BaseResult examine(Integer id,String examineResult,Integer gradeId){
        return lecturerRegisterService.examineLecturer(id,examineResult,gradeId);
    }

    /**
     * 上传头像
     * @param file
     * @return
     */
    @PostMapping("/saveHeadPhoto")
    public BaseResult saveHeadPhoto(MultipartFile file){
        BaseResult result = new BaseResult();
        if(Objects.isNull(file)){
            return result;
        }
        try {
            File userFile = new File(String.format("%s/%s", filePath, "head"));
            if (!userFile.exists() && !userFile.isDirectory()) {
                userFile.mkdir();
            }
            String fileAllName = file.getOriginalFilename();
            String suffixName = fileAllName.substring(fileAllName.indexOf("."), fileAllName.length());
            String fileNewName = String.format("%s%s", System.currentTimeMillis(),suffixName);
            String currentFilePath = String.format("%s/%s", userFile.getPath(), fileNewName);
            File localFile = new File(currentFilePath);
            file.transferTo(localFile);
            result.setResult(true);
            result.setObj(fileNewName);
        }catch (Exception ex){
            LOGGER.error("上传讲师头像异常",ex);
        }
        return result;
    }

    /**
     * 提交注册信息
     * @param lecturer
     * @return
     */
    @PostMapping("/submit")
    public BaseResult submit(LecturerRegisterDO lecturer){
        BaseResult result = new BaseResult();
        try{
            lecturerRegisterService.insertOrUpdate(lecturer);
            result.setResult(true);
        }catch (Exception ex){
            LOGGER.error("提交讲师申请异常",ex);
        }
        return result;
    }
}
