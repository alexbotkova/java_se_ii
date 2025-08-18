package cz.alexbotkova;

/**
 * Exception for when the user input could not be processed.
 */
public class InvalidInputException extends RuntimeException {
  /**
   * Creates a new InvalidInputException with a detail message.
   * @param message a detail message
   */
  public InvalidInputException(String message) {
    super(message);
  }
}
