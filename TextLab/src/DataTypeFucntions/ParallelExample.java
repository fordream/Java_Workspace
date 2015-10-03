package DataTypeFucntions;

public class ParallelExample implements Runnable {
	protected int countDown = 10;
	private static int taskCount =0;
	private final int id = taskCount++;
	public ParallelExample(){}
	public ParallelExample(int countDown){
		this.countDown = countDown;
	}
	public String status(){
		return "#" + id + "(" + (countDown>0?countDown:"Liftoff!") + "), ";
	}
	public void run(){
		System.out.println(id);
		Thread.yield();
	}

}
