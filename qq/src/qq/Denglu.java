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
			System.out.println("�ͻ���" + socket.getRemoteSocketAddress()); // ��ʾ�����ip+�˿�
			System.out.println("�����" + socket.getLocalAddress());
			System.out.println("�����" + socket.getLocalPort());

			inputStream = socket.getInputStream();
			byte[] b = new byte[1024];
			int len = -1;
			String res = null;
			while (-1 != (len = inputStream.read(b))) { // �����˺�����
				// ���ֽ�����ת��Ϊ�ַ���
				res = new String(b, 0, len);
				System.out.println(res);
				break;
			}
			
			String[] idPaStrings = res.split("&&"); // ��֤�˺�����
			String[] ip0 = idPaStrings[2].split("localhost/");			//ȥ��ip�е�localhost
			System.out.println("�ͻ���ip��"+ip0[1]);									//��ӡ�ͻ���ip

			for (int i = 0; i < Server.idPass.size(); i++) {
				if (idPaStrings[0].equals(Server.idPass.get(i).getId())
						&& idPaStrings[1]
								.equals(Server.idPass.get(i).getPass())) { // �ж�id
																			// pass
					System.out.println("��½�ɹ�");

					ThreadString threadString = new ThreadString(Server.idPass
							.get(i).getId(), socket); // ���浱ǰ�ͻ��˵�������Ϣ
					Server.threadArrayList.add(threadString);			// ���浱ǰ�ͻ��˵�������Ϣ

					int a = Integer.parseInt(idPaStrings[3]);
					outputStream = socket.getOutputStream();
					outputStream.write("��¼�ɹ�".getBytes());

					
					for(int j=0;j<Server.threadArrayList.size();j++){
		            	outputStream=Server.threadArrayList.get(j).getSocket().getOutputStream();
		            	outputStream.write((idPaStrings[0]+"�ѵ�¼").getBytes());
		            }
					
					
					
					inputStream = socket.getInputStream();
					b = new byte[1024];
					len = -1;
					res = null;
					while (-1 != (len = inputStream.read(b))) { // �����˺�����
						// ���ֽ�����ת��Ϊ�ַ���
						res = new String(b, 0, len);
						System.out.println(res);
						break;
					}
					System.out.println("���ڲ�ѯ�û�:" + res);
					String[] strings = res.split("&&");
					if (strings[0].equals("requestUser:")) {				//�ж��Ƿ�ʱ����
						for (int j = 0; j < Server.threadArrayList.size(); j++) {

							ThreadString threadString2 = Server.threadArrayList
									.get(j);
							if (strings[1].equals(threadString2.getName())) {				//�ж���������Ƿ����
								System.out.println("�д���");
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
