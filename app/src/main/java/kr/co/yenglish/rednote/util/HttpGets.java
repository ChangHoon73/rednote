package kr.co.yenglish.rednote.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Hoon on 2016-11-12.
 */

public class HttpGets {

    private static String TAG	=	"REDNOTE";
    public static String getData(String homepage, String param){

        //id=id1 & id2=
        String strRead="";
        Log.d(TAG, "http param:  "+ param);
        try {
            //요청data 또는 요청XML
            URL url = new URL(homepage);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            if(param != null){
                String[] params = param.split("&");
                for( String ps : params){
                    String[] pr = ps.split("=");
                    String data = URLEncoder.encode(pr[0], "UTF-8") + "="+
                            URLEncoder.encode(pr[1], "UTF-8")+"&";
                    wr.write(data);
                    Log.d(TAG, "http: 인코더까지 '-'");
                }
            }else { Log.d(TAG, "http: param 이 들어오지않음"); }
            wr.flush();

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line="";

            //overlap
            while ((line = rd.readLine()) != null) {
                //Log.v(TAG, "line:"+line);
                strRead +=line;  //결과 얻어오기
            }
            wr.close();
            rd.close();
        } catch (Exception e) {}
        Log.d(TAG, "http strread: "+ strRead);
        return strRead;
    }
}
