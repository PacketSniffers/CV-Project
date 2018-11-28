 Feature: To have a fully functioning log in system 
  
  Scenario Outline: Sucesfully log in as a user
    Given that you are on the Log In Page
     When the correct  user details "<Email>" username, "<Password>" password are entered on the Log In Page
     And the details are submitted on the Log In Page
     Then the "<Email>" email should be displayed on the Profile Page

  Examples:
  | Email | Password |
  | danhiggo23@gmail.com | Password123 |
  | bradley.hudson-grant@academytrainee.com | Password123 |
  | dan.higgins@academytrainee.com | Password123 |
  
  Scenario Outline: Prevent a incorrect log in
    Given that you are on the Log In Page
     When the incorrect correct user details "<Email>" username, "<Password>" password are entered on the Log In Page
     And the details are submitted on the Log In Page
     Then the user should be denied access

  Examples:
  | Email | Password |
  | test@gmail.com | Password123 |
  | notanaccount@gmail.com | Password123 |
  

