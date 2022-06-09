package fr.epsi.rennes.poec.vanessa.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.epsi.rennes.poec.vanessa.dao.UserDAO;
import fr.epsi.rennes.poec.vanessa.domain.User;
import fr.epsi.rennes.poec.vanessa.domain.UserRole;
import fr.epsi.rennes.poec.vanessa.exception.BusinessException;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.getUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found : " + username);
		}
		return user;
	}

	public void addUser(User user) throws BusinessException {
		if (user.getEmail() == null || user.getPassword() == null) {
			throw new BusinessException("subscribe.mandatory.missing");
		}
		user.setRole(UserRole.ROLE_USER);
		userDAO.addUser(user);
	}

}

