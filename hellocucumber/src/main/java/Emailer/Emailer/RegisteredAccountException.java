package Emailer;

public class RegisteredAccountException extends RuntimeException{

    public RegisteredAccountException(String message){
        super(message);
    }
}
