package kkg.fuber.Service;

import kkg.fuber.DAO.TaxiDAO;
import kkg.fuber.Entity.TaxiEntity;
import kkg.fuber.Entity.TaxiTypeEntity;
import kkg.fuber.Util.TaxiType;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
			taxiDAO.create(taxi);
			return taxi;
		}catch(Exception e){
			logger.error("Exception while creating taxi: "+taxi, e);
			throw new Exception("Exception while creating taxi: "+taxi, e);
		}
	}
	
	public TaxiEntity updateTaxi(TaxiEntity newTaxi) throws Exception{
		try{
			TaxiEntity oldTaxi = taxiDAO.getById(newTaxi.getId());
			
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
			
			taxiDAO.update(oldTaxi);
			return oldTaxi;
			
		}catch(Exception e){
			logger.error("Exception while updating taxi: "+newTaxi, e);
			throw new Exception("Exception while updating taxi: "+newTaxi, e);
		}
	}

	public TaxiEntity getTaxiById(Long id) throws Exception {
		try{
			return taxiDAO.getById(id);
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

	public Boolean removeTaxi(Long taxiId) throws Exception {
		TaxiEntity taxi = null;
		try{
			taxi = taxiDAO.getById(taxiId);
		}catch(Exception e){
			logger.error("Exception while fetching taxi with id: "+taxiId, e);
			throw new Exception("Exception while fetching taxi with id: "+taxiId, e);
		}
		if(taxi!=null){
			try{
				return taxiDAO.remove(taxi);
			}catch(Exception e){
				logger.error("Exception while removing taxi with id: "+taxiId, e);
				//throw new Exception("Exception while removing taxi with id: "+taxiId, e);
				return false;
			}
		}
		else{
			return false;
		}
		
	}
}
