package DataTypeFucntions;

public class foo {
	private Integer a;
	private Integer b;
	foo(){
		a = new Integer(16);
		System.out.println(a);
		b = new Integer(17);
		System.out.println(b);
	}
	foo(int n)
	{
		this();
		System.out.println("foo(int n)");
	}
	void fuck(){
		System.out.println("fuck");
	}
}
