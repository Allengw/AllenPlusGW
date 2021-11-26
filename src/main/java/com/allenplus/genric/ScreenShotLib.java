package com.allenplus.genric;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotLib {

	/**
	 * @description This method is used to take screenshots
	 * @param driver
	 * @param fileName
	 */
	public String getScreenShot(WebDriver driver, String fileName) 
	{
			TakesScreenshot ts=(TakesScreenshot) driver;
			
			
			File srcFile=ts.getScreenshotAs(OutputType.FILE);
			String dest="C:/Users/ginger/git/AllenDSAT/Screenshots/"+fileName+".png";
			
			File destFile=new File(dest);
			
			//logger.log(LogStatus.INFO, "ScreenShot has been Taken");
			
			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return dest;
	}

}
