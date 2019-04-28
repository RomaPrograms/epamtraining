package by.training.lakes_paradise.filter;

import by.training.lakes_paradise.action.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
        actions.put("/main", MenuAction.class);
        actions.put("/home", HomesteadAction.class);
        actions.put("/review", ReviewAction.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String contextPath = httpServletRequest.getContextPath();//TODO что эта хрень вообще возвращает.
            String uri = httpServletRequest.getRequestURI();
            LOGGER.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
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
                Action action = actionClass.newInstance();
                action.setName(actionName);
                httpServletRequest.setAttribute("action", action);
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (IllegalAccessException | InstantiationException e) {
                httpServletRequest.setAttribute("error",
                        String.format("Запрошенный адрес %s не может быть обработан сервером", uri));
                httpServletRequest.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(servletRequest, servletResponse);
            }
        } else {
            LOGGER.error("It is impossible to use HTTP filter");
            servletRequest.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
        //здесь требуется прописать закрытие всех ресурсов, которые мы использовали в данном фильтре.
    }
}
