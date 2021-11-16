package com.droptablepwr.cemetery.repository;

import com.droptablepwr.cemetery.model.Tombstone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TombstoneRepository extends JpaRepository<Tombstone, Integer> {
    boolean existsById(Integer id);

    //    @Query("select c from Cemetery c")
    <T> List<T> findBy(Class<T> type);

    <T> Optional<T> findById(Integer id, Class<T> type);
}
