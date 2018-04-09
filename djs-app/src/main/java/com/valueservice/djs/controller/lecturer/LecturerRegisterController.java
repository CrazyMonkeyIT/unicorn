package com.valueservice.djs.controller.lecturer;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.controller.BaseController;
import com.valueservice.djs.db.entity.lecturer.LecturerGradeDO;
import com.valueservice.djs.db.entity.lecturer.LecturerRegisterDO;
import com.valueservice.djs.service.lecturer.LecturerGradeService;
import com.valueservice.djs.service.lecturer.LecturerRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

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
     * 提交注册信息
     * @param lecturer
     * @return
     */
    @RequestMapping("/submit")
    @ResponseBody
    public BaseResult submit(@RequestBody LecturerRegisterDO lecturer){
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
