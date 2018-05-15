package com.anurag.example.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.anurag.example.hibernate.model.Employee;

public class CriteriaExample {
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Integer[] salaries = {111,222,333};
			Criteria criteria = session.createCriteria(Employee.class)
					.addOrder(Order.asc("employeeName"))
					.addOrder(Order.asc("employeeId"))
					.add(Restrictions.in("salary", salaries));
					//.add(Restrictions.between("salary", 222,444));
			// Criterion salaryEq = Restrictions.eq("salary", 111);
			// criteria.add(salaryEq);
			List list = criteria.list();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Employee emp = (Employee) it.next();
				System.out.println("Employee : " + emp.toString());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
}
