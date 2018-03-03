package app;

import common.Contants;
import common.ResultDict;
import manager.ExtraBonusManager;
import manager.LooklookManager;
import util.AdbUtils;

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
	private boolean isExtraBonusCompleted = true;
	private boolean isLooklookCompleted = false;

	ExtraBonusManager extraBonusManager;
	LooklookManager looklookManager;

	public MiZhuan() {
		extraBonusManager = new ExtraBonusManager();
		looklookManager = new LooklookManager();
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

	// ��װ����
	public void startInstallAppTask() {

	}

	// ���⽱��
	public int startSigninAppTask() {
		try {
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
			for (int i = 0; i < 50; i++) {
				Thread.sleep(500);
				AdbUtils.swipe(300, 500, 300, 1000);
				Thread.sleep(5000);
				AdbUtils.click(620, 320); 
				Thread.sleep(3000);
				switch (extraBonusManager.checkEnterApp()) {
				case ResultDict.COMMAND_BACK:
					AdbUtils.back();
					Thread.sleep(5000);
					continue;
				case ResultDict.COMMAND_RESTART_APP:
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

	// ����Ϸ׬�н��뿴��׬
	public int startLooklookTaskFromBottomGame() {
//		try {
			// �����Ϸ׬
//			AdbUtils.click(471, 1140); 
//			Thread.sleep(1000);
//			if (!looklookManager.checkClickBottomGame()) {
//				return ResultDict.COMMAND_RESTART_APP;
//			}
//			for (int i = 0; i < 5; i++) {
//				AdbUtils.swipe(500, 700, 500, 300);
//				System.out.println("swipe");
//				Thread.sleep(1000);
//			}
//			if (!clickEntertainmentNews()) {
//				return ResultDict.COMMAND_RESTART_APP;
//			}
			if (!clickThreeSixZeroNews()) {
				return ResultDict.COMMAND_RESTART_APP;
			}
			if (!clickHotNews()) {
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
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//			return ResultDict.COMMAND_RESTART_APP;
//		}
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
			AdbUtils.click(72, 510);
			Thread.sleep(2000);
			if (!looklookManager.checkClickHotNews()) {
				return false;
			}
			for (; HotNewsNum < Contants.HOT_NEWS_NUM + 3; HotNewsNum++) {
				swipeAndClickNews();
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
			Thread.sleep(2000);
			if (!looklookManager.checkClick360News()) {
				return false;
			}
			for (; ThreeSixZeroNewsNum < Contants.THREE_SIX_ZERO_NEWS_NUM + 4; ThreeSixZeroNewsNum++) {
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
			for (; tuituiNum < Contants.TUITUI_NUM; tuituiNum++) {
				AdbUtils.click(506, 880);
				Thread.sleep(20 * 1000);
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
				AdbUtils.click(216, 510);
				Thread.sleep(20 * 1000);
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

	// ���ͷ��
	public boolean clickGoldNews() {
		try {
			AdbUtils.click(360, 325);
			Thread.sleep(2000);
			if (!looklookManager.checkClickGoldNews()) {
				return false;
			}
			for (; goldNewsNum < Contants.GOLD_NEWS_NUM + 4; goldNewsNum++) {
				swipeAndClickNews2();
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
			AdbUtils.click(360, 325);
			Thread.sleep(2000);
			if (!looklookManager.checkClickEighteenNews()) {
				return false;
			}
			for (; eighteenNum < Contants.EIGHTEEN_NEWS_NUM + 4; eighteenNum++) {
				swipeAndClickNews2();
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
			AdbUtils.click(360, 325);
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
				swipeAndClickNews2();
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
}
