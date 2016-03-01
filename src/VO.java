
public class VO {

	//comments for test
	public VO (String lvl,String tagname,String tagvalue)
	{
		tagName=tagname;
		tagValue=tagvalue;
		level=lvl;
	}
	private String tagName="";
	private String tagValue="";
	private String level="";
	
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagValue() {
		return tagValue;
	}
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
