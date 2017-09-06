package view;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Button;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JTextField;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.JTable;

public class MainView extends JPanel implements Runnable{
	private JTextPane textPane;
	InputStream inputStream=null;
	OutputStream outputStream=null;
	Socket socket=null;
	JPanel panel=null;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public MainView(Socket socket) {
		this.socket=socket;
		setBackground(Color.ORANGE);
		setLayout(null);
		setBounds(0, 0, 400, 500);
		
		textPane = new JTextPane();
		textPane.setBackground(Color.PINK);
		textPane.setBounds(143, 0, 257, 371);
		add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(Color.ORANGE);
		textPane_1.setBounds(143, 369, 257, 131);
		add(textPane_1);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 144, 500);
		add(panel);
		
		table = new JTable();
		
		panel.add(table);
		
		
		
		textPane.setVisible(true);
		setVisible(true);

		
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		int sum=0;
		ArrayList<Button> buttons=new ArrayList<Button>();
		try {
			for (;;) {
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
				System.out.println("正在查询用户:" + res);
				System.out.println("123");
				String[] strings=res.split("&&");
				System.out.println(strings[0]);
				System.out.println(strings[1]);
				
				if(strings[0].equals("denglu:")){
					System.out.println("zxb");
					JButton denglu = new JButton(strings[1]);
					denglu.setBounds(10, 10, 120, 50);;
//					denglu.setSize(130, 50);;
					panel.add(denglu);
					
					ClientView.frame.repaint();
					ClientView.frame.setVisible(true);
				}
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
