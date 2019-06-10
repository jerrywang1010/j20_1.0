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

            if(left){
                x -=speed;
            }
            if(right){
                x += speed;
            }
            if(up){
                y -=speed;    //y = y-speed;
            }
            if(down){
                y += speed;
            }
        }else{

        }



    }

    public  Plane(Image  img, double x, double y){
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 3;
        this.width = img.getWidth(null) ;
        this.height = img.getHeight(null);

    }

    //°´ÏÂÄ³¸ö¼ü£¬Ôö¼ÓÏàÓ¦µÄ·½Ïò
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



    public void direction(KeyEvent e) {
        speed = 15;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = true;
//                System.out.println("left");
                break;
            case KeyEvent.VK_W:
                up = true;
//                System.out.println("up");
                break;
            case KeyEvent.VK_S:
                down = true;
//                System.out.println("down");
                break;
            case KeyEvent.VK_D:
                right = true;
//                System.out.println("right");
                break;
        }
    }

    //°´ÏÂÄ³¸ö¼ü£¬È¡ÏûÏàÓ¦µÄ·½Ïò
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
