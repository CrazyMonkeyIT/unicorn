package com.valueservice.djs.service.lecturer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.valueservice.djs.bean.BaseResult;
import com.valueservice.djs.bean.CommonConst;
import com.valueservice.djs.db.dao.lecturer.LecturerDOMapper;
import com.valueservice.djs.db.dao.lecturer.WithdrawExamineDOMapper;
import com.valueservice.djs.db.entity.lecturer.LecturerDO;
import com.valueservice.djs.db.entity.lecturer.WithdrawExamineDO;
import com.valueservice.djs.service.pay.EnterprisePayService;
import com.valueservice.djs.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Resource
    LecturerDOMapper lecturerDOMapper;

    @Resource
    EnterprisePayService enterprisePayService;
    /**
     * 查询提现申请列表
     * @param pageIndex
     * @return
     */
    public PageInfo<WithdrawExamineDO> selectList(Integer pageIndex){
        PageHelper.startPage(pageIndex == null ? 1 : pageIndex, CommonConst.DEFAULT_PAGE_SIZE);
        List<WithdrawExamineDO> list = withdrawExamineDOMapper.selectByList();
        return new PageInfo<WithdrawExamineDO>(list);
    }

    /**
     * 提交提现申请
     * @param record
     * @return
     */

    public BaseResult save(WithdrawExamineDO record){
        BaseResult result = new BaseResult();
        record.setStatus("WAIT");
        record.setCreateTime(DateUtil.currentTimestamp());
        int rows = withdrawExamineDOMapper.insertSelective(record);
        if(rows > 0){
            result.setResult(true);
        }
        return result;
    }
    /**
     * 处理提现申请
     * @param id
     * @param handleResult (WAIT:等待审核  ALREADY: 已通过 REFUSE: 已拒绝)
     * @return
     */
    public BaseResult handle(Integer id,String handleResult){
        BaseResult result = new BaseResult();
        WithdrawExamineDO record = withdrawExamineDOMapper.selectByPrimaryKey(id);
        if("ALREADY".equals(handleResult)){
            LecturerDO lecturer = lecturerDOMapper.selectByPrimaryKey(record.getLecturerId());
            BaseResult br = enterprisePayService.pay(lecturer.getOpenId(), record.getWithdrawMoney().intValue(), "提现");
            if(br.getResult()){
                record.setStatus(handleResult);
                withdrawExamineDOMapper.updateByPrimaryKeySelective(record);
                result.setResult(true);
            }else{
                result.setMessage("支付失败：" + br.getMessage());
            }
        }else{
            record.setStatus(handleResult);
            withdrawExamineDOMapper.updateByPrimaryKeySelective(record);
            result.setResult(true);
        }
        return result;
    }
}
