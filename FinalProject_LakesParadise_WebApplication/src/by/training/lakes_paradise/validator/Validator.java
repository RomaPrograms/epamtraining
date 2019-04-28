package by.training.lakes_paradise.validator;

import by.training.lakes_paradise.db.entity.Entity;
import by.training.lakes_paradise.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;

public interface Validator <Type extends Entity> {
    Type validate(HttpServletRequest request) throws IncorrectDataException;
}
