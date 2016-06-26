package kkg.uber.DAO;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import kkg.uber.Entity.TaxiEntity;
import kkg.uber.Util.TaxiType;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/**
 * @author Kushal
 *
 */
@Repository
public class TaxiDAO extends AbstractDAO<TaxiEntity, Long> {

	private static final Logger logger = Logger.getLogger(UserDAO.class);
	
	protected TaxiDAO() {
		super(TaxiEntity.class);
		// TODO Auto-generated constructor stub
	}

	public TaxiEntity createTaxi(TaxiEntity taxi) throws Exception{
		Date date = new Date();
		taxi.setCreatedTime(date);
		taxi.setUpdatedTime(date);
		try{
			save(taxi);
		}catch(Exception e){
			logger.error("Exception while persisting taxi: "+taxi, e);
			throw new Exception("Exception while persisting taxi: "+taxi, e);
		}
		return taxi;
	}
	
	public TaxiEntity updateTaxi(TaxiEntity taxi) throws Exception{
		taxi.setUpdatedTime(new Date());
		try{
			saveOrUpdate(taxi);
		}catch(Exception e){
			logger.error("Exception while updating taxi: "+taxi, e);
			throw new Exception("Exception while updating taxi: "+taxi, e);
		}
		return taxi;
	}

	public TaxiEntity getTaxiById(Long id) throws Exception {
		try{
			return findById(id);
		}catch(Exception e){
			logger.error("Exception while fetching taxi for id: "+id, e);
			throw new Exception("Exception while fetching taxi for id: "+id, e);
		}
	}
	
	public Boolean removeTaxi(TaxiEntity taxi) throws Exception {
		try{
			delete(taxi);
			return true;
		}catch(Exception e){
			logger.error("Exception while deleting taxi for id: "+taxi.getId(), e);
			throw new Exception("Exception while deleting taxi for id: "+taxi.getId(), e);
		}
	}

	public TaxiEntity getNearestTaxi(TaxiType taxiType, Double userLat,
			Double userLon) throws Exception {
		try{
	        String queryString = "SELECT t.id FROM taxis t,taxi_types tt WHERE !t.occupied and tt.name=\'"+taxiType.name()+"\' and t.taxi_type_id = tt.id ORDER BY POW((POW(t.lat-"+userLat+",2)+POW(t.lon-"+userLon+",2)),0.5) ASC LIMIT 1;";
	        SQLQuery query = getCurrentSession().createSQLQuery(queryString);
	        
			List<Object> list = query.list();

	        if(!CollectionUtils.isEmpty(list)){
				TaxiEntity nearestTaxi = getTaxiById(((BigInteger) list.get(0)).longValue());
				return nearestTaxi;
			}
			
			return null;
			
		}catch(Exception e){
			logger.error("Exception while fetching nearest taxi", e);
			throw new Exception("Exception while fetching nearest taxi", e);
		}
	}
   
}
