package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;


public class CutImageUtil {
	 private static final String DESTIMAGEPATH = "e://";
	    private static final String BASE_IMAGE_URL="http://pimages3.tianjimedia.com/resources/product/";
	    /**���е�X����*/
	    private static int x=16;
	    /**���е�Y����*/
	    private static int y=22;
	    /**���е���*/
	    private static int width=784;
	    /**���е�߶�*/
	    private static int height=560;

	    /**
	     *
	     * @Description: ��������ͼƬ
	     * String
	     */
	    public static String cutFromUrl(String imageUrl) {
	        String suffix=imageUrl.substring(imageUrl.lastIndexOf("."));
	        String fileName="";
	        String fileNameAndPath="";
	        InputStream is = null;
	        ImageInputStream iis = null;
	        String result=null;
	        try {
	            /**��ȡͼƬ*/
	            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
	            ImageReader reader = it.next();
	            /**��ȡͼƬ��*/
	            URL url = new URL(imageUrl);
	            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	            /**��������ʽΪ"GET"*/
	            httpConn.setRequestMethod("GET");
	            /**��ʱ��Ӧʱ��Ϊ5��*/
	            httpConn.setConnectTimeout(5 * 1000);
	            httpConn.connect();
	            is = httpConn.getInputStream();

	            iis = ImageIO.createImageInputStream(is);
	            reader.setInput(iis, true);

	            ImageReadParam param = reader.getDefaultReadParam();
	            Rectangle rect = new Rectangle(x, y, width, height);
	            param.setSourceRegion(rect);
	            BufferedImage bi = reader.read(0, param);

	            fileName=GlobalMethodUtil.createFileName(suffix);
	            fileNameAndPath=GlobalMethodUtil.createDir("/home/tmp/resources/product/")+fileName;
	            if(fileNameAndPath==null||fileNameAndPath.equals("")){
	                result="";
	            }else{
	                result=BASE_IMAGE_URL+fileNameAndPath.substring(fileNameAndPath.indexOf("product/")+8);
	            }
	            ImageIO.write(bi, "jpg", new File(fileNameAndPath));
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (is != null) {
	                    is.close();
	                }
	                if (iis != null) {
	                    iis.close();
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }
	    
	    public static String cutLocalImage(String imagePath , int minX , int minY , int width , int height) {
	        String fileName="";
	        String fileNameAndPath="";
	        FileInputStream fis = null;
	        ImageInputStream iis = null;
	        try {
	            /**��ȡͼƬ*/
	            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("png");
	            ImageReader reader = it.next();
	            /**��ȡͼƬ��*/
	            fis = new FileInputStream(imagePath);
	            iis = ImageIO.createImageInputStream(fis);
	            reader.setInput(iis, true);

	            ImageReadParam param = reader.getDefaultReadParam();
	            Rectangle rect = new Rectangle(minX, minY, width, height);
	            param.setSourceRegion(rect);
	            BufferedImage bi = reader.read(0, param);

	            fileName=GlobalMethodUtil.createFileName("png");
	            fileNameAndPath=GlobalMethodUtil.createDir("/home/tmp/qicheInfo/resources/product/")+fileName;
	            ImageIO.write(bi, "png", new File(DESTIMAGEPATH + new Date().getTime() + "." + "png"));
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (fis != null) {
	                    fis.close();
	                }
	                if (iis != null) {
	                    iis.close();
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	        return BASE_IMAGE_URL+fileNameAndPath.substring(fileNameAndPath.indexOf("product/")+8);
	    }

	    /**
	     *
	     * @Description: ���б���ͼƬ
	     *  @param imagePath
	     * String
	     */
	    public static String cutLocalImage(String imagePath) {
	        String fileName="";
	        String fileNameAndPath="";
	        FileInputStream fis = null;
	        ImageInputStream iis = null;
	        try {
	            /**��ȡͼƬ*/
	            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
	            ImageReader reader = it.next();
	            /**��ȡͼƬ��*/
	            fis = new FileInputStream(imagePath);
	            iis = ImageIO.createImageInputStream(fis);
	            reader.setInput(iis, true);

	            ImageReadParam param = reader.getDefaultReadParam();
	            Rectangle rect = new Rectangle(x, y, width, height);
	            param.setSourceRegion(rect);
	            BufferedImage bi = reader.read(0, param);

	            fileName=GlobalMethodUtil.createFileName("jpg");
	            fileNameAndPath=GlobalMethodUtil.createDir("/home/tmp/qicheInfo/resources/product/")+fileName;
	            ImageIO.write(bi, "jpg", new File(DESTIMAGEPATH + new Date().getTime() + "." + "jpg"));
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            try {
	                if (fis != null) {
	                    fis.close();
	                }
	                if (iis != null) {
	                    iis.close();
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	        return BASE_IMAGE_URL+fileNameAndPath.substring(fileNameAndPath.indexOf("product/")+8);
	    }

	    private static class GlobalMethodUtil {

	        /**
	         * �����ļ���(file)
	         * @Description:
	         * @return
	         */
	        public static String createFileName(String suffix) {
	            /**���Ŀ¼���ļ��� ����Ψһ��ʶ��*/
	            Calendar c = Calendar.getInstance();
	            /**����Сʱ���Ӻ����Դ�ļ����ļ����������ļ���+�ļ���׺*/
	            String name = c.get(Calendar.HOUR) + "" + c.get(Calendar.MINUTE) + "" + c.get(Calendar.SECOND)
	                    + c.get(Calendar.MILLISECOND) + randStr()
	                    + suffix;
	            return name;
	        }

	        /**
	         * ����26����ĸ���������ĸ���
	         * @Description:
	         * @return
	         */
	        public static String randStr() {
	            String[] rands = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "k", "o", "p", "q",
	                    "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	            String randstr = "";
	            /**����8�������ĸ���*/
	            for (int i = 0; i < 8; i++) {
	                randstr += rands[new Random().nextInt(26)];
	            }
	            return randstr;
	        }

	        /**
	         * �����ļ�Ŀ¼
	         * @Description:
	         * @return �ļ�Ŀ¼·��
	         */
	        public static String createDir(String path) {
	            Calendar c = Calendar.getInstance();
	            String dir = path;
	            dir += c.get(Calendar.YEAR) + "" + ((c.get(Calendar.MONTH) + 1)<10?"0"+(c.get(Calendar.MONTH) + 1):(c.get(Calendar.MONTH) + 1)) + "" + (c.get(Calendar.DATE)<10?"0"+c.get(Calendar.DATE):c.get(Calendar.DATE)) + "/";
	            File tempDir = new File(dir);
	            /**�ж��ļ�·���Ƿ����*/
	            if (!tempDir.exists()) {
	                /**�����ھʹ����ļ�Ŀ¼*/
	                tempDir.mkdirs();
	            }
	            return dir;
	        }

	        /**
	         *
	         * @Description: ��ȡ��ҳ����ҳ��
	         *  @param count ��������
	         *  @param size ÿҳ��ҳ����
	         *  @return
	         * int
	         */
	        public static int getPageSize(int count,int size){
	            return count%size!=0?(count/size+1):(count/size);
	        }
	    }
}
