package listener;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import common.BaseAction;
import net.bytebuddy.implementation.bind.annotation.Super;
import utils.Log;

public class TakeScreenshotListener extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailure(tr);
		// 类名为全类名，包含包名：com.testcases.LoginTest
		String classname = tr.getTestClass().getName();
		// 方法名为执行的方法：testWrongPasswordLogin
		String methodname = tr.getMethod().getMethodName();
		// 此处为获取当前的driver，可以写一个静态方法来获取当前driver，然后来调用
		WebDriver driver = BaseAction.getDriver();
		TakeScreenshot shot = new TakeScreenshot(driver);
		shot.takeScreenShot(classname, methodname);
		Log.info("运行失败");
		
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		Log.info("运行开始");

	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		Log.info("运行成功");
		
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		Log.info("运行跳过");
		
	}
	
}