package testcases.smokesuite;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;
import utilities.TestUtil;

public class RR5753ClientIssue1Test extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void rR5753ClientIssue1Test(Hashtable<String, String> data) throws IOException, InterruptedException {

		execution(data, "rR5753ClientIssue1Test");

		// refresh the page
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.navigate().refresh();

		// INCIDENT REPORTS SHOULD NOT SPIN CONTINUOUSLY WHEN TRYING TO SAVE A PERSON
		// INVOLVED RECORD WITHOUT NATURE OF INJURY OR CAUSE - RR-5626
		title("INCIDENT REPORTS SHOULD NOT SPIN CONTINUOUSLY WHEN TRYING TO SAVE A PERSON INVOLVED RECORD WITHOUT NATURE OF INJURY OR CAUSE - RR-5626");

		// LOGIN WITH ADMIN USER
		title("LOGIN WITH ADMIN USER");

		try {
			// wait for the element
			explicitWaitClickable("signinbtn_BTNTEXT");

			// Enter the username
			type("usernametxt_CSS", data.get("username_1"));

			// Enter the password
			type("passwordtxt_CSS", data.get("password_1"));

			// Clicking on the "Sign In" button
			click("signinbtn_BTNTEXT");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// UPDATE THE SYSTEM COMPANY
			updateCompany(data);

		} catch (Throwable t) {
			verificationFailed();
		}

		// VALIDATE THE INCIDENT REPORTS IS NOT DISPLAYING SPIN CONTINUOUSLY WHEN TRYING
		// TO SAVE A PERSON INVOLVED RECORD WITHOUT NATURE OF INJURY OR CAUSE
		title("VALIDATE THE INCIDENT REPORTS IS NOT DISPLAYING SPIN CONTINUOUSLY WHEN TRYING TO SAVE A PERSON INVOLVED RECORD WITHOUT NATURE OF INJURY OR CAUSE");

		try {

			// enter the property name in the search field
			type("envreports_propertylist_filtertxt_CSS", data.get("property_2"));

			// click on the OHS icon from the property list page
			click("ohsicon_CSS");

			// wait for the element
			Thread.sleep(5000);

			// click on the burger menu
			click("menubtn_CSS");

			// wait for the element
			Thread.sleep(5000);

			// click on the incident reports
			click("questionnaire_option_ohstab_incidentreports_XPATH");

			// ADD DETAILS IN THE INCIDENT REPORT TAB
			title("ADD DETAILS IN THE INCIDENT REPORT TAB");

			// click on the add button
			click("ohs_incident_addbtn_CSS");

			// wait for the element
			Thread.sleep(7000);

			// enter the incident time - hours

			type("ohs_incident_hourstxt_CSS", "11");

			// enter the incident time - minute

			type("ohs_incident_minutestxt_CSS", "20");

			// scroll down the screen

			scrollByPixel(500);

			// enter the short description
			type("ohs_incident_shortdescriptiontxt_CSS", data.get("describe_incident"));

			// click on the add event type button
			click("ohs_incident_addeventtypebtn_CSS");

			// click on the event type dropdown
			select("ohs_incident_eventtypedd_CSS", data.get("event_type"));

			// click on the add button of the add event type record model
			click("ohs_incident_eventty_addbtn_CSS");

		} catch (Throwable t) {
			verificationFailed();
		}

		try {

			// click on the next button
			click("ohs_incident_nextbtn_CSS");

			// synchronization
			explicitWait("ohs_incident_nextbtn_CSS");

			test.log(LogStatus.INFO, "Navigate to the Details, Cause Tab..");
			Reporter.log("Navigate to the Details, Cause Tab..");
			successMessage("Navigate to the Details, Cause Tab..");

			// ADD DETAILS IN THE DETAILS, CAUSES TAB
			title("ADD DETAILS IN THE DETAILS, CAUSES TAB");

			// Enter the data in the description incident field
			type("ohs_incident_descriptionincidenttxt_CSS", data.get("describe_incident"));

			// Enter the data in the Incident Cause field
			type("ohs_incident_incidentcausetxt_CSS", data.get("incident_cause"));

			// scroll down the screen

			scrollByPixel(400);

			// Enter the data in the Actions Taken field
			type("ohs_incident_actionstakentxt_CSS", data.get("actions_taken"));

		} catch (Throwable t) {
			verificationFailed();
		}

