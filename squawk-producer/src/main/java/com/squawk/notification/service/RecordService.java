package com.squawk.notification.service;

import com.squawk.notification.entity.Record;

import java.util.List;

public interface RecordService {
    List<Record> findAll();

    Record findOne(String id);

    Record create(Record record);

    void delete(String id);
}