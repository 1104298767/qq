package qq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Client1 extends Thread {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = Thread.currentThread();
		// TODO Auto-generated method stub
		/********************************************************************************************/
		// ��һ��
		// try {
		// InetAddress inetAddress = InetAddress.getByName("localhost");
		// for (;;) {
		// Socket socket = new Socket(inetAddress, 7788);
		// BufferedReader buf = new BufferedReader (new
		// InputStreamReader(System.in));
		// String string=buf.readLine();
		// OutputStream outputStream = socket.getOutputStream();
		// if(string.length()!=0)
		// {
		// outputStream.write(string.getBytes());
		// }
		// outputStream.close();

		// socket = new Socket(inetAddress, 7788);
		// InputStream inputStream = socket.getInputStream();
		// byte[] b = new byte[100];
		// if (b.length != 0) {
		// inputStream.read(b);
		// System.out.println(new String(b));
		// }
		// inputStream.close();
		// }

		// } catch (UnknownHostException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		/*****************************************************************************************/

		/******************************************************************************************/
		// �ڶ���
		System.out.println("���¼qq");
		Scanner reader = new Scanner(System.in);
		System.out.println("����������˺�");
		String id = reader.next();
		System.out.println("�������������");
		String pass = reader.next();
		String string = id + "&&" + pass;
		try {
			InetAddress inetAddress = InetAddress.getByName("localhost");
			Socket socket = new Socket(inetAddress, 7788);
//			ThreadString threadString = new ThreadString(id,
//					socket.getLocalAddress(), socket.getLocalPort());		//��¼��ǰ��ip+�˿�
			System.out.println("�ͻ���"+socket.getLocalAddress());
			System.out.println("�ͻ���"+socket.getLocalPort());
			OutputStream outputStream = socket.getOutputStream();
//			socket = new Socket(inetAddress, 7788);						//�����µ�socket���������
			string=string+"&&"+socket.getInetAddress()+"&&"+socket.getLocalPort();
			outputStream.write(string.getBytes());							//���˺�����ip+pass���͵�������

			 InputStream inputStream = socket.getInputStream();
			 byte[] b = new byte[12];
			 int len = -1;
			 String res = null;
			 while (-1 != (len = inputStream.read(b))) {
			 // ���ֽ�����ת��Ϊ�ַ���
			 res = new String(b, 0, len);
			 System.out.println(res);
			 break;
			 }
			 if (res.equals("��¼�ɹ�")) {
			System.out.println("�����������˭����");
			id = reader.next();
			
			ClientSendMess clientsendMess = new ClientSendMess(socket);
			ClientReceMess clientreceMess = new ClientReceMess(socket);
			clientsendMess.start();
			clientreceMess.start();
			
			
			outputStream = socket.getOutputStream();
			outputStream.write(id.getBytes()); 

			} else {
				System.out.println("123");
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
