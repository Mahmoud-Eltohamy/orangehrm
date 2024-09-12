package com.orangehrm.PO.base;

import com.orangehrm.PO.pages.UserMenu.UserMenu;
import com.orangehrm.StepDefinitions.Hooks.Hooks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

/**
 * This class contains all the step definitions for the test cases.
 */
public class StepDefinition extends TestBase {
    public WebDriver driver;
    public Locators locators;
    public static String employeeID;
    public UserMenu userMenu;

    /**
     * Constructor for the StepDefinition class.
     *
     * @param driver The WebDriver instance.
     */
    public StepDefinition(WebDriver driver) {
        this.driver = driver;
        this.locators = new Locators(driver);
        this.userMenu = new UserMenu(driver);
    }

    /**
     * This method clicks on the save button.
     */
    public void clickOnSaveButton() {
        clickElement(locators.saveButton, "Clicked on save button", true, 20);
    }

    /**
     * This method clicks on the cancel button.
     */
    public void clickOnCancelButton() {
        clickElement(locators.cancelButton, "Clicked on cancel button", true, 20);
    }

    /**
     * This method handles the delete pop up and clicks on the delete button if clickOnDelete is true.
     *
     * @param clickOnDelete Whether to click on the delete button or not.
     */
    public void handle_Delete_Pop_Up(boolean clickOnDelete) {
        validateText(locators.deleteJobTitlePopUpHeader, "Are you Sure?",
                "Delete Job Title pop up header validated successfully", 10);
        validateText(locators.deleteJobTitlePopUpBody,
                "The selected record will be permanently deleted. Are you sure you want to continue?",
                "Delete Job Title pop up message body validated successfully", 10);
        if (clickOnDelete) {
            click_On_Yes_Delete_Button();
        } else {
            click_No_Cancel_Button();
        }
    }

    /**
     * This method clicks on the yes delete button.
     */
    private void click_On_Yes_Delete_Button() {
        WebElement yesDeleteButton = locators.yesDeleteJobTitleButton;
        waitForElementToBeVisible(yesDeleteButton, 10, "Yes Delete button is displayed successfully");
        clickElement(yesDeleteButton, "Yes, Delete button is clicked successfully", true, 10);
    }

    /**
     * This method clicks on the no cancel button.
     */
    private void click_No_Cancel_Button() {
        WebElement noCancelButton = locators.noCancelJobTitleButton;
        waitForElementToBeVisible(noCancelButton, 10, "No Cancel button is displayed successfully");
        clickElement(noCancelButton, "No Cancel button is clicked successfully", true, 10);
    }

    /**
     * This method clicks on the add button.
     */
    public void click_On_Add_Button() {
        clickElement(locators.addButton, "Clicked on Add Button", true, 30);
    }

    /**
     * This method validates the already exists error message.
     */
    public void validate_Already_Exists_Error_Message() {
        waitForElementToBeVisible(locators.alreadyExistsMessage, 20, "Validated already exists message successfully");
    }

    /**
     * This method deletes all the pay grades from the pay grades page.
     */
    public void deleteAllPayGradesFromPayGradesPage() {
        while (isElementPresent(locators.allPayGrades)) {
            clickElement(locators.selectAllPayGradesCheckbox, "Clicked on select all Pay Grades checkbox", true, 30);
            clickElement(locators.deleteSelectedButton, "Clicked on delete selected button", true, 30);
            handle_Delete_Pop_Up(true);

            waitForElementToBeVisible(locators.payGradesTableHeaderRow, 30, "All Data deleted");
        }
        System.out.println("No data present now");
    }

    /**
     * This method sets the employee ID.
     *
     * @param employeeId The employee ID.
     */
    public static void setEmployeeID(String employeeId) {
        employeeID = employeeId;
    }

    /**
     * This method uploads a file.
     */
    public void uploadFile() {
        WebElement browseButton = Hooks.driver.findElement(By.xpath("//div[@class='oxd-file-button']"));
        clickElement(browseButton, "Browse button clicked", true, 10);
        try {
            Robot robot = new Robot();
            robot.delay(3000);

            StringSelection selection = new StringSelection("D:\\Project\\File Upload.pdf");
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_V);
            robot.delay(1000);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.delay(1000);
            robot.keyRelease(KeyEvent.VK_V);
            robot.delay(1000);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(1000);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(1000);

        } catch (AWTException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='oxd-file-loading']")));
    }

    /**
     * This method validates the table header is present.
     */
    public void validate_Table_Header_Is_Present() {
        waitForElementToBeVisible(locators.tableHeader, 30, "Table header is present");
    }

    /**
     * This method validates the table header checkbox is present.
     */
    public void validate_Table_Header_Checkbox_Is_Present() {
        waitForElementToBeVisible(locators.tableHeaderCheckbox, 30, "Table header Checkbox is present");
    }

    /**
     * This method validates the error message.
     *
     * @param errorMessage The error message.
     */
    public void validate_Error_Message(String errorMessage) {
        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated((By) locators.errorMessage));
        waitForElementToBeVisible(locators.errorMessage, 30);
        validateText(locators.errorMessage, errorMessage, "Error message validated successfully", 40);
    }

    /**
     * This method refreshes the page.
     */
    public void refreshPage() {
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(Hooks.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='oxd-userdropdown-tab']")));
        userMenu.validateUserMenuIsPresent();
    }
}

