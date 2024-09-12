package com.orangehrm.PO.pages.Admin.Job.JobTitle;

import com.orangehrm.PO.base.StepDefinition;
import com.orangehrm.PO.base.TestBase;
import com.orangehrm.PO.pages.Admin.AdminTopNavMenu.AdminTopNavMenuLocators;
import com.orangehrm.PO.pages.Login.LoginPage;
import com.orangehrm.PO.pages.SideMenu.SideMenu;
import org.openqa.selenium.*;

import java.util.List;

public class JobTitlePage extends TestBase {
    public WebDriver driver;
    public JobTitleLocators jobTitleLocators;
    public SideMenu sideMenu;
    public LoginPage loginPage;
    public StepDefinition stepDefinition;
    public AdminTopNavMenuLocators adminTopNavMenuLocators;

    public JobTitlePage(WebDriver driver) {
        this.driver = driver;
        this.jobTitleLocators = new JobTitleLocators(driver);
        sideMenu = new SideMenu(driver);
        loginPage = new LoginPage(driver);
        adminTopNavMenuLocators = new AdminTopNavMenuLocators(driver);
        this.stepDefinition = new StepDefinition(driver);
    }

    public void validate_Job_Title_Page_Header() {
        WebElement jobTitleHeader = jobTitleLocators.jobTitlesHeader;
        waitForElementToBeVisible(jobTitleHeader, 20);
        validateText(jobTitleHeader, "Job Titles", "Validated Job Title successfully", 10);
    }

    public void validate_Job_Title_Input_Text_Is_Present() {
        WebElement jobTitleInputText = jobTitleLocators.jobTitleInputHeader;
        waitForElementToBeVisible(jobTitleInputText, 10);
        validateText(jobTitleInputText, "Job Title", "Validated Job Title input header successfully", 5);
    }

    public WebElement validate_Job_Title_Input_Box_Is_Present() {
        WebElement jobTitleInput = jobTitleLocators.jobTitleInput;
        waitForElementToBeVisible(jobTitleInput, 10, "Validated Job Title Input Box successfully");
        return jobTitleInput;
    }

    public void enterJobTitle(String jobTitle) {
        WebElement jobTitleInput = jobTitleLocators.jobTitleInput;
        jobTitleInput.clear();
        waitForElementToBeClickable(jobTitleInput, 10);
        sendKeys(jobTitleInput, jobTitle, "Successfully entered " + jobTitle + " into text box", 10);
    }

    public void validate_Job_Description_Input_Text_Is_Present() {
        WebElement jobDescriptionInputHeader = jobTitleLocators.jobDescriptionInputHeader;
        waitForElementToBeVisible(jobDescriptionInputHeader, 10);
        validateText(jobDescriptionInputHeader, "Job Description",
                "Validated Job Description input header successfully", 5);
    }

    public WebElement validate_Job_Title_Description_Box_Is_Present() {
        WebElement jobTitleDescription = jobTitleLocators.jobDescriptionInput;
        waitForElementToBeVisible(jobTitleDescription, 10, "Validated Job Title description box successfully");
        return jobTitleDescription;
    }

    public void enter_Job_Description(String jobDescription) {
        WebElement jobDescriptionTextBox = validate_Job_Title_Description_Box_Is_Present();
        sendKeys(jobDescriptionTextBox, jobDescription,
                "Successfully entered " + jobDescription + " into job description text box", 10);
    }

    public void validate_Job_Specification_Input_Text_Is_Present() {
        WebElement jobSpecificationInputHeader = jobTitleLocators.jobSpecificationInputHeader;
        waitForElementToBeVisible(jobSpecificationInputHeader, 10);
        validateText(jobSpecificationInputHeader, "Job Specification",
                "Validated Job Specification input header successfully", 5);
    }

    public void validate_Job_Specification_Browse_Button_Is_Present() {
        WebElement jobSpecificationBrowseButton = jobTitleLocators.jobSpecificationBrowseButton;
        waitForElementToBeVisible(jobSpecificationBrowseButton, 10, "Job specification browse button is visible");
    }

    public void validate_Job_Specification_Hint_Text_Is_Present() {
        WebElement jobSpecificationHintText = jobTitleLocators.jobSpecificationHint;
        waitForElementToBeVisible(jobSpecificationHintText, 10);
    }

    public void validate_Note_Input_Text_Is_Present() {
        WebElement noteInputHeader = jobTitleLocators.noteInputHeader;
        waitForElementToBeVisible(noteInputHeader, 10);
        validateText(noteInputHeader, "Note", "Validated Note input header successfully", 5);
    }

    public WebElement validate_Note_Input_Box_Is_Present() {
        WebElement noteInputBox = jobTitleLocators.addNoteTextBox;
        waitForElementToBeVisible(noteInputBox, 10, "Validated add note input box successfully");
        return noteInputBox;
    }

    public void enterNote(String note) {
        sendKeys(jobTitleLocators.addNoteTextBox, note, "Entered note into note input box", 10);
    }

    public void validate_Cancel_Button_Is_Present() {
        WebElement cancelButton = jobTitleLocators.cancelButton;
        waitForElementToBeClickable(cancelButton, 10, "Validated cancel button successfully");
    }

    public WebElement validate_Save_Button_Is_Present() {
        WebElement saveButton = jobTitleLocators.saveButton;
        waitForElementToBeClickable(saveButton, 10, "Validated Save button successfully");
        return saveButton;
    }

    public void click_On_Save_Button() {
        WebElement saveButton = validate_Save_Button_Is_Present();
        clickElement(saveButton, "Save button clicked successfully", true, 30);
    }

