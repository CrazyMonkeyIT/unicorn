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
 * @author shawn
 * @date 2018-03-06
 */
@Service
public class LecturerService {

    @Resource
    LecturerDOMapper lecturerDOMapper;

    @Resource
    LecturerInviteDOMapper lecturerInviteDOMapper;

    @Resource
    LecturerAccountDOMapper lecturerAccountDOMapper;
    /**
     * 查询讲师列表
     * @param lecturerName
     * @param phone
     * @param pagaIndex
     * @return
     */
    public PageInfo<LecturerDO> selectList(String lecturerName,String phone,Integer pagaIndex){
        PageHelper.startPage(pagaIndex == null? 1 : pagaIndex, CommonConst.DEFAULT_PAGE_SIZE);
        List<LecturerDO> list = lecturerDOMapper.selectList(lecturerName,phone);
        return new PageInfo<LecturerDO>(list);
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
    public BaseResult replyLecturerInvite(Integer inviteCode){
        //获取讲师邀请信息
        LecturerInviteDO lecturerInvite = lecturerInviteDOMapper.selectByInviteCode(inviteCode);
        //创建讲师信息
        LecturerDO lecturer = new LecturerDO();
        lecturer.setLecturerName(lecturerInvite.getLecturerName());
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

    /**
     * 封停账号
     * @param lecturerId
     * @param describle
     * @return
     */
    public BaseResult frozenLecturer(Integer lecturerId,String describle){
        LecturerDO lecturer = new LecturerDO();
        lecturer.setId(lecturerId);
        lecturer.setStatus(LecturerStatus.FROZEN.getCode());
        lecturer.setStatusDesc(describle);
        lecturerDOMapper.updateByPrimaryKeySelective(lecturer);
        return new BaseResult(true);
    }

    /**
     * 封停讲师账户提现
     * @param lecturerId
     * @param describle
     * @return
     */
    public BaseResult frozenLecturerAccount(Integer lecturerId,String describle){
        LecturerAccountDO lecturerAccount = lecturerAccountDOMapper.selectByLecturerId(lecturerId);
        lecturerAccount.setWithdrawSwitch("OFF");
        lecturerAccount.setWithdrawOffDesc(describle);
        lecturerAccountDOMapper.updateByPrimaryKeySelective(lecturerAccount);
        return new BaseResult(true);
    }

    /**
     * 推荐首席
     * @param lecturerId
     * @return
     */
    public BaseResult recommendChief(Integer lecturerId){
        LecturerDO lecturer = new LecturerDO();
        lecturer.setId(lecturerId);
        lecturer.setIsChief("YES");
        lecturerDOMapper.updateByPrimaryKeySelective(lecturer);
        return new BaseResult(true);
    }

}
