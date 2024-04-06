package com.example.readsms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readsms.R;
import com.example.readsms.models.Transaction;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    private final Context context;
    private final List<Transaction> listTransaction;

    public TransactionAdapter(Context context, List<Transaction> listTransaction) {
        this.context = context;
        this.listTransaction = listTransaction;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = listTransaction.get(position);
        holder.textViewId.setText("ID: " + transaction.getId());
        holder.textViewSender.setText("Sender: " + transaction.getSender());
        holder.textViewAmount.setText("Amount: " + transaction.getAmount());
        holder.textViewSurplus.setText("Surplus: " + transaction.getSurplus());
        holder.textViewContent.setText("Content: " + transaction.getContent());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        String formattedDate = sdf.format(transaction.getTimestamp());
        holder.textViewTimestamp.setText("Time: " + formattedDate);
        String status = transaction.isStatus() ? "True" : "False";
        holder.textViewStatus.setText("Status: " + status);
    }

    @Override
    public int getItemCount() {
        return listTransaction.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewSender, textViewAmount, textViewSurplus, textViewContent, textViewTimestamp, textViewStatus;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewSender = itemView.findViewById(R.id.textViewSender);
            textViewAmount = itemView.findViewById(R.id.textViewAmount);
            textViewSurplus = itemView.findViewById(R.id.textViewSurplus);
            textViewContent = itemView.findViewById(R.id.textViewContent);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
        }
    }
}
