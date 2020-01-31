package org.ipo.socketSMDR;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;


public class Main {

	public static void main(String[] args){
		
		
		Properties props = new Properties();
	    InputStream is = null;
	    try {
	            is = Main.class.getClassLoader().getResourceAsStream("configuracion.properties");
	            props.load( is );
	            System.out.println(props.get("puertoCaptura"));
	            System.out.println(props.get("ip"));
	            System.out.println(props.get("baseDatos"));
	            System.out.println(props.get("baseDatos"));
	    }
	    catch ( Exception e ) { 
	    	
	    	e.printStackTrace();}
		
		SocketServer socket= new SocketServer();
		socket.setPort(Integer.valueOf(props.getProperty("puertoCaptura")));
		socket.setProps(props);
		 System.out.println("va a conectar al puerto" + Integer.valueOf(props.getProperty("puertoCaptura")));
		socket.start();
		
		
	}

}
