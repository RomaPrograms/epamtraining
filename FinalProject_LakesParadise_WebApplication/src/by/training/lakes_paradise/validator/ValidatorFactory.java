package by.training.lakes_paradise.validator;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.Entity;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.validator.entity.ValidatorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating validation classes.
 */
public final class ValidatorFactory {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ValidatorFactory.class);

    /**
     * Map which helps to create required validation class by entity class.
     */
    private static Map<Class<? extends Entity>, ValidatorType>
            validators = new HashMap<>();

    static {
        validators.put(Profile.class, ValidatorType.PROFILE_VALIDATOR);
        validators.put(User.class, ValidatorType.USER_VALIDATOR);
        validators.put(Order.class, ValidatorType.ORDER_VALIDATOR);
        validators.put(Homestead.class, ValidatorType.HOMESTEAD_VALIDATOR);
    }

    /**
     * Private constructor for utility class.
     */
    private ValidatorFactory() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Method creates required validator class.
     *
     * @param entity - entity class
     * @param <T> - entity class
     * @return required validator by entity class
     * @throws PersistentException - required validator doesn't exist exception
     */
    public static <T extends Entity> Validator<T> createValidator(
            final Class<T> entity) throws PersistentException {
        ValidatorType value = validators.get(entity);
        if (value != null) {
            return chooseValidator(value);
        }
        throw new PersistentException("Validator class named "
                + entity.getSimpleName() + " wasn't founded");
    }

    private static <T extends Entity> Validator<T> chooseValidator(
            ValidatorType validatorType) throws PersistentException {
        switch(validatorType) {
            case USER_VALIDATOR:
                return (Validator<T>) new UserValidator();
            case ORDER_VALIDATOR:
                return (Validator<T>) new OrderValidator();
            case PROFILE_VALIDATOR:
                return (Validator<T>) new ProfileValidator();
            case HOMESTEAD_VALIDATOR:
                return (Validator<T>) new HomesteadValidator();
                default:
                    throw new PersistentException(
                            "Validator class wasn't founded");
        }
    }
}
