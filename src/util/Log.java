package util;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import factory.LogFactory;

public class Log {
    // �Զ����ȫ��log������һ���������¼��
    public static Logger log = LogFactory.getGlobalLog();
    // Jdk1.7�Ժ��Դ���ȫ��log�������������FileHandler������д���ļ���־��
    private static Logger sysLog = Logger.getGlobal();

    static {
        LogUtils.addFileHandler(sysLog, Level.INFO, LogFactory.LOG_FOLDER + File.separator + "sys.log");
    }
	
}
