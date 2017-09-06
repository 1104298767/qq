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

public class Client extends Thread {
	public static void main(String[] args) throws InterruptedException {
//		Thread thread = Thread.currentThread();
		// TODO Auto-generated method stub
		/********************************************************************************************/
		// 第一次
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
		// 第二次
		System.out.println("请登录qq");
		Scanner reader = new Scanner(System.in);
		System.out.println("请输入你的账号");
		String id = reader.next();
		System.out.println("请输入你的密码");
		String pass = reader.next();
		String string = id + "&&" + pass;
		try {
			InetAddress inetAddress = InetAddress.getByName("localhost");
			Socket socket = new Socket(inetAddress, 7788);
			// ThreadString threadString = new ThreadString(id,
			// socket.getLocalAddress(), socket.getLocalPort()); //记录当前的ip+端口
			System.out.println("客户端" + socket.getLocalAddress());
			System.out.println("客户端" + socket.getLocalPort());
			OutputStream outputStream = socket.getOutputStream();
			// socket = new Socket(inetAddress, 7788); //创建新的socket传给服务端
			string = string + "&&" + socket.getInetAddress() + "&&"
					+ socket.getLocalPort();
			outputStream.write(string.getBytes()); // 将账号密码ip+pass发送到服务器

			InputStream inputStream = socket.getInputStream();
			byte[] b = new byte[12];
			int len = -1;
			String res = null;
			while (-1 != (len = inputStream.read(b))) {
				// 将字节数组转换为字符串
				res = new String(b, 0, len);
				System.out.println(res);
				break;
			}
			if (res.equals("登录成功")) {

				System.out
						.println("*************************************************");
				System.out
						.println("*                  1.不聊天                                                        *");
				System.out
						.println("*                  2.聊天                                                            *");
				System.out
						.println("*                                               *");
				System.out
						.println("*                                               *");
				System.out
						.println("*                                               *");
				System.out
						.println("*                                               *");
				System.out
						.println("*************************************************");

				ClientSendMess clientsendMess = new ClientSendMess(socket);
				ClientReceMess clientreceMess = new ClientReceMess(socket);
				

				int a;
				
				a = reader.nextInt();
				if (a == 1) {
					clientsendMess.start();
					clientreceMess.start();
				} else if (a == 2) {
					
//					System.out
//							.println("*************************************************");
//					for (int j = 0; j < Server.threadArrayList.size(); j++) {			//显示当前能聊天的人
//						System.out.println(("------"+j + ":" + Server.threadArrayList
//								.get(j).getName()+"------").getBytes());
//					}
//					
//					
//					System.out.println(Server.threadArrayList.size());
//					System.out
//							.println("*************************************************");

					System.out.println("请输入想要和谁聊天");
					id = reader.next();
					string = "requestUser:&&" + id;
					outputStream.write(string.getBytes());
					clientsendMess.start();
					clientreceMess.start();
				}

			} else {
				System.out.println("登录失败");
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
