package com.mynotebook.server.services;

import com.mynotebook.server.models.AccessLevel;
import com.mynotebook.server.models.PrimaryProjection;
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
    RecordRepository recordRepository;

    public int addPermission(int user_id, int record_id, AccessLevel level){
        return recordRepository.addPermission(user_id, record_id, level.getValue());
    }

    @Transactional
    public Record createRecord(String data, String title) {
        recordRepository.createRecord(data, title);
        int id = recordRepository.getLastInsertId();
        return recordRepository.findRecordById(id);
    }

    @Transactional
    public int updateRecord(int id ,String data, String title) {
        return recordRepository.updateRecord(id ,data, title);
    }

    public List<Record> getRecords(int user_id){
         Iterable<PrimaryProjection> primaries = recordRepository.getPrimaries(user_id);
         List<PrimaryProjection> primaryList = new ArrayList<>();
         primaries.forEach(primaryList::add);
        List<Record> res = new ArrayList<>();

        for (PrimaryProjection primary:
             primaryList) {
            Record record = recordRepository.findRecordById(primary.getRecord_id());
            res.add(record);
        }

        return res;
    }
}
