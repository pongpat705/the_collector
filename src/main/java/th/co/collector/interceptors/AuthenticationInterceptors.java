package th.co.collector.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptors implements HandlerInterceptor {
	
	Logger log = LoggerFactory.getLogger(this.getClass());	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(null !=SecurityContextHolder.getContext().getAuthentication()) {
			log.debug("access url {}", request.getRequestURL());
			log.debug("access by {}", SecurityContextHolder.getContext().getAuthentication().getName());
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
