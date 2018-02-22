package app;

import common.Contants;
import util.AdbUtils;

public class MiZhuan {
	private int HotNewsNum = 0;    //热点新闻 每天9条
	private int ThreeSixZeroNewsNum = 0;    //360新闻 每天9篇
	private int tuituiNum  = 0;     //推推乐 5篇
	private int redPackageNum  = 0;        //拆红包 5篇
	private int goldNewsNum  = 0;        //点金头条 7篇
	private int loveNewsNum  = 0;        //我爱头条 6篇

	
	public MiZhuan(){
		
	}
	
	public void start() {
		try {
		    if(!AdbUtils.getTopActivity().equals("me.mizhuan/.TabFragmentActivity")){
		    	AdbUtils.killProcess("me.mizhuan");
		    	AdbUtils.startActivity("me.mizhuan/.ActCover");
		    	Thread.sleep(50*1000);
		    }
			// 点击应用赚
			AdbUtils.click(270, 1140);   //CAN_CUN
//			AdbUtils.click(270, 1233);  //oppo A37m
			Thread.sleep(1000);
			// 额外奖励
			AdbUtils.click(610, 200);     //CAN_CUN
//			AdbUtils.click(610, 180);     //oppo A37m
			startSigninAppTask();
//			点击推荐
		   AdbUtils.click(90, 1230);  //oppo A37m
		   AdbUtils.click(97, 1144);  //CAN_CUN
		   //点击看看赚
		   AdbUtils.click(450, 224);  // oppo CAN_CUN一样
		   Thread.sleep(2000);
		   startLooklookTask();
		  
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//安装任务
	public void startInstallAppTask(){
		
	}
	//额外奖励
	public void startSigninAppTask() {
		try {
			for (int i = 0; i < 50; i++) {
				Thread.sleep(500);
				AdbUtils.swipe(300, 500, 300, 1000);
				Thread.sleep(5000);
//				AdbUtils.click(605, 340);   //CAN_CUN
				AdbUtils.click(620, 320);   //oppo A37m
				Thread.sleep(3000);
				if(AdbUtils.getTopActivity().equals("me.mizhuan/.TabFragmentActivity")){
					AdbUtils.back();
					continue;
				}
				Thread.sleep(1 * 70 * 1000);
				AdbUtils.killProcess(AdbUtils.getCurrentPackage());
				Thread.sleep(1000);
				AdbUtils.swipe(300, 500, 300, 1000);
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startLooklookTask(){
	    clickHotNews();
		clickThreeSixZeroNews();
		clickTuitui();
		clickRedPackage();
		if(isCAN_CUN()){
		AdbUtils.swipe(300, 1000, 300, 500);
		}
		clickGoldNews();
		clickLoveNews();
	}
	
	//点广告
	public void clickAdvs(){
		//oppo配置
		try {
			AdbUtils.click(70, 880);
			Thread.sleep(2000);
			AdbUtils.click(360, 820);
			Thread.sleep(2*60*1000);
			 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//热点新闻
	public void clickHotNews(){
	   //oppo配置
		try {
//			AdbUtils.click(225, 880);  //oppo A37m
			AdbUtils.click(215, 900);
			Thread.sleep(2000);
			 for(;HotNewsNum < Contants.HOT_NEWS_NUM;HotNewsNum++){
				 swipeAndClickNews();
			 }
			 Thread.sleep(1000);
			 AdbUtils.back();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//360新闻
	public void clickThreeSixZeroNews(){
		   //oppo配置
			try {
//				AdbUtils.click(360, 880); //oppo 
				AdbUtils.click(360, 900); //CAN_CUN
				Thread.sleep(2000);
				 for(;ThreeSixZeroNewsNum < Contants.THREE_SIX_ZERO_NEWS_NUM;ThreeSixZeroNewsNum++){
					 swipeAndClickNews();
				 }
				 Thread.sleep(1000);
				 AdbUtils.back();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	//推推乐
	public void clickTuitui(){
		   //oppo配置
			try {
				for(;tuituiNum < Contants.TUITUI_ADVS_NUM;tuituiNum++){
					AdbUtils.click(506, 880);			
					Thread.sleep(2*60*1000);
					AdbUtils.back();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 
	//拆红包
	public void clickRedPackage(){
		   //oppo配置
			try {
			
				for(;redPackageNum < Contants.RED_PACKAGES_NUM;redPackageNum++){
					AdbUtils.click(650, 880);
					Thread.sleep(2*60*1000);
					AdbUtils.back();
					AdbUtils.back();
					Thread.sleep(1000);
					AdbUtils.click(180, 90);
				}		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	//点金头条
	public void clickGoldNews(){
		   //oppo配置
			try {
//				AdbUtils.click(70, 1100); //oppo
				AdbUtils.click(70, 920); //CAN_CUN
				Thread.sleep(2000);
				 for(;goldNewsNum < Contants.GOLD_NEWS_NUM;goldNewsNum++){
					 swipeAndClickNews();
				 }
				 Thread.sleep(1000);
				 AdbUtils.back();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	//我爱头条
	public void clickLoveNews(){
		   //oppo配置
			try {
//				AdbUtils.click(215, 1100); //oppo
				AdbUtils.click(215, 9200); //CAN_CUN
				Thread.sleep(2000);
				 for(;goldNewsNum < Contants.GOLD_NEWS_NUM + 5;goldNewsNum++){
					 swipeAndClickNews();
				 }
				 Thread.sleep(1000);
				 AdbUtils.back();
			} catch (InterruptedException e) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public void swipeAndClickNews(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AdbUtils.swipe(300 , 1100 , 300 , 500);
		AdbUtils.click(300, 600);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AdbUtils.back();
	}
	
	public boolean isCAN_CUN(){
		return true;
	}
}
