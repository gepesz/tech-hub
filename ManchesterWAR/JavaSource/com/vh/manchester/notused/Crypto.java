package com.vh.manchester.notused;

import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.vh.manchester.notused.*;


/**
 * Encryption utils.
 *
 * Copyright (c) 2003 The VH Corporation.  All rights reserved.  
 * Copying or reproduction without prior written approval is prohibited.
 * 
 * @author ferenc.vehmann
 * Jun 15, 2004
 */
public class Crypto {

	private static byte[] rawKey={73, -99, 66, -83,-121, 96, 64, 121, 
								  56, -77,100,-102,  94, 35, 88,-52};
	
	private static Logger log = Logger.getLogger(Crypto.class);	
	public static final String HASH_ALGORITHM = "MD5"; 

	/**
	 * Encrypt the argument
	 * @return String
	 */    
	public static String encrypt(String s) {  
		return new String(convert827(crypt(s.getBytes(),Cipher.ENCRYPT_MODE))); 
	}


	/**
	 * Decrypt the argument
	 * @return String
	 */
	public static String decrypt(String s) {
		return new String(crypt(convert728(s.getBytes()),Cipher.DECRYPT_MODE));
	}


	/**
	 * Hash the argument
	 * @return String
	 */
	public static String hash(String s) {
	  String temp = null;
	  try {
		MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
		temp = new String(Base64Coder.encode(md.digest(s.getBytes())));
	  } catch(Exception e) {
	  	log.error("Hash error: "+e.toString());
		e.printStackTrace();
	  }
	  return temp;
	}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Private methods:

	private static byte[] crypt(byte[] input, int mode ) {
		try {  
      		Provider sunJce = new com.sun.crypto.provider.SunJCE();
      		Security.addProvider(sunJce);
    
      		SecretKeySpec sKeySpec = new SecretKeySpec(rawKey,"Blowfish");
      		Cipher cipher = Cipher.getInstance("Blowfish");
      		cipher.init(mode, sKeySpec);
  
      		return cipher.doFinal(input); 
    	} catch (Exception e) {
			log.error("Encrypt error: "+e.toString());
			e.printStackTrace();
		}
		return null;
	}
  
	private static byte[] convert827(byte[] b8) { //converting the byteflow into 7bit per bytes
		byte[] b7 = new byte [(b8.length % 7==0) ? b8.length*8/7 : b8.length*8/7+1];
		long k=0;
		for(int i=0; i<b8.length; i++) {
			for (int j=0; j<8; j++) {
				b7[(int)(k/7)]+= ((b8[i]&(1<<j))>0) ? (1<< (k%7)):0;
				k++;
			}
		}
		return b7;
	}
  
	private static byte[] convert728(byte[] b7) { //converting 7bits per bytes into bytes
		byte[] b8 = new byte[b7.length*7/8];
		long k=0;
		for(int i=0; i<b7.length && k/8<b8.length; i++) {
			for(int j=0; j<7 && k/8<b8.length; j++) {
				b8[(int)(k/8)]+= ((b7[i]&(1<<j))>0) ? (1<< (k%8)):0;
				k++;
			}
		}
		return b8;
	}

   /*
   private static void generateKey() {
	   Provider sunJce = new com.sun.crypto.provider.SunJCE();
	   Security.addProvider(sunJce);
                   
	   KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
	   kgen.init(448); //448 the maximum key size for blowfish
	   SecretKey skey = kgen.generateKey();
	   byte[] rawKey = skey.getEncoded();
   }
   */

}
