package com.example.readsms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readsms.R;
import com.example.readsms.models.SMS;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.SMSViewHolder> {
    private final Context context;
    private final List<SMS> smsList;

    public SmsAdapter(Context context, List<SMS> smsList) {
        this.context = context;
        this.smsList = smsList;
    }

    @NonNull
    @Override
    public SMSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sms_info, parent, false);
        return new SMSViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SMSViewHolder holder, int position) {
        SMS sms = smsList.get(position);
        holder.textViewId.setText("ID: " + sms.getId());
        holder.textViewSender.setText("Sender: " + sms.getSender());
        holder.textViewMessage.setText("Message: " + sms.getMessage());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDate = sdf.format(sms.getTimestamp());
        holder.textViewTimestamp.setText("Timestamp: " + formattedDate);
    }

    @Override
    public int getItemCount() {
        return smsList.size();
    }

    public static class SMSViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewSender, textViewMessage, textViewTimestamp;

        public SMSViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewSender = itemView.findViewById(R.id.textViewSender);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
        }
    }
}
