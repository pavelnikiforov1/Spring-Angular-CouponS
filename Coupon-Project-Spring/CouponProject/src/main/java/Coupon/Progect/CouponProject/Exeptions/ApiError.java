package Coupon.Progect.CouponProject.Exeptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A Class to represent the messages that describes the Exception thrown
 * 
 * @author pavel
 *
 */
public class ApiError {

	private HttpStatus status;

	private List<String> messages;

	/**
	 * Default Constructor for this class
	 * 
	 * @param status   - represent the status code for the Exception
	 * @param messages - represent one or more messages the describe the Exception
	 */
	public ApiError(HttpStatus status, String... messages) {
		super();
		this.status = status;
		this.messages = Arrays.asList(messages);
	}

	/**
	 * A method to add new message to the Exception
	 * 
	 * @param message -.
	 */
	public void addMessage(String message) {
		messages.add(message);
	}

	/**
	 * A method to get the Status Code For the Exception
	 * @return status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * A method to Set the Status Code For the Exception
	 * @param status -.
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * A method to get the List of messages Form the exception
	 * 
	 * @return - A list of messages List(String)
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * A method to set the List of messages Form the exception
	 * @param messages  -. 
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
