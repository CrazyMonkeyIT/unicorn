package com.valueservice.djs.service.lecturer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.CommonConst;
import com.valueservice.djs.bean.LecturerStatus;
import com.valueservice.djs.db.dao.grade.LecturerGradeDOMapper;
import com.valueservice.djs.db.dao.lecturer.LecturerAccountDOMapper;
import com.valueservice.djs.db.dao.lecturer.LecturerDOMapper;
import com.valueservice.djs.db.dao.lecturer.LecturerRegisterDOMapper;
import com.valueservice.djs.db.entity.grade.LecturerGradeDO;
import com.valueservice.djs.db.entity.lecturer.LecturerAccountDO;
import com.valueservice.djs.db.entity.lecturer.LecturerDO;
import com.valueservice.djs.db.entity.lecturer.LecturerRegisterDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

    @Resource
    LecturerDOMapper lecturerDOMapper;

    @Resource
    LecturerAccountDOMapper lecturerAccountDOMapper;

    @Resource
    private LecturerGradeDOMapper lecturerGradeDOMapper;
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
    public BaseResult examineLecturer(Integer id,String examineResult,Integer gradeId){
        LecturerRegisterDO lecturerRegister = lecturerRegisterDOMapper.selectByPrimaryKey(id);
        if("SUCCESS".equals(examineResult)){
            lecturerRegister.setGradeId(gradeId);
            //创建讲师信息
            LecturerDO lecturer = new LecturerDO();
            lecturer.setLecturerName(lecturerRegister.getLecturerName());
            lecturer.setOpenId(lecturerRegister.getOpenId());
            lecturer.setPhone(lecturerRegister.getPhone());
            lecturer.setGradeId(lecturerRegister.getGradeId());
            lecturer.setCompany(lecturerRegister.getCompany());
            lecturer.setPosition(lecturerRegister.getPosition());
            lecturer.setIsChief("NO");
            lecturer.setStatus(LecturerStatus.NORMAL.getCode());
            lecturer.setStatusDesc("首次激活");
            lecturer.setLiveHours("0");
            lecturer.setLiveNumber("0");
            lecturer.setCreateTime(new Timestamp(System.currentTimeMillis()));
            lecturerDOMapper.insertSelective(lecturer);
            //创建讲师账户
            LecturerGradeDO lecturerGrade = lecturerGradeDOMapper.selectByPrimaryKey(lecturer.getGradeId());
            LecturerAccountDO lecturerAccount = new LecturerAccountDO();
            lecturerAccount.setLecturerId(lecturer.getId());
            lecturerAccount.setCreateTime(new Timestamp(System.currentTimeMillis()));
            lecturerAccount.setPaymentRatio(lecturerGrade.getPaymentRatio());
            lecturerAccount.setTotalIncome(new BigDecimal(0));
            lecturerAccount.setWithdrawCash(new BigDecimal(0));
            lecturerAccount.setWithdrawSwitch("ON");
            lecturerAccountDOMapper.insertSelective(lecturerAccount);
        }
        lecturerRegister.setStatus(examineResult);
        lecturerRegisterDOMapper.updateByPrimaryKeySelective(lecturerRegister);

        return new BaseResult(true);
    }
}
