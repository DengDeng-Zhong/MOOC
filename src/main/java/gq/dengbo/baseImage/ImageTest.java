package gq.dengbo.baseImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ImageTest {

    public static void main(String[] args) throws Exception {
        
        //readAndWrite();
        //readComparison();
        //改变图片格式
//        cropImage("C:/Users/DengBo/Desktop/Demo.jpg", 
//                "C:/Users/DengBo/Desktop/D.jpg", 40, 30, 20, 20, "jpg", "jpg");//异常
        //两张图片横向拼接
//        combineImagesHorizontally("C:/Users/DengBo/Desktop/Demo.jpg", 
//                "C:/Users/DengBo/Desktop/Demo.jpg",
//                "jpg", "C:/Users/DengBo/Desktop/Demo1.jpg");//正常
        
        //验证码
//        generateCode("C:/Users/DengBo/Desktop/code.jpg");
        
        //画柱形图
        writeBar("C:/Users/DengBo/Desktop/bar.jpg");
        //画饼图
        writePie("C:/Users/DengBo/Desktop/pie.jpg");
        //画折线图
        writeLine("C:/Users/DengBo/Desktop/line.jpg");
    }
    
    private static void writeLine(String filePath) {
        DefaultCategoryDataset lines = new DefaultCategoryDataset();
        
        //第一条线
        lines.addValue(100, "java核心", "1月");
        lines.addValue(200, "java核心", "2月");
        lines.addValue(300, "java核心", "3月");
        lines.addValue(400, "java核心", "4月");
        //第二条线
        lines.addValue(100, "java核心（进阶）", "1月");
        lines.addValue(400, "java核心（进阶）", "2月");
        lines.addValue(700, "java核心（进阶）", "3月");
        
        try {
            ChartFactory.setChartTheme(getChineseTheme());
            JFreeChart chart = ChartFactory.createLineChart("折线图", "时间", "人数", lines, PlotOrientation.VERTICAL, false, false, false);
            ChartUtilities.saveChartAsJPEG(new File(filePath), chart, 600, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("完成");
    }

    private static void writePie(String filePath) {
        DefaultPieDataset pds = new DefaultPieDataset();
        pds.setValue("C人数", 100);
        pds.setValue("C++人数", 200);
        pds.setValue("JAVA人数", 300);
        try {
            ChartFactory.setChartTheme(getChineseTheme());
            JFreeChart chart = ChartFactory.createPieChart("饼图",pds, true, true, true);
            ChartUtilities.saveChartAsJPEG(new File(filePath), chart, 600, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("完成");
        }
    }

    private static void writeBar(String filePath) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(11, "", "第一季度");
        dataset.addValue(41, "", "第二季度");
        dataset.addValue(51, "", "第三季度");
        dataset.addValue(4, "", "第四季度");
        
        ChartFactory.setChartTheme(getChineseTheme());
        JFreeChart chart = ChartFactory.createBarChart3D("柱状图", "2018年", "产品质量", dataset, PlotOrientation.VERTICAL, false, false, false);
        
        try {
            ChartUtilities.saveChartAsJPEG(new File(filePath), chart, 600, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("柱形图完成");
    }

    public static void readAndWrite() throws Exception{
        BufferedImage image = ImageIO.read(new File("C:/Users/DengBo/Desktop/demo.png"));
        System.out.println("Height:"+image.getHeight());
        System.out.println("width:"+image.getWidth());
        ImageIO.write(image, "png", new File("C:/Users/DengBo/Desktop/Demo.jpg"));
    }
    
    
    public static void readComparison() throws Exception{
        System.out.println("======加载速度测试======");
        //ImageIO需要测试图片的类型，加载合适的IamgeReader来读取图片，耗时更长
        long startTime = System.currentTimeMillis();
        BufferedImage image = ImageIO.read(new File("C:/Users/DengBo/Desktop/demo.png"));
        System.out.println("Height:"+image.getHeight());
        System.out.println("Width:"+image.getWidth());
        long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime)/1000000.0 + "毫秒");
        
        //指定用jpg Reader来加载，速度会更快
        startTime = System.currentTimeMillis();
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader)readers.next();
        System.out.println(reader.getClass().getName());
        ImageInputStream iis = ImageIO.createImageInputStream(new File("C:/Users/DengBo/Desktop/Demo.jpg"));
        reader.setInput(iis, true);
        System.out.println("Height:"+reader.getHeight(0));
        System.out.println("Width:"+reader.getWidth(0));
        endTime = System.nanoTime();
        System.out.println((endTime-startTime)/1000000.0 + "毫秒");
        
    }
    
    /**
     * cropImage 将原始图片文件切割成一个矩形，并输出到目标图片文件
     * @param fromPath  原始图片
     * @param toPath    目标图片
     * @param x         坐标起点X
     * @param y         坐标起点Y
     * @param width     矩形宽度
     * @param height    矩形高度
     * @param readImageFormat   原始文件格式
     * @param writeImageFormat  目标文件格式
     * @throws Exception    抛出异常
     */
    public static void cropImage(String fromPath, String toPath, int x, int y, int width, int height
            ,String readImageFormat, String writeImageFormat) throws Exception {
        FileInputStream fis = null;
        ImageInputStream iis = null;
        
        try {
            fis = new FileInputStream(fromPath);
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(readImageFormat);
            ImageReader reader = it.next();
            iis  = ImageIO.createImageInputStream(fis);
            reader.setInput(iis, true);
            
            //定义一个矩形 并放入切割参数
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            
            //从源文件读取一个矩形大小的图像
            BufferedImage bi = reader.read(0, param);
            
            //写入到目标文件
            ImageIO.write(bi, writeImageFormat, new File(toPath));
            
        } finally {
            fis.close();
            iis.close();
        }
        
        
    }
    
    /**
     * 横行拼接两张图片，并写入目标文件
     * @param firstPath     第一个图片的路径
     * @param secondPath    第二个图片的路径
     * @param imageFormat   拼接生成图片的格式
     * @param toPath        目标图片的路径
     * @throws Exception
     */
    public static void combineImagesHorizontally(String firstPath, String secondPath, String imageFormat, String toPath)throws Exception {
        try {
            //读取第一张图片
            File first = new File(firstPath);
            BufferedImage imageOne = ImageIO.read(first);
            int width1 = imageOne.getWidth(); //宽
            int height1 = imageOne.getHeight();
            
            //从第一张那个图上读取RGB
            int[] firstRGB = new int[width1*height1];
            firstRGB = imageOne.getRGB(0, 0, width1, height1, firstRGB, 0, width1);
            
            //对第二张图片做同样的处理
            File second = new File(secondPath);
            BufferedImage imageTwo = ImageIO.read(second);
            int width2 = imageTwo.getWidth(); //宽
            int height2 = imageTwo.getHeight();
            int[] secondRGB = new int[width2*height2];
            secondRGB = imageTwo.getRGB(0, 0, width2, height2, secondRGB, 0, width2);
            
            //生成新图片
            int height3 = (height1>height2)?height1:height2; //挑选高度大的，
            int width3 = width1+width2;
            BufferedImage imageNew = new BufferedImage(width3, height3, BufferedImage.TYPE_INT_BGR);
            
            //设置作半部分的RGB从（0，0）开始
            imageNew.setRGB(0, 0, width1, height1, firstRGB, 0, width1);
            //设置右半部分的RGB从（width1，0）开始
            imageNew.setRGB(width1, 0, width2, height2, secondRGB, 0, width2);
            
            //保存图片
            ImageIO.write(imageNew, imageFormat, new File(toPath));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static int charNum = codeSequence.length;
    public static void generateCode(String filePath) throws Exception{
        int width = 80;
        int height = 32;
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        //定义图片上的干扰线
        Graphics2D gd = buffImg.createGraphics();
        gd.setColor(Color.LIGHT_GRAY);//填充浅灰色
        gd.fillRect(0, 0, width, height);
        gd.setColor(Color.black);//画边框
        gd.drawRect(0, 0, width-1, height-1);
        //随机产生16条干扰线
        gd.setColor(Color.gray);
        //创建一个随机数生成器，
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            gd.drawLine(x, y, x+x1, y1+y);
        }
        
        //计算字的位置坐标
        int codeCount = 4;//字符数
        int fontHeight;//字体高度
        int codeX;
        int codeY;
        codeX = (width - 4)/(codeCount+1);//第一个字母的起始位置
        fontHeight = height - 10;
        codeY = height - 7;
        
        //创建字体，根据图片的高度决定
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        gd.setFont(font);
        
        //随机产生codeCount数字的验证码
        for (int i = 0; i < codeCount; i++) {
            //每次随机拿一个字母，赋予随机颜色
            String strRand = String.valueOf(codeSequence[random.nextInt(charNum)]);
            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            gd.setColor(new Color(red, green, blue));
            //把字母放到图片上
            gd.drawString(strRand, (i+1)*codeX, codeY);
            
        }
        ImageIO.write(buffImg, "jpg", new File(filePath));
    }

    /**
     * 设置中文主题
     * @return 返回标准主题
     */
    public static StandardChartTheme getChineseTheme() {
        StandardChartTheme chineseTheme = new StandardChartTheme("CN");
        chineseTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
        chineseTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
        chineseTheme.setLargeFont(new Font("宋书", Font.BOLD, 20));
        return chineseTheme;
    }
    
    
}
