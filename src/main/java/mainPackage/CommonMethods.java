package mainPackage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CommonMethods 
{
	public static String getCalculatedDate(String dateRaw)
	{
		try
		{
			
			// create instance of the SimpleDateFormat that matches the given date  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	          
	        //create instance of the Calendar class and set the date to the given date  
	        Calendar cal = Calendar.getInstance();  
	          cal.setTime(sdf.parse(dateRaw));
	          
	        System.out.println(sdf.format(cal.getTime())+" is the date before adding days");  
	          
	        // use add() method to add the days to the given date  
	        cal.add(Calendar.DAY_OF_MONTH, 62);  
	        String dateAfter = sdf.format(cal.getTime());  
	          
	        //date after adding three days to the given date  
	        System.out.println(dateAfter+" is the date after adding 62 days.");
	        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
	        Date date = sdf.parse(dateAfter.trim());
		    System.out.println(sdf2.format(date));
		    String d = sdf2.format(date).toString();;
			return sdf2.format(date).toString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	public static String getCurrentDate()
    {
    	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		 LocalDateTime now = LocalDateTime.now();  
		// System.out.println(dtf.format(now));
		String d =  dtf.format(now);
		return d;
    }
	
	public static boolean compareBeforeDates(String date1, String date2)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			System.out.println(sdf.parse(date1).before(sdf.parse(date2)));
			if(sdf.parse(date1).before(sdf.parse(date2)))
				return true;
				else return false;
		}
		catch(Exception e)
		{
		return false;
		}
	}
	public static boolean compareAfterDates(String date1, String date2)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			System.out.println(sdf.parse(date1).after(sdf.parse(date2)));
			if(sdf.parse(date1).after(sdf.parse(date2)))
				return true;
				else return false;
		}
		catch(Exception e)
		{
		return false;
		}
	}
	
	public static String convertDate(String dateRaw) throws Exception
	{
		try
		{
		SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd, yyyy");
	    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
	    Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
	    System.out.println(format2.format(date));
		return format2.format(date).toString();
		}
		catch(Exception e)
		{
			try
			{
			SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd yyyy");
		    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
		    Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
		    System.out.println(format2.format(date));
			return format2.format(date).toString();
			}
			catch(Exception e2)
			{
			  if(dateRaw.trim().replaceAll(" +", " ").split(" ")[1].contains("st")||dateRaw.trim().replaceAll(" +", " ").split(" ")[1].contains("nd")||dateRaw.trim().replaceAll(" +", " ").split(" ")[1].contains("th"))
				  dateRaw = dateRaw.trim().replaceAll(" +", " ").replace("st", "").replace("nd", "").replace("th", "");
			  try
				{
				SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd yyyy");
			    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
			    Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
			    System.out.println(format2.format(date));
				return format2.format(date).toString();
				}
				catch(Exception e3)
				{
					try
					{
					SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd,yyyy");
				    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
				    Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
				    System.out.println(format2.format(date));
					return format2.format(date).toString();
					}
					catch(Exception e4)
					{
						try
						{
						SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd.yyyy");
					    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
					    Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
					    System.out.println(format2.format(date));
						return format2.format(date).toString();
						}
						catch(Exception e5)
						{
					return "";
					}
				}
			}
		}
	}
	} 

}
