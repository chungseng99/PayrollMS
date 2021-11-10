package exception;

/**
 * This is class of ItemNotAvailableForUpdateException
 * 
 * @author Group L
 *
 */
public class ItemNotAvailableForUpdateException extends Exception {

	private String message = "Item is not available for update";

	/**
	 * Constructor of this exception class
	 */
	public ItemNotAvailableForUpdateException() {
		super();
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
