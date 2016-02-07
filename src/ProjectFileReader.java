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
		add("INDI"); add("NAME"); add("SEX"); add("BIRT"); add("DEAT");
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
					new_indi.setId(splits[1]);
					
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
									
									new_indi.setName(name);
								} 
								else if ( tag.equals("SEX") )
								{
									new_indi.setSex(splits[2]);
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
									
									new_indi.setBirthDate(bd);
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
									
									new_indi.setDeathDate(dd);
								}
								else if ( tag.equals("FAMC") )
								{
									new_indi.setChildOf(splits[2]);
								}
								else if ( tag.equals("FAMS") )
								{
									new_indi.setSpouseOf(splits[2]);
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
					new_fam.setId(splits[1]);
					
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
									new_fam.setHusband(splits[2]);
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
									
									new_fam.setMarriageDate(md);
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
									
									new_fam.setDivorceDate(dd);
								}
								else if ( tag.equals("WIFE") )
								{
									new_fam.setWife(splits[2]);
								}
								else if ( tag.equals("CHIL") )
								{
									new_fam.addChild(splits[2]);
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
		PFR.ReadFile("./DonatoZacharyP01.ged");
		
		for (Individual indi : individuals)
		{
			System.out.println(indi.getId());
			System.out.println(indi.getName());
			System.out.println(indi.getBirthDate());
			System.out.println(indi.getDeathDate());
			System.out.println(indi.getSex());
			System.out.println(indi.getSpouseOf());
			System.out.println(indi.getChildOf());
			System.out.println();
		}
		
		for (Family fam : families)
		{
			System.out.println(fam.getId());
			System.out.println(fam.getWife());
			System.out.println(fam.getHusband());
			System.out.println(fam.getMarriageDate());
			System.out.println(fam.getDivorceDate());
			System.out.print("Children: ");
			for (String c : fam.getChildren())
			{
				System.out.print(c + " ");
			}
			System.out.println();
			System.out.println();
		}
	}

}
