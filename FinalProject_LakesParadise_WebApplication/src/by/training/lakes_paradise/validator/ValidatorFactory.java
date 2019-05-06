package by.training.lakes_paradise.validator;

import by.training.lakes_paradise.db.entity.Entity;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ValidatorFactory {

    private static final Logger LOGGER
            = LogManager.getLogger(ValidatorFactory.class);

    private static Map<Class<? extends Entity>, Class<? extends Validator>>
            validators = new HashMap<>();

    static {
        validators.put(Profile.class, ProfileValidator.class);
        validators.put(User.class, UserValidator.class);
        validators.put(Order.class, OrderValidator.class);
    }

    public static <Type extends Entity> Validator<Type> createValidator(
            Class<Type> entity) throws PersistentException {
        Class<? extends Validator> value = validators.get(entity);
        if (value != null) {
            try {
                return (Validator<Type>) value.getConstructor().newInstance();
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
        throw new PersistentException("Validator class named "
                + entity.getSimpleName() + " wasn't founded");
    }
}
