package com.squawk.notification.service;

import com.squawk.notification.entity.Record;
import com.squawk.notification.repository.RecordRepository;
import com.squawk.notification.service.Producer;
import com.squawk.notification.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository repository;

    @Autowired
    Producer producer;

    @Transactional(readOnly = true)
    public List<Record> findAll() {
        return (List<Record>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Record findOne(String id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Record with id " + id + " doesn't exist."));
    }

    @Transactional
    public Record create(Record record) {
        producer.sendMessage(record.getEmail(), record.getMessage());
        return repository.save(record);
    }

    @Transactional
    public void delete(String id) {
        Optional<Record> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new ResourceNotFoundException("Record with id " + id + " doesn't exist.");
        }
        repository.delete(existing.get());
    }
}