package cn.jerry.j20;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MyGameFrame extends JFrame{
    //Image i = GameUtil.getImage("Images/j20.png");
    Image bg = GameUtil.getImage("Images/bg.png");
    Image planeImg = GameUtil.getImage("Images/shabi.png");

    Plane plane = new Plane(planeImg, 250, 250);

    Shell[] shell_list = new Shell[50];

    boolean collide = false;

    Explode explode;

    @Override
    public void paint(Graphics g) {
        g.clearRect(0,0,this.getWidth(),this.getHeight());



        g.drawImage(bg, 0, 0, null);
        plane.drawSelf(g);




        for (int i = 0; i < shell_list.length; i ++){
            shell_list[i].drawSelf(g);

            collide = shell_list[i].getRect().intersects(plane.getRect());
            if (collide) {
                System.out.println("collision!!!");
                plane.alive = false;

                    if (explode == null)
                        explode = new Explode(plane.x, plane.y);

                    explode.draw(g);
            }
        }




    }


    class PaintThread extends Thread{
        @Override
        public void run() {
            super.run();
            while(true){
                repaint();
//                System.out.println("??");
                try {
                    Thread.sleep(40);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }


    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
//            System.out.println("?? " + e.getKeyCode());
            plane.addDirection(e);
//            plane.direction(e);
        }


        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
//            System.out.println("?? " + e.getKeyCode());
            plane.minusDirection(e);
        }
    }


    public void launchFrame(){
        this.setTitle("????j20");
        this.setVisible(true);
        this.setSize(500, 500);
        //this.setLocation(300, 300);
//
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        new PaintThread().start();
        addKeyListener(new KeyMonitor());


        for (int i = 0; i < shell_list.length; i ++){
            shell_list[i] = new Shell();
        }
    }

    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }

    private Image offScreenImage = null;
    public void update(Graphics g){
        if (offScreenImage == null)
            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

}
