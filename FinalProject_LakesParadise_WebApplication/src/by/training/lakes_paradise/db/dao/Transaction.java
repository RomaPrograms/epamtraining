package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.exception.PersistentException;

public interface Transaction {
    <T extends Dao<?>> T createDao(
            Class<T> key) throws PersistentException;

    void commit() throws PersistentException;

    void rollback() throws PersistentException;
}
