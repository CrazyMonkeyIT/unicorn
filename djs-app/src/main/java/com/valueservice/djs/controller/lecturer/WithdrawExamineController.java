package com.valueservice.djs.controller.lecturer;

import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.controller.BaseController;
import com.valueservice.djs.db.entity.lecturer.WithdrawExamineDO;
import com.valueservice.djs.enums.BankCodeEnum;
import com.valueservice.djs.service.lecturer.WithdrawExamineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 讲师提现审核
 * @author shawn
 * @date 2018-03-17
 */
@Controller
@RequestMapping("/lecturer/withdraw")
public class WithdrawExamineController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerRegisterController.class);

    @Resource
    WithdrawExamineService withdrawExamineService;

    /**
     * 提现申请列表
     * @param modelMap
     * @param pageIndex
     * @return
     */
    @RequestMapping("/list")
    public String list(ModelMap modelMap, Integer pageIndex){
        PageInfo<WithdrawExamineDO> page = withdrawExamineService.selectList(pageIndex);
        modelMap.addAttribute("page",page);
        return "/lecturer/withdraw";
    }

    /**
     * 提交提现申请
     * @param record
     * @return
     */
    @RequestMapping({"/submitWithdrawRequest", "/noauth/submitWithdrawRequest"})
    @ResponseBody
    public BaseResult saveWithdraw(WithdrawExamineDO record){
        return withdrawExamineService.save(record);
    }

    /**
     * 处理
     */
    @RequestMapping("/examine")
    @ResponseBody
    public BaseResult examine(Integer id, String handleResult){
        BaseResult result = new BaseResult();
        try{
            result = withdrawExamineService.handle(id, handleResult);
        }catch (Exception ex){
            LOGGER.error("处理讲师提现异常", ex);
        }
        return result;
    }

    /**
     * 获取银行代码集合
     */
    @RequestMapping({"/getBankCodeMap","/noauth/getBankCodeMap"})
    public @ResponseBody  List<Map<String,Object>> getBankCodeMap(){
        return BankCodeEnum.getEnumsMap();
    }
}
