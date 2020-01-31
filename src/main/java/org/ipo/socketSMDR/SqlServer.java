package org.ipo.socketSMDR;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public class SqlServer {

	 public void  insert(Properties props,List<String> data)
    {
        Connection conexion=null;
      
        try
        {
//        	Class.forName("net.sourceforge.jtds.jdbc.Driver");
//        	 String url = "jdbc:jtds:sqlserver://"+props.get("ip")+":1433/"+props.get("baseDatos")+";"
//             		+"instance="+props.get("instancia")+";"
//             		+ "user="+props.get("usuario")+";password="+props.get("password")+";";
  
        	Class.forName("com.mysql.jdbc.Driver");
       	 String url = "jdbc:mysql://"+props.get("ip")+"/"+props.get("baseDatos");
            		//+ "user="+props.get("usuario")+";password="+props.get("password")+";";        	
        	
//        	<property name="driverClassName" value="com.mysql.jdbc.Driver" />
//    		<property name="url" value="jdbc:mysql://localhost:3306/tarificador" />
//    		<property name="username" value="tarificador" />
//    		<property name="password" value="tarificador" />
        	
        	
            //String url = "jdbc:sqlserver://"+props.get("ip")+";"
            	//	+ "databaseName="+props.get("baseDatos")+";"
            	//			+ "user="+props.get("usuario")+";password="+props.get("password")+";";
            
            conexion= DriverManager.getConnection(url,""+props.get("usuario"),""+props.get("password"));
            
            DatabaseMetaData metadata = conexion.getMetaData();
            ResultSet resultSet = metadata.getColumns(null, null, ""+props.get("tabla"), null);
            
            String column="";
            int a=0;
            while (resultSet.next()) {
                String name = resultSet.getString("COLUMN_NAME");
               // String type = resultSet.getString("TYPE_NAME");
                column+=","+name;
                a++;
             
              }
            column=column.substring(1);
            
            String query="INSERT INTO  "+props.get("tabla")+" "
					+ "("+column+") "
					+" values(";
			
           
            if(a>data.size())
            {
            		int size=data.size();
            		for(int i=0;i<(a-size);i++)
            			data.add("");
            }
           
            
			for(String d:data)
			{
				query+="'"+d+"',";
			}
			
			//if(array.length<30)
			//	query+="'',";
			
			query=query.substring(0, query.length()-1);
			
			query+= ")";
			
            
            PreparedStatement ps = conexion.prepareStatement(query);	
            ps.execute();
            conexion.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
	
}
