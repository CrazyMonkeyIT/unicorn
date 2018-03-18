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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private static final Logger log = LoggerFactory.getLogger(LecturerRegisterController.class);

    @Resource
    LecturerRegisterService lecturerRegisterService;

    @Resource
    LecturerGradeService lecturerGradeService;
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
}
