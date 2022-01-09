package com.example.MetroStationProject.repository;

import com.example.MetroStationProject.model.Metro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetroRepository extends JpaRepository<Metro,Long> {
}
