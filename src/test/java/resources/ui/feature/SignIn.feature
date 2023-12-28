@yourTag
Feature: Sign in to Safeway Android app
    Background: User the app is installed and the device is
      Given User has launched the app
      Scenario: An existing user wants to sign in to the app with email
        When User clicks on Eamil option
        Then User verifies Email field is displayed
        When User enters the email id
        And User tabs on the arrow on Email field to continue with sign in
        Then User verifies OTP page is displayed
        When User retrieves the OTP from the email used for sigin
        And User enters the code into the OTP field
        And User tabs on the arrow on OTP field to continue with sig in
        Then User verifies that the sign in process proceeds to next
        When User consents to caching notification and click on Continue
        Then User verifies the caching notification modal is closed
        When User clicks on Next button on the notification modal for selecting store
        Then User verifies the store selection notification modal is closed
        When User clicks on Next button on the notification modal for clip coupons and deals
        Then User verifies the clip c&d notification modal is closed
        When User clicks on Next button on the notification modal for member account
        Then User verifies the member account selection notification modal is closed
        And User verifies Home page is displayed

