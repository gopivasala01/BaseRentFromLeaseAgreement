package mainPackage;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RunnerClass 
{
	public static String[][] pendingLeases; 
    public static ChromeDriver driver;
    public static String downloadFilePath;
	public static Actions actions;
	public static JavascriptExecutor js;
	public static WebDriverWait wait;
	
	public static String failedReason;
	
    public static String ID;
	public static String company;
	public static String leaseEntityID;
	public static String dateDifference;
	public static String moveInDate;
	
	public static ArrayList<String> autoChargeCodes;
	public static ArrayList<String> autoChargeAmounts;
	public static ArrayList<String> autoChargeStartDate;
	public static ArrayList<String> autoChargeDescription;
	
	public static String[][] completedLeasesList;
	public static String portfolioType = "";
	public static String baseRentAmount ="";
	public static String previousRecordCompany;
	
	public static String PDFFormatType= "";
	public static String baseRent = "";
	
	public static void main(String args[])
	{
		//Get Pending Leases
		DataBase.getLeasesList(AppConfig.pendingLeasesQuery);
		
		//Initial Browser
				PropertyWare.initiateBrowser();
				
				//Login to PW
				PropertyWare.signIn();
				//Loop over leases
				for(int i=0;i<pendingLeases.length;i++)
				{
					try
					{

					ID = pendingLeases[i][0];
					company = pendingLeases[i][1];
					leaseEntityID = pendingLeases[i][2];
					dateDifference = pendingLeases[i][3];
					moveInDate = pendingLeases[i][4].split(" ")[0].trim();
					autoChargeCodes = new ArrayList();
					autoChargeAmounts = new ArrayList();
					autoChargeStartDate = new ArrayList();
					autoChargeDescription = new ArrayList();
					portfolioType = "";
					baseRentAmount ="";
					failedReason ="";
					PDFFormatType= "";
					baseRent = "";
					System.out.println("Lease --"+leaseEntityID+"-- "+(i+1));
				
					if(PropertyWare.selectLease()==false)
					{
						String query = "Update Automation.BaseRentFromLeaseAgreements set Automation_Status='Failed',Automation_Notes='"+failedReason+"',Automation_CompletionDate =getdate() where ID = '"+ID+"'";
						DataBase.updateTable(query);
						previousRecordCompany= company;
						continue;
					}
					if(PropertyWare.downloadLeaseAgreement()==false)
					{
						String query = "Update Automation.BaseRentFromLeaseAgreements set Automation_Status='Failed',Automation_Notes='"+failedReason+"',Automation_CompletionDate =getdate() where ID = '"+ID+"'";
						DataBase.updateTable(query);
						previousRecordCompany= company;
						continue;
					}
					if(PDFReader.readPDFPerMarket(company)==true)
					{
					baseRent = PDFReader.monthlyRent;
					String query = "Update Automation.BaseRentFromLeaseAgreements set BaseRentFromAutoCharges='"+baseRent+"',Automation_Status='Completed',Automation_Notes='"+failedReason+"',Automation_CompletionDate =getdate() where ID = '"+ID+"'";
							
					DataBase.updateTable(query);
					previousRecordCompany= company;
					}
					else
					{
						String query = "Update Automation.BaseRentFromLeaseAgreements set Automation_Status='Failed',Automation_Notes='"+failedReason+"',Automation_CompletionDate =getdate() where ID = '"+ID+"'";
						DataBase.updateTable(query);
						previousRecordCompany= company;
						continue;
					}
					
					previousRecordCompany= company;
					//break;
					
					/*
					if(UpdateBaseRent.getBaseRentAmount()==false)
					{
						String query = "Update Automation.BaseRentFromLeaseAgreements set Automation_Status='Failed',Automation_Notes='"+failedReason+"',Automation_CompletionDate =getdate(),BaseRentFromAutoCharges='"+baseRentAmount+"' where ID = '"+ID+"'";
						DataBase.updateTable(query);
						continue;
					}
					
					UpdateBaseRent.updateBaseRent();
					*/
					//Update table for successful lease
					/*
					try
					{
						System.out.println("Base Rent Updated");
					String query = "Update Automation.BaseRentFromLeaseAgreements set Automation_Status='Completed',Automation_Notes='"+failedReason+"',Automation_CompletionDate =getdate(),BaseRentFromAutoCharges='"+baseRentAmount+"' where ID = '"+ID+"'";
					DataBase.updateTable(query);
					continue;
					}
					catch(Exception e) {}
					*/
					}
					catch(Exception e)
					{
						continue;
					}
					try
					{
						FileUtils.cleanDirectory(new File(AppConfig.downloadFilePath));
					}
					catch(Exception e) {}
					//break;
				}
	}
	
	public static File getLastModified() throws Exception
	{
		
	    File directory = new File(AppConfig.downloadFilePath);
	    File[] files = directory.listFiles(File::isFile);
	    long lastModifiedTime = Long.MIN_VALUE;
	    File chosenFile = null;

	    if (files != null)
	    { 
	        for (File file : files)
	        {
	            if (file.lastModified() > lastModifiedTime)
	            {
	                chosenFile = file;
	                lastModifiedTime = file.lastModified();
	            }
	        }
	    }

	    return chosenFile;
	}
	public static double round(double value, int places) 
	{
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	 public static boolean onlyDigits(String str)
	    {
			str = str.replace(",", "").replace(".", "").trim();
			if(str=="")
				return false;
			int numberCount =0;
	        for (int i = 0; i < str.length(); i++) 
	        {
	            if (Character.isDigit(str.charAt(i))) 
	            {
	            	numberCount++;
	            	//return true;
	            }
	        }
	        if(numberCount==str.length())
	        return true;
	        else
	        return false;
	    }
}
