package com.valueservice.djs.service.mini;

import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.db.dao.mini.MiniUserDOMapper;
import com.valueservice.djs.db.dao.mini.UserTradeRecordDOMapper;
import com.valueservice.djs.db.dao.mini.UserVipDOMapper;
import com.valueservice.djs.db.dao.mini.UserVipRecordDOMapper;
import com.valueservice.djs.db.entity.mini.MiniUserDO;
import com.valueservice.djs.db.entity.mini.UserTradeRecordDO;
import com.valueservice.djs.db.entity.mini.UserVipDO;
import com.valueservice.djs.db.entity.mini.UserVipRecordDO;
import com.valueservice.djs.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UserVipService {

    @Resource
    UserVipDOMapper userVipDOMapper;

    @Resource
    MiniUserDOMapper miniUserDOMapper;

    @Resource
    UserVipRecordDOMapper userVipRecordDOMapper;

    @Resource
    UserTradeRecordDOMapper userTradeRecordDOMapper;
    /**
     * 查询所有vip列表
     * @return
     */
    public List<UserVipDO> selectAllVipList(){
        return userVipDOMapper.selectAllList();
    }

    /**
     * 处理开通VIP
     */
    public BaseResult openMember(Integer vipId, String openId){
        BaseResult result= new BaseResult();
        UserVipDO vip = userVipDOMapper.selectByPrimaryKey(vipId);
        MiniUserDO user = miniUserDOMapper.selectByOpenId(openId);

        //修改用户信息
        Long invalidTime = new Long(vip.getValidDay() * 60 * 60 * 24 * 1000);
        user.setVipInvalidTime(new Timestamp(invalidTime));
        user.setIsVip(1);
        user.setTotalPayAmount(user.getTotalPayAmount() - vip.getOpenMoney().intValue());
        miniUserDOMapper.updateByPrimaryKeySelective(user);

        //保存开通记录
        UserVipRecordDO userVipRecord = new UserVipRecordDO();
        userVipRecord.setCreateTime(DateUtil.currentTimestamp());
        userVipRecord.setMiniUserId(user.getId());
        userVipRecord.setUserVipId(vip.getId());
        userVipRecord.setOpenMoney(vip.getOpenMoney().toString());
        int rows = userVipRecordDOMapper.insertSelective(userVipRecord);

        //保存交易记录
        UserTradeRecordDO utr = new UserTradeRecordDO();
        utr.setOpenId(openId);
        utr.setTradeAmount(new BigDecimal(0 - vip.getOpenMoney()));
        utr.setTradeType(1);
        userTradeRecordDOMapper.insertSelective(utr);

        if(rows > 0){
            result.setResult(true);
        }
        return result;
    }
}
