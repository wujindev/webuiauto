package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

//�½�һ��Retry �࣬implements IRetryAnalyzer�ӿڣ����������ȷ�����ܴ������Լ�����ÿ��ʧ���Ƿ���Ҫ��������
public class Retry implements IRetryAnalyzer {
	private static int maxRetryCount = 2;
	private int retryCount = 1;

	@Override
	public boolean retry(ITestResult result) {
//    	System.out.println(result);

		if (retryCount <= maxRetryCount) {

			String message = "Running retry for '" + result.getName() + "' on class " + this.getClass().getName()
					+ " Retrying " + retryCount + " times";
			Reporter.setCurrentTestResult(result);
			Reporter.log("RunCount=" + (retryCount + 1));
			retryCount++;
			return true;
		}
		return false;
	}
}
