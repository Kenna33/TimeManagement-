/*
 * @author  McKenna Woodrow
 * @version 1
 * Project Title: Time Management Planner 
 * File Title: ServiceResponse
 */

package service;

/*
 * Class that is responsible for holding the state of the message to 
 * return to the user 
 */
public class ServiceResponse {

    private boolean success;
    private String message;

    public ServiceResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }


}
