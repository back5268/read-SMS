package com.example.readsms;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import com.example.readsms.api.ApiClient;
import com.example.readsms.api.ApiService;
import com.example.readsms.api.ApiServiceIntentService;
import com.example.readsms.database.TransactionRepo;
import com.example.readsms.helper.NotificationHelper;
import com.example.readsms.models.Response;
import com.example.readsms.models.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String message = smsMessage.getMessageBody();
                String sender = smsMessage.getOriginatingAddress();
                if (sender != null && Objects.equals(sender, "MBBANK")) {
                    String[] parts = message.split("\\|");

                    if (parts.length >= 4) {
                        NotificationHelper.showNotification(context, "Bạn có một tin nhắn mới từ " + sender, message);
                        String account = parts[0].substring(3); // Bỏ qua "TK "
                        String gd = parts[1].substring(4); // Bỏ qua "GD: "

                        String[] partsGd = gd.split(" ");
                        long amount = Long.parseLong(partsGd[0].replaceAll("[^0-9]", "")); // Lọc ra chỉ các ký tự số
                        String time = partsGd[1] + " " + partsGd[2]; // Ngày và giờ
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
                        long timestamp;
                        try {
                            timestamp = Objects.requireNonNull(sdf.parse(time)).getTime();
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                        long surplus = Long.parseLong(parts[2].replaceAll("[^0-9]", "")); // Bỏ qua "SD: "
                        String content = parts[3].substring(4); // Bỏ qua "ND: "
                        Transaction transaction = new Transaction(sender, account, amount, surplus, content, timestamp, false);
                        callApi(context, transaction);
                    }
                }
            }
        }
    }

    private void callApi(Context context, Transaction transaction) {
        Application application = (Application) context.getApplicationContext();
        TransactionRepo transactionRepo = new TransactionRepo(application);
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<Response> call = apiService.get();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    transaction.setStatus(true);
                    transactionRepo.insert(transaction);
                } else {
                    Log.d("onResponse89", "onResponse89: ");
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("onFailure", "onFailure: ");
            }
        });
    }
}
