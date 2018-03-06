package com.valueservice.djs.controller.grade;

import com.valueservice.djs.service.grade.LecturerGradeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/lecturer/grade")
public class LecturerGradeController {

    @Resource
    private LecturerGradeService lecturerGradeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView selectLecturerGrade(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("str1","hello world");
        modelAndView.setViewName("system/grade/lecturerGrade");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addLecturerGrade(){

    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateLecturerGrade(){

    }
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public void delLecturerGrade(){

    }

}
