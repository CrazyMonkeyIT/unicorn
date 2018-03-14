package com.valueservice.djs.controller.advertisement;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.advertisement.AdvertisementVO;
import com.valueservice.djs.db.entity.advertisement.AdvertisementDO;
import com.valueservice.djs.db.entity.advertisement.AdvertisementTypeDO;
import com.valueservice.djs.db.entity.lecturer.LecturerDO;
import com.valueservice.djs.service.advertisement.AdvertisementService;
import com.valueservice.djs.service.lecturer.LecturerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
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
    @Resource
    private LecturerService lecturerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView selectAdvertisement(){
        ModelAndView modelAndView = new ModelAndView();
        List<AdvertisementTypeDO> advertisementTypeList =  advertisementService.selectAllAdvertisementType();
        List<LecturerDO> lecturerDOList = lecturerService.selectAll();
        PageInfo<AdvertisementDO> pageInfo =  advertisementService.selectValidAdvertisement(1, 10);
        modelAndView.addObject("lecturerList", lecturerDOList);
        modelAndView.addObject("typeList", advertisementTypeList);
        modelAndView.addObject("roomList", Collections.emptyList());
        modelAndView.addObject("page", pageInfo);
        modelAndView.setViewName("system/advertisement/advertisement");
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody String editAdvertisement(AdvertisementVO advertisementVO){
        advertisementService.addAdvertisement(advertisementVO);
        return "success";
    }

/*    @RequestMapping(value = "/type/list", method = RequestMethod.GET)
    public @ResponseBody List<AdvertisementTypeDO> selectAdvertisementType(){
        return advertisementService.selectAllAdvertisementType();
    }*/

}
