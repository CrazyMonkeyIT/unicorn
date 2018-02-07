package com.valueservice.djs.db.repository;

import com.valueservice.djs.db.entity.UserResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResourceRepository extends JpaRepository<UserResource,Long> {

}
