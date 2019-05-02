package by.training.lakes_paradise.action;

import by.training.lakes_paradise.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionManager {
    Forward execute(
            Action action, HttpServletRequest request,
            HttpServletResponse response) throws PersistentException;

    void close();
}
