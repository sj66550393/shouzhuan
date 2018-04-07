package manager;

import util.AdbUtils;
import util.Log;
import util.PicCompareUtils;

public class SigninManager {
	public boolean checkClickBottomRecommand(){
		String path = AdbUtils.ScreenCapAndCut(69, 1106, 42, 42);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/recommand_selected.png", path);
		Log.log.info("checkClickBottomRecommand sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkEnterSigninDetail(){
		String path = AdbUtils.ScreenCapAndCut(320,80,80,40);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/cun_mizhuan_signin_title_signin.png", path);
		Log.log.info("checkEnterSigninDetail sim="+sim);
		if (sim > 0.90) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkHasSignin(){
		String path = AdbUtils.ScreenCapAndCut(64,274,52,36);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/cun_mizhuan_signin_word_signin.png", path);
		Log.log.info("checkHasSignin sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
}
