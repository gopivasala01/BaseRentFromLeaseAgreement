package mainPackage;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import PDFAppConfig.PDFFormatDecider;

public class PDFReader 
{

	public static String commencementDate ="";
    public static String expirationDate ="";
    public static String proratedRent ="";
    public static String proratedRentDate ="";
    public static String monthlyRent="";
    public static String petRentWithTax="";
    public static String petRent="";
    public static String petFee;
    public static String pdfText="";
    public static String securityDeposit="";
    public static String leaseStartDate_PW="";
    public static String leaseEndDate_PW="";
    public static String prepaymentCharge;
    public static ArrayList<String> petType;
    public static ArrayList<String> petBreed;
    public static ArrayList<String> petWeight;
    public static Robot robot;
    public static boolean concessionAddendumFlag = false;
    public static boolean petSecurityDepositFlag = false;
    public static boolean petFlag = false;
    public static String portfolioType="";
    public static boolean incrementRentFlag = false;
    public static boolean proratedRentDateIsInMoveInMonthFlag=false;
    public static String increasedRent_previousRentStartDate ="";
    public static String increasedRent_previousRentEndDate ="";
    public static String increasedRent_amount ="";
    public static String increasedRent_newStartDate ="";
    public static boolean serviceAnimalFlag = false;
    public static ArrayList<String> serviceAnimalPetType;
    public static ArrayList<String> serviceAnimalPetBreed;
    public static ArrayList<String> serviceAnimalPetWeight;
    public static String lateFeeType ="";
    public static String flatFeeAmount ="";
    public static String lateFeePercentage="";
    public static boolean HVACFilterFlag = false;
    public static boolean residentBenefitsPackageAvailabilityCheck = false;
    public static String residentBenefitsPackage = "";
    public static String leaseRenewalFee = "";
    public static String startDate = "";
    public static String endDate = "";
    public static String lastDayOfTheStartDate = "";
    public static String firstFullMonth = "";
    public static String secondFullMonth = "";
    public static String previousMonthlyRent = "";
	public static String adminFee ="";
	public static String airFilterFee="";
	public static String earlyTermination = "";
	public static String occupants = "";
	public static String petSecurityDeposit ="";
	public static String proratedPetRent = "";
	public static String petOneTimeNonRefundableFee = "";
	public static String lateFeeRuleType ="";
	public static String lateChargeDay = "";
	public static String lateChargeFee ="";
	public static String lateFeeChargePerDay = "";
	public static String additionalLateChargesLimit = "";
	public static String additionalLateCharges = "";
	public static String proratePetRentDescription = "";
	public static boolean residentUtilityBillFlag = false; 
	public static String prorateRUBS = "";
	public static String RUBS = "";
	
	public static boolean checkifMoveInDateIsLessThan5DaysToEOM =false;
	public static boolean petInspectionFeeFlag = false;
	//Other Fields
	public static String RCDetails = "";
	public static boolean monthlyRentTaxFlag = false;
	public static String monthlyRentTaxAmount = "";
	public static String totalMonthlyRentWithTax = "";
	
	public static boolean petRentTaxFlag = false;
	public static String petRentTaxAmount = "";
	public static String totalPetRentWithTax = "";
	
