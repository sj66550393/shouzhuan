package control;

import java.util.Timer;
import java.util.TimerTask;

import app.MeiRiZhuanDian;
import app.MiZhuan;
import common.ResultDict;
import util.AdbUtils;
import util.CutImageUtil;

public class Main {

	public static void main(String[] args) {
		 Timer t = new Timer();
		 t.schedule(new Task1(), 1000);
//		AdbUtils.ScreenCapAndCut(210, 50, 300, 100);
		// for(int i=0;i<5;i++){
		// String path = CutImageUtil.cutLocalImage("d:/1.png",
		// "d:/",34+144*i,526 , 76, 76);
		// System.out.println(path);
		// }
	}
}

class Task1 extends TimerTask {
	private MiZhuan mizhuan;

	public Task1() {
		mizhuan = new MiZhuan();
	}

	@Override
	public void run() {
		switch (mizhuan.start()) {
		case ResultDict.COMMAND_RESTART_APP:
			new Thread(new Runnable() {
				@Override
				public void run() {
					restartApp();
				}
			}).start();
			break;
		case ResultDict.COMMAND_SUCCESS:
			System.out.println("success");
			break;
		default:
			break;
		}
	}

	public void restartApp() {
		try {
			while (!AdbUtils.getCurrentPackage().contains("launcher")) {
				AdbUtils.killProcess(AdbUtils.getCurrentPackage());
			}
			Thread.sleep(3000);
			AdbUtils.startActivity("me.mizhuan/.ActCover");
			Thread.sleep(30000);
			run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
