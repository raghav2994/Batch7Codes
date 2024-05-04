Feature: This feature will be testing behaviour of login page

  Background:
    Given User is on Login Page

#  Scenario: Check Login Success
#    When Fill valid credential
#    And User clicks on Submit Button
#    Then Verify user is successfully loggedIn
#
#
#  Scenario: Check Login Unsuccess
#    When Fill Invalid credential
#    And User clicks on Submit Button
#    Then Verify user is not successfully loggedIn

    Scenario Outline: Check Login screen
#      Given User is on Login Page
      When Fill credentials "<Username>" and "<Password>"
      Then User is loggedIn

      Examples:
        | Username | Password |
        | Raghav   | Pass     |
        | Raghav1  | Pass1    |

  Scenario: Extra TestCase
    When Fill credentials "Rajiv" and "Bhatia"
    Then User is loggedIn
