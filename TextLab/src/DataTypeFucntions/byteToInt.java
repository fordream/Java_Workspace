package DataTypeFucntions;

public class byteToInt {
	
	public static void main(String[] args)
	{
		byte Byte1=3;
		byte Byte2=-125;
		int Int1 =0;
		int Int2 =0;
		Int1 = Byte1;
		System.out.println(Int1);
		Int1 = Byte1&0xff;
		System.out.println(Int1);
		Int2 = Byte2;
		System.out.println(Int2);
		Int2 = Byte2&0xff;
		System.out.println(Int2);
	}

}
