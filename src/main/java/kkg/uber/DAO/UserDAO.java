package kkg.uber.DAO;

import kkg.uber.Entity.UserEntity;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * @author Kushal
 *
 */
@Repository
public class UserDAO extends CommonDAO<UserEntity> {
	
	private static final Logger logger = Logger.getLogger(UserDAO.class);
	
	@Override
	public Class<UserEntity> getModelClass() {
		return UserEntity.class;
	}
}
