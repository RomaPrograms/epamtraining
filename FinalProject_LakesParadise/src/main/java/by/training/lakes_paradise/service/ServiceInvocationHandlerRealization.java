package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.mysql.TransactionRealization;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceInvocationHandlerRealization implements InvocationHandler {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ServiceInvocationHandlerRealization.class);

    private ServiceRealization service;

    public ServiceInvocationHandlerRealization(ServiceRealization service) {
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            Object result = method.invoke(service, args);
            service.transaction.commit();
            return result;
        } catch(PersistentException e) {
            rollback(method);
            throw e;
        } catch(InvocationTargetException e) {
            rollback(method);
            throw e.getCause();//dfadsfadsf
        }
    }

    private void rollback(Method method) {
        try {
            service.transaction.rollback();
        } catch (PersistentException e) {
            LOGGER.warn("It is impossible to rollback transaction", e);
        }
    }
}
