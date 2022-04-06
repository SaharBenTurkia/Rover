package Exceptions;

public class RoverException extends Exception {

	private static final long serialVersionUID = 1L;
    private String message;

    public RoverException(String m) {
        
        super(m);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
