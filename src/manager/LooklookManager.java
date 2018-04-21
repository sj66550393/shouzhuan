package manager;

import app.MiZhuan;
import util.AdbUtils;
import util.ClassPathUtils;
import util.Log;
import util.PicCompareUtils;

public class LooklookManager {
	
	private String rootPath;

	public LooklookManager(){
		rootPath = ClassPathUtils.getAppPath(MiZhuan.class) + "/../";
	}
	public boolean checkClickBottomGame(){
		String path = AdbUtils.ScreenCapAndCut(429, 1106, 42, 42);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/game_selected.png", path);
		Log.log.info("checkClickBottomGame sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	public boolean checkClickEntertainNews(){
		String path = AdbUtils.ScreenCapAndCut(210,50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/entertainment_title.png", path);
		Log.log.info("checkClickEntertainNews sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkEnterEntertainNews(){
		String path = AdbUtils.ScreenCapAndCut(124,52 ,128, 96);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/entertainment_enter.png", path);
		Log.log.info("checkEnterEntertainNews sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkEnterHotNews(){
		String path = AdbUtils.ScreenCapAndCut(124,52 ,128, 96);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/hotnews_enter.png", path);
		Log.log.info("checkEnterHotNews sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkEnterGoldNews(){
		String path = AdbUtils.ScreenCapAndCut(124,52 ,128, 96);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/goldnews_enter.png", path);
		Log.log.info("checkEnterGoldNews sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkEnterEighteenNews(){
		String path = AdbUtils.ScreenCapAndCut(124,52 ,128, 96);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/eighteennews_enter.png", path);
		Log.log.info("checkEnterEighteenNews sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkClickHotNews(){
		String path = AdbUtils.ScreenCapAndCut(210,50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/hotnews_title.png", path);
		Log.log.info("checkClickHotNews sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkClickTurnturn(){
		String path = AdbUtils.ScreenCapAndCut(210,50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/turnturn_title.png", path);
		Log.log.info("checkClickTurnturn sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkClick360News(){
		String path = AdbUtils.ScreenCapAndCut(210,50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/360News_title.png", path);
		Log.log.info("checkClick360News sim="+sim);
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
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/goldnews_title.png", path);
		Log.log.info("checkClickGoldNews sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkClickEighteenNews(){
		String path = AdbUtils.ScreenCapAndCut(210,50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/eighteen_title.png", path);
		Log.log.info("checkClickEighteenNews sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkClickLoveNews() {
		String path = AdbUtils.ScreenCapAndCut(210, 50, 300, 100);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/lovenews_title.png", path);
		Log.log.info("checkClickLoveNews sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
}
