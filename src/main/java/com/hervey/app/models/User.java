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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users") // enhancement would be to change this to "users"
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // default strategy is AUTO...IDENTITY is most common and AUTO
														// selects it for MySQL
	private Long id;

	@Size(min = 1)
	private String firstName;

	@Size(min = 1)
	private String lastName;

	@Email() //uses default message
	@NotEmpty
	@Column(unique = true)
	private String email;

	@Size(min = 8)
	private String password;

	@Transient
	private String passwordConfirmation;
	
	
	private String noteToAdmin;

	private Date lastSignIn;

	private Date currentSignIn;

	@Column(updatable = false)
	private Date createdAt;

	private Date updateAt;

	
	//Many Users to Many roles...because each user can have many roles...and each user has many roles
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles; // this is the list of roles any one/this user has
	
	
	//Many Users to Many HorseRanches...because many subscribers
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="users_horse_ranches",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "horse_ranch_id")
			)
	private List<HorseRanch> horseRanches; //the list of horse ranches this user has subscribed
	
	
	//This one user to HorseRanch Many:  Because each user can own many Horse Ranches, but each Horse Ranch can have only one owner (user)
	@OneToMany(mappedBy="ranchOwner", fetch= FetchType.LAZY)
	private List<HorseRanch> ranchesOwned;  //this is the list of ranches owned by this user
	
	
	
	

	public Boolean isUserAdmin() {
		for (Role role : roles) {
			if (role.getName().contains("ADMIN")) {
				return true;
			}
			// System.out.println(role.getName());
		}
		return false;
	}
	
	public Boolean isUserGuest() {
		for (Role role : roles) {
			if (role.getName().contains("GUEST")) {
				return true;
			}
			// System.out.println(role.getName());
		}
		return false;
	}
	
	public Boolean isUserOwner() {
		for (Role role : roles) {
			if (role.getName().contains("OWNER")) {
				return true;
			}
			// System.out.println(role.getName());
		}
		return false;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
		this.lastSignIn = new Date();
		this.currentSignIn = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		// this.lastSignIn = new Date();
		this.updateAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getNoteToAdmin() {
		return noteToAdmin;
	}

	public void setNoteToAdmin(String noteToAdmin) {
		this.noteToAdmin = noteToAdmin;
	}

	public Date getLastSignIn() {
		return lastSignIn;
	}

	public void setLastSignIn(Date lastSignIn) {
		this.lastSignIn = lastSignIn;
	}

	public Date getCurrentSignIn() {
		return currentSignIn;
	}

	public void setCurrentSignIn(Date currentSignIn) {
		this.currentSignIn = currentSignIn;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	

	public List<HorseRanch> getHorseRanches() {
		return horseRanches;
	}

	public void setHorseRanches(List<HorseRanch> horseRanches) {
		this.horseRanches = horseRanches;
	}

	public List<HorseRanch> getRanchesOwned() {
		return ranchesOwned;
	}

	public void setRanchesOwned(List<HorseRanch> ranchesOwned) {
		this.ranchesOwned = ranchesOwned;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((currentSignIn == null) ? 0 : currentSignIn.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((horseRanches == null) ? 0 : horseRanches.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((lastSignIn == null) ? 0 : lastSignIn.hashCode());
		result = prime * result + ((noteToAdmin == null) ? 0 : noteToAdmin.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((passwordConfirmation == null) ? 0 : passwordConfirmation.hashCode());
		result = prime * result + ((ranchesOwned == null) ? 0 : ranchesOwned.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((updateAt == null) ? 0 : updateAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("top of user equals method");
		if (this == obj)
			return true;
		System.out.println("equals method about to check for obj=null");
		if (obj == null) {
			System.out.println("equals method thinks obj passed in (not this.obj) is null");
			return false;
		}
		if (getClass() != obj.getClass()) {
			System.out.println("getClass is:  " + getClass());
			System.out.println("obj.getClass is:  " + obj.getClass());
			System.out.println("user method says this.obj and passed in obj are not of same class, so equals is false");
			return false;
		}
		System.out.println("user equals aobut to check for user other = user obj");
		User other = (User) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (currentSignIn == null) {
			if (other.currentSignIn != null)
				return false;
		} else if (!currentSignIn.equals(other.currentSignIn))
			return false;
		System.out.println("user equals about to check for email==null");
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (horseRanches == null) {
			if (other.horseRanches != null)
				return false;
		} else if (!horseRanches.equals(other.horseRanches))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (lastSignIn == null) {
			if (other.lastSignIn != null)
				return false;
		} else if (!lastSignIn.equals(other.lastSignIn))
			return false;
		if (noteToAdmin == null) {
			if (other.noteToAdmin != null)
				return false;
		} else if (!noteToAdmin.equals(other.noteToAdmin))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordConfirmation == null) {
			if (other.passwordConfirmation != null)
				return false;
		} else if (!passwordConfirmation.equals(other.passwordConfirmation))
			return false;
		if (ranchesOwned == null) {
			if (other.ranchesOwned != null)
				return false;
		} else if (!ranchesOwned.equals(other.ranchesOwned))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (updateAt == null) {
			if (other.updateAt != null)
				return false;
		} else if (!updateAt.equals(other.updateAt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", passwordConfirmation=" + passwordConfirmation  + ", noteToAdmin=" + noteToAdmin + ", lastSignIn=" + lastSignIn + ", currentSignIn="
				+ currentSignIn + ", createdAt=" + createdAt + ", updateAt=" + updateAt + "xUser Entity]";
	}




}
