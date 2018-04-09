package com.valueservice.djs.service.chat;

import com.valueservice.djs.db.dao.chat.SubjectDOMapper;
import com.valueservice.djs.db.entity.chat.SubjectDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectService {

    @Resource
    SubjectDOMapper SubjectDOMapper;

    /**
     * 获取全部主题
     * @return
     */
    public List<SubjectDO> selectList(){
        return SubjectDOMapper.selectList();
    }
}
