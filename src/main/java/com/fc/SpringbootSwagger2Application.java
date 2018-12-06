package com.fc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.fc.test.mapper")
public class SpringbootSwagger2Application {
    public static void main(String[] args) {
<<<<<<< HEAD
        SpringApplication newRun= new SpringApplication(SpringbootSwagger2Application.class);
        newRun.setBannerMode(Banner.Mode.CONSOLE);
        newRun.run(args);
=======
        SpringApplication.run(SpringbootSwagger2Application.class, args);
                        System.out.println("////////////////////////////////////////////////////////////////////");
                        System.out.println("//                          _ooOoo_                               //");
                        System.out.println("//                         o8888888o                              //");
                        System.out.println("//                         88' . '88                              //");
                        System.out.println("//                         (| ^_^ |)                              //");
                        System.out.println("//                         O\\  =  /O                              //");
                        System.out.println("//                      ____/`---'\\____                           //");
                        System.out.println("//                    .'  \\\\|     |//  `.                         //");
                        System.out.println("//                   /  \\|||  :  |||//  \\                         //");
                        System.out.println("//                  /  _||||| -:- |||||-  \\                       //");
                        System.out.println("//                  |   | \\\\\\  -  /// |   |                       //");
                        System.out.println("//                  | \\_|  ''\\---/''  |   |                       //");
                        System.out.println("//                  \\  .-\\__  `-`  ___/-. /                       //");
                        System.out.println("//                ___`. .'  /--.--\\  `. . ___                     //");
                        System.out.println("//              .'' '<  `.___\\_<|>_/___.'  >'''.                  //");
                        System.out.println("//            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                 //");
                        System.out.println("//            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /                 //");
                        System.out.println("//      ========`-.____`-.___\\_____/___.-`____.-'========         //");
                        System.out.println("//                           `=---='                              //");
                        System.out.println("//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //");
                        System.out.println("//            佛祖保佑       永不宕机     永无BUG                    //");
                        System.out.println("////////////////////////////////////////////////////////////////////");
>>>>>>> 9bb98eff013c2d25778ef196943a3f1033e406d9
    }
}
