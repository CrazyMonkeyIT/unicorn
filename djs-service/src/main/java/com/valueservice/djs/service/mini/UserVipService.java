package com.valueservice.djs.service.mini;

import com.valueservice.djs.db.dao.mini.UserVipDOMapper;
import com.valueservice.djs.db.entity.mini.UserVipDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserVipService {

    @Resource
    UserVipDOMapper userVipDOMapper;

    /**
     * 查询所有vip列表
     * @return
     */
    public List<UserVipDO> selectAllVipList(){
        return userVipDOMapper.selectAllList();
    }
}
