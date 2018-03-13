package com.valueservice.djs.util;



import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.File;

public class OfficeToPDF {
    public void docToPdf(File inputFile, File outputFile){
        //启动服务
        String OpenOffice_HOME = "C:/Program Files (x86)/OpenOffice 4/";// 这里是OpenOffice的安装目录
        if(OpenOffice_HOME.charAt(OpenOffice_HOME.length()-1)!='/'){
            OpenOffice_HOME += "/";
        }
        Process pro = null;
        OpenOfficeConnection connection = null;
        // 启动OpenOffice的服务
        String command = OpenOffice_HOME
                + "program/soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
        // connect to an OpenOffice.org instance running on port 8100

        try{
            pro = Runtime.getRuntime().exec(command);
            connection = new SocketOpenOfficeConnection(8100);
            connection.connect();

            // convert
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            System.out.println(inputFile+"="+outputFile);

            converter.convert(inputFile, outputFile);
        }catch(Exception ex){
            System.out.println("程序出错了");
            ex.printStackTrace();

        }finally{
            // close the connection
            if(connection!=null){
                connection.disconnect();
                connection = null;
            }
            pro.destroy();
        }
    }


    //生产pdf线程
    static class TestThread extends java.lang.Thread{
        private File inputFile;
        private File outputFile;

        public void run(){
            OfficeToPDF t = new OfficeToPDF();
            t.docToPdf(inputFile, outputFile);
            System.out.println(outputFile.getName()+"文件已生成");
        }

        public void setInputFile(File inputFile) {
            this.inputFile = inputFile;
        }

        public void setOutputFile(File outputFile) {
            this.outputFile = outputFile;
        }
    }

    public static void main(String[] args) {
        //word 转 pdf
        OfficeToPDF wordToPDF = new OfficeToPDF();
        wordToPDF.docToPdf(new File("C:/Users/biao.chen/Desktop/交接文档.docx"), new File("C:/Users/biao.chen/Desktop/file/logback.pdf"));

//        //excel 转 pdf
//        wordToPDF.docToPdf(new File("E:/interface.xlsx"), new File("C:/Users/admin/Desktop/ss/interface.pdf"));
    }
}
