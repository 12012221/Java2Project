package com.example.springproject.mapper;

import com.example.springproject.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface issueRepository extends JpaRepository<Issue, Long> {
}
