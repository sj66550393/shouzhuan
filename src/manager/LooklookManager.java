package manager;

import util.AdbUtils;
import util.PicCompareUtils;

public class LooklookManager {

	public LooklookManager(){
		
	}
	public boolean checkClickBottomGame(){
		String path = AdbUtils.ScreenCapAndCut(249, 1106, 42, 42);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/application_selected.png", path);
		System.out.printf("checkClickBottomGame sim=%f\n", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	public boolean checkClickHotNews(){
		return true;
	}
	public boolean checkClick360News(){
		return true;
	}
	public boolean checkClickTuituiLe(){
		return true;
	}
	public boolean checkClickGoldNews(){
		return true;
	}
	public boolean checkClickLoveNews(){
		return true;
	}
}
