package com.valueservice.djs.service;

import com.valueservice.djs.db.dao.system.ResourcesDOMapper;
import com.valueservice.djs.db.entity.system.ResourcesDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourceService {

    @Resource
    private ResourcesDOMapper resourcesDOMapper;

    public List<ResourcesDO> selectByUserId(Long userId){
        return resourcesDOMapper.selectByUserId(userId);
    }

    public List<ResourcesDO> selectAll(){
        return resourcesDOMapper.selectAllActive();
    }
}
