package by.training.lakes_paradise.service;

import by.training.lakes_paradise.exception.PersistentException;

/**
 * Interface for factory which creates services during application running.
 */
public interface ServiceFactory {
    /**
     * Method creates instance of required service class.
     *
     * @param key - required service
     * @param <Type> - type of required service
     * @return required service
     * @throws PersistentException - exception connected with DAO
     */
    <Type extends Service> Type getService(
            Class<Type> key) throws PersistentException;

    /**
     * Method closes all resources after creation service.
     */
    void close();
}
