package MIPSCPU;
import java.io.*;

public class MIPSCPU {
	public static final int MAXMEM = 8192;
	public static final int	VMaddr	=6144;
	public static final int	WIDTH	=50;
	private static final int HEIGHT	=40;
	public static final int INTadr = 0;
	int	op, rs, rt, rd, imm, addr, sht, fun;
	
	int PC, IR, MDR, HI, LO;
	int [] Rgf;
	MemoryManager Memory;
	
	byte[]	IOPort;	//I/O�˿�
	byte[]	LUT;	//��ɫ���ұ�
	boolean	INTflag;	//�ж�����
	int	INTno, EPC;	//�жϺţ��жϵ�ǰ��ַ����
	
	
	MIPSCPU() {
		Rgf	= new int[32];
		Rgf[0]	=0;	//$zero
        Memory	= new MemoryManager(MAXMEM);

		IOPort	= new byte[64];	//PC����64KB
		INTflag	= false;	//���жϱ��
	}
	
	public void boot(String InputImg)
	{
		byte[] InputData = new byte[MAXMEM];
		try{
			DataInputStream in = 
			new DataInputStream(
			new FileInputStream(
			new File(InputImg)));
			in.read(InputData);
			Memory.InitMemory(InputData);
			in.close();
			} catch(Exception ex){
			}
			PC=0;
	}
	
