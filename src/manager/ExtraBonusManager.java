package manager;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import PicCompare.FingerPrint;
import common.ResultDict;
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
	
	public int checkEnterApp(){
		String path = AdbUtils.ScreenCapAndCut(287, 72, 433, 118);
		float sim = PicCompareUtils.comparePicByFingerPrint("res/application_reward_selected.png", path);
		System.out.printf("sim=%f", sim);
		if (sim < 0.95) {
			return ResultDict.COMMAND_BACK;
		}
		if(isHuaweiUpdateActivity()){
			return ResultDict.COMMAND_BACK;
		}
		if(AdbUtils.getTopActivity().equals("me.mizhuan/.TabFragmentActivity")){
			return ResultDict.COMMAND_RESTART_APP;
		}
		return ResultDict.COMMAND_SUCCESS;
	}
	
	public boolean checkKillApp(String name){
		if(AdbUtils.getTopActivity().equals("me.mizhuan/.TabFragmentActivity")){
			return false;
		}
		return true;
	}
	
	private boolean isHuaweiUpdateActivity(){
		return "com.huawei.android.hwouc/.ui.activities.firmware.FirmwareNewVersionDetailsActivity".equals(AdbUtils.getTopActivity());
	}
	
	
}
