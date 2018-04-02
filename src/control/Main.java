package control;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.MeiRiZhuanDian;
import app.MiZhuan;
import common.ResultDict;
import factory.LogFactory;
import util.AdbUtils;
import util.CutImageUtil;
import util.DateUtils;
import util.Log;
import util.LogUtils;

public class Main {

	public static void main(String[] args) {
		Timer t = new Timer();
		t.schedule(new Task1(), 1000);
//		 AdbUtils.ScreenCapAndCut(320,80,80,40);
		// for(int i=0;i<5;i++){
		// String path = CutImageUtil.cutLocalImage("d:/1.png",
		// "d:/",34+144*i,526 , 76, 76);
		// System.out.println(path);
		// }
		// AdbUtils.cleanApp();
		// MiZhuan mizhuan = new MiZhuan();
		// mizhuan.printDoingApp();
//		AdbUtils.isAwake();
	}
}

class Task1 extends TimerTask {
	private MiZhuan mizhuan;
	ExecutorService fixedThreadPool;

	public Task1() {
		mizhuan = new MiZhuan();
		fixedThreadPool = Executors.newFixedThreadPool(4);
	}

	@Override
	public void run() {
		switch (mizhuan.start()) {
		case ResultDict.COMMAND_RESTART_APP:
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					Log.log.info("restart app");
					if (AdbUtils.isAwake().equals("false")) {
						try {
							AdbUtils.clickPower();
							Thread.sleep(2000);
							AdbUtils.swipe(300, 900, 300, 300);
							Thread.sleep(2000);
							AdbUtils.swipe(100, 500, 600, 500);
							Thread.sleep(2000);
							AdbUtils.swipe(100, 500, 600, 500);
							Thread.sleep(2000);
							AdbUtils.swipe(300, 900, 300, 300);
						} catch (Exception e) {
						}
					}
					restartApp();
				}
			});

			break;
		case ResultDict.COMMAND_SUCCESS:
			Log.log.info("success");
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
