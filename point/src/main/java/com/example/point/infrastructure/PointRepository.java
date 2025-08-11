package com.example.point.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<com.example.point.domain.Point, Long> {
    com.example.point.domain.Point findByUserId(Long userId);
}
