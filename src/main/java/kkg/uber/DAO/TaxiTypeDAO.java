package kkg.uber.DAO;

import kkg.uber.Entity.TaxiTypeEntity;

import org.springframework.stereotype.Repository;

/**
 * @author Kushal
 *
 */
@Repository
public class TaxiTypeDAO extends AbstractDAO<TaxiTypeEntity, String> {

	protected TaxiTypeDAO() {
		super(TaxiTypeEntity.class);
		// TODO Auto-generated constructor stub
	}

   
}
