package MIPSGo;
import java.util.HashMap;

public class codeSegment {
	private String[] assembleCode = new String[1024];
	private String[] binaryCode = new String[1024];
	private HashMap<String, Integer> lableTable = new HashMap<String, Integer>();
	private int assembleCodeCount = 0;
	
	public codeSegment(String[] fileText)
	{	    
		int fileTextCount = 0;
		while(!fileText[fileTextCount++].equals(".text"));
		for(;fileTextCount<fileText.length;)
		{
			translateAsmCode(fileText[fileTextCount++]);
		}
	}

	private void translateAsmCode(String inString)
	{
		String Lable;
		if(inString.contains(":"))
		{
			Lable=inString.substring(0, inString.indexOf(':')-1);
			lableTable.put(Lable, assembleCode.length);
			inString=inString.substring(inString.indexOf(':'));
			inString=inString.trim();
		}
		if(inString.contains("mv"))
		{
			assembleCode[assembleCodeCount++]="add";
		}
		else if(inString.contains("la"))
		{
			
		}
		else
		{
			assembleCode[assembleCodeCount++]=inString;
		}
	}
		
	
	public String[] generateBinaryCode()
	{
		return assembleCode;
	}

}
