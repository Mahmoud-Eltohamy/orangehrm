package com.orangehrm.StepDefinitions.Admin.UserManagement;

import com.orangehrm.PO.base.StepDefinition;
import com.orangehrm.PO.pages.Admin.UserManagement.Users.UsersPage;
import com.orangehrm.PO.pages.PIM.AddEmployee.AddEmployee;
import com.orangehrm.PO.pages.SideMenu.SideMenu;
import com.orangehrm.StepDefinitions.Hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class UserFeatures {
    @Then("I navigate to User Management in Admin menu")
    public void navigate_To_Users_Page_In_Admin_Menu() {
        SideMenu sideMenu = new SideMenu(Hooks.driver);
        UsersPage usersPage = new UsersPage(Hooks.driver);

        sideMenu.clickOnAdminLink();
        usersPage.click_On_Users_From_User_Management_Dropdown();
        usersPage.validate_System_Users_Header();
    }

    @And("I search for the same employee in User Search by username that I added in PIM {string}")
    public void search_For_Employee_In_User_Search_By_Username(String username) {
        AddEmployee addEmployee = new AddEmployee(Hooks.driver);
        UsersPage usersPage = new UsersPage(Hooks.driver);

        addEmployee.enterUsername(username);
        usersPage.click_On_Search_Button();
        usersPage.validate_User_Is_Displayed_In_Table(username);
    }

    @Then("I click on Edit option for the employee {string}")
    public void click_On_Edit_Option_For_Employee(String username) {
        UsersPage usersPage = new UsersPage(Hooks.driver);
        usersPage.click_On_Edit_Button_For_Username(username);
        usersPage.validate_Edit_User_Page();
    }

    @And("I change the password for that employee {string} {string}")
    public void change_Password_For_Employee(String changedPassword, String confirmPassword) {
        UsersPage usersPage = new UsersPage(Hooks.driver);
        AddEmployee addEmployee = new AddEmployee(Hooks.driver);

        usersPage.click_On_ChangePassword_Yes_Checkbox();
        addEmployee.enterPassword(changedPassword);
        addEmployee.enterConfirmPassword(confirmPassword);
        addEmployee.clickOnSaveButton();

        usersPage.validate_System_Users_Header();
    }

    @Then("I delete the employee and validate that the employee is removed from PIM {string}")
    public void click_On_Delete_Option_For_Employee(String username) {
        UsersPage usersPage = new UsersPage(Hooks.driver);
        SideMenu sideMenu = new SideMenu(Hooks.driver);
        StepDefinition stepDefinition = new StepDefinition(Hooks.driver);

        sideMenu.clickOnPIMLink();
        usersPage.click_On_Delete_Button_For_Username(username);
        stepDefinition.handle_Delete_Pop_Up(true);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I select the status of the employee account as {string} and save")
    public void select_the_status_of_the_employee_account_as_enabled_and_save(String status) {
        UsersPage usersPage = new UsersPage(Hooks.driver);
        AddEmployee addEmployee = new AddEmployee(Hooks.driver);

        usersPage.select_the_status_of_the_employee_account_from_dropdown(status);
        addEmployee.clickOnSaveButton();

        usersPage.validate_System_Users_Header();
    }

    @And("I click on Add Button to add a User")
    public void click_on_Add_Button_In_User_Management_Page() {
        UsersPage usersPage = new UsersPage(Hooks.driver);
        usersPage.click_on_Add_Button();
        usersPage.validate_Add_User_Title();
    }

    @Then("I enter all the details and add the user into the system through User Management {string} {string} {string} {string} {string} {string}")
    public void enter_all_details_and_add_user_into_the_system_through_User_Management(String userRole, String employeeName, String status, String username, String password, String confirmPassword) {
        UsersPage usersPage = new UsersPage(Hooks.driver);

        usersPage.selectUserRole(userRole);
        usersPage.enterEmployeeName(employeeName);
        usersPage.select_the_status_of_the_employee_account_from_dropdown(status);
        usersPage.enterUsername(username);
        usersPage.enterPassword(password);
        usersPage.enterConfirmPassword(confirmPassword);
    }

    @Then("I validate that I am on the System Users Page")
    public void validateSystemUsersPage() {
        UsersPage usersPage = new UsersPage(Hooks.driver);
        usersPage.validate_System_Users_Header();
    }

    @And("I delete all the Users from the Users List table")
    public void delete_all_users_from_users_list_table() {
        UsersPage usersPage = new UsersPage(Hooks.driver);
        usersPage.deleteAllUsersFromUsersTable();
    }
}
