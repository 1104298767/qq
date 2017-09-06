package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSendMess extends Thread {
	Socket socket;

	public ClientSendMess(Socket socket) {
		this.socket=socket;
		// this.b=b;
	}

	@Override
	public void run() {

		try {

			for (;;) {
				BufferedReader buf = new BufferedReader(new InputStreamReader(
						System.in));
				String string = buf.readLine();
				OutputStream outputStream = socket.getOutputStream();
				if (string.length() != 0) {
					outputStream.write(string.getBytes());
					System.out.println("ÕýÔÚ·¢ËÍ");
				}
			}

			// socket.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
