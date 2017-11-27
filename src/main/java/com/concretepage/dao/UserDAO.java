package com.concretepage.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.concretepage.entity.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDAO implements IUserDAO {

	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public UserInfo getUserById(int userId) {
		return entityManager.find(UserInfo.class, userId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<UserInfo> getAllUsers() {
		String hql ="FROM UserInfo";
		return (List<UserInfo>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addUser(UserInfo userInfo) {
		entityManager.merge(userInfo);
	}

	@Override
	public void updateUser(UserInfo userInfo) {
		UserInfo usr = getUserById(userInfo.getId());
		usr.setLogin(userInfo.getLogin());
		usr.setPassword(userInfo.getPassword());
		entityManager.flush();
	}
	@Override
	public void deleteUser(int userId) {
		entityManager.remove(getUserById(userId));
	}
	@Override
	public boolean userExists(String login, String password) {
		String hql = "FROM UserInfo as usr WHERE usr.login = ? or usr.password = ?";
		int count = entityManager.createQuery(hql).setParameter(1, login)
				.setParameter(2, password).getResultList().size();
		return count > 0;
	}
	@Override
	public UserInfo getActiveUser(String login) {
		UserInfo activeUserInfoInfo = new UserInfo();
		short enabled = 1;
		List<?> list = entityManager.createQuery("FROM UserInfo as usr WHERE usr.login = ? and usr.enabled=?")
				.setParameter(1, login).setParameter(2, enabled).getResultList();
		if(!list.isEmpty()) {
			activeUserInfoInfo = (UserInfo)list.get(0);
		}
		return activeUserInfoInfo;
	}
}
