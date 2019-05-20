package by.training.lakes_paradise.filter;

import by.training.lakes_paradise.action.HomesteadsListAction;
import by.training.lakes_paradise.action.HomesteadInfoAction;
import by.training.lakes_paradise.action.LogInAction;
import by.training.lakes_paradise.action.MenuAction;
import by.training.lakes_paradise.action.SignUpAction;
import by.training.lakes_paradise.action.ChangeLanguageAction;
import by.training.lakes_paradise.action.ErrorAction;
import by.training.lakes_paradise.action.FindHomesteadsByNameAction;
import by.training.lakes_paradise.action.FindHomesteadsByPriceAction;
import by.training.lakes_paradise.action.admin.FindUserByLoginAction;
import by.training.lakes_paradise.action.admin.DeleteHomesteadAction;
import by.training.lakes_paradise.action.admin.DeleteReviewAction;
import by.training.lakes_paradise.action.admin.DeleteUserAction;
import by.training.lakes_paradise.action.admin.UsersListAction;
import by.training.lakes_paradise.action.authorized_user.LogOutAction;
import by.training.lakes_paradise.action.authorized_user.ReserveHomesteadAction;
import by.training.lakes_paradise.action.authorized_user.ReserveHomesteadInfoAction;
import by.training.lakes_paradise.action.authorized_user.UserCabinetAction;
import by.training.lakes_paradise.action.authorized_user.DeleteAccountAction;
import by.training.lakes_paradise.action.authorized_user.ReviewAction;
import by.training.lakes_paradise.action.authorized_user.UpdateUserInfoAction;
import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.owner.AddPhotoAction;
import by.training.lakes_paradise.action.owner.OwnerCabinetAction;
import by.training.lakes_paradise.action.owner.OwnerHomesteadsListAction;
import by.training.lakes_paradise.action.owner.UpdateHomesteadAction;
import by.training.lakes_paradise.action.owner.AddHomesteadAction;
import by.training.lakes_paradise.action.owner.DeleteOwnerHomesteadAction;
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

/**
 * Filter class which pinpoint name of action before it will be executed.
 */
public class ActionFilter implements Filter {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ActionFilter.class);

    /**
     * Map with names of actions and appropriate classes for executing that
     * actions.
     */
    private static Map<String, Class<? extends Action>> actions
            = new ConcurrentHashMap<>();

    static {
        actions.put("/", MenuAction.class);
        actions.put("/menu", MenuAction.class);
        actions.put("/sign_up", SignUpAction.class);
        actions.put("/log_in", LogInAction.class);
        actions.put("/homesteadInfo", HomesteadInfoAction.class);
        actions.put("/homesteadsList", HomesteadsListAction.class);
        actions.put("/findHomesteadsByName", FindHomesteadsByNameAction.class);
        actions.put("/findHomesteadsByPrice",
                FindHomesteadsByPriceAction.class);
        actions.put("/error", ErrorAction.class);

        actions.put("/language/en_US", ChangeLanguageAction.class);
        actions.put("/language/be_BY", ChangeLanguageAction.class);
        actions.put("/language/ru_RU", ChangeLanguageAction.class);

        actions.put("/authorized_user/log_out", LogOutAction.class);
        actions.put("/authorized_user/reservation",
                ReserveHomesteadAction.class);
        actions.put("/authorized_user/reservationInfo",
                ReserveHomesteadInfoAction.class);
        actions.put("/authorized_user/userCabinet", UserCabinetAction.class);
        actions.put("/authorized_user/updateUserInfo",
                UpdateUserInfoAction.class);
        actions.put("/authorized_user/homesteadReview", ReviewAction.class);
        actions.put("/authorized_user/deleteUserAccount",
                DeleteAccountAction.class);


        actions.put("/owner/addHomestead", AddHomesteadAction.class);
        actions.put("/owner/deleteHomestead", DeleteOwnerHomesteadAction.class);
        actions.put("/owner/updateHomestead", UpdateHomesteadAction.class);
        actions.put("/owner/ownerHomesteads", OwnerHomesteadsListAction.class);
        actions.put("/owner/addPhoto", AddPhotoAction.class);

        actions.put("/admin/deleteReview", DeleteReviewAction.class);
        actions.put("/admin/deleteHomestead", DeleteHomesteadAction.class);
        actions.put("/admin/deleteUser", DeleteUserAction.class);
        actions.put("/admin/usersList", UsersListAction.class);
        actions.put("/admin/findUserByLogin", FindUserByLoginAction.class);
        actions.put("/owner/ownerCabinet", OwnerCabinetAction.class);
    }

    /**
     * Method which will be executed before every request of user.
     * It pinpoints which action was called and uses appropriate class.
     *
     * @param servletRequest  - user request
     * @param servletResponse - user response
     * @param filterChain     - chain of filters
     * @throws IOException      - exception connected with forwarding
     * @throws ServletException - exception connected with servlet
     */
    @Override
    public void doFilter(
            final ServletRequest servletRequest,
            final ServletResponse servletResponse, final FilterChain
                    filterChain) throws IOException, ServletException {

        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest
                    = (HttpServletRequest) servletRequest;
            String uri = httpServletRequest.getRequestURI();
            LOGGER.debug(String.format(
                    "Starting of processing of request for URI \"%s\"", uri));
            int endAction = uri.lastIndexOf('.');
            String actionName;

            if (endAction >= 0) {
                actionName = uri.substring(0, endAction);
            } else {
                actionName = uri;
            }

            Class<? extends Action> actionClass = actions.get(actionName);
            try {
                Action action = actionClass.getConstructor().newInstance();
                action.setName(actionName);
                httpServletRequest.setAttribute("action", action);
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (IllegalAccessException | InstantiationException e) {
                httpServletRequest.setAttribute("error", String.format(
                        "Required address %s can not be handled by server",
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
    public void init(final FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
