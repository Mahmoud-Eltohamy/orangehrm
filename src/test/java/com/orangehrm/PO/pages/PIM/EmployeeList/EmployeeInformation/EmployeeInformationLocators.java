package com.orangehrm.PO.pages.PIM.EmployeeList.EmployeeInformation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeeInformationLocators {
    public EmployeeInformationLocators(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//label[text()='Employee Name']/parent::div/following-sibling::div//child::input")
    public WebElement employeeNameInputBox;

    @FindBy(xpath = "//a[@class='oxd-topbar-body-nav-tab-item' and text()='Employee List']")
    public WebElement employeeListNavButton;

    @FindBy(xpath = "//h5[text()='Employee Information']")
    public WebElement employeeInformationTitle;

    @FindBy(xpath = "//div[@class='orangehrm-container']//div[@class='oxd-table-card']/div[@role='row']")
    public List<WebElement> searchUsersList;

    @FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/span[@class='oxd-text oxd-text--span']")
    public WebElement recordsFoundText;

    @FindBy(xpath = "//div[@role='row' and .//ancestor::div[@class='oxd-table-body']]")
    public List<WebElement> allEmployeesListInTable;

    @FindBy(xpath = "//div[@class='oxd-table-header']//div[@class='oxd-checkbox-wrapper']")
    public WebElement selectAllEmployeesCheckbox;

    @FindBy(xpath = "//button[@type='button' and text()=' Delete Selected ']")
    public WebElement deleteSelectedButton;
}