    public void validate_Required_Error_Message_Is_Present() {
        WebElement requiredText = jobTitleLocators.jobTitleRequiredErrorMessage;
        waitForElementToBeVisible(requiredText, 10);
        String requiredTextContent = requiredText.getText();
        System.out.println("Required Text Displayed: " + requiredTextContent);
        validateText(requiredText, "Required", "Required Text Validated successfully", 30);
    }

    public WebElement validate_Add_Button() {
        WebElement addButton = jobTitleLocators.jobTitleAddButton;
        waitForElementToBeClickable(addButton, 10, "Add button is clickable");
        return addButton;
    }

    public void click_on_Add_Button() {
        clickElement(jobTitleLocators.jobTitleAddButton, "Clicked on Add button in job titles page", true, 5);
    }

    public void validate_Add_Job_Titles_Page_Header() {
        WebElement requiredText = jobTitleLocators.addJobTitleHeader;
        waitForElementToBeVisible(requiredText, 15);
        validateText(requiredText, "Add Job Title", "Validated Add Job Title header successfully", 5);
    }

    public WebElement validate_Job_Title_Is_Present_In_JobTitle_Table(String jobTitle) {
        waitForElementToBeVisible(jobTitleLocators.jobTitlesTable, 50, "Job Titles page is visible");
        WebElement requiredJobTitle = jobTitleLocators.allJobTitles
                .findElement(By.xpath("//div[text()='" + jobTitle + "']"));
        waitForElementToBeVisible(requiredJobTitle, 20, "Validated " + requiredJobTitle + " in the Job Titles Table");
        return requiredJobTitle;
    }

    public void delete_Job_Title_From_Table(String jobTitle) {
        WebElement requiredJobTitle = validate_Job_Title_Is_Present_In_JobTitle_Table(jobTitle);
        WebElement trashIconForRequiredJobTitle = requiredJobTitle.findElement(
                By.xpath("//ancestor::div[@class='oxd-table-card']//button/i[@class='oxd-icon bi-trash']"));
        clickElement(trashIconForRequiredJobTitle, "Successfully deleted " + jobTitle, true, 30);
        stepDefinition.handle_Delete_Pop_Up(true);
        waitForElementToBeClickable(requiredJobTitle, 20);
    }

    public void deleteAllJobTitlesFromJobTitlePage() {
        while (isElementPresent(jobTitleLocators.allJobTitles)) {
            clickElement(jobTitleLocators.selectAllJobTitlesCheckbox, "Clicked on select all Job Title checkbox", true,
                    30);
            clickElement(jobTitleLocators.deleteSelectedButton, "Clicked on delete selected button", true, 30);
            stepDefinition.handle_Delete_Pop_Up(true);

            waitForElementToBeVisible(jobTitleLocators.jobTitlesTableHeaderRow, 30, "All Job Titles deleted");
        }
        System.out.println("No jobs present now");
    }

    public void click_On_Trash_Icon_In_Job_Titles_Table(String jobTitle) {
        List<WebElement> jobTitleRows = jobTitleLocators.jobTitlesTableRows;
        for (WebElement currentRow : jobTitleRows) {
            WebElement jobTitleElement = currentRow.findElement(By.xpath("//div[text()='" + jobTitle + "']"));
            if (getTextFromElement(jobTitleElement, 10).equalsIgnoreCase(jobTitle)) {
                logInfo("Successfully validated job title in the table", true);
                WebElement trashIcon = currentRow.findElement(By.xpath("//i[@class='oxd-icon bi-trash']"));
                waitForElementToBeVisible(trashIcon, 10, "Delete icon is visible");
                clickElement(trashIcon, "Trash Icon for the " + jobTitle + " is clicked successfully", true, 10);
                break;
            }
        }
    }

    public void click_On_Edit_Icon_In_Job_Titles_Table(String jobTitle) {
        WebElement editIconForJobTitle = driver
                .findElement(By.xpath("//div[@class='oxd-table-body']//div[@role='row' and .//div[text()='" + jobTitle
                        + "']]//i[@class='oxd-icon bi-pencil-fill']"));
        clickElement(editIconForJobTitle, "Edit Icon for the " + jobTitle + " is clicked successfully", true, 10);
    }

    public void validateJobTitleAlreadyExistsErrorMessage(String fieldName) {
        WebElement jobTitleRequiredErrorMessage = driver
                .findElement(By.xpath("//div[@class='oxd-form-row' and .//label[text()='" + fieldName
                        + "']]//span[text()='Already exists']"));
        waitForElementToBeVisible(jobTitleRequiredErrorMessage, 10,
                "Already exists error message in job title field is displayed correctly");
    }

    public void validate_error_message_displayed_while_entering_more_than_400_characters(String fieldName) {
        WebElement errorElement = jobTitleLocators.getMaximumCharactersErrorMessageXpath(fieldName);
        waitForElementToBeVisible(errorElement, 10,
                "Error message of maximum characters is displayed in the job description field");
        validateText(errorElement, "Should not exceed 400 characters",
                "Successfully validated the content of the error message", 10);
    }

    public void validate_No_Records_Found_Text_Displayed() {
        validateText(jobTitleLocators.noRecordsFoundText, "No Records Found",
                "Validated No Records Found text successfully", 30);
    }

    public void validate_No_Records_Found_In_Table() {
        List<WebElement> recordsList = jobTitleLocators.jobTitlesTableRows;
        if (recordsList.size() == 0) {
            logPass("No Records found in the table", true);
        } else {
            logFail("Records are found in the table", true);
        }
    }
}
