package com.mynotebook.server.services;

import com.mynotebook.server.models.AccessLevel;
import com.mynotebook.server.models.Primary;
import com.mynotebook.server.models.Record;
import com.mynotebook.server.repository.RecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Record> getRecords(int user_id){
         Iterable<Primary> primaries = repository.getPrimaries(user_id);
         List<Primary> primaryList = new ArrayList<>();
         primaries.forEach(primaryList::add);
        List<Record> res = new ArrayList<>();

        for (Primary primary:
             primaryList) {
            Record record = repository.findRecordById(primary.getRecord_id());
            res.add(record);
        }

        return res;
    }
}
