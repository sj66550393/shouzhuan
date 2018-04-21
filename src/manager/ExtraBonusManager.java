package manager;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import PicCompare.FingerPrint;
import app.MiZhuan;
import common.ResultDict;
import util.AdbUtils;
import util.ClassPathUtils;
import util.Log;
import util.PicCompareUtils;

public class ExtraBonusManager {
	
	private String rootPath;

	public ExtraBonusManager(){
		rootPath = ClassPathUtils.getAppPath(MiZhuan.class) + "/../";
	}

	public boolean checkClickBottomApplication() {
		String path = AdbUtils.ScreenCapAndCut(249, 1106, 42, 42);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/application_selected.png", path);
		Log.log.info("checkClickBottomApplication sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkClickExtraBonus(){
		String path = AdbUtils.ScreenCapAndCut(480, 150, 240, 85);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/application_reward_selected.png", path);
		Log.log.info("checkClickExtraBonus sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	public int checkEnterApp(){
		String path = AdbUtils.ScreenCapAndCut(287, 72, 146, 46);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/app_detail.png", path);
		Log.log.info("checkEnterApp sim="+sim);
		if (sim > 0.95) {
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
		Log.log.info("checkKillApp");
		if(!AdbUtils.getTopActivity().equals("me.mizhuan/.TabFragmentActivity")){
			return false;
		}
		return true;
	}
	
	public boolean checkFinishExtraBonus(){
		String path = AdbUtils.ScreenCapAndCut(300,1114, 120,50);
		float sim = PicCompareUtils.comparePicByFingerPrint(rootPath + "res/application_detail_use_gray.png", path);
		Log.log.info("checkFinishExtraBonus sim="+sim);
		if (sim > 0.95) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isHuaweiUpdateActivity(){
		return "com.huawei.android.hwouc/.ui.activities.firmware.FirmwareNewVersionDetailsActivity".equals(AdbUtils.getTopActivity());
	}
}
