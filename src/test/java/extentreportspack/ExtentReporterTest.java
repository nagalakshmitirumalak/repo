package extentreportspack;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTest {

	static ExtentReports extentReport;
	
	public static ExtentReports getExtentReporter() {
		
		String reportpath = System.getProperty("user.dir")+"\\reports\\extent_report.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportpath);
		
		reporter.config().setReportName("Tutorials Ninja Test Report");
		reporter.config().setDocumentTitle("extent reports");
		
		extentReport = new ExtentReports();
		
		extentReport.attachReporter(reporter);
		
		extentReport.setSystemInfo("Operating System", "Windows 10");
		extentReport.setSystemInfo("Tested by", "Naga Lakshmi Thirumala Kamineni");
		
		return extentReport;
		
	}
	
}
