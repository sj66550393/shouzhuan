package manager;

import java.io.IOException;

import util.AdbUtils;
import util.CutImageUtil;

public class ExtraBonusManager {
	public boolean checkClickBottomApplicationButton() {
		Runtime process = Runtime.getRuntime();
		 try {
			 String name = AdbUtils.screenCapAndCut(69, 1106, 42, 42);
			 System.out.println(name);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean checkClickApplicationBonusButton() {
		return true;
	}

	public boolean checkRefreshList() {
		return true;
	}
	
	public boolean checkClickStartApp() {
		return true;
	}
	public boolean checkEnterApp() {
		return true;
	}
	public boolean checkKillApp() {
		return true;
	}
}
