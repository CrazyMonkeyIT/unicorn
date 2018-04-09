package com.valueservice.djs.service.chat;

import com.valueservice.djs.db.dao.chat.MsgEventDOMapper;
import com.valueservice.djs.db.entity.chat.MsgEventDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 20:31 2018/4/9
 * @Modified by:
 */
@Service
public class MsgEvevtService {

    @Resource
    private MsgEventDOMapper msgEventDOMapper;


    @Transactional
    public void saveMsgEvent(MsgEventDO msgEventDO){
        msgEventDOMapper.insertSelective(msgEventDO);
    }
}
