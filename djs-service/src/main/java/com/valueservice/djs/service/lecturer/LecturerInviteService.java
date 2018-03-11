package com.valueservice.djs.service.lecturer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.CommonConst;
import com.valueservice.djs.bean.LecturerStatus;
import com.valueservice.djs.db.dao.lecturer.LecturerAccountDOMapper;
import com.valueservice.djs.db.dao.lecturer.LecturerDOMapper;
import com.valueservice.djs.db.dao.lecturer.LecturerInviteDOMapper;
import com.valueservice.djs.db.entity.lecturer.LecturerAccountDO;
import com.valueservice.djs.db.entity.lecturer.LecturerDO;
import com.valueservice.djs.db.entity.lecturer.LecturerInviteDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * 讲师邀请
 * @author shawn
 * @date 2018-03-11
 */
@Service
public class LecturerInviteService {

    @Resource
    LecturerInviteDOMapper lecturerInviteDOMapper;

    @Resource
    LecturerDOMapper lecturerDOMapper;

    @Resource
    LecturerAccountDOMapper lecturerAccountDOMapper;

    /**
     * 查询邀请列表
     * @param pageIndex
     * @return
     */
    public PageInfo<LecturerInviteDO> selectList(Integer pageIndex){
        PageHelper.startPage(pageIndex == null? 1 : pageIndex , CommonConst.DEFAULT_PAGE_SIZE);
        List<LecturerInviteDO> list = lecturerInviteDOMapper.selectList();
        return new PageInfo<LecturerInviteDO>(list);
    }
    /**
     * 创建讲师邀请
     * @param lecturerInvite
     * @return
     */
    public BaseResult createLecturerInvite(LecturerInviteDO lecturerInvite){
        lecturerInvite.setCreateTime(new Timestamp(System.currentTimeMillis()));
        lecturerInviteDOMapper.insertSelective(lecturerInvite);
        return new BaseResult(true);
    }

    /**
     * 接受邀请
     * @param inviteCode
     * @return
     */
    public BaseResult replyLecturerInvite(Integer inviteCode,String openId){
        //获取讲师邀请信息
        LecturerInviteDO lecturerInvite = lecturerInviteDOMapper.selectByInviteCode(inviteCode);
        //创建讲师信息
        LecturerDO lecturer = new LecturerDO();
        lecturer.setLecturerName(lecturerInvite.getLecturerName());
        lecturer.setOpenId(openId);
        lecturer.setPhone(lecturerInvite.getPhone());
        lecturer.setGradeId(lecturerInvite.getGradeId());
        lecturer.setCompany(lecturerInvite.getCompany());
        lecturer.setPosition(lecturerInvite.getPosition());
        lecturer.setIsChief("NO");
        lecturer.setStatus(LecturerStatus.NORMAL.getCode());
        lecturer.setStatusDesc("首次激活");
        lecturer.setLiveHours("0");
        lecturer.setLiveNumber("0");
        lecturer.setCreateTime(new Timestamp(System.currentTimeMillis()));
        lecturerDOMapper.insertSelective(lecturer);
        //创建讲师账户
        LecturerAccountDO lecturerAccount = new LecturerAccountDO();
        lecturerAccount.setLecturerId(lecturer.getId());
        lecturerAccount.setCreateTime(new Timestamp(System.currentTimeMillis()));
        lecturerAccount.setPaymentRatio(0);
        lecturerAccount.setTotalIncome(new BigDecimal(0));
        lecturerAccount.setWithdrawCash(new BigDecimal(0));
        lecturerAccount.setWithdrawSwitch("ON");
        lecturerAccountDOMapper.insertSelective(lecturerAccount);

        return new BaseResult(true);
    }

}
