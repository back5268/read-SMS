package com.example.readsms.api;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.readsms.models.Response;
import com.example.readsms.models.Transaction;

import retrofit2.Call;
import retrofit2.Callback;

public class ApiServiceIntentService extends IntentService {

    public ApiServiceIntentService() {
        super("ApiServiceIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("onHandleIntent", "onHandleIntent: ");
//        assert intent != null;
//        String sender = intent.getStringExtra("sender");
//        String account = intent.getStringExtra("account");
//        long amount = intent.getLongExtra("amount", 0);
//        long timestamp = intent.getLongExtra("timestamp", 0);
//        long surplus = intent.getLongExtra("surplus", 0);
//        String content = intent.getStringExtra("content");
//        Transaction transaction = new Transaction(sender, account, amount, surplus, content, timestamp, false);
//        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
//        Call<Response> call = apiService.postRequest(transaction);
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                Log.d("onResponse", "onResponse: ");
//                if (response.isSuccessful()) {
//                    Response res = response.body();
//                    assert res != null;
//                    if (res.isStatus()) {
//                        transaction.setStatus(true);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//                Log.d("onFailure", "onFailure: ");
//            }
//        });
    }
}
