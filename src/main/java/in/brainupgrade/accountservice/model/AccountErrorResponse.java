package in.brainupgrade.accountservice.model;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class AccountErrorResponse {
	/**
	 * AccountErrorResponse for returning Error response
	 */
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String reason;
	private String message;

	/**
	 * Creates a new {@code AccountErrorResponse} instance.
	 *
	 * @param timestamp AccountErrorResponse for returning Error response
	 * @param status
	 * @param reason
	 * @param message
	 */
	public AccountErrorResponse(final LocalDateTime timestamp, final HttpStatus status, final String reason, final String message) {
		this.timestamp = timestamp;
		this.status = status;
		this.reason = reason;
		this.message = message;
	}

	public AccountErrorResponse() {
	}

	/**
	 * AccountErrorResponse for returning Error response
	 */
	public LocalDateTime getTimestamp() {
		return this.timestamp;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	public String getReason() {
		return this.reason;
	}

	public String getMessage() {
		return this.message;
	}

	/**
	 * AccountErrorResponse for returning Error response
	 */
	public void setTimestamp(final LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public void setStatus(final HttpStatus status) {
		this.status = status;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
