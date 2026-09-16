package in.brainupgrade.accountservice.model;

public class AuthenticationResponse {
	/**
	 * Class used for sending authentication response
	 */
	private String userid;
	private String name;
	private boolean isValid;

	/**
	 * Class used for sending authentication response
	 */
	public String getUserid() {
		return this.userid;
	}

	public String getName() {
		return this.name;
	}

	public boolean isValid() {
		return this.isValid;
	}

	/**
	 * Class used for sending authentication response
	 */
	public void setUserid(final String userid) {
		this.userid = userid;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setValid(final boolean isValid) {
		this.isValid = isValid;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "AuthenticationResponse(userid=" + this.getUserid() + ", name=" + this.getName() + ", isValid=" + this.isValid() + ")";
	}

	public AuthenticationResponse() {
	}

	/**
	 * Creates a new {@code AuthenticationResponse} instance.
	 *
	 * @param userid Class used for sending authentication response
	 * @param name
	 * @param isValid
	 */
	public AuthenticationResponse(final String userid, final String name, final boolean isValid) {
		this.userid = userid;
		this.name = name;
		this.isValid = isValid;
	}
}
