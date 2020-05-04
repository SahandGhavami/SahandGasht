package ir.sahand.tour;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static String ConvertInputStreamToString(InputStream inputStream){
        StringBuilder sb = new StringBuilder();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        try{
            while (bis.available() != 0 ){
                sb.append((char) bis.read());
            }
            bis.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return sb.toString();
    }
}
