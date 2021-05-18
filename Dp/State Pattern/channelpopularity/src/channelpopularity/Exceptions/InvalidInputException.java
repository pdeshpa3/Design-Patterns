package channelpopularity.Exceptions;

public class InvalidInputException extends Exception {
    /**
     * This method prints an exception
     * @param messg : Message to print
     */
    public InvalidInputException(String messg){
        System.out.println("Invalid Input " + messg);
    }
}
