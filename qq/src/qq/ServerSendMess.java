package qq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ServerSendMess extends Thread {
	Socket socket=null;

	public ServerSendMess(Socket socket) {

		this.socket=socket;
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
				}
				outputStream.close();

			}
			// socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
