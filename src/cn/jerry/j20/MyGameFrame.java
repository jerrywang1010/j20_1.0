package cn.jerry.j20;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;


public class MyGameFrame extends JFrame{
    //Image i = GameUtil.getImage("Images/j20.png");
    Image bg = GameUtil.getImage("Images/bg.png");
    Image planeImg = GameUtil.getImage("Images/shabi.png");

    Plane plane = new Plane(planeImg, 250, 250);

    Shell[] shell_list = new Shell[10];




    boolean collide = false;

    Explode explode;

    Date start_time = new Date();
    Date end_time;
    int period;
    Waigua[] waigua_list = new Waigua[shell_list.length];


   public Waigua[] initialize_waigua(Plane plane, Shell[] shell_list){
       for (int i = 0; i < shell_list.length; i ++){
           int ID = i;
           double direction = shell_list[i].degree;
           int speed = shell_list[i].speed;
           double distance = Math.sqrt(Math.pow((plane.x - shell_list[i].x), 2) +
                                        Math.pow((plane.y - shell_list[i].y), 2));
           double time = 0;
//           double degree_shell_to_plane = Math.atan((plane.y - shell_list[i].y) /
//                                                    (plane.x - shell_list[i].x));

//           if (direction - degree_shell_to_plane > -Math.PI / 2 && direction - degree_shell_to_plane < Math.PI / 2){
//           }
           for (int step = 0; step < distance / speed; step ++){
               Shell test_shell = new Shell(shell_list[i].x + speed * Math.cos(direction) * step,
                                            shell_list[i].y + Math.sin(direction) * step * speed);

               if (plane.getRect().intersects(test_shell.getRect())) {
                    time = step;
                    break;
               }
           }


           waigua_list[i] = new Waigua(ID, time, distance, direction, speed);
       }

       return waigua_list;
   }

   public void print_waigua_list(Waigua[] waigua_list){
       for (int i = 0; i < waigua_list.length; i ++){
            System.out.print(waigua_list[i].speed + " " + waigua_list[i].distance + " " + waigua_list[i].time);
            System.out.print("\n");
       }
   }


    @Override
    public void paint(Graphics g) {
        g.clearRect(0,0,this.getWidth(),this.getHeight());



        g.drawImage(bg, 0, 0, null);
        plane.drawSelf(g);




        for (int i = 0; i < shell_list.length; i ++){
            shell_list[i].drawSelf(g);

            collide = shell_list[i].getRect().intersects(plane.getRect());
            if (collide && plane.alive) {
                System.out.println("collision!!!");
                plane.alive = false;

                    if (explode == null)
                        explode = new Explode(plane.x, plane.y);

                    explode.draw(g);
                    end_time = new Date();
                    period = (int) (end_time.getTime()- start_time.getTime())/1000;
            }


        }

        if (!plane.alive) {
            g.setColor(Color.RED);
            Font f = new Font("宋体", Font.BOLD, 50);
            g.setFont(f);
            g.drawString("时间 = " + period + "s", (int) plane.x, (int) plane.y);
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
        this.setTitle("j20");
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
        f.initialize_waigua(f.plane, f.shell_list);
        f.print_waigua_list(f.waigua_list);
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
