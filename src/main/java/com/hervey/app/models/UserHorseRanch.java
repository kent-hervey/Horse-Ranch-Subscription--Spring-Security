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
	
	














	
	

}
