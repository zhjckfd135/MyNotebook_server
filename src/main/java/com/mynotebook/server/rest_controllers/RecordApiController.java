package com.mynotebook.server.rest_controllers;

import com.mynotebook.server.models.AccessLevel;
import com.mynotebook.server.models.Record;
import com.mynotebook.server.services.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RecordApiController {
    @Autowired
    RecordsService service;

    @PostMapping("/records/newRecord")
    public ResponseEntity createRecordAndAddPrimary(@RequestParam("user_id") int user_id,
                                                    @RequestParam("title") String title,
                                                    @RequestParam("data") String data
                                                    ){
        Record record = service.createRecord(data, title);
        if(record == null)
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

        ResponseEntity answer = addPrimary(user_id, record.getRecord_id(), AccessLevel.WRITE_AND_READ.getValue());
        if(answer.getStatusCode().isError())
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/records/addPrimary")
    public ResponseEntity addPrimary(@RequestParam("user_id") int user_id,
                                     @RequestParam("record_id") int record_id,
                                     @RequestParam("access_level") int access_level){
        int res = service.addPermission(user_id, record_id, AccessLevel.getEnum(access_level));
        if(res != 1)
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);


        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
