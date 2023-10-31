package mainPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import net.sourceforge.tess4j.Tesseract;

public class pdfTesseractExtraction {
	
	public static String startDate ="";
	public static String endDate ="";
	public static String baseRent ="";
	public static boolean monthlyRentTaxFlag = false;
	public static String monthlyRentTaxAmount = "";
	public static String totalMonthlyRentWithTax = "";

	public static void pdfScreenShot(String market) throws Exception {
		File file = RunnerClass.getLastModified();
		FileInputStream fis = new FileInputStream(file);
		
		PDDocument	pdfDocument = PDDocument.load(fis);
		
		 PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);
		 String AlabamatargetText = "monthly installments,";
			startDate ="";
			 endDate ="";
			 baseRent ="";
			monthlyRentTaxAmount = "";
			totalMonthlyRentWithTax = "";
		 switch(market)
		 {
		 case "Alabama":
		 for (int page = 0; page < pdfDocument.getNumberOfPages(); ++page) {
				 BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
			
   		         File outputFile = new File(AppConfig.pdfImage+"Image.jpeg");
		         ImageIO.write(bim, "jpeg", outputFile);
		        
				  
			     Tesseract tesseract = new Tesseract();

				 tesseract.setDatapath(System.getProperty("user.dir")+"\\tessdata");
  
				 try {
				   String text= tesseract.doOCR(new File(AppConfig.pdfImage+"Image.jpeg"));
				   //System.out.print(text);
				   if(text.contains("(the “Initial Term”) shall commence on")) {
					   String startDateRaw = text.substring(text.indexOf("(the “Initial Term”) shall commence on")+("(the “Initial Term”) shall commence on").length()).trim();
					   startDate = startDateRaw.substring(0, startDateRaw.indexOf('(')).trim();
					   System.out.println("OCR Startdate: "+ startDate);
					   String endDateRaw = text.substring(text.indexOf("location of the Premises) on")+("location of the Premises) on").length()).trim();
					   endDate = endDateRaw.substring(0, endDateRaw.indexOf('(')).trim();
					   if(endDate.contains("_")) {
						   endDate = endDate.replaceAll("_", "");
					   }
					   System.out.println("OCR Enddate: "+endDate);
				   }
				  
				   if(text.contains(AlabamatargetText)) {
					   try {
						   String baseRentRaw = text.substring(text.indexOf("monthly rent in the amount of")+("monthly rent in the amount of ").length()+1).trim().split(" ")[0];
						   baseRent = baseRentRaw.replace("$", "").replace(",","");
					   }
					   catch(Exception e) {
						   PDFReader.monthlyRent = "Error";
						   e.printStackTrace();
					   }
					   //|| text.contains(targetText2)
					   //System.out.println("page Number:  "+page);
					   System.out.println("Base Rent is :"+baseRent);
					   try
					    {
					    	monthlyRentTaxAmount = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.monthlyRentTaxAmount)+PDFAppConfig.Alabama_Format2.monthlyRentTaxAmount.length()).split(" ")[0].trim();
					    	if(monthlyRentTaxAmount.trim().equalsIgnoreCase("0.00")||monthlyRentTaxAmount.trim().equalsIgnoreCase("N/A")||monthlyRentTaxAmount.trim().equalsIgnoreCase("n/a")||monthlyRentTaxAmount.trim().equalsIgnoreCase("na")||monthlyRentTaxAmount.trim().equalsIgnoreCase(""))
					    	{
					    		monthlyRentTaxFlag = false;
					    		
					    	}
					    	else
					    	{
					    		monthlyRentTaxFlag = true;
					    		totalMonthlyRentWithTax = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.totalMonthlyRent2)+PDFAppConfig.Alabama_Format2.totalMonthlyRent2.length()).split(" ")[0].trim();
					    		if(totalMonthlyRentWithTax.matches(".*[a-zA-Z]+.*"))
							    {
					    			totalMonthlyRentWithTax = text.substring(text.indexOf(PDFAppConfig.Alabama_Format2.totalMonthlyRent)+PDFAppConfig.Alabama_Format2.totalMonthlyRent.length()).split(" ")[0].trim();
							    }
					    	}
					    }
					    catch(Exception e)
					    {
					    	monthlyRentTaxFlag = false;
					    	monthlyRentTaxAmount = "";
					    }
					    System.out.println("Monthly Rent Tax Amount from OCR = "+monthlyRentTaxAmount);
				    	System.out.println("Monthly Rent Tax Flag from OCR= "+monthlyRentTaxFlag);
				    	System.out.println("Total Monthly Rent with Tax Amount from OCR= "+totalMonthlyRentWithTax);
					   break;
				   }
						
				   }    
				 catch(Exception e) {
					
				    System.out.println("PDF Read Error");
				   }
				      
	        }
		 break;
		 case "Tenessee":
			 break;
		 case "Georgia":
			 break;
		 case "Florida":
			 break;
		 case "Indiana":	
			 break;
		 case "South Carolina":	 
			 break;
		 case "North Carolina":	 
			 break;
		 case "Savannah": 
		 }
		 // Closing the PDF document
	        pdfDocument.close();
	
		        
		       
	 }
}
