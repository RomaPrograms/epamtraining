package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.Transaction;

abstract public class ServiceRealization implements Service{
    protected Transaction transaction = null;

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}