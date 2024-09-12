@login @regression
Feature: Login End To End Testing

  Background:
    Given I am on the OrangeHRM login page
    And I login with valid Admin credentials
    Then I should be on the "dashboard" page
    When I navigate to the PIM module

  @smoke @sanity
  Scenario: Delete all employees from PIM
    And I delete all the employees from the employee list table
    Then I validate there are no employees present in PIM
    When I navigate to User Management in Admin menu
    And I delete all the Users from the Users List table

  @smoke
  Scenario Outline: Admin Account Creation

# Add an employee without the login credentials
    When I click on the Add Employee button
    And I enter all the employee details without login details: "<First Name>" "<Middle Name>" "<Last Name>" "<Employee ID>"
    And I save the details
    Then I validate that I am on the Personal Details Page

    ## Add User with login details
    And I navigate to User Management in Admin menu
    And I click on Add Button to add a User
    Then I enter all the details and add the user into the system through User Management "<User Role>" "<Employee Name>" "<Status>" "<Username>" "<Password>" "<Confirm Password>"
    And I save the details
    Then I validate that I am on the System Users Page
    And I logout from the application

# Login with the Admin user that was created above
    When I login as the same employee that I added in PIM as an Admin "<Username>" "<Password>"
    And I am on the dashboard page
    Then I validate that the user should be able to access all the features of an Admin User
    And I logout from the application

    Examples:
      | First Name | Middle Name | Last Name | Employee ID | Username | Password      | Confirm Password | Employee Name    | User Role | Status  |
      | mahmoud      | mohammed       | eltohamy      | 1001        | mahmoud123 | Password@1234 | Password@1234    | mahmoud mohammed eltohamy | Admin     | Enabled |

  @smoke @sanity
  Scenario: Delete all employees from PIM
    And I delete all the employees from the employee list table
    Then I validate there are no employees present in PIM
    When I navigate to User Management in Admin menu
    And I delete all the Users from the Users List table