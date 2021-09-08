package com.HIT.reactintegration.repositories;

import com.HIT.reactintegration.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecordRepository extends JpaRepository<Record, UUID> {
}
