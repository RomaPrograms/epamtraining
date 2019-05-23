package by.training.lakes_paradise.filter;

import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Filter class which sets encoding of request before it will be executed.
 */
public class EncodingFilter implements Filter {
    /**
     * Method which will be executed before every request of user.
     * It changes encoding of servlet request.
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
            final ServletResponse servletResponse,
            final FilterChain filterChain)
            throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(final FilterConfig filterConfig) {
        /*Do nothing cause parameters aren't passed throw the FilterConfig
         instance*/
    }

    @Override
    public void destroy() {
        /*Do nothing cause there no parameters for destroying*/
    }
}
