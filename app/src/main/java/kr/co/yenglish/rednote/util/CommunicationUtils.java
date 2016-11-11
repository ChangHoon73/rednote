package kr.co.yenglish.rednote.util;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.HashMap;

import kr.co.yenglish.rednote.Constants;
import kr.co.yenglish.rednote.util.extras.HttpRequest;

/**
 * Created by Hoon on 2016-11-12.
 */

public class CommunicationUtils {

    // Constants for JOIN
    private final String JOIN_OK = "OK";
    private final String JOIN_FAIL = "FAIL";
    private final String JOIN_ALREADY_ID = "USINGID";

    // Constants for LOGIN
    private final String LOGIN_OK = "OK";

    // Constants for general case
    private final String RESULT_OK = "OK";
    private final String RESULT_FAIL = "FAIL";
    private final String RESULT_ERROR = "ERROR";

    private Context mContext;

    public CommunicationUtils(){}

    public CommunicationUtils(Context context){
        this.mContext = context;
    }


    public boolean loginService(String uid, String upw){
        boolean isSucess = false;
        try{
            String query = Constants.DEFAULT_HOSTNAME+"/php/loginok.php";
            HashMap<String, String> postData = new HashMap<String, String>();
            postData.put("api_key", Constants.API_KEY);
            postData.put("uid", uid);
            postData.put("upw", upw);

            String tempResult = HttpRequest.post(query).form(postData).body();
            Utils.log("Login result : "+ tempResult);

            JsonElement jElement = new JsonParser().parse(tempResult);
            JsonArray jArray = jElement.getAsJsonArray();

            String result = jArray.get(0).getAsJsonObject().get("result").toString();

            Utils.log("real result : "+ result);
            if( LOGIN_OK.equals(ParseUtils.deleteQuatationFromJson(result)) ){
                String name = jArray.get(0).getAsJsonObject().get("name").toString();
                int depth = jArray.get(0).getAsJsonObject().get("depth").getAsInt();
                isSucess = true;
                Utils.setStringPreference(mContext, "uid", uid);
                Utils.setStringPreference(mContext, "name", name);
                Utils.setIntegerPreference(mContext, "depth", depth);

            }else{
                isSucess = false;
            }

        }catch(Exception err){
            Utils.exceptionLog(err);
            isSucess = false;
        }

        return isSucess;
    }
}
