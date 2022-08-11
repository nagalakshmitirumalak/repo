Feature: Application Login

Scenario: Login with valid credentials
Given Open any Broswer
And Navigate to Login Page
When User enters username as "arun.selenium@gmail.com" and password as "Second@123" into the fields
And User clicks on Login button
Then verify user is able to successfully