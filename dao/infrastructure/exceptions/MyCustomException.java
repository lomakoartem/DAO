package dao.infrastructure.exceptions;

/**
 * Created by lomak on 18.11.2016.
 */
public class MyCustomException extends Exception {

        private static final long serialVersionUID = 1L;

        public MyCustomException() { }

        public MyCustomException(String message) {
            super(message);
        }

        public MyCustomException(String message, Throwable cause) {
            super(message, cause);
        }

}
