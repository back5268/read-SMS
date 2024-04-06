package com.example.readsms.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "sender")
    public String sender;
    @ColumnInfo(name = "account")
    public String account;
    @ColumnInfo(name = "amount")
    public long amount;
    @ColumnInfo(name = "surplus")
    public long surplus;
    @ColumnInfo(name = "content")
    public String content;
    @ColumnInfo(name = "timestamp")
    public long timestamp;
    @ColumnInfo(name = "status")
    public boolean status;

    public Transaction(String sender, String account, long amount, long surplus, String content, long timestamp, boolean status) {
        this.sender = sender;
        this.account = account;
        this.amount = amount;
        this.surplus = surplus;
        this.content = content;
        this.timestamp = timestamp;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getSurplus() {
        return surplus;
    }

    public void setSurplus(long surplus) {
        this.surplus = surplus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
