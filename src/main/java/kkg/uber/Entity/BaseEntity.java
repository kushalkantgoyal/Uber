package kkg.uber.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import kkg.uber.Util.Utils;

/**
 * @author Kushal
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2978982773992424210L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id", nullable = false)
	private Long id;

	@Column(name= "created_time", nullable = false)
	private Date createdTime;

	@Column(name= "updated_time", nullable = false)
	private Date updatedTime;
	
	@PrePersist
	private void prePersist(){
		createdTime = Utils.convertDateToGMTDate(new Date());
		updatedTime = Utils.convertDateToGMTDate(new Date());
	}
	
	@PreUpdate
	private void preUpdate(){
		updatedTime = Utils.convertDateToGMTDate(new Date());

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
