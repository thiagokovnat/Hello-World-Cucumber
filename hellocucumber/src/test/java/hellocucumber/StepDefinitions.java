package hellocucumber;

import Emailer.Emailer;
import Emailer.NonExistingAccountException;
import Emailer.RegisteredAccountException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;


public class StepDefinitions {

    private Emailer emailer = null;
    private NonExistingAccountException nea = null;
    private RegisteredAccountException rae = null;

    @Given("Account {string} is not registered.")
    public void account_is_not_registered(String string) {
        this.emailer = new Emailer();
    }

    @When("Trying to register {string}")
    public void trying_to_register(String string) {

        try {
            this.emailer.createAccount(string);
        }catch (RegisteredAccountException ex){
            this.rae = ex;
        }
    }

    @Then("Account {string} should be created succesfully")
    public void account_should_be_created_succesfully(String string) {
        assertTrue(this.emailer.isRegistered(string));
        assertNull(this.rae);
    }

    @Given("Account {string} is already registered")
    public void accountIsAlreadyRegistered(String email) {
        this.emailer = new Emailer();
        this.emailer.createAccount(email);
    }

    @Then("Operation should be denied due to existing account")
    public void operationShouldBeDeniedDueToExistingAccount() {
        assertNotNull(this.rae);
    }

    @Given("Account {string} and {string} are already registered")
    public void accountsAreRegistered(String firstEmail, String secondEmail) {
        this.emailer = new Emailer();
        this.emailer.createAccount(firstEmail);
        this.emailer.createAccount(secondEmail);
    }

    @When("Account {string} sends an email to {string}")
    public void sendingEmailBetweenAccounts(String firstEmail, String secondEmail) {

        try{
            this.emailer.sendEmail(firstEmail, secondEmail);
        }catch (NonExistingAccountException ex){
            this.nea = ex;
        }
    }

    @Then("{string} has a new pending email")
    public void receiverHasPendingEmail(String receiver) {
        int pending = -1;
        try{
            pending = this.emailer.pendingEmails(receiver);
        }catch(NonExistingAccountException ex){
            this.nea = ex;
        }

        assertNull(this.nea);
        assertEquals(1, pending);
    }
}
