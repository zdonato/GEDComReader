import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public  class Utils {
	
	public static boolean compareDate(String src_sz,String date_to_campare_sz,String[] msg,boolean bcheckdatebefore,boolean bcheckdateafter)
	{
	 
		boolean bresult=true;
		
		try{
    		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        	 
    		Date src_date = sdf.parse(src_sz);
        	Date date_to_campare = sdf.parse(date_to_campare_sz);

        	//System.out.println("src_date "+sdf.format(src_date));
        	//System.out.println("date_to_campare "+sdf.format(date_to_campare));
        	
        	if(src_date.compareTo(date_to_campare)>0){ //after date1
        		//System.out.println("compareTo response: "+ src_date.compareTo(date_to_campare));
        		//System.out.println(src_sz+" is after "+date_to_campare_sz);
        		if(bcheckdateafter)
        		{
	        		msg[0]="\""+src_sz.trim()+" after "+"\""+date_to_campare_sz.trim()+"\"";
	        		bresult=false;
        		}
        	}else if(src_date.compareTo(date_to_campare)<0){//before date1
        		//	System.out.println("compareTo response: "+ src_date.compareTo(date_to_campare));
        		//System.out.println(src_sz+" is before "+date_to_campare_sz);
        		if(bcheckdatebefore)
        		{
	        		msg[0]="\""+src_sz.trim()+"\" before "+"\""+date_to_campare_sz.trim()+"\"";
	        		bresult=false;
        		}
        	} 
        	else{//src_date.compareTo(date_to_campare)==0
        		//do nothing
        	}
        	
    	}catch(ParseException ex){
    		ex.printStackTrace();
    	}
		return bresult;
	}
	
 
	public static Individual searchPersonBD(ArrayList<Individual> individuals, String id_to_search)
	{
		 
		Individual indi_to_return=null;
		
		for (Individual indi : individuals)
		{
			if(id_to_search.equalsIgnoreCase(indi.getId_vo().getTagValue())) 
			{
				if(indi.getBirthDate_vo()!=null)
					System.out.println(indi.getBirthDate_vo().getLevel()+" BIRT"); 
				System.out.println(getStringFromVO(indi.getBirthDate_vo()));
				indi_to_return=indi;
				break;
			}
		}
		return indi_to_return;
	}
	
	/**
	 * Method to check if an individual is under 150 years old.
	 * @param bd
	 * @param dd - Optional, provide NULL if no death date
	 * @return Returns true if under 150 years old, false otherwise.
	 */
	@SuppressWarnings("deprecation")
	public static Boolean isUnder150YearsOld(String bd, String dd)
	{
		// Grab the current date.
		Calendar cal = Calendar.getInstance();
		int curr_year = cal.get(Calendar.YEAR);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

		try {
			Date birth_date = sdf.parse(bd);
			Calendar cal_birth = Calendar.getInstance();
			cal_birth.setTime(birth_date);
			int birth_year = cal_birth.get(Calendar.YEAR);
			
			if (dd != null)
			{
				Date death_date = sdf.parse(dd);
				Calendar cal_death = Calendar.getInstance();
				cal_death.setTime(death_date);
				int death_year = cal_death.get(Calendar.YEAR);
				
				return (death_year - birth_year) <= 150;
			}
			
			
			return (curr_year - birth_year) <= 150;
			
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static String getStringFromVO(VO src_vo)
	{
		String result="",space=" ";
		
		if(src_vo!=null)
		{
			result=src_vo.getLevel()+space+src_vo.getTagName() +space+src_vo.getTagValue();
		}
		
		return result;
	}
}