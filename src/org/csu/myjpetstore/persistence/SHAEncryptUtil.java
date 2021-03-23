package org.csu.myjpetstore.persistence;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: AiBinXiao
 * @Description: SHA加密算法工具类
 * @Date: 2017/9/12 9:44
 */
public class SHAEncryptUtil {

    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    public static String SHA256(final String strText)
    {
        return SHA(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText
     * @return
     */
    public static String SHA512(final String strText)
    {
        return SHA(strText, "SHA-512");
    }

    /**
     * 传入文本内容，返回 MD5 串
     *
     * @param strText
     * @return
     */
    public static String MD5(final String strText)
    {
        return SHA(strText, "MD5");
    }

    /**
     * 字符串 SHA 加密
     *
     * @param strText
     * @param strType
     * @return
     */
    private static String SHA(final String strText, final String strType)
    {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密类型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 类型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 转换为 string
                StringBuffer strHexString = new StringBuffer();
                // 遍历 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回结果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return strResult;

    }

    /**
     * 验证明文密码和加密后的密文是否一致
     * @param emial
     * @param encEmail
     * @return
     */
    public static boolean validateCode(String emial, String encEmail) {
        if (StringUtils.isEmpty(emial) || StringUtils.isEmpty(encEmail)) {
            return false;
        }
        boolean result = false;
        result = encEmail.equals(SHA256(emial));
        return result;
    }

    /*public static void main(String args[]){
        SHAEncryptUtil ey = new SHAEncryptUtil();
        System.out.println(ey.MD5("ILoveYou"));//62accaf23ac9a73c0b28765b7dfaf75a
        System.out.println(ey.MD5("ILoveYou").length());//32
        System.out.println(ey.SHA256("ILoveYou"));//7502d2589a3588f2572619b7467c13afaa90e4559367537f1a9dfbf6c76c4e26
        System.out.println(ey.SHA256("ILoveYou").length());//64
        System.out.println(ey.SHA512("ILoveYou"));//3a90feeeeb40ec2fcd0db3cbe146ddd071a53cdbe29bbd590bc775dcbadc001a6f1570a8f950c7eb421b3297df869d78ca134fbecfb2ae79b94e6af15b2bcc87
        System.out.println(ey.SHA512("ILoveYou").length());//128

        System.out.println(validatePwd("ILoveYou","3a90feeeeb40ec2fcd0db3cbe146ddd071a53cdbe29bbd590bc775dcbadc001a6f1570a8f950c7eb421b3297df869d78ca134fbecfb2ae79b94e6af15b2bcc87"));
    }*/
}
