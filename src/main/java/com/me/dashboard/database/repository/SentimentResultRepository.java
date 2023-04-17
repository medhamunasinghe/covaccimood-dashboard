package com.me.dashboard.database.repository;

import com.me.dashboard.database.entities.SentimentResult;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface SentimentResultRepository extends CrudRepository<SentimentResult, Long> {

    // @Query(value = "SELECT COUNT(id) FROM covaccimood-db.sentence where sentiment ='Positive'", nativeQuery = true)
    // Long positiveCount();

    // @Query(value = "SELECT COUNT(id) FROM covaccimood-db.sentence where sentiment ='Negative'", nativeQuery = true)
    // Long negativeCount();

    // @Query(value = "SELECT COUNT(id) FROM covaccimood-db.sentence where sentiment ='Neutral'", nativeQuery = true)
    // Long neutralCount();

    Long countAllBySentiment(String sentiment);

    @Override
    List<Platform> findAll();
}
