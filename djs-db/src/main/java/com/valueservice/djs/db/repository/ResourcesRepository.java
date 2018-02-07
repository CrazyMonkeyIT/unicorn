package com.valueservice.djs.db.repository;

import com.valueservice.djs.db.entity.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources,Long> {


}
