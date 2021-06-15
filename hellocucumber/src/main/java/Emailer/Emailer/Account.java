package Emailer;

public class Account{

    private int receivedEmails;
    private String email;

    public Account(String email){
        this.email = email;
        this.receivedEmails = 0;
    }

    public void receiveEmail(){
        this.receivedEmails += 1;
    }

    public void sendEmailTo(Account receiver){
        receiver.receiveEmail();
    }

    public int getReceivedEmails(){
        return this.receivedEmails;
    }
}