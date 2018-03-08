package com.valueservice.djs.controller.advertisement;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.controller.grade.LecturerGradeController;
import com.valueservice.djs.db.entity.advertisement.AdvertisementDO;
import com.valueservice.djs.db.entity.advertisement.AdvertisementTypeDO;
import com.valueservice.djs.service.advertisement.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lee
 * @create 2018-03-07
 */
@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementController.class);

    @Resource
    private AdvertisementService advertisementService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView selectAdvertisement(){
        ModelAndView modelAndView = new ModelAndView();
        List<AdvertisementTypeDO> advertisementTypeList =  advertisementService.selectAllAdvertisementType();
        PageInfo<AdvertisementDO> pageInfo =  advertisementService.selectValidAdvertisement(1, 10);
        modelAndView.addObject("typeList", advertisementTypeList);
        modelAndView.addObject("page", pageInfo);
        modelAndView.setViewName("system/advertisement/advertisement");
        return modelAndView;
    }



}
