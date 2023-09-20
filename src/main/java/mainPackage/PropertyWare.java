package mainPackage;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PropertyWare 
{
	public static boolean initiateBrowser()
	{
		try
		{
		RunnerClass.downloadFilePath = AppConfig.downloadFilePath;
		Map<String, Object> prefs = new HashMap<String, Object>();
	    // Use File.separator as it will work on any OS
	    prefs.put("download.default_directory",
	    		RunnerClass.downloadFilePath);
        // Adding cpabilities to ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--headless");
		WebDriverManager.chromedriver().clearDriverCache().setup();
        RunnerClass.driver= new ChromeDriver(options);
        RunnerClass.driver.manage().window().maximize();
		return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean signIn()
	{
		try
		{
		RunnerClass.driver.get(AppConfig.URL);
        RunnerClass.driver.findElement(Locators.userName).sendKeys(AppConfig.username); 
        RunnerClass.driver.findElement(Locators.password).sendKeys(AppConfig.password);
        Thread.sleep(2000);
        RunnerClass.driver.findElement(Locators.signMeIn).click();
        Thread.sleep(3000);
        RunnerClass.actions = new Actions(RunnerClass.driver);
        RunnerClass.js = (JavascriptExecutor)RunnerClass.driver;
        RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(2));
        try
        {
        if(RunnerClass.driver.findElement(Locators.loginError).isDisplayed())
        {
        	System.out.println("Login failed");
			return false;
        }
        }
        catch(Exception e) {}
        RunnerClass.driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(100));
        return true;
		}
		catch(Exception e)
		{
			System.out.println("Login failed");
			return false;
		}
	}
	
	public static boolean selectLease()
	{
		
		try
		{
			RunnerClass.driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
	        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(100));
	        RunnerClass.driver.navigate().refresh();
	        PropertyWare.intermittentPopUp();
	        //if(PropertyWare.checkIfBuildingIsDeactivated()==true)
	        	//return false;
	        if(RunnerClass.previousRecordCompany==null||!RunnerClass.previousRecordCompany.equals(RunnerClass.company)||RunnerClass.previousRecordCompany.equals(""))
	        {
	        RunnerClass.driver.findElement(Locators.marketDropdown).click();
	        String marketName = "HomeRiver Group - "+RunnerClass.company;
	        Select marketDropdownList = new Select(RunnerClass.driver.findElement(Locators.marketDropdown));
	        marketDropdownList.selectByVisibleText(marketName);
	        Thread.sleep(3000);
	        }
	        String buildingPageURL = AppConfig.buildingPageURL+RunnerClass.leaseEntityID;
	        RunnerClass.driver.navigate().to(buildingPageURL);
	        if(PropertyWare.permissionDeniedPage()==true)
	        {
	        	System.out.println("Wrong Lease Entity ID");
	        	RunnerClass.failedReason = "Wrong Lease Entity ID";
	        	return false;
	        }
	        PropertyWare.intermittentPopUp();
	        if(PropertyWare.checkIfBuildingIsDeactivated()==true)
	        	return false;
	        boolean portfolioCheck = false;
	        try
	        {
	        	String portfolioText = RunnerClass.driver.findElement(Locators.portfolioText).getText();
	        	for(int i=0;i<AppConfig.IAGClientList.length;i++)
	        	{
	        		String portfolioAbbr = AppConfig.IAGClientList[i];
	        		if(portfolioText.startsWith(portfolioAbbr))
	        		{
	        			RunnerClass.portfolioType = "MCH";
	        			portfolioCheck = true;
	        			break;
	        		}
	        	}
	        	if(portfolioCheck == false)
	        		RunnerClass.portfolioType = "Others";
	        }
	        catch(Exception e)
	        {
	        	
	        }
	        
	        return true;
	        /*
	        String buildingAddress = RunnerClass.driver.findElement(Locators.buildingTitle).getText();
	        if(buildingAddress.toLowerCase().contains(RunnerClass.address.substring(0,RunnerClass.address.lastIndexOf(" ")).toLowerCase()))
	        return true;
	        else
	        {
	        	System.out.println("Address it not matched");
	        	RunnerClass.failedReason = "Address is not matched";
	        	return false;
	        }*/
		}
		catch(Exception e)
		{
			RunnerClass.failedReason= "Lease not found";
			return false;
		}
	}
	public static void intermittentPopUp()
	{
		//Pop up after clicking lease name
				try
				{
					RunnerClass.driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
			        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(1));
			        try
			        {
					if(RunnerClass.driver.findElement(Locators.popUpAfterClickingLeaseName).isDisplayed())
					{
						RunnerClass.driver.findElement(Locators.popupClose).click();
					}
			        }
			        catch(Exception e) {}
			        try
			        {
					if(RunnerClass.driver.findElement(Locators.scheduledMaintanancePopUp).isDisplayed())
					{
						RunnerClass.driver.findElement(Locators.scheduledMaintanancePopUpOkButton).click();
					}
			        }
			        catch(Exception e) {}
			        try
			        {
			        if(RunnerClass.driver.findElement(Locators.scheduledMaintanancePopUpOkButton).isDisplayed())
			        	RunnerClass.driver.findElement(Locators.scheduledMaintanancePopUpOkButton).click();
			        }
			        catch(Exception e) {}
					RunnerClass.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));
				}
				catch(Exception e) {}
			
	}
	
	public static void evictionPopUp()
	{
		//Pop up after clicking lease name
				try
				{
					RunnerClass.driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
			        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(1));
			        try
			        {
					if(RunnerClass.driver.findElement(Locators.evictionPopUp).isDisplayed())
					{
						try
						{
							if(RunnerClass.driver.findElement(Locators.evictionNotAcceptPaymentCheckbox).isSelected()&& RunnerClass.driver.findElement(Locators.evictionNotAllowportalCheckbox).isSelected()) {
								RunnerClass.driver.findElement(Locators.saveEvictionPopUp).click();
							}
						}
						catch(Exception e) {}
					}
			        }
			        catch(Exception e) {}
					RunnerClass.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));
				}
				catch(Exception e) {}
			
	}
	
	public static boolean checkIfBuildingIsDeactivated()
	{
		//Pop up after clicking lease name
				try
				{
					RunnerClass.driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
			        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(1));
			        try
			        {
					if(RunnerClass.driver.findElement(Locators.buildingDeactivatedMessage).isDisplayed())
					{
						System.out.println("Building is Deactivated");
						RunnerClass.failedReason = "Building is Deactivated";
						RunnerClass.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
				        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));
			        	return true;
					}
			        }
			        catch(Exception e) {}
			        
					RunnerClass.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));
			        return false;
				}
				catch(Exception e) {}
				return false;
				
	}
	public static boolean permissionDeniedPage()
	{
		try
		{
		RunnerClass.driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(1));
        if(RunnerClass.driver.findElement(Locators.permissionDenied).isDisplayed())
        {
        	RunnerClass.driver.navigate().back();
        	return true;
        }
        RunnerClass.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));
		}
		catch(Exception e)
		{
			return false;
		}
		return false;
	}


	public static boolean downloadLeaseAgreement() throws Exception
	{
		PropertyWare.intermittentPopUp();
		RunnerClass.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));
		
		try
		{
			//RunnerClass.portfolioType = RunnerClass.driver.findElement(Locators.checkPortfolioType).getText();
			//System.out.println("Portfolio Type = "+RunnerClass.portfolioType);
		/*
		int portfolioFlag =0;
		for(int i=0;i<AppConfig.IAGClientList.length;i++)
		{
			if(RunnerClass.portfolioType.startsWith(mainPackage.AppConfig.IAGClientList[i]))
			{
				portfolioFlag =1;
				break;
			}
		}
		
		if(portfolioFlag==1)
			RunnerClass.portfolioType = "MCH";
		else RunnerClass.portfolioType = "Others";
	    System.out.println("Portfolio Type = "+RunnerClass.portfolioType);
		}
	
		catch(Exception e) 
		{
			System.out.println("Unable to fetch Portfolio Type");
			 RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Unable to fetch Portfolio Type";
		
		try
		{
		RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(2000);
		if(RunnerClass.driver.findElement(Locators.leasesTab).getText().equals("Leases"))
		RunnerClass.driver.findElement(Locators.leasesTab).click();
		else 
			RunnerClass.driver.findElement(Locators.leasesTab2).click();
		RunnerClass.driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));
		try
		{
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(By.partialLinkText(ownerName.trim()))).build().perform();
		RunnerClass.driver.findElement(By.partialLinkText(ownerName.trim())).click();
		}
		catch(Exception e2)
		{
			e.printStackTrace();
			System.out.println("Unable to Click Lease Owner Name");
		    RunnerClass.failedReason =  RunnerClass.failedReason+","+  "Unable to Click Lease Onwer Name";
			return false;
		}
		//Pop up after clicking Lease Name
		PropertyWare.intermittentPopUp();
		*/
		RunnerClass.driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(15));
        RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		
		RunnerClass.driver.findElement(Locators.notesAndDocs).click();
		
		List<WebElement> documents = RunnerClass.driver.findElements(Locators.documentsList);
		boolean checkLeaseAgreementAvailable = false;
		 
		for(int i =0;i<documents.size();i++)
		{
			for (int k=0;k<AppConfig.LeaseModificationFileNames.length;k++) {
				if(documents.get(i).getText().startsWith(AppConfig.LeaseModificationFileNames[k])){
					RunnerClass.failedReason =  RunnerClass.failedReason+AppConfig.LeaseModificationFileNames[k]+" document is available";
					 return false;
				}
			}
			for(int j=0;j<AppConfig.LeaseAgreementFileNames.length;j++)
			{
			
			 if((documents.get(i).getText().startsWith(AppConfig.LeaseAgreementFileNames[j])|| documents.get(i).getText().contains(AppConfig.LeaseAgreementFileNames[j]))&&!documents.get(i).getText().contains("Lease Modification")&&!documents.get(i).getText().contains("Lease_Modification")&&!documents.get(i).getText().contains("Termination")&&!documents.get(i).getText().contains("_Mod")&&!documents.get(i).getText().contains("_MOD"))//&&documents.get(i).getText().contains(AppConfig.getCompanyCode(RunnerClass.company)))
			 {
			 	documents.get(i).click();
				checkLeaseAgreementAvailable = true;
				break;
			 }
			 else {
				 if(documents.get(i).getText().contains("MTM Letter") ||documents.get(i).getText().contains("MTM_Letter")) {
					 RunnerClass.failedReason =  RunnerClass.failedReason+"Lease has Month to Month Agreement";
					 return false;
					 
				 }
			 }
			}
			if(checkLeaseAgreementAvailable == true)
				break;
		}
		
		if(checkLeaseAgreementAvailable==false)
		{
			System.out.println("Lease Agreement not found");
		    RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Lease Agreement not found";
			return false;
		}
		Thread.sleep(10000);
		File file = RunnerClass.getLastModified();
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(RunnerClass.driver).withTimeout(Duration.ofSeconds(25)).pollingEvery(Duration.ofMillis(100));
		wait.until( x -> file.exists());
		Thread.sleep(5000);
		return true;
		}
		catch(Exception e3)
		{
			System.out.println("Unable to download Lease Agreement");
		    RunnerClass.failedReason =  RunnerClass.failedReason+","+"Unable to download Lease Agreement";
			return false;
		}
	}
}

