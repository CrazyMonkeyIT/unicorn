package com.valueservice.djs.service.mini;

import com.valueservice.djs.db.dao.mini.MiniUserDOMapper;
import com.valueservice.djs.db.entity.mini.MiniUserDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MiniUserService {

    @Resource
    private MiniUserDOMapper miniUserDOMapper;

    public int saveOrUpdate(MiniUserDO record){

        MiniUserDO existsUser = miniUserDOMapper.selectByOpenid(record.getOpenid());
        if(existsUser == null){
            return miniUserDOMapper.insert(record);
        }else {
            record.setId(existsUser.getId());
            record.setCreateTime(existsUser.getCreateTime());
            return miniUserDOMapper.updateByPrimaryKey(record);
        }


    }
}
