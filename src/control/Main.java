package control;

import java.util.Timer;
import java.util.TimerTask;

import app.MeiRiZhuanDian;
import app.MiZhuan;
import util.AdbUtils;
import util.CutImageUtil;



public class Main {
	private static MeiRiZhuanDian meizhuan;
	private static MiZhuan mizhuan;
	public static void main(String[] args) {
//		Timer t = new Timer();
//    	t.schedule(new Task1(), 1000); 
		AdbUtils.ScreenCapAndCut(287, 72, 433, 118);
	}
}

class Task1 extends TimerTask{
	@Override
	public void run() {
		System.out.println("Task1,threadID = " + Thread.currentThread().getId());
		MiZhuan mizhuan = new MiZhuan();
    	mizhuan.start();
	}
}

