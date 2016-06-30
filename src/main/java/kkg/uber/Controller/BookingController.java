package kkg.uber.Controller;

import kkg.uber.Entity.BookingEntity;
import kkg.uber.Service.BookingService;
import kkg.uber.Util.Result;
import kkg.uber.Util.TaxiType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kushal
 *
 */
@RestController
@RequestMapping("booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	private static final Logger logger = Logger.getLogger(BookingController.class);
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public Result getBookingById(@RequestParam("id") Long id){
		try {
			BookingEntity booking = bookingService.getBookingById(id);
			if(booking!=null){
				return new Result(true,"Successfull",booking);
			}
			else{
				return new Result(true,"Booking doesn't exist with id: "+id,null);
			}
		} catch (Exception e) {
			logger.error("Exception while getting booking for id: "+id, e);
			return new Result(false,null,null);
		}
	}
	
	@RequestMapping(value = "/bookTaxi", method = RequestMethod.POST)
	@ResponseBody
	public Result bookTaxi(@RequestParam(value = "userId", required = true) Long userId,
			@RequestParam(value = "userLat", required = true) Double userLat,
			@RequestParam(value = "userLon", required = true) Double userLon,
			@RequestParam(value = "taxiType", required = true) TaxiType taxiType){
		try {
			BookingEntity booking = bookingService.bookTaxi(userId, taxiType, userLat, userLon);
			if(booking!=null){
				return new Result(true,"Taxi Booked successfully",booking);
			}
			else{
				return new Result(true,"Sorry, No taxi available right now.",null);
			}
		} catch (Exception e) {
			logger.error("Exception while finding a taxi for userId:"+userId, e);
			return new Result(false,"Exception while finding a Taxi",null);
		}
	}
	
	@RequestMapping(value = "/startRide", method = RequestMethod.POST)
	@ResponseBody
	public Result startRide(@RequestParam(value = "bookingId", required = true) Long bookingId){
		try {
			BookingEntity booking = bookingService.startRide(bookingId);
			if(booking!=null){
				return new Result(true,"Ride started successfully",booking);
			}
			else{
				return new Result(true,"Unable to start ride.",null);
			}
		} catch (Exception e) {
			logger.error("Exception while starting ride for bookingId"+bookingId, e);
			return new Result(false,null,null);
		}
	}
	
	@RequestMapping(value = "/endRide", method = RequestMethod.POST)
	@ResponseBody
	public Result endRide(@RequestParam(value = "bookingId", required = true) Long bookingId,
			@RequestParam(value = "endLat", required = true) Double endLat,
			@RequestParam(value = "endLon", required = true) Double endLon){
		try {
			BookingEntity booking = bookingService.endRide(bookingId, endLat, endLon);
			if(booking!=null){
				return new Result(true,"Ride ended successfully.",booking);
			}
			else{
				return new Result(true,"Unable to end ride.",null);
			}
		} catch (Exception e) {
			logger.error("Exception while ending ride for bookingId: "+bookingId, e);
			return new Result(false,null,null);
		}
	}

	@Deprecated
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Result createBooking(@RequestBody BookingEntity booking){
		try {
			booking = bookingService.createBooking(booking);
			if(booking!=null){
				return new Result(true,"Booking created successfully",booking);
			}
			else{
				return new Result(true,"Unable to create booking",null);
			}
		} catch (Exception e) {
			logger.error("Exception while creating a new booking"+booking, e);
			return new Result(false,null,null);
		}
	}
	
	@Deprecated
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public Result updateBooking(@RequestBody BookingEntity booking){
		try {
			booking = bookingService.updateBooking(booking);
			if(booking!=null){
				return new Result(true,"Booking updated successfully",booking);
			}
			else{
				return new Result(true,"Unable to update booking",null);
			}
		} catch (Exception e) {
			logger.error("Exception while updating booking"+booking, e);
			return new Result(false,null,null);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Result deleteBooking(@RequestParam(value = "id", required = true) Long id){
		try {
			Boolean bookingRemoved = bookingService.removeBooking(id);
			if(bookingRemoved){
				return new Result(true,"Booking removed successfully",null);
			}
			else{
				return new Result(true,"Unable to remove booking",null);
			}
		} catch (Exception e) {
			logger.error("Error while removing booking with id: "+id, e);
			return new Result(false,"Error while removing booking with id: "+id,null);
			
		}
	}
}
