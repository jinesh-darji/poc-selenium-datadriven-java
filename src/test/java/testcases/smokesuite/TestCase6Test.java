package testcases.smokesuite;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.Helper;
import base.TestBase;
import utilities.TestUtil;

public class RR5940PSDashboard1Test extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void rR5940PSDashboard1Test(Hashtable<String, String> data) throws IOException, InterruptedException {

		execution(data, "rR5940PSDashboard1Test");

		Helper helper = new Helper();

		// VERIFY THE COUNT AND DETAILS OF THE 30 DAYS OVERDUE TASK CARD
		title("VERIFY THE COUNT AND DETAILS OF THE 30 DAYS OVERDUE TASK CARD");

		// refresh the page
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.navigate().refresh();

		try {
			// LOGIN WITH ADMIN USER
			title("LOGIN WITH ADMIN USER");

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

			// VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARD - INITIALY
			title("VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARD - INITIALY");

			try {

				// wait for the element
				Thread.sleep(5000);

				// click on the side menu button
				click("menubtn_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the portfolio summary option
				click("sidemenu_portfoliosummary_XPATH");

				// verify property count
				switchVerification("portfoliosummary_toppropertycount_XPATH", "4",
						"The property count is not displayed correctly.");

				// scroll down the screen
				scrollTillElement("portfoliosummary_30daysduedatelabel_XPATH");

				// verify percentage of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count1_XPATH", "0%",
						"The percentage of the 30 days due date task is displayed incorrect.");

				// verify count of the overdue tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count2_XPATH", "0",
						"The count of the overdue tasks of the 30 days due date task is displayed incorrect.");

				// verify count of the all tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count3_XPATH", "0",
						"The count of the all tasks of the 30 days due date task is displayed incorrect.");

//				// scroll down the screen
//				WebElement label2 = driver.findElement(By.xpath("portfoliosummary_31to90daysduedatelabel_XPATH"));
//				js.executeScript("arguments[0].scrollIntoView(true);", label2);
//
//				// verify percentage of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count1_XPATH", "0%",
//						"The percentage of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the overdue tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count2_XPATH", "0",
//						"The count of the overdue tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the all tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count3_XPATH", "0",
//						"The count of the all tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the more than 90 days overdue of the 31 to 90 days due date
//				// task
//				switchVerification("portfoliosummary_31to90daysduedate_count4_XPATH", "0",
//						"The count of the more than 90 days overdue of the 31 to 90 days due date task is displayed incorrect.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// ADD THE FIRST TASK - SET DUE DATE AS A TODAY
			title("ADD THE FIRST TASK - SET DUE DATE AS A TODAY");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the add task button
				click("task_addtaskbtn_CSS");

				// enter the data in the task title field

				type("task_addtask_titletxt_CSS", data.get("task_title"));

				// enter the data in the task location field

				type("task_addtask_locationtxt_CSS", data.get("task_location"));

				// scroll down the screen

				scrollByPixel(600);

				// SELECT THE DUE DATE

				// click on the due date field
				click("task_duedatetxt_CSS");

				// wait for the element
				explicitWaitClickable("task_tasktag_newduedatetxt_todaybtn_XPATH");

				// click on the today button
				click("task_tasktag_newduedatetxt_todaybtn_XPATH");

				// scroll down the screen
				scrollByPixel(600);

				// wait for the element
				explicitWait("task_addtask_moduledd_CSS");

				// select the application option from the module dropdown
				select("task_addtask_moduledd_CSS", data.get("task_module"));

				// click on the save button
				click("survey_task_savebtn_CSS");

				// scroll up the screen
				scrollByPixel(-400);

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_psd1_XPATH", "Smoke Test PSD 1 Task Title",
						"The Smoke Test PSD 1 Task Title is not displayed.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARDS - AFTER CREATED TASK WITH
			// TODAY'S DUE DATE
			title("VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARDS - AFTER CREATED TASK WITH TODAY'S DUE DATE");

			try {

				// wait for the element
				Thread.sleep(5000);

				// click on the side menu button
				click("menubtn_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the portfolio summary option
				click("sidemenu_portfoliosummary_XPATH");

				// verify property count
				switchVerification("portfoliosummary_toppropertycount_XPATH", "4",
						"The property count is not displayed correctly.");

				// scroll down the screen
				scrollTillElement("portfoliosummary_30daysduedatelabel_XPATH");

				// verify percentage of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count1_XPATH", "0%",
						"The percentage of the 30 days due date task is displayed incorrect.");

				// verify count of the overdue tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count2_XPATH", "0",
						"The count of the overdue tasks of the 30 days due date task is displayed incorrect.");

				// verify count of the all tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count3_XPATH", "0",
						"The count of the all tasks of the 30 days due date task is displayed incorrect.");

