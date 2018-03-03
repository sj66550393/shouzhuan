package manager;

import util.AdbUtils;
import util.PicCompareUtils;

public class LooklookManager {

	public LooklookManager(){
		
	}
	public boolean checkClickBottomGame(){
		String path = AdbUtils.ScreenCapAndCut(429, 1106, 42, 42);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/game_selected.png", path);
		System.out.printf("checkClickBottomGame sim=%f\n", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	public boolean checkClickEntertainNews(){
		String path = AdbUtils.ScreenCapAndCut(210,50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/entertainment_title.png", path);
		System.out.printf("checkClickEntertainNews sim=%f\n", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkClickHotNews(){
		String path = AdbUtils.ScreenCapAndCut(210,50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/hotnews_title.png", path);
		System.out.printf("checkClickHotNews sim=%f\n", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkClickTurnturn(){
		String path = AdbUtils.ScreenCapAndCut(210,50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/turnturn_title.png", path);
		System.out.printf("checkClickTurnturn sim=%f\n", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkClick360News(){
		String path = AdbUtils.ScreenCapAndCut(210,50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/360News_title.png", path);
		System.out.printf("checkClick360News sim=%f\n", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkClickTuituiLe(){
		return true;
	}
	
	public boolean checkClickGoldNews(){
		String path = AdbUtils.ScreenCapAndCut(210,50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/goldnews_title.png", path);
		System.out.printf("checkClickGoldNews sim=%f\n", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkClickEighteenNews(){
		String path = AdbUtils.ScreenCapAndCut(210,50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/eighteen_title.png", path);
		System.out.printf("checkClickEighteenNews sim=%f\n", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkClickLoveNews() {
		String path = AdbUtils.ScreenCapAndCut(210, 50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/lovenews_title.png", path);
		System.out.printf("checkClickLoveNews sim=%f\n", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
}
