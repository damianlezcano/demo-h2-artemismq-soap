package com.example.mockapi.repository;

import com.example.mockapi.model.SampleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleDataRepository extends JpaRepository<SampleData, Long> {
}
