package com.valueservice.djs.controller.lecturer;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.controller.BaseController;
import com.valueservice.djs.db.entity.lecturer.LecturerAccountDO;
import com.valueservice.djs.db.entity.lecturer.LecturerDO;
import com.valueservice.djs.db.entity.lecturer.LecturerGradeDO;
import com.valueservice.djs.service.lecturer.LecturerGradeService;
import com.valueservice.djs.service.lecturer.LecturerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    LecturerGradeService lecturerGradeService;
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
        List<LecturerGradeDO> gradeList = lecturerGradeService.selectLecturerGradeList();
        modelMap.addAttribute("page",page);
        modelMap.addAttribute("gradeList",gradeList);
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
     * @param accountId
     * @param desc
     * @return
     */
    @RequestMapping("/frozenLecturerAccount")
    @ResponseBody
    public BaseResult frozenLecturerAccount(Integer accountId,String wswitch,String desc){
        return lecturerService.frozenLecturerAccount(accountId,wswitch,desc);
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

    /**
     * 查询讲师信息
     * @param id
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public LecturerDO get(Integer id){
        return lecturerService.selectById(id);
    }
    /**
     * 更新讲师信息
     */
    @RequestMapping("/update")
    @ResponseBody
    public BaseResult update(LecturerDO lecturer){
        return lecturerService.updateInfo(lecturer);
    }

    /**
     * 获取讲师账户信息
     */
    @RequestMapping("/account")
    @ResponseBody
    public LecturerAccountDO account(Integer lecturerId){
        return lecturerService.selectLecturerAccount(lecturerId);
    }

    /**
     * 获取讲师信息，根据OpenId
     * @param openId
     * @return
     */
    @RequestMapping("/getByOpenId")
    @ResponseBody
    public BaseResult getByOpenId(String openId){
        BaseResult result = new BaseResult();
        LecturerDO record = lecturerService.selectByOpenId(openId);
        if(record != null){
            result.setResult(true);
            result.setObj(record);
        }else{
            result.setMessage("未找到对应的讲师");
        }
        return result;
    }

}
