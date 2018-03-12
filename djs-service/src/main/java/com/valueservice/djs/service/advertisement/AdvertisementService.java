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
import java.util.List;

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

    public void addAdvertisement(AdvertisementVO advertisementVO){
        AdvertisementDO advertisementDO = new AdvertisementDO();
        advertisementDO.setAdvertisementTypeId(advertisementVO.getAdvertisementTypeId());
        advertisementDO.setAdvertisementUrl(advertisementVO.getAdvertisementUrl());
        advertisementDO.setAdvertisementImgPath(advertisementVO.getAdvertisementImgPath());
        advertisementDO.setAdvertisementTitle(advertisementVO.getAdvertisementTitle());
        advertisementDO.setAdvertisementDesc(advertisementVO.getAdvertisementDesc());
        advertisementDO.setCreateTime(DateUtil.currentDate());
        advertisementDO.setStatus(CommonConst.ADVERTISEMENT_STATUS_NORMAL);
        advertisementDOMapper.insertSelective(advertisementDO);
        //Integer primaryKeyValue = advertisementDO.getAdvertisementId();
    }



}
