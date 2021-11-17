package com.droptablepwr.cemetery.repository;

import com.droptablepwr.cemetery.model.Tombstone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TombstoneRepository extends JpaRepository<Tombstone, Integer> {
    Boolean existsByIdAndCemetery_Id(@Param("id") Integer id, @Param("cemeteryId") Integer cemeteryId);

    Optional<Tombstone> findByIdAndCemetery_Id(@Param("id") Integer id, @Param("cemeteryId") Integer cemeteryId);

    //    @Query("select c from Cemetery c")
    <T> List<T> findBy(Class<T> type);

    <T> Optional<T> findById(Integer id, Class<T> type);
}
