package com.ecohub.ecohubbac.repository;

import com.ecohub.ecohubbac.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n WHERE n.category.name = :categoryName")
    List<News> findByCategoryName(@Param("categoryName") String categoryName);
}