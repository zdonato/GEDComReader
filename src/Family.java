/**
 * Zachary Donato, Dmitriy Grishin
 * 06 February 2016
 * CS555 Project
 * I pledge my honor that I have abided by the Stevens Honor System.
 * 
 * Family record to hold the information of a family.
 */

import java.util.ArrayList;

public class Family 
{
	// Private data members.
	private String id;
	private String marriageDate;
	private String husband;
	private String wife;
	private ArrayList<String> children = new ArrayList<String>();
	private String divorceDate;
	
	/**
	 * Paramaterless constructor.
	 */
	public Family () {}
	
	/**
	 * Getter/setter for id;
	 */
	public String getId()
	{
		return this.id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	/**
	 * Getter/setter for marriageDate.
	 */
	public String getMarriageDate()
	{
		return this.marriageDate;
	}
	
	public void setMarriageDate(String md)
	{
		this.marriageDate = md;
	}
	
	/**
	 * Getter/setter for husband.
	 */
	public String getHusband()
	{
		return this.husband;
	}
	
	/**
	 * Method to set the husband of the family.
	 * @param h : The ID of the husband Individual.
	 */
	public void setHusband(String h)
	{
		this.husband = h;
	}
	
	/** 
	 * Getter/setter for wife.
	 */
	public String getWife()
	{
		return this.wife;
	}
	
	/**
	 * Method to set the wife of the family.
	 * @param w : The ID of the wife Individual.
	 */
	public void setWife(String w)
	{
		this.wife = w;
	}
	
	/**
	 * Getter/setter for children.
	 */
	public ArrayList<String> getChildren()
	{
		return this.children;
	}
	
	/**
	 * Method to set the children of the family. 
	 * @param c : The ID of the child individual.
	 */
	public void addChild(String c)
	{
		this.children.add(c);
	}
	
	/**
	 * Getter/setter for divorce.
	 */
	public String getDivorceDate()
	{
		return this.divorceDate;
	}
	
	public void setDivorceDate(String dd)
	{
		this.divorceDate = dd;
	}
	
	/**
	 * Main method.
	 */
	public static void main(String[] args)
	{
		
	}
}