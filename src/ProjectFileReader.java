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
	 * Package array of acceptable tags for our project.
	 */
	final private static ArrayList<String> ACCEPTED_TAGS = new ArrayList<String>() {{
		add("INDI"); add("NAME"); add("SEX"); add("BIRT"); add("DEAT");
		add("FAMC"); add("FAMS"); add("FAM"); add("MARR"); add("HUSB");
		add("HUSB"); add("WIFE"); add("CHIL"); add("DIV"); add("DATE");
		add("HEAD"); add("TRLR"); add("NOTE");
	}};

	/**
	 * Method to read in the GEDCOM file.
	 * @param filename : Relative pathname to the file. 
	 */
	public void ReadFile(String filename) 
	{
		String 			inputLine; 
		String[] 		splits = new String[100];
		BufferedReader 	in;
		
		try 
		{
			in 			= new BufferedReader(new FileReader(filename));
			inputLine 	= in.readLine();
			
			while (inputLine != null)
			{
				handleLine(inputLine);
				inputLine = in.readLine();
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
	 * @param level : The level of the line
	 * @param tag 	: The tag of the line.
	 */
	private static void handleLine(String line){
		String level, tag;
		
		String[] splits = line.split(" ");
		
		if (splits.length != 0)
		{
			level = splits[0]; 
			
			// Check if the tag is valid for our project.
			if (ACCEPTED_TAGS.contains(splits[1]))
			{
				tag = splits[1];
			} 
			else {
				tag = "Invalid tag";
			}

			// Print out the line, the level number and the tag for each line.
			System.out.println(line);
			System.out.println("    Level: " + level);
			System.out.println("    Tag:   " + tag);
		}	
	}
	
	/** 
	 * Main method of the class
	 * @param args
	 */
	public static void main(String[] args) {
		ProjectFileReader PFR = new ProjectFileReader();
		
		// Read in and print out the file.
		PFR.ReadFile("./DonatoZacharyP01.ged");
		// My comment.
		System.out.println(" some message ");
	}

}
