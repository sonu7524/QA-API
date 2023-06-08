package com.driver.QAFetcher.Repository;


import com.driver.QAFetcher.Entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {
    Optional<QuestionEntity> findById(Integer questionId);
}
