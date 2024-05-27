package com.mynotebook.server.repository;

import com.mynotebook.server.models.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RecordRepository extends CrudRepository<Record, Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO records(data) VALUES(:data)", nativeQuery = true)
    Long createRecord(@Param("data") String data);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_records_access(user_id, record_id) VALUES(:user_id, :record_id)", nativeQuery = true)
    int addPermission(@Param("user_id") int user_id,
                      @Param("record_id") int record_id);
}
