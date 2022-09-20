package com.example.electronicshop.registration;

import com.example.electronicshop.captchaStoreMode.CaptchaStorage;
import com.example.electronicshop.captchaStoreMode.CaptchaStoreFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class DrawCaptcha {
    public static final String CAPTCHA_ID = "captchaId";
    public static final int CAPTCHA_LIFE_TIME = 30000;
    public static final String CAPTCHA_STORE_TYPE = "storeType";
    public CaptchaStoreFactory storeFactory = new CaptchaStoreFactory();
    public CaptchaStorage setCaptchaStore;
    public static final int TOTAL_CHARS = 6;
    public static final int CAPTCHA_HEIGHT = 40;
    public static final int CAPTCHA_WIDTH = 150;

    /**
     * generates a captcha and renders it
     *
     * @param request  - HttpServletRequest
     * @param response - HttpServletResponse
     * @throws IOException - when can't write response
     */
    public void draw(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String storeType = request.getServletContext().getInitParameter(CAPTCHA_STORE_TYPE);

        Font fntStyle1 = new Font("Arial", Font.BOLD, 30);
        Random randChars = new Random();
        String sImageCode = (Integer.toString(Math.abs(randChars.nextInt()))).substring(0, TOTAL_CHARS);
        setCaptchaStore = storeFactory.getCaptchaStore(storeType);
        setCaptchaStore.setCaptchaStore(request, response, sImageCode);
        BufferedImage biImage = new BufferedImage(CAPTCHA_WIDTH, CAPTCHA_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2dImage = (Graphics2D) biImage.getGraphics();
        g2dImage.setPaint(new Color(255, 255, 255));
        g2dImage.fillRect(0, 0, CAPTCHA_WIDTH, CAPTCHA_HEIGHT);
        g2dImage.setFont(fntStyle1);
        for (int i = 0; i < TOTAL_CHARS; i++) {
            g2dImage.setColor(new Color(randChars.nextInt(255), randChars.nextInt(255),
                    randChars.nextInt(255)));
            if (i % 2 == 0) {
                g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 24);
            } else {
                g2dImage.drawString(sImageCode.substring(i, i + 1), 25 * i, 35);
            }
        }
        OutputStream osImage = response.getOutputStream();
        ImageIO.write(biImage, "jpeg", osImage);
        osImage.close();
        g2dImage.dispose();

    }
}
