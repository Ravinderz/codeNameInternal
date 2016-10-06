package com.congun.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.congun.web.dao.AuthDao;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOGGER = Logger
			.getLogger(AuthenticationInterceptor.class);

	@Autowired
	AuthDao authDao;

	private final String PING_URL = "/ping";
	// Specify the URLS for those where authentication not required

	private final String LOGIN = "/login";
	private final String REGISTER = "/register";
	private final String GETALLPOSTS = "/getAllPosts";
	private final String GETREQUIREMENTBYEQUIPMENT = "/getRequirementsByequipment";
	private final String CONTRACTORREQUIREMENT = "/contractorRequirement";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String method = request.getMethod();

		LOGGER.info(" Intercepting || URI: " + uri);
		LOGGER.info(" Intercepting || METHOD " + method);
		LOGGER.debug(" Intercepting || ServerName: " + request.getServerName());
		LOGGER.debug(" Intercepting || ServerPort: " + request.getServerPort());

		if (isTokenAuthenticationRequired(uri, method)) {
			String token = request.getHeader("token");

			LOGGER.debug("************* Printing token " + token);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			if (token != null) {
				System.out.println("yyyyyyyyyyyy ");
				String tokenResponse = authDao.tokenLogin(token);
				if (!tokenResponse.isEmpty()) {
					System.out.println("************* token in-valid "
							+ tokenResponse);
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.getWriter().write(tokenResponse);
					return false;
				}
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write(
						new ApplicationUtil().composeJsonOuput(
								ResponseConstants.AUTH_FAILURE_CODE,
								ResponseConstants.UNAUTHORISED_SERVICE_CALL)
								.toString());
				return false;
			}
			LOGGER.debug("************* token valid ");
		}
		return true;
	}

	private boolean isTokenAuthenticationRequired(String uri, String method) {
		if ((uri.contains(LOGIN)) || (uri.contains(GETALLPOSTS)) || (uri.contains(GETREQUIREMENTBYEQUIPMENT)) 
				|| (uri.contains(CONTRACTORREQUIREMENT)) || (uri.contains(REGISTER)))

		{
			return false;
		}
		LOGGER.info("***** Authentication Required *****" + uri + " : "
				+ method);
		return true;
	}
}
