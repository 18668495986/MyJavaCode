package homework.bank.view;

import homework.bank.bean.Customer;
import homework.bank.service.CustomerServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月16日
 *
 * @author 徐威
 * @version : 1.0
 */
public class CustomerView {
    static Scanner sc = new Scanner(System.in);
    static CustomerServer server = new CustomerServer();
    static int count = 3;

    public void mainView() throws IOException, ClassNotFoundException {
        boolean loop = true;
        server.in(); //读取文件数据
        while (true) {
            System.out.println("✈✈✈✈✈✈✈✈✈✈✈✈✈✈✈✈  客户信息管理软件  ✈✈✈✈✈✈✈✈✈✈✈✈✈✈✈✈");
            System.out.println("                                 1 注 册                    ");
            System.out.println("                                 2 登 录                     ");
            System.out.println("                                 3 查 询                     ");
            System.out.println("                                 4 退 出                     ");
            System.out.println("请输入操作");
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    regist(); //注册
                    break;
                case 2:
                    login(); //登录
                    break;
                case 3:
                    list(); //查询
                    break;
                case 4:
                    System.out.println("成功退出，欢迎下次光临！");
                    server.out(); //最后退出时，将之前的操作持久化到数据
                    server.close();
                    return;
                default:
                    System.out.println("您输入的参数有误！");
            }
        }
    }

    public static void list(){
        server.list();
    } //查询遍历

    private static void regist() throws IOException, ClassNotFoundException {
        System.out.println("请输入用户名");
        String admin = sc.next();
        System.out.println("请输入密码");
        String passwd = sc.next();
        Customer customer = new Customer(admin, passwd);
        boolean regist = server.regist(customer);
        if (regist) {
            System.out.println("\n注册成功");
        }else {
            System.out.println("注册失败，用户已存在！");
        }
    }

    public static void login() { //登录
        while (count > 0) {
            System.out.println("请输入用户名");
            String admin = sc.next();
            System.out.println("请输入密码");
            String passwd = sc.next();
            Customer customer = server.login(admin, passwd);
            if (customer != null) {
                System.out.println("登录成功");
                businessView(customer);
                return;
            } else {
                count--;
                System.out.println("登录失败,你还有" + count + "次机会");
            }
        }
    }

    private static void businessView(Customer customer) {
        boolean flag = true;
        while (flag) {
            System.out.println("✈✈✈✈✈✈✈✈✈✈✈✈✈✈✈✈  存取界面  ✈✈✈✈✈✈✈✈✈✈✈✈✈✈✈✈");
            System.out.println("                           您 当 前 余 额 为  元" + customer.getMoney());
            System.out.println("                                 1 取 钱                    ");
            System.out.println("                                 2 存 钱                     ");
            System.out.println("                                 3 退 出                     ");
            System.out.println("请输入你的操作");
            String opera = sc.next();
            switch (opera) {
                case "1":
                    draw(customer);
                    break;
                case "2":
                    deposit(customer);
                    break;
                case "3":
                    System.out.println("退出成功");
                    return;
                default:
                    System.out.println("输入有误");
            }
        }
    }

    private static void draw(Customer customer) { //取钱
        while (true) {
            System.out.println("请输入您要取的金额");
            int outCash = sc.nextInt();
            boolean b = server.outCash(customer, outCash);
            if (b) {
                System.out.println("取款成功");
                return;
            } else {
                System.out.println("取款金额有误！请重新输入");
            }
        }
    }

    private static void deposit(Customer customer) { // 存钱
        while (true) {
            System.out.println("请输入您要存入的金额");
            int intoCash = sc.nextInt();
            boolean b = server.intoCash(customer, intoCash);
            if (b) {
                System.out.println("存钱成功");
                return;
            } else {
                System.out.println("存入金额有误！请重新输入");
            }
        }
    }
}
