package com.hervey.app.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "horse_ranches")

public class HorseRanch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min =1)
	private String location;
	
	@Size(min = 1) 
	private String numberAcres;
	
	@Min(value=1, message="Can't be a horse ranch without a horse")
	private Integer horseCapacity;
	
	@Min(value=1, message="no fun if at least one person can't be there")
	private Integer peopleCapacity;
	
	@Size(min=1, message="price required")
	private String annualSubscriptionPrice;


	@Column(updatable = false)
	private Date createdAt;

	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate 
	protected void onUpdate() {
		// this.lastSignIn = new Date();
		this.updatedAt = new Date();
	}
	
	public HorseRanch() {
 
	}

	
	//Need many to many to User for the subscribers
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_horse_ranches",
			joinColumns = @JoinColumn(name = "horse_ranch_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> subscribers;  //users who have this Horse Ranch; or subscribers to this horse ranch
	
	//this many Horse Ranches to one user accounts for many ranches each being owned by one user/owner
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User ranchOwner; //This is the user who can own several ranches; per field user_id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNumberAcres() {
		return numberAcres;
	}

	public void setNumberAcres(String numberAcres) {
		this.numberAcres = numberAcres;
	}

	public Integer getHorseCapacity() {
		return horseCapacity;
	}

	public void setHorseCapacity(Integer horseCapacity) {
		this.horseCapacity = horseCapacity;
	}

	public Integer getPeopleCapacity() {
		return peopleCapacity;
	}

	public void setPeopleCapacity(Integer peopleCapacity) {
		this.peopleCapacity = peopleCapacity;
	}

	public String getAnnualSubscriptionPrice() {
		return annualSubscriptionPrice;
	}

	public void setAnnualSubscriptionPrice(String annualSubscriptionPrice) {
		this.annualSubscriptionPrice = annualSubscriptionPrice;
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

	public List<User> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<User> subscribers) {
		this.subscribers = subscribers;
	}

	public User getRanchOwner() {
		return ranchOwner;
	}

	public void setRanchOwner(User ranchOwner) {
		this.ranchOwner = ranchOwner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annualSubscriptionPrice == null) ? 0 : annualSubscriptionPrice.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((horseCapacity == null) ? 0 : horseCapacity.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((numberAcres == null) ? 0 : numberAcres.hashCode());
		result = prime * result + ((peopleCapacity == null) ? 0 : peopleCapacity.hashCode());
		result = prime * result + ((ranchOwner == null) ? 0 : ranchOwner.hashCode());
		result = prime * result + ((subscribers == null) ? 0 : subscribers.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		HorseRanch other = (HorseRanch) obj;
		if (annualSubscriptionPrice == null) {
			if (other.annualSubscriptionPrice != null)
				return false;
		} else if (!annualSubscriptionPrice.equals(other.annualSubscriptionPrice))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (horseCapacity == null) {
			if (other.horseCapacity != null)
				return false;
		} else if (!horseCapacity.equals(other.horseCapacity))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (numberAcres == null) {
			if (other.numberAcres != null)
				return false;
		} else if (!numberAcres.equals(other.numberAcres))
			return false;
		if (peopleCapacity == null) {
			if (other.peopleCapacity != null)
				return false;
		} else if (!peopleCapacity.equals(other.peopleCapacity))
			return false;
		if (ranchOwner == null) {
			if (other.ranchOwner != null)
				return false;
		} else if (!ranchOwner.equals(other.ranchOwner))
			return false;
		if (subscribers == null) {
			if (other.subscribers != null)
				return false;
		} else if (!subscribers.equals(other.subscribers))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HorseRanch [id=" + id + ", location=" + location + ", numberAcres=" + numberAcres + ", horseCapacity="
				+ horseCapacity + ", peopleCapacity=" + peopleCapacity + ", annualSubscriptionPrice="
				+ annualSubscriptionPrice + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", subscribers="
				+ subscribers + ", ranchOwner=" + ranchOwner + "]";
	}

	
	
	
}
