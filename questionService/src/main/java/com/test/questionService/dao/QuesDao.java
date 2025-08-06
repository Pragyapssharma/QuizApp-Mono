package com.test.questionService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.questionService.Question;

@Repository
public interface QuesDao extends JpaRepository<Question, Integer> 
{
	
	List<Question> findByCategory(String category);

	@Query(value = "Select qid from question where category=:category order by RANDOM() Limit :numQ", nativeQuery = true)
	List<Integer> findRandomQuesByCategory(String category, int numQ);
	
}
