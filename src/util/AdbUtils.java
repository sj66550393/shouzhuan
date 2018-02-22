package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdbUtils {
	private static String adb =  "D:\\AndroidSDK\\platform-tools\\adb.exe shell ";
    public static String getTopActivity(){
    	String execResult = printf(adb + "dumpsys activity activities | grep mFocusedActivity");
    	System.out.println("exeResult = " + execResult);
    	String[] str = execResult.split(" ");
    	return  execResult.split(" ")[5];
    	
    }
    public static void changeAdb(String str){
    	adb  = "D:\\AndroidSDK\\platform-tools\\adb.exe " + "-s " + str + " shell";
    }
    
    public static String getAdb(String deviceId){
    	return "D:\\AndroidSDK\\platform-tools\\adb.exe " + "-s " + deviceId + " shell";
    }
    
    public static String getCurrentPackage(){
    	  return getTopActivity().split("/")[0];
    }
    
    public static void killProcess(String pkg){
    	 printf(adb + "am force-stop " + pkg);
    }
    
    public static void startActivity(String activity){
    	 printf(adb + "am start -n " + activity);
    }
    
    public static void exec(String cmd) throws Exception {
        Process ps = null;
        try {
            System.out.println(cmd);
            ps = Runtime.getRuntime().exec(cmd);
            int code = ps.waitFor();
            if (code != 0) throw new Exception("exec error(code=" + code + "): " + cmd);
        } finally {
            if (ps != null) ps.destroy();
        }
    }
    
    public static String printf( String cmd ){
    	BufferedReader reader = null;
    	String content = "";
    	try {
    		Process process = Runtime.getRuntime().exec(cmd);
    	    reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    	    StringBuffer output = new StringBuffer();
    	    int read;
    	    char[] buffer = new char[4096];
    	    while ((read = reader.read(buffer)) > 0) {
    	        output.append(buffer, 0, read);
    	    }
    	    reader.close();
    	    content = output.toString();
    	    return content;
    	} catch (IOException e) {
    	    e.printStackTrace();
    		return "";
    	}
    }
    
    public static void click(int x ,int y ){
    	try {
			exec(adb + "input tap " + x + " " + y);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public  static void back(){
    	try {
    		exec(adb + "input keyevent 4");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void swipe(int x1 , int y1 , int x2 , int y2){
    	try {
    		exec(adb + "input swipe " + x1 + " " + y1 + " " + x2 + " " + y2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
