package it.bootcamp.exeption;

import it.bootcamp.controller.UserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotExistsInDBException extends RuntimeException {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    public NotExistsInDBException(String message) {
        super(message);
        logger.info(message);
    }
}
