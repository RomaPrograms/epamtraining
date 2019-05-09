package by.training.lakes_paradise.validator;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.Entity;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating validation classes.
 */
public class ValidatorFactory {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ValidatorFactory.class);

    /**
     * Map which helps to create required validation class by entity class.
     */
    private static Map<Class<? extends Entity>, Class<? extends Validator>>
            validators = new HashMap<>();

    static {
        validators.put(Profile.class, ProfileValidator.class);
        validators.put(User.class, UserValidator.class);
        validators.put(Order.class, OrderValidator.class);
        validators.put(Homestead.class, HomesteadValidator.class);
    }

    /**
     * Method creates required validator class.
     *
     * @param entity - entity class
     * @param <Type> - entity class
     * @return required validator by entity class
     * @throws PersistentException - required validator doesn't exist exception
     */
    public static <Type extends Entity> Validator<Type> createValidator(
            final Class<Type> entity) throws PersistentException {
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
