package UOB;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class AuditReports extends BaseLoginLogout {
	
	@Test
	public void Userloginlogoutreport() throws InterruptedException, IOException
	{
		
		
		
		driver.findElement(By.xpath("//a[normalize-space()='Audit Reports']")).click();	
		
		UserManagementReportClass.User_Management_Report("User Login Logout Report", driver,"User_Login_Logout_Report_","User ID","Login Date Time","Logout Date Time","","","","","","");
				
		UserManagementReportClass.User_Management_Report("User Management Report", driver,"User_Management_Report_","User ID","Status","Action By","Last Updated Date","","","","","");	
		UserManagementReportClass.User_Management_Report("Group Management Report", driver,"Group_Management_Report_","Group Name","Status","Action By","Last Updated Date","","","","","");
		UserManagementReportClass.User_Management_Report("Unsuccessful Login Attempts Report", driver,"Unsuccessful_Login_Attempts_Report_","User ID","No Of Attempts","Failure Reason","Login Date","","","","","");
		UserManagementReportClass.User_Management_Report("Documents search Report", driver,"Documents_Search_Report_","User ID","Action","Search Date","Search Fields","","","","","");

		UserManagementReportClass.User_Management_Report("Staff Statement Access Report", driver,"Staff_Statement_Access_Report_","CIF Number","Account Number","Cycle Date","Statement Type","Staff Flag","User ID","User Name","Department","Stmt View Time");
		UserManagementReportClass.User_Management_Report("Resend Requests Report", driver,"Resend_Requests_Report_","REQUESTER USER ID","CIF NUMBER","ACCOUNT NUMBER","STATEMENT TYPE","CYCLE DATE","DELIVERY MODE","REQUESTED TIMESTAMP","","");	
		
		
		
	}

		
}


