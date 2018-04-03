package com.ssm;

import java.security.MessageDigest;
import java.util.UUID;

public class MD5Helper {

    /**
     * @Description:加密-32位小写
     */
    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }


    /**
     * @Description:加密-16位小写
     */
    public static String encrypt16(String encryptStr) {
        return encrypt32(encryptStr).substring(8, 24);
    }



    /**
     *@Description:uuid加密-16位小写
     */
    public static String uuidEncrypt16(){
        UUID uuid= UUID.randomUUID();
        return encrypt32(uuid.toString()).substring(8, 24);
    }

}
