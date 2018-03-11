package com.valueservice.djs.controller.lecturer;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.controller.BaseController;
import com.valueservice.djs.controller.system.LoginController;
import com.valueservice.djs.db.entity.lecturer.LecturerDO;
import com.valueservice.djs.service.lecturer.LecturerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 讲师管理
 * @author shawn
 * @date 2018-03-11
 */
@Controller
@RequestMapping("/lecturer")
public class LecturerController extends BaseController{
    private static final Logger log = LoggerFactory.getLogger(LecturerController.class);

    @Resource
    LecturerService lecturerService;

    /**
     * 列表
     * @param modelMap
     * @param lecturerName
     * @param phone
     * @param pageIndex
     * @return
     */
    @RequestMapping("/list")
    public String list(ModelMap modelMap,String lecturerName, String phone, Integer pageIndex){
        PageInfo<LecturerDO> page = lecturerService.selectList(lecturerName,phone,pageIndex);
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("lecturerName",lecturerName);
        modelMap.addAttribute("phone",phone);
        return "lecturer/lecturer";
    }

    /**
     * 封停账号
     * @param id
     * @param desc
     * @return
     */
    @RequestMapping("/frozenLecturer")
    @ResponseBody
    public BaseResult frozenLecturer(Integer id,String desc){
        return lecturerService.frozenLecturer(id,desc);
    }

    /**
     * 封停提现
     * @param id
     * @param desc
     * @return
     */
    @RequestMapping("/frozenLecturerAccount")
    @ResponseBody
    public BaseResult frozenLecturerAccount(Integer id,String desc){
        return lecturerService.frozenLecturerAccount(id,desc);
    }

    /**
     * 推荐首席
     * @param id
     * @return
     */
    @RequestMapping("/recommendChief")
    @ResponseBody
    public BaseResult recommendChief(Integer id){
        return lecturerService.recommendChief(id);
    }
}
