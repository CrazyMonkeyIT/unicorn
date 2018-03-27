package com.valueservice.djs.service.mini;

import com.valueservice.djs.db.dao.mini.MiniUserDOMapper;
import com.valueservice.djs.db.entity.mini.MiniUserDO;
import com.valueservice.djs.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class MiniUserService {

    @Resource
    private MiniUserDOMapper miniUserDOMapper;

    public MiniUserDO saveOrUpdate(MiniUserDO record) throws Exception{
        MiniUserDO existsUser = miniUserDOMapper.selectByOpenid(record.getOpenId());
        if(existsUser == null){
            record.setActive(1);
            record.setIsVip(0);
            record.setCreateTime(new Timestamp(System.currentTimeMillis()));
            miniUserDOMapper.insert(record);
            return record;
        }else {
            record.setId(existsUser.getId());
            miniUserDOMapper.updateByPrimaryKeySelective(record);
            return existsUser;
        }
    }

    /**
     * 通过openId查询用户信息
     * @param openId
     * @return
     */
    public MiniUserDO selectByOpenId(String openId){
        return miniUserDOMapper.selectByOpenid(openId);
    }
}
