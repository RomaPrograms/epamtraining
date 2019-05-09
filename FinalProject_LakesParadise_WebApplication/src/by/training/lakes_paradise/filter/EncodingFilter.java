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
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }
}
