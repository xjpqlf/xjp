package ui.zlz.utility;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

public class GetSign {

    private static String sign;

    public static String getSign() {

        Date date = new Date();
        long str = date.getTime();
       String num=getRandomString(5);
        String s="API595654866dklnULdnld";
        SHA sha=new SHA();

        try {
            sign = MD5.md5(  SHA.SHA1Digest(str+num+s));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

       // DebugFlags.logD("签名"+str+"=="+num+"=="+sign);



        return str+","+num+","+sign;
    }

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
