package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileUtils {
	
	public static  void String2File(String filePath , String context){
        try {
            File file = new File(filePath);
            if(file.exists()){
            	file.createNewFile();
            }
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(context);// ���ļ���д���ַ���
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
