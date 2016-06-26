package kkg.uber.Service;

import javax.transaction.Transactional;

import kkg.uber.DAO.TaxiDAO;
import kkg.uber.Entity.TaxiEntity;
import kkg.uber.Entity.TaxiTypeEntity;
import kkg.uber.Util.TaxiType;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kushal
 *
 */
@Service("taxiService")
@Transactional
public class TaxiService {
	@Autowired
	private TaxiDAO taxiDAO;
	private static final Logger logger = Logger.getLogger(TaxiService.class);
	
	public TaxiEntity createTaxi(TaxiEntity taxi) throws Exception{
		try{
			TaxiTypeEntity taxiType = new TaxiTypeEntity();
			taxiType.setId(taxi.getTaxiType().getId());
			taxi.setTaxiType(taxiType);
			taxiDAO.createTaxi(taxi);
			return taxi;
		}catch(Exception e){
			logger.error("Exception while creating taxi: "+taxi, e);
			throw new Exception("Exception while creating taxi: "+taxi, e);
		}
	}
	
	public TaxiEntity updateTaxi(TaxiEntity newTaxi) throws Exception{
		try{
			TaxiEntity oldTaxi = taxiDAO.findById(newTaxi.getId());
			
			if(StringUtils.isNotEmpty(newTaxi.getEmail())){
				oldTaxi.setEmail(newTaxi.getEmail());
			}
			if(StringUtils.isNotEmpty(newTaxi.getDriverName())){
				oldTaxi.setDriverName(newTaxi.getDriverName());
			}
			if(StringUtils.isNotEmpty(newTaxi.getRegistrationNumber())){
				oldTaxi.setRegistrationNumber(newTaxi.getRegistrationNumber());
			}
			if(newTaxi.getMobileNumber()!=null){
				oldTaxi.setMobileNumber(newTaxi.getMobileNumber());
			}
			if(newTaxi.getOccupied()!=null){
				oldTaxi.setOccupied(newTaxi.getOccupied());
			}
			if(newTaxi.getLat()!=null){
				oldTaxi.setLat(newTaxi.getLat());
			}
			if(newTaxi.getLon()!=null){
				oldTaxi.setLon(newTaxi.getLon());
			}
			if(newTaxi.getTaxiType()!=null){
				TaxiTypeEntity taxiType = new TaxiTypeEntity();
				taxiType.setId(newTaxi.getTaxiType().getId());
				oldTaxi.setTaxiType(taxiType);
			}
			
			taxiDAO.updateTaxi(oldTaxi);
			return oldTaxi;
			
		}catch(Exception e){
			logger.error("Exception while updating taxi: "+newTaxi, e);
			throw new Exception("Exception while updating taxi: "+newTaxi, e);
		}
	}

	public TaxiEntity getTaxiById(Long id) throws Exception {
		try{
			return taxiDAO.getTaxiById(id);
		}catch(Exception e){
			logger.error("Exception while fetching taxi for id: "+id, e);
			throw new Exception("Exception while fetching taxi for id: "+id, e);
		}
	}

	public TaxiEntity findNearestTaxi(TaxiType taxiType, Double userLat,
			Double userLon) throws Exception{
		try{
			return taxiDAO.getNearestTaxi(taxiType, userLat, userLon);
		}catch(Exception e){
			logger.error("Exception while fetching nearest taxi", e);
			throw new Exception("Exception while fetching nearest taxi", e);
		}
	}
}
