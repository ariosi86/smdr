package org.ipo.socketSMDR;

import java.io.IOException;


import java.net.ServerSocket;
import java.net.Socket;

import java.util.Properties;

public class SocketServer extends Thread{
	
	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	private Integer port;
	
	private ServerSocket sc;
	
	private Properties props;
	
	
	
	public void run()
	{
		
			try {
				
				sc= new ServerSocket(port);
				System.out.println("conectado al puerto");
				while(true)
				{
					Socket so = sc.accept();
					Thread tc= new SocketThread(so,props);
					tc.start();
					
				}

				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		
	
	}
	
	public void stopSocket()
	{
		
		try {
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}
	

}
