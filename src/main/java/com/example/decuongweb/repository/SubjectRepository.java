package com.example.decuongweb.repository;

import com.example.decuongweb.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByMaMon(String maMon);
    Optional<Subject> findByTenMon(String tenMon);
}
