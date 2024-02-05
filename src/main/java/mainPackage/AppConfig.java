package mainPackage;

public class AppConfig 
{
	public static boolean saveButtonOnAndOff= false;
	
	   public static String URL ="https://app.propertyware.com/pw/login.jsp";
	   public static String username ="mds0418@gmail.com";
	   public static String password ="KRm#V39fecMDGg#";
	   
	   public static String excelFileLocation = "C:\\Users\\gopi\\Documents\\BaseRent Update Files";
	   public static String downloadFilePath = "C:\\Users\\gopi\\Documents\\BaseRent Update Files\\master";
	   public static String logFilePath = "C:\\Users\\gopi\\Documents\\BaseRent Update Files\\LogsFolder";
	   
	   public static String pdfImage = "C:\\Users\\gopi\\Documents\\Base Rent\\";
	   
	   public static String buildingPageURL = "https://app.propertyware.com/pw/leases/lease_detail.do?entityID=";
	   //Mail credentials
	   public static String fromEmail = "bireports@beetlerim.com";
	   public static String fromEmailPassword = "Welcome@123";
	   
	   public static String toEmail = "gopi.v@beetlerim.com";
	   public static String CCEmail = "gopi.v@beetlerim.com";
	   
	   public static String mailSubject = "Base Rent Update for  ";
	   
	   public static String[] LeaseAgreementFileNames = {"Full Lease","Full_Lease","Full lease","Full_lease","RT_Full_Lease","RT Full Lease","Lease Renewal","Lease_Renewal","Renewal Lease","Renewal_Lease","RENEWAL","Renewal_","IAG Lease Renewal","IAG_Lease_Renewal","REVISED_Lease_","Lease_","Leases_","_Lease",};
	   
	   public static String[] LeaseModificationFileNames = {"Lease Modification","Lease_Modification","_MOD","_Mod"};
	   
	   public static String[] IAGClientList = {"510.","AVE.","BTH.","CAP.","FOR.","HRG.","HS.","MAN.","MCH.","OFF.","PIN.","RF.","SFR3.","TH.","HH.","Lofty.Ai","TA."};
	   
	   public static String connectionUrl = "jdbc:sqlserver://azrsrv001.database.windows.net;databaseName=HomeRiverDB;user=service_sql02;password=xzqcoK7T;encrypt=true;trustServerCertificate=true;";
	   
	  // public static String leaseFetchQuery  = "Select Company, Building,leaseName from Automation.InitialRentsUpdate where Status ='Pending' and Company ='Georgia'";
	   
	   public static String pendingLeasesQuery = "Select ID, Company, LeaseEntityID,DateDiff(Day,MoveInDate,Getdate()) as datedifference,moveInDate,Status from Automation.BaseRentFromLeaseAgreements where  Company ='Indiana' and Status in ('Active','Active - Notice Given','Active - TTO','Active - Month to Month') and Automation_Status is null";//and Automation_Notes in (',Unable to download Lease Agreement') "; //
	   
	   public static String failedLeasesQuery = "Select Company, LeaseEntityID,DateDiff(Day,MoveInDate,Getdate()) as datedifference,moveInDate from Automation.BaseRentFromLeaseAgreements where  Company='Alabama' and Status ='Failed'";
	   
	   public static String getLeasesWithStatusforCurrentDay = "Select Company, Building,ThirdPartyUnitID, Leaseidnumber, LeaseName,LeaseStatus,leaseExecutionDate, StartDate, EndDate, MonthlyRent, MonthlyRentFromPW, PetRent, PetRentFromPW,Status, Notes from Automation.InitialRentsUpdate and company in ('Tennessee') ";//where Format(convert(datetime, CompletedDate, 101),'dd MM yyyy') = format(getdate(),'dd MM yyyy') ";//and company in ('Florida','North Carolina')";
	   

}
