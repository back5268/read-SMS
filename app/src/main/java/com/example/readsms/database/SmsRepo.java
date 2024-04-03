package com.example.readsms.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.readsms.dao.SmsDao;
import com.example.readsms.models.SMS;

import java.util.List;

public class SmsRepo {
    private final SmsDao smsDao;
    private final LiveData<List<SMS>> listSms;

    public SmsRepo(Application application) {
        SmsDatabase db = SmsDatabase.getDatabase(application);
        smsDao = db.smsDao();
        listSms = smsDao.getAll();
    }

    public LiveData<List<SMS>> getAllSms() {
        return listSms;
    }

    public void insert(SMS sms) {
        SmsDatabase.databaseWriteExecutor.execute(() -> {
            smsDao.insert(sms);
        });
    }

    public void clearSMS() {
        SmsDatabase.databaseWriteExecutor.execute(smsDao::deleteAll);
    }
}
