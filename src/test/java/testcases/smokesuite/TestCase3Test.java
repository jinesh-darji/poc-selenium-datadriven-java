package testcases.smokesuite;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.Helper;
import base.TestBase;
import utilities.TestUtil;

public class RR5595Morguard1Test extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void rR5595Morguard1Test(Hashtable<String, String> data) throws IOException, InterruptedException {

		execution(data, "rR5595Morguard1Test");

		Helper helper = new Helper();

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

		// MORGUARD INTERNAL AND EXTERNAL USERS SHOULD BE LISTED IN THE USER
		// ADMINISTRATION SECTION
		title("MORGUARD INTERNAL AND EXTERNAL USERS SHOULD BE LISTED IN THE USER ADMINISTRATION SECTION");

		try {

			// wait for 3 seconds
			Thread.sleep(5000);

			// click on the settings icon from the top of the screen
			click("questionnaire_settingicon_CSS");

			// wait for 3 seconds
			Thread.sleep(3000);

			// click on the Administration option from the side menu
			click("questionnaire_administrationoption_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the users tab
			click("questionnaire_usersoption_XPATH");

			// enter the name of the external user

			type("users_filtertxt_CSS", data.get("external_user"));

			// verify the external user is display or not
			switchVerification("users_username_externaluser_XPATH", "jineshexternal",
					"The jineshexternal is not displayed.");

			// enter the name of the internal user

			type("users_filtertxt_CSS", data.get("internal_user"));

			// verify the internal user is display or not
			switchVerification("users_username_internaluser_XPATH", "jineshinternal",
					"The jineshinternal is not displayed.");

		} catch (Throwable t) {
			verificationFailed();
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_CSS");

		// wait for the element
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		// VERIFY ROLES THAT TAKE PRECEDENCE SHOULD HAVE A LOWER RANK THAN THE OTHERS
		title("VERIFY ROLES THAT TAKE PRECEDENCE SHOULD HAVE A LOWER RANK THAN THE OTHERS");

		try {

			// wait for 3 seconds
			Thread.sleep(5000);

			// click on the settings icon from the top of the screen
			click("questionnaire_settingicon_CSS");

			// wait for 3 seconds
			Thread.sleep(3000);

			// click on the Administration option from the side menu
			click("questionnaire_administrationoption_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the role tab
			click("questionnaire_rolesoption_XPATH");

			// VERIFY RANK OF THE ENVIRO-ADMIN ROLE
			title("VERIFY RANK OF THE ENVIRO-ADMIN ROLE");

			// scroll down the screen
			scrollByPixel(200);

			try {
				String pages = driver.findElement(By.cssSelector("strong")).getText();
				String[] arrOfStr = pages.split(" ");
				String pageno = arrOfStr[3];
				int pagecount = Integer.parseInt(pageno);

				outerloop: for (int i = 0; i < pagecount; i++) {

					List<WebElement> roles = driver.findElements(By.xpath("//tr//div"));
					int rolesnum = roles.size();

					for (int j = 0; j < rolesnum; j++) {

						WebElement rolestext = roles.get(j);
						String getrole = rolestext.getText();

						if (getrole.equalsIgnoreCase("Enviro-Admins")) {

							// verify rank of the enviro-admins role is displayed correct or not
							String enviro_admin = (driver
									.findElement(
											By.xpath(OR.getProperty("task_permission_role_enviroadmin_rank_XPATH")))
									.getText()).trim();

							if (enviro_admin.equals("1")) {
								successMessage("THE RANK OF THE ENVIRO-ADMIN ROLE IS DISPLAYED CORRECTLY.");

							} else {
								verificationFailed();
							}

							// VERIFY PERMISSION OF THE ADMINISTRATION TO THE ENVIRO-ADMIN ROLE
							helper.rolePermissionValidation("Enviro-Admins", "ADMINISTRATION", "2", "green",
									"checkmark");

							// VERIFY PERMISSION OF THE PROPERTY EDIT TO THE ENVIRO-ADMIN ROLE
							helper.rolePermissionValidation("Enviro-Admins", "PROPERTY EDIT", "3", "green",
									"checkmark");

							// VERIFY PERMISSION OF THE SEES ALL PROPERTIES TO THE ENVIRO-ADMIN ROLE
							helper.rolePermissionValidation("Enviro-Admins", "SEES ALL PROPERTIES", "4", "green",
									"checkmark");

							// VERIFY PERMISSION OF THE SEES ALL TASKS TO THE ENVIRO-ADMIN ROLE
							helper.rolePermissionValidation("Enviro-Admins", "SEES ALL TASKS", "5", "red", "close");

							// VERIFY PERMISSION OF THE WEEKLY TASK EMAILS TO THE ENVIRO-ADMIN ROLE
							helper.rolePermissionValidation("Enviro-Admins", "WEEKLY TASK EMAILS", "6", "red", "close");

							// VERIFY PERMISSION OF THE DEFAULT ROLE TO THE ENVIRO-ADMIN ROLE
							helper.rolePermissionValidation("Enviro-Admins", "DEFAULT ROLE", "7", "red", "close");

							break outerloop;
						}

					}

					// click on the next button
					click("task_permission_roles_nextbtn_CSS");

				}
			} catch (Throwable t) {
				verificationFailed();
			}

			// VERIFY RANK OF THE ENVIRO-MANAGERS ROLE
			title("VERIFY RANK OF THE ENVIRO-MANAGERS ROLE");

			// scroll up the screen
			scrollByPixel(-200);

			// click on the administration option
			click("administration_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the role tab
			click("questionnaire_rolesoption_XPATH");

			// scroll down the screen
			scrollByPixel(200);

			try {
				String pages = driver.findElement(By.cssSelector("strong")).getText();
				String[] arrOfStr = pages.split(" ");
				String pageno = arrOfStr[3];
				int pagecount = Integer.parseInt(pageno);

				outerloop: for (int i = 0; i < pagecount; i++) {

					List<WebElement> roles = driver.findElements(By.xpath("//tr//div"));
					int rolesnum = roles.size();

					for (int j = 0; j < rolesnum; j++) {

						WebElement rolestext = roles.get(j);
						String getrole = rolestext.getText();

						if (getrole.equalsIgnoreCase("Enviro-Managers")) {

							// verify rank of the Enviro-Managers role is displayed correct or not
							String enviro_manager = (driver
									.findElement(
											By.xpath(OR.getProperty("task_permission_role_enviromanager_rank_XPATH")))
									.getText()).trim();

							if (enviro_manager.equals("25")) {

								successMessage("THE RANK OF THE ENVIRO-MANAGERS ROLE IS DISPLAYED CORRECTLY.");

							} else {
								verificationFailed();
							}

							// VERIFY PERMISSION OF THE ADMINISTRATION TO THE ENVIRO-MANAGERS ROLE
							helper.rolePermissionValidation("Enviro-Managers", "ADMINISTRATION", "2", "red", "close");

							// VERIFY PERMISSION OF THE PROPERTY EDIT TO THE ENVIRO-MANAGERS ROLE
							helper.rolePermissionValidation("Enviro-Managers", "PROPERTY EDIT", "3", "red", "close");

							// VERIFY PERMISSION OF THE SEES ALL PROPERTIES TO THE ENVIRO-MANAGERS ROLE
							helper.rolePermissionValidation("Enviro-Managers", "SEES ALL PROPERTIES", "4", "green",
									"checkmark");

							// VERIFY PERMISSION OF THE SEES ALL TASKS TO THE ENVIRO-MANAGERS ROLE
							helper.rolePermissionValidation("Enviro-Managers", "SEES ALL TASKS", "5", "red", "close");

							// VERIFY PERMISSION OF THE WEEKLY TASK EMAILS TO THE ENVIRO-MANAGERS ROLE
							helper.rolePermissionValidation("Enviro-Managers", "WEEKLY TASK EMAILS", "6", "red",
									"close");

							// VERIFY PERMISSION OF THE DEFAULT ROLE TO THE ENVIRO-MANAGERS ROLE
							helper.rolePermissionValidation("Enviro-Managers", "DEFAULT ROLE", "7", "red", "close");

							break outerloop;
						}
					}

					// click on the next button
					click("task_permission_roles_nextbtn_CSS");

				}
			} catch (Throwable t) {
				verificationFailed();
			}

			// VERIFY RANK OF THE ENVIRO-CONSULTANTS ROLE
			title("VERIFY RANK OF THE ENVIRO-CONSULTANTS ROLE");

			// scroll up the screen
			scrollByPixel(-200);

			// click on the administration option
			click("administration_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the role tab
			click("questionnaire_rolesoption_XPATH");

			// scroll down the screen
			scrollByPixel(200);

			try {
				String pages = driver.findElement(By.cssSelector("strong")).getText();
				String[] arrOfStr = pages.split(" ");
				String pageno = arrOfStr[3];
				int pagecount = Integer.parseInt(pageno);

				outerloop: for (int i = 0; i < pagecount; i++) {

					List<WebElement> roles = driver.findElements(By.xpath("//tr//div"));
					int rolesnum = roles.size();

					for (int j = 0; j < rolesnum; j++) {

						WebElement rolestext = roles.get(j);
						String getrole = rolestext.getText();

						if (getrole.equalsIgnoreCase("Enviro-Consultants")) {

							// verify rank of the Enviro-Consultants role is displayed correct or not
							String enviro_consultants = (driver
									.findElement(By
											.xpath(OR.getProperty("task_permission_role_enviroconsultants_rank_XPATH")))
									.getText()).trim();

							if (enviro_consultants.equals("50")) {
								successMessage("THE RANK OF THE ENVIRO-CONSULTANTS ROLE IS DISPLAYED CORRECTLY.");

							} else {
								verificationFailed();
							}

							// VERIFY PERMISSION OF THE ADMINISTRATION TO THE ENVIRO-CONSULTANTS ROLE
							helper.rolePermissionValidation("Enviro-Consultants", "ADMINISTRATION", "2", "red",
									"close");

							// VERIFY PERMISSION OF THE PROPERTY EDIT TO THE ENVIRO-CONSULTANTS ROLE
							helper.rolePermissionValidation("Enviro-Consultants", "PROPERTY EDIT", "3", "green",
									"checkmark");

							// VERIFY PERMISSION OF THE SEES ALL PROPERTIES TO THE ENVIRO-CONSULTANTS ROLE
							helper.rolePermissionValidation("Enviro-Consultants", "SEES ALL PROPERTIES", "4", "red",
									"close");

							// VERIFY PERMISSION OF THE SEES ALL TASKS TO THE ENVIRO-CONSULTANTS ROLE
							helper.rolePermissionValidation("Enviro-Consultants", "SEES ALL TASKS", "5", "red",
									"close");

							// VERIFY PERMISSION OF THE WEEKLY TASK EMAILS TO THE ENVIRO-CONSULTANTS ROLE
							helper.rolePermissionValidation("Enviro-Consultants", "WEEKLY TASK EMAILS", "6", "red",
									"close");

							// VERIFY PERMISSION OF THE DEFAULT ROLE TO THE ENVIRO-CONSULTANTS ROLE
							helper.rolePermissionValidation("Enviro-Consultants", "DEFAULT ROLE", "7", "red", "close");

							break outerloop;
						}
					}

					// click on the next button
					click("task_permission_roles_nextbtn_CSS");

				}
			} catch (Throwable t) {
				verificationFailed();
			}

			// VERIFY RANK OF THE ENVIRO-USERS ROLE
			title("VERIFY RANK OF THE ENVIRO-USERS ROLE");

			// scroll up the screen
			scrollByPixel(-200);

			// click on the administration option
			click("administration_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the role tab
			click("questionnaire_rolesoption_XPATH");

			// scroll down the screen
			scrollByPixel(200);

			try {
				String pages = driver.findElement(By.cssSelector("strong")).getText();
				String[] arrOfStr = pages.split(" ");
				String pageno = arrOfStr[3];
				int pagecount = Integer.parseInt(pageno);

				outerloop: for (int i = 0; i < pagecount; i++) {

					List<WebElement> roles = driver.findElements(By.xpath("//tr//div"));
					int rolesnum = roles.size();

					for (int j = 0; j < rolesnum; j++) {

						WebElement rolestext = roles.get(j);
						String getrole = rolestext.getText();

						if (getrole.equalsIgnoreCase("Enviro-Users")) {

							// verify rank of the Enviro-Users role is displayed correct or not
							String enviro_users = (driver
									.findElement(
											By.xpath(OR.getProperty("task_permission_role_envirousers_rank_XPATH")))
									.getText()).trim();

							if (enviro_users.equals("75")) {
								successMessage("THE RANK OF THE ENVIRO-USERS ROLE IS DISPLAYED CORRECTLY.");

							} else {
								verificationFailed();
							}

							// VERIFY PERMISSION OF THE ADMINISTRATION TO THE ENVIRO-USERS ROLE
							helper.rolePermissionValidation("Enviro-Users", "ADMINISTRATION", "2", "red", "close");

							// VERIFY PERMISSION OF THE PROPERTY EDIT TO THE ENVIRO-USERS ROLE
							helper.rolePermissionValidation("Enviro-Users", "PROPERTY EDIT", "3", "red", "close");

							// VERIFY PERMISSION OF THE SEES ALL PROPERTIES TO THE ENVIRO-USERS ROLE
							helper.rolePermissionValidation("Enviro-Users", "SEES ALL PROPERTIES", "4", "green",
									"checkmark");

							// VERIFY PERMISSION OF THE SEES ALL TASKS TO THE ENVIRO-USERS ROLE
							helper.rolePermissionValidation("Enviro-Users", "SEES ALL TASKS", "5", "red", "close");

							// VERIFY PERMISSION OF THE WEEKLY TASK EMAILS TO THE ENVIRO-USERS ROLE
							helper.rolePermissionValidation("Enviro-Users", "WEEKLY TASK EMAILS", "6", "red", "close");

							// VERIFY PERMISSION OF THE DEFAULT ROLE TO THE ENVIRO-USERS ROLE
							helper.rolePermissionValidation("Enviro-Users", "DEFAULT ROLE", "7", "red", "close");

							break outerloop;
						}
					}

					// click on the next button
					click("task_permission_roles_nextbtn_CSS");

				}
			} catch (Throwable t) {
				verificationFailed();
			}

			// VERIFY RANK OF THE ENVIRO-MIL-R-G ROLE
			title("VERIFY RANK OF THE ENVIRO-MIL-R-G ROLE");

			// scroll up the screen
			scrollByPixel(-200);

			// click on the administration option
			click("administration_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the role tab
			click("questionnaire_rolesoption_XPATH");

			// scroll down the screen
			scrollByPixel(200);

			try {
				String pages = driver.findElement(By.cssSelector("strong")).getText();
				String[] arrOfStr = pages.split(" ");
				String pageno = arrOfStr[3];
				int pagecount = Integer.parseInt(pageno);

				outerloop: for (int i = 0; i < pagecount; i++) {

					List<WebElement> roles = driver.findElements(By.xpath("//tr//div"));
					int rolesnum = roles.size();

					for (int j = 0; j < rolesnum; j++) {

						WebElement rolestext = roles.get(j);
						String getrole = rolestext.getText();

						if (getrole.equalsIgnoreCase("Enviro-MIL-R-G")) {

							// verify rank of the Enviro-MIL-R-G role is displayed correct or not
							String enviro_mil = (driver
									.findElement(
											By.xpath(OR.getProperty("task_permission_role_enviromilrg_rank_XPATH")))
									.getText()).trim();

							if (enviro_mil.equals("100")) {
								successMessage("THE RANK OF THE ENVIRO-MIL-R-G ROLE IS DISPLAYED CORRECTLY.");

							} else {
								verificationFailed();
							}

							// VERIFY PERMISSION OF THE ADMINISTRATION TO THE ENVIRO-MIL-R-G ROLE
							helper.rolePermissionValidation("Enviro-MIL-R-G", "ADMINISTRATION", "2", "red", "close");

							// VERIFY PERMISSION OF THE PROPERTY EDIT TO THE ENVIRO-MIL-R-G ROLE
							helper.rolePermissionValidation("Enviro-MIL-R-G", "PROPERTY EDIT", "3", "red", "close");

							// VERIFY PERMISSION OF THE SEES ALL PROPERTIES TO THE ENVIRO-MIL-R-G ROLE
							helper.rolePermissionValidation("Enviro-MIL-R-G", "SEES ALL PROPERTIES", "4", "green",
									"checkmark");

							// VERIFY PERMISSION OF THE SEES ALL TASKS TO THE ENVIRO-MIL-R-G ROLE
							helper.rolePermissionValidation("Enviro-MIL-R-G", "SEES ALL TASKS", "5", "red", "close");

							// VERIFY PERMISSION OF THE WEEKLY TASK EMAILS TO THE ENVIRO-MIL-R-G ROLE
							helper.rolePermissionValidation("Enviro-MIL-R-G", "WEEKLY TASK EMAILS", "6", "red",
									"close");

							// VERIFY PERMISSION OF THE DEFAULT ROLE TO THE ENVIRO-MIL-R-G ROLE
							helper.rolePermissionValidation("Enviro-MIL-R-G", "DEFAULT ROLE", "7", "red", "close");

							break outerloop;
						}
					}

					// click on the next button
					click("task_permission_roles_nextbtn_CSS");

				}
			} catch (Throwable t) {
				verificationFailed();
			}

			// VERIFY RANK OF THE ENVIRO-MRC-R-G ROLE
			title("VERIFY RANK OF THE ENVIRO-MRC-R-G ROLE");

			// scroll up the screen
			scrollByPixel(-200);

			// click on the administration option
			click("administration_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the role tab
			click("questionnaire_rolesoption_XPATH");

			// scroll down the screen
			scrollByPixel(200);

			try {
				String pages = driver.findElement(By.cssSelector("strong")).getText();
				String[] arrOfStr = pages.split(" ");
				String pageno = arrOfStr[3];
				int pagecount = Integer.parseInt(pageno);

				outerloop: for (int i = 0; i < pagecount; i++) {

					List<WebElement> roles = driver.findElements(By.xpath("//tr//div"));
					int rolesnum = roles.size();

					for (int j = 0; j < rolesnum; j++) {

						WebElement rolestext = roles.get(j);
						String getrole = rolestext.getText();

						if (getrole.equalsIgnoreCase("Enviro-MRC-R-G")) {

							// verify rank of the Enviro-MRC-R-G role is displayed correct or not
							String enviro_mrc = (driver
									.findElement(
											By.xpath(OR.getProperty("task_permission_role_enviromrcrg_rank_XPATH")))
									.getText()).trim();

							if (enviro_mrc.equals("100")) {
								successMessage("THE RANK OF THE ENVIRO-MRC-R-G ROLE IS DISPLAYED CORRECTLY.");

							} else {
								verificationFailed();
							}

							// VERIFY PERMISSION OF THE ADMINISTRATION TO THE ENVIRO-MRC-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRC-R-G", "ADMINISTRATION", "2", "red", "close");

							// VERIFY PERMISSION OF THE PROPERTY EDIT TO THE ENVIRO-MRC-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRC-R-G", "PROPERTY EDIT", "3", "red", "close");

							// VERIFY PERMISSION OF THE SEES ALL PROPERTIES TO THE ENVIRO-MRC-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRC-R-G", "SEES ALL PROPERTIES", "4", "green",
									"checkmark");

							// VERIFY PERMISSION OF THE SEES ALL TASKS TO THE ENVIRO-MRC-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRC-R-G", "SEES ALL TASKS", "5", "red", "close");

							// VERIFY PERMISSION OF THE WEEKLY TASK EMAILS TO THE ENVIRO-MRC-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRC-R-G", "WEEKLY TASK EMAILS", "6", "red",
									"close");

							// VERIFY PERMISSION OF THE DEFAULT ROLE TO THE ENVIRO-MRC-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRC-R-G", "DEFAULT ROLE", "7", "red", "close");

							break outerloop;
						}
					}

					// click on the next button
					click("task_permission_roles_nextbtn_CSS");

				}
			} catch (Throwable t) {
				verificationFailed();
			}

			// VERIFY RANK OF THE ENVIRO-MREIT-R-G ROLE
			title("VERIFY RANK OF THE ENVIRO-MREIT-R-G ROLE");

			// scroll up the screen
			scrollByPixel(-200);

			// click on the administration option
			click("administration_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the role tab
			click("questionnaire_rolesoption_XPATH");

			// scroll down the screen
			scrollByPixel(200);

			try {
				String pages = driver.findElement(By.cssSelector("strong")).getText();
				String[] arrOfStr = pages.split(" ");
				String pageno = arrOfStr[3];
				int pagecount = Integer.parseInt(pageno);

				outerloop: for (int i = 0; i < pagecount; i++) {

					List<WebElement> roles = driver.findElements(By.xpath("//tr//div"));
					int rolesnum = roles.size();

					for (int j = 0; j < rolesnum; j++) {

						WebElement rolestext = roles.get(j);
						String getrole = rolestext.getText();

						if (getrole.equalsIgnoreCase("Enviro-MREIT-R-G")) {

							// verify rank of the Enviro-MREIT-R-G role is displayed correct or not
							String enviro_mreit = (driver
									.findElement(
											By.xpath(OR.getProperty("task_permission_role_enviromreitrg_rank_XPATH")))
									.getText()).trim();

							if (enviro_mreit.equals("100")) {
								successMessage("THE RANK OF THE ENVIRO-MREIT-R-G ROLE IS DISPLAYED CORRECTLY.");

							} else {
								verificationFailed();
							}

							// VERIFY PERMISSION OF THE ADMINISTRATION TO THE ENVIRO-MREIT-R-G ROLE
							helper.rolePermissionValidation("Enviro-MREIT-R-G", "ADMINISTRATION", "2", "red", "close");

							// VERIFY PERMISSION OF THE PROPERTY EDIT TO THE ENVIRO-MREIT-R-G ROLE
							helper.rolePermissionValidation("Enviro-MREIT-R-G", "PROPERTY EDIT", "3", "red", "close");

							// VERIFY PERMISSION OF THE SEES ALL PROPERTIES TO THE ENVIRO-MREIT-R-G ROLE
							helper.rolePermissionValidation("Enviro-MREIT-R-G", "SEES ALL PROPERTIES", "4", "green",
									"checkmark");

							// VERIFY PERMISSION OF THE SEES ALL TASKS TO THE ENVIRO-MREIT-R-G ROLE
							helper.rolePermissionValidation("Enviro-MREIT-R-G", "SEES ALL TASKS", "5", "red", "close");

							// VERIFY PERMISSION OF THE WEEKLY TASK EMAILS TO THE ENVIRO-MREIT-R-G ROLE
							helper.rolePermissionValidation("Enviro-MREIT-R-G", "WEEKLY TASK EMAILS", "6", "red",
									"close");

							// VERIFY PERMISSION OF THE DEFAULT ROLE TO THE ENVIRO-MREIT-R-G ROLE
							helper.rolePermissionValidation("Enviro-MREIT-R-G", "DEFAULT ROLE", "7", "red", "close");

							break outerloop;
						}
					}

					// click on the next button
					click("task_permission_roles_nextbtn_CSS");

				}
			} catch (Throwable t) {
				verificationFailed();
			}

			// VERIFY RANK OF THE ENVIRO-MRI-R-G ROLE
			title("VERIFY RANK OF THE ENVIRO-MRI-R-G ROLE");

			// scroll up the screen
			scrollByPixel(-200);

			// click on the administration option
			click("administration_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the role tab
			click("questionnaire_rolesoption_XPATH");

			// scroll down the screen
			scrollByPixel(200);

			try {
				String pages = driver.findElement(By.cssSelector("strong")).getText();
				String[] arrOfStr = pages.split(" ");
				String pageno = arrOfStr[3];
				int pagecount = Integer.parseInt(pageno);

				outerloop: for (int i = 0; i < pagecount; i++) {

					List<WebElement> roles = driver.findElements(By.xpath("//tr//div"));
					int rolesnum = roles.size();

					for (int j = 0; j < rolesnum; j++) {

						WebElement rolestext = roles.get(j);
						String getrole = rolestext.getText();

						if (getrole.equalsIgnoreCase("Enviro-MRI-R-G")) {

							// verify rank of the Enviro-MRI-R-G role is displayed correct or not
							String enviro_mri = (driver
									.findElement(
											By.xpath(OR.getProperty("task_permission_role_enviromrirg_rank_XPATH")))
									.getText()).trim();

							if (enviro_mri.equals("100")) {
								successMessage("THE RANK OF THE ENVIRO-MRI-R-G ROLE IS DISPLAYED CORRECTLY.");

							} else {
								verificationFailed();
							}

							// VERIFY PERMISSION OF THE ADMINISTRATION TO THE ENVIRO-MRI-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRI-R-G", "ADMINISTRATION", "2", "red", "close");

							// VERIFY PERMISSION OF THE PROPERTY EDIT TO THE ENVIRO-MRI-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRI-R-G", "PROPERTY EDIT", "3", "red", "close");

							// VERIFY PERMISSION OF THE SEES ALL PROPERTIES TO THE ENVIRO-MRI-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRI-R-G", "SEES ALL PROPERTIES", "4", "green",
									"checkmark");

							// VERIFY PERMISSION OF THE SEES ALL TASKS TO THE ENVIRO-MRI-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRI-R-G", "SEES ALL TASKS", "5", "red", "close");

							// VERIFY PERMISSION OF THE WEEKLY TASK EMAILS TO THE ENVIRO-MRI-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRI-R-G", "WEEKLY TASK EMAILS", "6", "red",
									"close");

							// VERIFY PERMISSION OF THE DEFAULT ROLE TO THE ENVIRO-MRI-R-G ROLE
							helper.rolePermissionValidation("Enviro-MRI-R-G", "DEFAULT ROLE", "7", "red", "close");

							break outerloop;
						}
					}

					// click on the next button
					click("task_permission_roles_nextbtn_CSS");

				}
			} catch (Throwable t) {
				verificationFailed();
			}

		} catch (Throwable t) {
			verificationFailed();
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_CSS");

		// wait for the element
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		// VERIFY DEFULT DASHBOARD FOR ROLES SHOULD BE EXECUTIVE
		title("VERIFY DEFULT DASHBOARD FOR ROLES SHOULD BE EXECUTIVE");

		// VERIFY DEFAULT DASHBOARD OF THE ENVIRO-ADMIN ROLE
		title("VERIFY DEFAULT DASHBOARD OF THE ENVIRO-ADMIN ROLE");

		try {

			try {

				// wait for 3 seconds
				Thread.sleep(5000);

				// click on the settings icon from the top of the screen
				click("questionnaire_settingicon_CSS");

				// wait for 3 seconds
				Thread.sleep(3000);

				// click on the Administration option from the side menu
				click("questionnaire_administrationoption_XPATH");

				// click on the security tab
				click("questionnaire_securitytab_XPATH");

				// click on the role tab
				click("questionnaire_rolesoption_XPATH");

				helper.defaultExecutiveOptionValidation("Enviro-Admins", "task_permission_role_enviroadmin_rank_XPATH");

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			} catch (Throwable t) {

				verificationFailed();

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			}

			// VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MANAGER ROLE
			title("VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MANAGER ROLE");

			try {

				// click on the security tab
				click("questionnaire_securitytab_XPATH");

				// click on the role tab
				click("questionnaire_rolesoption_XPATH");

				helper.defaultExecutiveOptionValidation("Enviro-Managers",
						"task_permission_role_enviromanager_rank_XPATH");

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			} catch (Throwable t) {
				verificationFailed();

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			}

			// VERIFY DEFAULT DASHBOARD OF THE ENVIRO-CONSULTANTS ROLE
			title("VERIFY DEFAULT DASHBOARD OF THE ENVIRO-CONSULTANTS ROLE");

			try {

				// click on the security tab
				click("questionnaire_securitytab_XPATH");

				// click on the role tab
				click("questionnaire_rolesoption_XPATH");

				helper.defaultExecutiveOptionValidation("Enviro-Consultants",
						"task_permission_role_enviroconsultants_rank_XPATH");

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			} catch (Throwable t) {

				verificationFailed();

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			}

			// VERIFY DEFAULT DASHBOARD OF THE ENVIRO-USERS ROLE
			title("VERIFY DEFAULT DASHBOARD OF THE ENVIRO-USERS ROLE");

			try {

				// click on the security tab
				click("questionnaire_securitytab_XPATH");

				// click on the role tab
				click("questionnaire_rolesoption_XPATH");

				helper.defaultExecutiveOptionValidation("Enviro-Users", "task_permission_role_envirousers_rank_XPATH");

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			} catch (Throwable t) {

				verificationFailed();

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			}

			// VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MIL-R-G ROLE
			title("VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MIL-R-G ROLE");

			try {

				// click on the security tab
				click("questionnaire_securitytab_XPATH");

				// click on the role tab
				click("questionnaire_rolesoption_XPATH");

				helper.defaultExecutiveOptionValidation("Enviro-MIL-R-G",
						"task_permission_role_enviromilrg_rank_XPATH");

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			} catch (Throwable t) {
				verificationFailed();

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			}

			// VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MRC-R-G ROLE
			title("VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MRC-R-G ROLE");

			try {

				// click on the security tab
				click("questionnaire_securitytab_XPATH");

				// click on the role tab
				click("questionnaire_rolesoption_XPATH");

				helper.defaultExecutiveOptionValidation("Enviro-MRC-R-G",
						"task_permission_role_enviromrcrg_rank_XPATH");

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			} catch (Throwable t) {
				verificationFailed();

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			}

			// VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MRC-R-G ROLE
			title("VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MRC-R-G ROLE");

			try {

				// click on the security tab
				click("questionnaire_securitytab_XPATH");

				// click on the role tab
				click("questionnaire_rolesoption_XPATH");

				helper.defaultExecutiveOptionValidation("Enviro-MRC-R-G",
						"task_permission_role_enviromrcrg_rank_XPATH");

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			} catch (Throwable t) {
				verificationFailed();

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			}

			// VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MREIT-R-G ROLE
			title("VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MREIT-R-G ROLE");

			try {

				// click on the security tab
				click("questionnaire_securitytab_XPATH");

				// click on the role tab
				click("questionnaire_rolesoption_XPATH");

				helper.defaultExecutiveOptionValidation("Enviro-MREIT-R-G",
						"task_permission_role_enviromreitrg_rank_XPATH");

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			} catch (Throwable t) {
				verificationFailed();

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			}

			// VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MRI-R-G ROLE
			title("VERIFY DEFAULT DASHBOARD OF THE ENVIRO-MRI-R-G ROLE");

			try {

				// click on the security tab
				click("questionnaire_securitytab_XPATH");

				// click on the role tab
				click("questionnaire_rolesoption_XPATH");

				helper.defaultExecutiveOptionValidation("Enviro-MRI-R-G",
						"task_permission_role_enviromrirg_rank_XPATH");

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			} catch (Throwable t) {
				verificationFailed();

				// scroll up the screen

				scrollTop();

				// click on the administration option
				click("administration_XPATH");

			}
		} catch (Throwable t) {
			verificationFailed();
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_CSS");

		// wait for the element
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		// ADMINISTRATION ASSIGN SELECTED PROPERTIES FOR CONSULTANT TO VIEW/EDIT
		title("ADMINISTRATION ASSIGN SELECTED PROPERTIES FOR CONSULTANT TO VIEW/EDIT");

		String groupName = RandomStringUtils.randomAlphabetic(8);

		try {

			// wait for 3 seconds
			Thread.sleep(5000);

			// click on the settings icon from the top of the screen
			click("questionnaire_settingicon_CSS");

			// wait for 3 seconds
			Thread.sleep(3000);

			// click on the Administration option from the side menu
			click("questionnaire_administrationoption_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the property groups tab
			click("questionnaire_propertygroupoption_XPATH");

			// ADD NEW PROPERTY GROUP
			title("ADD NEW PROPERTY GROUP");

			// scroll down the screen
			scrollTillElement("administration_security_groupproperty_addbtn_CSS");

			// click on the add button
			click("administration_security_groupproperty_addbtn_CSS");

			// enter the group name
			type("administration_security_groupproperty_groupnametxt_XPATH", groupName);

			// click on the add of the add property group
			click("administration_security_groupproperty_groupname_addbtn_CSS");

			// scroll up the screen
			scrollTop();

			try {

				// verify newly created group is displayed or not
				String addedGroup = driver.findElement(
						By.cssSelector(OR.getProperty("administration_security_groupproperty_addedgroupname_CSS")))
						.getText();

				consoleMessage("Added Group:::::::::::::" + addedGroup);

				if (addedGroup.equals("0 Properties in '" + groupName + "'")) {
					successMessage("THE NEWLY CREATED GROUP IS DISPLAYED SUCCESSFULLY.");
				} else {
					verificationFailedMessage("THE NEWLY CREATED GROUP IS NOT DISPLAYED");
				}
			} catch (Throwable t) {

				verificationFailedMessage("THE NEWLY CREATED GROUP IS NOT DISPLAYED");

			}

			// ADD PROPERTY IN THE NEWLY CREATED PROPERTY GROUP
			title("ADD PROPERTY IN THE NEWLY CREATED PROPERTY GROUP");

			// click on the edit button
			click("administration_security_groupproperty_editbtn_CSS");

			// enter property name in the search field
			type("administration_security_groupproperty_searchfield_CSS", data.get("property_1"));

			// click on the checkbox of the property
			click("administration_security_groupproperty_searchedpropertyckbx_XPATH");

			// click on the update button
			click("administration_security_groupproperty_addproperty_updatebtn_CSS");

			try {

				// verify the added property is displayed or not
				String propertyName = (driver
						.findElement(
								By.xpath(OR.getProperty("administration_security_groupproperty_addedproperty_XPATH")))
						.getText()).trim();

				if (propertyName.equals("CAPREIT - White Frost")) {
					successMessage("THE ADDED PROPERTY IS DISPLAYED SUCCESSFULLY.");

				} else {
					verificationFailedMessage("THE ADDED PROPERTY IS NOT DISPLAYED");
				}

			} catch (Throwable t) {

				verificationFailedMessage("THE ADDED PROPERTY IS NOT DISPLAYED");

			}

			// VERIFY RESEPCTIVE PROPERTY DISPLAYED IN THE CONSULTANT USER ACCOUNT
			title("VERIFY RESEPCTIVE PROPERTY DISPLAYED IN THE CONSULTANT USER ACCOUNT");

			// scroll up to top
			scrollTop();

			// click on the administration option
			click("administration_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the users tab
			click("questionnaire_usersoption_XPATH");

			// enter the user name in the search field

			type("users_filtertxt_CSS", data.get("consultant_user"));

			// click on the assign property icon of the searched user
			click("administration_security_searcheduser_assignproperty_XPATH");

			// click on the property group radio button
			click("administration_security_searcheduser_propertygroupradio_XPATH");

			// enter group name in the search field

			type("administration_security_searcheduser_searchgrouptxt1_XPATH", groupName);

			// click on the assign icon of the searched group
			String assignIcon = "//div[text()='" + groupName
					+ "']//parent::td//following-sibling::td[@ng-click='assignPropertyGroup(group);']";
			driver.findElement(By.xpath(assignIcon)).click();

			// enter group name in the search field

			type("administration_security_searcheduser_searchgrouptxt2_XPATH", groupName);

			try {
				// validate group assigned successfully to respective user
				String assignedGroup = "//td[@ng-click='unassignPropertyGroup(assignedGroup);']//preceding-sibling::td[text()='"
						+ groupName + "']";

				String assignedGroupText = driver.findElement(By.xpath(assignedGroup)).getText();

				if (assignedGroupText.equals(groupName)) {
					successMessage("THE GROUP ASSIGNED TO THE RESPECTIVE USER SUCCESSFULLY.");
				} else {
					verificationFailedMessage("THE GROUP NOT ASSIGNED TO THE RESPECTIVE USER");
				}

			} catch (Throwable t) {
				verificationFailedMessage("THE GROUP NOT ASSIGNED TO THE RESPECTIVE USER");
			}

			// click on the administration option
			click("administration_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the users tab
			click("questionnaire_usersoption_XPATH");

			// enter the user name in the search field

			type("users_filtertxt_CSS", data.get("consultant_user"));

			// click on the switch to icon of the searched user
			click("administration_security_searcheduser_switchto_XPATH");

			// wait for the element
			Thread.sleep(5000);

			// click on the switch button
			click("administration_security_searcheduser_switchbtn_CSS");

			// LOGIN WITH CONSULTANT USER
			title("LOGIN WITH CONSULTANT USER");

			try {
				// wait for the element
				explicitWait("propertylist_title_XPATH");

				// verify the property list
				switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

				// enter the property name in the search field
				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// verify searched property displayed or not
				switchVerification("morguard_searchedproperty1_XPATH", "CAPREIT - White Frost",
						"The CAPREIT - White Frost property is not displayed.");

				// wait for the element
				explicitWaitClickable("questionnaire_settingicon_CSS");

				// click on the settings icon from the top of the screen
				click("questionnaire_settingicon_CSS");

				// wait for the element
				explicitWaitClickable("sidemenu_logout_CSS");

				// click on the logout option from the side menu
				click("sidemenu_logout_CSS");

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

			} catch (Throwable t) {
				successMessage("AN EXEPTIONS ARE OCCURED IN THIS USER, SO LOGOUT FROM THIS USER.");

				verificationFailed();

				driver.navigate().refresh();
				Thread.sleep(3000);
				driver.navigate().refresh();

				// wait for the element
				explicitWaitClickable("questionnaire_settingicon_userupdate_CSS");

				// click on the settings icon from the top of the screen
				click("questionnaire_settingicon_userupdate_CSS");

				// wait for the element
				explicitWaitClickable("sidemenu_logout_CSS");

				// click on the logout option from the side menu
				click("sidemenu_logout_CSS");

				// LOGIN WITH ADMIN USER
				title("LOGIN WITH ADMIN USER");

				// wait for the element
				explicitWaitClickable("signinbtn_BTNTEXT");

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
			}

			// EDIT THE PROPERTY GROUP
			title("EDIT THE PROPERTY GROUP");

			// wait for 3 seconds
			Thread.sleep(5000);

			// click on the settings icon from the top of the screen
			click("questionnaire_settingicon_CSS");

			// wait for 3 seconds
			Thread.sleep(3000);

			// click on the Administration option from the side menu
			click("questionnaire_administrationoption_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the property groups tab
			click("questionnaire_propertygroupoption_XPATH");

			// enter the group name in the search field
			type("administration_security_createdgroup_searchtxt_CSS", groupName);

			// click on the searched group
			String group = "//td[text()='" + groupName + "']";
			driver.findElement(By.xpath(group)).click();

			// wait for the element
			Thread.sleep(3000);

			try {

				// verify newly created group is displayed or not
				String addedGroup = driver.findElement(
						By.cssSelector(OR.getProperty("administration_security_groupproperty_addedgroupname_CSS")))
						.getText();

				consoleMessage("Added Group:::::::::::::" + addedGroup);

				if (addedGroup.equals("1 Properties in '" + groupName + "'")) {
					successMessage("THE NEWLY CREATED GROUP IS DISPLAYED SUCCESSFULLY.");
				} else {
					verificationFailedMessage("THE NEWLY CREATED GROUP IS NOT DISPLAYED");
				}
			} catch (Throwable t) {

				verificationFailedMessage("THE NEWLY CREATED GROUP IS NOT DISPLAYED");

			}

			// UPDATE THE PROPERTY IN THE NEWLY CREATED PROPERTY GROUP
			title("UPDATE THE PROPERTY IN THE NEWLY CREATED PROPERTY GROUP");

			// click on the edit button
			click("administration_security_groupproperty_editbtn_CSS");

			// enter property name in the search field

			type("administration_security_groupproperty_searchfield_CSS", data.get("property_1"));

			// click on the checkbox of the property
			click("administration_security_groupproperty_searchedpropertyckbx_XPATH");

			// enter property name in the search field

			type("administration_security_groupproperty_searchfield_CSS", data.get("property_2"));

			// click on the checkbox of the property
			click("administration_security_groupproperty_searchedpropertyckbx2_XPATH");

			// enter property name in the search field

			type("administration_security_groupproperty_searchfield_CSS", data.get("property_3"));

			// click on the checkbox of the property
			click("administration_security_groupproperty_searchedpropertyckbx3_XPATH");

			// click on the update button
			click("administration_security_groupproperty_addproperty_updatebtn_CSS");

			// verify the added property is displayed or not
			switchVerification("administration_security_groupproperty_addedproperty2_XPATH",
					"Capreit - *Off-Site Incident Report", "The Capreit - *Off-Site Incident Report is not displayed.");

			switchVerification("administration_security_groupproperty_addedproperty3_XPATH", "SmartCentres Brockville",
					"The SmartCentres Brockville is not displayed.");

			deleteVerification("administration_security_groupproperty_addedproperty_XPATH", "CAPREIT - White Frost");

			// VERIFY RESEPCTIVE UPDATED PROPERTY DISPLAYED IN THE CONSULTANT USER ACCOUNT
			title("VERIFY RESEPCTIVE UPDATED PROPERTY DISPLAYED IN THE CONSULTANT USER ACCOUNT");

			// scroll up to top
			scrollTop();

			// click on the administration option
			click("administration_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the users tab
			click("questionnaire_usersoption_XPATH");

			// enter the user name in the search field

			type("users_filtertxt_CSS", data.get("consultant_user"));

			// click on the switch to icon of the searched user
			click("administration_security_searcheduser_switchto_XPATH");

			// click on the switch button
			click("administration_security_searcheduser_switchbtn_CSS");

			// LOGIN WITH CONSULTANT USER
			title("LOGIN WITH CONSULTANT USER");

			try {
				// wait for the element
				explicitWait("propertylist_title_XPATH");

				// verify the property list
				switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

				// enter the property name in the search field

				type("envreports_propertylist_filtertxt_CSS", data.get("property_1"));

				// verify searched property displayed or not
				deleteVerification("morguard_searchedproperty1_XPATH", "CAPREIT - White Frost");

				// enter the property name in the search field

				type("envreports_propertylist_filtertxt_CSS", data.get("property_2"));

				switchVerification("morguard_searchedproperty2_XPATH", "Capreit - *Off-Site Incident Report",
						"The Capreit - *Off-Site Incident Report property is not displayed.");

				// enter the property name in the search field

				type("envreports_propertylist_filtertxt_CSS", data.get("property_3"));

				switchVerification("morguard_searchedproperty3_XPATH", "SmartCentres Brockville",
						"The SmartCentres Brockville property is not displayed.");

				// wait for the element
				explicitWaitClickable("questionnaire_settingicon_CSS");

				// click on the settings icon from the top of the screen
				click("questionnaire_settingicon_CSS");

				// wait for the element
				explicitWaitClickable("sidemenu_logout_CSS");

				// click on the logout option from the side menu
				click("sidemenu_logout_CSS");

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

			} catch (Throwable t) {
				consoleMessage("AN EXEPTIONS ARE OCCURED IN THIS USER, SO LOGOUT FROM THIS USER.");

				verificationFailed();

				driver.navigate().refresh();
				Thread.sleep(3000);
				driver.navigate().refresh();

				// wait for the element
				explicitWaitClickable("questionnaire_settingicon_userupdate_CSS");

				// click on the settings icon from the top of the screen
				click("questionnaire_settingicon_userupdate_CSS");

				// wait for the element
				explicitWaitClickable("sidemenu_logout_CSS");

				// click on the logout option from the side menu
				click("sidemenu_logout_CSS");

				// LOGIN WITH ADMIN USER
				title("LOGIN WITH ADMIN USER");

				// wait for the element
				explicitWaitClickable("signinbtn_BTNTEXT");

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
			}

		} catch (Throwable t) {
			verificationFailed();
		}

		try {

			// DELETE THE NEWLY CREATED PROPERTY GROUP
			title("DELETE THE NEWLY CREATED PROPERTY GROUP");

			// wait for 3 seconds
			Thread.sleep(5000);

			// click on the settings icon from the top of the screen
			click("questionnaire_settingicon_CSS");

			// wait for 3 seconds
			Thread.sleep(3000);

			// click on the Administration option from the side menu
			click("questionnaire_administrationoption_XPATH");

			// click on the security tab
			click("questionnaire_securitytab_XPATH");

			// click on the property groups tab
			click("questionnaire_propertygroupoption_XPATH");

			// enter the group name in the search field

			type("administration_security_createdgroup_searchtxt_CSS", groupName);

			// click on the delete button of the property group
			String groupDeleteXPATH = "//td[text()='" + groupName
					+ "']//following-sibling::td[@data-target='#deleteGroupModal']";
			driver.findElement(By.xpath(groupDeleteXPATH)).click();

			// click on the delete button of the confirmation popup
			click("administration_security_groupproperty_confirmationdeletebtn_XPATH");

			// verify deleted property group is displayed or not

			deleteVerification(groupDeleteXPATH, groupName);

		} catch (Throwable t) {
			verificationFailed();
		}

		// click on the home icon from the top of the screen
		click("questionnaire_homeburgermenubtn_CSS");

		// synchronization
		explicitWait("propertylist_title_XPATH");

		// verify the property list
		switchVerification("propertylist_title_XPATH", "Property List", "The property list is not displayed.");

		try {
			// wait for the element
			Thread.sleep(7000);

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