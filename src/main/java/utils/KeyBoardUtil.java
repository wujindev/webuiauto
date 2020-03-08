package utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyBoardUtil {
	//��tab���ķ�װ����
	public static void pressTabKey(){
		Robot robot=null;
		try {
			robot=new Robot();
		} catch (AWTException e) {
			
			e.printStackTrace();
		}
		//����KeyPress�ķ���ʵ�ְ���tab��
		robot.keyPress(KeyEvent.VK_TAB);
		//����KeyPress�ķ���ʵ���ͷ�tab��
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	//��Enter���ķ�װ����
	public static void pressEnterKey(){
		Robot robot=null;
		try {
			robot=new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����KeyPress�ķ���ʵ�ְ���Enter��
		robot.keyPress(KeyEvent.VK_ENTER);
		//����KeyPress�ķ���ʵ���ͷ�Enter��
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	/*��ָ�����ַ�����Ϊ���а�����ݣ�Ȼ��ִ���������
	 * ��ҳ�潹���л��������󣬵��ô˺������Խ�ָ�����ַ���������������
	 */
	public static void setAndCtrlVClipboardData(String string){
		StringSelection	stringSelection=new	StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Robot robot=null;
		try {
			robot=new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����4�д����ʾ���º��ͷ�Ctrl+V��ϼ�
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
	}
	
	
}
