package Emailer;
import java.util.HashMap;

public class Emailer{

    private HashMap<String, Account> accounts;

    public Emailer(){
        this.accounts = new HashMap<String, Account>();
    }

    public void createAccount(String email) throws RegisteredAccountException{

        if(this.isRegistered(email)){
            throw new RegisteredAccountException("Account is already registered");
        }

        this.accounts.put(email.toLowerCase(), new Account(email.toLowerCase()));
    }

    public void sendEmail(String sender, String receiver) throws NonExistingAccountException{

        if(!this.isRegistered(sender) || !this.isRegistered(receiver))
            throw new NonExistingAccountException("Non existing account detected.");

        Account senderAccount = this.accounts.get(sender.toLowerCase());
        Account receiverAccount = this.accounts.get(receiver.toLowerCase());
        senderAccount.sendEmailTo(receiverAccount);
    }

    public boolean isRegistered(String email){
        return this.accounts.containsKey(email.toLowerCase());
    }

    public int pendingEmails(String email) throws NonExistingAccountException{
        if(!this.isRegistered(email))
            throw new NonExistingAccountException("Non existing account detected");

        Account account = this.accounts.get(email.toLowerCase());
        return account.getReceivedEmails();
    }
}
