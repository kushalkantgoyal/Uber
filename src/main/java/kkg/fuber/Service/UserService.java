package kkg.fuber.Service;

import kkg.fuber.DAO.UserDAO;
import kkg.fuber.Entity.UserEntity;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Kushal
 *
 */
@Service("userService")
@Transactional
public class UserService {
	@Autowired
	private UserDAO userDAO;
	private static final Logger logger = Logger.getLogger(UserService.class);
	
	public UserEntity create(UserEntity user) throws Exception{
		try{
			userDAO.create(user);
			return user;
		}catch(Exception e){
			logger.error("Exception while creating user: "+user, e);
			throw new Exception("Exception while creating user: "+user, e);
		}
	}
	
	public UserEntity update(UserEntity user) throws Exception{
		try{
			UserEntity oldUser = userDAO.getById(user.getId());
			
			if(StringUtils.isNotEmpty(user.getEmail())){
				oldUser.setEmail(user.getEmail());
			}
			if(StringUtils.isNotEmpty(user.getFirstName())){
				oldUser.setFirstName(user.getFirstName());
			}
			if(StringUtils.isNotEmpty(user.getLastName())){
				oldUser.setLastName(user.getLastName());
			}
			if(user.getMobileNumber()!=null){
				oldUser.setMobileNumber(user.getMobileNumber());
			}
			
			userDAO.update(oldUser);
			return oldUser;
			
		}catch(Exception e){
			logger.error("Exception while updating user: "+user, e);
			throw new Exception("Exception while updating user: "+user, e);
		}
	}

	public UserEntity getUserById(Long id) throws Exception {
		try{
			return userDAO.getById(id);
		}catch(Exception e){
			logger.error("Exception while fetching user for userId: "+id, e);
			throw new Exception("Exception while fetching user for userId: "+id, e);
		}
	}
	
	public Boolean removeUser(Long userId) throws Exception {
		UserEntity user = null;
		try{
			user = userDAO.getById(userId);
		}catch(Exception e){
			logger.error("Exception while fetching user with id: "+userId, e);
			throw new Exception("Exception while fetching user with id: "+userId, e);
		}
		if(user!=null){
			try{
				return userDAO.remove(user);
			}catch(Exception e){
				logger.error("Exception while removing user with id: "+userId, e);
				return false;
			}
		}
		else{
			return false;
		}
	}
}
