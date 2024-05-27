package com.mynotebook.server.rest_controllers;

import com.mynotebook.server.models.Record;
import com.mynotebook.server.services.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;

@RestController
@RequestMapping("/api")
public class RecordApiController {
    @Autowired
    RecordsService service;

    @PostMapping("/records/newRecord")
    public ResponseEntity registerNewName(@RequestParam("user_id") int user_id,
                                          @RequestParam("data") String data){
        Long record = service.createNewRecord(data);

        if(record != 1)
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);

        int res = service.addPermission(user_id, record);
        if(res != 1)
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
