package MIPSCPU;

public class MemoryManager {

	private byte[] MainMemory;
	
	public MemoryManager(int MAXMEM) {
		MainMemory = new byte [MAXMEM];
	}
	
	public void InitMemory(byte[] InputData)
	{
		for(int i=0;i<InputData.length;i++)
			MainMemory[i]=InputData[i];
	}
	
	public int lw(int Addr)
	{
		int Byte1=(MainMemory[Addr++]&0xff)<<24;
		int Byte2=(MainMemory[Addr++]&0xff)<<16;
		int Byte3=(MainMemory[Addr++]&0xff)<<8;
		int Byte4=(MainMemory[Addr++]&0xff);
		return Byte1+Byte2+Byte3+Byte4;
	}
	
	public void sw(int Addr,int Data)
	{
		MainMemory[Addr+3]=(byte)(Data&0xff);
		Data>>>=8;
		MainMemory[Addr+2]=(byte)(Data&0xff);
		Data>>>=8;
		MainMemory[Addr+1]=(byte)(Data&0xff);
		Data>>>=8;
		MainMemory[Addr]=(byte)(Data&0xff);
	}
	
	public short lh(int Addr)
	{
		short Byte1=(short) ((MainMemory[Addr++]&0xff)<<8);
		short Byte2=(short) (MainMemory[Addr++]&0xff);
		return (short) (Byte1+Byte2);
		
	}
	
	public void sh(int Addr, short Data)
	{
		MainMemory[Addr+1]=(byte)(Data&0xff);
		Data>>>=8;
		MainMemory[Addr]=(byte)(Data&0xff);
	}
	
	public byte lb(int Addr)
	{
		return MainMemory[Addr];
	}
	
	public void sb(int Addr, byte Data)
	{
		MainMemory[Addr]=Data;
	}
}