		public static boolean readPDFPerMarket(String market) throws Exception  
		{
			//Initialize all PDF data variables
			commencementDate ="";
			expirationDate="";
			monthlyRent="";
			HVACFilterFlag = false;
			residentBenefitsPackageAvailabilityCheck = false;
			residentBenefitsPackage = "";
			proratedRent ="";
		    proratedRentDate ="";
		    petFlag = false;
		    leaseRenewalFee = "";
		    startDate = "";
		    lastDayOfTheStartDate = "";
		    firstFullMonth = "";
		    airFilterFee = "";
		    secondFullMonth = "";
		    petRent ="";
		    incrementRentFlag = false;
		    increasedRent_previousRentEndDate ="";
		    increasedRent_amount ="";
		    increasedRent_newStartDate ="";
		    previousMonthlyRent = "";
		    adminFee ="";
		    airFilterFee="";
		    portfolioType="";
		    petSecurityDeposit ="";
		    proratedPetRent = "";
		    petOneTimeNonRefundableFee = "";
		    lateFeeRuleType ="";
		    petType = new ArrayList();
		    petBreed = new ArrayList();
		    petWeight = new ArrayList();
		    lateChargeDay = "";
		    lateChargeFee ="";
		    lateFeeChargePerDay = "";
		    additionalLateChargesLimit = "";
		    additionalLateCharges = "";
		    proratePetRentDescription = "";
		    proratedRentDateIsInMoveInMonthFlag = false;
		    residentUtilityBillFlag = false; 
		    concessionAddendumFlag = false;
		    prorateRUBS = "";
			RUBS = "";
			checkifMoveInDateIsLessThan5DaysToEOM = false;
			petInspectionFeeFlag = false;
			monthlyRentTaxFlag = false;
			monthlyRentTaxAmount = "";
			totalMonthlyRentWithTax = "";
			petRentTaxFlag = false;
			petRentTaxAmount = "";
			totalPetRentWithTax = "";
		    
		    
		    //Other information
		    //RCDetails = "";
		    earlyTermination = "";
		    occupants = "";
		    serviceAnimalFlag = false;
		    serviceAnimalPetType = new ArrayList();
		    serviceAnimalPetBreed = new ArrayList();
		    serviceAnimalPetWeight = new ArrayList();
		    
			switch(market)
			{
			case "Austin":
				String pdfFormatType_Austin = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_Austin);
				if(pdfFormatType_Austin=="Format1")
				{
					if(PDFDataExtract.Austin_Format1.austin()==false)
						return false;
				}
				
				else 
					if(pdfFormatType_Austin=="Format2")
				     {
					if(PDFDataExtract.Austin_Format2.austin()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				    
				break;
				
			case "California":
				String pdfFormatType_California = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_California);
				if(pdfFormatType_California=="Format1")
				{
					if(PDFDataExtract.California_Format1.format1()==false)
						return false;
				}
				
				else 
					if(pdfFormatType_California=="Format2")
				     {
					if(PDFDataExtract.California_Format2.format2()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				    
				break;
				
			case "California PFW":
				String pdfFormatType_CaliforniaPFW = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_CaliforniaPFW);
				if(pdfFormatType_CaliforniaPFW=="Format1")
				{
					if(PDFDataExtract.CaliforniaPFW_Format1.format1()==false) 
						return false;
				}
				
				else 
					if(pdfFormatType_CaliforniaPFW=="Format2")
				     {
					if(PDFDataExtract.CaliforniaPFW_Format2.format2()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				    
				break;
				
			case "Chattanooga":
				String pdfFormatType_Chattanooga = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_Chattanooga);
				if(pdfFormatType_Chattanooga=="Format1")
				{
					if(PDFDataExtract.Chattanooga_Format1.chattanooga()==false)
						return false;
				}
				
				else 
					if(pdfFormatType_Chattanooga=="Format2")
				     {
					if(PDFDataExtract.Chattanooga_Format2.chattanooga()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
			case "Chicago PFW":
				String pdfFormatType_ChicagoPFW = PDFReader.decidePDFFormat(market);
				System.out.println("PDF Format Type = "+pdfFormatType_ChicagoPFW);
				if(pdfFormatType_ChicagoPFW=="Format1")
				{
					if(PDFDataExtract.ChicagoPFW_Format1.format1()==false)
						return false;
				}
				
				else 
					if(pdfFormatType_ChicagoPFW=="Format2")
				     {
					if(PDFDataExtract.ChicagoPFW_Format2.format2()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				break;
				
			case "Colorado Springs":
				String pdfFormatType_ColoradoSprings = PDFReader.decidePDFFormat(market);
				//System.out.println("PDF Format Type = "+pdfFormatType_ColoradoSprings);
				if(pdfFormatType_ColoradoSprings=="Format1")
				{
					if(PDFDataExtract.ColoradoSprings_Format1.format1()==false)
						return false;
				}
				
				else 
					if(pdfFormatType_ColoradoSprings=="Format2")
				     {
					if(PDFDataExtract.ColoradoSprings_Format2.format2()==false)
						return false;
			        }
				    else 
				   {
					RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
					return false;
				    }
				    
				break;
				
				case "Kansas City":
					String pdfFormatType_KansasCity = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_KansasCity);
					if(pdfFormatType_KansasCity=="Format1")
					{
						if(PDFDataExtract.KansasCity_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_KansasCity=="Format2")
					     {
						if(PDFDataExtract.KansasCity_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
				case "Houston":
					String pdfFormatType_Houston = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Houston);
					if(pdfFormatType_Houston=="Format1")
					{
						if(PDFDataExtract.Houston_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Houston=="Format2")
					     {
						if(PDFDataExtract.Houston_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Maine":
					String pdfFormatType_Maine = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Maine);
					if(pdfFormatType_Maine=="Format1")
					{
						if(PDFDataExtract.Maine_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Maine=="Format2")
					     {
						if(PDFDataExtract.Maine_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Savannah":
					String pdfFormatType_Savannah = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Savannah);
					if(pdfFormatType_Savannah=="Format1")
					{
						if(PDFDataExtract.Savannah_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Savannah=="Format2")
					     {
						if(PDFDataExtract.Savannah_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
				case "North Carolina":
					String pdfFormatType_NorthCarolina = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_NorthCarolina);
					if(pdfFormatType_NorthCarolina=="Format1")
					{
						if(PDFDataExtract.NorthCarolina_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_NorthCarolina=="Format2")
					     {
						if(PDFDataExtract.NorthCarolina_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
				case "Alabama":
					String pdfFormatType_Alabama = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Alabama);
					if(pdfFormatType_Alabama=="Format1")
					{
						if(PDFDataExtract.Alabama_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Alabama=="Format2")
					     {
						if(PDFDataExtract.Alabama_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Arkansas":
					String pdfFormatType_Arkansas = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Arkansas);
					if(pdfFormatType_Arkansas=="Format1")
					{
						if(PDFDataExtract.Arkansas_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Arkansas=="Format2")
					     {
						if(PDFDataExtract.Arkansas_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
				case "Dallas/Fort Worth":
					String pdfFormatType_DFW = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_DFW);
					if(pdfFormatType_DFW=="Format1")
					{
						if(PDFDataExtract.DFW_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_DFW=="Format2")
					     {
						if(PDFDataExtract.DFW_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
				case "Indiana":
					String pdfFormatType_Indiana = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Indiana);
					if(pdfFormatType_Indiana=="Format1")
					{
						if(PDFDataExtract.Indiana_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Indiana=="Format2")
					     {
						if(PDFDataExtract.Indiana_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
				case "Little Rock":
					String pdfFormatType_LittleRock = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_LittleRock);
					if(pdfFormatType_LittleRock=="Format1")
					{
						if(PDFDataExtract.LittleRock_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_LittleRock=="Format2")
					     {
						if(PDFDataExtract.LittleRock_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
				case "San Antonio":
					String pdfFormatType_SanAntonio = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_SanAntonio);
					if(pdfFormatType_SanAntonio=="Format1")
					{
						if(PDFDataExtract.SanAntonio_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_SanAntonio=="Format2")
					     {
						if(PDFDataExtract.SanAntonio_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Tulsa":
					String pdfFormatType_Tulsa = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Tulsa);
					if(pdfFormatType_Tulsa=="Format1")
					{
						if(PDFDataExtract.Tulsa_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Tulsa=="Format2")
					     {
						if(PDFDataExtract.Tulsa_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Georgia":
					String pdfFormatType_Georgia = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Georgia);
					if(pdfFormatType_Georgia=="Format1")
					{
						if(PDFDataExtract.Georgia_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Georgia=="Format2")
					     {
						if(PDFDataExtract.Georgia_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "OKC":
					String pdfFormatType_OKC = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_OKC);
					if(pdfFormatType_OKC=="Format1")
					{
						if(PDFDataExtract.OKC_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_OKC=="Format2")
					     {
						if(PDFDataExtract.OKC_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "South Carolina":
					String pdfFormatType_SouthCarolina = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_SouthCarolina);
					if(pdfFormatType_SouthCarolina=="Format1")
					{
						if(PDFDataExtract.SouthCarolina_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_SouthCarolina=="Format2")
					     {
						if(PDFDataExtract.SouthCarolina_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Florida":
					String pdfFormatType_Florida = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Florida);
					if(pdfFormatType_Florida=="Format1")
					{
						if(PDFDataExtract.Florida_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Florida=="Format2")
					     {
						if(PDFDataExtract.Florida_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Tennessee":
					String pdfFormatType_Tennessee = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Tennessee);
					if(pdfFormatType_Tennessee=="Format1")
					{
						if(PDFDataExtract.Tennessee_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Tennessee=="Format2")
					     {
						if(PDFDataExtract.Tennessee_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "New Mexico":
					String pdfFormatType_NewMexico = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_NewMexico);
					if(pdfFormatType_NewMexico=="Format1")
					{
						if(PDFDataExtract.NewMexico_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_NewMexico=="Format2")
					     {
						if(PDFDataExtract.NewMexico_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Ohio":
					String pdfFormatType_Ohio = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Ohio);
					if(pdfFormatType_Ohio=="Format1")
					{
						if(PDFDataExtract.Ohio_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Ohio=="Format2")
					     {
						if(PDFDataExtract.Ohio_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Pennsylvania":
					String pdfFormatType_Pennsylvania = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Pennsylvania);
					if(pdfFormatType_Pennsylvania=="Format1")
					{
						if(PDFDataExtract.Pennsylvania_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Pennsylvania=="Format2")
					     {
						if(PDFDataExtract.Pennsylvania_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Lake Havasu":
					String pdfFormatType_LakeHavasu = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_LakeHavasu);
					if(pdfFormatType_LakeHavasu=="Format1")
					{
						if(PDFDataExtract.LakeHavasu_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_LakeHavasu=="Format2")
					     {
						if(PDFDataExtract.LakeHavasu_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Saint Louis":
					String pdfFormatType_SaintLouis = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_SaintLouis);
					if(pdfFormatType_SaintLouis=="Format1")
					{
						if(PDFDataExtract.SaintLouis_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_SaintLouis=="Format2")
					     {
						if(PDFDataExtract.SaintLouis_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Maryland":
					String pdfFormatType_Maryland = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Maryland);
					if(pdfFormatType_Maryland=="Format1")
					{
						if(PDFDataExtract.Maryland_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Maryland=="Format2")
					     {
						if(PDFDataExtract.Maryland_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Virginia":
					String pdfFormatType_Virginia = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Virginia);
					if(pdfFormatType_Virginia=="Format1")
					{
						if(PDFDataExtract.Virginia_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Virginia=="Format2")
					     {
						if(PDFDataExtract.Virginia_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Boise":
					String pdfFormatType_Boise = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Boise);
					if(pdfFormatType_Boise=="Format1")
					{
						if(PDFDataExtract.Boise_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Boise=="Format2")
					     {
						if(PDFDataExtract.Boise_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
				case "Idaho Falls":
					String pdfFormatType_IdahoFalls = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_IdahoFalls);
					if(pdfFormatType_IdahoFalls=="Format1")
					{
						if(PDFDataExtract.IdahoFalls_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_IdahoFalls=="Format2")
					     {
						if(PDFDataExtract.IdahoFalls_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
				case "Utah":
					String pdfFormatType_Utah = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Utah);
					if(pdfFormatType_Utah=="Format1")
					{
						if(PDFDataExtract.Utah_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Utah=="Format2")
					     {
						if(PDFDataExtract.Utah_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Spokane":
					String pdfFormatType_Spokane = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Spokane);
					if(pdfFormatType_Spokane=="Format1")
					{
						if(PDFDataExtract.Spokane_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Spokane=="Format2")
					     {
						if(PDFDataExtract.Spokane_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
				case "Washington DC":
					String pdfFormatType_WashingtonDC = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_WashingtonDC);
					if(pdfFormatType_WashingtonDC=="Format1")
					{
						if(PDFDataExtract.WashingtonDC_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_WashingtonDC=="Format2")
					     {
						if(PDFDataExtract.WashingtonDC_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
				case "Hawaii":
					String pdfFormatType_Hawaii = PDFReader.decidePDFFormat(market);
					System.out.println("PDF Format Type = "+pdfFormatType_Hawaii);
					if(pdfFormatType_Hawaii=="Format1")
					{
						if(PDFDataExtract.Hawaii_Format1.format1()==false)
							return false;
					}
					
					else 
						if(pdfFormatType_Hawaii=="Format2")
					     {
						if(PDFDataExtract.Hawaii_Format2.format2()==false)
							return false;
				        }
					    else 
					   {
						RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
						return false;
					    }
					    
					break;
					
			}
			
			//Prepayment charge
			try
			{
				PDFReader.prepaymentCharge =String.valueOf(RunnerClass.round((Double.parseDouble(PDFReader.monthlyRent.replace(",", "")) - Double.parseDouble(PDFReader.proratedRent.replace(",", ""))),2)); 
			}
			catch(Exception e)
			{
				PDFReader.prepaymentCharge ="Error";
			}
			System.out.println("Prepayment Charge = "+PDFReader.prepaymentCharge);
			return true;
			
			
		}
		
		public static String decidePDFFormat(String company) throws Exception
		{
			try
			{
			String format1Text = "";
			String format2Text = "";
			switch(company)
			{
			
			case "Austin":
			    format1Text  = PDFAppConfig.PDFFormatDecider.austin_Format1;
			    format2Text  = PDFAppConfig.PDFFormatDecider.austin_Format2;
			break;
			
			case "California":
		        format1Text = PDFAppConfig.PDFFormatDecider.california_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.california_Format2;
		        break;
		        
			case "California PFW":
		        format1Text = PDFAppConfig.PDFFormatDecider.californiaPFW_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.californiaPFW_Format2;
		        break;
			case "Chicago PFW":
		        format1Text = PDFAppConfig.PDFFormatDecider.ChicagoPFW_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.ChicagoPFW_Format2;
		        break;
			case "Colorado Springs":
		        format1Text = PDFAppConfig.PDFFormatDecider.ColoradoSprings_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.ColoradoSprings_Format2;
		        break; 
			case "Kansas City":
		        format1Text = PDFAppConfig.PDFFormatDecider.KansasCity_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.KansasCity_Format2;
		        break; 
			case "Houston":
		        format1Text = PDFAppConfig.PDFFormatDecider.Houston_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Houston_Format2;
		        break; 
			case "Maine":
		        format1Text = PDFAppConfig.PDFFormatDecider.Maine_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Maine_Format2;
		        break; 
			case "Savannah":
		        format1Text = PDFAppConfig.PDFFormatDecider.Savannah_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Savannah_Format2;
		        break;
			case "North Carolina":
		        format1Text = PDFAppConfig.PDFFormatDecider.NorthCarolina_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.NorthCarolina_Format2;
		        break; 
			case "Alabama":
		        format1Text = PDFAppConfig.PDFFormatDecider.Alabama_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Alabama_Format2;
		        break; 
			case "Arkansas":
		        format1Text = PDFAppConfig.PDFFormatDecider.Arkansas_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Arkansas_Format2;
		        break; 
			case "Dallas/Fort Worth":
		        format1Text = PDFAppConfig.PDFFormatDecider.DFW_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.DFW_Format2;
		        break; 
			case "Indiana":
		        format1Text = PDFAppConfig.PDFFormatDecider.Indiana_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Indiana_Format2;
		        break; 
			case "Little Rock":
		        format1Text = PDFAppConfig.PDFFormatDecider.LittleRock_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.LittleRock_Format2;
		        break; 
			case "San Antonio":
		        format1Text = PDFAppConfig.PDFFormatDecider.SanAntonio_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.SanAntonio_Format2;
		        break; 
			case "Tulsa":
		        format1Text = PDFAppConfig.PDFFormatDecider.Tulsa_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Tulsa_Format2;
		        break; 
			case "Georgia":
		        format1Text = PDFAppConfig.PDFFormatDecider.Georgia_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Georgia_Format2;
		        break; 
			case "OKC":
		        format1Text = PDFAppConfig.PDFFormatDecider.OKC_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.OKC_Format2;
			case "South Carolina":
		        format1Text = PDFAppConfig.PDFFormatDecider.SouthCarolina_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.SouthCarolina_Format2;
			case "Florida":
		        format1Text = PDFAppConfig.PDFFormatDecider.Florida_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Florida_Format2;
			case "Tennessee":
		        format1Text = PDFAppConfig.PDFFormatDecider.Tennessee_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Tennessee_Format2;
			case "New Mexico":
		        format1Text = PDFAppConfig.PDFFormatDecider.NewMexico_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.NewMexico_Format2;
			case "Ohio":
		        format1Text = PDFAppConfig.PDFFormatDecider.Ohio_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Ohio_Format2;
			case "Pennsylvania":
		        format1Text = PDFAppConfig.PDFFormatDecider.Pennsylvania_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Pennsylvania_Format2;
			case "Lake Havasu":
		        format1Text = PDFAppConfig.PDFFormatDecider.LakeHavasu_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.LakeHavasu_Format2;
			case "SaintLouis":
		        format1Text = PDFAppConfig.PDFFormatDecider.SaintLouis_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.SaintLouis_Format2;
			case "Maryland":
		        format1Text = PDFAppConfig.PDFFormatDecider.Maryland_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Maryland_Format2;
			case "Virginia":
		        format1Text = PDFAppConfig.PDFFormatDecider.Virginia_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Virginia_Format2;
			case "Boise":
		        format1Text = PDFAppConfig.PDFFormatDecider.Boise_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Boise_Format2;
			case "Idaho Falls":
		        format1Text = PDFAppConfig.PDFFormatDecider.IdahoFalls_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.IdahoFalls_Format2;
			case "Utah":
		        format1Text = PDFAppConfig.PDFFormatDecider.Utah_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Utah_Format2;
			case "Spokane":
		        format1Text = PDFAppConfig.PDFFormatDecider.Spokane_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Spokane_Format2;
			case "Washington DC":
		        format1Text = PDFAppConfig.PDFFormatDecider.WashingtonDC_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.WashingtonDC_Format2;
			case "Hawaii":
		        format1Text = PDFAppConfig.PDFFormatDecider.Hawaii_Format1;
		        format2Text = PDFAppConfig.PDFFormatDecider.Hawaii_Format2;
			}
			
			File file = RunnerClass.getLastModified();
			//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\Full_Lease_-_[6128_Creekview_Court]_-_[Wallace_-_Crawford]_-_[02.01.2023]_-_[04.30.2024].PDF_(1).pdf");
			System.out.println(file);
			FileInputStream fis = new FileInputStream(file);
			PDDocument document = PDDocument.load(fis);
			String text = new PDFTextStripper().getText(document);
			text = text.replaceAll(System.lineSeparator(), " ");
		    text = text.replaceAll(" +", " ");
		    text = text.toLowerCase();
		    if(text.contains(format1Text.toLowerCase())||text.contains(PDFFormatDecider.format1.toLowerCase())||text.contains(PDFFormatDecider.format1_2.toLowerCase()))
		    {
		    	RunnerClass.PDFFormatType = "Format1";
		    	System.out.println("PDF Format Type  = "+RunnerClass.PDFFormatType);
		    	return "Format1";
		    }
		    
		    else if(text.contains(format2Text.toLowerCase()))
		    {
		    	RunnerClass.PDFFormatType = "Format2";
		    	System.out.println("PDF Format Type = "+RunnerClass.PDFFormatType);
		    	return "Format2";
		    }
		    else return "Error";
			}
			catch(Exception e)
			{
				return "Error";
			}
		}
	  



}
