package control;

import java.util.Timer;
import java.util.TimerTask;

import app.MeiRiZhuanDian;
import app.MiZhuan;



public class Main {
	private static MeiRiZhuanDian meizhuan;
	private static MiZhuan mizhuan;
	public static void main(String[] args) {
		Timer t = new Timer();
    	t.schedule(new Task1(), 1000); 
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

