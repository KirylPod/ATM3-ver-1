package by.training.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Logging {

    String PROCESSING = "Processing – do not remove the card";
    String COMPLETE = "Transaction complete. Remove the card";
    String REPEAT = "Please try again";
    String REQUEST = "We are processing your request";

    default public Logger getLogger(){
        return LogManager.getLogger(this.getClass());
    }
}
