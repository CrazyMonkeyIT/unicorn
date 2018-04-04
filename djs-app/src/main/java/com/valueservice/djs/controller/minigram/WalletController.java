package com.valueservice.djs.controller.minigram;

import com.valueservice.djs.controller.BaseController;
import com.valueservice.djs.db.entity.lecturer.LecturerAccountDO;
import com.valueservice.djs.db.entity.lecturer.LecturerIncomeDO;
import com.valueservice.djs.db.entity.mini.UserTradeRecordDO;
import com.valueservice.djs.service.lecturer.LecturerIncomeService;
import com.valueservice.djs.service.lecturer.LecturerService;
import com.valueservice.djs.service.mini.UserTradeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WalletController.class);

    @Resource
    LecturerService lecturerService;

    @Resource
    LecturerIncomeService lecturerIncomeService;

    @Resource
    UserTradeRecordService userTradeRecordService;
    /**
     * 获取讲师账户信息
     * @param lecturerId
     * @return
     */
    @RequestMapping("/getLecturerAccount")
    public LecturerAccountDO getLecturerAccount(Integer lecturerId){
        return lecturerService.selectLecturerAccount(lecturerId);
    }

    /**
     * 获取讲师收益明细
     * @param lecturerId
     * @return
     */
    @RequestMapping("/getLecturerIncome")
    public List<LecturerIncomeDO> selectLecturerIncome(Integer lecturerId){
        return lecturerIncomeService.selectListByLecturerId(lecturerId);
    }

    /**
     * 查询用户支出
     * @param openId
     * @return
     */
    @RequestMapping("/getUserTradePayRecord")
    public List<UserTradeRecordDO> getUserTradePay(String openId){
        return userTradeRecordService.selectListByOpenId(openId,1);
    }
    /**
     * 查询用户收支
     * @param openId
     * @return
     */
    @RequestMapping("/getUserTradeRecord")
    public List<UserTradeRecordDO> getUserTradeRecord(String openId){
        return userTradeRecordService.selectListByOpenId(openId,null);
    }
}
