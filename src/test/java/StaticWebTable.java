import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class StaticWebTable {

	public WebDriver driver;
	ExtentReports extent;
	ExtentTest test;

	@BeforeClass
	public void extentReport() {
		//ExtentSparkReport //ExtentReports
		String path= System.getProperty("user.dir")+"\\reports\\index.html";

		ExtentSparkReporter report=new ExtentSparkReporter(path);
		report.config().setReportName("Web Automation Report");
		report.config().setDocumentTitle("Test Result");


		extent=new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "Akshay Sarve");
	}

	@Test
	public void launchApplication() throws IOException {
		
		ExtentTest test= extent.createTest("Static WebTable");
		// System properties for chrome browser
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");

		// Creating an instance for chrome browser
		driver=new ChromeDriver();

		// String to set url
		String url="https://nxtgenaiacademy.com/webtable/";

		// Launch application
		driver.get(url);

		// Maximize the browser
		driver.manage().window().maximize();

		
		String tableXpath="//*/table/tbody/tr[";

		// First Name
		WebElement firstName=driver.findElement(By.xpath(tableXpath + "1]/td[2]"));
		String empFirstName=firstName.getText();
		System.out.println("Employer First Name is " + empFirstName);

		// Last Name
		WebElement lastName=driver.findElement(By.xpath(tableXpath + "2]/td[2]"));
		String empLastName=lastName.getText();
		System.out.println("Employer Last Name is " + empLastName);

		// Gender
		WebElement gender=driver.findElement(By.xpath(tableXpath + "3]/td[2]"));
		String empGender=gender.getText();
		System.out.println("Employer Gender is " + empGender);

		// Date of Birth
		WebElement dateOfBirth=driver.findElement(By.xpath(tableXpath + "4]/td[2]"));
		String empDOB=dateOfBirth.getText();
		System.out.println("Employer Date Of Birth is " + empDOB);

		// Born
		WebElement born=driver.findElement(By.xpath(tableXpath + "5]/td[2]"));
		String empBorn=born.getText();
		System.out.println("Employer Born in " + empBorn);

		// Title
		WebElement title=driver.findElement(By.xpath(tableXpath + "6]/td[2]"));
		String empTitle=title.getText();
		System.out.println("Employer Title is " + empTitle);

		// Emp Number
		WebElement empNumber=driver.findElement(By.xpath(tableXpath + "7]/td[2]"));
		String empNum=empNumber.getText();
		System.out.println("Employer Number is " + empNum);

		// Salary
		WebElement salary=driver.findElement(By.xpath(tableXpath + "8]/td[2]"));
		String empSalary=salary.getText();
		System.out.println("Employer Salary is " + empSalary);

		// Email id
		WebElement emailID=driver.findElement(By.xpath(tableXpath + "9]/td[2]"));
		String empEmailID=emailID.getText();
		System.out.println("Employer Email ID is " + empEmailID);
		
		//test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("1.png").build());
		test.pass("Result is match");
				
	}
	
	

	@AfterClass
	public void closeApplication(ITestResult result){
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("C:\\Users\\sarve\\eclipse-workspace\\Flowace\\ExtentReport\\screenshot\\Web.png"));
					
			
			test.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(src).build());
		}
		
		
		extent.flush();
		// Close application
		driver.close();
		
	}


}