		try {

			// click on the next button
			click("ohs_incident_nextbtn_CSS");

			// synchronization
			explicitWait("ohs_incident_personsinvolved_addbtn_CSS");

			test.log(LogStatus.INFO, "Navigate to the Persons Involved Tab.");
			Reporter.log("Navigate to the Persons Involved Tab.");
			successMessage("Navigate to the Persons Involved Tab.");

			// ADD DETAILS IN THE PERSONS INVOLVED TAB
			title("ADD DETAILS IN THE PERSONS INVOLVED TAB");

			// wait for the element
			Thread.sleep(7000);

			try {
				// click on the add button
				click("ohs_incident_personsinvolved_addbtn_CSS");

				// select an employee option from the type dropdown
				select("ohs_incident_typedd_CSS", data.get("person_type"));

				// enter data in the Insurance Company Name field
				type("ohs_incident_insurancecompanynametxt_CSS", data.get("insurance_company_name"));

				// enter data in the Insurance Policy Number field
				type("ohs_incident_insurancepolicynumbertxt_CSS", data.get("insurance_policy_number"));

				// click on the Is this person injured? checkbox
				click("ohs_incident_personinjuredckbx_CSS");

				// scroll down the screen

				scrollByPixel(600);

				// enter data in the first name field
				type("ohs_incident_personfirstnametxt_CSS", data.get("person_first_name"));

				// enter data in the last name field
				type("ohs_incident_personlastnametxt_CSS", data.get("person_last_name"));

				// enter data in the job title
				type("ohs_incident_personjobtitletxt_CSS", data.get("job_title"));

				// enter data in the email field
				type("ohs_incident_personemailtxt_CSS", data.get("person_email"));

				// enter data in the phone number field
				type("ohs_incident_personphonenumbertxt_CSS", "9876543210");

				// enter data in the manager name field
				type("ohs_incident_managernametxt_CSS", data.get("manager_name"));

				// enter data in the manager email field
				type("ohs_incident_manageremailtxt_CSS", data.get("manager_email"));

				// click on the save button
				click("ohs_incident_personalinvolvedtab_savebtn_XPATH");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				switchVerification("ohs_incident_invalidinputvalidation_XPATH",
						"Invalid input. Please select at least one nature of injury field",
						"The respective validaiton message is not displayed.");

				// click on the toaster close button
				click("closetoastmsg_CSS");

			} catch (Throwable t) {
				verificationFailed();

			}

			// ADD DETAILS IN THE NATURE OF INJURY TAB
			title("ADD DETAILS IN THE NATURE OF INJURY TAB");

			try {
				// scroll up the screen

				scrollByPixel(-600);

				// click on the nature of injury tab
				click("ohs_incident_natureofinjurytab_CSS");

				// wait for the element
				Thread.sleep(7000);

				// click on the abdomen checkbox
				click("ohs_incident_abdomenckbx_CSS");

				// click on the left hand checkbox
				click("ohs_incident_lefthandckbx_CSS");

				// click on the psychological checkbox
				click("ohs_incident_psychologicalckbx_CSS");

				// click on the right shoulder checkbox
				click("ohs_incident_rightshoulderckbx_CSS");

			} catch (Throwable t) {
				verificationFailed();

			}

			// ADD DETAILS IN THE CAUSE OF INJURY TAB
			title("ADD DETAILS IN THE CAUSE OF INJURY TAB");

			try {
				// click on the causes of injury tab
				click("ohs_incident_causeofinjurytab_CSS");

				// wait for the element
				Thread.sleep(7000);

				try {

					// wait for the element
					explicitWaitClickable("ohs_incident_causeofinjury_animaloption_XPATH");

					// click on the first option of the causes of injury
					click("ohs_incident_causeofinjury_animaloption_XPATH");

					// scroll down the screen

					scrollByPixel(400);

					// click on the save button
					click("ohs_incident_causesofinjurytab_savebtn_CSS");

					// verify added person involved details are displayed or not
					switchVerification("ohs_incident_addedpersoninvolved_XPATH", "Employee",
							"The Employee is not displayed.");

				} catch (Throwable t) {

					verificationFailedMessage("The checklists are not displayed in classic mode.");

					// click on the cancel button
					click("ohs_incident_causesofinjurytab_cancelbtn_XPATH");

				}

			} catch (Throwable t) {
				verificationFailed();
			}

		} catch (Throwable t) {
			verificationFailed();
		}

