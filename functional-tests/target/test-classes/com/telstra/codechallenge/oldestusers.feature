# See
# https://github.com/intuit/karate#syntax-guide
# for how to write feature scenarios
Feature: As an api user I want to retrieve some oldest users with zero followers

  Scenario: Get a 1 user with 0 followers
    Given url microserviceUrl
    And path '/oldestUsers'
    And param count = 1
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
     # The response should have an array of Users objects
    And match response == 
    """
    { 
      items : [{
        id : '#number',
        login : '#string',
        html_url : '#string'
      }]
    }
    """

  Scenario: Get 4 Oldest Git User Account
    Given url microserviceUrl
    And path '/oldestUsers'
    And param number_of_accounts = 4
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    # see https://github.com/intuit/karate#schema-validation
    # Define the required schema
    * def userSchema = { id : '#number', login : '#string', html_url : '#string' }
    # The response should have an array of 2 User objects
    And match response == 
    """
    { 
      items : '#[4] userSchema'
    }
    """