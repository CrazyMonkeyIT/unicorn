package com.valueservice.djs.service.mini;

import com.valueservice.djs.db.dao.mini.UserTradeRecordDOMapper;
import com.valueservice.djs.db.entity.mini.UserTradeRecordDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户交易记录
 */
@Service
public class UserTradeRecordService {

    @Resource
    UserTradeRecordDOMapper userTradeRecordDOMapper;

    /**
     * 查询所有支出记录
     * @param openId
     * @return
     */
    public List<UserTradeRecordDO> selectListByOpenId(String openId,Integer tradeType){
        return userTradeRecordDOMapper.selectListByOpenId(openId,tradeType);
    }

}
