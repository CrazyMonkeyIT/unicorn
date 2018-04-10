package com.valueservice.djs.service.advertisement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.CommonConst;
import com.valueservice.djs.bean.advertisement.AdvertisementVO;
import com.valueservice.djs.db.dao.advertisement.AdvertisementDOMapper;
import com.valueservice.djs.db.dao.advertisement.AdvertisementTypeDOMapper;
import com.valueservice.djs.db.entity.advertisement.AdvertisementDO;
import com.valueservice.djs.db.entity.advertisement.AdvertisementTypeDO;
import com.valueservice.djs.util.DateUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Lee
 * @create 2018-03-07
 */
@Service
public class AdvertisementService {

    @Resource
    private AdvertisementTypeDOMapper advertisementTypeDOMapper;
    @Resource
    private AdvertisementDOMapper advertisementDOMapper;

    /**
     *
     * @return
     */
    public List<AdvertisementTypeDO> selectAllAdvertisementType(){
        return advertisementTypeDOMapper.selectAll();
    }

    /**
     * 分页获取所有广告位信息
     * @param pageNum   第几页
     * @param pageSize  每页大小
     * @return
     */
    public PageInfo<AdvertisementDO> selectValidAdvertisement(Integer pageNum, Integer pageSize){
        pageNum = null == pageNum ? CommonConst.DEFAULT_PAGE_NUM : pageNum;
        pageSize = null == pageSize ? CommonConst.DEFAULT_PAGE_SIZE : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<AdvertisementDO> advertisementDOList = advertisementDOMapper.selectAll();
        PageInfo<AdvertisementDO> pageInfo = new PageInfo<>(advertisementDOList);
        return pageInfo;
    }

    public List<AdvertisementDO> selectValidAdvertisement(){
        return advertisementDOMapper.selectAll();
    }

    public void addAdvertisement(AdvertisementVO advertisementVO){
        AdvertisementDO advertisementDO = new AdvertisementDO();
        advertisementDO.setAdvertisementTypeId(advertisementVO.getAdvertisementTypeId());
        advertisementDO.setAdvertisementUrl(advertisementVO.getAdvertisementUrl());
        advertisementDO.setRoomId(advertisementVO.getRoomId());
        advertisementDO.setLecturerId(advertisementVO.getLecturerId());
        advertisementDO.setAdvertisementImgPath(advertisementVO.getAdvertisementImgPath());
        advertisementDO.setAdvertisementTitle(advertisementVO.getAdvertisementTitle());
        advertisementDO.setAdvertisementDesc(advertisementVO.getAdvertisementDesc());
        advertisementDO.setCreateTime(DateUtil.currentDate());
        advertisementDO.setStatus(CommonConst.ADVERTISEMENT_STATUS_NORMAL);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String invalidDateStr = String.format("%s %s", advertisementVO.getInvalidDateStr(), "23:59:59");
            Date invalidDate = sf.parse(invalidDateStr);
            advertisementDO.setInvalidDate(invalidDate);
        } catch (ParseException e) {
            e.printStackTrace();
            //TODO log
        }
        advertisementDOMapper.insertSelective(advertisementDO);
        //Integer primaryKeyValue = advertisementDO.getAdvertisementId();
    }

    public void delAdvertisement(Integer advertisementId, Integer status){
        AdvertisementDO dbRecord = advertisementDOMapper.selectByPrimaryKey(advertisementId);
        dbRecord.setStatus(status);
        advertisementDOMapper.updateByPrimaryKeySelective(dbRecord);
    }

}
