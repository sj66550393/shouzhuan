package manager;

import app.MiZhuan;
import common.ResultDict;
import util.AdbUtils;
import util.ClassPathUtils;
import util.Log;
import util.PicCompareUtils;

public class InstallAppManager {
	
	private String rootPath;
	public InstallAppManager(){
		rootPath = ClassPathUtils.getAppPath(MiZhuan.class) + "/../";
	}
	
	public boolean checkEnterAppDetail(){
		String path = AdbUtils.ScreenCapAndCut(95,361,200,50);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/install_firstinstall.png", path);
		Log.log.info("checkEnterAppDetail_install_firstinstall sim="+sim);
		if (sim < 0.95) {
			return false;
		}
		float sim2 = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/install_firstregister.png", path);
		Log.log.info("checkEnterAppDetail_install_firstregister sim="+sim);
		if (sim2 < 0.95) {
			return false;
		}
		return true;
	}
	
	public boolean checkClickApplicationButton(){
		String path = AdbUtils.ScreenCapAndCut(0, 150, 240, 85);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/install_application.png", path);
		Log.log.info("checkClickApplicationButton sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkTL00Install() {
		String path = AdbUtils.ScreenCapAndCut(497,1032, 193,96);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/install_CUN_TL00_before_install.png", path);
		Log.log.info("checkTL00Install sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkTL00Install2() {
		String path = AdbUtils.ScreenCapAndCut(360,1032, 330,96);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/install_CUN_TL00_before_install2.png", path);
		Log.log.info("checkTL00Install sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkTL00InstallComplete(){
		String path = AdbUtils.ScreenCapAndCut(30,1032, 330,96);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/install_CUN_TL_install_complete.png", path);
		Log.log.info("checkTL00InstallComplete sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean checkBottomInstantInstall(){
		String path = AdbUtils.ScreenCapAndCut(300,1114,120,50);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/install_detail_bottom_install.png", path);
		Log.log.info("checkBottomInstantInstall sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkBottomContinueExperience(){
		String path = AdbUtils.ScreenCapAndCut(300,1114,120,50);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/install_detail_bottom_experience.png", path);
		Log.log.info("checkBottomContinueExperience sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkTL00DeletePackage() {
		String path = AdbUtils.ScreenCapAndCut(360,642,507,698);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/install_CUN_TL00_delete_package.png", path);
		Log.log.info("checkTL00DeletePackage sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkEnterApp(){
		if(AdbUtils.getTopActivity().equals("me.mizhuan/.TabFragmentActivity")){
			return false;
		}
		return true;
	}
	
	public boolean checkKillApp() {
		String path = AdbUtils.ScreenCapAndCut(287, 72, 146, 46);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/app_detail.png", path);
		Log.log.info("checkKillApp sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
}
