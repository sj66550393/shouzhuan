package manager;

import common.ResultDict;
import util.AdbUtils;
import util.PicCompareUtils;

public class InstallAppManager {
	
	public boolean checkEnterAppDetail(){
		String path = AdbUtils.ScreenCapAndCut(95,361,200,50);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/install_firstinstall.png", path);
		System.out.printf("checkEnterAppDetail_install_firstinstall sim=%f", sim);
		if (sim < 0.95) {
			return false;
		}
		float sim2 = PicCompareUtils.comparePicByFingerPrint("res/install_firstregister.png", path);
		System.out.printf("checkEnterAppDetail_install_firstregister sim=%f", sim);
		if (sim2 < 0.95) {
			return false;
		}
		return true;
	}
	
	public boolean checkClickApplicationButton(){
		String path = AdbUtils.ScreenCapAndCut(0, 150, 240, 235);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/install_application.png", path);
		System.out.printf("checkClickApplicationButton sim=%f", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkTL00Install() {
		String path = AdbUtils.ScreenCapAndCut(497,1032, 193,96);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/install_CUN_TL00_before_install.png", path);
		System.out.printf("checkTL00Install sim=%f", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkTL00InstallComplete(){
		String path = AdbUtils.ScreenCapAndCut(30,1032, 330,96);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/install_CUN_TL_install_complete.png", path);
		System.out.printf("checkTL00InstallComplete sim=%f", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean checkBottomInstantInstall(){
		String path = AdbUtils.ScreenCapAndCut(300,1114,120,50);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/install_detail_bottom_install.png", path);
		System.out.printf("checkBottomInstantInstall sim=%f", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkBottomContinueExperience(){
		String path = AdbUtils.ScreenCapAndCut(300,1114,120,50);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/install_detail_bottom_experience.png", path);
		System.out.printf("checkBottomContinueExperience sim=%f", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkTL00DeletePackage() {
		String path = AdbUtils.ScreenCapAndCut(360,642,507,698);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/install_CUN_TL00_delete_package.png", path);
		System.out.printf("checkTL00DeletePackage sim=%f", sim);
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
		String path = AdbUtils.ScreenCapAndCut(287, 72, 433, 118);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/app_detail.png", path);
		System.out.printf("checkKillApp sim=%f", sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
}