		try {

			// click on the next button
			click("ohs_incident_nextbtn_CSS");

			// synchronization
			explicitWait("ohs_incident_nextbtn_CSS");

			consoleMessage("Navigate to the Witnesses Tab.");

			// click on the next button
			click("ohs_incident_nextbtn_CSS");

			explicitWait("ohs_incident_nextbtn_CSS");

			consoleMessage("Navigate to the emergency services Tab.");

			// click on the next button
			click("ohs_incident_nextbtn_CSS");

			// synchronization
			explicitWait("ohs_incident_nextbtn_CSS");

			consoleMessage("Navigate to the property damage Tab.");

			// click on the next button
			click("ohs_incident_nextbtn_CSS");

			// synchronization
			explicitWait("ohs_incident_nextbtn_CSS");

			consoleMessage("Navigate to the insurance Tab.");

			// click on the next button
			click("ohs_incident_nextbtn_CSS");

			// synchronization
			explicitWait("ohs_incident_updaterecordbtn_CSS");

			consoleMessage("Navigate to the attachments Tab.");

			// wait for the element
			Thread.sleep(7000);

			// click on the update record button
			click("ohs_incident_updaterecordbtn_CSS");

		} catch (Throwable t) {
			verificationFailed();
		}

		try {
			// wait for the element
			explicitWaitClickable("ohs_incident_search_clearbtn_XPATH");

			// click on the clear button
			click("ohs_incident_search_clearbtn_XPATH");

			// enter the newly created incident report in the search field
			type("ohs_incident_searchtxt_CSS", data.get("describe_incident"));

			// click on the search button
			click("ohs_incident_search_searchbtn_CSS");

			// verification of the incident report is added or not
			switchVerification("ohs_incident_addedincidentreport_clientissue1_XPATH",
					"Clientissue1 Describe Incident Text Testing Purpose",
					"The Clientissue1 Describe Incident Text Testing Purpose is not displayed successfully.");

		} catch (Throwable t) {
			verificationFailed();
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_hide_CSS");

		// synchronization
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		// DELETE DETAILS IN THE INCIDENT REPORT
		title("DELETE DETAILS IN THE INCIDENT REPORT");

		try {

			// enter the property name in the search field
			type("envreports_propertylist_filtertxt_CSS", data.get("property_2"));

			// click on the OHS icon from the property list page
			click("ohsicon_CSS");

			// wait for the element
			Thread.sleep(5000);

			// click on the burger menu
			click("menubtn_CSS");

			// wait for the element
			Thread.sleep(5000);

			// click on the incident reports
			click("questionnaire_option_ohstab_incidentreports_XPATH");

			// wait for the element
			explicitWaitClickable("ohs_incident_search_clearbtn_XPATH");

			// click on the clear button
			click("ohs_incident_search_clearbtn_XPATH");

			// enter the newly created incident report in the search field
			type("ohs_incident_searchtxt_CSS", data.get("describe_incident"));

			// click on the search button
			click("ohs_incident_search_searchbtn_CSS");

			// click on the incident report
			click("ohs_incident_addedincidentreport_clientissue1_XPATH");

			// wait for the element
			explicitWaitClickable("ohs_incident_deletebtn_CSS");

			// click on the delete button
			click("ohs_incident_deletebtn_CSS");

			// wait for the element
			explicitWaitClickable("ohs_incident_confirmdeletebtn_CSS");

			// click on the delete button of confirmation popup
			click("ohs_incident_confirmdeletebtn_CSS");

			// wait for the element
			explicitWaitClickable("ohs_incident_search_clearbtn_XPATH");

			// click on the clear button
			click("ohs_incident_search_clearbtn_XPATH");

			// enter the newly created incident report in the search field

			type("ohs_incident_searchtxt_CSS", data.get("describe_incident"));

			// click on the search button
			click("ohs_incident_search_searchbtn_CSS");

			// verify details are deleted or not
			deleteVerification("ohs_incident_addedincidentreport_clientissue1_XPATH",
					"Clientissue1 Describe Incident Text Testing Purpose");

		} catch (Throwable t) {
			verificationFailed();
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_hide_CSS");

		// synchronization
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		// SURVEYS DASHBOARD SHOULD DISPLAY COMPLIANCE DATA - RR-5620
		title("SURVEYS DASHBOARD SHOULD DISPLAY COMPLIANCE DATA - RR-5620");

		// select the survey option from the dashboard dropdown
		select("ssc_environmental_dashboarddd_CSS", data.get("surveydd_dashboard"));

		// type survey name in the search field
		type("surveydashboard_compliance_searchtxt_XPATH", data.get("survey_name"));

		// wait for the element
		Thread.sleep(3000);

		// click on the action icon
		click("surveydashboard_compliance_actionicon_XPATH");

		// click on the summary statistics option
		click("surveydashboard_compliance_summarystatisticsoption_XPATH");

		// scroll down the screen
		scrollTillElement("surveydashboard_compliance_completenesstitle_XPATH");

		// wait for the element
		Thread.sleep(5000);

		// verify count of the Surveys have not started is displayed or not
		try {

			boolean completeness1 = driver
					.findElement(By.xpath(OR.getProperty("surveydashboard_compliance_completeness1_XPATH")))
					.isDisplayed();

			consoleMessage("completeness1::::::::::::::::::" + completeness1);

			if (completeness1 == true) {
				successMessage("THE COUNT OF THE SURVEYS HAVE NOT STARTED IS DISPLAYED SUCCESSFULLY.");
			} else {

				verificationFailedMessage("THE COUNT OF THE SURVEYS HAVE NOT STARTED IS NOT DISPLAYED.");
			}
		} catch (Throwable t) {
			verificationFailedMessage("THE COUNT OF THE SURVEYS HAVE NOT STARTED IS NOT DISPLAYED.");
		}

		// verify count of the Surveys in progress is displayed or not
		try {

			boolean completeness2 = driver
					.findElement(By.xpath(OR.getProperty("surveydashboard_compliance_completeness2_XPATH")))
					.isDisplayed();

			if (completeness2 == true) {
				successMessage("THE COUNT OF THE SURVEYS IN PROGRESS IS DISPLAYED SUCCESSFULLY.");
			} else {

				verificationFailedMessage("THE COUNT OF THE SURVEYS IN PROGRESS IS NOT DISPLAYED.");
			}
		} catch (Throwable t) {
			verificationFailedMessage("THE COUNT OF THE SURVEYS IN PROGRESS IS NOT DISPLAYED.");
		}

		// verify count of the Surveys have completed is displayed or not
		try {

			boolean completeness3 = driver
					.findElement(By.xpath(OR.getProperty("surveydashboard_compliance_completeness3_XPATH")))
					.isDisplayed();

			if (completeness3 == true) {
				successMessage("THE COUNT OF THE SURVEYS HAVE COMPLETED IS DISPLAYED SUCCESSFULLY.");
			} else {

				verificationFailedMessage("THE COUNT OF THE SURVEYS HAVE COMPLETED IS NOT DISPLAYED.");
			}
		} catch (Throwable t) {
			verificationFailedMessage("THE COUNT OF THE SURVEYS HAVE COMPLETED IS NOT DISPLAYED.");
		}

		// verify count of the Compliant Sites is displayed or not
		try {

			boolean compliance1 = driver
					.findElement(By.xpath(OR.getProperty("surveydashboard_compliance_compliance1_XPATH")))
					.isDisplayed();

			if (compliance1 == true) {
				successMessage("THE COUNT OF THE COMPLIANT SITES IS DISPLAYED SUCCESSFULLY.");
			} else {

				verificationFailedMessage("THE COUNT OF THE COMPLIANT SITES IS NOT DISPLAYED.");
			}
		} catch (Throwable t) {
			verificationFailedMessage("THE COUNT OF THE COMPLIANT SITES IS NOT DISPLAYED.");
		}

		// verify count of the Non-Compliant Sites is displayed or not
		try {

			boolean compliance2 = driver
					.findElement(By.xpath(OR.getProperty("surveydashboard_compliance_compliance2_XPATH")))
					.isDisplayed();

			if (compliance2 == true) {
				successMessage("THE COUNT OF THE NON-COMPLIANT SITES IS DISPLAYED SUCCESSFULLY.");
			} else {

				verificationFailedMessage("THE COUNT OF THE NON-COMPLIANT SITES IS NOT DISPLAYED.");
			}
		} catch (Throwable t) {
			verificationFailedMessage("THE COUNT OF THE NON-COMPLIANT SITES IS NOT DISPLAYED.");
		}

		// verify count of the Sites Require Review is displayed or not
		try {

			boolean compliance3 = driver
					.findElement(By.xpath(OR.getProperty("surveydashboard_compliance_compliance3_XPATH")))
					.isDisplayed();

			if (compliance3 == true) {
				successMessage("THE COUNT OF THE SITES REQUIRE REVIEW IS DISPLAYED SUCCESSFULLY.");
			} else {

				verificationFailedMessage("THE COUNT OF THE SITES REQUIRE REVIEW IS NOT DISPLAYED.");
			}
		} catch (Throwable t) {
			verificationFailedMessage("THE COUNT OF THE SITES REQUIRE REVIEW IS NOT DISPLAYED.");
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_hide_CSS");

		// synchronization
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		try {
			// wait for the element
			explicitWaitClickable("questionnaire_settingicon_CSS");

			// click on the settings icon from the top of the screen
			click("questionnaire_settingicon_CSS");

			// wait for the element
			explicitWaitClickable("sidemenu_logout_CSS");

			// click on the logout option from the side menu
			click("sidemenu_logout_CSS");

			// wait for the element
			explicitWaitClickable("signinbtn_BTNTEXT");

		} catch (Throwable t) {
			verificationFailed();
		}

	}
}