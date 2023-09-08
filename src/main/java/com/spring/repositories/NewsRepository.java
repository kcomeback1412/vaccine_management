package com.spring.repositories;

import com.spring.entities.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,String> {
    @Query(value = "select n from News n" +
            " where n.title LIKE %:search% " )
//            "OR n.content LIKE %:search%")
    Page<News> searchByTitle(@Param("search") String search, Pageable pageable);

}
