package app;

import common.Contants;
import common.ResultDict;
import manager.ExtraBonusManager;
import manager.InstallAppManager;
import manager.LooklookManager;
import util.AdbUtils;

public class MiZhuan {

	private int redPackageNum = 0; // 拆红包 5篇
	private int todayMustNum = 0; // 今日必看 6篇

	private int entertainmentNews = 0; // 娱乐爆料 5篇
	private int ThreeSixZeroNewsNum = 0; // 360新闻 每天6篇
	private int HotNewsNum = 0; // 热点新闻 每天5条
	private int turnturnNum = 0; // 翻翻乐 9篇
	private int tuituiNum = 0; // 推推乐 5篇
	private int goldNewsNum = 0; // 点金头条 7篇
	private int eighteenNum = 0; // 18头条
	private int loveNewsNum = 0; // 我爱头条 9篇
	private int DEFAULT_EXTRABONUS_TIME = 1;
	private int DEFAULT_INSTALL_COUNT  =10;
	private boolean isExtraBonusCompleted = false;
	private boolean isLooklookCompleted = false;
	private boolean isInstallCompleted = false;

	ExtraBonusManager extraBonusManager;
	LooklookManager looklookManager;
	InstallAppManager installAppManager;

	public MiZhuan() {
		extraBonusManager = new ExtraBonusManager();
		looklookManager = new LooklookManager();
		installAppManager = new InstallAppManager();
	}

	public int start() {
		if (!AdbUtils.getTopActivity().equals("me.mizhuan/.TabFragmentActivity")) {
			return ResultDict.COMMAND_RESTART_APP;
		}
		int result = ResultDict.COMMAND_SUCCESS;
		if (!isExtraBonusCompleted) {
			result = startSigninAppTask();
			if (ResultDict.COMMAND_SUCCESS != result)
				return result;
		}
		if (!isLooklookCompleted) {
			result = startLooklookTaskFromBottomGame();
			if (ResultDict.COMMAND_SUCCESS != result)
				return result;
		}
		return ResultDict.COMMAND_SUCCESS;
	}

