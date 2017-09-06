package view;

import java.net.InetAddress;
import java.net.Socket;

public class ThreadString {

	private String name;
	private Socket socket;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public ThreadString(String name, Socket socket) {
		super();
		this.name = name;
		this.socket = socket;
	}
	@Override
	public String toString() {
		return "ThreadString [name=" + name + ", socket=" + socket + "]";
	}

	

}
