package com.valueservice.djs.service.lecturer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.CommonConst;
import com.valueservice.djs.db.dao.lecturer.LecturerRegisterDOMapper;
import com.valueservice.djs.db.entity.lecturer.LecturerRegisterDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 讲师注册
 * @author shawn
 * @date 2018-03-11
 */
@Service
public class LecturerRegisterService {

    @Resource
    LecturerRegisterDOMapper lecturerRegisterDOMapper;

    /**
     * 查询讲师注册列表
     * @param lecturerName
     * @param phone
     * @param pageIndex
     * @return
     */
    public PageInfo<LecturerRegisterDO> selectList(String lecturerName,String phone,Integer pageIndex){
        PageHelper.startPage(pageIndex != null? pageIndex : 1, CommonConst.DEFAULT_PAGE_SIZE);
        List<LecturerRegisterDO> list = lecturerRegisterDOMapper.selectList(lecturerName,phone);
        return new PageInfo<LecturerRegisterDO>(list);
    }

    /**
     * 讲师审核
     * @param id
     * @param examineResult 审核结果（SUCCESS：成功 FAILURE：失败）
     * @return
     */
    public BaseResult examineLecturer(Integer id,String examineResult){
        LecturerRegisterDO record = new LecturerRegisterDO();
        record.setId(id);
        record.setStatus(examineResult);
        lecturerRegisterDOMapper.updateByPrimaryKeySelective(record);
        return new BaseResult(true);
    }
}
