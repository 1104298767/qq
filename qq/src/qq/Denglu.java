package qq;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Denglu extends Thread {
	Thread thread = Thread.currentThread();
	Socket socket;
	String name;

	public Denglu(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		OutputStream outputStream;
		InputStream inputStream;

		try {
			System.out.println("客户端" + socket.getRemoteSocketAddress()); // 显示服务端ip+端口
			System.out.println("服务端" + socket.getLocalAddress());
			System.out.println("服务端" + socket.getLocalPort());

			inputStream = socket.getInputStream();
			byte[] b = new byte[1024];
			int len = -1;
			String res = null;
			while (-1 != (len = inputStream.read(b))) { // 读入账号密码
				// 将字节数组转换为字符串
				res = new String(b, 0, len);
				System.out.println(res);
				break;
			}
			
			String[] idPaStrings = res.split("&&"); // 验证账号密码
			String[] ip0 = idPaStrings[2].split("localhost/");			//去掉ip中的localhost
			System.out.println("客户端ip："+ip0[1]);									//打印客户端ip

			for (int i = 0; i < Server.idPass.size(); i++) {
				if (idPaStrings[0].equals(Server.idPass.get(i).getId())
						&& idPaStrings[1]
								.equals(Server.idPass.get(i).getPass())) { // 判断id
																			// pass
					System.out.println("登陆成功");

					ThreadString threadString = new ThreadString(Server.idPass
							.get(i).getId(), socket); // 保存当前客户端的连接信息
					Server.threadArrayList.add(threadString);			// 保存当前客户端的连接信息

					int a = Integer.parseInt(idPaStrings[3]);
					outputStream = socket.getOutputStream();
					outputStream.write("登录成功".getBytes());

					
					for(int j=0;j<Server.threadArrayList.size();j++){
		            	outputStream=Server.threadArrayList.get(j).getSocket().getOutputStream();
		            	outputStream.write((idPaStrings[0]+"已登录").getBytes());
		            }
					
					
					
					inputStream = socket.getInputStream();
					b = new byte[1024];
					len = -1;
					res = null;
					while (-1 != (len = inputStream.read(b))) { // 读入账号密码
						// 将字节数组转换为字符串
						res = new String(b, 0, len);
						System.out.println(res);
						break;
					}
					System.out.println("正在查询用户:" + res);
					String[] strings = res.split("&&");
					if (strings[0].equals("requestUser:")) {				//判断是否时聊天
						for (int j = 0; j < Server.threadArrayList.size(); j++) {

							ThreadString threadString2 = Server.threadArrayList
									.get(j);
							if (strings[1].equals(threadString2.getName())) {				//判断聊天对象是否存在
								System.out.println("有此人");
								Socket socketTo = Server.threadArrayList.get(j)
										.getSocket();
								System.out.println(socket);
								System.out.println(socketTo);
								String thisName=idPaStrings[0];
								String thatName=strings[1];
								ServerReceMess serverReceMess;
								serverReceMess = new ServerReceMess(thisName,socket,thatName,
										socketTo);
								serverReceMess.start();
							}
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
