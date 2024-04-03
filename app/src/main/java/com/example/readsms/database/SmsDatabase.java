package com.example.readsms.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.readsms.dao.SmsDao;
import com.example.readsms.models.SMS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SMS.class}, version = 1)
public abstract class SmsDatabase extends RoomDatabase {
    public abstract SmsDao smsDao();

    private static volatile SmsDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static SmsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SmsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SmsDatabase.class, "sms_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
