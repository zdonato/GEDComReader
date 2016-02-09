/**
 * Zachary Donato
 * 02 February 2016
 * CS555 P02
 * I pledge my honor that I have abided by the Stevens Honor System.
 * 
 * A class to read in a GEDCOM file.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class ProjectFileReader 
{
	
	/**
	 * Constructor.
	 */
	public ProjectFileReader () {} 
	
	
	/**
	 * Private array of acceptable tags for our project.
	 */
	final private static ArrayList<String> ACCEPTED_TAGS = new ArrayList<String>() {{
		add("INDI"); add("NAME");add("GIVN");add("SURN"); add("SEX"); add("BIRT"); add("DEAT");
		add("FAMC"); add("FAMS"); add("FAM"); add("MARR"); add("HUSB");
		add("HUSB"); add("WIFE"); add("CHIL"); add("DIV"); add("DATE");
		add("HEAD"); add("TRLR"); add("NOTE");
	}};
	
	// Constant strings for checking if the tag is an Individual or family.
	final private static String INDI = "INDI";
	final private static String FAM  = "FAM";
	
	/**
	 * Private array to hold the individuals.
	 */
	private static ArrayList<Individual> individuals = new ArrayList<Individual>();
	
	/**
	 * Private array to hold the families.
	 */
	private static ArrayList<Family> families = new ArrayList<Family>();

	/**
	 * Method to read in the GEDCOM file.
	 * @param filename : Relative pathname to the file. 
	 */
	public void ReadFile(String filename) 
	{
		
		String 			inputLine; 
		String 			recordType;
		String			level;
		String 			tag;
		String[] 		splits = new String[5];
		BufferedReader 	in;
		
		try 
		{
			in 			= new BufferedReader(new FileReader(filename));
			inputLine 	= in.readLine();
			
			while (inputLine != null)
			{
				recordType = getRecordType(inputLine);
				
				splits = inputLine.split(" ");
				
				// Create the individual.
				if (recordType.equals(INDI))
				{
					level = "-1";
					Individual new_indi = new Individual();
					
					// Set the id. 
					new_indi.setId_vo("0", INDI, splits[1]);
					
					inputLine = in.readLine();
				
					// Handle each line until the next record.
					while ( !(level.equals("0")) )
					{
						splits = inputLine.split(" ");
						
						
						if (splits.length > 0)
						{
							// Determine tag and then set the correct property.
							if ( ACCEPTED_TAGS.contains(splits[1]) )
							{
								tag = splits[1];
								
								if ( tag.equals("NAME") )
								{	
									String name = "";
									for (int i = 2; i < splits.length; i++)
									{
										name += splits[i] + " ";
									}
									
									new_indi.setName_vo(splits[0], "NAME", name);
								} 
								else if ( tag.equals("GIVN") )
								{
									new_indi.setGivn_vo(splits[0], "GIVN", splits[2]);
								}
								else if ( tag.equals("SURN") )
								{
									new_indi.setSurn_vo(splits[0], "SURN", splits[2]);
								}
								else if ( tag.equals("SEX") )
								{
									new_indi.setSex_vo(splits[0], "SEX",splits[2]);
								}
								else if ( tag.equals("BIRT") )
								{
									// Read the next line to get the birth date.
									inputLine = in.readLine();
									splits = inputLine.split(" ");
									
									String bd = "";
									
									for (int i = 2; i < splits.length; i++)
									{
										bd += splits[i] + " ";
									}
									
									new_indi.setBirthDate_vo(splits[0], "DATE", bd);
								}
								else if ( tag.equals("DEAT") )
								{
									// Read the next line to get the birth date.
									inputLine = in.readLine();
									splits = inputLine.split(" ");
									
									String dd = "";
									
									for (int i = 2; i < splits.length; i++)
									{
										dd += splits[i] + " ";
									}
									
									new_indi.setDeathDate_vo(splits[0], "DATE",dd);
								}
								else if ( tag.equals("FAMC") )
								{
									new_indi.setChildOf_vo(splits[0], "FAMC",splits[2]);
								}
								else if ( tag.equals("FAMS") )
								{
									new_indi.setSpouseOf_vo(splits[0], "FAMS",splits[2]);
								}
							}
						}
						
						// Read in the next line, and split it up to check the level.
						inputLine = in.readLine();
						splits = inputLine.split(" ");
						level = splits[0];
					}
					
					individuals.add(new_indi);
				}
				else if (recordType.equals(FAM))
				{
					level = "-1";
					Family new_fam = new Family();
					
					// Set the id. 
					new_fam.setId_vo("0","FAM",splits[1]);
					
					inputLine = in.readLine();
				
					// Handle each line until the next record.
					while ( !(level.equals("0")) )
					{
						splits = inputLine.split(" ");
							
						if (splits.length > 0)
						{
							// Determine tag and then set the correct property.
							if ( ACCEPTED_TAGS.contains(splits[1]) )
							{
								tag = splits[1];
								if ( tag.equals("HUSB") )
								{
									new_fam.setHusband_vo(splits[0], "HUSB",splits[2]);
								}
								else if ( tag.equals("MARR") )
								{
									// Read the next line to get the birth date.
									inputLine = in.readLine();
									splits = inputLine.split(" ");
									
									String md = "";
									
									for (int i = 2; i < splits.length; i++)
									{
										md += splits[i] + " ";
									}
									
									new_fam.setMarriageDate_vo(splits[0], "DATE", md);
								}
								else if ( tag.equals("DIV") )
								{
									// Read the next line to get the birth date.
									inputLine = in.readLine();
									splits = inputLine.split(" ");
									
									String dd = "";
									
									for (int i = 2; i < splits.length; i++)
									{
										dd += splits[i] + " ";
									}
									
									new_fam.setDivorceDate_vo(splits[0], "DIV", dd);
								}
								else if ( tag.equals("WIFE") )
								{
									new_fam.setWife_vo(splits[0], "WIFE", splits[2]);
								}
								else if ( tag.equals("CHIL") )
								{
									new_fam.getChildren().add(new VO(splits[0], "CHIL",splits[2]));
								}
							}
						}
						
						// Read in the next line, and split it up to check the level.
						inputLine = in.readLine();
						splits = inputLine.split(" ");
						level = splits[0];
					}
					
					families.add(new_fam);
				}				
				
				else {
					inputLine = in.readLine();
				}
			}
			
		}
		catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Private method to determine the type of the record.
	 * 
	 * @param line : The line of the file.
	 * 
	 * @return Returns either FAM or INDI to indicate which record type to create.
	 * 			Will return "NONE" if not FAM or INDI.
	 */	
	private static String getRecordType(String line)
	{
		String level, recordType = "NONE";
		
		String[] splits = line.split(" ");
		
		if (splits.length > 0) 
		{
			level = splits[0];
			// If level 0, check if dealing with INDI or FAM record.
			if ("0".equals(level))
			{
				if ( splits.length >= 3 && (FAM.equals(splits[2]) || INDI.equals(splits[2])))
				{
					recordType = splits[2];
				}
			}
		}
		
		return recordType;
	}
	
	/**
	 * Method to find an individual in the individuals array.
	 * 
	 * @param id - The ID of the individual to find.
	 * @return Returns the individual Object if found, null otherwise.
	 */
	private static Individual findIndividualById(String id)
	{
		for (Individual indi : individuals)
		{
			if (indi.getId_vo().getTagValue().equals(id))
			{
				return indi;
			}
		}
		
		return null;
	}
	
	/** 
	 * Main method of the class
	 * @param args
	 */
	public static void main(String[] args) {
		ProjectFileReader PFR = new ProjectFileReader();
		
		// Read in and print out the file.
		PFR.ReadFile("./DonatoZacharyP01.ged");
		String space=" ";

		Collections.sort(individuals);
		Collections.sort(families);
		
		System.out.println("Individuals: ");
		for (Individual indi : individuals)
		{
			// For each individual, print out their name and unique ID. 
			System.out.println("	" + indi.getId_vo().getTagValue() + ": " + indi.getName_vo().getTagValue());
			
		}
		
		System.out.println();

		System.out.println("Familes: ");
		for (Family fam : families)
		{
			String hid = fam.getHusband_vo().getTagValue();
			String wid = fam.getWife_vo().getTagValue();
			System.out.println("	" + fam.getId_vo().getTagValue());
			System.out.println("		Husband: " + hid + " " + findIndividualById(hid).getName_vo().getTagValue() );
			System.out.println("		Wife   : " + wid + " " + findIndividualById(wid).getName_vo().getTagValue() );

		}
		
		
	}
	private static String getStringFromVO(VO src_vo)
	{
		String result="",space=" ";
		
		if(src_vo!=null)
		{
			result=src_vo.getLevel()+space+src_vo.getTagName() +space+src_vo.getTagValue();
		}
		
		return result;
	}
}
