package com.droptablepwr.cemetery.repository;

import com.droptablepwr.cemetery.model.Cemetery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CemeteryRepository extends JpaRepository<Cemetery, Integer> {
    Boolean existsByName(String name);

    List<Cemetery> findAll();
}
