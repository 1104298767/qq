package view;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerReceMess extends Thread {
	Socket socket=null;
	Socket socketTo=null;
	String thisName;
	String thatName;
	public ServerReceMess(String thisName,Socket socket,String thatName,Socket socketTo) {
		// TODO Auto-generated constructor stub
		this.socket=socket;
		this.socketTo=socketTo;
		this.thisName=thisName;
		this.thatName=thatName;
	}

	@Override
	public void run() {							//������������ת��
		try {
			for (;;) {
				InputStream inputStream = socket.getInputStream();
				byte[] b = new byte[100];
				int len = -1;
				String res = null;
				while (-1 != (len = inputStream.read(b))) { 
					res = new String(b, 0, len);
					System.out.println(res);
					break;
				}
				System.out.println("�������Ѿ����յ����Կͻ��˵ĻỰ����---"+res);
				System.out.println("����ת��");
				String string="����---"+thisName+":"+res;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
