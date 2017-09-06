package qq;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientReceMess extends Thread{
	Socket socket=null;
	public ClientReceMess(Socket socket){
		this.socket=socket;
		
	}
	
	@Override
	public void run(){
		InetAddress inetAddress;
		try {
			
			for(;;){
			InputStream inputStream = socket.getInputStream();
			byte[] b = new byte[1024];
			int len = -1;
			String res = null;
			while (-1 != (len = inputStream.read(b))) { // 读入账号密码
				// 将字节数组转换为字符串
				res = new String(b, 0, len);
				System.out.println(res);
				break;

			}
//				socket.close();
		} 
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
}
}
