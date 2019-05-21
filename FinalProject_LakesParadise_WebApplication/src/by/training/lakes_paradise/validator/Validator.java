package by.training.lakes_paradise.validator;

import by.training.lakes_paradise.db.entity.Entity;
import by.training.lakes_paradise.exception.IncorrectDataException;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface with common method for all validators.
 *
 * @param <T> - type of entity class which will be validated
 */
public interface Validator<T extends Entity> {
    /**
     * Method for validation entity classes.
     *
     * @param request - user request
     * @return Entity class which will be created if validation will be passed
     * successfully
     * @throws IncorrectDataException - incorrect validation exception
     */
    T validate(HttpServletRequest request) throws IncorrectDataException;
}
