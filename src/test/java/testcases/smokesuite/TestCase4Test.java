package testcases.smokesuite;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.Helper;
import base.TestBase;
import utilities.TestUtil;

public class RR5714RioCan1Test extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void rR5714RioCan1Test(Hashtable<String, String> data) throws IOException, InterruptedException {

		execution(data, "rR5714RioCan1Test");

		String taskType = RandomStringUtils.randomAlphabetic(12);

		// refresh the page
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.navigate().refresh();

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

		} catch (Throwable t) {
			verificationFailed();
		}

		// CREATE A NEW RECOMMENDATION TEMPLATE
		title("CREATE A NEW RECOMMENDATION TEMPLATE");

		try {
			// wait for the element
			Thread.sleep(5000);

			// click on the side menu
			click("ssc_leftsidemenubtn_CSS");

			// wait for the element
			Thread.sleep(5000);

			// click on the risk management
			click("sidemenu_riskmanagement_CSS");

			// select the Recommendations option from the Dashboard drop down
			select("dashboard_CSS", data.get("recommendations_dashboard"));

			// wait for the element
			Thread.sleep(3000);

			// scroll down till bottom of the screen

			scrollBottom();

			// click on the Recommendations Overview link
			click("riskmanagement_recommendationsdashboard_recommendationsoverviewlink_XPATH");

			// verify task type screen is displayed
			switchVerification("riskmanagement_recommendationsdashboard_tasktypetitle_XPATH", "Task Type",
					"The Task Type title is not displayed.");

			// click on the add recommendations button
			click("riskmanagement_recommendationsdashboard_createrecommendationsbtn_XPATH");

			// enter task title in the field
			type("riskmanagement_recommendationsdashboard_createrecommendations_tasktypetxt_XPATH", taskType);

			// scroll down till assign to label
			scrollTillElement("riskmanagement_recommendationsdashboard_createrecommendations_assignedtolbl_XPATH");

			// select the Not Applicable option from the recurring type drop down
			select("riskmanagement_recommendationsdashboard_createrecommendations_recurringtypedd_XPATH",
					data.get("recurrence_type_option"));

			// select the environmental option from the drop down
			select("riskmanagement_recommendationsdashboard_createrecommendations_moduledd_XPATH", data.get("module"));

			// click on the save button
			click("riskmanagement_recommendationsdashboard_createrecommendations_savebtn_XPATH");

			// wait for the element
			explicitWaitClickable("closetoastmsg_CSS");

			// click on the toaster close button
			click("closetoastmsg_CSS");

			// enter the newly created task type in the search field

			type("ssc_task_tasktemplateoption_filter_CSS", taskType);

			// verify newly created task type is displayed or not

			try {
				String createdTaskType_XPATH = "//td[contains(text(),'" + taskType + "')]";

				String str2 = (driver.findElement(By.xpath(createdTaskType_XPATH)).getText()).trim();

				if (str2.equals(taskType)) {
					successMessage("The " + taskType + " is verified successfully.");
				} else {
					verificationFailedMessage("The " + taskType + " is not displayed.");
				}

			} catch (Throwable t) {
				verificationFailed();
			}
		} catch (Throwable t) {
			verificationFailed();
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_hide_CSS");

		// synchronization
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		// VERIFY NEWLY CREATED TASK TYPE IS DISPLAYED WHILE CREATING THE NEW TASK
		title("VERIFY NEWLY CREATED TASK TYPE IS DISPLAYED WHILE CREATING THE NEW TASK");

		try {

			// enter the property name in the search field

			type("envreports_propertylist_filtertxt_CSS", data.get("property_2"));

			// click on the task icon
			click("taskicon_CSS");

			// click on the add task button
			click("task_addtaskbtn_CSS");

			// click on the task type field
			click("riskmanagement_createtask_tasktypetxt_XPATH");

			// type the newly created task type in the search field
			type("task_tasktag_searchtxt_CSS", taskType);

			// click on the search task type
			String searchedTaskType = "//div[@ng-click='addOrRemoveToSelected(item); $hide()']//span[text()='"
					+ taskType + "']";
			driver.findElement(By.xpath(searchedTaskType)).click();

			// verify the select task type is correct or not

			try {
				String taskTypeText = driver
						.findElement(By.xpath(OR.getProperty("riskmanagement_insuranceclaim_task_titletxt_XPATH")))
						.getAttribute("value");

				if (taskTypeText.equals(taskType)) {
					successMessage("THE SELECTED TASK TYPE IS DISPLAYED CORRECT.");
				} else {

					verificationFailedMessage("THE SELECTED TASK TYPE IS NOT DISPLAYED CORRECT.");
				}

			} catch (Throwable t) {
				verificationFailed();
			}

		} catch (Throwable t) {
			verificationFailed();
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_hide_CSS");

		// synchronization
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		// CREATE A NEW RECOMMENDATIONS TASK
		title("CREATE A NEW RECOMMENDATIONS TASK");

		try {
			// enter the property name in the search field

			type("envreports_propertylist_filtertxt_CSS", data.get("property_2"));

			// click on the risk management icon from the property list page
			click("riskmanagementicon_CSS");

			// wait for the element
			Thread.sleep(5000);

			// click on the burger menu
			click("menubtn_CSS");

			// wait for the element
			Thread.sleep(5000);

			// click on the tasks option
			click("riskmanagement_taskoption_CSS");

			// click on the add button
			click("riskmanagement_addtaskbtn_CSS");

			// click on the recommendation field
			click("riskmanagement_addtaskbtn_recommendationtxt_XPATH");

			// click on the clear button
			click("riskmanagement_addtaskbtn_recommendationclearbtn_XPATH");

			// enter newly created recommendation in search field
			type("riskmanagement_addtaskbtn_roleassignsearchtxt_CSS", taskType);

			// click on the searched recommendation
			String searchedRecommendation = "//div[@ng-click='addOrRemoveToSelected(item); $hide()']//span[text()='"
					+ taskType + "']";
			driver.findElement(By.xpath(searchedRecommendation)).click();

			// select the option from the for who drop down
			select("riskmanagement_addtaskbtn_forwhodd_XPATH", data.get("for_who"));

			// scroll down the screen
			scrollTillElement("riskmanagement_addtaskbtn_statuslbl_XPATH");

			// wait for the element
			Thread.sleep(3000);

			// click on the due date field
			click("riskmanagement_addtaskbtn_duedatetxt_XPATH");

			// click on the today button
			click("riskmanagement_addtaskbtn_todaybtn_XPATH");

			// click on the save button
			click("riskmanagement_addtaskbtn_savebtn_CSS");

			// enter the newly created recommendation task in the search field

			type("riskmanagement_searchtxt_CSS", taskType);

			// verify newly created recommendation task is displayed or not

			try {
				String createdRecommendationTask_XPATH = "//td[text()='" + taskType + "']";

				String str2 = (driver.findElement(By.xpath(createdRecommendationTask_XPATH)).getText()).trim();

				if (str2.equals(taskType)) {
					successMessage("The " + taskType + " is verified successfully.");
				} else {
					verificationFailedMessage("The " + taskType + " is not displayed.");
				}

			} catch (Throwable t) {
				verificationFailed();
			}

		} catch (Throwable t) {
			verificationFailed();
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_hide_CSS");

		// synchronization
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		// DELETE THE NEWLY CREATED RECOMMENDATION TEMPLATE WITHOUT DELETE
		// RECOMMENDATION TASK
		title("DELETE THE NEWLY CREATED RECOMMENDATION TEMPLATE WITHOUT DELETE RECOMMENDATION TASK");

		try {

			// wait for the element
			Thread.sleep(5000);

			// click on the side menu
			click("ssc_leftsidemenubtn_CSS");

			// wait for the element
			Thread.sleep(5000);

			// click on the risk management
			click("sidemenu_riskmanagement_CSS");

			// wait for the element
			Thread.sleep(5000);

			// select the Recommendations option from the Dashboard drop down
			select("dashboard_CSS", data.get("recommendations_dashboard"));

			// wait for the element
			Thread.sleep(3000);

			// scroll down till bottom of the screen

			scrollBottom();

			// click on the Recommendations Overview link
			click("riskmanagement_recommendationsdashboard_recommendationsoverviewlink_XPATH");

			// verify task type screen is displayed
			switchVerification("riskmanagement_recommendationsdashboard_tasktypetitle_XPATH", "Task Type",
					"The Task Type title is not displayed.");

			// enter the newly created task type in the search field

			type("ssc_task_tasktemplateoption_filter_CSS", taskType);

			// click on the newly created recommendation template
			String searchedRecommendationTemplate = "//td[contains(text(),'" + taskType + "')]";
			driver.findElement(By.xpath(searchedRecommendationTemplate)).click();

			// wait for the element
			Thread.sleep(7000);

			// click on the delete button
			click("riskmanagement_deletebtn_XPATH");

			// click on the delete button of confirmation popup
			click("riskmanagement_deletebtn_confirmation_XPATH");

			// scroll up the screen
			scrollTop();

			// verify edit task type title is displayed or not
			switchVerification("riskmanagement_edittasktypelbl_XPATH", "Edit Task Type",
					"The Edit Task Type title is not displayed.");

		} catch (Throwable t) {
			verificationFailed();
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_hide_CSS");

		// synchronization
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		// DELETE THE NEWLY CREATED RECOMMENDATIONS TASK
		title("DELETE THE NEWLY CREATED RECOMMENDATIONS TASK");

		try {
			// enter the property name in the search field

			type("envreports_propertylist_filtertxt_CSS", data.get("property_2"));

			// click on the risk management icon from the property list page
			click("riskmanagementicon_CSS");

			// wait for the element
			Thread.sleep(5000);

			// click on the burger menu
			click("menubtn_CSS");

			// wait for the element
			Thread.sleep(5000);

			// click on the tasks option
			click("riskmanagement_taskoption_CSS");

			// enter the newly created recommendation task in the search field

			type("riskmanagement_searchtxt_CSS", taskType);

			// click on the newly created recommendation task
			String createdRecommendationTask_XPATH = "//td[text()='" + taskType + "']";
			driver.findElement(By.xpath(createdRecommendationTask_XPATH)).click();

			// wait for the element
			Thread.sleep(5000);

			// click on the delete button
			click("riskmanagement_recommendationtask_deletebtn_XPATH");

			// wait for the element
			Thread.sleep(3000);

			// click on the delete button of the confirmation popup
			click("riskmanagement_recommendationtask_deletebtn_confirmation_XPATH");

			// enter the newly created recommendation task in the search field

			type("riskmanagement_searchtxt_CSS", taskType);

			// verify deleted recommendation task is displayed or not
			try {

				String deleteElement = (driver.findElement(By.xpath(createdRecommendationTask_XPATH)).getText()).trim();

				if (deleteElement.equals(taskType)) {
					verificationFailedMessage("The  " + taskType + " is not deleted : ");
				}
			} catch (Throwable t) {
				successMessage("The " + taskType + " is deleted successfully.");
			}

		} catch (Throwable t) {
			verificationFailed();
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_hide_CSS");

		// synchronization
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

//		// DELETE THE NEWLY CREATED RECOMMENDATION TEMPLATE AFTER DELETE
//		// RECOMMENDATION TASK
//		title("DELETE THE NEWLY CREATED RECOMMENDATION TEMPLATE AFTER DELETE RECOMMENDATION TASK");
//
//		try {
//			// click on the side menu
//			click("ssc_leftsidemenubtn_CSS");
//
//			// wait for the element
//			explicitWaitClickable("sidemenu_riskmanagement_CSS");
//
//			// click on the risk management
//			click("sidemenu_riskmanagement_CSS");
//
//			// select the Recommendations option from the Dashboard drop down
//			select("dashboard_CSS", data.get("recommendations_dashboard"));
//
//			// wait for the element
//			Thread.sleep(3000);
//
//			// scroll down till bottom of the screen
//			 
//			scrollBottom();
//
//			// click on the Recommendations Overview link
//			click("riskmanagement_recommendationsdashboard_recommendationsoverviewlink_XPATH");
//
//			// verify task type screen is displayed
//			switchVerification("riskmanagement_recommendationsdashboard_tasktypetitle_XPATH", "Task Type",
//					"The Task Type title is not displayed.");
//
//			// enter the newly created task type in the search field
//			 
//			type("ssc_task_tasktemplateoption_filter_CSS", taskType);
//
//			// click on the newly created recommendation template
//			String searchedRecommendationTemplate = "//td[contains(text(),'" + taskType + "')]";
//			driver.findElement(By.xpath(searchedRecommendationTemplate)).click();
//
//			// wait for the element
//			Thread.sleep(7000);
//
//			// click on the delete button
//			click("riskmanagement_deletebtn_XPATH");
//
//			// wait for the element
//			Thread.sleep(3000);
//
//			// click on the delete button of confirmation popup
//			click("riskmanagement_deletebtn_confirmation_XPATH");
//
//			// enter the newly created task type in the search field
//			 
//			type("ssc_task_tasktemplateoption_filter_CSS", taskType);
//
//			// verify deleted recommendation template is displayed or not
//			try {
//
//				String createdRecommendationTemplate_XPATH = "//td[text()='" + taskType + "']";
//
//				String deleteElement1 = (driver.findElement(By.xpath(createdRecommendationTemplate_XPATH)).getText())
//						.trim();
//
//				if (deleteElement1.equals(taskType)) {
//					verificationFailedMessage("The  " + taskType + " is not deleted : ");
//
//				}
//
//			} catch (Throwable t) {
//				successMessage("The " + taskType + " is deleted successfully.");
//			}
//
//		} catch (Throwable t) {
//			verificationFailed();
//		}
//
//		// click on the home icon from the top of the screen
//		click("questionnaire_homeburgermenubtn_hide_CSS");
//
//		// synchronization
//		explicitWait("propertylist_title_XPATH");
//
//		// verify the property list
//		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

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