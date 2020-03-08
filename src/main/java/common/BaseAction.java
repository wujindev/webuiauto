package common;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.testng.Assert;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import utils.CommonUtil;
import utils.KeyBoardUtil;
import utils.Log;
import utils.ObjectMap;
import utils.WaitUtil;

public class BaseAction{

	public static WebDriver driver;
	private static ObjectMap objectMap = new ObjectMap();
	static {
		DOMConfigurator.configure("log4j.xml");
	}

	public static WebDriver getDriver() {
		String browserType=CommonUtil.getPropValue(System.getProperty("user.dir")+"\\src\\main\\resources\\conf\\autotest.properties", "browserType");
		switch (browserType) {
		case "ie":
			//����������
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\driver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			Log.info("IE�����ʵ���Ѿ�����");
			break;
		case "firefox":
			//����������
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
			Log.info("��������ʵ���Ѿ�����");
			break;
		case "chrome":
			//����������
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			Log.info("Chrome�����ʵ���Ѿ�����");
			break;
		}
		return driver;
	}

	public static void openUrl(String url) {	
		driver.get(url);
		WaitUtil.sleep(2);
		Log.info("�����������ַ" + url);
	}

	public static String getCurrentPageUrl(){
		String currentPageUrl = null;
		try {
			currentPageUrl=driver.getCurrentUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentPageUrl;
	}

	public static void input(String elementString,String inputString) {
		try {
			driver.findElement(objectMap.getLocator(elementString)).clear();
			Log.info("���" + elementString+"������е���������");
			driver.findElement(objectMap.getLocator(elementString)).sendKeys(inputString);
			Log.info("��" + elementString+"�����������"+inputString);
		} catch (Exception e) {
			Log.info("��" + elementString+"�����������"+inputString+"�����쳣" + e.getMessage());
		}

	}

	public static void click(String elementString) {
		try {
			driver.findElement(objectMap.getLocator(elementString)).click();
			Log.info("����" + elementString+"ҳ��Ԫ�سɹ�");
		} catch (Exception e) {
			Log.info("����" + elementString+"ҳ��Ԫ��ʧ��"+ e.getMessage());
		}
	}
	
	public static void press_Tab(String elementString) {
		try {
			Thread.sleep(2000);
			// ����KeyBoardUtil��ķ�װ����pressTabKey
			KeyBoardUtil.pressTabKey();
			Log.info("��Tab���ɹ�");
		} catch (Exception e) {
			Log.info("��Tab�������쳣�������쳣��Ϣ" + e.getMessage());
		}
	}
	
	public static void press_enter(String elementString) {
		try {
			KeyBoardUtil.pressEnterKey();
			Log.info("��enter���ɹ�");
		} catch (Exception e) {
			Log.info("��enter�������쳣�������쳣��Ϣ" + e.getMessage());
		}
	}
	
	public static WebElement findElement(String elementString) {

		WebElement element = null;
		try {
			element = driver.findElement(objectMap.getLocator(elementString));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;
	}
	
	public static List<WebElement> findElements(String elementString) {

		List<WebElement> elements = null;
		try {
			elements = driver.findElements(objectMap.getLocator(elementString));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elements;
	}

	
	public static void Assert_PageSource(String assertString) {
		try {
			Assert.assertTrue(driver.getPageSource().contains(assertString));
			Log.info("�ɹ����Թؼ���:" + assertString);
		} catch (AssertionError e) {
			Log.info("���ֶ���ʧ�ܣ������쳣��Ϣ" + e.getMessage());
		}
	}

	public static void closeBrowser() {
		try {
			Log.info("�ر����������");
			driver.close();
		} catch (Exception e) {
			Log.info("�ر�����������쳣�������쳣��Ϣ" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void closeAllBrowser() {
		try {
			Log.info("�ر����������");
			driver.quit();
		} catch (Exception e) {
			Log.info("�ر�����������쳣�������쳣��Ϣ" + e.getMessage());
			e.printStackTrace();
		}
	}
	public void BrowserWindowsMax(){
		driver.manage().window().maximize();
	}
	
	public void SetBrowserWindows(int x,int y){
		Dimension dimesion=new Dimension(x,y);
		driver.manage().window().setSize(dimesion);
	}
	
	public void FreshPage(){
		driver.navigate().refresh();
	}
	/*ģ���������ǰ������*/
	public void ForwardPage(){
		driver.navigate().forward();
	}
	/*ģ��������ĺ��˹���*/
	public void BackPage(){
		driver.navigate().back();
	}
	
	public static void OpenLinkWindow(String elementString,String string){
		//����ǰ��������ڵľ���洢��parentWindowHandle������
		String parentWindowHandle=driver.getWindowHandle();
		//�ҵ�ҳ����Ψһ������Ԫ�أ��洢��link������
		WebElement link;
		try {
			link = driver.findElement(objectMap.getLocator(elementString));
			//��������
			link.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ȡ��ǰ���д򿪴��ڵľ�����洢��һ��set������
		Set<String> allWindowHandles=driver.getWindowHandles();
		//�洢����Ϊ�գ����������е����ж���
		if(!allWindowHandles.isEmpty()){
			for(String allWindowHandle:allWindowHandles){
				//��ȡҳ��ı������ԣ��ж�����ֵ�Ƿ�string�����ؼ���
				String title=driver.switchTo().window(allWindowHandle).getTitle();
				if(title.equals(string)){
					Log.info("�жϳ���"+string);
					break;
				}else{
					Log.info("�жϲ�����"+string);
					break;
				}
			}
		}
		//�����ʼ�򿪵������ҳ��
		driver.switchTo().window(parentWindowHandle);
	}
	
	public static void getScreenshot(){
	      Date now = new Date();
	      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh-mm-ss");
	 
	    
		//�ѵ�ǰ�������ҳ����н�ͼ�����浽File������
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			//��File�����ļ�ת����һ��png�ļ������浽c����
			FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\screenshots\\"+ft.format(now)+"_report.png"));
			Log.info("����ͼƬ�ļ��ɹ�");
		} catch (IOException e) {
			Log.info("����ͼƬ�ļ�ʧ��");
			e.printStackTrace();
		}
	}
	
		public static void switchToFrame(String elementString){
		try{
			//��ȡǶ��ʽ��ܶ��󣬽���������
			WebElement iframe=driver.findElement(objectMap.getLocator(elementString));
			driver.switchTo().frame(iframe);
			System.out.println("�ڿ����");
			Log.info("����"+elementString+"��ܳɹ�");
		}catch(Exception e){
			Log.info("����"+elementString+"���ʧ��");
			e.printStackTrace();
		}
	}
	public static void switchToAlert(){
		try{
			//��ȡAlert����
			Alert alert=driver.switchTo().alert();
			//����ȷ����ť
			alert.accept();
			//����ȡ����ť
			//alert.dismiss();
		}catch(Exception e){
			Log.info("���Բ�����ʧ��");
			e.printStackTrace();
		}
	}

	public static void handleCookie(){
		Set<Cookie>cookies=driver.manage().getCookies();
		for(Cookie cookie:cookies){
			System.out.println("���ڵ���:"+cookie.getDomain());
			System.out.println("name:"+cookie.getName());
			System.out.println("value:"+cookie.getValue());
			System.out.println("��Ч����:"+cookie.getExpiry());
			System.out.println("·��:"+cookie.getPath());
			//ͨ������,ɾ��Cookie
			Cookie newCookie=new Cookie(cookie.getName(),cookie.getValue());
			driver.manage().deleteCookie(newCookie);		
		}
		/*
		 * ɾ��Cookie����1ͨ������
		 * driver.manage().deleteCookieNamed("");
		 * ɾ��Cookie����3ɾ������
		 * driver.manage().deleteAllCookies();
		 */
		try {
			Thread.sleep(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isElementExist(String elementString,WebElement string) {
		try {
			string=driver.findElement(objectMap.getLocator(elementString));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(string!=null){
			Log.info("������Ԫ���ҵ�");
			return true;
		}else{
			Log.info("������Ԫ��û���ҵ�");
			return false;
		}
	}

	public void assertWebElementText(String elementString,String string){
		//�������
		WebElement text;
		try {
			text = driver.findElement(objectMap.getLocator(elementString));
			String contentText=text.getText();
			//��ȫƥ��
			Assert.assertEquals(contentText, string);
			//���������Ƿ����String
			Assert.assertTrue(contentText.contains(string));
			//�������ݿ�ʼ�����Ƿ���String
			Assert.assertTrue(contentText.startsWith(string));
			//�������ݽ��������Ƿ���String
			Assert.assertTrue(contentText.endsWith(string));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void WaitForElement(String elementString) {

		try {
			// ���÷�װ��waitWebElement������ʾ�ȴ�ҳ��Ԫ���Ƿ����
			WaitUtil.waitWebElement(driver, elementString);
			Log.info("��ʾ�ȴ�Ԫ�س��ֳɹ���Ԫ����" +elementString);
		} catch (Exception e) {
			Log.info("��ʾ�ȴ�Ԫ�س����쳣�������쳣��Ϣ" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void CheckSlectText(String elementString,String[] string){
		try {
			Select dropList=new Select(driver.findElement(objectMap.getLocator(elementString)));
			//�趨��ѡ�б������ֵ
			List<String>expect_option=new ArrayList<String>();
			expect_option.add(string.toString());
			//��ȡ��ѡ�б��ʵ��ֵ�洢��list�б���
			List<String>actual_option=new ArrayList<String>();
			for(WebElement option:dropList.getOptions()){
				actual_option.add(option.getText());
				
			}
				//�ж�����ֵ�Ƿ���ʵ��ֵһ��
				if(expect_option.toArray().equals(actual_option.toArray())){
					Log.info("����ֵ��ʵ��ֵһ��"+expect_option.toArray());
					
				}else{
					Log.info("����ֵ��ʵ��ֵ��һ��"+actual_option.toArray());
					
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	//ѡ�и�ѡ��
	public void SelectCheckBox(String elementString,String string){
		//��ȡ��ѡ��Ԫ�ض���
		WebElement checkbox;
		try {
			checkbox = driver.findElement(objectMap.getLocator(elementString));
			if(!checkbox.isSelected()){
				checkbox.click();
				Log.info("ѡ�и�ѡ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//ȡ����ѡ��
	public void NoSelectCheckBox(String elementString,String string){
		//��ȡ��ѡ��Ԫ�ض���
		WebElement checkbox;
		try {
			checkbox = driver.findElement(objectMap.getLocator(elementString));
			if(checkbox.isSelected()){
				checkbox.click();
				Log.info("ȡ��ѡ�и�ѡ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//ѡ��ȫ����ѡ��
	public void AllSelectCheckBox(String elementString,String string){
		List<WebElement> checkboxs;
		try {
			checkboxs = driver.findElements(objectMap.getLocator(elementString));
			for(WebElement checkbox:checkboxs ){
				checkbox.click();
				//checkbox.click();ȡ��ȫ��ѡ�еĸ�ѡ��
				Log.info("ȫ��ѡ�и�ѡ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SelectRadio(String elementString) {
		WebElement radio;
		try {
			radio = driver.findElement(objectMap.getLocator(elementString));
			if (!radio.isSelected()) {
				radio.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//ȡ����ѡ��
	public void NoSelectRadio(String elementString){
		WebElement radio;
		try {
			radio = driver.findElement(objectMap.getLocator(elementString));
			if (radio.isSelected()) {
				radio.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//ѡ��ȫ����ѡ��
	public void AllSelectCheckbox(String elementString){
		List<WebElement> checkboxs;
		try {
			checkboxs = driver.findElements(objectMap.getLocator(elementString));
			for(WebElement checkbox:checkboxs){
				if(!checkbox.isSelected()){
					checkbox.click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void DropList(String elementString,String string){
		try {
			Select dropList=new Select(driver.findElement(objectMap.getLocator(elementString)));
			//�ж������б��Ƿ�֧�ֶ�ѡ,֧�ֶ�ѡ����true
			boolean flag=dropList.isMultiple();
			if(flag==true){
				dropList.selectByValue(string);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void NoDropList(String elementString,String string){
		try {
			Select dropList=new Select(driver.findElement(objectMap.getLocator(elementString)));
			//�ж������б��Ƿ�֧�ֶ�ѡ,֧�ֶ�ѡ����true
			boolean flag=dropList.isMultiple();
			if(flag==true){
				dropList.deselectByValue(string);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void OneDropList(String elementString,String string){
		try {
			Select dropList=new Select(driver.findElement(objectMap.getLocator(elementString)));
			boolean flag=dropList.isMultiple();
			if(flag==false){
				Log.info("���뵥ѡ�����б�");
				dropList.selectByValue(string);
				/* ͨ��ѡ���б���±�ֵ����ѡ��
				 * dropList.selectByIndex(0);
				 * ͨ��ѡ������ֽ���ѡ��
				 * dropList.selectByVisibleText(string);
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void DoubleClick(String elementString){
		WebElement doubleClick;
		try {
			//��ȡ�������Ķ���
			doubleClick=driver.findElement(objectMap.getLocator(elementString));
			//����Actions����
			Actions action=new Actions(driver);
			//˫��Ԫ�ض���
			action.doubleClick(doubleClick).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean GetTitle(String Title){
		String getTitle=driver.getTitle();
		if(getTitle.contains(Title)){
			Log.info("��ת��ҳ��"+getTitle);
			return true;
		}else{
			Log.info("û����ת��ҳ��"+getTitle);
			return false;
		}
	}

	public void LeftClickAndRelease(String elementString){
		try {
			//��ȡ������������
			WebElement leftmouse=driver.findElement(objectMap.getLocator(elementString));
			//����Actions����
			Actions action=new Actions(driver);
			//����������
			action.clickAndHold(leftmouse).perform();
			//��ͣ2���ͷ�������
			WaitUtil.sleep(2000);
			action.release(leftmouse);
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	
	public void RightClick(String elementString){
		try {
			//��ȡ������������
			WebElement rightmouse=driver.findElement(objectMap.getLocator(elementString));
			//����Actions����
			Actions action=new Actions(driver);
			//��ͣ3�뵥������Ҽ�
			WaitUtil.sleep(3000);
			action.contextClick(rightmouse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void OverMouseLink(String elementString){
		//��ȡ������������
		try {
			WebElement mouselink=driver.findElement(objectMap.getLocator(elementString));
			//����Actions����
			Actions action=new Actions(driver);
			//����ڳ�����������
			action.moveToElement(mouselink).perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static void scrollToElement(WebDriver driver, WebElement element) {
        WaitUtil.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
 
    }

    public static boolean clickByJS(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click()", element);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception Ex) {
            return false;
        }
    }

    public static void acceptAlert(WebDriver driver) {
        if (isAlertPresent(driver)) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
    }

    public static void cancelAlert(WebDriver driver) {
        if (isAlertPresent(driver)) {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        }
    }

}
