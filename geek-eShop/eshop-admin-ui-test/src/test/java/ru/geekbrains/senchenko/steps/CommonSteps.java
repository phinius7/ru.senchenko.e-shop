package ru.geekbrains.senchenko.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekbrains.senchenko.DriverInitializer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CommonSteps {

    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void iOpenWebBrowser() {
        webDriver = DriverInitializer.getWebDriver();
    }

    @When("^I navigate to login page$")
    public void iNavigateToLoginPage() {
        webDriver.get(DriverInitializer.getProperty("login.url"));
    }

    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAndPassword(String username, String password) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("inp-username"));
        webElement.sendKeys(username);
        Thread.sleep(2000);
        webElement = webDriver.findElement(By.id("inp-password"));
        webElement.sendKeys(password);
        Thread.sleep(2000);
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() {
        WebElement webElement = webDriver.findElement(By.id("btn-login"));
        webElement.click();
    }

    @Then("^name should be \"([^\"]*)\"$")
    public void nameShouldBe(String name) {
        WebElement webElement = webDriver.findElement(By.id("dd_user"));
        assertThat(webElement.getText()).isEqualTo(name);
    }

    @When("^open dropdown menu$")
    public void openDropdownMenu() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.id("logged-in-username"));
        Thread.sleep(2000);
        webElement.click();
        Thread.sleep(3000);
    }

    @When("^click logout button$")
    public void clickLogoutButton() {
        WebElement webElement = webDriver.findElement(By.id("link-logout"));
        webElement.click();
    }

    @Then("^user logged out$")
    public void userLoggedOut() {
        webDriver.findElement(By.id("inp-username"));
        webDriver.findElement(By.id("inp-password"));
    }

    @When("^I click on Categories$")
    public void iClickOnCategories() throws Throwable {
        Thread.sleep(2000);
        WebElement webElement = webDriver.findElement(By.id("inp-categories"));
        webElement.click();
    }

    @Then("^page should be \"([^\"]*)\"$")
    public void shouldBeCategoriesPage(String categories_page) {
        WebElement webElement = webDriver.findElement(By.id("category-page"));
        assertThat(webElement.getText()).isEqualTo(categories_page);
    }

    @When("^I click on create button$")
    public void iClickOnCreateButton() throws Throwable {
        Thread.sleep(2000);
        WebElement webElement = webDriver.findElement(By.id("create-btn"));
        webElement.click();
    }

    @Then("^will be categories \"([^\"]*)\"$")
    public void willBeCategoriesAddForm(String add_form_page) {
        WebElement webElement = webDriver.findElement(By.id("add-form-page"));
        assertThat(webElement.getText()).isEqualTo(add_form_page);
    }

    @When("^I provide title as \"([^\"]*)\" and code as \"([^\"]*)\"$")
    public void iProvideCodeAndCategoryName(String title, String code) throws Throwable {
        Thread.sleep(2000);
        WebElement  webElement = webDriver.findElement(By.id("ipn-title"));
        webElement.sendKeys(title);
        Thread.sleep(2000);
        webElement = webDriver.findElement(By.id("code"));
        webElement.sendKeys(code);
        Thread.sleep(2000);


    }

    @And("^I click on add button$")
    public void iClickOnAddButton() {
        WebElement webElement = webDriver.findElement(By.id("add-btn"));
        webElement.click();
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }
}
