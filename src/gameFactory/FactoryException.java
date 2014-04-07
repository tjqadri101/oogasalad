package gameFactory;
/**
 * FactoryException is thrown when the ExecutableFactory
 * could not instantiate the desired object.
 * @Author: Steve (Siyang) Wang
 */


@SuppressWarnings("serial")
public class FactoryException extends Exception {
    public FactoryException () {
        super();
    }

    public FactoryException (String message) {
        super(message);
    }
}

