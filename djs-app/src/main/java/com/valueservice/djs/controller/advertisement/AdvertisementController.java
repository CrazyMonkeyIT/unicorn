package com.valueservice.djs.controller.advertisement;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.advertisement.AdvertisementVO;
import com.valueservice.djs.db.entity.advertisement.AdvertisementDO;
import com.valueservice.djs.db.entity.advertisement.AdvertisementTypeDO;
import com.valueservice.djs.db.entity.chat.RoomDO;
import com.valueservice.djs.db.entity.lecturer.LecturerDO;
import com.valueservice.djs.service.advertisement.AdvertisementService;
import com.valueservice.djs.service.chat.RoomService;
import com.valueservice.djs.service.lecturer.LecturerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @Resource
    private LecturerService lecturerService;
    @Resource
    private RoomService roomService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView selectAdvertisement(){
        ModelAndView modelAndView = new ModelAndView();
        List<AdvertisementTypeDO> advertisementTypeList =  advertisementService.selectAllAdvertisementType();
        List<LecturerDO> lecturerDOList = lecturerService.selectAll();
        List<RoomDO> roomList = roomService.selectAll();
        PageInfo<AdvertisementDO> pageInfo =  advertisementService.selectValidAdvertisement(1, 10);
        modelAndView.addObject("lecturerList", lecturerDOList);
        modelAndView.addObject("typeList", advertisementTypeList);
        modelAndView.addObject("roomList", roomList);
        modelAndView.addObject("page", pageInfo);
        modelAndView.setViewName("system/advertisement/advertisement");
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody String editAdvertisement(AdvertisementVO advertisementVO){
        advertisementService.addAdvertisement(advertisementVO);
        return "success";
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public @ResponseBody Boolean delAdvertisement(Integer advertisementId){
        try {
            advertisementService.delAdvertisement(advertisementId, 0);
        }catch (Exception ex){
            LOGGER.error("", ex);
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    public @ResponseBody Boolean stopAdvertisement(Integer advertisementId){
        try {
            advertisementService.delAdvertisement(advertisementId, -2);
        }catch (Exception ex){
            LOGGER.error("", ex);
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/mini/advertisement/list", method = RequestMethod.GET)
    public @ResponseBody BaseResult getAdvertisementList(){
        BaseResult result = new BaseResult(true);
        try {
            result.setObj(advertisementService.selectValidAdvertisement());
        }catch (Exception ex){
            LOGGER.error("", ex);
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

}
