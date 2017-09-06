package qq;
import java.io.IOException;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count=0,i;
		Boolean first=true;
		System.out.println("请输入：");
		try {
			while((i=System.in.read())!='\r')
			{
				if(first)
				{
					System.out.println("接收到:");
					first=false;
				}
				count++ ;
				System.out.println((char)i);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
