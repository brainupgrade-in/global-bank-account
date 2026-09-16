package in.brainupgrade.accountservice.model;

import java.sql.Date;
import jakarta.persistence.Table;

@Table
public class CustomerEntity {
	/**
	 * Class used for inputing customer entity
	 */
	private String userid;
	private String username;
	private String password;
	private Date dateOfBirth;
	private String pan;
	private String address;

	/**
	 * Class used for inputing customer entity
	 */
	public String getUserid() {
		return this.userid;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public String getPan() {
		return this.pan;
	}

	public String getAddress() {
		return this.address;
	}

	/**
	 * Class used for inputing customer entity
	 */
	public void setUserid(final String userid) {
		this.userid = userid;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public void setDateOfBirth(final Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setPan(final String pan) {
		this.pan = pan;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public CustomerEntity() {
	}

	/**
	 * Creates a new {@code CustomerEntity} instance.
	 *
	 * @param userid Class used for inputing customer entity
	 * @param username
	 * @param password
	 * @param dateOfBirth
	 * @param pan
	 * @param address
	 */
	public CustomerEntity(final String userid, final String username, final String password, final Date dateOfBirth, final String pan, final String address) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.pan = pan;
		this.address = address;
	}
}
