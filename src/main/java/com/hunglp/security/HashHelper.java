package com.hunglp.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class HashHelper {
	 public static String getSecurePassword(String passwordToHash, byte[] salt)
	    {
	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-512");
	            md.update(salt);
	            byte[] bytes = md.digest(passwordToHash.getBytes());
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
	        }
	        catch (NoSuchAlgorithmException e)
	        {
	            e.printStackTrace();
	        }
	        return generatedPassword;
	    }
	     
	    //Add salt
	    public static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
	    {
	        //Always use a SecureRandom generator
	        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
	        //Create array for salt
	        byte[] salt = new byte[16];
	        //Get a random salt
	        sr.nextBytes(salt);
	        //return salt
	        return salt;
	    }
	    
	    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
			String password = "hunglp9397";
			String salt = "[B@1510e771";
			System.out.println(getSecurePassword(password,salt.getBytes()));
		}
}
