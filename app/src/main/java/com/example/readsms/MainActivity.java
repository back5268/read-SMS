package com.example.readsms;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.readsms.adapter.TransactionAdapter;
import com.example.readsms.database.TransactionRepo;
import com.example.readsms.models.Transaction;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_SMS_PERMISSION = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestSmsPermission();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TransactionRepo transactionRepo = new TransactionRepo(getApplication());
        LiveData<List<Transaction>> listTransaction = transactionRepo.getAll();

        listTransaction.observe(this, new Observer<List<Transaction>>() {
            @Override
            public void onChanged(List<Transaction> transactionList) {
                TransactionAdapter transactionAdapter = new TransactionAdapter(MainActivity.this, transactionList);
                recyclerView.setAdapter(transactionAdapter);
            }
        });
    }

    private void requestSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, REQUEST_SMS_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_SMS_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Đã cấp quyền đọc SMS!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Không có quyền đọc SMS!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}