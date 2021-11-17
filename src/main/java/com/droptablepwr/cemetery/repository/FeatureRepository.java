package com.droptablepwr.cemetery.repository;

import com.droptablepwr.cemetery.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {
    List<Feature> findAll();
}
