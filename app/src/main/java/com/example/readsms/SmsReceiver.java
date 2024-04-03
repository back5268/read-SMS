package com.example.readsms;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import com.example.readsms.database.SmsRepo;
import com.example.readsms.helper.NotificationHelper;
import com.example.readsms.models.SMS;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String message = smsMessage.getMessageBody();
                String sender = smsMessage.getOriginatingAddress();
                long timeStamp = System.currentTimeMillis();

                Application application = (Application) context.getApplicationContext();
                SmsRepo smsRepo = new SmsRepo(application);
                smsRepo.insert(new SMS(sender, message, timeStamp));
                NotificationHelper.showNotification(context, "Bạn có một tin nhắn mới từ " + sender, message);
            }
        }
    }
}
