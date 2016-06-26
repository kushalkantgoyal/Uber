package kkg.uber.DAO;

import java.util.Date;

import javax.persistence.PersistenceException;

import kkg.uber.Entity.UserEntity;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import antlr.Lookahead;

/**
 * @author Kushal
 *
 */
@Repository
public class UserDAO extends AbstractDAO<UserEntity, Long> {
	
	private static final Logger logger = Logger.getLogger(UserDAO.class);
	protected UserDAO() {
		super(UserEntity.class);
		// TODO Auto-generated constructor stub
	}
	
	public UserEntity createUser(UserEntity user) throws Exception{
		Date date = new Date();
		user.setCreatedTime(date);
		user.setUpdatedTime(date);
		try{
			save(user);
		}catch(Exception e){
			logger.error("Exception while persisting user: "+user, e);
			throw new Exception("Exception while persisting user: "+user, e);
		}
		return user;
	}
	
	public UserEntity updateUser(UserEntity user) throws Exception{
		user.setUpdatedTime(new Date());
		try{
			saveOrUpdate(user);
		}catch(Exception e){
			logger.error("Exception while updating user: "+user, e);
			throw new Exception("Exception while updating user: "+user, e);
		}
		return user;
	}

	public UserEntity getUserById(Long id) throws Exception {
		try{
			return findById(id);
		}catch(Exception e){
			logger.error("Exception while fetching user for userID: "+id, e);
			throw new Exception("Exception while fetching user for userID: "+id, e);
		}
	}
   
}
