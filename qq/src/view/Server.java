package view;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;




public class Server {

		static ArrayList<ThreadString> threadArrayList = new ArrayList<ThreadString>();
		static ArrayList<IdPass> idPass = new ArrayList<IdPass>();

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Thread thread = Thread.currentThread();
		
		
		idPass.add(new IdPass("张龙涛", "1111"));
		idPass.add(new IdPass("安玉海", "1111"));
		idPass.add(new IdPass("穆宇", "1111"));
		idPass.add(new IdPass("邹坤", "1111"));
		int port = 7788;  
        ServerSocket server = new ServerSocket(port); 
        while (true) {  
        	Socket socket = server.accept();
            System.out.println("正在连接，请稍后。。。");
//            new Denglu(socket).start();
            Denglu denglu=new Denglu(socket);
            denglu.start();
  
        }  
	}

}
