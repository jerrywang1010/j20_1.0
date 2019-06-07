package cn.jerry.j20;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{

    boolean left, right, up, down;

    public void drawSelf(Graphics g){
        g.drawImage(img, (int) x, (int) y, null);
        //x ++;
    }

    public Plane(Image img, double x, double y){
        this.img = img;
        this.x = x;
        this.y = y;
    }

    public void direction(KeyEvent e){
        speed = 15;
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                left = true;
                System.out.println("left");
                break;
            case KeyEvent.VK_W:
                up = true;
                System.out.println("up");
                break;
            case KeyEvent.VK_S:
                down = true;
                System.out.println("down");
                break;
            case KeyEvent.VK_D:
                right = true;
                System.out.println("right");
                break;
        }

        if (right)
            x += speed;
        if (left)
            x -= speed;
        if (up)
            y -= speed;
        if (down)
            y += speed;

        right = false;
        left = false;
        up = false;
        down = false;
        System.out.println("归零");
    }
}
