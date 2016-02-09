/**
 * Zachary Donato, Dmitriy Grishin
 * 06 February 2016
 * CS555 Project
 * I pledge my honor that I have abided by the Stevens Honor System.
 * 
 * Family record to hold the information of a family.
 */

import java.util.ArrayList;

public class Family implements Comparable
{
	// Private data members.
	 private VO id_vo;
	 private VO marriageDate_vo;
	 private VO husband_vo; 
	 private VO wife_vo; 	 
	 private ArrayList<VO> children = new ArrayList<VO>();	
	 private VO divorceDate_vo; 
	 private VO chil_vo;
	 
	 public VO getId_vo() {
		return id_vo;
	}
	public VO getChil_vo() {
		return chil_vo;
	}
	public void setChil_vo(String level,String tagName,String value) {
		this.chil_vo =new VO(level,tagName,value);
	}
	public void setId_vo(String level,String tagName,String value) {
		
		this.id_vo = new VO(level,tagName,value);
	}
	public VO getMarriageDate_vo() {
		return marriageDate_vo;
	}
	public void setMarriageDate_vo(String level,String tagName,String value) {
		this.marriageDate_vo = new VO(level,tagName,value);
	}
	public VO getHusband_vo() {
		return husband_vo;
	}
	public void setHusband_vo(String level,String tagName,String value) {
		this.husband_vo = new VO(level,tagName,value);
	}
	public VO getWife_vo() {
		return wife_vo;
	}
	public void setWife_vo(String level,String tagName,String value) {
		this.wife_vo = new VO(level,tagName,value);
	}
	public ArrayList<VO> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<VO> children) {
		this.children = children;
	}
	public VO getDivorceDate_vo() {
		return divorceDate_vo;
	}
	public void setDivorceDate_vo(String level,String tagName,String value) {
		this.divorceDate_vo = new VO(level,tagName,value);
	}
	
	/**
	 * Method compareTo for sorting individuals.
	 */
	@Override
	public int compareTo(Object o)
	{
		String thisValue = this.id_vo.getTagValue();
		String compareValue = ( (Family) o ).getId_vo().getTagValue();

		int compare = thisValue.compareTo(compareValue);

		if (compare < 0)
		{
			return -1;
		}
		else if (compare > 0)
		{
			return 1;
		}
		
		return 0;
	}
}