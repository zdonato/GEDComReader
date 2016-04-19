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
		add("HUSB"); add("WIFE"); add("WIFE_2");add("CHIL"); add("DIV"); add("DATE");
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
								else if ( tag.equals("WIFE_2") )
								{
									new_fam.setWife_2_vo(splits[0], "WIFE_2", splits[2]);
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
	 * Private method to print out the contents of the file.
	 * 
	 * @param line 	: The line of the file.
	 * 
	 * @return Returns either FAM or INDI to indicate which record type to create.
	 * 			Will return "NONE" if not FAM or INDI.
	 */
	private static String handleLine(String line){
		return "";
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
	 * Main method of the class
	 * @param args
	 */
	public static void main(String[] args) {
		ProjectFileReader PFR = new ProjectFileReader();
		
		// Read in and print out the file.
		//PFR.ReadFile("./DonatoZacharyP01_US01_02_input.ged");
		PFR.ReadFile("./DonatoZachary_Dmitriy P01_US06_11_input.ged");
		
		String space=" ";
		
		for (Individual indi : individuals)
		{
			 
			//comments for test
			System.out.println(Utils.getStringFromVO(indi.getId_vo()));	 
			System.out.println(Utils.getStringFromVO(indi.getName_vo()));
			System.out.println(Utils.getStringFromVO(indi.getGivn_vo()));
			 
			System.out.println(Utils.getStringFromVO(indi.getSurn_vo()));
			if(indi.getBirthDate_vo()!=null)
				System.out.println(indi.getBirthDate_vo().getLevel()+" BIRT"); 
			System.out.println(Utils.getStringFromVO(indi.getBirthDate_vo()));
			
			if(indi.getDeathDate_vo()!=null)
			{
				System.out.println(indi.getDeathDate_vo().getLevel()+" DEAT");
				System.out.println(Utils.getStringFromVO(indi.getDeathDate_vo()));
				
				// Check for death before birth. 
				if (indi.getBirthDate_vo() != null)
				{
					String[] msg=new String[]{""};			
					if(!Utils.compareDate(indi.getBirthDate_vo().getTagValue(), indi.getDeathDate_vo().getTagValue(),msg,false,true))
					{
						System.out.println((indi.getId_vo().getTagValue()+space+indi.getName_vo().getTagValue()+space+"was born "+msg[0] + " was dead!").toUpperCase());
					}	
					
					// Check if death date was less than 150 years after birth date.
					if (!Utils.isUnder150YearsOld(indi.getBirthDate_vo().getTagValue(), indi.getDeathDate_vo().getTagValue()))
					{
						System.out.println( "\n" + indi.getId_vo().getTagValue() + " " + indi.getName_vo().getTagValue() + " older than 150\n" );
					}
				}
			} else 
			{
				// Check to make sure user is under 150 years old.
				if (!Utils.isUnder150YearsOld(indi.getBirthDate_vo().getTagValue(), null))
				{
					System.out.println( "\n" + indi.getId_vo().getTagValue() + " " + indi.getName_vo().getTagValue() + " older than 150\n" );
				}
			}
			
			System.out.println(Utils.getStringFromVO(indi.getSex_vo()));
			System.out.println(Utils.getStringFromVO(indi.getSpouseOf_vo())); 
			System.out.println(Utils.getStringFromVO(indi.getChildOf_vo()));
			
			// Check to make sure all dates are before the current date.
			Utils.ensureIndividualsDatesBeforeCurrentDate(indi);
			
			//print age
			System.out.println(indi.getName_vo().getTagValue() +"Age: "+ Utils.calculateAge(indi));
			
			System.out.println();
		}
		
		for (Family fam : families)
		{
			System.out.println(Utils.getStringFromVO(fam.getId_vo()));
			System.out.println(Utils.getStringFromVO(fam.getWife_vo()));
			System.out.println(Utils.getStringFromVO(fam.getWife_2_vo()));
			System.out.println(Utils.getStringFromVO(fam.getHusband_vo()));
			
			if(fam.getMarriageDate_vo()!=null)
			{
				System.out.println(fam.getMarriageDate_vo().getLevel()+" MARR");
				System.out.println(Utils.getStringFromVO(fam.getMarriageDate_vo()));
				
				//check wife
				Individual indi=Utils.searchPersonBD(individuals, fam.getWife_vo().getTagValue());		
				String[] msg=new String[]{""};			
				if(!Utils.compareDate(fam.getMarriageDate_vo().getTagValue(), indi.getBirthDate_vo().getTagValue(),msg,true,false))
				{
					System.out.println((indi.getId_vo().getTagValue()+space+indi.getName_vo().getTagValue()+space+"was born "+msg[0] + " was married!").toUpperCase());
				}
				if (indi.getDeathDate_vo() != null &&
						!Utils.compareDate(fam.getMarriageDate_vo().getTagValue(), indi.getDeathDate_vo().getTagValue(), msg, false, true))
				{
					System.out.println("\n" + indi.getId_vo().getTagValue() + " " + indi.getName_vo().getTagValue() + " died before marriage date.\n");
				}

				//check husband		
				indi=Utils.searchPersonBD(individuals, fam.getHusband_vo().getTagValue());				
				if(!Utils.compareDate(fam.getMarriageDate_vo().getTagValue(), indi.getBirthDate_vo().getTagValue(),msg,true, false))
				{
					System.out.println((indi.getId_vo().getTagValue()+space+indi.getName_vo().getTagValue()+space+"was born "+msg[0]+" was married!").toUpperCase());
				}
				if (indi.getDeathDate_vo() != null &&
						!Utils.compareDate(fam.getMarriageDate_vo().getTagValue(), indi.getDeathDate_vo().getTagValue(), msg, false, true))
				{
					System.out.println("\n" + indi.getId_vo().getTagValue() + " " + indi.getName_vo().getTagValue() + " died before marriage date.\n");
				}
					
			}
			
			//check for bigamy US11
			if(fam.getWife_2_vo()!=null)
			{
				Individual indi=Utils.searchIndiById(individuals, fam.getHusband_vo().getTagValue());
				
				System.out.println("\n" + indi.getId_vo().getTagValue() + " " + indi.getName_vo().getTagValue() + " has two wifes (bigamy)!\n");
			}
			
		 
			//check if was divorced before death
			if(fam.getDivorceDate_vo()!=null)
			{
				System.out.println(fam.getDivorceDate_vo().getLevel()+" DIV");
				System.out.println(Utils.getStringFromVO(fam.getDivorceDate_vo()));
				
				Individual indi=Utils.searchPersonBD(individuals, fam.getHusband_vo().getTagValue());	
				String[] msg=new String[]{""};			
				//if(!Utils.compareDate(fam.getDivorceDate_vo().getTagValue(), indi.getBirthDate_vo().getTagValue(),msg,true,false))
				if(!Utils.compareDate(indi.getDeathDate_vo().getTagValue(), fam.getDivorceDate_vo().getTagValue(),msg,true,false))
				{
					System.out.println((indi.getId_vo().getTagValue()+space+indi.getName_vo().getTagValue()+space+"divorced  "+fam.getDivorceDate_vo().getTagValue() + " after death "+ indi.getDeathDate_vo().getTagValue()).toUpperCase());
				}
			}
			
			// Ensure all dates before current date.
			Utils.ensureFamilyDatesBeforeCurrentDate(fam);
			 			 
			for (VO chilvo : fam.getChildren())
			{
				System.out.println(Utils.getStringFromVO(chilvo));
				Individual indi = Utils.searchIndiById(individuals, chilvo.getTagValue());
				if (indi != null)
				{
					Utils.ensureChildBornAfterMarriage(indi, fam);
				}
			}
			
			// Ensure marriage date is after both husband and wife are at least 14.
			Individual husband = Utils.searchIndiById(individuals, fam.getHusband_vo().getTagValue());
			Individual wife = Utils.searchIndiById(individuals, fam.getWife_vo().getTagValue());
			if (husband != null && wife != null && fam.getMarriageDate_vo() != null){
				Utils.ensureSpouseIsAtLeast14(husband, wife, fam.getMarriageDate_vo().getTagValue());
			}

			Utils.verifyIfParentsTooOld(fam,individuals);
			if(Utils.verifyIfSiblings(fam,individuals,families))
				System.out.println("Siblings should not marry one another");
			
			System.out.println();
			System.out.println();
		}
		
		// Ensure all ids are unique.
		Utils.ensureAllIDsUnique(individuals, families);
		
	}
	
}
