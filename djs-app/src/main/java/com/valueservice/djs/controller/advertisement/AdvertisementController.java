package com.valueservice.djs.controller.advertisement;

import com.valueservice.djs.service.advertisement.AdvertisementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @author Lee
 * @create 2018-03-07
 */
@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Resource
    private AdvertisementService advertisementService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView selectAdvertisement(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("str1", "test");
        modelAndView.setViewName("system/advertisement/advertisement");
        return modelAndView;
    }

}
