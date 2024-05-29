package com.mynotebook.server.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="user_records_access")
public class Primary {
    private int user_id;
    private int record_id;
    private int access_level;

    public int getUser_id() {
        return user_id;
    }

    public int getRecord_id() {
        return record_id;
    }

    public int getLevel() {
        return access_level;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public void setLevel(int level) {
        this.access_level = level;
    }

}

