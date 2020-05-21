package com.sy.by.mycontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class YanZheng {
    //验证码
    @GetMapping("/yanzheng")
    public void yanZ(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //设置给浏览器响应的类型
        resp.setContentType("image/png");
        ServletOutputStream os = resp.getOutputStream();

        //一个缓存区
        BufferedImage image=new BufferedImage(120, 40, BufferedImage.TYPE_INT_RGB);
        Graphics gs = image.getGraphics();
        gs.setColor(new Color(230,230,230));
        gs.fillRect(0, 0, 120, 40);


        //随机画50条线
        Random random=new Random();
        for(int i=0;i<50;i++)
        {
            gs.setColor(new Color(200+random.nextInt(30),100+random.nextInt(30),200+random.nextInt(30)));
            gs.drawLine(random.nextInt(120),random.nextInt(40), random.nextInt(120), random.nextInt(40));
        }

        //循环写出4个随机字母，并将它们存在stringBuilder,并且写在session上
        StringBuilder sBuffer=new StringBuilder();
        for(int i=0;i<4;i++)
        {
            String string=this.randChar();
            sBuffer.append(string);

            gs.setColor(new Color(160+random.nextInt(30),80+random.nextInt(30),80+random.nextInt(30)));
            gs.setFont(new Font("微软雅黑", Font.BOLD, 26));
            gs.drawString(string,12+i*30, 23+random.nextInt(5));
        }

        req.getSession().setAttribute("ran",sBuffer.toString());
        System.out.println(req.getSession().getAttribute("ran")+"yanzhengma这个controller里面存的session");

        req.getServletContext().setAttribute("random",sBuffer.toString());

        //将图片写到输出流
        ImageIO.write(image, "png", os);
    }
    //生成一个随机字母
    private String randChar() {
        int re=(int) ((Math.random()*26)+65);
        return String.valueOf((char)re);
    }
}
