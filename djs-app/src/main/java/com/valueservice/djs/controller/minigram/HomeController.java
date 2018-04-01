package com.valueservice.djs.controller.minigram;

import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.service.advertisement.AdvertisementService;
import com.valueservice.djs.service.chat.RoomService;
import com.valueservice.djs.service.lecturer.LecturerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lee on 2018/4/1.
 */
@Controller
@RequestMapping("/mini/home")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private static final String advertisement_key = "advertisementList";
    private static final String chief_key = "chiefList";
    private static final String road_show_key = "roadShowList";
    private static final String live_key = "liveList";
    private static final String is_chief = "YES";
    private static final int home_data_list_size = 5;

    @Resource
    LecturerService lecturerService;
    @Resource
    private AdvertisementService advertisementService;
    @Resource
    private RoomService roomService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody BaseResult getHomeList(){
        BaseResult result = new BaseResult(true);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(advertisement_key, advertisementService.selectValidAdvertisement());
            map.put(chief_key, lecturerService.selectChiefList(is_chief));
            map.put(road_show_key, null);
            map.put(live_key, roomService.selectLiveRoom());
            result.setObj(map);
        }catch (Exception ex){
            LOGGER.error("", ex);
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/advertisement/list", method = RequestMethod.GET)
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

    @RequestMapping(value = "/chief/list", method = RequestMethod.GET)
    public @ResponseBody BaseResult getChiefList(){
        BaseResult result = new BaseResult(true);
        try {
            result.setObj(lecturerService.selectChiefList(is_chief));
        }catch (Exception ex){
            LOGGER.error("", ex);
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

}
