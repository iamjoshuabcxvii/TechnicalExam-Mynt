package com.job.technicalexam.repository;

import com.job.technicalexam.model.DatabaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseRepository extends JpaRepository<DatabaseModel, Long> {
}
