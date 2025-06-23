package com.example.decuongweb.repository;

import com.example.decuongweb.model.DeCuong;
import com.example.decuongweb.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeCuongRepository extends JpaRepository<DeCuong, Long> {
    List<DeCuong> findBySubject_TenMonContainingIgnoreCase(String keyword);
}
