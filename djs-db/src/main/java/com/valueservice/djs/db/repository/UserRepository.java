package com.valueservice.djs.db.repository;

import com.valueservice.djs.db.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LoginUser,Long>{

}
