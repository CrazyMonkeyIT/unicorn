package com.valueservice.djs.controller.lecturer;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.controller.BaseController;
import com.valueservice.djs.db.entity.lecturer.LecturerGradeDO;
import com.valueservice.djs.db.entity.lecturer.LecturerInviteDO;
import com.valueservice.djs.service.lecturer.LecturerGradeService;
import com.valueservice.djs.service.lecturer.LecturerInviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 讲师邀请
 * @author shawn
 * @date 2018-03-11
 */
@Controller
@RequestMapping("/lecturer/invite")
public class LecturerInviteController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(LecturerInviteController.class);

    @Resource
    LecturerInviteService lecturerInviteService;

    @Resource
    LecturerGradeService lecturerGradeService;
    /**
     * 查询讲师邀请列表
     * @param modelMap
     * @param pageIndex
     * @return
     */
    @RequestMapping("/list")
    public String list(ModelMap modelMap,Integer pageIndex){
        PageInfo<LecturerInviteDO> page = lecturerInviteService.selectList(pageIndex);
        List<LecturerGradeDO> list = lecturerGradeService.selectLecturerGradeList();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("gradeList",list);
        return "/lecturer/lecturerInvite";
    }

    /**
     * 通过邀请码接受邀请
     * @param inviteCode
     * @return
     */
    @RequestMapping({"/accept", "/noauth/accept"})
    @ResponseBody
    public BaseResult accept(Integer inviteCode,String openId){
        return lecturerInviteService.replyLecturerInvite(inviteCode,openId);
    }
    /**
     * 通过邀请码查询讲师邀请信息
     * @param inviteCode
     * @return
     */
    @RequestMapping({"/getLecturerByInviteCode", "/noauth/getLecturerByInviteCode"})
    @ResponseBody
    public BaseResult getLecturerByInviteCode(Integer inviteCode){
        BaseResult result = new BaseResult();
        LecturerInviteDO record = lecturerInviteService.selectByInviteCode(inviteCode);
        if(record != null){
            result.setResult(true);
            result.setObj(record);
        }else{
            result.setMessage("未找到符合邀请码的邀请信息");
        }
        return result;
    }
    /**
     * 创建邀请
     * @param record
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public BaseResult createInvite(LecturerInviteDO record){
        record.setCreateUserId(getCurrentUser().getUserId().toString());
        return lecturerInviteService.createLecturerInvite(record);
    }

    /**
     * 删除讲师邀请
     * @param id
     * @return
     */
    @RequestMapping("/del")
    public @ResponseBody BaseResult delete(Integer id){
        return lecturerInviteService.delete(id);
    }

    /**
     * 获取邀请信息
     * @param id
     * @return
     */
    @RequestMapping("/get")
    public @ResponseBody BaseResult selectById(Integer id){
        return lecturerInviteService.selectById(id);
    }
}
