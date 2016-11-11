package kr.co.yenglish.rednote.util;

import java.util.ArrayList;

/**
 * Created by Hoon on 2016-11-12.
 */

public class ParseUtils{

    /**
     * Used for extracting real string from string contains " (quotation) mark.
     *
     * @param input
     * @return
     */
    public static String deleteQuatationFromJson(String input){
        return input.replace("\"", "");
    }

    /**
     * Change (ArrayList of String -> String)
     * For example, list(str1, str2, str3, ...) -> one_str = str1,str2,str3,...
     *
     * @param stringList
     * @return
     */
    public static String getCombinedStringFromList(ArrayList<String> stringList){
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < stringList.size() ; i++){
            sb.append(stringList.get(i));
            if(i < (stringList.size()-1)){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * Change (String -> ArrayList) that divide using , (comma)
     *
     * @param expStr
     * @return
     */
    public static ArrayList<String> parseExpString(String expStr){
        return stringDivider(expStr, ",");
    }

    /**
     * Change (String -> ArrayList) that divide using / (slash)
     *
     * @param choiceStr
     * @return
     */
    public static ArrayList<String> parseChoiceString(String choiceStr){
        return stringDivider(choiceStr, "/");
    }

    private static ArrayList<String> stringDivider(String str, String mark){
        ArrayList<String> list = new ArrayList<String>();
        String[] result = str.split(mark);
        for(int i = 0 ; i < result.length ; i++){
            list.add(result[i]);
        }
        return list;
    }


}