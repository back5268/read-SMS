package com.example.readsms.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.readsms.models.SMS;

import java.util.List;

@Dao
public interface SmsDao {
    @Query("SELECT * FROM SMS ORDER BY timestamp ASC")
    LiveData<List<SMS>> getAll();

    @Query("SELECT * FROM SMS WHERE sender LIKE :sender")
    LiveData<List<SMS>> findBySender(String sender);

    @Insert
    void insertAll(SMS... listSms);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SMS sms);

    @Delete
    void delete(SMS sms);

    @Query("DELETE FROM SMS")
    void deleteAll();
}