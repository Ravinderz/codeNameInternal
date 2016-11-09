package com.congun.web.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.congun.web.model.Machines;
import com.congun.web.model.UserSession;
import com.congun.web.util.ApplicationUtil;
import com.congun.web.util.AuthenticationInterceptor;
import com.congun.web.util.ResponseConstants;

@Repository
public class AuthDao {

	private static final Logger LOGGER = Logger.getLogger(AuthDao.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public String tokenLogin(String token) {
		System.out.println("eeeeeeeeeeeeeeeeee : "+token);
		List<UserSession> userSessionList = null;
		try {
			Criteria criteria = getSession().createCriteria(UserSession.class);
			criteria.add(Restrictions.eq("tokenID", token));
			userSessionList = criteria.list();
			if (!userSessionList.isEmpty()) {
				UserSession userSession = userSessionList.get(0);
				System.out.println("tokenLogin incoming token" + token);
				System.out.println("tokenLogin table token"
						+ userSession.getTokenID());
				if (userSession != null) {
					return "";
				}
			}
			System.out.println(" @@ Authentication Failed @@ ");
			return new ApplicationUtil().composeJsonOuput(
					ResponseConstants.AUTH_FAILURE_CODE,
					ResponseConstants.AUTHENTICATION_FAILED_STRING).toString();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception in tokenLogin()" + e.getMessage());
			return null;
		}
	}

}
