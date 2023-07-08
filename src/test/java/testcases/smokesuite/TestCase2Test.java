package testcases.smokesuite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.relevantcodes.extentreports.LogStatus;

import base.Helper;
import base.TestBase;
import utilities.TestUtil;

public class RR5368Capreit1Test extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void rR5368Capreit1Test(Hashtable<String, String> data) throws IOException, InterruptedException {

		execution(data, "rR5368Capreit1Test");

		Helper helper = new Helper();

		// refresh the page
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.navigate().refresh();

		// PERFORM THE TEST CASES OF THE CAPREIT - PROPERTY MANAGER
		title("PERFORM THE TEST CASES OF THE CAPREIT - PROPERTY MANAGER");

		try {
			// LOGIN WITH PROPERTY MANAGER USER
			title("LOGIN WITH PROPERTY MANAGER USER");

			// wait for the element
			explicitWaitClickable("signinbtn_BTNTEXT");

			// Enter the username
			type("usernametxt_CSS", data.get("username"));

			// Enter the password
			type("passwordtxt_CSS", data.get("password"));

			// Clicking on the "Sign In" button
			click("signinbtn_BTNTEXT");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// UPDATE THE SYSTEM COMPANY
			updateCompany(data);

			// CREATE THE NEW INSPECTION AND CHECKLIST (PROPERTY WITH BUILDING) - PROPERTY
			// MANAGER
			title("CREATE THE NEW INSPECTION AND CHECKLIST (PROPERTY WITH BUILDING) - PROPERTY MANAGER");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the environmental icon from the property list page
				click("environmentalicon_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the burger menu
				click("menubtn_CSS");

				// wait for 5 seconds
				Thread.sleep(5000);

				// click on the checklist/inspection option from side menu
				click("checklist_sidemenu_XPATH");

				// wait for 3 seconds
				Thread.sleep(3000);

				// select property from the property drop down
				select("envreports_movereports_propertydd_CSS", data.get("property_leveldropdown"));

				// BUTTON NOT AVAILABLE
				// verify the new checklist button is displayed or not
				try {

					boolean newChecklistBtn = driver
							.findElement(By.cssSelector(OR.getProperty("checklist_newchecklistbtn_CSS"))).isDisplayed();

					consoleMessage("For Property List" + newChecklistBtn);

					if (newChecklistBtn == true) {

						// click on the new checklist button
						click("checklist_newchecklistbtn_CSS");

						// collect all the option from the checklist type option and verify
						List<WebElement> checklists = driver
								.findElements(By.cssSelector(OR.getProperty("checklist_checklisttypedd_list_CSS")));

						List<String> checklistList = new ArrayList<String>();

						for (WebElement e : checklists) {
							String str = e.getText();
							checklistList.add(str);
						}

						for (String s : checklistList) {
							if (s.equals("Capreit - Property Inspection (PM)")) {
								verificationFailedMessage(
										"The checklist is displayed even if the questionnaire is not available in property level.");
							} else {
								successMessage("THE EXPECTED CHECKLIST IS NOT DISPLAYED AS EXPECTED.");
							}
						}
					} else {
						successMessage("THE CHECKLIST BUTTON IS NOT DISPLAYED AS EXPECTED.");
					}
				} catch (Throwable t) {
					verificationFailed();
				}

				// click on the close button
				click("checklist_addchecklist_cancelbtn_XPATH");

				// wait for 3 seconds
				Thread.sleep(3000);

				// select building from the property drop down
				select("envreports_movereports_propertydd_CSS", data.get("building_leveldropdown"));

				// wait for 5 seconds
				Thread.sleep(5000);

				// BUTTON AVAILABLE
				// verify the new checklist button is displayed or not
				try {

					boolean newChecklistBtn = driver
							.findElement(By.cssSelector(OR.getProperty("checklist_newchecklistbtn_CSS"))).isDisplayed();

					consoleMessage("For Property List" + newChecklistBtn);

					if (newChecklistBtn == true) {

						// click on the new checklist button
						click("checklist_newchecklistbtn_CSS");

						// collect all the option from the checklist type option and verify
						List<WebElement> checklists = driver
								.findElements(By.cssSelector(OR.getProperty("checklist_checklisttypedd_list_CSS")));

						List<String> checklistList = new ArrayList<String>();

						for (WebElement e : checklists) {
							String str = e.getText();
							checklistList.add(str);
						}

						int count = 0;

						for (String s : checklistList) {
							if (s.equals("Capreit - Property Inspection (PM)")) {

								count = count + 1;

								successMessage("THE CHECKLIST IS DISPLAYED AS EXPECTED.");

							}
						}
						if (count == 0) {
							verificationFailedMessage(
									"The checklist is not displayed even if the questionnaire is available in building level.");
						}
					} else {
						verificationFailedMessage(
								"The checklist is not displayed even if the questionnaire is available in building level.");
					}
				} catch (Throwable t) {

					verificationFailed();
				}

				// select the questionnaire option from the checklist type dropdown
				select("checklist_addchecklist_checklisttypedd_CSS", data.get("questionnaire_checklist_title"));

				// enter data in the checklist title field
				type("checklist_addchecklist_titletxt_CSS", data.get("checklist_property_title"));

				// click on the save button
				click("checklist_addchecklist_savebtn_CSS");

				// verify questionnaire is selected correctly or not
				String covidtxt = driver.findElement(By.xpath(OR.getProperty("checklist_wizard_inspectiontitle_XPATH")))
						.getText();

				consoleMessage("COVID 19 INSTECTION TITLE : " + covidtxt);

				switchVerification("checklist_wizard_inspectiontitle_XPATH",
						"Test Questionnaire Property Title Smoke Capreit One",
						"The Test Questionnaire Property Title Smoke Capreit One is not displayed.");

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// synchronization
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// CREATE THE NEW INSPECTION AND CHECKLIST (PROPERTY WITHOUT BUILDING) -
			// PROPERTY MANAGER
			title("CREATE THE NEW INSPECTION AND CHECKLIST (PROPERTY WITHOUT BUILDING) - PROPERTY MANAGER");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_2"));

				// click on the environmental icon from the property list page
				click("environmentalicon_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the burger menu
				click("menubtn_CSS");

				// wait for 5 seconds
				Thread.sleep(5000);

				// click on the checklist/inspection option from side menu
				click("checklist_sidemenu_XPATH");

				// wait for 3 seconds
				Thread.sleep(3000);

				// select property from the property drop down
				select("envreports_movereports_propertydd_CSS", data.get("property_leveldropdown"));

				// BUTTON AVAILABLE
				// verify the new checklist button is displayed or not
				try {

					boolean newChecklistBtn = driver
							.findElement(By.cssSelector(OR.getProperty("checklist_newchecklistbtn_CSS"))).isDisplayed();

					consoleMessage("For Property List" + newChecklistBtn);

					if (newChecklistBtn == true) {

						// click on the new checklist button
						click("checklist_newchecklistbtn_CSS");

						// collect all the option from the checklist type option and verify
						List<WebElement> checklists = driver
								.findElements(By.cssSelector(OR.getProperty("checklist_checklisttypedd_list_CSS")));

						List<String> checklistList = new ArrayList<String>();

						for (WebElement e : checklists) {
							String str = e.getText();
							checklistList.add(str);
						}

						for (String s : checklistList) {
							if (s.equals("Capreit - Property Inspection (PM)")) {

								verificationFailedMessage("The excluded checklist is displayed.");
							} else {
								successMessage("THE EXCLUDED CHECKLIST IS NOT DISPLAYED AS EXPECTED.");
							}
						}
					} else {
						successMessage("THE EXCLUDED CHECKLIST IS NOT DISPLAYED AS EXPECTED.");
					}
				} catch (Throwable t) {

					verificationFailed();
				}

				// wait for the element
				explicitWaitClickable("checklist_addchecklist_cancelbtn_XPATH");

				// click on the close button
				click("checklist_addchecklist_cancelbtn_XPATH");

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// synchronization
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// SUBMIT THE COVID 19 INSPECTION - PROPERTY MANAGER
			title("SUBMIT THE COVID 19 INSPECTION - PROPERTY MANAGER");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the environmental icon from the property list page
				click("environmentalicon_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the burger menu
				click("menubtn_CSS");

				// wait for 5 seconds
				Thread.sleep(5000);

				// click on the checklist/inspection option from side menu
				click("checklist_sidemenu_XPATH");

				// wait for 3 seconds
				Thread.sleep(3000);

				// select property from the property drop down
				select("envreports_movereports_propertydd_CSS", data.get("building_leveldropdown"));

				// wait for 5 seconds
				Thread.sleep(5000);

				// enter newly created inspection in the search field
				type("task_listofchecklist_filterfield_CSS", data.get("checklist_property_title"));

				// click on the searched inspection
				click("questionnaire_checklist_createdinspectioncovid19_XPATH");

				// click on the switch to wizard mode button
				click("checklist_classicviewbtn_CSS");

				// click on the yes option of the 1st question
				click("checklist_wizard_yesoption_CSS");

				// click on the next button from the 1st question
				click("checklist_wizard_nextbtn_CSS");

				// click on the yes option of the 2nd question
				click("checklist_wizard_yesoption_CSS");

				// click on the next button from the 2nd question
				click("checklist_wizard_nextbtn_CSS");

				// click on the next button from the 3rd question
				click("checklist_wizard_nextbtn_CSS");

				// click on the next button from the 4th question
				click("checklist_wizard_nextbtn_CSS");

				// click on the next button from the 5th question
				click("checklist_wizard_nextbtn_CSS");

				// click on the submit button
				click("checklist_submitbtn_CSS");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				try {
					// verify inspection submitted or not
					String submittedText = driver
							.findElement(By.xpath(OR.getProperty("checklist_wizard_submittedtxt_XPATH"))).getText();

					if (submittedText.equals("The inspection has been submitted successfully")) {
						successMessage("THE INSPECTION SUBMITTED SUCCESSFULLY.");
					}
				} catch (Throwable t) {
					verificationFailedMessage("The inspection is not submitted.");
				}

				// click on the back button
				click("questionnaire_checklist_backbtn_CSS");

				// select the approved option from the status dropdown
				select("checklist_statusdd_CSS", data.get("status_dd"));

				// enter newly created inspection in the search field
				type("task_listofchecklist_filterfield_CSS", data.get("checklist_property_title"));

				// verify submitted inspection is displayed or not
				switchVerification("questionnaire_checklist_createdinspectioncovid19_XPATH",
						"Test Questionnaire Property Title Smoke Capreit One",
						"The Test Questionnaire Property Title Smoke Capreit One is not displayed.");

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// synchronization
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// GENERATE THE AUTO TASK - PROPERTY MANAGER USER
			title("GENERATE THE AUTO TASK - PROPERTY MANAGER USER");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the environmental icon from the property list page
				click("environmentalicon_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the burger menu
				click("menubtn_CSS");

				// wait for 5 seconds
				Thread.sleep(5000);

				// click on the checklist/inspection option from side menu
				click("checklist_sidemenu_XPATH");

				// select property from the building drop down
				select("envreports_movereports_propertydd_CSS", data.get("building_leveldropdown"));

				// wait for 5 seconds
				Thread.sleep(5000);

				// select the approved option from the status dropdown
				select("checklist_statusdd_CSS", data.get("status_dd"));

				// enter newly created inspection in the search field
				type("task_listofchecklist_filterfield_CSS", data.get("checklist_property_title"));

				// click on the action icon
				click("checklist_actionicon_CSS");

				// click on the reopen option
				click("checklist_wizard_reopenoption_CSS");

				// wait for the element
				explicitWaitClickable("checklist_wizard_reopenbtn_CSS");

				// click on the reopen button
				click("checklist_wizard_reopenbtn_CSS");

				// select the open option from the status dropdown
				select("checklist_statusdd_CSS", data.get("status_dd_open"));

				// enter newly created inspection in the search field
				type("task_listofchecklist_filterfield_CSS", data.get("checklist_property_title"));

				// click on the searched inspection
				click("questionnaire_checklist_createdinspectioncovid19_XPATH");

				// click on the no answer of the 1st question
				click("survey_noanswerbtn_1_CSS");

				// wait for 3 seconds
				Thread.sleep(3000);

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the Home button
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// VERIFY AUTO GENERATED TASK IS DISPLAYED OR NOT - PROPERTY MANAGER USER
			title("VERIFY AUTO GENERATED TASK IS DISPLAYED OR NOT - PROPERTY MANAGER USER");

			try {
				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the task icon of the respective property
				click("taskicon_CSS");

				// wait for 5 seconds
				Thread.sleep(5000);

				// select property from the building drop down
				select("task_addedtask_propertybuildingdd_CSS", data.get("building_leveldropdown"));

				// click on the clear button
				click("task_createdtask_clearbtn_XPATH");

				// enter the data in the search field
				type("task_filter_CSS", data.get("task_title"));

				// click on the search button
				click("task_createdtask_searchbtn_XPATH");

				// verify the auto generated task is displayed or not
				switchVerification("task_createdtask_covid19autotask_XPATH", "Get safety plan on site",
						"The Get safety plan on site is not displayed.");

				// click on the auto generated task
				click("task_createdtask_covid19autotask_XPATH");

				// verify correct task title is displayed correct or not
				try {
					String taskTitle = driver.findElement(By.cssSelector(OR.getProperty("task_tasktitletxt_CSS")))
							.getAttribute("value");

					if (taskTitle.equals("Get safety plan on site")) {
						successMessage("THE CORRECT TASK TITLE IS DISPLAYED.");
					} else {
						verificationFailedMessage("The task title is not displayed correctly.");
					}

				} catch (Throwable t) {
					verificationFailedMessage("The task title is not displayed correctly.");
				}

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the Home button
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// ADD THE TASK IN COVID 19 INSPECTION - PROPERTY MANAGER
			title("ADD THE TASK IN COVID 19 INSPECTION - PROPERTY MANAGER");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the environmental icon from the property list page
				click("environmentalicon_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the burger menu
				click("menubtn_CSS");

				// wait for 5 seconds
				Thread.sleep(5000);

				// click on the checklist/inspection option from side menu
				click("checklist_sidemenu_XPATH");

				// wait for the element
				explicitWaitClickable("envreports_movereports_propertydd_CSS");

				// select property from the property drop down
				select("envreports_movereports_propertydd_CSS", data.get("building_leveldropdown"));

				// wait for 3 seconds
				Thread.sleep(3000);

				// enter newly created inspection in the search field
				type("task_listofchecklist_filterfield_CSS", data.get("checklist_property_title"));

				// click on the searched inspection
				click("questionnaire_checklist_createdinspectioncovid19_XPATH");

				// wait for 3 seconds
				Thread.sleep(3000);

				// click on the switch to wizard mode button
				click("checklist_classicviewbtn_CSS");

				// wait for 3 seconds
				Thread.sleep(3000);

				// click on the task tab
				click("inspectionchecklist_tasktab_XPATH");

				// click on the new task button
				click("inspectionchecklist_newtaskbtn_XPATH");

				// click on the task type field
				click("task_permission_task_tasktypetxt_CSS");

				// enter task type in the search field
				type("inspectionchecklist_tasktagsearchfield_CSS", data.get("tasktag"));

				// click on the searched task tag
				click("inspectionchecklist_tasktagsearchedresult_XPATH");

				// verify correct task title is displayed correct or not
				try {
					String taskTitle1 = driver.findElement(By.cssSelector(OR.getProperty("task_addtask_titletxt_CSS")))
							.getAttribute("value");

					if (taskTitle1.equals("Electrical (Light Fixture)")) {
						successMessage("THE CORRECT TASK TITLE IS DISPLAYED.");
					} else {
						verificationFailedMessage("The task title is not displayed correctly.");
					}

				} catch (Throwable t) {
					verificationFailedMessage("The task title is not displayed correctly.");
				}

				// scroll down the screen

				scrollByPixel(500);

				// click on the save task button
				click("survey_task_savebtn_CSS");

				// verify newly created task is displayed or not
				switchVerification("inspectionchecklist_tasktagcreatedtask_XPATH", "Electrical (Light Fixture)",
						"The Electrical (Light Fixture) is not displayed.");

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the Home button
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// ADD COMMENT IN THE NEWLY CREATED TASK - PROPERTY MANAGER USER
			title("ADD COMMENT IN THE NEWLY CREATED TASK - PROPERTY MANAGER USER");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the environmental icon from the property list page
				click("environmentalicon_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the burger menu
				click("menubtn_CSS");

				// wait for 5 seconds
				Thread.sleep(5000);

				// click on the checklist/inspection option from side menu
				click("checklist_sidemenu_XPATH");

				// wait for 3 seconds
				Thread.sleep(3000);

				// select property from the property drop down
				select("envreports_movereports_propertydd_CSS", data.get("building_leveldropdown"));

				// wait for 5 seconds
				Thread.sleep(5000);

				// enter newly created inspection in the search field
				type("task_listofchecklist_filterfield_CSS", data.get("checklist_property_title"));

				// click on the searched inspection
				click("questionnaire_checklist_createdinspectioncovid19_XPATH");

				// click on the switch to wizard mode button
				click("checklist_classicviewbtn_CSS");

				// click on the task tab
				click("inspectionchecklist_tasktab_XPATH");

				// scroll down the screen
				scrollByPixel(400);

				// click on the newly created task
				click("inspectionchecklist_tasktagcreatedtask_XPATH");

				// scroll down the screen
				scrollByPixel(400);

				// click on the comment tab
				click("propertyproject_mu_addedrepair_taskcommenttab_CSS");

				// scroll down the screen
				scrollByPixel(500);

				// click on the add comment button
				click("task_addcommentbtn_CSS");

				// enter the text in the comment field
				type("task_addtask_commentstab_commenttxt_CSS", data.get("comment1"));

				// click on the add button for save the entered comment
				click("task_comment_addcommentbtn_CSS");

				// wait for the element
				explicitWait("task_addtask_commentstab_savedcomment_capreitone_XPATH");

				// verify the saved comment is displayed or not
				switchVerification("task_addtask_commentstab_savedcomment_capreitone_XPATH",
						"Testing Purpose Comment Capreit One",
						"The Testing Purpose Comment Capreit One is not displayed.");

				// click on the update button
				click("survey_task_updatebtn_CSS");

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the Home button
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// LOGOUT FROM PROPERTY MANAGER USER
			title("LOGOUT FROM PROPERTY MANAGER USER");

			// wait for the 5 seconds
			Thread.sleep(5000);

			// click on the settings icon from the top of the screen
			click("questionnaire_settingicon_CSS");

			// wait for the element
			explicitWaitClickable("sidemenu_logout_CSS");

			// click on the logout option from the side menu
			click("sidemenu_logout_CSS");

			// wait for the element
			explicitWaitClickable("signinbtn_BTNTEXT");

			// LOGIN IN WITH RESPORTAL ENTRY USER
			title("LOGIN IN WITH RESPORTAL ENTRY USER");

			// Enter the username
			type("username_MODEL", data.get("username_2"));

			// Enter the password
			type("password_MODEL", data.get("password_2"));

			// Clicking on the "Sign In" button
			click("signinbtn_BTNTEXT");

			// wait for the element
			explicitWait("mytask_title_XPATH");

			// verify the property list
			switchVerification("mytask_title_XPATH", "Title", "The Title is not displayed.");

			// UPDATE THE SYSTEM COMPANY
			title("UPDATE THE SYSTEM COMPANY");

			try {
				// wait for the 5 seconds
				Thread.sleep(5000);

				// click on the settings icon from the top of the screen
				click("questionnaire_settingicon_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the Switch System Company option from the side menu
				click("ssc_sidemenubtn_CSS");

				// click on the switch system company dropdown
				click("ssc_systemcompanydd_CSS");

				// select the system company from the system company dropdown
				select("ssc_systemcompanydd_CSS", data.get("system_company_1"));

				// click on system company dropdown
				click("ssc_systemcompanydd_CSS");

				// click on the select button
				click("ssc_selectbtn_BTNTEXT");

				// wait for the element
				explicitWait("mytask_title_XPATH");

				// verify the property list
				switchVerification("mytask_title_XPATH", "Title", "The Title is not displayed.");

			} catch (Throwable t) {
				verificationFailed();
			}

			// ADD THE PO NUMBER IN THE NEWLY CREATED TASK - RESPORTAL ENTRY USER
			title("ADD THE PO NUMBER IN THE NEWLY CREATED TASK - RESPORTAL ENTRY USER");

			try {

				// click on the clear button
				click("mytaskdashboard_createdtask_clearbtn_XPATH");

				// enter newly created task in search field
				type("survey_task_searchfield_CSS", data.get("tasktag"));

				// click on the search button
				click("mytaskdashboard_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_createdtask_rpecreatedtask_XPATH", "Electrical (Light Fixture)",
						"The Electrical (Light Fixture) is not displayed.");

				// click on the newly created task
				click("task_createdtask_rpecreatedtask_XPATH");

				// scroll down the screen
				scrollByPixel(400);

				// click on the po/warranty tab
				click("task_tasktag_powarrantytab_CSS");

				// enter the po number
				type("task_tasktag_ponumber_CSS", data.get("task_ponumber"));

				// click on the update button
				click("survey_task_updatebtn_CSS");

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("mytask_title_XPATH");

			// verify the property list
			switchVerification("mytask_title_XPATH", "Title", "The Title is not displayed.");

			// VERIFY NEWLY CREATED TASK IS DISPLAYED OR NOT - RESPORTAL ENTRY USER
			title("VERIFY NEWLY CREATED TASK IS DISPLAYED OR NOT - RESPORTAL ENTRY USER");

			try {

				// click on the clear button
				click("mytaskdashboard_createdtask_clearbtn_XPATH");

				// enter newly created task in search field
				type("survey_task_searchfield_CSS", data.get("tasktag"));

				// click on the search button
				click("mytaskdashboard_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_createdtask_rpecreatedtask_XPATH", "Electrical (Light Fixture)",
						"The Electrical (Light Fixture) is not displayed.");

				// VERIFY ADDED COMMENT IS DISPLAYED OR NOT - RESPORTAL ENTRY USER
				title("VERIFY ADDED COMMENT IS DISPLAYED OR NOT - RESPORTAL ENTRY USER");

				// wait for the element
				Thread.sleep(5000);

				// click on the newly created task
				click("task_createdtask_rpecreatedtask_XPATH");

				// scroll down the screen
				scrollByPixel(400);

				// click on the comment tab
				click("propertyproject_mu_addedrepair_taskcommenttab_CSS");

				// scroll down the screen
				scrollByPixel(500);

				// verify the previously added task is displayed or not
				switchVerification("survey_task_createdtasktext_XPATH", "Testing Purpose Comment Capreit One",
						"The Testing Purpose Comment Capreit One is not displayed.");

				// click on the add comment button
				click("task_addcommentbtn_CSS");

				// enter the text in the comment field
				type("task_addtask_commentstab_commenttxt_CSS", data.get("updated_comment1"));

				// click on the add button for save the entered comment
				click("task_comment_addcommentbtn_CSS");

				// wait for the element
				explicitWait("task_addtask_savedcomment_capreitone_rpe_XPATH");

				// verify the saved comment is displayed or not
				switchVerification("task_addtask_savedcomment_capreitone_rpe_XPATH",
						"ResPortal Entry Testing Purpose Comment Capreit One",
						"The ResPortal Entry Testing Purpose Comment Capreit One is not displayed.");

				// click on the update button
				click("survey_task_updatebtn_CSS");

				// click on the back button
				click("task_backbtn2_CSS");

				// scroll up the screen
				scrollByPixel(-500);

				// click on the clear button
				click("mytaskdashboard_createdtask_clearbtn_XPATH");

				// enter newly created task in search field
				type("survey_task_searchfield_CSS", data.get("tasktag"));

				// click on the search button
				click("mytaskdashboard_createdtask_searchbtn_XPATH");

				// verify newly created task is displayed or not
				switchVerification("task_createdtask_rpecreatedtask_XPATH", "Electrical (Light Fixture)",
						"The Electrical (Light Fixture) is not displayed.");

			} catch (Throwable t) {
				verificationFailed();
			}

			// COMPLETE THE NEWLY CREATED TASK - RESPORTAL ENTRY USER
			title("COMPLETE THE NEWLY CREATED TASK - RESPORTAL ENTRY USER");

			try {

				// wait for the element
				Thread.sleep(5000);

				// click on the newly created task
				click("task_createdtask_rpecreatedtask_XPATH");

				// SET TASK AS A IN PROGRESS
				consoleMessage("SET TASK AS A IN PROGRESS");

				// click on the start button
				click("propertyproject_mu_repair_startbtn_CSS");

				// scroll up the screen
				scrollByPixel(-500);

				// verify in progress status is display
				String inprogressStatus = driver
						.findElement(By.xpath(OR.getProperty("task_inprogress_statuslbl_XPATH"))).getText();
				String finalInprogressStatus = inprogressStatus.trim();
				switchVerification("task_inprogress_statuslbl_XPATH", finalInprogressStatus,
						"The " + finalInprogressStatus + " is not displayed.");

				// SET TASK AS A PENDING
				consoleMessage("SET TASK AS A PENDING");

				// click on the pending button
				click("task_pendingbtn_CSS");

				// scroll up the screen
				scrollByPixel(-500);

				// verify in pending status is display
				String pendingStatus = driver.findElement(By.xpath(OR.getProperty("task_pendingstatus_XPATH")))
						.getText();
				String finalPendingStatus = pendingStatus.trim();
				switchVerification("task_pendingstatus_XPATH", finalPendingStatus,
						"The " + finalPendingStatus + " is not displayed.");

				// SET TASK AS A APPROVED
				consoleMessage("SET TASK AS A APPROVED");

				// click on the start button
				click("propertyproject_mu_repair_startbtn_CSS");

				// click on the approve button
				click("task_approvebtn_CSS");

				// scroll up the screen
				scrollByPixel(-500);

				// verify in approved status is display
				String approvedStatus = driver.findElement(By.xpath(OR.getProperty("task_approvedstatus_XPATH")))
						.getText();
				String finalApprovedStatus = approvedStatus.trim();
				switchVerification("task_approvedstatus_XPATH", finalApprovedStatus,
						"The " + finalApprovedStatus + " is not displayed.");

				// SET TASK AS A CLOSED
				consoleMessage("SET TASK AS A CLOSED");

				// click on the cancel button
				click("task_closebtn_CSS");

				// click on the yes button of the confirmation popup
				click("task_closebtn_yesbtn_CSS");

				// click on the back button
				click("task_backbtn2_CSS");

				// click on the clear button
				click("mytaskdashboard_createdtask_clearbtn_XPATH");

				// enter newly created task in search field
				type("survey_task_searchfield_CSS", data.get("tasktag"));

				// click on the search button
				click("mytaskdashboard_createdtask_searchbtn_XPATH");

				// verify in closed task is display or not
				deleteVerification("task_createdtask_rpecreatedtask_XPATH", "Electrical (Light Fixture)");

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("mytask_title_XPATH");

			// verify the property list
			switchVerification("mytask_title_XPATH", "Title", "The Title is not displayed.");

			// LOGOUT FROM THE RESPORTAL ENTRY USER
			title("LOGOUT FROM THE RESPORTAL ENTRY USER");

			try {

				// wait for the 5 seconds
				Thread.sleep(5000);

				// click on the settings icon from the top of the screen
				click("questionnaire_settingicon_CSS");

				// wait for the element
				explicitWaitClickable("sidemenu_logout_CSS");

				// click on the logout option from the side menu
				click("sidemenu_logout_CSS");

				// LOGIN WITH PROPERTY MANAGER USER
				title("LOGIN WITH PROPERTY MANAGER USER");

				// wait for the element
				explicitWaitClickable("signinbtn_BTNTEXT");

				// Enter the username
				type("usernametxt_CSS", data.get("username"));

				// Enter the password
				type("passwordtxt_CSS", data.get("password"));

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

			// COMPLETE THE AUTO GENERATED TASK - PROPERTY MANAGER USER
			title("COMPLETE THE AUTO GENERATED TASK - PROPERTY MANAGER USER");

			try {

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the environmental icon from the property list page
				click("environmentalicon_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the burger menu
				click("menubtn_CSS");

				// wait for 5 seconds
				Thread.sleep(5000);

				// click on the checklist/inspection option from side menu
				click("checklist_sidemenu_XPATH");

				// wait for 3 seconds
				Thread.sleep(3000);

				// select property from the property drop down
				select("envreports_movereports_propertydd_CSS", data.get("building_leveldropdown"));

				// wait for 3 seconds
				Thread.sleep(3000);

				// enter newly created inspection in the search field
				type("task_listofchecklist_filterfield_CSS", data.get("checklist_property_title"));

				// click on the searched inspection
				click("questionnaire_checklist_createdinspectioncovid19_XPATH");

				// click on the switch to wizard mode button
				click("checklist_classicviewbtn_CSS");

				// click on the task tab
				click("inspectionchecklist_tasktab_XPATH");

				// scroll down the screen
				scrollByPixel(500);

				// click on the auto generated task
				click("questionnaire_checklist_autogeneratedtask_capreit1_XPATH");

				// SET TASK AS A IN PROGRESS
				consoleMessage("SET TASK AS A IN PROGRESS");

				// wait for the element
				explicitWaitClickable("propertyproject_mu_repair_startbtn_CSS");

				// click on the start button
				click("propertyproject_mu_repair_startbtn_CSS");

				// SET TASK AS A PENDING
				consoleMessage("SET TASK AS A PENDING");

				// wait for the element
				explicitWaitClickable("task_pendingbtn_CSS");

				// click on the pending button
				click("task_pendingbtn_CSS");

				// SET TASK AS A APPROVED
				consoleMessage("SET TASK AS A APPROVED");

				// wait for the element
				explicitWaitClickable("propertyproject_mu_repair_startbtn_CSS");

				// click on the start button
				click("propertyproject_mu_repair_startbtn_CSS");

				// wait for the element
				explicitWaitClickable("task_approvebtn_CSS");

				// click on the approve button
				click("task_approvebtn_CSS");

				// SET TASK AS A CLOSED
				consoleMessage("SET TASK AS A CLOSED");

				// click on the back button
				click("task_addtask_backbtn_inspectiontask_XPATH");

				// click on the action icon of the approved task
				click("task_autotask_approvedlbl_actionicon_XPATH");

				// click on the complete task option
				click("task_autotask_approvedlbl_actionicon_closeoption_XPATH");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// verify in closed status is display
				switchVerification("task_autotask_closedlbl_XPATH", "Closed", "The closed status is not displayed.");

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// synchronization
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// DELETE THE LINKED TASKS - PROPERTY MANAGER USER
			title("DELETE THE LINKED TASKS - PROPERTY MANAGER USER");

			try {
				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the environmental icon from the property list page
				click("environmentalicon_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the burger menu
				click("menubtn_CSS");

				// wait for 5 seconds
				Thread.sleep(5000);

				// click on the checklist/inspection option from side menu
				click("checklist_sidemenu_XPATH");

				// wait for the element
				explicitWaitClickable("envreports_movereports_propertydd_CSS");

				// select property from the property drop down
				select("envreports_movereports_propertydd_CSS", data.get("building_leveldropdown"));

				// wait for 3 seconds
				Thread.sleep(3000);

				// enter newly created inspection in the search field

				type("task_listofchecklist_filterfield_CSS", data.get("checklist_property_title"));

				// click on the searched inspection
				click("questionnaire_checklist_createdinspectioncovid19_XPATH");

				// wait for 3 seconds
				Thread.sleep(3000);

				// click on the switch to wizard mode button
				click("checklist_classicviewbtn_CSS");

				// wait for 3 seconds
				Thread.sleep(3000);

				// click on the task tab
				click("inspectionchecklist_tasktab_XPATH");

				// click on the action icon of the approved task
				click("task_addtask_approvedlbl_actionicon_XPATH");

				// click on the reopen option
				click("task_addtask_approvedlbl_actionicon_reopenoption_XPATH");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// click on the action icon of the approved task
				click("task_addtask_approvedlbl_actionicon_XPATH");

				// click on the delete task option
				click("task_addtask_approvedlbl_actionicon_deleteoption_XPATH");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// click on the action icon of the task
				click("task_autotask_approvedlbl_actionicon_XPATH");

				// click on the reopen option of the task
				click("task_autotask_actionicon_reopenoption_XPATH");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the action icon of the task
				click("task_autotask_approvedlbl_actionicon_XPATH");

				// click on the delete task option
				click("task_autotask_actionicon_deleteoption_XPATH");

				// wait for the element
				explicitWaitClickable("closetoastmsg_CSS");

				// click on the toaster close button
				click("closetoastmsg_CSS");

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// synchronization
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// DELETE THE INSTANCE - PROPERTY MANAGER USER
			title("DELETE THE INSTANCE - PROPERTY MANAGER USER");

			try {
				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// click on the environmental icon from the property list page
				click("environmentalicon_CSS");

				// wait for the element
				Thread.sleep(5000);

				// click on the burger menu
				click("menubtn_CSS");

				// wait for 5 seconds
				Thread.sleep(5000);

				// click on the checklist/inspection option from side menu
				click("checklist_sidemenu_XPATH");

				// wait for the element
				explicitWaitClickable("envreports_movereports_propertydd_CSS");

				// select property from the property drop down
				select("envreports_movereports_propertydd_CSS", data.get("building_leveldropdown"));

				// wait for 3 seconds
				Thread.sleep(3000);

				// enter newly created inspection in the search field

				type("task_listofchecklist_filterfield_CSS", data.get("checklist_property_title"));

				// click on the action icon
				click("checklist_actionicon_CSS");

				// click on the delete option
				click("checklist_deleteoption_CSS");

				// click on the delete button
				click("checklist_deletebtn_CSS");

				// verify deleted inspection
				deleteVerification("questionnaire_checklist_createdinspectioncovid19_XPATH",
						"Test Questionnaire Property Title Smoke Capreit One");

			} catch (Throwable t) {
				verificationFailed();
			}

			// click on the home icon from the top of the screen
			click("questionnaire_homeburgermenubtn_hide_CSS");

			// wait for the element
			explicitWait("propertylist_title_XPATH");

			// verify the property list
			switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

			// wait for the 5 seconds
			Thread.sleep(5000);

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

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_hide_CSS");

		// synchronization
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		try {
			// wait for the 5 seconds
			Thread.sleep(5000);

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