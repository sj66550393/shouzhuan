package app;

import common.Contants;
import common.ResultDict;
import control.Main;
import manager.ExtraBonusManager;
import manager.InstallAppManager;
import manager.LooklookManager;
import util.AdbUtils;
import util.DateUtils;
import util.FileUtils;
import util.Log;

public class MiZhuan {

	private int redPackageNum = 0; // ���� 5ƪ
	private int todayMustNum = 0; // ���ձؿ� 6ƪ
	private int entertainmentNews = 0; // ���ֱ��� 5ƪ
	private int ThreeSixZeroNewsNum = 0; // 360���� ÿ��6ƪ
	private int HotNewsNum = 0; // �ȵ����� ÿ��5��
	private int turnturnNum = 0; // ������ 9ƪ
	private int tuituiNum = 0; // ������ 5ƪ
	private int goldNewsNum = 0; // ���ͷ�� 7ƪ
	private int eighteenNum = 0; // 18ͷ��
	private int loveNewsNum = 0; // �Ұ�ͷ�� 9ƪ
	private int DEFAULT_EXTRABONUS_TIME = 1;
	private int INSTALL_EXPERIWNCE_TIME = 5;
	private int DEFAULT_INSTALL_COUNT  =3;
	private boolean isExtraBonusCompleted = false;
	private boolean isLooklookCompleted = false;
	private boolean isInstallCompleted = true;

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
		if(!isInstallCompleted){
			result = startInstallAppTask();
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

	// ��װ����
	public int startInstallAppTask() {
		try {
			// ���Ӧ��׬
			AdbUtils.click(270, 1140);
			Thread.sleep(1000);
			if (!extraBonusManager.checkClickBottomApplication()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			// ���Ӧ��
			AdbUtils.click(120, 192);
			if (!installAppManager.checkClickApplicationButton()) {
				return ResultDict.COMMAND_RESTART_APP;
			}

			int installCount = 0;
			// �����������ҳ
			for (int i = 0; i < 100; i++) {
				if(installCount == DEFAULT_INSTALL_COUNT){
					isInstallCompleted = true;
					break;
				}	
				// �����������ҳ
				AdbUtils.click(360, 318);
				Thread.sleep(2000);
				if (!installAppManager.checkEnterAppDetail()) {
					AdbUtils.back();
					Thread.sleep(1000);
					AdbUtils.swipe(300,800,300,665);
					continue;
				}
				if (!installAppManager.checkBottomContinueExperience()) {
					// ������ذ�װ���ߴ�
					AdbUtils.click(360, 1139);
					Thread.sleep(20 * 1000);
					if ((!installAppManager.checkTL00Install()) && (!installAppManager.checkTL00Install2())) {
						return ResultDict.COMMAND_RESTART_APP;
					}
					// �����װ
					AdbUtils.click(594, 1080);
					Thread.sleep(40 * 1000);
					// �鿴�Ƿ����
					if (!installAppManager.checkTL00InstallComplete()) {
						return ResultDict.COMMAND_RESTART_APP;
					}
					// ������
					AdbUtils.click(195, 1080);
					if (!installAppManager.checkTL00DeletePackage()) {
						return ResultDict.COMMAND_RESTART_APP;
					}
					// ���ɾ��
					AdbUtils.click(507, 698);
					Thread.sleep(1000);
					installCount++;
				}
				if(!installAppManager.checkBottomContinueExperience()){
					return ResultDict.COMMAND_RESTART_APP;
				}
				//�����������
				AdbUtils.click(360, 1139);
				Thread.sleep(3000);
				if(!installAppManager.checkEnterApp()){
					return ResultDict.COMMAND_RESTART_APP;
				}
				Thread.sleep(2*1000);
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

	// ���⽱��
	public int startSigninAppTask() {
		try {
			String lastPackage = "";
			int appUseTime = 1;
			boolean leftSwipe = false;
			while (!((DateUtils.getHour() > 8) || ((DateUtils.getHour() == 8) && (DateUtils.getMinute() > 30)))) {
				Log.log.info("waiting for 8:30");
				if (leftSwipe) {
					AdbUtils.swipe(100, 500, 400, 500);
				} else {
					AdbUtils.swipe(400, 500, 100, 500);
				}
				Thread.sleep(1 * 60 * 1000);
				leftSwipe = !leftSwipe;
			}
			// ���Ӧ��׬
			AdbUtils.click(270, 1140); 
			Thread.sleep(1000);
			if (!extraBonusManager.checkClickBottomApplication()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			// ���⽱��
			AdbUtils.click(610, 200); // CAN_CUN
			// AdbUtils.click(610, 180); //oppo A37m
			Thread.sleep(8000);
			if (!extraBonusManager.checkClickExtraBonus()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			while (true) {
				Thread.sleep(500);
				AdbUtils.swipe(300, 500, 300, 1000);
				Thread.sleep(5000);
				AdbUtils.click(620, 320); 
				Thread.sleep(3000);
				switch (extraBonusManager.checkEnterApp()) {
				case ResultDict.COMMAND_BACK:
					if (extraBonusManager.checkFinishExtraBonus()) {
						AdbUtils.back();
						Thread.sleep(5000);
						if (!((DateUtils.getHour() > 10) || ((DateUtils.getHour() == 10) && (DateUtils.getMinute() > 30)))) {
							Log.log.info("waiting for 10:30");
							Thread.sleep(5 * 60 * 1000);
							continue;
						} else {
							isExtraBonusCompleted = true;
							return ResultDict.COMMAND_SUCCESS;
						}
					}
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
				if(lastPackage.equals(AdbUtils.getCurrentPackage())){
					appUseTime++;
				}else{
					appUseTime = 1;
				}
				Thread.sleep(appUseTime * 70 * 1000);
				String name = AdbUtils.getCurrentPackage();
				lastPackage = AdbUtils.getCurrentPackage();
				AdbUtils.killProcess(AdbUtils.getCurrentPackage());
				Thread.sleep(5000);
				if (!extraBonusManager.checkKillApp(name)) {
					return ResultDict.COMMAND_RESTART_APP;
				}
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultDict.COMMAND_RESTART_APP;
		}
	}

	// ����Ϸ׬�н��뿴��׬
	public int startLooklookTaskFromBottomGame() {
		try {
			// �����Ϸ׬
			AdbUtils.click(471, 1140);
			Thread.sleep(1000);
			if (!looklookManager.checkClickBottomGame()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			for (int i = 0; i < 5; i++) {
				AdbUtils.swipe(500, 700, 500, 300);
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
//			if (!clickLoveNews()) {
//				return ResultDict.COMMAND_RESTART_APP;
//			}
			isLooklookCompleted = true;
			return ResultDict.COMMAND_SUCCESS;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return ResultDict.COMMAND_RESTART_APP;
		}
	}

	// ����
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

	// �ȵ�����
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
					Log.log.info("click blank");
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

	// 360����
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

	// ������
	public void clickTuitui() {
		try {
			Thread.sleep(10000);
			for (; tuituiNum < Contants.TUITUI_NUM; tuituiNum++) {
				AdbUtils.click(648,510);
				Thread.sleep(10 * 1000);
				AdbUtils.back();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ������
	public boolean clickTurnturn() {
		try {
			for (; tuituiNum < Contants.TURNTURN_NUM; tuituiNum++) {
				AdbUtils.click(504,510);
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

	// ����
	public void clickRedPackage() {
		// oppo����
		try {

			for (; redPackageNum < Contants.RED_PACKAGES_NUM; redPackageNum++) {
				AdbUtils.click(360, 510);
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

	// ���ͷ��
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
					Log.log.info("click blank");
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

	// 18ͷ��
	public boolean clickEighteenNews() {
		try {
			AdbUtils.click(216,695);
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

	// �Ұ�ͷ��
	public boolean clickLoveNews() {
		try {
			AdbUtils.click(648,695);
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

	// ���ձؿ�
	public void clickTodayMustNews() {
		// TODO
	}

	// ���ֱ���
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
					Log.log.info("click blank");
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

	// ����������� ���Ž���ͣ��20S
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

	// ����������� ���Ž��滬��
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
	
	public void printDoingApp() {
		// �����������ҳ
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < 100; i++) {
				// �����������ҳ
				AdbUtils.click(360, 318);
				Thread.sleep(2000);
				if (!installAppManager.checkBottomContinueExperience()) {
					// ������ذ�װ���ߴ�
					AdbUtils.click(360, 1139);
					Thread.sleep(20 * 1000);

				}
				String name = AdbUtils.getCurrentPackage();
				sb.append(name + "\n");
				AdbUtils.killProcess(AdbUtils.getCurrentPackage());
				Thread.sleep(5000);
				AdbUtils.back();
				Thread.sleep(1000);
				AdbUtils.swipe(300, 800, 300, 665);
				Log.log.info(sb.toString());
			}
			FileUtils.String2File("d:/doingApp.txt", sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
