package org.example.repository;

import org.example.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TablesRepository extends JpaRepository<Tables,Long> {

    Tables findByTableNumber(long tableId);
}
