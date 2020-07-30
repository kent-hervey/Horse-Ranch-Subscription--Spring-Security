package com.hervey.app.models;

import java.util.Collection;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;

//wrapper class/decorator to add functionality to User class
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6919933950585765014L;

	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private Boolean isUserAdmin;

	private Date lastSignIn;

	private Date currentSignIn;

	private Date createdAt;

	private Date updateAt;

	public CustomUserDetails(Long id, String firstName, String lastName, String email, String password,
			Boolean isUserAdmin, Date lastSignIn, Date currentSignIn, Date createdAt, Date updateAt, Boolean enabled,
			Boolean accountNonExpired, Boolean credentialsNonExpired, Boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {

		super(email, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isUserAdmin = isUserAdmin;
		this.lastSignIn = lastSignIn;
		this.currentSignIn = currentSignIn;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	public Boolean getIsUserAdmin() {
		return isUserAdmin;
	}

	public void setIsUserAdmin(Boolean isUserAdmin) {
		this.isUserAdmin = isUserAdmin;
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

	public Date getLastSignIn() {
		return lastSignIn;
	}

	public Date getCurrentSignIn() {
		return currentSignIn;
	}

	public void setCurrentSignIn(Date currentSignIn) {
		this.currentSignIn = currentSignIn;
	}

	public void setLastSignIn(Date lastSignIn) {
		this.lastSignIn = lastSignIn;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((currentSignIn == null) ? 0 : currentSignIn.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isUserAdmin == null) ? 0 : isUserAdmin.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((lastSignIn == null) ? 0 : lastSignIn.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((updateAt == null) ? 0 : updateAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomUserDetails other = (CustomUserDetails) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isUserAdmin == null) {
			if (other.isUserAdmin != null)
				return false;
		} else if (!isUserAdmin.equals(other.isUserAdmin))
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		return "CustomUserDetails [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", password=" + password + ", isUserAdmin=" + isUserAdmin + ", lastSignIn=" + lastSignIn
				+ ", currentSignIn=" + currentSignIn + ", createdAt=" + createdAt + ", updateAt=" + updateAt + "]";
	}

}
