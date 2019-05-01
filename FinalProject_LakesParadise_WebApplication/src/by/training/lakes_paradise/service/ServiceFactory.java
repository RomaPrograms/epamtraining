package by.training.lakes_paradise.service;

import by.training.lakes_paradise.exception.PersistentException;

public interface ServiceFactory {
    <Type extends Service> Type getService(
            Class<Type> key) throws PersistentException;

    void close();
}
