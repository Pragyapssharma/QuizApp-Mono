package com.test.quizapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.quizapp.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> 
{

}
