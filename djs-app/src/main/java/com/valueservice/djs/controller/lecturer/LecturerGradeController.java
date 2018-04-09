package com.valueservice.djs.controller.lecturer;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.db.entity.lecturer.LecturerGradeDO;
import com.valueservice.djs.service.lecturer.LecturerGradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/lecturer/grade")
public class LecturerGradeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerGradeController.class);

    @Resource
    private LecturerGradeService lecturerGradeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView selectLecturerGrade(Integer pageNum){
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<LecturerGradeDO> pageInfo =  lecturerGradeService.selectLecturerGradeList(pageNum, null);
        modelAndView.addObject("page",pageInfo);
        modelAndView.setViewName("system/grade/lecturerGrade");
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addLecturerGrade(Integer lecturerGradeId, String gradeName, Integer paymentRatio){
        try {
            if(null == lecturerGradeId){
                lecturerGradeService.addLecturerGradeData(gradeName, paymentRatio);
            }else{
                lecturerGradeService.updateLecturerGradeInfo(lecturerGradeId, gradeName , paymentRatio);
            }
        }catch (Exception ex){
            LOGGER.error("", ex);
            return false;
        }
        return true;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateLecturerGrade(Integer lecturerGradeId, String gradeName, Integer paymentRatio){
        try {
            lecturerGradeService.updateLecturerGradeInfo(lecturerGradeId, gradeName , paymentRatio);
        }catch (Exception ex){
            LOGGER.error("", ex);
            return false;
        }
        return true;
    }
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delLecturerGrade(Integer lecturerGradeId){
        try {
            lecturerGradeService.delLecturerGrade(lecturerGradeId);
        }catch (Exception ex){
            LOGGER.error("", ex);
            return false;
        }
        return true;
    }

}
