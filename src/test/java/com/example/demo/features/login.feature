Feature: This is to test login feature
  Scenario: User logs into home page
    Given User lands on login page
    When User enters "admin" and "admin"
    Then User should login to home page