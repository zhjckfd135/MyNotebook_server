package com.mynotebook.server.services;

import com.mynotebook.server.models.AccessLevel;
import com.mynotebook.server.models.Primary;
import com.mynotebook.server.models.Record;
import com.mynotebook.server.repository.RecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordsService {
    @Autowired
    RecordRepository repository;

    public int addPermission(int user_id, int record_id, AccessLevel level){
        return repository.addPermission(user_id, record_id, level.getValue());
    }

    @Transactional
    public Record createRecord(String data, String title) {
        repository.createRecord(data, title);
        int id = repository.getLastInsertId();
        return repository.findRecordById(id);
    }
}
