package by.training.lakes_paradise.controller;

import javax.servlet.*;
import java.io.IOException;

public class ActionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {
        //здесь требуется прописать закрытие всех ресурсов, которые мы использовали в данном фильтре.
    }
}
