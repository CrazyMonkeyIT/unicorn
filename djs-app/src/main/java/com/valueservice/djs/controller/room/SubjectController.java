package com.valueservice.djs.controller.room;

import com.valueservice.djs.controller.BaseController;
import com.valueservice.djs.db.entity.chat.SubjectDO;
import com.valueservice.djs.service.room.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectController.class);

    @Resource
    SubjectService subjectService;

    /**
     * 获取所有主题类型
     * @return
     */
    @RequestMapping("/noauth/getAllSubject")
    @ResponseBody
    public List<SubjectDO> getAllSubject(){
        List<SubjectDO> list = new ArrayList<>();
        try{
            list = subjectService.selectList();
        }catch (Exception ex){
            LOGGER.error("获取所有主题类型异常",ex);
        }
        return list;
    }
}
