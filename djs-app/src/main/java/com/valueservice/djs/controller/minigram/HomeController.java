package com.valueservice.djs.controller.minigram;

import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.db.entity.chat.RoomDO;
import com.valueservice.djs.db.entity.lecturer.LecturerDO;
import com.valueservice.djs.service.advertisement.AdvertisementService;
import com.valueservice.djs.service.room.RoomService;
import com.valueservice.djs.service.lecturer.LecturerService;
import com.valueservice.djs.service.room.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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
    private LecturerService lecturerService;
    @Resource
    private AdvertisementService advertisementService;
    @Resource
    private RoomService roomService;
    @Resource
    private SubjectService subjectService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody BaseResult getHomeList(){
        BaseResult result = new BaseResult(true);
        Map<String, Object> map = new HashMap<>();
        try {
            map.put(advertisement_key, advertisementService.selectValidAdvertisement());
            map.put(chief_key, lecturerService.selectChiefList(is_chief));
            map.put(road_show_key, roomService.selectByType(1));
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

    @RequestMapping(value = "/chief/{lecturerId}/detail", method = RequestMethod.GET)
    public @ResponseBody BaseResult getChiefDetail(@PathVariable Integer lecturerId){
        BaseResult result = new BaseResult(true);
        try {
            LecturerDO lecturerDO = lecturerService.selectById(lecturerId);
            List<RoomDO> roomDOList = roomService.selectByLecturerId(lecturerId);
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("lecturerInfo", lecturerDO);
            resultMap.put("roomList", roomDOList);
            result.setObj(resultMap);
        }catch (Exception ex){
            LOGGER.error("", ex);
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/live/list", method = RequestMethod.GET)
    public @ResponseBody BaseResult getLiveList(){
        BaseResult result = new BaseResult(true);
        try {
            result.setObj(roomService.selectLiveRoom());
        }catch (Exception ex){
            LOGGER.error("", ex);
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/room/{type}/list", method = RequestMethod.GET)
    public @ResponseBody BaseResult getRoomList(@PathVariable Integer type){
        BaseResult result = new BaseResult(true);
        try {
            result.setObj(roomService.selectByType(type));
        }catch (Exception ex){
            LOGGER.error("", ex);
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/subject/{subjectId}/rooms", method = RequestMethod.GET)
    public @ResponseBody BaseResult getSubjectRooms(@PathVariable Integer subjectId){
        BaseResult result = new BaseResult(true);
        try {
            result.setObj(roomService.selectBySubjectId(subjectId));
        }catch (Exception ex){
            LOGGER.error("", ex);
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/subject/list", method = RequestMethod.GET)
    public @ResponseBody BaseResult getSubjectList(){
        BaseResult result = new BaseResult(true);
        try {
            result.setObj(subjectService.selectValidList());
        }catch (Exception ex){
            LOGGER.error("", ex);
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/search/{inputValue}", method = RequestMethod.GET)
    public @ResponseBody BaseResult search(@PathVariable String inputValue){
        BaseResult result = new BaseResult(true);
        try {
            Map<String, Object> map = new HashMap<>();
            List<RoomDO> roomDOList = roomService.searchRoomByName(inputValue);
            List<LecturerDO> lecturerDOList = lecturerService.searchLecturerByName(inputValue);
            map.put("roomList", roomDOList);
            map.put("lecturerList", lecturerDOList);
            result.setObj(map);
        }catch (Exception ex){
            LOGGER.error("", ex);
            result.setResult(false);
            result.setMessage(ex.getMessage());
        }
        return result;
    }


}
