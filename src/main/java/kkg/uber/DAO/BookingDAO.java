package kkg.uber.DAO;

import kkg.uber.Entity.BookingEntity;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * @author Kushal
 *
 */
@Repository
public class BookingDAO extends CommonDAO<BookingEntity> {

	private static final Logger logger = Logger.getLogger(BookingDAO.class);
	
	@Override
	public Class<BookingEntity> getModelClass() {
		return BookingEntity.class;
	}
}
