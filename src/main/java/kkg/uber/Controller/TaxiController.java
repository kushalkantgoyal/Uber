package kkg.uber.Controller;

import kkg.uber.Entity.TaxiEntity;
import kkg.uber.Entity.UserEntity;
import kkg.uber.Service.TaxiService;
import kkg.uber.Service.UserService;
import kkg.uber.Util.Result;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
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
@RequestMapping("taxi")
public class TaxiController {

	@Autowired
	private TaxiService taxiService;
	private static final Logger logger = Logger.getLogger(TaxiController.class);
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public Result getTaxiById(@RequestParam("id") Long id){
		try {
			TaxiEntity taxi = taxiService.getTaxiById(id);
			if(taxi!=null){
				return new Result(true,"Successfull",taxi);
			}
			else{
				return new Result(true,"Taxi doesn't exist with id: "+id,null);
			}
		} catch (Exception e) {
			logger.error("Exception while getting taxis for id: "+id, e);
			return new Result(false,null,null);
		}
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Result createTaxi(@RequestBody TaxiEntity taxi){
		try {
			taxi = taxiService.createTaxi(taxi);
			if(taxi!=null){
				return new Result(true,"Taxi created successfully",taxi);
			}
			else{
				return new Result(true,"Unable to create taxi",null);
			}
		} catch (Exception e) {
			logger.error("Exception while creating a new taxi"+taxi, e);
			return new Result(false,null,null);
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Result deleteTaxi(@RequestParam(value = "id", required = true) Long id){
		try {
			Boolean taxiRemoved = taxiService.removeTaxi(id);
			if(taxiRemoved){
				return new Result(true,"Taxi removed successfully",null);
			}
			else{
				return new Result(true,"Unable to remove taxi",null);
			}
		} catch (Exception e) {
			logger.error("Error while removing taxi with id: "+id, e);
			return new Result(false,"Error while removing taxi with id: "+id,null);
			
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public Result updateTaxi(@RequestBody TaxiEntity taxi){
		try {
			taxi = taxiService.updateTaxi(taxi);
			if(taxi!=null){
				return new Result(true,"Taxi updated successfully",taxi);
			}
			else{
				return new Result(true,"Unable to update taxi",null);
			}
		} catch (Exception e) {
			logger.error("Exception while updating taxi"+taxi, e);
			return new Result(false,null,null);
		}
	}
}
