package com.valueservice.djs.service.lecturer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.CommonConst;
import com.valueservice.djs.bean.LecturerStatus;
import com.valueservice.djs.bean.chief.ChiefListVO;
import com.valueservice.djs.db.dao.lecturer.LecturerAccountDOMapper;
import com.valueservice.djs.db.dao.lecturer.LecturerDOMapper;
import com.valueservice.djs.db.dao.lecturer.LecturerGradeDOMapper;
import com.valueservice.djs.db.entity.lecturer.LecturerAccountDO;
import com.valueservice.djs.db.entity.lecturer.LecturerDO;
import com.valueservice.djs.db.entity.lecturer.LecturerGradeDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Resource
    LecturerGradeDOMapper lecturerGradeDOMapper;

    public List<LecturerDO> selectAll(){
        return lecturerDOMapper.selectAll();
    }

    /**
     * 模糊匹配讲师信息
     * @param lecturerName  搜索值
     * @return
     */
    public List<LecturerDO> searchLecturerByName(String lecturerName){
        lecturerName = String.format("%s%s%s", "%", lecturerName, "%");
        return lecturerDOMapper.searchLecturerByName(lecturerName);
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
     * 封停/解封账号
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
     * 封停/解封讲师账户提现
     * @param accountId
     * @param describle
     * @return
     */
    public BaseResult frozenLecturerAccount(Integer accountId,String wswitch, String describle){
        LecturerAccountDO lecturerAccount = lecturerAccountDOMapper.selectByPrimaryKey(accountId);
        lecturerAccount.setWithdrawSwitch(wswitch);
        lecturerAccount.setWithdrawOffDesc(describle);
        lecturerAccountDOMapper.updateByPrimaryKeySelective(lecturerAccount);
        return new BaseResult(true);
    }

    /**
     * 推荐/取消首席
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

    /**
     * 更新讲师信息
     * @param record
     * @return
     */
    public BaseResult updateInfo(LecturerDO record){
        lecturerDOMapper.updateByPrimaryKeySelective(record);
        return new BaseResult(true);
    }

    /**
     * 获取讲师信息
     * @return
     */
    public LecturerDO selectById(Integer id){
        return lecturerDOMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取讲师账户信息
     */
    public LecturerAccountDO selectLecturerAccount(Integer lecturerId){
        return lecturerAccountDOMapper.selectByLecturerId(lecturerId);
    }

    /**
     * 获取讲师信息
     * @param openid
     * @return
     */
    public LecturerDO selectByOpenId(String openid){
        LecturerDO record =  lecturerDOMapper.selectByOpenId(openid);
        if(!Objects.isNull(record)) {
            LecturerGradeDO grade = lecturerGradeDOMapper.selectByPrimaryKey(record.getGradeId());
            if(grade != null) {
                record.setGradeName(grade.getGradeName());
            }
        }
        return record;
    }

    /**
     * 获取是否首席讲师
     * @param isChief
     * @return
     */
    public List<ChiefListVO> selectChiefList(String isChief){
        List<ChiefListVO> chiefListVOList = new ArrayList<>();
        List<LecturerDO> lecturerDOList = lecturerDOMapper.selectByChief(isChief);
        for(LecturerDO lecturerDO : lecturerDOList){
            ChiefListVO chiefListVO = new ChiefListVO();
            chiefListVO.setLecturerId(lecturerDO.getId());
            chiefListVO.setLecturerName(lecturerDO.getLecturerName());
            chiefListVO.setCompany(lecturerDO.getCompany());
            chiefListVO.setPosition(lecturerDO.getPosition());
            chiefListVO.setHeadPhotoFile(lecturerDO.getHeadPhotoFile());
            chiefListVO.setLiveNumber(lecturerDO.getLiveNumber());
            chiefListVO.setGradeName(lecturerDO.getGradeName());
            chiefListVO.setIntroduction(lecturerDO.getIntroduction());
            chiefListVOList.add(chiefListVO);
        }
        return chiefListVOList;
    }

    /**
     * 更新讲师账户信息
     */
    public BaseResult updateLecturerAccount(LecturerAccountDO record){
        lecturerAccountDOMapper.updateByPrimaryKeySelective(record);
        return new BaseResult(true);
    }

}
