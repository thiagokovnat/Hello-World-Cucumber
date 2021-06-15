Feature: Email operations
  Checking Email Operations


  Scenario: Trying to register a new account
    Given Account 'prueba123@gmail' is not registered.
    When Trying to register 'prueba123@gmail.com'
    Then Account 'prueba123@gmail.com' should be created succesfully

  Scenario: Trying to register an existing account
    Given Account 'prueba123@gmail.com' is already registered
    When Trying to register 'prueba123@gmail.com'
    Then Operation should be denied due to existing account

  Scenario: Sending an email to existing account
    Given Account 'prueba123@gmail.com' and 'receiver@gmail.com' are already registered
    When Account 'prueba123@gmail.com' sends an email to 'receiver@gmail.com'
    Then 'receiver@gmail.com' has a new pending email




