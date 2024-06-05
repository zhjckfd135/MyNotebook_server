package com.mynotebook.server.repository;

import com.mynotebook.server.models.PrimaryProjection;
import com.mynotebook.server.models.Record;
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
    @Query(value = "INSERT INTO records(data, title) VALUES(:data, :title)", nativeQuery = true)
    void createRecord(@Param("data") String data,
                     @Param("title") String title);

    @Transactional
    @Modifying
    @Query(value = "delete from records where record_id = :record_id", nativeQuery = true)
    int deleteRecord(@Param("record_id") int record_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE records SET data = :data, title= :title WHERE record_id = :id", nativeQuery = true)
    int updateRecord(@Param("id") int id,
                      @Param("data") String data,
                      @Param("title") String title);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    int getLastInsertId();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_records_access(user_id, record_id, access_level) VALUES(:user_id, :record_id, :access_level)", nativeQuery = true)
    int addPermission(@Param("user_id") int user_id,
                      @Param("record_id") int record_id,
                      @Param("access_level") int access_level);
    //1-READ
    //2-WRITE_AND_READ

    @Query("SELECT r FROM Record r WHERE r.id = :id")
    Record findRecordById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "SELECT user_id, record_id, access_level FROM user_records_access WHERE user_id = :user_id", nativeQuery = true)
    Iterable<PrimaryProjection> getPrimaries(@Param("user_id") int user_id);
}
