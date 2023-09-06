package PDFDataExtract;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import mainPackage.PDFReader;
import mainPackage.RunnerClass;

public class Alabama_Format2 
{
	public static String text ="";
	public static  boolean  format2() throws Exception
	//public static void main(String[] args) throws Exception 
	{
		//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\Lease_423_424_2426_Folsom_St_AL_McCay_Gilmo (2).pdf");
		File file = RunnerClass.getLastModified();
		FileInputStream fis = new FileInputStream(file);
		PDDocument document = PDDocument.load(fis);
	    text = new PDFTextStripper().getText(document);
	    text = text.replaceAll(System.lineSeparator(), " ");
	    text = text.trim().replaceAll(" +", " ");
	    //System.out.println(text);
	    System.out.println("------------------------------------------------------------------");
	    
	    try
	    {
	    	String commensementRaw = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.commensementDate_Prior)+PDFAppConfig.Alabama_Format2.commensementDate_Prior.length()+1).trim();//,text.indexOf(PDFAppConfig.Alabama_Format2.commensementDate_After)).trim();
	    	 PDFReader.commencementDate = commensementRaw.substring(0, commensementRaw.indexOf('(')).trim();
	    	 PDFReader.commencementDate = PDFReader.commencementDate.trim().replaceAll(" +", " ");
		    System.out.println("Commensement Date = "+PDFReader.commencementDate);//.substring(commensementDate.lastIndexOf(":")+1));
	    }
	    catch(Exception e)
	    {
	    	PDFReader.commencementDate = "Error";
	    	e.printStackTrace();
	    }
	    
	    try
	    {
	    	String expirationDateRaw  = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.expirationDate_Prior)+PDFAppConfig.Alabama_Format2.expirationDate_Prior.length()).trim();//,text.indexOf(PDFAppConfig.Alabama_Format2.expirationDate_After)).trim();
	    	PDFReader.expirationDate = expirationDateRaw.substring(0,expirationDateRaw.indexOf('(')).trim();
	    	PDFReader.expirationDate = PDFReader.expirationDate.trim().replaceAll(" +", " ");
	    	System.out.println("Expiration Date = "+PDFReader.expirationDate);//.substring(commensementDate.lastIndexOf(":")+1));
	    }
	    catch(Exception e)
	    {
	    	PDFReader.expirationDate = "Error";
	    	e.printStackTrace();
	    }
	    
	    try
	    {
	    	PDFReader.proratedRentDate = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.proratedRentDate_Prior)+PDFAppConfig.Alabama_Format2.proratedRentDate_Prior.length()+1,text.indexOf(PDFAppConfig.Alabama_Format2.proratedRentDate_After)).trim();
		    System.out.println("prorated Rent Date = "+PDFReader.proratedRentDate);//.substring(commensementDate.lastIndexOf(":")+1));
	    }
	    catch(Exception e)
	    {
	    	PDFReader.proratedRentDate = "Error";
	    	e.printStackTrace();
	    }
	    
	    try
	    {
	    	PDFReader.proratedRent = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.proratedRent_Prior)+PDFAppConfig.Alabama_Format2.proratedRent_Prior.length()).split(" ")[0].trim();
	    	if(PDFReader.proratedRent.matches(".*[a-zA-Z]+.*"))
		    {
		    	PDFReader.proratedRent = "Error";
		    }
	    }
	    catch(Exception e)
	    {
	    	PDFReader.proratedRent = "Error";
	    	e.printStackTrace();
	    }
	    System.out.println("prorated Rent = "+PDFReader.proratedRent);//.substring(commensementDate.lastIndexOf(":")+1));
	    
	    try
	    {
	    	PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.monthlyRent_Prior)+PDFAppConfig.Alabama_Format2.monthlyRent_Prior.length()).split(" ")[0].trim();
	    	if(!PDFReader.monthlyRent.contains("."))
	    		PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.monthlyRent_Prior2)+PDFAppConfig.Alabama_Format2.monthlyRent_Prior2.length()).split(" ")[0].trim();
	    	if(PDFReader.monthlyRent.matches(".*[a-zA-Z]+.*"))
		    {
		    	PDFReader.monthlyRent = "Error";
		    }
	    	if(PDFReader.monthlyRent.contains("*")||text.contains(PDFAppConfig.Alabama_Format2.monthlyRentAvailabilityCheck)==true)
	    	{
	    		PDFReader.incrementRentFlag = true;
	    		PDFReader.monthlyRent = PDFReader.monthlyRent.replace("*", "");
	    		System.out.println("Monthly Rent has Asterick *");
	    		
	    		//PDFReader.increasedRent_amount = text.substring(text.indexOf(". $")+". $".length()).trim().split(" ")[0];
	    		String increasedRent_ProviousRentEndDate = "Per the Landlord, Monthly Rent from "+PDFReader.commencementDate.trim()+" through ";
	    		 String endDateArray[] = text.substring(text.indexOf(". $")+". $".length()).split(" ");
	    		if(endDateArray[2].trim().length()==4)//&&RunnerClass.onlyDigits(endDateArray[2]))
	    		 {
	    		  PDFReader.increasedRent_previousRentEndDate = endDateArray[0]+" "+endDateArray[1]+" "+endDateArray[2];
	    		  System.out.println("Increased Rent - Previous rent end date = "+PDFReader.increasedRent_previousRentEndDate);
	    		 
	    		  String newRentStartDate[] = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.increasedRent_newStartDate_Prior)+PDFAppConfig.Alabama_Format2.increasedRent_newStartDate_Prior.length()).trim().split(" ");
	    		  PDFReader.increasedRent_newStartDate = newRentStartDate[0]+" "+newRentStartDate[1]+" "+newRentStartDate[2];
	    		  System.out.println("Increased Rent - New Rent Start date = "+PDFReader.increasedRent_newStartDate);
	    		  
	    		  String increasedRentRaw = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.increasedRent_newStartDate_Prior)+PDFAppConfig.Alabama_Format2.increasedRent_newStartDate_Prior.length()).trim();
	    		  PDFReader.increasedRent_amount = increasedRentRaw.substring(increasedRentRaw.indexOf("shall be $")+"shall be $".length()).trim().split(" ")[0];
	    		  System.out.println("Increased Rent - Amount = "+PDFReader.increasedRent_amount); 
	    		}
	    		else 
	    		 {
	    			 String adding0toMonth = "0"+PDFReader.commencementDate.trim().split(" ")[1];
	    			 String commeseDate = PDFReader.commencementDate.trim().replace(PDFReader.commencementDate.trim().split(" ")[1], adding0toMonth);
	    			 increasedRent_ProviousRentEndDate = "Per the Landlord, Monthly Rent from "+commeseDate+" through ";
		    		 String endDateArray2[] = text.substring(text.indexOf(increasedRent_ProviousRentEndDate)+increasedRent_ProviousRentEndDate.length()).split(" ");
		    		 if(endDateArray2[2].trim().length()==4)//&&RunnerClass.onlyDigits(endDateArray[2]))
		    		 {
		    		  PDFReader.increasedRent_previousRentEndDate = endDateArray2[0]+" "+endDateArray2[1]+" "+endDateArray2[2];
		    		  System.out.println("Increased Rent - Previous rent end date = "+PDFReader.increasedRent_previousRentEndDate);
		    		 
		    		  String newRentStartDate[] = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.increasedRent_newStartDate_Prior)+PDFAppConfig.Alabama_Format2.increasedRent_newStartDate_Prior.length()).trim().split(" ");
		    		  PDFReader.increasedRent_newStartDate = newRentStartDate[0]+" "+newRentStartDate[1]+" "+newRentStartDate[2];
		    		  System.out.println("Increased Rent - New Rent Start date = "+PDFReader.increasedRent_newStartDate);
		    		  
		    		  String increasedRentRaw = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.increasedRent_newStartDate_Prior)+PDFAppConfig.Alabama_Format2.increasedRent_newStartDate_Prior.length()).trim();
		    		  PDFReader.increasedRent_amount = increasedRentRaw.substring(increasedRentRaw.indexOf("shall be $")+"shall be $".length()).trim().split(" ")[0];
		    		  System.out.println("Increased Rent - Amount = "+PDFReader.increasedRent_amount); 
		    		 }
	    		 }
	    	}
	    }
	    catch(Exception e)
	    {
	    	PDFReader.monthlyRent = "Error";
	    	e.printStackTrace();
	    }
	    System.out.println("Monthly Rent = "+PDFReader.monthlyRent);//.substring(commensementDate.lastIndexOf(":")+1));
	    
	    //Monthly Rent Tax Check
	    try
	    {
	    	PDFReader.monthlyRentTaxAmount = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.monthlyRentTaxAmount)+PDFAppConfig.Alabama_Format2.monthlyRentTaxAmount.length()).split(" ")[0].trim();
	    	if(PDFReader.monthlyRentTaxAmount.trim().equalsIgnoreCase("0.00")||PDFReader.monthlyRentTaxAmount.trim().equalsIgnoreCase("N/A")||PDFReader.monthlyRentTaxAmount.trim().equalsIgnoreCase("n/a")||PDFReader.monthlyRentTaxAmount.trim().equalsIgnoreCase("na")||PDFReader.monthlyRentTaxAmount.trim().equalsIgnoreCase(""))
	    	{
	    		PDFReader.monthlyRentTaxFlag = false;
	    	}
	    	else
	    	{
	    		PDFReader.monthlyRentTaxFlag = true;
	    		PDFReader.totalMonthlyRentWithTax = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.totalMonthlyRent)+PDFAppConfig.Alabama_Format2.totalMonthlyRent.length()).split(" ")[0].trim();
	    		if(PDFReader.totalMonthlyRentWithTax.matches(".*[a-zA-Z]+.*"))
			    {
	    			PDFReader.totalMonthlyRentWithTax = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.totalMonthlyRent2)+PDFAppConfig.Alabama_Format2.totalMonthlyRent2.length()).split(" ")[0].trim();
			    }
	    	}
	    }
	    catch(Exception e)
	    {
	    	PDFReader.monthlyRentTaxFlag = false;
	    	PDFReader.monthlyRentTaxAmount = "";
	    }
	    System.out.println("Monthly Rent Tax Amount = "+PDFReader.monthlyRentTaxAmount);
    	System.out.println("Monthly Rent Tax Amount = "+PDFReader.monthlyRentTaxFlag);
    	System.out.println("Monthly Rent Tax Amount = "+PDFReader.totalMonthlyRentWithTax);
		return true;
	}


}
