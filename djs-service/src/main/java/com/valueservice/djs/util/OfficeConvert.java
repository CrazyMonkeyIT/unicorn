package com.valueservice.djs.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @desc:利用openoffice将word、ppt、excel等转化为pdf,再而转化为图片
 * @author biao.chen
 */
@Component
public class OfficeConvert {

    private final static Logger logger = LoggerFactory.getLogger(OfficeConvert.class);

    // 水印透明度
    private float alpha = 0.5f;
    // 水印横向位置
    private int positionWidth = 150;
    // 水印纵向位置
    private int positionHeight = 300;
    // 水印文字字体
    private Font font = new Font("仿宋", Font.BOLD, 26);
    // 水印文字颜色
    private Color color = Color.RED;

    @Value("${openoffice.workpath}")
    private String OPENOFFICE_HOME;

    @Value("${execute.openoffice.command}")
    private String OPENOFFICE_COMMAND;


    /**
     *TEST
     */
    public static void main(String[] args) {
        OfficeConvert officeConvert = new OfficeConvert();
        officeConvert.docToPdf("C:/Users/biao.chen/Desktop/服务器列表.pptx", "C:/Users/biao.chen/Desktop/file/logback.pdf");
        officeConvert.pdfToIamge(1.2f,"C:/Users/biao.chen/Desktop/file/logback.pdf", "C:/Users/biao.chen/Desktop/file/");
    }

    /**
     * 生成pdf的缩略图
     * @param zoom  缩略图显示倍数，1表示不缩放，0.5表示缩小到50%
     * @param inputFile  需要生成缩略图的书籍的完整路径
     * @param outputFile 生成缩略图的放置路径
     */
    public List<String> pdfToIamge(float zoom, String inputFile, String outputFile) {
        List<String> list = null;
        Document document = null;
        try {
            list = new ArrayList(0);
            document = new Document();
            document.setFile(inputFile);
            float rotation = 0;
            int maxPages = document.getPageTree().getNumberOfPages();
            for (int i = 0; i < maxPages; i++) {
                BufferedImage bfimage =
                        (BufferedImage) document.getPageImage(i,
                                GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, rotation, zoom);
                //添加水印
                //bfimage = setGraphics(bfimage);
                RenderedImage rendImage = bfimage;
                ImageIO.write(rendImage, "png", new File(outputFile+i+".png"));
                bfimage.flush();
                list.add(outputFile+i+".png");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(document!=null){
            document.dispose();
        }
        return list;
    }

    public String docToPdf(String inputFilePath, String outputFilePath){
        if(StringUtils.isEmpty(inputFilePath) || StringUtils.isEmpty(outputFilePath)){
            logger.error("Parameters cannot be empty!");
            return null;
        }
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);
        if(Objects.isNull(inputFile)){
            logger.error("inputFile cannot be empty!");
            return null;
        }
        if(StringUtil.isEmpty(OPENOFFICE_HOME)){
            //当成工具类启动会注入地址失败
            OPENOFFICE_HOME = "C:/Program Files (x86)/OpenOffice 4/";
            OPENOFFICE_COMMAND = "program/soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;";
        }
        if(OPENOFFICE_HOME.charAt(OPENOFFICE_HOME.length()-1)!='/'){
            OPENOFFICE_HOME += "/";
        }
        Process pro = null;
        OpenOfficeConnection connection = null;
        // 启动OpenOffice的服务
        OPENOFFICE_COMMAND = String.format("%s%s",OPENOFFICE_HOME,OPENOFFICE_COMMAND);
        try{
            // connect to an OpenOffice.org instance running on port 8100
            pro = Runtime.getRuntime().exec(OPENOFFICE_COMMAND);
            connection = new SocketOpenOfficeConnection(8100);
            connection.connect();

            // convert
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            logger.info("{}={}",inputFile,outputFile);

            converter.convert(inputFile, outputFile);
        }catch(Exception ex){
            logger.error("程序出错了",ex);
        }finally{
            // close the connection
            if(connection!=null){
                connection.disconnect();
                connection = null;
            }
            pro.destroy();
        }
        return outputFilePath;
    }

    /**
     * 添加上水印方法
     * @param bfimage
     * @return
     */
    private BufferedImage setGraphics(BufferedImage bfimage){
        Graphics2D g = bfimage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        // 5、设置水印文字颜色
        g.setColor(color);
        // 6、设置水印文字Font
        g.setFont(font);
        // 7、设置水印文字透明度
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
        //设置旋转
        g.rotate(-Math.PI/6);
        g.drawString("独角兽智库", 0, (bfimage.getHeight()/2)*1);
        // 9、释放资源
        g.dispose();
        return bfimage;
    }
}
