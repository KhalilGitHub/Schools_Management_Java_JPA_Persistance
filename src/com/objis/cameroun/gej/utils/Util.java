package com.objis.cameroun.gej.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;



public class Util {
	
	public static String parseBlobToString(Blob photo) 
	{
		                           
			InputStream inputStream = null;
			try {
				inputStream = photo.getBinaryStream();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}                
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();                
			byte[] buffer = new byte[4096];                
			int bytesRead = -1;   
			
			try {
			while ((bytesRead = inputStream.read(buffer)) != -1) 
			{                    
				outputStream.write(buffer, 0, bytesRead);                                   
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                                 
			byte[] imageBytes = outputStream.toByteArray();                
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);                                                  
			try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                
			try {
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
			
			return base64Image;
	}

}