//				// scroll down the screen
//				WebElement label2 = driver.findElement(By.xpath("portfoliosummary_31to90daysduedatelabel_XPATH"));
//				js.executeScript("arguments[0].scrollIntoView(true);", label2);
//
//				// verify percentage of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count1_XPATH", "0%",
//						"The percentage of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the overdue tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count2_XPATH", "0",
//						"The count of the overdue tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the all tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count3_XPATH", "0",
//						"The count of the all tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the more than 90 days overdue of the 31 to 90 days due date
//				// task
//				switchVerification("portfoliosummary_31to90daysduedate_count4_XPATH", "0",
//						"The count of the more than 90 days overdue of the 31 to 90 days due date task is displayed incorrect.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// UPDATE THE DUE DATE OF THE TASK FROM TODAY TO FUTURE DATE
			title("UPDATE THE DUE DATE OF THE TASK FROM TODAY TO FUTURE DATE");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_psd1_XPATH", "Smoke Test PSD 1 Task Title",
						"The Smoke Test PSD 1 Task Title is not displayed.");

				// click on the newly created task
				click("task_addedtask_psd1_XPATH");

				// click on the change due date button
				click("task_tasktag_changeduedatebtn_CSS");

				// enter date which is 2 days after current date
				LocalDate futureDate = LocalDate.now().plusDays(2);
				String finalFutureDate = futureDate.toString();
				type("task_tasktag_changeduedate_newduedate_XPATH", finalFutureDate);

				// click on the new due date label
				click("task_tasktag_changeduedate_newduedatelbl_XPATH");

				// enter data in the reason for change field

				type("task_updateduedate_reasonforchangetxt_CSS", data.get("reason_for_change"));

				// click on the apply button
				click("task_updateduedate_applybtn_CSS");

				// scroll up the screen

				scrollByPixel(-500);

				// click on the update button
				click("survey_task_updatebtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// click on the back button
				click("task_backbtn_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_psd1_XPATH", "Smoke Test PSD 1 Task Title",
						"The Smoke Test PSD 1 Task Title is not displayed.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARD - AFTER UPDATED THE DUE DATE OF
			// THE TASK FROM TODAY TO FUTURE DATE
			title("VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARD - AFTER UPDATED THE DUE DATE OF THE TASK FROM TODAY TO FUTURE DATE");

			try {

				// wait for the element
				Thread.sleep(5000);

				// click on the side menu button
				click("menubtn_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the portfolio summary option
				click("sidemenu_portfoliosummary_XPATH");

				// verify property count
				switchVerification("portfoliosummary_toppropertycount_XPATH", "4",
						"The property count is not displayed correctly.");

				// scroll down the screen
				scrollTillElement("portfoliosummary_30daysduedatelabel_XPATH");

				// verify percentage of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count1_XPATH", "0%",
						"The percentage of the 30 days due date task is displayed incorrect.");

				// verify count of the overdue tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count2_XPATH", "0",
						"The count of the overdue tasks of the 30 days due date task is displayed incorrect.");

				// verify count of the all tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count3_XPATH", "0",
						"The count of the all tasks of the 30 days due date task is displayed incorrect.");

//				// scroll down the screen
//				WebElement label2 = driver.findElement(By.xpath("portfoliosummary_31to90daysduedatelabel_XPATH"));
//				js.executeScript("arguments[0].scrollIntoView(true);", label2);
//
//				// verify percentage of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count1_XPATH", "0%",
//						"The percentage of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the overdue tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count2_XPATH", "0",
//						"The count of the overdue tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the all tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count3_XPATH", "0",
//						"The count of the all tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the more than 90 days overdue of the 31 to 90 days due date
//				// task
//				switchVerification("portfoliosummary_31to90daysduedate_count4_XPATH", "0",
//						"The count of the more than 90 days overdue of the 31 to 90 days due date task is displayed incorrect.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// UPDATE THE DUE DATE OF THE TASK FROM FUTURE TO PAST DATE - LESS THAN 30 DAYS
			title("UPDATE THE DUE DATE OF THE TASK FROM FUTURE TO PAST DATE - LESS THAN 30 DAYS");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_psd1_XPATH", "Smoke Test PSD 1 Task Title",
						"The Smoke Test PSD 1 Task Title is not displayed.");

				// click on the newly created task
				click("task_addedtask_psd1_XPATH");

				// click on the change due date button
				click("task_tasktag_changeduedatebtn_CSS");

				// enter date which is 5 days before current date
				LocalDate pastDate = LocalDate.now().minusDays(5);
				String finalPastDate = pastDate.toString();
				type("task_tasktag_changeduedate_newduedate_XPATH", finalPastDate);

				// click on the new due date label
				click("task_tasktag_changeduedate_newduedatelbl_XPATH");

				// enter data in the reason for change field

				type("task_updateduedate_reasonforchangetxt_CSS", data.get("reason_for_change"));

				// click on the apply button
				click("task_updateduedate_applybtn_CSS");

				// scroll up the screen

				scrollByPixel(-500);

				// click on the update button
				click("survey_task_updatebtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// click on the back button
				click("task_backbtn_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_psd1_XPATH", "Smoke Test PSD 1 Task Title",
						"The Smoke Test PSD 1 Task Title is not displayed.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARD - AFTER UPDATED THE DUE DATE OF
			// THE TASK FROM FUTURE TO PAST DATE - LESS THAN 30 DAYS
			title("VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARD - AFTER UPDATED THE DUE DATE OF THE TASK FROM FUTURE TO PAST DATE - LESS THAN 30 DAYS");

			try {

				// wait for the element
				Thread.sleep(5000);

				// click on the side menu button
				click("menubtn_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the portfolio summary option
				click("sidemenu_portfoliosummary_XPATH");

				// verify property count
				switchVerification("portfoliosummary_toppropertycount_XPATH", "4",
						"The property count is not displayed correctly.");

				// scroll down the screen
				scrollTillElement("portfoliosummary_30daysduedatelabel_XPATH");

				// verify percentage of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count1_XPATH", "100%",
						"The percentage of the 30 days due date task is displayed incorrect.");

				// verify count of the overdue tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count2_XPATH", "1",
						"The count of the overdue tasks of the 30 days due date task is displayed incorrect.");

				// verify count of the all tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count3_XPATH", "1",
						"The count of the all tasks of the 30 days due date task is displayed incorrect.");

//				// scroll down the screen
//				WebElement label2 = driver.findElement(By.xpath("portfoliosummary_31to90daysduedatelabel_XPATH"));
//				js.executeScript("arguments[0].scrollIntoView(true);", label2);
//
//				// verify percentage of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count1_XPATH", "0%",
//						"The percentage of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the overdue tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count2_XPATH", "0",
//						"The count of the overdue tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the all tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count3_XPATH", "0",
//						"The count of the all tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the more than 90 days overdue of the 31 to 90 days due date
//				// task
//				switchVerification("portfoliosummary_31to90daysduedate_count4_XPATH", "0",
//						"The count of the more than 90 days overdue of the 31 to 90 days due date task is displayed incorrect.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// VERIFY UPDATED DUE DATE IN THE TASK FROM THE PORTFOLIO SUMMARY DASHBOARD
			// SCREEN
			title("VERIFY UPDATED DUE DATE IN THE TASK FROM THE TASK LIST OF THE PORTFOLIO SUMMARY DASHBOARD SCREEN");

			try {

				// wait for the element
				Thread.sleep(5000);

				// click on the side menu button
				click("menubtn_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the portfolio summary option
				click("sidemenu_portfoliosummary_XPATH");

				// verify property count
				switchVerification("portfoliosummary_toppropertycount_XPATH", "4",
						"The property count is not displayed correctly.");

				// scroll down the screen
				scrollTillElement("portfoliosummary_30daysduedatelabel_XPATH");

				// verify percentage of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count1_XPATH", "100%",
						"The percentage of the 30 days due date task is displayed incorrect.");

				// enter the newly created first task in the search field

				type("riskmanagement_task_searchtxt_XPATH", data.get("task_title"));

				try {
					// validate the due date is displayed correct or not
					LocalDate pastDate1 = LocalDate.now().minusDays(5);
					String finalPastDate1 = pastDate1.toString();
					String duedatetask1 = "//td[text()='" + data.get("task_title")
							+ "']//following-sibling::td[text()='" + finalPastDate1 + "']";
					String duedate1 = (driver.findElement(By.xpath(duedatetask1)).getText()).trim();

					if (duedate1.equals(finalPastDate1)) {
						successMessage("The past due date is verified successfully.");
					} else {
						verificationFailedMessage("The past due date is not displayed correct.");
					}
				} catch (Throwable t) {
					verificationFailedMessage("The past due date is not displayed correct.");
				}

				// click on the searched task
				String task1 = "//td[text()='" + data.get("task_title") + "']";
				driver.findElement(By.xpath(task1)).click();

				// click on the change due date button
				click("task_tasktag_changeduedatebtn_CSS");

				// enter date which is 5 days before current date
				LocalDate pastDate2 = LocalDate.now().minusDays(15);
				String finalPastDate2 = pastDate2.toString();
				type("task_tasktag_changeduedate_newduedate_XPATH", finalPastDate2);

				// click on the new due date label
				click("task_tasktag_changeduedate_newduedatelbl_XPATH");

				// enter data in the reason for change field

				type("task_updateduedate_reasonforchangetxt_CSS", data.get("reason_for_change"));

				// click on the apply button
				click("task_updateduedate_applybtn_CSS");

				// scroll up the screen
				scrollByPixel(-500);

				// click on the update button
				click("survey_task_updatebtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// enter the newly created first task in the search field

				type("riskmanagement_task_searchtxt_XPATH", data.get("task_title"));

				try {
					// validate the due date is displayed correct or not
					LocalDate pastDate4 = LocalDate.now().minusDays(15);
					String finalPastDate4 = pastDate4.toString();
					String duedatetask4 = "//td[text()='" + data.get("task_title")
							+ "']//following-sibling::td[text()='" + finalPastDate4 + "']";
					String duedate4 = (driver.findElement(By.xpath(duedatetask4)).getText()).trim();

					if (duedate4.equals(finalPastDate4)) {
						successMessage("The updated past due date is verified successfully.");
					} else {
						verificationFailedMessage("The updated past due date is not displayed correct.");
					}
				} catch (Throwable t) {
					verificationFailedMessage("The updated past due date is not displayed correct.");
				}

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// VERIFY UPDATED DUE DATE IN THE TASK MODULE
			title("VERIFY UPDATED DUE DATE IN THE TASK MODULE");

			try {

				// enter the property name in the search field

				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_psd1_XPATH", "Smoke Test PSD 1 Task Title",
						"The Smoke Test PSD 1 Task Title is not displayed.");

				try {
					// verify the update due date of the first task
					LocalDate pastDate5 = LocalDate.now().minusDays(15);
					String finalPastDate5 = pastDate5.toString();
					String updatedDueDate5 = "//p[text()='" + data.get("task_title")
							+ "']//parent::td[@ng-click='editTaskFromLandingPage(task)']//following-sibling::td[text()='"
							+ finalPastDate5 + "']";

					if (updatedDueDate5.equals(finalPastDate5)) {
						successMessage("The updated past due date is verified successfully.");
					} else {
						verificationFailedMessage("The updated past due date is not displayed correct.");
					}
				} catch (Throwable t) {
					verificationFailedMessage("The updated past due date is not displayed correct.");
				}

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// ADD THE SECOND TASK - SET DUE DATE AS A TODAY
			title("ADD THE SECOND TASK - SET DUE DATE AS A TODAY");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the add task button
				click("task_addtaskbtn_CSS");

				// enter the data in the task title field

				type("task_addtask_titletxt_CSS", data.get("task_title_2"));

				// enter the data in the task location field

				type("task_addtask_locationtxt_CSS", data.get("task_location"));

				// scroll down the screen

				scrollByPixel(600);

				// SELECT THE DUE DATE

				// click on the due date field
				click("task_duedatetxt_CSS");

				// wait for the element
				explicitWaitClickable("task_tasktag_newduedatetxt_todaybtn_XPATH");

				// click on the today button
				click("task_tasktag_newduedatetxt_todaybtn_XPATH");

				// scroll down the screen
				scrollByPixel(600);

				// wait for the element
				explicitWait("task_addtask_moduledd_CSS");

				// select the application option from the module dropdown
				select("task_addtask_moduledd_CSS", data.get("task_module"));

				// click on the save button
				click("survey_task_savebtn_CSS");

				// scroll up the screen
				scrollByPixel(-400);

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title_2"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_psd2_XPATH", "Smoke Test PSD 2 Task Title",
						"The Smoke Test PSD 2 Task Title is not displayed.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARD - AFTER CREATED THE SECOND TASK
			// WITH TODAY'S DUE DATE
			title("VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARD - AFTER CREATED THE SECOND TASK WITH TODAY'S DUE DATE");

			try {

				// wait for the element
				Thread.sleep(5000);

				// click on the side menu button
				click("menubtn_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the portfolio summary option
				click("sidemenu_portfoliosummary_XPATH");

				// verify property count
				switchVerification("portfoliosummary_toppropertycount_XPATH", "4",
						"The property count is not displayed correctly.");

				// scroll down the screen
				scrollTillElement("portfoliosummary_30daysduedatelabel_XPATH");

				// verify percentage of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count1_XPATH", "100%",
						"The percentage of the 30 days due date task is displayed incorrect.");

				// verify count of the overdue tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count2_XPATH", "1",
						"The count of the overdue tasks of the 30 days due date task is displayed incorrect.");

				// verify count of the all tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count3_XPATH", "1",
						"The count of the all tasks of the 30 days due date task is displayed incorrect.");

//				// scroll down the screen
//				WebElement label2 = driver.findElement(By.xpath("portfoliosummary_31to90daysduedatelabel_XPATH"));
//				js.executeScript("arguments[0].scrollIntoView(true);", label2);
//
//				// verify percentage of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count1_XPATH", "0%",
//						"The percentage of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the overdue tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count2_XPATH", "0",
//						"The count of the overdue tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the all tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count3_XPATH", "0",
//						"The count of the all tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the more than 90 days overdue of the 31 to 90 days due date
//				// task
//				switchVerification("portfoliosummary_31to90daysduedate_count4_XPATH", "0",
//						"The count of the more than 90 days overdue of the 31 to 90 days due date task is displayed incorrect.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// UPDATE THE DUE DATE OF THE SECOND TASK FROM TODAY TO PAST DATE - LESS THAN 30
			// DAYS
			title("UPDATE THE DUE DATE OF THE SECOND TASK FROM TODAY TO PAST DATE - LESS THAN 30 DAYS");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title_2"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_psd2_XPATH", "Smoke Test PSD 2 Task Title",
						"The Smoke Test PSD 2 Task Title is not displayed.");

				// click on the newly created task
				click("task_addedtask_psd2_XPATH");

				// click on the change due date button
				click("task_tasktag_changeduedatebtn_CSS");

				// enter date which is 3 days before current date
				LocalDate pastDate = LocalDate.now().minusDays(3);
				String finalPastDate = pastDate.toString();
				type("task_tasktag_changeduedate_newduedate_XPATH", finalPastDate);

				// click on the new due date label
				click("task_tasktag_changeduedate_newduedatelbl_XPATH");

				// enter data in the reason for change field

				type("task_updateduedate_reasonforchangetxt_CSS", data.get("reason_for_change"));

				// click on the apply button
				click("task_updateduedate_applybtn_CSS");

				// scroll up the screen

				scrollByPixel(-500);

				// click on the update button
				click("survey_task_updatebtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// click on the back button
				click("task_backbtn_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_psd2_XPATH", "Smoke Test PSD 2 Task Title",
						"The Smoke Test PSD 2 Task Title is not displayed.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARDS - AFTER UPDATED THE DUE DATE
			// OF THE SECOND TASK FROM TODAY TO PAST DATE - LESS THAN 30 DAYS
			title("VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARD - AFTER UPDATED THE DUE DATE OF THE SECOND TASK FROM TODAY TO PAST DATE - LESS THAN 30 DAYS");

			try {

				// wait for the element
				Thread.sleep(5000);

				// click on the side menu button
				click("menubtn_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the portfolio summary option
				click("sidemenu_portfoliosummary_XPATH");

				// verify property count
				switchVerification("portfoliosummary_toppropertycount_XPATH", "4",
						"The property count is not displayed correctly.");

				// scroll down the screen
				scrollTillElement("portfoliosummary_30daysduedatelabel_XPATH");

				// verify percentage of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count1_XPATH", "100%",
						"The percentage of the 30 days due date task is displayed incorrect.");

				// verify count of the overdue tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count2_XPATH", "2",
						"The count of the overdue tasks of the 30 days due date task is displayed incorrect.");

				// verify count of the all tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count3_XPATH", "2",
						"The count of the all tasks of the 30 days due date task is displayed incorrect.");

//				// scroll down the screen
//				WebElement label2 = driver.findElement(By.xpath("portfoliosummary_31to90daysduedatelabel_XPATH"));
//				js.executeScript("arguments[0].scrollIntoView(true);", label2);
//
//				// verify percentage of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count1_XPATH", "0%",
//						"The percentage of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the overdue tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count2_XPATH", "0",
//						"The count of the overdue tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the all tasks of the 31 to 90 days due date task
//				switchVerification("portfoliosummary_31to90daysduedate_count3_XPATH", "0",
//						"The count of the all tasks of the 31 to 90 days due date task is displayed incorrect.");
//
//				// verify count of the more than 90 days overdue of the 31 to 90 days due date
//				// task
//				switchVerification("portfoliosummary_31to90daysduedate_count4_XPATH", "0",
//						"The count of the more than 90 days overdue of the 31 to 90 days due date task is displayed incorrect.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// VERIFY UPDATED DUE DATE IN THE TASK FROM THE PORTFOLIO SUMMARY DASHBOARD
			// SCREEN
			title("VERIFY UPDATED DUE DATE IN THE TASK FROM THE TASK LIST OF THE PORTFOLIO SUMMARY DASHBOARD SCREEN");

			try {

				// wait for the element
				Thread.sleep(5000);

				// click on the side menu button
				click("menubtn_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the portfolio summary option
				click("sidemenu_portfoliosummary_XPATH");

				// verify property count
				switchVerification("portfoliosummary_toppropertycount_XPATH", "4",
						"The property count is not displayed correctly.");

				// scroll down the screen
				scrollTillElement("portfoliosummary_30daysduedatelabel_XPATH");

				// verify percentage of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count1_XPATH", "100%",
						"The percentage of the 30 days due date task is displayed incorrect.");

				// enter the newly created first task in the search field

				type("riskmanagement_task_searchtxt_XPATH", data.get("task_title_2"));

				try {
					// validate the due date is displayed correct or not
					LocalDate pastDate1 = LocalDate.now().minusDays(3);
					String finalPastDate1 = pastDate1.toString();
					String duedatetask1 = "//td[text()='" + data.get("task_title_2")
							+ "']//following-sibling::td[text()='" + finalPastDate1 + "']";
					String duedate1 = (driver.findElement(By.xpath(duedatetask1)).getText()).trim();

					if (duedate1.equals(finalPastDate1)) {
						successMessage("The past due date is verified successfully.");
					} else {
						verificationFailedMessage("The past due date is not displayed correct.");
					}
				} catch (Throwable t) {
					verificationFailedMessage("The past due date is not displayed correct.");
				}

				// click on the searched task
				String task1 = "//td[text()='" + data.get("task_title_2") + "']";
				driver.findElement(By.xpath(task1)).click();

				// click on the change due date button
				click("task_tasktag_changeduedatebtn_CSS");

				// enter date which is 5 days before current date
				LocalDate pastDate2 = LocalDate.now().minusDays(10);
				String finalPastDate2 = pastDate2.toString();
				type("task_tasktag_changeduedate_newduedate_XPATH", finalPastDate2);

				// click on the new due date label
				click("task_tasktag_changeduedate_newduedatelbl_XPATH");

				// enter data in the reason for change field

				type("task_updateduedate_reasonforchangetxt_CSS", data.get("reason_for_change"));

				// click on the apply button
				click("task_updateduedate_applybtn_CSS");

				// scroll up the screen
				scrollByPixel(-500);

				// click on the update button
				click("survey_task_updatebtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// enter the newly created first task in the search field

				type("riskmanagement_task_searchtxt_XPATH", data.get("task_title_2"));

				try {
					// validate the due date is displayed correct or not
					LocalDate pastDate4 = LocalDate.now().minusDays(10);
					String finalPastDate4 = pastDate4.toString();
					String duedatetask4 = "//td[text()='" + data.get("task_title_2")
							+ "']//following-sibling::td[text()='" + finalPastDate4 + "']";
					String duedate4 = (driver.findElement(By.xpath(duedatetask4)).getText()).trim();

					if (duedate4.equals(finalPastDate4)) {
						successMessage("The updated past due date is verified successfully.");
					} else {
						verificationFailedMessage("The updated past due date is not displayed correct.");
					}
				} catch (Throwable t) {
					verificationFailedMessage("The updated past due date is not displayed correct.");
				}

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// VERIFY UPDATED DUE DATE IN THE TASK MODULE
			title("VERIFY UPDATED DUE DATE IN THE TASK MODULE");

			try {

				// enter the property name in the search field

				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title_2"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_psd2_XPATH", "Smoke Test PSD 2 Task Title",
						"The Smoke Test PSD 2 Task Title is not displayed.");

				try {
					// verify the update due date of the first task
					LocalDate pastDate5 = LocalDate.now().minusDays(10);
					String finalPastDate5 = pastDate5.toString();
					String updatedDueDate5 = "//p[text()='" + data.get("task_title_2")
							+ "']//parent::td[@ng-click='editTaskFromLandingPage(task)']//following-sibling::td[text()='"
							+ finalPastDate5 + "']";

					if (updatedDueDate5.equals(finalPastDate5)) {
						successMessage("The updated past due date is verified successfully.");
					} else {
						verificationFailedMessage("The updated past due date is not displayed correct.");
					}
				} catch (Throwable t) {
					verificationFailedMessage("The updated past due date is not displayed correct.");
				}

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// UPDATE THE STATUS OF THE FIRST TASK TO COMPLETED
			title("UPDATE THE STATUS OF THE FIRST TASK TO COMPLETED");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_psd1_XPATH", "Smoke Test PSD 1 Task Title",
						"The Smoke Test PSD 1 Task Title is not displayed.");

				// click on the newly created task
				click("task_addedtask_psd1_XPATH");

				// click on the start button
				click("propertyproject_mu_repair_startbtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// scroll up the screen

				scrollByPixel(-400);

				// verify the in-progress status is displayed or not
				switchVerification("task_inprogress_statuslbl_XPATH", "In Progress",
						"The In Progress status is not displayed.");

				// wait for the element
				explicitWaitClickable("task_closebtn_CSS");

				// click on the close button
				click("task_closebtn_CSS");

				// wait for the element
				explicitWaitClickable("task_closebtn_yesbtn_CSS");

				// click on the yes button of confirmation popup model
				click("task_closebtn_yesbtn_CSS");

				// scroll up the screen
				scrollByPixel(-400);

				// verify the close status is displayed or not
				switchVerification("task_closedstatus_XPATH", "Closed", "The Closed status is not displayed.");

				// click on the update button
				click("survey_task_updatebtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// click on the back button
				click("task_backbtn_CSS");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARD - AFTER CLOSE THE FIRST TASK
			title("VERIFY COUNT OF THE 30 DAYS OVERDUE TASK CARD - AFTER CLOSE THE FIRST TASK");

			try {

				// wait for the element
				Thread.sleep(5000);

				// click on the side menu button
				click("menubtn_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the portfolio summary option
				click("sidemenu_portfoliosummary_XPATH");

				// verify property count
				switchVerification("portfoliosummary_toppropertycount_XPATH", "4",
						"The property count is not displayed correctly.");

				// scroll down the screen
				scrollTillElement("portfoliosummary_30daysduedatelabel_XPATH");

				// verify percentage of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count1_XPATH", "50%",
						"The percentage of the 30 days due date task is displayed incorrect.");

				// verify count of the overdue tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count2_XPATH", "1",
						"The count of the overdue tasks of the 30 days due date task is displayed incorrect.");

				// verify count of the all tasks of the 30 days due date task
				switchVerification("portfoliosummary_30daysduedate_count3_XPATH", "2",
						"The count of the all tasks of the 30 days due date task is displayed incorrect.");

				// click on the percentage of the due date of less than 30 days
				click("portfoliosummary_30daysduedate_count1_XPATH");

				// enter the newly created first task in the search field

				type("riskmanagement_task_searchtxt_XPATH", data.get("task_title"));

				// verify first task
				deleteVerification("portfoliosummary_tasklist_task1_XPATH", "Smoke Test PSD 1 Task Title");

				// enter the newly created first task in the search field

				type("riskmanagement_task_searchtxt_XPATH", data.get("task_title_2"));

				// verify second task
				switchVerification("portfoliosummary_tasklist_task2_XPATH", "Smoke Test PSD 2 Task Title",
						"The Smoke Test PSD 2 Task Title is not displayed.");

				// click on the back button
				click("portfoliosummary_tasklist_backbtn_XPATH");

				// click on the count of the due date of less than 30 days
				click("portfoliosummary_30daysduedate_count2_XPATH");

				// enter the newly created first task in the search field

				type("riskmanagement_task_searchtxt_XPATH", data.get("task_title"));

				// verify first task
				deleteVerification("portfoliosummary_tasklist_task1_XPATH", "Smoke Test PSD 1 Task Title");

				// enter the newly created first task in the search field

				type("riskmanagement_task_searchtxt_XPATH", data.get("task_title_2"));

				// verify second task
				switchVerification("portfoliosummary_tasklist_task2_XPATH", "Smoke Test PSD 2 Task Title",
						"The Smoke Test PSD 2 Task Title is not displayed.");

				// click on the back button
				click("portfoliosummary_tasklist_backbtn_XPATH");

				// click on the count of the due date of less than 30 days
				click("portfoliosummary_30daysduedate_count3_XPATH");

				// enter the newly created first task in the search field

				type("riskmanagement_task_searchtxt_XPATH", data.get("task_title"));

				// verify first task
				deleteVerification("portfoliosummary_tasklist_task1_XPATH", "Smoke Test PSD 1 Task Title");

				// enter the newly created first task in the search field

				type("riskmanagement_task_searchtxt_XPATH", data.get("task_title_2"));

				// verify second task
				switchVerification("portfoliosummary_tasklist_task2_XPATH", "Smoke Test PSD 2 Task Title",
						"The Smoke Test PSD 2 Task Title is not displayed.");

				// click on the back button
				click("portfoliosummary_tasklist_backbtn_XPATH");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// =================================================================

			// reopen closed task and verify count in PSD
			// UPDATE DUE DATE WITH MORE THAN 30 DAYS AND MORE THAN 90 DAYS
			// DELETE ALL THE CREATED TASKS

			// =================================================================

			// CLOSE THE TASK AND VERIFY UPDATED DUE DATE IS DISPLAYED OR NOT
			title("CLOSE THE TASK AND VERIFY UPDATED DUE DATE IS DISPLAYED OR NOT");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_smoke11_XPATH", "Smoke Test One Task Title",
						"The Smoke Test One Task Title is not displayed.");

				// click on the newly created task
				click("task_addedtask_smoke11_XPATH");

				// wait for the element
				explicitWaitClickable("task_closebtn_CSS");

				// click on the close button
				click("task_closebtn_CSS");

				// wait for the element
				explicitWaitClickable("task_closebtn_yesbtn_CSS");

				// click on the yes button of confirmation popup model
				click("task_closebtn_yesbtn_CSS");

				// scroll up the screen

				scrollByPixel(-400);

				// verify the close status is displayed or not
				switchVerification("task_closedstatus_XPATH", "Closed", "The Closed status is not displayed.");

				// click on the back button
				click("task_backbtn_CSS");

				// select the close option from the status drop down
				select("survey_task_closefilter_CSS", data.get("task_closed_status"));

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				switchVerification("task_addedtask_smoke11_XPATH", "Smoke Test One Task Title",
						"The Smoke Test One Task Title is not displayed.");

				// VERIFY THE DUE DATE IS DISPLAYED CORRECT OR NOT ON CLOSED TASK LIST
				title("VERIFY THE DUE DATE IS DISPLAYED CORRECT OR NOT ON CLOSED TASK LIST");

				try {
					LocalDate currentDate = LocalDate.now();

					String expectedCurrentDate = currentDate.toString();

					String currentDateXpath = "//td[text()='" + currentDate + "']";

					String finalCurrentDate = driver.findElement(By.xpath(currentDateXpath)).getText();

					if (expectedCurrentDate.equals(finalCurrentDate)) {
						successMessage("THE DUE DATE IS DISPLAYED CORRECTLY ON CLOSED TASK LIST AS EXPECTED.");

					} else {
						verificationFailedMessage("The due date is displayed incorrect on closed task list.");
					}

				} catch (Throwable t) {
					verificationFailedMessage("The due date is displayed incorrect on closed task list.");
				}

				// select the close option from the status drop down
				select("survey_task_closefilter_CSS", data.get("task_active_status"));

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				switchVerification("task_addedtask_smoke11_XPATH", "Smoke Test One Task Title",
						"The Smoke Test One Task Title is not displayed.");

				// VERIFY THE DUE DATE IS DISPLAYED CORRECT OR NOT ON ACTIVE TASK LIST
				title("VERIFY THE DUE DATE IS DISPLAYED CORRECT OR NOT ON ACTIVE TASK LIST");

				try {
					LocalDate futureDate = LocalDate.now().plusMonths(1);

					String expectedFutureDate = futureDate.toString();

					String futureDateXpath = "//td[text()='" + futureDate + "']";

					String finalFutureDate = driver.findElement(By.xpath(futureDateXpath)).getText();

					if (expectedFutureDate.equals(finalFutureDate)) {
						successMessage("THE DUE DATE IS DISPLAYED CORRECTLY ON ACTIVE TASK LIST AS EXPECTED.");

					} else {
						verificationFailedMessage("The due date is displayed incorrect on active task list.");
					}

				} catch (Throwable t) {
					verificationFailedMessage("The due date is displayed incorrect on active task list.");
				}

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// UPDATE THE DUE DATE OF THE TASK
			title("UPDATE THE DUE DATE OF THE TASK");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_smoke11_XPATH", "Smoke Test One Task Title",
						"The Smoke Test One Task Title is not displayed.");

				// click on the newly created task
				click("task_addedtask_smoke11_XPATH");

				// click on the change due date button
				click("task_tasktag_changeduedatebtn_CSS");

				// enter date which is 2 days before current date
				LocalDate pastDate = LocalDate.now().minusDays(2);
				String finalPastDate = pastDate.toString();
				type("task_tasktag_changeduedate_newduedate_XPATH", finalPastDate);

				// click on the new due date label
				click("task_tasktag_changeduedate_newduedatelbl_XPATH");

				// enter data in the reason for change field
				type("task_updateduedate_reasonforchangetxt_CSS", data.get("reason_for_change"));

				// click on the reschedule checkbox
				click("task_updateduedate_rescheduleckbx_CSS");

				// click on the apply button
				click("task_updateduedate_applybtn_CSS");

				// scroll up the screen

				scrollByPixel(-500);

				// enter the data in the task title field

				type("task_addtask_titletxt_CSS", data.get("task_title_updated"));

				// click on the update button
				click("survey_task_updatebtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// click on the back button
				click("task_backbtn_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title_updated"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				switchVerification("task_addedtask_smoke111_XPATH", "Smoke Test One Task Title After Due Date Update",
						"The Smoke Test One Task Title After Due Date Update is not displayed.");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// CLOSE THE TASK AFTER UPDATE THE DUE DATE AND VERIFY UPDATED DUE DATE IS
			// DISPLAYED OR NOT
			title("CLOSE THE TASK AFTER UPDATE THE DUE DATE AND VERIFY UPDATED DUE DATE IS DISPLAYED OR NOT");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_addedtask_smoke111_XPATH", "Smoke Test One Task Title After Due Date Update",
						"The Smoke Test One Task Title After Due Date Update is not displayed.");

				// click on the newly created task
				click("task_addedtask_smoke111_XPATH");

				// click on the close button
				click("task_closebtn_CSS");

				// click on the yes button of confirmation popup model
				click("task_closebtn_yesbtn_CSS");

				// scroll up the screen

				scrollByPixel(-400);

				// verify the close status is displayed or not
				switchVerification("task_closedstatus_XPATH", "Closed", "The Closed status is not displayed.");

				// click on the back button
				click("task_backbtn_CSS");

				// select the close option from the status drop down
				select("survey_task_closefilter_CSS", data.get("task_closed_status"));

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title_updated"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				switchVerification("task_addedtask_smoke111_XPATH", "Smoke Test One Task Title After Due Date Update",
						"The Smoke Test One Task Title After Due Date Update is not displayed.");

				// VERIFY THE DUE DATE IS DISPLAYED CORRECT OR NOT ON THE CLOSED TASK LIST
				title("VERIFY THE DUE DATE IS DISPLAYED CORRECT OR NOT ON THE CLOSED TASK LIST");

				try {
					LocalDate updatedDueDate = LocalDate.now().minusDays(2);

					String expectedUpdatedDueDate = updatedDueDate.toString();

					String updatedDueDateXpath = "//td[text()='" + updatedDueDate + "']";

					String finalUpdateDueDate = driver.findElement(By.xpath(updatedDueDateXpath)).getText();

					if (expectedUpdatedDueDate.equals(finalUpdateDueDate)) {
						successMessage(
								"THE DUE DATE IS DISPLAYED CORRECTLY AFTER UPDATED THE DUE DATE ON CLOSED TASK LIST AS EXPECTED.");

					} else {
						verificationFailedMessage(
								"The due date is displayed incorrect after updated the due date on closed task list.");
					}

				} catch (Throwable t) {
					verificationFailedMessage(
							"The due date is displayed incorrect after updated the due date on closed task list.");
				}

				// select the active option from the status drop down
				select("survey_task_closefilter_CSS", data.get("task_active_status"));

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				switchVerification("task_addedtask_smoke111_XPATH", "Smoke Test One Task Title After Due Date Update",
						"The Smoke Test One Task Title After Due Date Update is not displayed.");

				// VERIFY THE DUE DATE IS DISPLAYED CORRECT OR NOT ON THE ACTIVE TASK LIST
				title("VERIFY THE DUE DATE IS DISPLAYED CORRECT OR NOT ON THE ACTIVE TASK LIST");

				try {
					LocalDate updatedFutureDate = LocalDate.now().minusDays(2);

					LocalDate updatedPastFutureDate = updatedFutureDate.plusMonths(1);

					String expectedUpdatedFutureDate = updatedPastFutureDate.toString();

					String updatedFutureDateXpath = "//td[text()='" + updatedPastFutureDate + "']";

					String finalUpdatedFutureDate = driver.findElement(By.xpath(updatedFutureDateXpath)).getText();

					if (expectedUpdatedFutureDate.equals(finalUpdatedFutureDate)) {
						successMessage(
								"THE DUE DATE IS DISPLAYED CORRECTLY AFTER UPDATED THE DUE DATE ON ACTIVE TASK LIST AS EXPECTED.");

					} else {
						verificationFailedMessage(
								"The due date is displayed incorrect after updated the due date on active task list.");
					}

				} catch (Throwable t) {
					verificationFailedMessage(
							"The due date is displayed incorrect after updated the due date on active task list.");
				}

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// DELETE THE NEWLY CREATED SECOND TASK FROM THE ACTIVE TASK LIST
			title("DELETE THE NEWLY CREATED SECOND TASK FROM THE ACTIVE TASK LIST");

			try {
				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title_updated"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// click on the newly created task
				click("task_addedtask_smoke111_XPATH");

				// click on the delete button
				click("task_deletedtaskbtn_CSS");

				// synchronization
				explicitWait("task_deletedtaskbtn_confirmaiton_CSS");

				// click on the delete button of the confirmation popup
				click("task_deletedtaskbtn_confirmaiton_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// scroll up the screen

				scrollByPixel(-500);

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title_updated"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify the questionnaire deleted or not

				deleteVerification("task_addedtask_smoke111_XPATH", "Smoke Test One Task Title After Due Date Update");

			} catch (Throwable t) {

				verificationFailedMessage("");

			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// REOPEN THE NEWLY CREATED FIRST TASK FROM THE CLOSED TASK LIST
			title("REOPEN THE NEWLY CREATED FIRST TASK FROM THE CLOSED TASK LIST");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon
				click("taskicon_CSS");

				// select the close option from the status drop down
				select("survey_task_closefilter_CSS", data.get("task_closed_status"));

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				switchVerification("task_addedtask_smoke11_XPATH", "Smoke Test One Task Title",
						"The Smoke Test One Task Title is not displayed.");

				// click on the newly created task
				click("task_addedtask_smoke11_XPATH");

				// click on the reopen button
				click("survey_reopenbtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// scroll down the screen

				scrollByPixel(400);

				// wait for the element
				explicitWait("task_reopenedstatus_XPATH");

				// verify the Reopened status is displayed or not
				switchVerification("task_reopenedstatus_XPATH", "Reopened", "The Reopened status is not displayed.");

				// click on the update button
				click("survey_task_updatebtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// click on the back button
				click("task_backbtn_CSS");

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title_updated"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				switchVerification("task_addedtask_smoke111_XPATH", "Smoke Test One Task Title After Due Date Update",
						"The Smoke Test One Task Title After Due Date Update is not displayed.");

				// click on the newly created task
				click("task_addedtask_smoke111_XPATH");

				// click on the reopen button
				click("survey_reopenbtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				try {
					boolean reopenbtn = driver.findElement(By.cssSelector(OR.getProperty("survey_reopenbtn_CSS")))
							.isDisplayed();

					if (reopenbtn == true) {
						successMessage("THE REOPEN BUTTON IS DISPLAYED BECAUSE OF VALIDATION MESSAGE.");
					}

					else {

						verificationFailedMessage("The validation message is not displayed.");

					}

				} catch (Throwable t) {
					verificationFailedMessage("");
				}

				// click on the back button
				click("task_backbtn_CSS");

				// DELETE THE NEWLY CREATED FIRST TASK WHICH ARE REOPENED
				title("DELETE THE NEWLY CREATED FIRST TASK WHICH ARE REOPENED");

				// select the active option from the status drop down
				select("survey_task_closefilter_CSS", data.get("task_active_status"));

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				switchVerification("task_addedtask_smoke11_XPATH", "Smoke Test One Task Title",
						"The Smoke Test One Task Title is not displayed.");

				// click on the newly created task
				click("task_addedtask_smoke11_XPATH");

				// click on the delete button
				click("task_deletedtaskbtn_CSS");

				// synchronization
				explicitWait("task_deletedtaskbtn_confirmaiton_CSS");

				// click on the delete button of the confirmation popup
				click("task_deletedtaskbtn_confirmaiton_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// scroll up the screen
				scrollByPixel(-500);

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify the questionnaire deleted or not

				deleteVerification("task_addedtask_smoke11_XPATH", "Smoke Test One Task Title");

				// select the closed option from the status drop down
				select("survey_task_closefilter_CSS", data.get("task_closed_status"));

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the newly created task in the search field
				type("task_filter_CSS", data.get("task_title_updated"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				switchVerification("task_addedtask_smoke111_XPATH", "Smoke Test One Task Title After Due Date Update",
						"The Smoke Test One Task Title After Due Date Update is not displayed.");

				// click on the newly created task
				click("task_addedtask_smoke111_XPATH");

				// click on the reopen button
				click("survey_reopenbtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// scroll down the screen
				scrollByPixel(400);

				// wait for the element
				explicitWait("task_reopenedstatus_XPATH");

				// verify the Reopened status is displayed or not
				switchVerification("task_reopenedstatus_XPATH", "Reopened", "The Reopened status is not displayed.");

				// click on the update button
				click("survey_task_updatebtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// click on the back button
				click("task_backbtn_CSS");

				// DELETE THE NEWLY CREATED SECOND TASK WHICH ARE REOPENED
				title("DELETE THE NEWLY CREATED SECOND TASK WHICH ARE REOPENED");

				// select the active option from the status drop down
				select("survey_task_closefilter_CSS", data.get("task_active_status"));

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// type the newly created task in the search field
				type("task_filter_CSS", data.get("task_title_updated"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// click on the newly created task
				click("task_addedtask_smoke111_XPATH");

				// click on the delete button
				click("task_deletedtaskbtn_CSS");

				// synchronization
				explicitWait("task_deletedtaskbtn_confirmaiton_CSS");

				// click on the delete button of the confirmation popup
				click("task_deletedtaskbtn_confirmaiton_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// scroll up the screen
				scrollByPixel(-500);

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title_updated"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify the questionnaire deleted or not

				deleteVerification("task_addedtask_smoke111_XPATH", "Smoke Test One Task Title After Due Date Update");

			} catch (Throwable t) {
				verificationFailedMessage("");
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// synchronization
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

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

			// LOGIN IN ADMIN USER
			title("LOGIN IN ADMIN USER");

			// Enter the username

			type("username_MODEL", data.get("username_1"));

			// Enter the password
			type("password_MODEL", data.get("password_1"));

			// Clicking on the "Sign In" button
			click("signinbtn_BTNTEXT");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		} catch (Throwable t) {
			helper.questionnaireNotDisplayLogout(data);
		}

		// UPDATE THE SYSTEM COMPANY
		updateCompany(data);

		// wait for the element
		Thread.sleep(10000);

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
			verificationFailedMessage("");
		}
	}
}