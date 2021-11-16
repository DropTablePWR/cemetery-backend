package com.droptablepwr.cemetery.repository;

import com.droptablepwr.cemetery.model.Cemetery;
import com.droptablepwr.cemetery.model.projection.CemeteryInfoBasic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CemeteryRepository extends JpaRepository<Cemetery, Integer> {
    boolean existsByName(String name);

    boolean existsById(Integer id);

//    @Query("select c from Cemetery c")
    <T> List<T> findBy(Class<T> type);

    <T> Optional<T> findById(Integer id, Class<T> type);

}
