package app;

import common.Contants;
import util.AdbUtils;
import util.Log;

public class MeiRiZhuanDian {
	private int NewsPressNum = 0;    //新闻阅读 每天80条
	private int SpeedTaskNum = 0;    //快速任务签到任务
	private int eighteenNewsNum  = 0;     //18条4篇
	private int goldenNewsNum  = 0;        //点金头条4篇
	private int loveTopNewsNum  = 0;        //我爱头条4篇
	private int topNewsNum  = 0;        //头条新闻4篇
	private int todayNewsNum  = 0;        //今日必看3篇
	private int militryNewsNum  = 0;        //今日必看5篇
	private int gift1Num = 0;                     //第一个礼包
	private int gift2Num = 0;                     //第二个礼包
	private int gift3Num = 0;                     //第三个礼包
	private int gift4Num = 0;                     //第四个礼包
	private int gift5Num = 0;                     //第五个礼包
	private int gift6Num = 0;                     //第六个礼包
	private int gift7Num = 0;                     //第七个礼包
	
	//快速任务模块
	private boolean mSpeedTaskSigninFinish;     //快速任务签到
	private boolean mSpeedTaskInstallFinish;    //快速任务安装 
	
	
	//新闻阅读模块
	private boolean mNewPressFinish; 
	public MeiRiZhuanDian() {
		NewsPressNum = 0;
		SpeedTaskNum = 0;
		eighteenNewsNum = 0;
		goldenNewsNum = 0;
		loveTopNewsNum = 0;
		topNewsNum = 0;
		todayNewsNum = 0;
		militryNewsNum = 0;
		gift1Num = 0;
		gift2Num = 0;
		gift3Num =0;
		gift4Num =0;
		gift5Num =0;
		gift6Num = 0;
		gift7Num = 0;
		mSpeedTaskSigninFinish = false;
		mSpeedTaskInstallFinish = false;

		mNewPressFinish = false;
	}
	//新闻阅读
	public void startNewsPress(){
		if(isNewsPressFinish())
			return;
		// to do
	}
	//快速任务
	public void startSpeedTask(){
		if(isSppedTaskFinish())
			return;
		startSpeedTaskInstall();
		startSpeedTaskSignin();
		AdbUtils.back();
	}
	//简单赚钱
	public void startSimpleTask(){
		if(isSimpleTaskFinish())
			return;
		startGiftAction();
		startNewsAction();
	}
	
	private boolean isNewsPressFinish(){
		return true;
	}
	
    private boolean isSppedTaskFinish(){
    	return mSpeedTaskSigninFinish && mSpeedTaskInstallFinish;
    }
    
    private boolean isSimpleTaskFinish(){
    	return (gift1Num == Contants.GIFT1_NUM) 
    			 && (gift2Num == Contants.GIFT2_NUM)  
    			 && (gift3Num == Contants.GIFT3_NUM)  
    			 && (gift4Num == Contants.GIFT4_NUM)  
    			 && (gift5Num == Contants.GIFT5_NUM)  
    			 && (gift6Num == Contants.GIFT6_NUM)  
    			 && (gift7Num == Contants.GIFT7_NUM)
    			 && mSpeedTaskInstallFinish
    			 && mSpeedTaskSigninFinish;
    }
	
	public void startNewsAction(){
//		startEighteenNews();
//		startGoldenNews();
//		startLoveTopNews();
//		startTopNews();
//		startTodayNews();
//		startMilitryNews();
	}
	//18头条
	public void startEighteenNews(){
		if(eighteenNewsNum == Contants.EIGHTEEN_NEWS_NUM)
			return ;
		AdbUtils.click(360, 300);	
		for(;eighteenNewsNum<Contants.EIGHTEEN_NEWS_NUM;eighteenNewsNum++){
			swipeAndClickNews();
		}
		AdbUtils.back();
	}
	//点金头条
	public void startGoldenNews(){		
		if(goldenNewsNum == Contants.GOLDEN_NEWS_NUM)
			return ;
		AdbUtils.click(500, 300);
		for(;goldenNewsNum<Contants.GOLDEN_NEWS_NUM;goldenNewsNum++){
			swipeAndClickNews();
		}
		AdbUtils.back();
	}
	//我爱头条
	public void startLoveTopNews(){	
		if(loveTopNewsNum == Contants.LOVETOP_NEWS_NUM)
			return ;
		AdbUtils.click(640, 300);
		for(;loveTopNewsNum<Contants.LOVETOP_NEWS_NUM;loveTopNewsNum++){
			swipeAndClickNews();
		}
		AdbUtils.back();
	}
	//头条新闻
	public void startTopNews(){
		Log.log.info("topNeesNum = " + topNewsNum);
		if(topNewsNum == Contants.TOP_NEWS_NUM)
			return ;
		AdbUtils.click(210, 300);
		for(;topNewsNum<Contants.TOP_NEWS_NUM;topNewsNum++){
			swipeAndClickNews();
		}
		AdbUtils.back();
	}
	//今日必看
	public void startTodayNews(){
		if(todayNewsNum == Contants.TODAY_NEWS_NUM)
			return ;
		AdbUtils.click(70, 450);
		for(;todayNewsNum<Contants.TODAY_NEWS_NUM;todayNewsNum++){
			swipeAndClickNews();
		}
		AdbUtils.back();
	}
	//米尔军事
	public void startMilitryNews(){
		if(militryNewsNum == Contants.MILITARY_NEWS_NUM)
			return ;
		AdbUtils.click(210, 450);
		for(;militryNewsNum<10;militryNewsNum++){
			swipeAndClickNews();
		}
		AdbUtils.back();
	}
	
