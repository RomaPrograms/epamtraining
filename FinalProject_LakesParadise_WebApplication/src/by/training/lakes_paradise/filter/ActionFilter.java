package by.training.lakes_paradise.filter;

import by.training.lakes_paradise.action.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionFilter implements Filter {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ActionFilter.class);

    private static Map<String, Class<? extends Action>> actions = new ConcurrentHashMap<>();

    static {
        actions.put("/", MenuAction.class);
        actions.put("/homesteads", HomesteadListAction.class);
        actions.put("/signUp", SignUpAction.class);
        actions.put("/menu", MenuAction.class);
        actions.put("/home", HomesteadAction.class);
        actions.put("/review", ReviewAction.class);
        actions.put("/changeStatus", LogInAction.class);
        actions.put("/personalCabinet", UserCabinetAction.class);
        actions.put("/addHomestead", AddHomesteadAction.class);
        actions.put("/deleteHomestead", DeleteHomesteadAction.class);
        actions.put("/updateHomestead", UpdateHomesteadAction.class);
        actions.put("/en_US", ChangeLanguageAction.class);
        actions.put("/be_BY", ChangeLanguageAction.class);
        actions.put("/ru_RU", ChangeLanguageAction.class);
        actions.put("/ownerHomesteads", OwnerHomesteadListAction.class);
        actions.put("/addPhoto", AddPhotoAction.class);
        actions.put("/findHomesteadByCategory", FindHomesteadsAction.class);
        actions.put("/reserveHomestead", ReservedHomesteadsListAction.class);
        actions.put("/reservation", ReserveHomesteadAction.class);
    }

    @Override
    public void doFilter(
            final ServletRequest servletRequest,
            final ServletResponse servletResponse, final FilterChain
                    filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest
                    = (HttpServletRequest) servletRequest;
            String contextPath = httpServletRequest.getContextPath();//TODO что эта хрень вообще возвращает.
            String uri = httpServletRequest.getRequestURI();
            LOGGER.debug(String.format(
                    "Starting of processing of request for URI \"%s\"", uri));
            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;
            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }
            Class<? extends Action> actionClass = actions.get(actionName);
            try {
                Action action = actionClass.getConstructor().newInstance();
                action.setName(actionName);
                httpServletRequest.setAttribute("action", action);
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (IllegalAccessException | InstantiationException e) {
                httpServletRequest.setAttribute("error", String.format(
                        "Запрошенный адрес %s не может быть обработан сервером",
                        uri));
                httpServletRequest.getServletContext().getRequestDispatcher(
                        "/jsp/error.jsp").forward(servletRequest,
                        servletResponse);
            } catch (InvocationTargetException e) {
                LOGGER.error("Constructor of " + actionClass.getSimpleName()
                        + " throws an exception", e);
            } catch (NoSuchMethodException e) {
                LOGGER.error("Matching method is not found", e);
            }
        } else {
            LOGGER.error("It is impossible to use HTTP filter");
            servletRequest.getServletContext().getRequestDispatcher(
                    "/jsp/error.jsp").forward(servletRequest,
                    servletResponse);
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
        //здесь требуется прописать закрытие всех ресурсов, которые мы использовали в данном фильтре.
    }
}
