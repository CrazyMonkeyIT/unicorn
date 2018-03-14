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
    LecturerAccountDOMapper lecturerAccountDOMapper;

    public List<LecturerDO> selectAll(){
        return lecturerDOMapper.selectAll();
    }

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
     * 封停账号
     * @param lecturerId
     * @param describle
     * @return
     */
    public BaseResult frozenLecturer(Integer lecturerId,String describle){
        LecturerDO dbRecord = lecturerDOMapper.selectByPrimaryKey(lecturerId);
        LecturerDO lecturer = new LecturerDO();
        lecturer.setId(lecturerId);
        lecturer.setStatus(dbRecord.getStatus() == LecturerStatus.FROZEN.getCode() ? LecturerStatus.NORMAL.getCode() : LecturerStatus.FROZEN.getCode());
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
        LecturerAccountDO old = lecturerAccountDOMapper.selectByLecturerId(lecturerId);
        LecturerAccountDO lecturerAccount = lecturerAccountDOMapper.selectByLecturerId(lecturerId);
        lecturerAccount.setWithdrawSwitch(old.getWithdrawSwitch().equals("OFF") ? "ON" : "OFF");
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
        LecturerDO old = lecturerDOMapper.selectByPrimaryKey(lecturerId);
        LecturerDO lecturer = new LecturerDO();
        lecturer.setId(lecturerId);
        lecturer.setIsChief(old.getIsChief().equals("YES") ? "NO" : "YES");
        lecturerDOMapper.updateByPrimaryKeySelective(lecturer);
        return new BaseResult(true);
    }

}
