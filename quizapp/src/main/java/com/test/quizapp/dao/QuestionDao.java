package com.test.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.quizapp.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> 
{
	
	List<Question> findByCategory(String category);

	@Query(value = "Select * from question where category=:category order by RANDOM() Limit :numQ", nativeQuery = true)
	List<Question> findRandomQuesByCategory(String category, int numQ);
	
}
