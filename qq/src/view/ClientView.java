package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Container;
import java.awt.List;
import java.awt.BorderLayout;

import javax.swing.JTextPane;

import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument.Content;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientView extends JPanel implements ActionListener {

	static JFrame frame;
	private JTextField id;
	private JTextField pass;
	private JButton denglu;
	private JButton zhuce;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientView window = new ClientView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setBounds(100, 100, 400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("ÕËºÅ");
		label.setBounds(67, 154, 32, 31);
		frame.getContentPane().add(label);

		JLabel lblNewLabel = new JLabel("ÃÜÂë");
		lblNewLabel.setBounds(67, 224, 32, 31);
		frame.getContentPane().add(lblNewLabel);

		id = new JTextField();
		id.setBounds(119, 154, 145, 31);
		frame.getContentPane().add(id);
		id.setColumns(10);

		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(119, 224, 145, 31);
		frame.getContentPane().add(pass);

		denglu = new JButton("µÇÂ½");
		denglu.setBounds(84, 289, 66, 23);
		frame.getContentPane().add(denglu);
		zhuce = new JButton("×¢²á");
		zhuce.setBounds(179, 289, 60, 23);
		frame.getContentPane().add(zhuce);

		denglu.addActionListener(this);
		zhuce.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == denglu) {

			InetAddress inetAddress;
			Socket socket = null;
			String res = null;
			try {

				System.out.println("²âÊÔ");
				inetAddress = InetAddress.getByName("localhost");
				socket = new Socket(inetAddress, 7788);
				OutputStream outputStream = socket.getOutputStream();
				InputStream inputStream = socket.getInputStream();
				String string = id.getText() + "&&" + pass.getText();
				string = string + "&&" + socket.getInetAddress() + "&&"
						+ socket.getLocalPort();
				System.out.println(id.getText());
				System.out.println(pass.getText());
				outputStream.write(string.getBytes());

				inputStream = socket.getInputStream();
				byte[] b = new byte[1024];
				int len = -1;
				while (-1 != (len = inputStream.read(b))) { // ¶ÁÈëÕËºÅÃÜÂë
					// ½«×Ö½ÚÊý×é×ª»»Îª×Ö·û´®
					res = new String(b, 0, len);
					System.out.println(res);
					break;
				}

			} catch (UnknownHostException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (res.equals("µÇÂ¼³É¹¦")) {
				frame.getContentPane().removeAll();
				MainView mainView = new MainView(socket);
				Thread thread = new Thread(mainView);
				thread.start();
				frame.getContentPane().add(mainView);
				frame.repaint();
				frame.setVisible(true);

			} 
			
		}

	}

}
