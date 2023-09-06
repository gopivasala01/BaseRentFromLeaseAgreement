package PDFDataExtract;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import mainPackage.PDFReader;
import mainPackage.RunnerClass;

public class NorthCarolina_Format1 
{
	public static boolean petFlag;
	public static String text="";
	public static boolean format1() throws Exception
	//public static void main(String args[]) throws Exception
	{
		
		File file = RunnerClass.getLastModified();
		//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\Lease_02.22_02.23_200_Doc_Johns_Dr_ATX_Smith (3).pdf");
		FileInputStream fis = new FileInputStream(file);
		PDDocument document = PDDocument.load(fis);
	    text = new PDFTextStripper().getText(document);
	    text = text.replaceAll(System.lineSeparator(), " ");
	    text = text.trim().replaceAll(" +", " ");
	    System.out.println(text);
	    System.out.println("------------------------------------------------------------------");
	    try
	    {
	    	PDFReader.commencementDate = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_commencementDate_Prior)+PDFAppConfig.NorthCarolina_Format1.AB_commencementDate_Prior.length());
	    	PDFReader.commencementDate = PDFReader.commencementDate.substring(0, PDFReader.commencementDate.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_commencementDate_After)).trim();
	    	PDFReader.commencementDate = PDFReader.commencementDate.trim().replaceAll(" +", " ");
	    }
	    catch(Exception e)
	    {
	    	PDFReader.commencementDate = "Error";
	    	e.printStackTrace();
	    }
	    System.out.println("Commensement Date = "+PDFReader.commencementDate);
	   try
	    {
		   String expirationDateWaw = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_expirationDate_Prior)+PDFAppConfig.NorthCarolina_Format1.AB_expirationDate_Prior.length());
		   PDFReader.expirationDate =expirationDateWaw.substring(0,expirationDateWaw.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_expirationDate_After)).trim();
		   PDFReader.expirationDate = PDFReader.expirationDate.trim().replaceAll(" +", " ");
	    }
	    catch(Exception e)
	    {
	    	 PDFReader.expirationDate = "Error";
	    	 e.printStackTrace();
	    }
	   System.out.println("Expiration Date = "+PDFReader.expirationDate);
	   try
	    {
		    PDFReader.proratedRent = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_proratedRent_Prior)+PDFAppConfig.NorthCarolina_Format1.AB_proratedRent_Prior.length(),text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_proratedRent_After));
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
	   System.out.println("Prorated Rent = "+PDFReader.proratedRent);
	    try
	    {
		    PDFReader.proratedRentDate = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_proratedRentDate_Prior)+PDFAppConfig.NorthCarolina_Format1.AB_proratedRentDate_Prior.length(),text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_proratedRentDate_After)).trim();
	    }
	    catch(Exception e)
	    {
	    	PDFReader.proratedRentDate = "Error";
	    	e.printStackTrace();
	    }
	    System.out.println("Prorated Rent Date= "+PDFReader.proratedRentDate.trim());
	    /*
	    try
	    {
		    PDFReader.monthlyRentDate = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_fullRentDate_Prior)+PDFAppConfig.NorthCarolina_Format1.AB_fullRentDate_Prior.length(),text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_fullRentDate_After));
		    System.out.println("Monthly Rent Date= "+PDFReader.monthlyRentDate.trim());
	    }
	    catch(Exception e)
	    {
	    	try
	    	{
	    		PDFReader.monthlyRentDate = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_fullRentDate_Prior)+PDFAppConfig.NorthCarolina_Format1.AB_fullRentDate_Prior.length(),text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_fullRentDate1_After));
			   	System.out.println("Monthly Rent Date= "+PDFReader.monthlyRentDate.trim());
	    	}
	    	catch(Exception e1)
		    {
		    	PDFReader.monthlyRentDate = "Error";  
		    	e1.printStackTrace();
		    }
	    }*/
	    try
	    {
		    PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_fullRent_Prior)+PDFAppConfig.NorthCarolina_Format1.AB_fullRent_Prior.length()).trim().split(" ")[0].trim();//,text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_fullRent_After)).substring(1).replaceAll("[^.0-9]", "");;
		    if(RunnerClass.onlyDigits(PDFReader.monthlyRent.replace(".", "").replace(",", ""))==false)
		    {
		    	PDFReader.monthlyRent = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format1.AB_fullRent2_Prior)+PDFAppConfig.NorthCarolina_Format1.AB_fullRent2_Prior.length()).trim().split(" ")[0].trim();
		    }
		    if(PDFReader.monthlyRent.contains("*"))
		    {
		    	PDFReader.monthlyRent = PDFReader.monthlyRent.replace("*","");
		    }
		    if(PDFReader.monthlyRent.matches(".*[a-zA-Z]+.*"))
		    {
		    	PDFReader.monthlyRent = "Error";
		    }
		    if(PDFReader.monthlyRent.endsWith(","))
		    {
		    	PDFReader.monthlyRent = PDFReader.monthlyRent.substring(0,PDFReader.monthlyRent.length()-1);
		    }
	    }
	    catch(Exception e)
	    {
	    	 PDFReader.monthlyRent = "Error";
	    	 e.printStackTrace();
	    }
	    System.out.println("Monthly Rent "+PDFReader.monthlyRent.trim());
	    
	  //Increased Rent Check
	    try
	    {
	    	if(PDFReader.monthlyRent.contains("*")||text.contains(PDFAppConfig.NorthCarolina_Format2.monthlyRentAvailabilityCheck)==true)
	    	{
	    		PDFReader.incrementRentFlag = true;
	    		PDFReader.monthlyRent = PDFReader.monthlyRent.replace("*", "");
	    		System.out.println("Monthly Rent has Asterick *");
	    		
	    		//PDFReader.increasedRent_amount = text.substring(text.indexOf(". $")+". $".length()).trim().split(" ")[0];
	    		String increasedRent_ProviousRentEndDate = "Per the Landlord, Monthly Rent from "+PDFReader.commencementDate.trim()+", through ";
	    		 //String endDateArray[] = text.substring(text.indexOf(". $")+". $".length()).split(" ");
	    		//if(endDateArray[2].trim().length()==4)//&&RunnerClass.onlyDigits(endDateArray[2]))
	    		 //{
	    		  PDFReader.increasedRent_previousRentEndDate = text.substring(text.indexOf(increasedRent_ProviousRentEndDate)+increasedRent_ProviousRentEndDate.length(),text.indexOf(" shall be $"));
	    				  //endDateArray[0]+" "+endDateArray[1]+" "+endDateArray[2];
	    		  System.out.println("Increased Rent - Previous rent end date = "+PDFReader.increasedRent_previousRentEndDate);
	    		 
	    		  String newRentStartDate[] = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format2.increasedRent_newStartDate_Prior)+PDFAppConfig.NorthCarolina_Format2.increasedRent_newStartDate_Prior.length()).trim().split(" ");
	    		  PDFReader.increasedRent_newStartDate = newRentStartDate[0]+" "+newRentStartDate[1]+" "+newRentStartDate[2];
	    		  System.out.println("Increased Rent - New Rent Start date = "+PDFReader.increasedRent_newStartDate);
	    		  
	    		  String increasedRentRaw = text.substring(text.indexOf(PDFAppConfig.NorthCarolina_Format2.increasedRent_newStartDate_Prior)+PDFAppConfig.NorthCarolina_Format2.increasedRent_newStartDate_Prior.length()).trim();
	    		  PDFReader.increasedRent_amount = increasedRentRaw.substring(increasedRentRaw.indexOf("shall be $")+"shall be $".length()).trim().split(" ")[0];
	    		  System.out.println("Increased Rent - Amount = "+PDFReader.increasedRent_amount); 
	    	}
	    }
	    catch(Exception e)
	    {
	    	
	    }
	    
		return true;		
	}




}
