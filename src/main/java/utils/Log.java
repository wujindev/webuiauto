package utils;

import org.apache.log4j.Logger;


public class Log {
	
	//��־��¼��
	public static final Logger logger=Logger.getLogger(Log.class.getName());
	// �������������ʼִ�еĴ�ӡ����������־�д�ӡ����������ʼִ�е���Ϣ
	public static void startTestCase(String testCaseName) {
		logger.info("-------------------\"" + testCaseName + "\"��ʼִ��---------------");
	}

	// �����������ִ����Ϻ�Ĵ�ӡ����������־�д�ӡ��������ִ����ϵ���Ϣ
	public static void endTestCase(String testCaseName) {
		logger.info("-------------------\"" + testCaseName + "\"ִ�н���---------------");
	}
	//�����ӡinfo������־�ķ���
	public static void info(String message){
		logger.info(message);
	}
	//�����ӡerror������־�ķ���
	public static void error(String message){
		logger.error(message);
	}
	//�����ӡdebug������־�ķ���
	public static void debug(String message){
		logger.debug(message);
	}
}
