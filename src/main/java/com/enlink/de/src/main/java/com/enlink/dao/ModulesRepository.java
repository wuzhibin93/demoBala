package com.enlink.de.src.main.java.com.enlink.dao;

import com.enlink.de.src.main.java.com.enlink.model.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ModulesRepository extends JpaRepository<Modules, Integer>, JpaSpecificationExecutor<Modules> {
    List<Modules> findByParentId(Integer parentId);
}
