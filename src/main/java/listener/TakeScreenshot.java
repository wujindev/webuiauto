package listener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenshot {
    WebDriver driver;
    String filePath;

    public TakeScreenshot(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenShot(String classname, String methodname) {
        // ��ȡ��ͼfile
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // ��ͼƬ�ƶ���ָ��λ��
            FileUtils.copyFile(file, new File(getFilePath(classname, methodname)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getFilePath(String classname, String methodname) {
        // ��������ͼƬ��·�����������򴴽�
        File dir = new File(System.getProperty("user.dir")+"\\screenshots\\");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        String dateStr = dateFormat.format(new Date());
        // ��ȡ�µ��ļ���������ʱ�䣬������������
        String fileName = dateStr + "_" + classname + "_" + methodname + ".jpg";
        // ��ȡ�ļ�·��
        String filePath = dir.getAbsolutePath() + "/" + fileName;
        return filePath;

    }
}