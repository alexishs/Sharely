package fr.dawan.sharely.controlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class IntercepteurConnexion extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest requeteHttp, HttpServletResponse response, Object handler) throws Exception {
		SessionUtilisateur sessionUtilisateur = SessionUtilisateur.getSession(requeteHttp);
		if(!"/sharely/user/login".equals(requeteHttp.getRequestURI())) {
			if(!sessionUtilisateur.estConnecte()) {
				response.sendError(HttpStatus.FORBIDDEN.value());
			}
		}
		return true;
	}
}
