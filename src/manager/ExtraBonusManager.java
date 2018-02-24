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

	public boolean checkClickBottomApplication() {
		String path = AdbUtils.ScreenCapAndCut(249, 1106, 42, 42);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/application_selected.png", path);
		System.out.printf("sim=%f", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkClickExtraBonus(){
		String path = AdbUtils.ScreenCapAndCut(480, 150, 240, 85);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/application_reward_selected.png", path);
		System.out.printf("sim=%f", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
}
