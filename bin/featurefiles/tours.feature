Feature: Login to the Mercury Tours Site with admin privilages

  Scenario: 
    Given user is on Mercury tours site
    And check the url of the browser being displayed with the text 'sign-in here'
    And The user is registered with username 'test' and password 'test'
    When the User logins with username 'test' and password 'test'
    And Clicks on the login button
    Then the message shows 'Flight Details'

  Scenario: 
    Given user is on Mercury tours site
    And check the url of the browser being displayed with the text 'sign-in here'
    And The user is registered with username 'Ashfaq' and password 'Imran'
    When the User logins with username 'Ashfaq' and password 'Imran'
    And Clicks on the login button
    Then it should be redirected to registration page and should give a message 'registration form'
