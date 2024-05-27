package com.mynotebook.server.services;

import com.mynotebook.server.models.Record;
import com.mynotebook.server.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class RecordsService {
    @Autowired
    RecordRepository repository;

    public Long createNewRecord(String data){
        return repository.createRecord(data);
    }

    public int addPermission(int user_id, int record_id){
        return repository.addPermission(user_id, record_id);
    }

    private static Record getRecordFromIterable(Iterable<Record> records) {
        if(!records.iterator().hasNext())
            return null;
        return records.iterator().next();
    }
}
