package com.squawk.notification.repository;

import com.squawk.notification.entity.Record;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecordRepository extends CrudRepository<Record, String> {
}