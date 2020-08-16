package com.squawk.notification.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.squawk.notification.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import com.squawk.notification.service.RecordService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/send")

public class InputController {

    @Autowired
    RecordService service;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Record ingest_record(@RequestBody Record record) {
        return service.create(record);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

}