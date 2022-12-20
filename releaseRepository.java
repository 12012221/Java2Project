package com.example.springproject.mapper;

import com.example.springproject.domain.Release;
import org.springframework.data.jpa.repository.JpaRepository;

public interface releaseRepository extends JpaRepository<Release, Long> {
}
