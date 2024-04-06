package com.example.readsms.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.readsms.models.Transaction;

import java.util.List;

@Dao
public interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY timestamp ASC")
    LiveData<List<Transaction>> getAll();

    @Query("SELECT * FROM transactions WHERE sender LIKE :sender")
    LiveData<List<Transaction>> findBySender(String sender);

    @Query("SELECT * FROM transactions WHERE account LIKE :account")
    LiveData<List<Transaction>> findByAccount(String account);

    @Query("SELECT * FROM transactions WHERE timestamp between :start and :end")
    LiveData<List<Transaction>> findByTimestamp(long start, long end);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Transaction transaction);
}