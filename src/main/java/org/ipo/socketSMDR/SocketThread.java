package org.ipo.socketSMDR;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SocketThread extends Thread {

	private Socket sc;
	private Properties props;
	private static final Logger logger = Logger.getLogger(SocketThread.class);
	public SocketThread(Socket sc,Properties props) {
		// TODO Auto-generated constructor stub
		this.sc=sc;
		this.props=props;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			
			try {
				//entrada = new DataInputStream(sc.getInputStream());
				 
				 String texto="";
				 
				 BufferedReader entrada = new BufferedReader(new InputStreamReader(sc.getInputStream(),"UTF-8"));
				 PrintWriter out = new PrintWriter(sc.getOutputStream(), true);
				 while(true)
				 {
					 
					 texto=entrada.readLine();
					
					 if(!sc.isConnected())
						 break;
					 if(texto==null)
						 break;
					 
					logger.info(texto);
					System.out.println(texto);
					
					String b=(String) props.get("guardarEnBD");
					Boolean bb=Boolean.valueOf(b);
					
					if(bb)
					{
						//String[] array= new String[30];
						//for(int a=0;a<array.length;a++)
						//	array[a]="";
						
						
						
						String textos[]=texto.split(",");
						
						//for(int a=0;a<textos.length;a++)
						//	array[a]=textos[a]; 
						
						List<String> data=new ArrayList<String>();
						data.addAll(Arrays.asList(textos));
						SqlServer sql= new SqlServer();
						sql.insert(props, data);
						
						
					}
					
				 }
				 
				entrada.close();
				out.close();
				sc.close();
				
				System.out.println("termina conexion");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	
}
