package com.lti.repository;

import org.springframework.stereotype.Repository;

import com.lti.entity.Farmer;
import com.lti.entity.LoginCredentials;


@Repository
public class LoginRepository extends GenericRepository {

	public  boolean isUserPresent(String id, String type) {
		 return (Long) entityManager.createQuery("select count(lc.id) from LoginCredentials lc where lc.id = :id and lc.type =:type")
					.setParameter("id", id)
					.setParameter("type", type)
					.getSingleResult() == 1 ?true:false;
	}

	public boolean checkCredntials(LoginCredentials loginCredentials) {
		return (Long) entityManager.createQuery("select count(lc.id) from LoginCredentials lc where lc.id = :id and lc.password =:password")
				.setParameter("id", loginCredentials.getId())
				.setParameter("password", loginCredentials.getPassword())
				.getSingleResult() == 1 ?true:false;
	}

	public Object fetchByEmail(Class className, String id) {
		return  entityManager.createQuery("select obj from "+className.getSimpleName()+" obj where obj.email = :id")
//		return  entityManager.createQuery("select obj from Farmer obj where obj.email = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
//	public void changePassword(LoginCredentials loginCredentials) {
//		entityManager.createQuery("update LoginCredentials lc set lc.password =:password where lc.id = :id ")
//				.setParameter("id", loginCredentials.getId())
//				.setParameter("password", loginCredentials.getPassword());
//				
//	}
	





}
