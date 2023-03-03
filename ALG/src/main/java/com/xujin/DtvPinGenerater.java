package com.xujin;

import java.math.BigInteger;
import java.security.MessageDigest;

public class DtvPinGenerater{
	private static final int LENGTH_OF_SMARTCARDID = 11;
	private static final int LENGTH_OF_CONST = 16;

	private void xor(byte[] byteArray1, byte[] byteArray2, int len, byte[] byteResult){
		if ((byteArray1.length < len) || (byteArray2.length < len) || (byteResult.length < len)){
			return;
		}

		for (int i=0; i<len; i++){
			byteResult[i] = (byte) (byteArray1[i] ^ byteArray2[i]);
		}
	}

	private String bytesToString(byte[] bByte){
		StringBuffer strBuf = new StringBuffer();

		for (int i=0; i<bByte.length; i++){
			if (Integer.toHexString(0xFF & bByte[i]).length() == 1){
				strBuf.append("0").append(Integer.toHexString(0xFF & bByte[i]));
			}else{
				strBuf.append(Integer.toHexString(0xFF & bByte[i]));
			}
		}
		return strBuf.toString().toUpperCase();
	}

	public String getPIN(String strSmartCard){

		String strPIN=null;

		if (strSmartCard.length() != LENGTH_OF_SMARTCARDID)
			return null;

		try {
			byte[] const_data1= {(byte)0x29, (byte)0x4A, (byte)0x6D, (byte)0xFF, (byte)0x91, (byte)0x8D, (byte)0x7C, (byte)0xE0,
					             (byte)0xD8, (byte)0x42, (byte)0x15, (byte)0xBA, (byte)0x33, (byte)0xDE, (byte)0x64, (byte)0xB8};
			byte[] const_data2= {(byte)0x1D, (byte)0x3B, (byte)0x42, (byte)0x70, (byte)0x29, (byte)0x58, (byte)0xA6, (byte)0x03,
					             (byte)0xBC, (byte)0xEE, (byte)0x39, (byte)0x95, (byte)0x57, (byte)0xC4, (byte)0x82, (byte)0x61};
			byte[] dataBuf = new byte[LENGTH_OF_SMARTCARDID + 2*LENGTH_OF_CONST];

			System.arraycopy(const_data1, 0, dataBuf, 0, LENGTH_OF_CONST);
			System.arraycopy(strSmartCard.getBytes(), 0, dataBuf, LENGTH_OF_CONST, LENGTH_OF_SMARTCARDID);
			System.arraycopy(const_data2, 0, dataBuf, LENGTH_OF_CONST+LENGTH_OF_SMARTCARDID, LENGTH_OF_CONST);

			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(dataBuf);
			byte[] hashValue = messageDigest.digest();

			byte[] tmpBuff = new byte[8];
			System.arraycopy(hashValue, 8, tmpBuff, 0, 8);
			xor(hashValue, tmpBuff, 8, hashValue);
			System.arraycopy(hashValue, 16, tmpBuff, 0, 8);
			xor(hashValue, tmpBuff, 8, hashValue);
			System.arraycopy(hashValue, 24, tmpBuff, 0, 8);
			xor(hashValue, tmpBuff, 8, hashValue);
			System.arraycopy(hashValue, 4, tmpBuff, 0, 4);
			xor(hashValue, tmpBuff, 4, hashValue);

			BigInteger intPIN = new BigInteger(bytesToString(hashValue).substring(0,8), 16);
			strPIN = String.format("%010d", intPIN).substring(4,10);
			//System.out.println("strPIN:" + strPIN);
		}catch (Exception e) {
			System.out.println("Exception occoured : " + e);
		}

		return strPIN;
	}
}