	public void swipeAndClickNews(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AdbUtils.swipe(300 , 1100 , 300 , 500);
		AdbUtils.click(200, 500);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AdbUtils.back();
	}
	
	public void startGift1(){
		if(gift1Num == Contants.GIFT1_NUM)
			return;
		for(; gift1Num < Contants.GIFT1_NUM ; gift1Num++){
		AdbUtils.click(70, 740);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AdbUtils.back();
		}
	}
	
	public void startGift2(){
		if(gift2Num == Contants.GIFT2_NUM)
			return;
		for(; gift2Num < Contants.GIFT2_NUM ; gift2Num++){
		AdbUtils.click(215, 740);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AdbUtils.back();
		AdbUtils.back();
		}
	}
	
	public void startGift3(){
		if(gift3Num == Contants.GIFT3_NUM)
			return;
		for(; gift3Num < Contants.GIFT3_NUM ; gift3Num++){
		AdbUtils.click(360, 740);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AdbUtils.back();
		AdbUtils.back();
		}
	}
	public void startGift4(){
		if(gift4Num == Contants.GIFT4_NUM)
			return;
		for(; gift4Num< Contants.GIFT4_NUM ; gift4Num++){
		AdbUtils.click(510, 740);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AdbUtils.back();
		}
	}
	public void startGift5(){
		if(gift5Num == Contants.GIFT5_NUM)
			return;
		for(; gift5Num< Contants.GIFT5_NUM ; gift5Num++){
		AdbUtils.click(660, 740);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AdbUtils.back();
		}
	}
	public void startGift6(){
		if(gift6Num == Contants.GIFT6_NUM)
			return;
		for(; gift6Num < Contants.GIFT6_NUM ; gift6Num++){
		AdbUtils.click(70, 940);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AdbUtils.back();
		AdbUtils.back();
		}
	}
	
	public void startGift7(){
		if(gift7Num == Contants.GIFT7_NUM)
			return;
		for(; gift7Num < Contants.GIFT7_NUM ; gift7Num++){
		AdbUtils.click(215, 940);
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AdbUtils.back();
		}
	}
	
	public void startGiftAction(){
		startGift1();
		startGift2();
		startGift3();
		startGift4();
//		startGift5();
		startGift6();
		startGift7();
	}

    private  boolean isMatch(String str){
        return str.equals(AdbUtils.getTopActivity());
    }
    
    private  void startFromMainActivity(){
    	if(NewsPressNum < 80){
        	try {
        		AdbUtils.click(200, 600);
    		    switchActivity();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}else{
    		try {
    			AdbUtils.click(100, 450);
				switchActivity();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    private  void startFromNewsPressActivity(){
    	 try {
    		 AdbUtils.swipe(300, 800, 300, 500);
    		 AdbUtils.click(200, 500);
			switchActivity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private  void startFromNewsPressDetailActivity(){
    	try{
         while(true){
         AdbUtils.swipe(300, 800, 300, 500);
		 for (int i =0 ;i < 20 ; i++){
			 AdbUtils.swipe(300, 1000, 300, 500);
		 }
		 AdbUtils.back();
         }
//		 switchActivity();
    	}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public  void startFromLauncher(){
		 try {
			 AdbUtils.killProcess("com.adsmobile.mrzd");
			 AdbUtils.startActivity("com.adsmobile.mrzd/.MainActivity");
			Thread.sleep(20000);
			switchActivity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void start(){
    	
        try {
            //简单任务
            Thread.sleep(1000);
        	AdbUtils.click(80, 600);
			Thread.sleep(1000);
	    	startSimpleTask();
	    	AdbUtils.back();
        	//快速任务
	    	AdbUtils.click(80, 480);
	    	Thread.sleep(5000);
	    	startSpeedTask();
            AdbUtils.back();
	    	//新闻阅读
	    	Thread.sleep(1000);
	    	AdbUtils.click(220,600);
	    	Thread.sleep(1000);
	    	startNewsPress();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public  void startSpeedTaskSignin(){
    	try { 
    		AdbUtils.click(440, 195);
			for(int i=0; i < 30 ; i++){
			Thread.sleep(500);
			AdbUtils.click(140, 350);
			Thread.sleep(5*60*1000);
			AdbUtils.killProcess(AdbUtils.getCurrentPackage());
			Thread.sleep(1000);
			AdbUtils.swipe(300, 500, 300, 1000);
			Thread.sleep(2000);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			mSpeedTaskSigninFinish = true;
		}
    }
    
    public void startSpeedTaskInstall(){
    	mSpeedTaskInstallFinish = true;
    }
    
    
    private  void switchActivity(){
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String activity = AdbUtils.getTopActivity();
    	switch (activity) {
		case "com.adsmobile.mrzd/.MainActivity":
			startFromMainActivity();
			break;
		case "com.adsmobile.mrzd/.ui.activity.newnews.NewNewsPressActivity":
			startFromNewsPressActivity();
			break;
		case "com.adsmobile.mrzd/.ui.activity.newnews.NewsPressDetailActivity":
			startFromNewsPressDetailActivity();
			break;
		case "com.huawei.android.internal.app/.HwResolverActivity":
			AdbUtils.back();
			break;
		case "com.android.packageinstaller/.PackageInstallerActivity":
			AdbUtils.back();
			break;
		case "sogou.mobile.explorer/.BrowserActivity":
			AdbUtils.back();
			break;
		case "com.adsmobile.mrzd/.ui.activity.SpeedTaskDataListActivity":
			startSpeedTask();
			break;			
		default:
			startFromLauncher();
			break;
		}
    }
}
