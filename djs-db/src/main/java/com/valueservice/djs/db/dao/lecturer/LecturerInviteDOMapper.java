package com.valueservice.djs.db.dao.lecturer;

import com.valueservice.djs.db.entity.lecturer.LecturerInviteDO;
import org.apache.ibatis.annotations.Param;

public interface LecturerInviteDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LecturerInviteDO record);

    int insertSelective(LecturerInviteDO record);

    LecturerInviteDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LecturerInviteDO record);

    int updateByPrimaryKey(LecturerInviteDO record);

    LecturerInviteDO selectByInviteCode(@Param("inviteCode")Integer inviteCode);
}