package kkg.uber.Service;

import javax.transaction.Transactional;

import kkg.uber.DAO.UserDAO;
import kkg.uber.Entity.UserEntity;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			userDAO.createUser(user);
			return user;
		}catch(Exception e){
			logger.error("Exception while creating user: "+user, e);
			throw new Exception("Exception while creating user: "+user, e);
		}
	}
	
	public UserEntity update(UserEntity newUser) throws Exception{
		try{
			UserEntity oldUser = userDAO.findById(newUser.getId());
			
			if(StringUtils.isNotEmpty(newUser.getEmail())){
				oldUser.setEmail(newUser.getEmail());
			}
			if(StringUtils.isNotEmpty(newUser.getFirstName())){
				oldUser.setFirstName(newUser.getFirstName());
			}
			if(StringUtils.isNotEmpty(newUser.getLastName())){
				oldUser.setLastName(newUser.getLastName());
			}
			if(newUser.getMobileNumber()!=null){
				oldUser.setMobileNumber(newUser.getMobileNumber());
			}
			
			userDAO.updateUser(oldUser);
			return oldUser;
			
		}catch(Exception e){
			logger.error("Exception while updating user: "+newUser, e);
			throw new Exception("Exception while updating user: "+newUser, e);
		}
	}

	public UserEntity getUserById(Long id) throws Exception {
		try{
			return userDAO.getUserById(id);
		}catch(Exception e){
			logger.error("Exception while fetching user for userId: "+id, e);
			throw new Exception("Exception while fetching user for userId: "+id, e);
		}
	}
}
