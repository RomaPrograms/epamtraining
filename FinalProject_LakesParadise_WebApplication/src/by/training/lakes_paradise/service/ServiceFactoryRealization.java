package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.Transaction;
import by.training.lakes_paradise.db.dao.TransactionFactory;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactoryRealization implements ServiceFactory{
    private static final Logger logger = LogManager.getLogger(ServiceFactoryRealization.class);

    private static final Map<Class<? extends Service>, Class<? extends ServiceRealization>> SERVICES = new ConcurrentHashMap<>();

    static {
        SERVICES.put(HomesteadService.class, HomesteadServiceRealization.class);
        SERVICES.put(ImageService.class, ImageServiceRealization.class);
        SERVICES.put(OrderService.class, OrderServiceRealization.class);
        SERVICES.put(ProfileService.class, ProfileServiceRealization.class);
        SERVICES.put(ReviewService.class, ReviewServiceRealization.class);
        SERVICES.put(UserService.class, UserServiceRealization.class);
    }

    private TransactionFactory factory;

    public ServiceFactoryRealization(TransactionFactory factory) throws PersistentException {
        this.factory = factory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Type extends Service> Type getService(Class<Type> key) throws PersistentException {
        Class<? extends ServiceRealization> value = SERVICES.get(key);
        if(value != null) {
            try {
                ClassLoader classLoader = value.getClassLoader();
                Class<?>[] interfaces = {key};
                Transaction transaction = factory.createTransaction();
                ServiceRealization service = value.newInstance();
                service.setTransaction(transaction);
                InvocationHandler handler = new ServiceInvocationHandlerRealization(service);
                return (Type) Proxy.newProxyInstance(classLoader, interfaces, handler);
            } catch(PersistentException e) {
                throw e;
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("It is impossible to instance service class", e);
                throw new PersistentException(e);
            }
        }
        return null;
    }

    @Override
    public void close() {
        factory.close();
    }
}
