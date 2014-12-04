package tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2014/11/27.
 */
public class sendG {
    public static String sendGet(String url, String param, String charset) {
        BufferedReader in = null;
        String body = "";
        try {
            URL toUrl = new URL(url + param);
            URLConnection connection = toUrl.openConnection();

            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.connect();

            /*Map<String,List<String>> header = connection.getHeaderFields();
            for (String key : header.keySet()) {
                System.out.println(key + " ---> " + header.get(key));
            }*/

            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            String line = "";
            while ((line = in.readLine()) != null) {
                body += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return body;
    }
}
