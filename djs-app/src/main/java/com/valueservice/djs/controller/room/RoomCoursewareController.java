package com.valueservice.djs.controller.room;

import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.controller.BaseController;
import com.valueservice.djs.db.entity.chat.RoomCoursewareDO;
import com.valueservice.djs.service.room.RoomCoursewareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@Controller
public class RoomCoursewareController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomCoursewareController.class);

    @Resource
    private RoomCoursewareService roomCoursewareService;

    /**
     * 更新房间课件信息
     * @param record
     * @return
     */
    @RequestMapping("/minigram/updateRoomCourseware")
    public @ResponseBody BaseResult updateRoomCourseware(RoomCoursewareDO record){
        BaseResult result = new BaseResult();
        try{
            result = roomCoursewareService.update(record);
        }catch (Exception ex){
            LOGGER.error("更新房间课件信息异常",ex);
        }
        return result;
    }

    /**
     * 查询课件是否上传完毕
     * @param id
     * @return
     */
    @RequestMapping("/minigram/selectCoursewareFinish/{id}")
    public @ResponseBody BaseResult selectCoursewareFinish(@PathVariable Long id){
        BaseResult result = new BaseResult();
        RoomCoursewareDO record = roomCoursewareService.selectByPrimaryKey(id);
        if(Objects.nonNull(record.getCoursewareId()) && Objects.nonNull(record.getHeraldPath())){
            result.setResult(true);
            result.setObj(record);
        }
        return result;
    }

    /**
     * 进入上传课件页
     */
    @RequestMapping("/minigram/intoRoomCourseware/{roomCoursewareId}")
    public String uploadRoomCourseware(ModelMap modelMap, @PathVariable Long roomCoursewareId){
        try {
            RoomCoursewareDO record = roomCoursewareService.selectByPrimaryKey(roomCoursewareId);
            if(!Objects.isNull(record)){
                modelMap.addAttribute("rcid", roomCoursewareId);
            }
        }catch (Exception ex){
            LOGGER.error("进入上传课件页异常",ex);
        }
        return "room/uploadCourseware";
    }

    /**
     * 保存课件
     */
    @RequestMapping("/minigram/saveCoursewareInfo")
    public @ResponseBody BaseResult saveCoursewareInfo(String actualFilePath, String splitFiles){
        BaseResult result = new BaseResult();
        try{
            result = roomCoursewareService.saveCourseware(actualFilePath, splitFiles, 1);
        }catch (Exception ex){
            LOGGER.error("保存课件异常",ex);
        }
        return result;
    }

}