	public int execute(boolean FullSpeed)
	{
	

	do{

	/*�жϴ���ÿ��ָ��ִ��ǰ������ж�������
	*Memory�У�INTadr��Ϊһ�ж�������ÿ4�ֽڴ�
	*��һ���жϵķ�������ַ��
	*/
	if(INTflag){	//����ж�������
	EPC = PC;	//���浱ǰ��ַ
	PC = INTadr + INTno*4;	//PC����INTadr=0
	}

	IR = Memory.lw(PC);	PC+=4;
	if(IR==0)
		return -1;
	op =(IR>>26)&63;	//IR{31..26}
	rs =(IR>>21)&31;	//IR{25..21}
	rt =(IR>>16)&31;	//IR{20..16}
	rd =(IR>>11)&31;	//IR{15..11}
	sht=(IR>>6)&31;	//IR{11..6}
	fun=IR&63;	//IR{5..0}
	imm=(int)(short)(IR&0xFFFF);	//IR{15..0}
	addr=(IR&0x3FFFFFF)<<2;	//IR{25..0}<<2

	switch(op){
	case 0:	//R-type
		switch(fun){
			case 32:	//ADD
				Rgf[rd] = Rgf[rs]+Rgf[rt];
				break;
			case 33:    //ADDU
				Rgf[rd] = (int)(getUnsignedInt(Rgf[rs])+getUnsignedInt(Rgf[rt]))&0xFFFF;
				break;
			case 34:	//SUB
				Rgf[rd] = Rgf[rs]-Rgf[rt];
				break;
			case 35:    //SUBU
				Rgf[rd] = (int)(getUnsignedInt(Rgf[rs])-getUnsignedInt(Rgf[rt]))&0xFFFF;
				break;
			case 0x2A:  //SLT
				Rgf[rd] = Rgf[rs]<Rgf[rt]?1:0;
				break;
			case 0x2B:  //SLTU
				Rgf[rd] = getUnsignedInt(Rgf[rs])<getUnsignedInt(Rgf[rt])?1:0;
				break;  
			case 0x24:  //AND
				Rgf[rd] = Rgf[rs]&Rgf[rt];
				break;
			case 0x25:  //OR
				Rgf[rd] = Rgf[rs]|Rgf[rt];
				break;	
			case 0x26:  //XOR
				Rgf[rd] = Rgf[rs]^Rgf[rt];
				break;
			case 0x27:  //NOR
				Rgf[rd] = ~(Rgf[rs]|Rgf[rt]);
				break;
			case 0:     //SLL
				Rgf[rd] = Rgf[rt] << sht;
				break;
			case 2:     //SRL
				Rgf[rd] = Rgf[rt] >>> sht;
				break;
			case 3:     //SRA
				Rgf[rd] = Rgf[rt] >> sht;
				break;
			case 9: //JALR
			    Rgf[31] = PC+4; //$ra = PC+4
			    PC=Rgf[rs]; 
			    break;
			case 8:	//R
				PC=Rgf[rs];
				break;
			case 0xd:
				EPC=PC+4; PC=Rgf[3];//
				break;
			case 0xc:
				EPC=PC+4; PC=Rgf[3];//
				break;
		}
	break;
	case 35:	//LW
		Rgf[rt]=Memory.lw(Rgf[rs+imm]);
		break;
	case 43:	//SW
		Memory.sw(Rgf[rs]+imm, Rgf[rt]);
		break;
	case 0x20:	//LB
		Rgf[rt]=Memory.lb(Rgf[rs+imm]);
		break;
	case 0x24:	//LBU
		Rgf[rt]=Memory.lb(Rgf[rs+imm])&0xff;
		break;
	case 0x28:	//SB
		Memory.sw(Rgf[rs]+imm, (byte)Rgf[rt]);
		break;
	case 0x21:  //LH
		Rgf[rt]=Memory.lh(Rgf[rs+imm]);
		break;
	case 0x25:	//LHU
		Rgf[rt]=Memory.lh(Rgf[rs+imm])&0xff;
		break;
	case 0x29:	//SH
		Memory.sw(Rgf[rs]+imm, (short)Rgf[rt]);
		break;
	case 8:     //ADDI
		Rgf[rt] = Rgf[rs] +imm;
		break;
	case 9:     //ADDIU
		Rgf[rt] = (int) (getUnsignedInt(Rgf[rs])+imm);
		break;
	case 0xc:   //ANDI
		Rgf[rt] = Rgf[rs]&imm;
		break;
	case 0xd:   //ORI
		Rgf[rt] = Rgf[rs]|imm;
		break;
	case 0xe:   //XORI
		Rgf[rt] = Rgf[rs]^imm;
		break;
	case 0xf:   //LUI
		Rgf[rt] = imm<<16;
		break;
	case 0xa:   //SLTI
		Rgf[rt] = Rgf[rs]<imm?1:0;
		break;
	case 0xb:   //SLTIU
		Rgf[rt] = getUnsignedInt(Rgf[rs])<imm?1:0;
		break;
	case 4:	//BEQ
		if(Rgf[rs] == Rgf[rt])PC+=(imm<<2);
		break;
	case 5:	//BNE
		if(Rgf[rs] != Rgf[rt])PC+=(imm<<2);
		break;
	case 6:	//BLEZ
		if(Rgf[rs] <= 0)PC+=(imm<<2);
		break;
	case 7:	//BGTZ
		if(Rgf[rs] > 0)PC+=(imm<<2);
		break;
	case 2:	//J
		PC=PC&0xFFFFFFF | addr;
		break;
	case 3:	//JAL
		Rgf[31] = PC+4; //$ra = PC+4
		PC=PC&0xFFFFFFF | addr;
		break;
	case 0x10:
		switch(fun){
		    case 18:
		    	PC=EPC;
		    	break;
		    default:
		    	System.out.println("Instruction Error!");
				return -1;
		}
	default:
		System.out.println("Instruction Error!");
		return -1;
//		break;
	}

	/******* ģ���������(���������߳�)**********/
	//KBadr = ����IO��ַ;��//IOadr��ӳ�䵽�ڴ��е�ַ
//	if(kbhit()){	//����Ƿ񰴼�
//	IOPort[KBadr]=getch();	//����IO��ַ
//	Memory[IOadr + KBadr]=getch();	//IOӳ�䵽�ڴ�
//	INTno=KBINT;	//keyboard int
//	}
	/********************************************/

	/******* ģ���ı�ģʽ(���������߳�)**********/
	//VM = &Memory[�Դ���ʼ��ַ];��//�Դ汻ӳ�䵽�ڴ���
//	for(int row=0; row<getHeight(); row++){
//	for(int col=0; col<WIDTH; col++){
//	System.out.print((char)Memory.lb(VMaddr+row*WIDTH+col));
//	}	System.out.println();
//	}
	/********************************************/

	/******* ģ��ͼ��ģʽ�����ɫ(���������߳�)**********/
	//VM = &Memory[�Դ���ʼ��ַ];��//�Դ汻ӳ�䵽�ڴ���
//	for(int row=0; row<HEIGHT; row++){
//	for(int col=0; col<WIDTH; col++){
//	putpixel(col, row, MMU.Memory[VMaddr+row*WIDTH+col]);
//	}
//	}
	/********************************************/

	/******* ģ��ͼ��ģʽ���ٲ�ɫ(���������߳�)**********/
	//VM = &Memory[�Դ���ʼ��ַ];��//�Դ汻ӳ�䵽�ڴ���
//	for(int row=0; row<HEIGHT; row++){
//	for(int col=0; col<WIDTH; col++){
//	putpixel(col, row, LUT(MMU.Memory[VMaddr+row*WIDTH+col]));
//	}
//	}
	/********************************************/
	 }while(FullSpeed);	//for(;;)
	return 1;
	}
	
	private long getUnsignedInt (int data){     //��int����ת��Ϊ0~4294967295 (0xFFFFFFFF��DWORD)��
		return data&0x0FFFFFFFFl;
		}
	
	String[] getVramData(){
		String [] VramData = new String[getHeight()];
		for(int row=0; row<getHeight(); row++){
			VramData[row]="";
			for(int col=0; col<WIDTH; col++){
				
			if(Memory.lb(VMaddr+row*WIDTH+col)==0)
			{
				VramData[row]+=" ";
			}
			else
			{
				VramData[row]+=(char)Memory.lb(VMaddr+row*WIDTH+col);
			}
			}
		}
		return VramData;
	}

	public static int getHeight() {
		return HEIGHT;
	}
}
