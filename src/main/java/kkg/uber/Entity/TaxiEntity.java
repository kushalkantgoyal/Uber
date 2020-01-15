package kkg.uber.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Kushal
 *
 */
@Entity
@Table(name = "taxis")
public class TaxiEntity extends BaseEntity {

	public TaxiEntity() {
	}

	public TaxiEntity(String registrationNumber, String driverName,
			Long mobileNumber, String email, TaxiTypeEntity taxiType,
			Double lat, Double lon, Boolean occupied) {
		super();
		this.registrationNumber = registrationNumber;
		this.driverName = driverName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.taxiType = taxiType;
		this.lat = lat;
		this.lon = lon;
		this.occupied = occupied;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1623265882453966660L;

	@Column (name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;
    
    @Column (name = "driver_name", nullable = false)
    private String driverName;
    
    @Column (name = "mobile_number", nullable = false, unique = true)
    private Long mobileNumber;
    
    @Column (name = "email", nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn (name = "taxi_type_id", nullable = false)
    private TaxiTypeEntity taxiType;

    @Column (name = "lat")
    private Double lat;

    @Column (name = "lon")
    private Double lon;

    @Column (name = "occupied", nullable = false)
    private Boolean occupied;

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TaxiTypeEntity getTaxiType() {
		return taxiType;
	}

	public void setTaxiType(TaxiTypeEntity taxiType) {
		this.taxiType = taxiType;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
}
