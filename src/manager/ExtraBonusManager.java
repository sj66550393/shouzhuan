package manager;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import PicCompare.FingerPrint;
import util.AdbUtils;
import util.PicCompareUtils;

public class ExtraBonusManager {

	public ExtraBonusManager(){
		
	}
	public boolean checkClickBottomApplication(){
		String path = AdbUtils.ScreenCapAndCut(249,1106,42,42);
		try {
			FingerPrint fp1 = new FingerPrint(ImageIO.read(new File("res/application_selected.png")));
	        FingerPrint fp2 =new FingerPrint(ImageIO.read(new File(path)));
	        float sim  = fp1.compare(fp2);
	        System.out.printf("sim=%f",sim);
	        if(sim > 0.95){
	        	return true;
	        }else{
	        	return false;
	        }
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
