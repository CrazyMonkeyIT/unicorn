package com.valueservice.djs.service.lecturer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.CommonConst;
import com.valueservice.djs.db.dao.lecturer.WithdrawExamineDOMapper;
import com.valueservice.djs.db.entity.lecturer.WithdrawExamineDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 讲师提现表
 * @author shawn
 * @date 2018-03-06
 */
@Service
public class WithdrawExamineService {

    @Resource
    WithdrawExamineDOMapper withdrawExamineDOMapper;

    /**
     * 查询提现申请列表
     * @param pageIndex
     * @return
     */
    public PageInfo<WithdrawExamineDO> selectList(Integer pageIndex){
        PageHelper.offsetPage(pageIndex == null ? 1 : pageIndex, CommonConst.DEFAULT_PAGE_SIZE);
        List<WithdrawExamineDO> list = withdrawExamineDOMapper.selectByList();
        return new PageInfo<WithdrawExamineDO>(list);
    }

    /**
     * 处理提现申请
     * @param id
     * @param handleResult (wait:等待审核  already: 已通过 refuse: 已拒绝)
     * @return
     */
    public BaseResult handle(Integer id,String handleResult){
        WithdrawExamineDO record = withdrawExamineDOMapper.selectByPrimaryKey(id);
        record.setStatus(handleResult);
        withdrawExamineDOMapper.updateByPrimaryKeySelective(record);
        return new BaseResult(true);
    }
}
