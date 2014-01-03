package helper;

import java.security.MessageDigest;

public class Helper 
{
	public static String md5Java(String pass)
	{
        String digest = null;
        try 
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(pass.getBytes("UTF-8"));
           
            //converting byte array to Hexadecimal String
           StringBuilder sb = new StringBuilder(2*hash.length);
           for(byte b : hash)
           {
               sb.append(String.format("%02x", b&0xff));
           }          
           digest = sb.toString();
          
        } 
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
        return digest;
    }
}
