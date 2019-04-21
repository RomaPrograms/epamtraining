package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.exception.PersistentException;

public interface TransactionFactory {
    Transaction createTransaction() throws PersistentException;

    void close();
}
