package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
