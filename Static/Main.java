public class Main{
 
	public static void main(String[] args){
		StaticExample se=new StaticExample();
		se.run();
		se.cal();
		se=null;
		se.run();
		se.cal();
	}
}
