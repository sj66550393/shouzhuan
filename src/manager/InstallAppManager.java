package manager;

import util.AdbUtils;
import util.PicCompareUtils;

public class InstallAppManager {
	
	public boolean checkEnterAppDetail(){
		String path = AdbUtils.ScreenCapAndCut(95,361,200,50);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/install_firstinstall.png", path);
		System.out.printf("checkFinishExtraBonus sim=%f", sim);
		if (sim < 0.95) {
			return false;
		}
		float sim2 = PicCompareUtils.comparePicByFingerPrint("res/install_firstregister.png", path);
		System.out.printf("checkFinishExtraBonus sim=%f", sim);
		if (sim2 < 0.95) {
			return false;
		}
		return true;
	}

}
