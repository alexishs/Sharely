package fr.dawan.sharely.controlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class IntercepteurConnexion extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest requeteHttp, HttpServletResponse response, Object handler) throws Exception {
		if(!"/sharely/user/login".equals(requeteHttp.getRequestURI())) {
			if(!SessionUtilisateur.getSession(requeteHttp).estConnecte()) {
				response.sendError(HttpStatus.FORBIDDEN.value());
			}
		}
		return true;
	}
}
