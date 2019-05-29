package com.hunglp;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.hunglp.security.HashHelper;

public class TestHash {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
		String passwordToHash = "hunglp9397";
        //byte[] salt = HashHelper.getSalt();
		String salt = "[B@348c9009";
         
        String securePassword = HashHelper.getSecurePassword(passwordToHash, salt.getBytes());
        System.out.println(securePassword); //Prints 83ee5baeea20b6c21635e4ea67847f66
         
        String regeneratedPassowrdToVerify = HashHelper.getSecurePassword(passwordToHash, salt.getBytes());
        System.out.println(regeneratedPassowrdToVerify); //Prints 83ee5baeea20b6c21635e4ea67847f66
	}
}
