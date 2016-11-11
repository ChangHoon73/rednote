package kr.co.yenglish.rednote.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kr.co.yenglish.rednote.Constants;

/**
 * Created by Hoon on 2016-11-12.
 */

public class Utils {
    public static void log(Object str){
        if(Constants.LOGGING){
            Log.i(Constants.APP_TAG, (String)str);
        }
    }

    public static void error(Object str){
        if(Constants.LOGGING){
            Log.e(Constants.APP_TAG, (String)str);
        }
    }

    public static void print(Object str){
        Log.i(Constants.APP_TAG, (String)str);
    }

    /**
     * Print out information about the exception
     * - class name
     * - method name
     * - file name
     * - line number
     *
     * @param e The exception object
     */
    public static void exceptionLog(Exception e){
        if(Constants.LOGGING){
            StringBuffer sb = new StringBuffer();
            sb.append("<Exception> ");
            sb.append(e.getStackTrace()[0].getClassName());
            sb.append("::");
            sb.append(e.getStackTrace()[0].getMethodName());
            sb.append(" <");
            sb.append(e.getStackTrace()[0].getFileName());
            sb.append(" : ");
            sb.append(e.getStackTrace()[0].getLineNumber());
            sb.append(">");
            sb.append(" : ");
            sb.append(e.toString());
            print(sb.toString());
        }
    }

    /**
     * Compare two string.
     * It handles null related routine too.
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compareTwoString(String str1, String str2){
        if(str1 != null && str2 != null){		// compare if there is no null value
            return str1.equals(str2);
        }else if(str1 == null && str2 == null){	// both of them are null
            return true;
        }else{									// one is null, ohter one is not null
            return false;
        }
    }

    /**
     * Get MD5 hash value
     *
     * @param s
     * @return
     */
    public static String getMD5Hash(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            exceptionLog(e);
        } catch (Exception e){
            exceptionLog(e);
        }

