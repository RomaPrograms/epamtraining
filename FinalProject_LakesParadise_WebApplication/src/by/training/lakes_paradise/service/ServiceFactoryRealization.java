package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.Transaction;
import by.training.lakes_paradise.db.dao.TransactionFactory;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactoryRealization implements ServiceFactory {
    private static final Logger LOGGER
            = LogManager.getLogger(ServiceFactoryRealization.class);

    private static final Map<Class<? extends Service>,
            Class<? extends ServiceRealization>> SERVICES
            = new ConcurrentHashMap<>();

    static {
        SERVICES.put(HomesteadService.class, HomesteadServiceRealization.class);
        SERVICES.put(ImageService.class, ImageServiceRealization.class);
        SERVICES.put(OrderService.class, OrderServiceRealization.class);
        SERVICES.put(ProfileService.class, ProfileServiceRealization.class);
        SERVICES.put(ReviewService.class, ReviewServiceRealization.class);
        SERVICES.put(UserService.class, UserServiceRealization.class);
    }

    private TransactionFactory factory;

    public ServiceFactoryRealization(
            final TransactionFactory curFactory) {
        this.factory = curFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Service> T getService(
            final Class<T> key) throws PersistentException {
        Class<? extends ServiceRealization> value = SERVICES.get(key);
        if (value != null) {
            try {
                ClassLoader classLoader = value.getClassLoader();
                Class<?>[] interfaces = {key};
                Transaction transaction = factory.createTransaction();
                ServiceRealization service
                        = value.getConstructor().newInstance();
                service.setTransaction(transaction);
                InvocationHandler handler
                        = new ServiceInvocationHandlerRealization(service);
                return (T) Proxy.newProxyInstance(classLoader,
                        interfaces, handler);
            } catch (IllegalAccessException | InstantiationException e) {
                LOGGER.error("It is impossible to instance service class", e);
                throw new PersistentException(e);
            } catch (InvocationTargetException e) {
                LOGGER.error("Constructor of " + value.getSimpleName()
                        + " throws an exception", e);
                throw new PersistentException(e);
            } catch (NoSuchMethodException e) {
                LOGGER.error("Matching method is not found", e);
                throw new PersistentException(e);
            }
        }
        throw new PersistentException("Service class named "
                + key.getSimpleName() + " wasn't founded");
    }

    @Override
    public void close() {
        factory.close();
    }
}
