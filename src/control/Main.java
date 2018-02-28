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
	}
}

class Task1 extends TimerTask{
	private MiZhuan mizhuan;
	public Task1() {
		mizhuan = new MiZhuan();
	}
	@Override
	public void run() {
    	switch(mizhuan.start()) {
    	case ResultDict.COMMAND_RESTART_APP:
    	    
    		default:
    		break;	
    	}
	}
	
	public void restartApp(){
		try {
			while(!AdbUtils.getCurrentPackage().contains("launcher")){
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

