package kkg.fuber.DAO;

import kkg.fuber.Entity.TaxiTypeEntity;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * @author Kushal
 *
 */
@Repository
public class TaxiTypeDAO extends CommonDAO<TaxiTypeEntity> {

	private static final Logger logger = Logger.getLogger(TaxiTypeDAO.class);
	
	@Override
	public Class<TaxiTypeEntity> getModelClass() {
		return TaxiTypeEntity.class;
	}
   
}
