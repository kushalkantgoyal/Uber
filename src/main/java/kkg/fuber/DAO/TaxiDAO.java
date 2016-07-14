package kkg.fuber.DAO;

import kkg.fuber.Entity.TaxiEntity;
import kkg.fuber.Util.TaxiType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * @author Kushal
 *
 */
@Repository
public class TaxiDAO extends CommonDAO<TaxiEntity> {

	private static final Logger logger = Logger.getLogger(TaxiDAO.class);
	
	@Override
	public Class<TaxiEntity> getModelClass() {
		return TaxiEntity.class;
	}


	public TaxiEntity getNearestTaxi(TaxiType taxiType, Double userLat,
			Double userLon) throws Exception {
		try{
			initCommonAttributes();
				
			String sqlString = "SELECT t.* FROM taxis t,taxi_types tt WHERE "
			+ "!t.occupied and tt.name=:taxiTypeName and t.taxi_type_id = tt.id"
					+ " ORDER BY SQRT(POW(t.lat-:userLat,2)+POW(t.lon-:userLon,2))"
							+ " ASC LIMIT 1;";
			
			TaxiEntity resultList = (TaxiEntity) entityManager.createNativeQuery(sqlString,TaxiEntity.class)
					.setParameter("taxiTypeName", taxiType.name())
					.setParameter("userLat", userLat)
					.setParameter("userLon", userLon)
					.getSingleResult();
			
			return resultList;
			
		}catch(Exception e){
			logger.error("Exception while fetching nearest taxi", e);
			throw new Exception("Exception while fetching nearest taxi", e);
		}
	}
}
