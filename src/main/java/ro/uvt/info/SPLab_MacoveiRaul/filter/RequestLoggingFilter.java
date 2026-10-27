package ro.uvt.info.SPLab_MacoveiRaul.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component il face bean, iar Spring Boot il adauga automat in lantul de filtre
@Component
public class RequestLoggingFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String method = httpRequest.getMethod();
        String path = httpRequest.getRequestURI();

        log.info("--> {} {}", method, path);
        long start = System.currentTimeMillis();
        try {
            // predam request-ul urmatoarei verigi din lant (alt filtru sau, la final, controller-ul)
            chain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - start;
            log.info("<-- {} {} {} ({} ms)", method, path, httpResponse.getStatus(), duration);
        }
    }
}
