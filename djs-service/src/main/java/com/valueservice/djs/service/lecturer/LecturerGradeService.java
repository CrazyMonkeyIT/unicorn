package com.valueservice.djs.service.lecturer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.CommonConst;
import com.valueservice.djs.db.dao.lecturer.LecturerGradeDOMapper;
import com.valueservice.djs.db.entity.lecturer.LecturerGradeDO;
import com.valueservice.djs.util.DateUtil;
import com.valueservice.djs.util.UnicornUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LecturerGradeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LecturerGradeService.class);

    @Resource
    private LecturerGradeDOMapper lecturerGradeDOMapper;

    /**
     * 分页获取有效的讲师等级列表
     * @param pageNum   第几页
     * @param pageSize  每页大小
     * @return
     */
    public PageInfo<LecturerGradeDO> selectLecturerGradeList(Integer pageNum, Integer pageSize){
        pageNum = null == pageNum ? CommonConst.DEFAULT_PAGE_NUM : pageNum;
        pageSize = null == pageSize ? CommonConst.DEFAULT_PAGE_SIZE : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<LecturerGradeDO> list = lecturerGradeDOMapper.selectAll();
        PageInfo<LecturerGradeDO> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 新增讲师等级信息
     * @param gradeName     讲师等级名称
     * @param paymentRatio  讲师分成比例
     */
    public void addLecturerGradeData(String gradeName, Integer paymentRatio){
        LecturerGradeDO record = new LecturerGradeDO();
        record.setGradeName(gradeName);
        record.setPaymentRatio(paymentRatio);
        record.setIsValid(CommonConst.VALID_VALUE);
        record.setCreateTime(DateUtil.currentDate());
        lecturerGradeDOMapper.insertSelective(record);
        LOGGER.info("新增讲师等级成功，保存信息为 【{}】", UnicornUtil.toJson(record));
    }

    /**
     * 修改讲师等级信息
     * @param lecturerGradeId   主键ID
     * @param gradeName         讲师等级名称
     * @param paymentRatio      讲师分成比例
     */
    public void updateLecturerGradeInfo(Integer lecturerGradeId,String gradeName, int paymentRatio){
        LecturerGradeDO existsRecord = lecturerGradeDOMapper.selectByGradeName(gradeName);
        if(null != existsRecord)
            throw new RuntimeException("此讲师等级名称已经存在");
        LecturerGradeDO dbRecord = lecturerGradeDOMapper.selectByPrimaryKey(lecturerGradeId);
        if(null == dbRecord)
            throw new NullPointerException("无法修改此讲师等级信息");
        if(CommonConst.VALID_VALUE != dbRecord.getIsValid())
            throw new RuntimeException("讲师等级不存在");
        dbRecord.setGradeName(gradeName);
        dbRecord.setPaymentRatio(paymentRatio);
        dbRecord.setUpdateTime(DateUtil.currentDate());
        lecturerGradeDOMapper.updateByPrimaryKeySelective(dbRecord);
        LOGGER.info("修改讲师等级信息成功，修改后信息为【{}】", UnicornUtil.toJson(dbRecord));
    }

    /**
     * 逻辑删除讲师等级
     * @param lecturerGradeId   主键ID
     */
    public void delLecturerGrade(Integer lecturerGradeId){
        LecturerGradeDO record = new LecturerGradeDO();
        record.setLecturerGradeId(lecturerGradeId);
        record.setIsValid(CommonConst.INVALID_VALUE);
        record.setUpdateTime(DateUtil.currentDate());
        lecturerGradeDOMapper.updateByPrimaryKeySelective(record);
    }


    /**
     * 获取有效的讲师等级列表
     * @return
     */
    public List<LecturerGradeDO> selectLecturerGradeList(){
        List<LecturerGradeDO> list = lecturerGradeDOMapper.selectAll();
        return list;
    }
}
