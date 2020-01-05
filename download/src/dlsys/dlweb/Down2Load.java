package dlsys.dlweb;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.Test;
import utils.allUtil.BaseServlet;
import utils.beanfactory.staticfactory.Factory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2020 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2020年01月04日
 *
 * @author 徐威
 * @version : 1.0
 */
public class Down2Load extends BaseServlet {
    private static Pools pools = Factory.createBean("Pools");

    //文件上传
    public String upload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = "D:\\out\\download"; //给了一个存储目录
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath + "目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
        //消息提示 没有运行到最后的都认为失败
        String message = "文件上传失败";
        message = pools.getCommonMessage(message, request, response, savePath);
        request.setAttribute("message", message);
        return "upload:/listFile.jsp";
    }


    //--------------------改良后文件上传-----------------------
    public String googUpload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = "D:\\out\\download";
        //上传时生成的临时文件保存目录
        String tempPath = "D:\\out\\download\\temp";
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdir();
        }
        //消息提示
        String message = "";
        message = pools.getMessage(message, request, response, tmpFile, savePath);
        request.getSession().setAttribute("message", message);
        //成功上传后，跳转到查询界面
        return "googUpload:/listFile.jsp";
    }


    //----------------查询上传的文件目录-------------------
    public String listFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取上传文件的目录
        String uploadFilePath = "D:\\out\\download";
        //存储要下载的文件名
        Map<String, String> fileNameMap = new HashMap<String, String>();
        //递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
        pools.listfile(new File(uploadFilePath), fileNameMap);//File既可以代表一个文件也可以代表一个目录
        //将Map集合发送到listfile.jsp页面进行显示
        request.getSession().setAttribute("fileNameMap", fileNameMap);
        return "listFile:/listFile.jsp";
    }

    //--------------实现下载的功能-----------------
    public String downFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //得到要下载的文件名
        String fileName = request.getParameter("filename");
        System.out.println(fileName); //0caa20c8-68c8-468e-85b7-1d013b8fcb75_突然想起你.mp3
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath = "D:\\out\\download"; //修改下载存储的目录
        //通过文件名找出文件的所在目录
        String path = pools.findFileSavePathByFileName(fileName, fileSaveRootPath);
        //得到要下载的文件(目录+文件名)
        String filename=path+"\\"+fileName;
        System.out.println(filename); // D:\out\download\4\14\0caa20c8-68c8-468e-85b7-1d013b8fcb75_突然想起你.mp3
        File file = new File(filename);

        //如果文件不存在
        if (!file.exists()) {
            request.setAttribute("message", "您要下载的资源已被删除！！");
            return "downFile:/message.jsp";
        }
        //处理文件名
        String realname = fileName.substring(fileName.indexOf("_") + 1);
        System.out.println(realname);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path + "\\" + fileName);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024 * 1024 * 50];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while ((len = in.read(buffer)) > 0) {
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
        request.getSession().setAttribute("message", "下载成功");
        return "downFile:/index.jsp";
    }

    //普通的下载方式：
    public String downFile2(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename=request.getParameter("filename");
        System.out.println(filename);
        String path="D:\\out\\download"+"\\"+filename;
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(path));

        //获取文件类型
        String mimeType = this.getServletContext().getMimeType(filename);
        response.setHeader("content-type",mimeType);

        // 获取浏览器名称
        String agent = request.getHeader("user-agent");
        System.out.println(agent);
        filename = pools.getFileName(agent, filename);

        response.setHeader("content-disposition","attachment;filename="+filename);
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int b=0;
        while ((b=bin.read(buff))!=-1){
            sos.write(buff,0,b);
        }
        System.out.println("下载完成");
        bin.close();
        return "downFile2:/index.jsp";
    }
}


