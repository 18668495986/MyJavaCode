package dlsys.dlweb;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.allUtil.BaseServlet;
import utils.beanfactory.staticfactory.Factory;

import javax.servlet.ServletException;
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
        String savePath = "D:\\out\\download";
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath + "目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
        //消息提示
        String message = "upload:/message.jsp";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                return "upload:/listFile.jsp";
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                } else {//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);
                    if (filename == null || filename.trim().equals("")) {
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while ((len = in.read(buffer)) > 0) {
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功！";
                }
            }
        } catch (Exception e) {
            message = "文件上传失败！";
            e.printStackTrace();

        }
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
        String fileName = request.getParameter("filename");  //23239283-92489-阿凡达.avi
        System.out.println(fileName); //6677a0c6-e2de-4e71-a87f-91b52999138d_项目介绍.docx
        fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath = "D:\\out\\download";
        //通过文件名找出文件的所在目录
        String path = pools.findFileSavePathByFileName(fileName, fileSaveRootPath);
        System.out.println(path); // D:\out\download\11\9
        //得到要下载的文件
        File file = new File(path + "\\" + fileName);

        System.out.println(file);
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
}


