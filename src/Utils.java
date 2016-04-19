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
	public static Individual searchIndiById(ArrayList<Individual> individuals, String id_to_search)
	{
		 
		Individual indi_to_return=null;
		
		for (Individual indi : individuals)
		{
			if(id_to_search.equalsIgnoreCase(indi.getId_vo().getTagValue())) 
			{
			/*	if(indi.getGivn_vo()!=null)
					System.out.println(indi.getBirthDate_vo().getLevel()+" GIVN"); 
				
				System.out.println(getStringFromVO(indi.getGivn_vo()));*/
				indi_to_return=indi;
				break;
			}
		}
		return indi_to_return;
	}
	
	/**
	 * Method to check that an Individual's dates are all before the current date.
	 * @param indi : Individual to check
	 * 
	 */
	public static void ensureIndividualsDatesBeforeCurrentDate(Individual indi)
	{
		// Check birth date.
		if (indi.getBirthDate_vo() != null)
		{
			VO birthDate = indi.getBirthDate_vo();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			Date currDate = new Date();
			String[] msg=new String[]{""};
			if (!compareDate(birthDate.getTagValue(), sdf.format(currDate), msg, false, true))
			{
				System.out.println(indi.getId_vo().getTagValue() + " " + indi.getName_vo().getTagValue() + " birth date " + birthDate.getTagValue() + " is after the current date.");
			}
		}
		
		// Check death date.
		if (indi.getDeathDate_vo() != null)
		{
			VO deathDate = indi.getDeathDate_vo();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			Date currDate = new Date();
			String[] msg=new String[]{""};
			if (!compareDate(deathDate.getTagValue(), sdf.format(currDate), msg, false, true))
			{
				System.out.println(indi.getId_vo().getTagValue() + " " + indi.getName_vo().getTagValue() + " death date " + deathDate.getTagValue() + " is after the current date.");
			}
		}
	}
	
	/**
	 * Method to check that a Family's dates are all before the current date.
	 * @param fam : Family to check
	 */
	public static void ensureFamilyDatesBeforeCurrentDate(Family fam)
	{
		// Check marriage date.
		if (fam.getMarriageDate_vo() != null)
		{
			VO marriageDate = fam.getMarriageDate_vo();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			Date currDate = new Date();
			String[] msg = new String[]{""};
			if (!compareDate(marriageDate.getTagValue(), sdf.format(currDate), msg, false, true))
			{
				System.out.println(fam.getId_vo().getTagValue() + " marriage date " + marriageDate.getTagValue() + " is after current date.");
			}
		}
		
		if (fam.getDivorceDate_vo() != null)
		{
			VO divorceDate = fam.getDivorceDate_vo();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			Date currDate = new Date();
			String[] msg = new String[]{""};
			
			if (!compareDate(divorceDate.getTagValue(), sdf.format(currDate), msg, false, true))
			{
				System.out.println(fam.getId_vo().getTagValue() + " divorce date " + divorceDate.getTagValue() + " is after current date.");
			}
		}
	}
	
	public static boolean verifyIfSiblings(Family src_fam,ArrayList<Individual> individuals,ArrayList<Family> families)
	{
		
		boolean bresult=false;
		
		if(src_fam.getChildren()!=null)
		{
			Individual src_indi_child=searchPersonBD(individuals,src_fam.getChildren().get(0).getTagValue());
			getSiblings(src_fam,searchPersonBD(individuals,src_fam.getHusband_vo().getTagValue()),src_indi_child,families,individuals);
			bresult=true;
		}
		return bresult;
	}
	
	private static void getSiblings(Family src_fam,Individual src_indi_parent,Individual src_indi_child,ArrayList<Family> families,ArrayList<Individual> individuals)
	{
		for (Family fam : families)
		{
			if(!src_fam.getId_vo().getTagValue().equalsIgnoreCase(fam.getId_vo().getTagValue()))
			{
				String id=fam.getHusband_vo().getTagValue();
				String src_id=src_indi_parent.getId_vo().getTagValue();
				if(src_id.equals(id))
				{
					if(fam.getChildren()!=null)
					{
						Individual indi_sibling = searchPersonBD(individuals,fam.getChildren().get(0).getTagValue());
						long dif=1;
						if(compareDatesGreaterThan(src_indi_child.getBirthDate_vo().getTagValue(),indi_sibling.getBirthDate_vo().getTagValue(),1))		
						{
							System.out.println(src_indi_child.getName_vo().getTagValue() + "src child BD: "+ src_indi_child.getBirthDate_vo().getTagValue());
							System.out.println(indi_sibling.getName_vo().getTagValue() + "sibling BD: "+ indi_sibling.getBirthDate_vo().getTagValue());
							
							System.out.println(src_indi_child.getName_vo().getTagValue() +" "+ "birthday validation less then " +dif+ " year(s) " + "of the sibling " +  indi_sibling.getName_vo().getTagValue());
						}
					}
				
				}
			}	 
		}
	}
	public static boolean verifyIfParentsTooOld(Family fam,ArrayList<Individual> individuals)
	{
		
		boolean bresult=false;
		
		if(fam.getChildren()!=null)
		{
			Individual indi_parent_1 =searchPersonBD(individuals,fam.getHusband_vo().getTagValue());
			Individual indi_parent_2 =searchPersonBD(individuals,fam.getWife_vo().getTagValue());
		
			
			if(compareDateGreaterThan(indi_parent_1,100))
				System.out.println(indi_parent_1.getName_vo().getTagValue() + " parent too old ");
				
		 
			if(compareDateGreaterThan(indi_parent_2,100))
				System.out.println(indi_parent_2.getName_vo().getTagValue() + " parent too old ");
		}
		return bresult;
	}
	
	private static boolean compareDateGreaterThan(Individual indi, long l_src)
	{
		boolean bresult=false;
		
		try
		{
		 	
			SimpleDateFormat formater=new SimpleDateFormat("dd MMM yyyy");
			Date today = Calendar.getInstance().getTime(); 
			String sz_today = formater.format(today);
			long d1=formater.parse(sz_today).getTime();
		 
			long d2=formater.parse(indi.getBirthDate_vo().getTagValue()).getTime();
			long dif=Math.abs((d1-d2)/(1000*60*60*24))/365;
			//System.out.println(Math.abs((d1-d2)/(1000*60*60*24)));
			//System.out.println(dif);
			
			if(dif>=l_src) bresult=true;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return bresult;
	}
	
	private static boolean compareDatesGreaterThan(String sz_date_1,String sz_date_2, long l_src)
	{
		boolean bresult=false;
		
		try
		{
		 	
			SimpleDateFormat formater=new SimpleDateFormat("dd MMM yyyy");
			
			long d1=formater.parse(sz_date_1).getTime();	 
			long d2=formater.parse(sz_date_2).getTime();
			long dif=Math.abs((d1-d2)/(1000*60*60*24))/365;
			
			System.out.println(Math.abs((d1-d2)/(1000*60*60*24)));
			System.out.println(dif);
			
			if(dif<=l_src) bresult=true;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return bresult;
	}
	/**
	 * Method to check if child is born after the marriage of their parents and before their divorce.
	 * @param indi : Child to check
	 * @param fam : Family to check
	 */
	public static void ensureChildBornAfterMarriage(Individual indi, Family fam)
	{
		if (fam.getMarriageDate_vo() != null)
		{
			VO marriageDate = fam.getMarriageDate_vo();
			
			// Check if individual has birth date.
			if (indi.getBirthDate_vo() != null)
			{
				String[] msg = new String[]{""};
				if (!compareDate(marriageDate.getTagValue(), indi.getBirthDate_vo().getTagValue(), msg, false, true))
				{
					System.out.println(indi.getId_vo().getTagValue() + " born into family " + fam.getId_vo().getTagValue() + " before marriage date.");
				}
			}
			
		}
		
		if (fam.getDivorceDate_vo() != null)
		{
			VO divorceDate = fam.getDivorceDate_vo();
			
			// Check if individual has birth date.
			if (indi.getBirthDate_vo() != null)
			{
				String[] msg = new String[]{""};
				if (!compareDate(indi.getBirthDate_vo().getTagValue(), divorceDate.getTagValue(), msg, false, true))
				{
					System.out.println(indi.getId_vo().getTagValue() + " born into family " + fam.getId_vo().getTagValue() + " after divorce date.");
				}
			}
		}
	}
	
	/**
	 * Method to ensure that all ids are unique.
	 * @param individuals : The array of individuals
	 * @param families : The array of families
	 * 
	 */
	public static void ensureAllIDsUnique(ArrayList<Individual> individuals, ArrayList<Family> families){
		ArrayList<String> ids = new ArrayList<String>();
		
		for (Individual indi : individuals){
			String id = indi.getId_vo().getTagValue();
			
			if (ids.contains(id))
			{
				System.out.println(id + " is not unique.");
				continue;
			}
			
			ids.add(id);
		}
		
		for (Family fam : families){
			String id = fam.getId_vo().getTagValue();
			
			if (ids.contains(id))
			{
				System.out.println(id + " is not unique.");
				continue;
			}
			
			ids.add(id);
		}
	}
	
	/**
	 * Method to ensure that each spouse is at least 14 years old.
	 * @param husband
	 * @param wife
	 * @param md - marriage date
	 */
	public static void ensureSpouseIsAtLeast14(Individual husband, Individual wife, String md){				
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		
		try {
			Date marriage_date = sdf.parse(md);
			Calendar cal_marriage = Calendar.getInstance();
			cal_marriage.setTime(marriage_date);
			int marriage_year = cal_marriage.get(Calendar.YEAR);	
			
			Date birth_date_husband = sdf.parse(husband.getBirthDate_vo().getTagValue());
			Calendar cal_bd_husband = Calendar.getInstance();
			cal_bd_husband.setTime(birth_date_husband);
			int birth_year_husband = cal_bd_husband.get(Calendar.YEAR);
			
			Date birth_date_wife = sdf.parse(wife.getBirthDate_vo().getTagValue());
			Calendar cal_bd_wife = Calendar.getInstance();
			cal_bd_wife.setTime(birth_date_wife);
			int birth_year_wife = cal_bd_wife.get(Calendar.YEAR);
			
			if (marriage_year - birth_year_husband < 14){
				System.out.println(husband.getId_vo().getTagValue() + " married before he was 14");
			}
			
			if (marriage_year - birth_year_wife < 14){
				System.out.println(wife.getId_vo().getTagValue() + " married before she was 14");
			}
			
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
	}
	
	public static String calculateAge(Individual indi)
	{
		long dif=0;
		try
		{
			SimpleDateFormat formater=new SimpleDateFormat("dd MMM yyyy");
			
			Date today = Calendar.getInstance().getTime(); 
			String sz_today = formater.format(today);
			long d1=formater.parse(sz_today).getTime();
		 
			long d2=formater.parse(indi.getBirthDate_vo().getTagValue()).getTime();
			dif=Math.abs((d1-d2)/(1000*60*60*24))/365;
			 
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ""+dif;
	}
}
