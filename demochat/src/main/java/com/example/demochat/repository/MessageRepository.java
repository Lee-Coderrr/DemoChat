package com.example.demochat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demochat.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    
}
