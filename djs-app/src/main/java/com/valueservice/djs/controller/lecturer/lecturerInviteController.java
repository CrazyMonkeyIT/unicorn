package com.valueservice.djs.controller.lecturer;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.controller.BaseController;
import com.valueservice.djs.db.entity.lecturer.LecturerInviteDO;
import com.valueservice.djs.service.lecturer.LecturerInviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 讲师邀请
 * @author shawn
 * @date 2018-03-11
 */
@Controller
@RequestMapping("/lecturerInvite")
public class lecturerInviteController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(lecturerInviteController.class);

    @Resource
    LecturerInviteService lecturerInviteService;

    /**
     * 查询讲师邀请列表
     * @param modelMap
     * @param pageIndex
     * @return
     */
    @RequestMapping("/list")
    public String list(ModelMap modelMap,Integer pageIndex){
        PageInfo<LecturerInviteDO> page = lecturerInviteService.selectList(pageIndex);
        modelMap.addAttribute("page",page);
        return "/lecturer/lecturerInvite";
    }

    /**
     * 通过邀请码接受邀请
     * @param inviteCode
     * @return
     */
    @RequestMapping("/accept")
    @ResponseBody
    public BaseResult accept(Integer inviteCode,String openId){
        return lecturerInviteService.replyLecturerInvite(inviteCode,openId);
    }

    /**
     * 创建邀请
     * @param record
     * @return
     */
    @RequestMapping("/createInvite")
    @ResponseBody
    public BaseResult createInvite(LecturerInviteDO record){
        record.setCreateUserId(getCurrentUser().getUserId().toString());
        return lecturerInviteService.createLecturerInvite(record);
    }
}
