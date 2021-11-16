package com.droptablepwr.cemetery.repository;

import com.droptablepwr.cemetery.model.Cemetery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CemeteryRepository extends JpaRepository<Cemetery, Integer> {
    boolean existsByName(String name);

    boolean existsById(Integer id);

    //    @Query("select c from Cemetery c")
    <T> List<T> findBy(Class<T> type);

    <T> Optional<T> findById(Integer id, Class<T> type);

}
