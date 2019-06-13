package cn.jerry.j20;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane  extends GameObject {
    boolean  left,up,right,down;

    boolean  alive = true;

    public  void  drawSelf(Graphics  g){
        if(alive){
            g.drawImage(img, (int)x,(int) y, null);

            if(left && x > 10){
                x -=speed;
            }
            if(right && x < Constant.GAME_WIDTH - 30){
                x += speed;
            }
            if(up && y > 33){
                y -=speed;    //y = y-speed;
            }
            if(down && y < Constant.GAME_HEIGHT - 40) {
                y += speed;
            }
        }else{

        }



    }

    public  Plane(Image  img, double x, double y){
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 5;
        this.width = img.getWidth(null) ;
        this.height = img.getHeight(null);

    }

    //degree in radian
    public void dodge(double degree){
        if (degree >= 0 && degree < Math.PI / 2) {
            System.out.println("degree = " + degree + " up_right");
            up = true;
            right = true;
//            reset();
            return;
        }
        if (degree >= Math.PI / 2 && degree < Math.PI) {
            System.out.println("degree = " + degree + " up_left");
            up = true;
            left = true;
//            reset();
            return;
        }
        if (degree >= Math.PI && degree < Math.PI * 3/2) {
            System.out.println("degree = " + degree + " down_left");
            down = true;
            left = true;
//            reset();
            return;
        }
        if (degree >= Math.PI * 3/2 && degree < Math.PI * 2) {
            System.out.println("degree = " + degree + " down_right");
            down = true;
            right = true;
//            reset();
            return;
        }

    }

    public void reset(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public  void   addDirection(KeyEvent  e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
        }
    }


    public  void   minusDirection(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
        }
    }


}
