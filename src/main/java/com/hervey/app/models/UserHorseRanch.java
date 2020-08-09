package com.hervey.app.models;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="users_horse_ranches") //this could have been called subscribers of ranches
public class UserHorseRanch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(updatable=false)
	private Date createdAt;
	
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	public UserHorseRanch() {
	}


	//Creating Many to Many for subscribers to Ranches via connections from this middle table per below

	//Look back to users table
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User userSubscriber; //this is the subscriber of the ranch in the record
	
	//Look back to horse_ranches table
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="horse_ranch_id")
	private HorseRanch horseRanchSubscriber;  //this is the horse ranch for the subscriber in the record

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUserSubscriber() {
		return userSubscriber;
	}

	public void setUserSubscriber(User userSubscriber) {
		this.userSubscriber = userSubscriber;
	}

	public HorseRanch getHorseRanchSubscriber() {
		return horseRanchSubscriber;
	}

	public void setHorseRanchSubscriber(HorseRanch horseRanchSubscriber) {
		this.horseRanchSubscriber = horseRanchSubscriber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((horseRanchSubscriber == null) ? 0 : horseRanchSubscriber.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		result = prime * result + ((userSubscriber == null) ? 0 : userSubscriber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserHorseRanch other = (UserHorseRanch) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (horseRanchSubscriber == null) {
			if (other.horseRanchSubscriber != null)
				return false;
		} else if (!horseRanchSubscriber.equals(other.horseRanchSubscriber))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		if (userSubscriber == null) {
			if (other.userSubscriber != null)
				return false;
		} else if (!userSubscriber.equals(other.userSubscriber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserHorseRanch [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", userSubscriber=" + userSubscriber + ", horseRanchSubscriber=" + horseRanchSubscriber + "]";
	}
	
	














	
	

}
