package com.anurag.example.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AppMain {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			User user = null;
			for (int i = 101; i <= 105; i++) {
				user = new User();
				user.setUserId(i);
				user.setUserName("Editor " + i);
				user.setCreatedBy("Anurag");
				user.setCreatedDate(new Date());
				session.save(user);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			if (null != session.getTransaction()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
