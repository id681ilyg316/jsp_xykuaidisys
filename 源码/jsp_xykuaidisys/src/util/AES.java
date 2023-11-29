package util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * This program generates a AES key, retrieves its raw bytes, and then
 * reinstantiates a AES key from the key bytes. The reinstantiated key is used
 * to initialize a AES cipher for encryption and decryption.
 */

public class AES {
	// ����
	public static byte[] Encrypt(byte[] sSrc, String sKey, String offset)
			throws Exception {
		if (sKey == null) {
			System.out.print("KeyΪ��null");
			return null;
		}
		// �ж�Key�Ƿ�Ϊ16λ
		if (sKey.length() != 16) {
			System.out.print("Key���Ȳ���16λ");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "�㷨/ģʽ/���뷽ʽ"
		IvParameterSpec iv = new IvParameterSpec(offset.getBytes());// ʹ��CBCģʽ����Ҫһ������iv�������Ӽ����㷨��ǿ��
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		// cipher.
		byte[] encrypted = cipher.doFinal(sSrc);

		return encrypted;
		// return new
		// BASE64Encoder().encode(encrypted);//�˴�ʹ��BASE64��ת�빦�ܣ�ͬʱ����2�μ��ܵ����á�
	}

	// ����
	public static byte[] Decrypt(byte[] sSrc, String sKey, String offset)
			throws Exception {
		try {
			// �ж�Key�Ƿ���ȷ
			if (sKey == null) {
				System.out.print("KeyΪ��null");
				return null;
			}
			// �ж�Key�Ƿ�Ϊ16λ
			if (sKey.length() != 16) {
				System.out.print("Key���Ȳ���16λ");
				return null;
			}
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(offset.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			// byte[] encrypted1 = new
			// BASE64Decoder().decodeBuffer(sSrc);//����base64����
			try {
				byte[] original = cipher.doFinal(sSrc);
				// String originalString = new String(original);
				return original;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	public static String byteArray2Hex(byte[] src) {
		StringBuilder sb = new StringBuilder();
		for (byte by : src) {
			sb.append(by);
		}
		return sb.toString();

	}

	public static void main(String[] args) throws Exception {
		/*
		 * �����õ�Key ������26����ĸ��������ɣ���ò�Ҫ�ñ����ַ�����Ȼ�����������ô�þ������˿��������
		 * �˴�ʹ��AES-128-CBC����ģʽ��key��ҪΪ16λ��
		 */

//		String cKey = SysConsts.AES_KEY;
//		// ��Ҫ���ܵ��ִ�
//		String cSrc = "Email : arix04@xxx.com";
//		cSrc = "abcdeefghijkl"; // \n�й�
//		System.out.println(cSrc);
//		// ����
//		long lStart = System.currentTimeMillis();
//		byte[] enced = AES.Encrypt(cSrc.getBytes(), cKey);
//		// String enString = new BASE64Encoder().encode(enced);;
//		String base64 = LsdUtils.encodeToBase64(enced);
//		System.out.println("���ܺ���ִ��ǣ�" + base64);
//
//		long lUseTime = System.currentTimeMillis() - lStart;
//		System.out.println("���ܺ�ʱ��" + lUseTime + "����");
//		// ����
//		lStart = System.currentTimeMillis();
//		// decode from base64
//		byte[] ori = LsdUtils.decodeFromBASE64(base64);
//		byte[] DeString = AES.Decrypt(ori, cKey);
//		System.out.println("���ܺ���ִ��ǣ�" + new String(DeString));
//		lUseTime = System.currentTimeMillis() - lStart;
//		System.out.println("���ܺ�ʱ��" + lUseTime + "����");
	}

}
