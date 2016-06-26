package kkg.uber.DAO;

import java.util.Date;

import kkg.uber.Entity.BookingEntity;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * @author Kushal
 *
 */
@Repository
public class BookingDAO extends AbstractDAO<BookingEntity, Long> {

	private static final Logger logger = Logger.getLogger(BookingDAO.class);
	
	protected BookingDAO() {
		super(BookingEntity.class);
	}

	public BookingEntity createBooking(BookingEntity booking) throws Exception{
		Date date = new Date();
		booking.setCreatedTime(date);
		booking.setUpdatedTime(date);
		try{
			save(booking);
		}catch(Exception e){
			logger.error("Exception while create new booking: "+booking, e);
			throw new Exception("Exception while create new booking: "+booking, e);
		}
		return booking;
	}
	
	public BookingEntity updateBooking(BookingEntity booking) throws Exception{
		booking.setUpdatedTime(new Date());
		try{
			saveOrUpdate(booking);
		}catch(Exception e){
			logger.error("Exception while update booking: "+booking, e);
			throw new Exception("Exception while update booking: "+booking, e);
		}
		return booking;
	}

	public BookingEntity getBookingById(Long id) throws Exception {
		try{
			return findById(id);
		}catch(Exception e){
			logger.error("Exception while fetching booking for id: "+id, e);
			throw new Exception("Exception while fetching booking for id: "+id, e);
		}
	}
   
}
