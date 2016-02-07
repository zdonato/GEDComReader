/**
 * Zachary Donato, Dmitriy Grishin
 * 06 February 2016
 * CS555 Project
 * I pledge my honor that I have abided by the Stevens Honor System.
 * 
 * Individual record to hold the information of an individual.
 */

public class Individual
{
	// Private data members.
	private String name;
	private String sex;
	private String birthDate;
	private String deathDate;
	private String childOf;
	private String spouseOf;
	private String id;
		
	/**
	 * Parameterless constructor.
	 */
	public Individual() {}
	
	/**
	 * Getter/setter for name.
	 */
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Getter/Setter for id.
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
	 * Getter/Setter for sex.
	 */
	public String getSex()
	{
		return this.sex;
	}
	
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	/**
	 * Getter/Setter for birthdate.
	 */
	public String getBirthDate()
	{
		return this.birthDate;
	}
	
	public void setBirthDate(String birthDate)
	{
		this.birthDate = birthDate;
	}
	
	/**
	 * Getter/Setter for deathdate.
	 */
	public String getDeathDate()
	{
		return this.deathDate;
	}
	
	public void setDeathDate(String deathDate)
	{
		this.deathDate = deathDate;
	}
	
	/**
	 * Getter/setter for child of.
	 */
	public String getChildOf()
	{
		return this.childOf;
	}
	
	public void setChildOf(String co)
	{
		this.childOf = co;
	}
	
	/**
	 * Getter/setter for spouse of.
	 */
	public String getSpouseOf()
	{
		return this.spouseOf;
	}
	
	public void setSpouseOf(String so)
	{
		this.spouseOf = so;
	}
	
	/**
	 * Main method.
	 */
	public static void main(String[] args)
	{
		
	}
}