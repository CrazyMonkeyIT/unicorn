package com.valueservice.djs.db.dao.lecturer;

import com.valueservice.djs.db.entity.lecturer.LecturerInviteDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LecturerInviteDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(LecturerInviteDO record);

    LecturerInviteDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LecturerInviteDO record);

    LecturerInviteDO selectByInviteCode(@Param("inviteCode")Integer inviteCode);

    List<LecturerInviteDO> selectList();
}