        return "";
    }

    /**
     * Get SHA256 hash value
     *
     * @param s
     * @return
     */
    public static String getSHA256Hash(String s) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            exceptionLog(e);
        }catch (Exception e){
            exceptionLog(e);
        }

        return "";
    }

    public static String getKoreanKRWPriceFormat(String price){
        return getKoreanKRWPriceFormat(Integer.parseInt(price));
    }

    public static String getKoreanKRWPriceFormat(int price){
        String priceWon = String.format("%,d", price) + " 원";
        return priceWon;
    }

    /**
     * Generate random number
     *
     * @param range If it is 100, then it generate number between 0 ~ 99
     * @return
     */
    public static int randomIntGenerator(int range){
        Random random = new Random();
        return random.nextInt(range);
    }

    /**
     * Bitmap -> File (which saved in SD card - temp path)
     *
     * @param bitmap
     * @return
     */
    public static File bitmapToFile(Context context, Bitmap bitmap){
        File file = null;
        try{
            String tempFileName = "temp_bitmap_"+randomIntGenerator(7000)+".jpg";
            file = new File(context.getCacheDir(), tempFileName);
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }else{
                file.createNewFile();
            }

            //Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        }catch(Exception e){
            Utils.exceptionLog(e);
        }

        return file;
    }

    /**
     * Shows toast message
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows long-time toast message
     *
     * @param context
     * @param message
     */
    public static void showLongToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Check every variables are not null and not an empty (Only for string objects)
     * (Works well)
     *
     * @param params
     * @return
     */
    public static boolean everyVariableMustNotNullOrEmpty(String... params){
        boolean valid = true;

        try{
            for(int i = 0 ; i < params.length ; i++){
                if(params[i] == null || "".equals(params[i]) || "null".equals(params[i])){
                    valid = false;
                    break;
                }
            }
        }catch(Exception e){
            valid = false;
        }

        return valid;
    }

    /**
     * Validation check process.
     * Used for method that only one input parameter is null.
     *
     * @param params
     * @return
     */
    public static boolean onlyOneVariableIsNotNull(String... params){
        boolean valid = false;

        try{
            int count = 0;
            for(int i = 0 ; i < params.length ; i++){
                if(params[i] != null){
                    count++;
                }
            }

            if(count == 1){
                valid = true;
            }else{
                valid = false;
            }

        }catch(Exception e){
            valid = false;
        }

        return valid;
    }

    /**
     * Only specified view in position will be refreshed.
     *
     * @param position
     */
    public static void updateItemAtPosition(ListView listView, int position) {
        int visiblePosition = listView.getFirstVisiblePosition();
        View view = listView.getChildAt(position - visiblePosition);
        listView.getAdapter().getView(position, view, listView);
    }

    /**
     * Simple method (int -> boolean)
     * boolean and integer hava relationship basically, but for sure.
     *
     * @param intValue
     * @return
     */
    public static boolean integerToBoolean(int intValue){
        boolean result = false;
        if(intValue > 0 ){
            result = true;
        }else{
            result = false;
        }
        return result;
    }

    /**
     * DP -> Pixel
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int getPixelsFromDp(Context context, int dipValue){
        Resources r = context.getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
        return px;
    }

    /**
     * SP -> Pixel
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int getPixelsFromSp(Context context, int spValue){
        Resources r = context.getResources();
        int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, r.getDisplayMetrics());
        return px;
    }

    /**
     *
     * Clear all shared preferences
     *
     * @param context
     */
    public static void clearAllSharedPreferences(Context context) {
        context.getSharedPreferences(Constants.APP_TAG, Context.MODE_PRIVATE).edit().clear().commit();
    }

    /**
     *
     * Store string value to app data
     *
     * @param context
     * @param type
     * @param value
     */
    public static void setStringPreference(Context context, String type, String value) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.APP_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(type, value);
        editor.commit();
    }

    /**
     *
     * Get string value from app data
     *
     * @param context
     * @param type
     * @return
     */
    public static String getStringPreference(Context context, String type) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.APP_TAG, Context.MODE_PRIVATE);
        String value = prefs.getString(type, null);
        return value;
    }

    /**
     *
     * Store integer value to app data
     *
     * @param context
     * @param type
     * @param value
     */
    public static void setIntegerPreference(Context context, String type, int value) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.APP_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(type, value);
        editor.commit();
    }

    /**
     *
     * Get integer value from app data
     *
     * @param context
     * @param type
     * @return
     */
    public static int getIntegerPreference(Context context, String type) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.APP_TAG, Context.MODE_PRIVATE);
        int value = prefs.getInt(type, -1);
        return value;
    }

    /**
     *
     * Store boolean value to app data
     *
     * @param context
     * @param type
     * @param value
     */
    public static void setBooleanPreference(Context context, String type, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.APP_TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(type, value);
        editor.commit();
    }

    /**
     *
     * Get boolean value from app data
     *
     * @param context
     * @param type
     * @return
     */
    public static boolean getBooleanPreference(Context context, String type) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.APP_TAG, Context.MODE_PRIVATE);
        boolean value = prefs.getBoolean(type, false);
        return value;
    }

    /**
     * Convert "array of string" to "array list of string"
     *
     * TODO : Need to use Generics later
     *
     * @param array
     * @return
     */
    public static ArrayList<String> convertStringArrayToList(String[] array){
        ArrayList<String> stringList = new ArrayList<String>();
        for(int i = 0 ; i < array.length ; i++){
            stringList.add(array[i]);
        }
        return stringList;
    }

    /**
     * Validating email format is correct or incorrect
     *
     * @param email
     * @return
     */
    public static boolean validateEmailAddress(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Getting the distance between two points.
     *
     * @param currentLat
     * @param currentLng
     * @param placeLat
     * @param placeLng
     * @return The scale is a "meter"
     */
    public static int getDistanceAsMeter(double currentLat, double currentLng,
                                         double placeLat, double placeLng) {
        float[] result = new float[3];
        Location.distanceBetween(currentLat, currentLng, placeLat, placeLng, result);
        int dist = (int)result[0];
        return dist;
    }

    /**
     * UTF-8 Korean encoding
     *
     * @param orgUrl
     * @return
     */
    public static String encodeKoreanURL(String orgUrl){
        String tempUrl = null;
        try{
            tempUrl = URLEncoder.encode(orgUrl, "UTF-8");
        }catch(UnsupportedEncodingException e) {
            Utils.exceptionLog(e);
            return orgUrl;
        }
        tempUrl = tempUrl.replace("%3A", ":");
        tempUrl = tempUrl.replace("%2F", "/");

        return tempUrl;
    }

    /**
     * Getting the device width
     *
     * @param context
     * @return
     */
    public static int getDeviceWidth(Context context){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        return width;
    }

    /**
     * Getting the device height
     *
     * @param context
     * @return
     */
    public static int getDeviceHeight(Context context){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        return height;
    }

    /**
     * Check internet connection
     *
     * @param context
     * @return
     */
    public static boolean haveInternetConnection(Context context) {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null || !info.isConnected()) {
            return false;
        }
        if (info.isRoaming()) {
            // here is the roaming option you can change it if you want to
            // disable internet while roaming, just return false
            return true;
        }
        return true;
    }



    /**
     * Get bitmap from view.
     *
     * @param v
     * @return
     */
    public static Bitmap loadBitmapFromView(View v){
        try{
            Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
            v.draw(c);
            return b;
        }catch(Exception e){
            return null;
        }
    }

    /**
     * Get file from bitmap instance.
     *
     * @param bitmap
     * @return
     */
    public static File getFileFromBitmap(Bitmap bitmap){
        try{
            File external = Environment.getExternalStorageDirectory();
            File imageFile = new File(external, "temp_img.jpg");
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            return imageFile;
        }catch(Exception e){
            return null;
        }catch(OutOfMemoryError e){
            return null;
        }
    }

    /**
     * Get file from view.
     *
     * @param view
     * @return
     */
    public static File getFileFromView(View view){
        try{
            File external = Environment.getExternalStorageDirectory();
            File imageFile = new File(external, "temp_img.jpg");
            FileOutputStream fos = new FileOutputStream(imageFile);

            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = view.getDrawingCache();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            return imageFile;
        }catch(Exception e){
            return null;
        }catch(OutOfMemoryError e){
            return null;
        }
    }

}
