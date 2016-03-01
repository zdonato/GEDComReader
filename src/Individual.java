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
	private VO name_vo; 
	private VO sex_vo;
	private VO birthDate_vo;
	private VO deathDate_vo;
	private VO childOf_vo;
	private VO spouseOf_vo;
	private VO id_vo;
	private VO givn_vo;
	private VO surn_vo;
	private VO level_vo;
	
	
	public VO getName_vo() {
		return name_vo;
	}
	public void setName_vo(String level,String tagName,String value) {
		this.name_vo =  new VO(level,tagName,value);
	}
	public VO getSex_vo() {
		return sex_vo;
	}
	public void setSex_vo(String level,String tagName,String value) {
		this.sex_vo =  new VO(level,tagName,value);
	}
	public VO getBirthDate_vo() {
		return birthDate_vo;
	}
	public void setBirthDate_vo(String level,String tagName,String value) {
		this.birthDate_vo =  new VO(level,tagName,value);
	}
	public VO getDeathDate_vo() {
		return deathDate_vo;
	}
	public void setDeathDate_vo(String level,String tagName,String value) {
		this.deathDate_vo =  new VO(level,tagName,value);
	}
	public VO getChildOf_vo() {
		return childOf_vo;
	}
	public void setChildOf_vo(String level,String tagName,String value) {
		this.childOf_vo =  new VO(level,tagName,value);
	}
	public VO getSpouseOf_vo() {
		return spouseOf_vo;
	}
	public void setSpouseOf_vo(String level,String tagName,String value) {
		this.spouseOf_vo =  new VO(level,tagName,value);
	}
	public VO getId_vo() {
		return id_vo;
	}
	public void setId_vo(String level,String tagName,String value) {
		this.id_vo =  new VO(level,tagName,value);
	}
	public VO getGivn_vo() {
		return givn_vo;
	}
	public void setGivn_vo(String level,String tagName,String value) {
		this.givn_vo =  new VO(level,tagName,value);
	}
	public VO getSurn_vo() {
		return surn_vo;
	}
	public void setSurn_vo(String level,String tagName,String value) {
		this.surn_vo =  new VO(level,tagName,value);
	}
	public VO getLevel_vo() {
		return level_vo;
	}
	public void setLevel_vo(String level,String tagName,String value) {
		this.level_vo =  new VO(level,tagName,value);
	}
	
	//comments for test
	
}