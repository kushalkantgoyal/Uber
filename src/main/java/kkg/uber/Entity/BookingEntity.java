package kkg.uber.Entity;

import java.util.Date;

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
@Table(name = "bookings")
public class BookingEntity extends CommonEntity {

	public BookingEntity() {
	}

	public BookingEntity(UserEntity user, TaxiEntity taxi) {
		super();
		this.user = user;
		this.taxi = taxi;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -791527586594242650L;

	@ManyToOne
	@JoinColumn (name = "user_id", nullable = false)
    private UserEntity user;
    
	@ManyToOne
	@JoinColumn (name = "taxi_id", nullable = false)
    private TaxiEntity taxi;
    
    @Column (name = "fare")
    private Float fare;
    
    @Column (name = "start_time")
    private Date startTime;

    @Column (name = "end_time")
    private Date endTime;

    @Column (name = "start_lat")
    private Double startLat;

    @Column (name = "start_lon")
    private Double startLon;
    
    @Column (name = "end_lat")
    private Double endLat;

    @Column (name = "end_lon")
    private Double endLon;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public TaxiEntity getTaxi() {
		return taxi;
	}

	public void setTaxi(TaxiEntity taxi) {
		this.taxi = taxi;
	}

	public Float getFare() {
		return fare;
	}

	public void setFare(Float fare) {
		this.fare = fare;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Double getStartLat() {
		return startLat;
	}

	public void setStartLat(Double startLat) {
		this.startLat = startLat;
	}

	public Double getStartLon() {
		return startLon;
	}

	public void setStartLon(Double startLon) {
		this.startLon = startLon;
	}

	public Double getEndLat() {
		return endLat;
	}

	public void setEndLat(Double endLat) {
		this.endLat = endLat;
	}

	public Double getEndLon() {
		return endLon;
	}

	public void setEndLon(Double endLon) {
		this.endLon = endLon;
	}

    	
}
