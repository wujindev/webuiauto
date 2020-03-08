package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WaitUtil {
	public static void sleep(long time){
		try {
			Thread.sleep(time*1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void waitWebElement(WebDriver driver,String elementName) throws Exception{
		WebDriverWait wait=new WebDriverWait(driver,10);
	
		//����ExpectedConditions��presenceofElementLocated�����ж�ҳ���Ƿ����
		wait.until(ExpectedConditions.presenceOfElementLocated(new ObjectMap().getLocator(elementName)));
		
	}
	//��ʾ�ȴ�ҳ��Ԫ�س��ֵķ�װ������������Ϊҳ��Ԫ�ص�By���󣬴˺�������֧�ָ���Ķ�λ���ʽ
	public static void waitWebElement(WebDriver driver,By by){
		WebDriverWait wait=new WebDriverWait(driver,10);
		//����ExpectedConditions��presenceOfElementLocated�����ж�ҳ��Ԫ���Ƿ����
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
}
