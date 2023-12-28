package org.selflearning.pagesteps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.selflearning.pageObject.android.SignInAndroidPage;

public class SignInAndroidPageSteps {
    private AndroidDriver driver;
    public SignInAndroidPage signInAndroidPage;
    public SignInAndroidPageSteps(){}
    public SignInAndroidPageSteps(AndroidDriver driver){
        this.driver =driver;
        this.signInAndroidPage = new SignInAndroidPage(driver);
    }

    @Given("User has launched the app")
    public void user_has_launched_the_app() {
        signInAndroidPage.appInstalledAndLaunched();
    }
    @When("User clicks on Eamil option")
    public void user_clicks_on_eamil_option() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User verifies Email field is displayed")
    public void user_verifies_email_field_is_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User enters the email id")
    public void user_enters_the_email_id() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User tabs on the arrow on Email field to continue with sign in")
    public void user_tabs_on_the_arrow_on_email_field_to_continue_with_sign_in() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User verifies OTP page is displayed")
    public void user_verifies_otp_page_is_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User retrieves the OTP from the email used for sigin")
    public void user_retrieves_the_otp_from_the_email_used_for_sigin() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User enters the code into the OTP field")
    public void user_enters_the_code_into_the_otp_field() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User tabs on the arrow on OTP field to continue with sig in")
    public void user_tabs_on_the_arrow_on_otp_field_to_continue_with_sig_in() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User verifies that the sign in process proceeds to next")
    public void user_verifies_that_the_sign_in_process_proceeds_to_next() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User consents to caching notification and click on Continue")
    public void user_consents_to_caching_notification_and_click_on_continue() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User verifies the caching notification modal is closed")
    public void user_verifies_the_caching_notification_modal_is_closed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User clicks on Next button on the notification modal for selecting store")
    public void user_clicks_on_next_button_on_the_notification_modal_for_selecting_store() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User verifies the store selection notification modal is closed")
    public void user_verifies_the_store_selection_notification_modal_is_closed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User clicks on Next button on the notification modal for clip coupons and deals")
    public void user_clicks_on_next_button_on_the_notification_modal_for_clip_coupons_and_deals() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User verifies the clip c&d notification modal is closed")
    public void user_verifies_the_clip_c_d_notification_modal_is_closed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("User clicks on Next button on the notification modal for member account")
    public void user_clicks_on_next_button_on_the_notification_modal_for_member_account() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User verifies the member account selection notification modal is closed")
    public void user_verifies_the_member_account_selection_notification_modal_is_closed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User verifies Home page is displayed")
    public void user_verifies_home_page_is_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
