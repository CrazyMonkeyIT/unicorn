package com.valueservice.djs.controller.minigram;

import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.controller.lecturer.LecturerController;
import com.valueservice.djs.db.entity.mini.UserVipDO;
import com.valueservice.djs.service.mini.UserVipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * vip管理
 */
@Controller
@RequestMapping("/vip")
public class UserVipController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserVipController.class);

    @Resource
    UserVipService userVipService;

    /**
     * 获取全部vip列表
     * @return
     */
    @RequestMapping("/all")
    @ResponseBody
    public BaseResult getAll(){
        BaseResult result = new BaseResult();
        List<UserVipDO> vipList = userVipService.selectAllVipList();
        if(vipList  != null){
            result.setResult(true);
            result.setObj(vipList);
        }
        return result;
    }
}