	// 安装任务
	public int startInstallAppTask() {
		try {
			// 点击应用赚
			AdbUtils.click(270, 1140);
			Thread.sleep(1000);
			if (!extraBonusManager.checkClickBottomApplication()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			// 点击应用
			AdbUtils.click(120, 192);
			if (!installAppManager.checkClickApplicationButton()) {
				return ResultDict.COMMAND_RESTART_APP;
			}

			int installCount = 0;
			// 点击进入详情页
			for (int i = 0; i < 100; i++) {
				if(installCount == DEFAULT_INSTALL_COUNT){
					isInstallCompleted = true;
					break;
				}	
				// 点击进入详情页
				AdbUtils.click(360, 318);
				if (!installAppManager.checkEnterAppDetail()) {
					AdbUtils.back();
					continue;
				}
				if (!installAppManager.checkBottomContinueExperience()) {
					// 点击下载安装或者打开
					AdbUtils.click(360, 1139);
					Thread.sleep(20 * 1000);
					if (!installAppManager.checkTL00Install()) {
						return ResultDict.COMMAND_RESTART_APP;
					}
					// 点击安装
					AdbUtils.click(594, 1080);
					Thread.sleep(20 * 1000);
					// 查看是否完成
					if (!installAppManager.checkTL00InstallComplete()) {
						return ResultDict.COMMAND_RESTART_APP;
					}
					// 点击完成
					AdbUtils.click(195, 1080);
					if (!installAppManager.checkTL00DeletePackage()) {
						return ResultDict.COMMAND_RESTART_APP;
					}
					// 点击删除
					AdbUtils.click(507, 698);
					Thread.sleep(1000);
					installCount++;
				}
				if(!installAppManager.checkBottomContinueExperience()){
					return ResultDict.COMMAND_RESTART_APP;
				}
				//点击继续体验
				AdbUtils.click(360, 1139);
				Thread.sleep(3000);
				if(!installAppManager.checkEnterApp()){
					return ResultDict.COMMAND_RESTART_APP;
				}
				Thread.sleep(5*60*1000);
				String name = AdbUtils.getCurrentPackage();
				AdbUtils.killProcess(AdbUtils.getCurrentPackage());
				Thread.sleep(5000);
				if(!installAppManager.checkKillApp()){
					return ResultDict.COMMAND_RESTART_APP;
				}
				AdbUtils.back();
				Thread.sleep(1000);
				AdbUtils.swipe(300,800,300,665);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// 额外奖励
	public int startSigninAppTask() {
		try {
			// 点击应用赚
			AdbUtils.click(270, 1140); 
			Thread.sleep(1000);
			if (!extraBonusManager.checkClickBottomApplication()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			// 额外奖励
			AdbUtils.click(610, 200); // CAN_CUN
			// AdbUtils.click(610, 180); //oppo A37m
			Thread.sleep(8000);
			if (!extraBonusManager.checkClickExtraBonus()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			for (int i = 0; i < 200; i++) {
				Thread.sleep(500);
				AdbUtils.swipe(300, 500, 300, 1000);
				Thread.sleep(5000);
				AdbUtils.click(620, 320); 
				Thread.sleep(3000);
				switch (extraBonusManager.checkEnterApp()) {
				case ResultDict.COMMAND_BACK:
//					if(extraBonusManager.checkFinishExtraBonus()){
//						isExtraBonusCompleted = true;
//						return ResultDict.COMMAND_SUCCESS;
//					}
					AdbUtils.back();
					Thread.sleep(5000);
					continue;
				case ResultDict.COMMAND_RESTART_APP:
					if(extraBonusManager.checkFinishExtraBonus()){
						isExtraBonusCompleted = true;
						return ResultDict.COMMAND_SUCCESS;
					}
					return ResultDict.COMMAND_RESTART_APP;
				case ResultDict.COMMAND_SUCCESS:
					break;
				default:
					break;
				}
				Thread.sleep(DEFAULT_EXTRABONUS_TIME * 70 * 1000);
				String name = AdbUtils.getCurrentPackage();
				AdbUtils.killProcess(AdbUtils.getCurrentPackage());
				Thread.sleep(5000);
				if (!extraBonusManager.checkKillApp(name)) {
					return ResultDict.COMMAND_RESTART_APP;
				}
				Thread.sleep(5000);
			}
			isExtraBonusCompleted = true;
			return ResultDict.COMMAND_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDict.COMMAND_RESTART_APP;
		}
	}

	// 从游戏赚中进入看看赚
	public int startLooklookTaskFromBottomGame() {
		try {
			 //点击游戏赚
			AdbUtils.click(471, 1140); 
			Thread.sleep(1000);
			if (!looklookManager.checkClickBottomGame()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			for (int i = 0; i < 5; i++) {
				AdbUtils.swipe(500, 700, 500, 300);
				System.out.println("swipe");
				Thread.sleep(1000);
			}
			if (!clickEntertainmentNews()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			if (!clickThreeSixZeroNews()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			if (!clickTurnturn()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			
			clickTuitui();
			if (!clickGoldNews()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			if (!clickEighteenNews()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			if (!clickLoveNews()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			isLooklookCompleted = true;
			return ResultDict.COMMAND_SUCCESS;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return ResultDict.COMMAND_RESTART_APP;
		}
	}

	// 点广告
	public void clickAdvs() {
		try {
			AdbUtils.click(70, 880);
			Thread.sleep(2000);
			AdbUtils.click(360, 820);
			Thread.sleep(2 * 60 * 1000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 热点新闻
	public boolean clickHotNews() {
		try {
			AdbUtils.click(216,510);
			Thread.sleep(2000);
			if (!looklookManager.checkClickHotNews()) {
				return false;
			}
			for (; HotNewsNum < Contants.HOT_NEWS_NUM + 3; HotNewsNum++) {
				Thread.sleep(5000);
				AdbUtils.swipe(300, 1100, 300, 500);
				AdbUtils.click(300, 600);
				Thread.sleep(10000);
				if(!looklookManager.checkEnterHotNews()){
					System.out.println("click blank");
					AdbUtils.click(300, 800);
				}
				Thread.sleep(10000);
				AdbUtils.back();
				Thread.sleep(3000);
				if (!looklookManager.checkClickHotNews()) {
					return false;
				}
			}
			Thread.sleep(2000);
			AdbUtils.back();
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 360新闻
	public boolean clickThreeSixZeroNews() {
		try {
			AdbUtils.click(72, 510);
			Thread.sleep(10000);
			if (!looklookManager.checkClick360News()) {
				return false;
			}
			for (; ThreeSixZeroNewsNum < Contants.THREE_SIX_ZERO_NEWS_NUM + 3; ThreeSixZeroNewsNum++) {
				swipeAndClickNews();
				if (!looklookManager.checkClick360News()) {
					return false;
				}
			}
			Thread.sleep(2000);
			AdbUtils.back();
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 推推乐
	public void clickTuitui() {
		try {
			Thread.sleep(10000);
			for (; tuituiNum < Contants.TUITUI_NUM; tuituiNum++) {
				AdbUtils.click(504,510);
				Thread.sleep(10 * 1000);
				AdbUtils.back();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 翻翻乐
	public boolean clickTurnturn() {
		try {
			for (; tuituiNum < Contants.TURNTURN_NUM; tuituiNum++) {
				AdbUtils.click(360,510);
				Thread.sleep(10 * 1000);
				if (!looklookManager.checkClickTurnturn()) {
					return false;
				}
				AdbUtils.back();
				AdbUtils.back();
			}
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 拆红包
	public void clickRedPackage() {
		// oppo配置
		try {

			for (; redPackageNum < Contants.RED_PACKAGES_NUM; redPackageNum++) {
				AdbUtils.click(650, 880);
				Thread.sleep(2 * 60 * 1000);
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

	// 点金头条
	public boolean clickGoldNews() {
		try {
			AdbUtils.click(216,695);
			Thread.sleep(2000);
			if (!looklookManager.checkClickGoldNews()) {
				return false;
			}
			for (; goldNewsNum < Contants.GOLD_NEWS_NUM ; goldNewsNum++) {
				Thread.sleep(5000);
				for(int i=0;i<goldNewsNum;i++){
				AdbUtils.swipe(300, 1100, 300, 500);
				}
				AdbUtils.click(300, 600);
				Thread.sleep(10000);
				if(!looklookManager.checkEnterGoldNews()){
					System.out.println("click blank");
					AdbUtils.click(300, 800);
				}
				Thread.sleep(10000);
				for (int i = 0; i < 10; i++) {
					AdbUtils.swipe(300, 1100, 300, 500);
					Thread.sleep(1000);
				}
				AdbUtils.back();
				Thread.sleep(3000);
				if (!looklookManager.checkClickGoldNews()) {
					return false;
				}
			}
			Thread.sleep(2000);
			AdbUtils.back();
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 18头条
	public boolean clickEighteenNews() {
		try {
			AdbUtils.click(360,695);
			Thread.sleep(2000);
			if (!looklookManager.checkClickEighteenNews()) {
				return false;
			}
			for (; eighteenNum < Contants.EIGHTEEN_NEWS_NUM; eighteenNum++) {
				Thread.sleep(5000);
				for(int i=0;i<eighteenNum;i++){
				AdbUtils.swipe(300, 1100, 300, 500-20*i);
				}
				AdbUtils.click(300, 600);
				Thread.sleep(10000);
				for (int i = 0; i < 10; i++) {
					AdbUtils.swipe(300, 1100, 300, 500);
					Thread.sleep(1000);
				}
				AdbUtils.back();
				Thread.sleep(3000);
				if (!looklookManager.checkClickEighteenNews()) {
					return false;
				}
			}
			Thread.sleep(2000);
			AdbUtils.back();
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 我爱头条
	public boolean clickLoveNews() {
		try {
			AdbUtils.click(504,695);
			Thread.sleep(2000);
			if (!looklookManager.checkClickLoveNews()) {
				return false;
			}
			for (; loveNewsNum < Contants.LOVE_NEWS_NUM + 4; loveNewsNum++) {
				swipeAndClickNews2();
				if (!looklookManager.checkClickLoveNews()) {
					return false;
				}
			}
			Thread.sleep(2000);
			AdbUtils.back();
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 今日必看
	public void clickTodayMustNews() {
		// TODO
	}

	// 娱乐爆料
	public boolean clickEntertainmentNews() {
		try {
			AdbUtils.click(504, 325);
			Thread.sleep(2000);
			if (!looklookManager.checkClickEntertainNews()) {
				return false;
			}
			for (; entertainmentNews < Contants.ENTERTAINMENT_NEWS + 4; entertainmentNews++) {
				Thread.sleep(5000);
				AdbUtils.swipe(300, 1100, 300, 500);
				AdbUtils.click(300, 600);
				Thread.sleep(10000);
				if (!looklookManager.checkEnterEntertainNews()) {
					System.out.println("click blank");
					AdbUtils.click(300, 800);
					Thread.sleep(10000);
				}
				for (int i = 0; i < 10; i++) {
					AdbUtils.swipe(300, 1100, 300, 500);
					Thread.sleep(1000);
				}
				AdbUtils.back();
				Thread.sleep(3000);
				while(looklookManager.checkEnterEntertainNews()){
					AdbUtils.back();
					Thread.sleep(8000);
				}
				if (!looklookManager.checkClickEntertainNews()) {
					return false;
				}
			}
			Thread.sleep(2000);
			AdbUtils.back();
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 滑动点击新闻 新闻界面停留20S
	public void swipeAndClickNews() {
		try {
			Thread.sleep(5000);
			AdbUtils.swipe(300, 1100, 300, 500);
			AdbUtils.click(300, 600);
			Thread.sleep(20000);
			AdbUtils.back();
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// 滑动点击新闻 新闻界面滑动
	public void swipeAndClickNews2() {
		try {
			Thread.sleep(5000);
			AdbUtils.swipe(300, 1100, 300, 500);
			AdbUtils.click(300, 600);
			Thread.sleep(10000);
			for (int i = 0; i < 10; i++) {
				AdbUtils.swipe(300, 1100, 300, 500);
				Thread.sleep(1000);
			}
			AdbUtils.back();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isCAN_CUN() {
		return true;
	}
}
