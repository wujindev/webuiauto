package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectMap {

	private static String elementPath=System.getProperty("user.dir")+"\\src\\main\\resources\\conf\\ObjectMap.properties";

	
	
	
	public static By getLocator(String elementName) throws Exception {
		String locator = CommonUtil.getPropValue(elementPath, elementName);
		// �����ö����еĶ�λ���ʹ洢��locatorType�����У�����λ���ʽ��ֵ�洢��locatorValue��
		// ��ȡ�ļ�ֵ�ַ���ͨ��>�ָ�
		String locatorType = locator.split("=>")[0];//��λ����
		String locatorValue = locator.split("=>")[1];//��λ���ʽ
		locatorValue = new String(locatorValue.getBytes("ISO-8859-1"), "UTF-8");
		// ���locatorType��locatorValue����ֵ����֤��ֵ�Ƿ���ȷ
		System.out.println("��ȡ�Ķ�λ����:" + locatorType + "\t��ȡ�Ķ�λ���ʽ:" + locatorValue);
		// ����locatorType�ı���ֵ�����жϷ��غ��ֶ�λ��ʽ��by����
		if (locatorType.toLowerCase().equals("id")) {
			return By.id(locatorValue);
		} else if (locatorType.toLowerCase().equals("name")) {
			return By.name(locatorValue);

		} else if (locatorType.toLowerCase().equals("classname") || locatorType.toLowerCase().equals("class")) {
			return By.className(locatorValue);

		} else if (locatorType.toLowerCase().equals("tagname") || locatorType.toLowerCase().equals("tag")) {
			return By.tagName(locatorValue);

		} else if (locatorType.toLowerCase().equals("linktext") || locatorType.toLowerCase().equals("link")) {
			return By.linkText(locatorValue);

		} else if (locatorType.toLowerCase().equals("partiallinktext")) {
			return By.partialLinkText(locatorValue);

		} else if (locatorType.toLowerCase().equals("cssselector") || locatorType.toLowerCase().equals("css")) {
			return By.cssSelector(locatorValue);

		} else if (locatorType.toLowerCase().equals("xpath")) {
			return By.xpath(locatorValue);

		} else
			throw new Exception("�����locatorTypeδ�ڳ����ж���:" + locatorType);

	}
}