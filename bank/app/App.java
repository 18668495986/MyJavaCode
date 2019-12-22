package homework.bank.app;


import homework.bank.view.CustomerView;
import org.junit.Test;

import java.io.IOException;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2019 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2019年12月16日
 *
 * @author 徐威
 * @version : 1.0
 */
public class App {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CustomerView view = new CustomerView();
        view.mainView();
    }
    @Test
    public void print(){
        System.out.println("hi");
    }
}
