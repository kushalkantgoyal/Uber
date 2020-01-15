package kkg.uber.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import kkg.uber.Util.TaxiType;

/**
 * @author Kushal
 *
 */
@Entity
@Table(name = "taxi_types")
public class TaxiTypeEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9186398771246431348L;

	@Enumerated(EnumType.STRING)
	@Column (name = "name", nullable = false, unique = true)
    private TaxiType name;
    
    @Column (name = "base_fare", nullable = false)
    private Float baseFare;
    
    @Column (name = "rate_per_km", nullable = false)
    private Float ratePerKm;
    
    @Column (name = "rate_per_min", nullable = false)
    private Float ratePerMin;

	public Float getBaseFare() {
		return baseFare;
	}

	public void setBaseFare(Float baseFare) {
		this.baseFare = baseFare;
	}

	public Float getRatePerKm() {
		return ratePerKm;
	}

	public void setRatePerKm(Float ratePerKm) {
		this.ratePerKm = ratePerKm;
	}

	public Float getRatePerMin() {
		return ratePerMin;
	}

	public void setRatePerMin(Float ratePerMin) {
		this.ratePerMin = ratePerMin;
	}

	public TaxiType getName() {
		return name;
	}

	public void setName(TaxiType name) {
		this.name = name;
	}

}
