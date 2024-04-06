package com.example.readsms.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.readsms.dao.TransactionDao;
import com.example.readsms.models.Transaction;

import java.util.List;

public class TransactionRepo {
    private final TransactionDao transactionDao;
    private final LiveData<List<Transaction>> listTransaction;

    public TransactionRepo(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        transactionDao = db.smsDao();
        listTransaction = transactionDao.getAll();
    }

    public LiveData<List<Transaction>> getAll() {
        return listTransaction;
    }

    public LiveData<List<Transaction>> findBySender(String sender) {
        return transactionDao.findBySender(sender);
    }

    public LiveData<List<Transaction>> findByAccount(String account) {
        return transactionDao.findByAccount(account);
    }

    public LiveData<List<Transaction>> findByTimestamp(long start, long end) {
        return transactionDao.findByTimestamp(start, end);
    }

    public void insert(Transaction transaction) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            transactionDao.insert(transaction);
        });
    }
}
