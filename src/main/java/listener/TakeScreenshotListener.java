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
		// ����Ϊȫ����������������com.testcases.LoginTest
		String classname = tr.getTestClass().getName();
		// ������Ϊִ�еķ�����testWrongPasswordLogin
		String methodname = tr.getMethod().getMethodName();
		// �˴�Ϊ��ȡ��ǰ��driver������дһ����̬��������ȡ��ǰdriver��Ȼ��������
		WebDriver driver = BaseAction.getDriver();
		TakeScreenshot shot = new TakeScreenshot(driver);
		shot.takeScreenShot(classname, methodname);
		Log.info("����ʧ��");
		
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		Log.info("���п�ʼ");

	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		Log.info("���гɹ�");
		
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		Log.info("��������");
		
	}
	
}