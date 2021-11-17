package com.droptablepwr.cemetery.repository;

import com.droptablepwr.cemetery.model.TombstonesFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TombstonesFeatureRepository extends JpaRepository<TombstonesFeature, Integer> {
    List<TombstonesFeature> getByTombstone_IdAndTombstone_Cemetery_Id(Integer tombstoneId, Integer cemeteryId);

    Boolean existsByIdAndTombstone_IdAndTombstone_Cemetery_Id(Integer id, Integer tombstoneId, Integer cemeteryId);
}
