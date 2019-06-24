package cn.jerry.j20;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.util.Arrays;

public class Plane  extends GameObject {
    boolean  left,up,right,down;

    boolean  alive = true;

    public  void  drawSelf(Graphics  g){
        if(alive){
            g.drawImage(img, (int)x,(int) y, null);

            if(left && x > 10){
                x -= speed;
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
            reset();
        }else {

        }

    }


    public Plane (int x, int y){
        this.x = x;
        this.y = y;
        this.width = Constant.PLANE_WIDTH;
        this.height = Constant.PLANE_HEIGHT;
    }

    public  Plane(Image  img, double x, double y){
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = 6;
        this.width = img.getWidth(null) ;
        this.height = img.getHeight(null);

    }

    //degree in radian
//    public void dodge(double degree){
//        if (degree >= 0 && degree < Math.PI / 2 && degree != Double.POSITIVE_INFINITY) {
//            System.out.println("degree = " + degree + " up_right");
//            up = true;
//            right = true;
//            return;
//        }
//        if (degree >= Math.PI / 2 && degree < Math.PI && degree != Double.POSITIVE_INFINITY) {
//            System.out.println("degree = " + degree + " up_left");
//            up = true;
//            left = true;
//            return;
//        }
//        if (degree >= Math.PI && degree < Math.PI * 3/2 && degree != Double.POSITIVE_INFINITY) {
//            System.out.println("degree = " + degree + " down_left");
//            down = true;
//            left = true;
//            return;
//        }
//        if (degree >= Math.PI * 3/2 && degree < Math.PI * 2 && degree != Double.POSITIVE_INFINITY) {
//            System.out.println("degree = " + degree + " down_right");
//            down = true;
//            right = true;
//            return;
//        }
//
//    }


    void dodge (Shell[] shell_list){

        if (alive) {
            int min_step_not_move = 0;
            int min_step_move_right = 0;
            int min_step_move_up_right = 0;
            int min_step_move_up = 0;
            int min_step_move_up_left = 0;
            int min_step_move_left = 0;
            int min_step_move_down_left = 0;
            int min_step_move_down = 0;
            int min_step_move_down_right = 0;

            Shell[] original_position = shell_list;
            //step forward time to determine the optimal movement direction for the plane.
            //one step at a time

            System.out.println("================================");

            //need to consider if the plane is at the boundary

            //move right
            if (x + speed + 22 < Constant.GAME_WIDTH) {
                Plane plane_move_right = new Plane((int) x + speed, (int) y);
                min_step_move_right = step_from_collide(plane_move_right, shell_list[0]);
                for (int i = 1; i < shell_list.length; i++) {
                    int step_temp = step_from_collide(plane_move_right, shell_list[i]);
                    if (step_temp < min_step_move_right)
                        min_step_move_right = step_temp;
                }
            }
            System.out.println("move right step = " + min_step_move_right);


            //not move
            Plane plane_not_move = new Plane((int) x, (int) y);
            min_step_not_move = step_from_collide(plane_not_move, shell_list[0]);
            for (int i = 1; i < shell_list.length; i++) {
                int step_temp = step_from_collide(plane_not_move, shell_list[i]);
                if (step_temp < min_step_not_move)
                    min_step_not_move = step_temp;
            }
            System.out.println("not move step = " + min_step_not_move);


            //move up_right
            if (x + 22 + speed < Constant.GAME_WIDTH && y - speed > 0) {
                Plane plane_move_up_right = new Plane((int) x + speed, (int) y - speed);
                min_step_move_up_right = step_from_collide(plane_move_up_right, shell_list[0]);
                for (int i = 1; i < shell_list.length; i++) {
                    int step_temp = step_from_collide(plane_move_up_right, shell_list[i]);
                    if (step_temp < min_step_move_up_right)
                        min_step_move_up_right = step_temp;
                }
            }
            System.out.println("move up right step = " + min_step_move_up_right);


            //move up
            if (y - speed > 0) {
                Plane plane_move_up = new Plane((int) x, (int) y - speed);
                min_step_move_up = step_from_collide(plane_move_up, shell_list[0]);
                for (int i = 1; i < shell_list.length; i++) {
                    int step_temp = step_from_collide(plane_move_up, shell_list[i]);
                    if (step_temp < min_step_move_up)
                        min_step_move_up = step_temp;
                }
            }
            System.out.println("move up step = " + min_step_move_up);

            //move up_left
            if (y - speed > 0 && x - speed > 0) {
                Plane plane_move_up_left = new Plane((int) x - speed, (int) y - speed);
                min_step_move_up_left = step_from_collide(plane_move_up_left, shell_list[0]);
                for (int i = 1; i < shell_list.length; i++) {
                    int step_temp = step_from_collide(plane_move_up_left, shell_list[i]);
                    if (step_temp < min_step_move_up_left)
                        min_step_move_up_left = step_temp;
                }
            }
            System.out.println("move up left step = " + min_step_move_up_left);


            //move left
            if (x - speed > 0) {
                Plane plane_move_left = new Plane((int) x - speed, (int) y);
                min_step_move_left = step_from_collide(plane_move_left, shell_list[0]);
                for (int i = 1; i < shell_list.length; i++) {
                    int step_temp = step_from_collide(plane_move_left, shell_list[i]);
                    if (step_temp < min_step_move_left)
                        min_step_move_left = step_temp;
                }
            }
            System.out.println("move left step = " + min_step_move_left);


            //move down_left
            if (x - speed > 0 && y + 33 + speed < Constant.GAME_HEIGHT) {
                Plane plane_move_down_left = new Plane((int) x - speed, (int) y + speed);
                min_step_move_down_left = step_from_collide(plane_move_down_left, shell_list[0]);
                for (int i = 1; i < shell_list.length; i++) {
                    int step_temp = step_from_collide(plane_move_down_left, shell_list[i]);
                    if (step_temp < min_step_move_down_left)
                        min_step_move_down_left = step_temp;
                }
            }
            System.out.println("move down left step = " + min_step_move_down_left);


            //move down
            if (y + 33 + speed < Constant.GAME_HEIGHT) {
                Plane plane_move_down = new Plane((int) x, (int) y + speed);
                min_step_move_down = step_from_collide(plane_move_down, shell_list[0]);
                for (int i = 1; i < shell_list.length; i++) {
                    int step_temp = step_from_collide(plane_move_down, shell_list[i]);
                    if (step_temp < min_step_move_down)
                        min_step_move_down = step_temp;
                }
            }
            System.out.println("move down step = " + min_step_move_down);


            //move down_right
            if (y + 33 +speed < Constant.GAME_HEIGHT && x + speed + 22 < Constant.GAME_WIDTH) {
                Plane plane_move_down_right = new Plane((int) x + speed, (int) y + speed);
                min_step_move_down_right = step_from_collide(plane_move_down_right, shell_list[0]);
                for (int i = 1; i < shell_list.length; i++) {
                    int step_temp = step_from_collide(plane_move_down_right, shell_list[i]);
                    if (step_temp < min_step_move_down_right)
                        min_step_move_down_right = step_temp;
                }
            }
            System.out.println("move down right step = " + min_step_move_down_right);


            //find the optimal moving direction and move

            find_movement(min_step_not_move, min_step_move_right, min_step_move_up_right, min_step_move_up,
                    min_step_move_up_left, min_step_move_left, min_step_move_down_left, min_step_move_down, min_step_move_down_right, plane_not_move);

            //determine the actual movement direction of plane
            if (up){
                if (right)
                    System.out.println("moving up right");
                if (left)
                    System.out.println("moving up left");
                if (!left && !right)
                    System.out.println("moving up");
            }
            if (down){
                if (right)
                    System.out.println("moving down right");
                if (left)
                    System.out.println("moving down left");
                if (!left && !right)
                    System.out.println("moving down");
            }
            if (!up && !down) {
                if (left)
                    System.out.println("moving left");
                if (right)
                    System.out.println("moving right");
                if (!left && !right)
                    System.out.println("not moving");
            }



        }


//        shell_list[0] = original_position[0];
    }


    //move to the largest collision steps direction
    void find_movement(int not_move_step, int right_step, int up_right_step, int up_step,
                        int up_left_step, int left_step, int down_left_step, int down_step, int down_right_step, Plane plane){
        int[] Arr = {not_move_step, right_step, up_right_step, up_step, up_left_step, left_step,
                    down_left_step, down_step, down_right_step};
        Arrays.sort(Arr);


        //minimum is the first element
        //not move is priority
        if (not_move_step == Arr[Arr.length - 1]) {
            reset();
            return;
        }

        else {

            //if two or more steps are the same, move further from the wall
            if (Arr[Arr.length - 1] == Arr[Arr.length - 2]) {
                double distance_from_right_wall = 0;
                double distance_from_left_wall = 0;
                double distance_from_top_wall = 0;
                double distance_from_bottom_wall = 0;

                double distance_from_top_right = 0;
                double distance_from_top_left = 0;
                double distance_from_bottom_left = 0;
                double distance_from_bottom_right = 0;

                if (right_step == Arr[Arr.length - 1]) {
                    distance_from_right_wall = Constant.GAME_WIDTH - plane.x - 22;
                    System.out.println("distance from right wall = " + distance_from_right_wall);
                }
                if (left_step == Arr[Arr.length - 1]) {
                    distance_from_left_wall = plane.x;
                    System.out.println("distance from left wall = " + distance_from_left_wall);

                }
                if (up_step == Arr[Arr.length - 1]) {
                    distance_from_top_wall = plane.y;
                    System.out.println("distance from top wall = " + distance_from_top_wall);

                }
                if (down_step == Arr[Arr.length - 1]) {
                    distance_from_bottom_wall = Constant.GAME_HEIGHT - plane.y - 33;
                    System.out.println("distance from bottom wall = " + distance_from_bottom_wall);

                }

                if (up_right_step == Arr[Arr.length - 1]) {
                    distance_from_top_right = Math.sqrt(Math.pow((Constant.GAME_WIDTH - plane.x - 22), 2) + Math.pow(plane.y, 2));
                    System.out.println("distance from top right wall = " + distance_from_top_right);

                }
                if (up_left_step == Arr[Arr.length - 1]) {
                    distance_from_top_left = Math.sqrt(Math.pow(plane.x, 2) + Math.pow(plane.y, 2));
                    System.out.println("distance from top left wall = " + distance_from_top_left);
                }
                if (down_left_step == Arr[Arr.length - 1]) {
                    distance_from_bottom_left = Math.sqrt(Math.pow(plane.x, 2) + Math.pow(Constant.GAME_HEIGHT - plane.y - 33, 2));
                    System.out.println("distance from bottom left wall = " + distance_from_bottom_left);
                }
                if (down_right_step == Arr[Arr.length - 1]) {
                    distance_from_bottom_right = Math.sqrt(Math.pow((Constant.GAME_WIDTH - plane.x - 22), 2) + Math.pow(Constant.GAME_HEIGHT - plane.y - 33, 2));
                    System.out.println("distance from down right wall = " + distance_from_bottom_right);
                }

                //move to the direction that has distance largest
                double[] distance = {distance_from_right_wall, distance_from_left_wall, distance_from_top_wall, distance_from_bottom_wall, distance_from_top_right,
                                    distance_from_top_left, distance_from_bottom_left, distance_from_bottom_right};

                Arrays.sort(distance);
                //largest distance is length - 1


                if (distance_from_right_wall == distance[distance.length - 1]) {
                    right = true;
                }
                if (distance_from_left_wall == distance[distance.length - 1]) {
                    left = true;
                }
                if (distance_from_top_wall == distance[distance.length - 1]) {
                    up = true;
                }
                if (distance_from_bottom_wall == distance[distance.length - 1]) {
                    down = true;
                }

                if (distance_from_top_right == distance[distance.length - 1]) {
                    up = true;
                    right = true;
                }
                if (distance_from_top_left == distance[distance.length - 1]) {
                    up = true;
                    left = true;
                }
                if (distance_from_bottom_left == distance[distance.length - 1]) {
                    down = true;
                    left = true;
                }
                if (distance_from_bottom_right == distance[distance.length - 1]) {
                    down = true;
                    right = true;
                }
            }

            //if only one is max step or none
            else {
                if (not_move_step == Arr[Arr.length - 1]){
                    return;
                }

                if (right_step == Arr[Arr.length - 1]) {
                    right = true;
                    return;
                }

                if (up_right_step == Arr[Arr.length - 1]) {
                    up = true;
                    right = true;
                    return;
                }

                if (up_step == Arr[Arr.length - 1]){
                    up = true;
                    return;
                }


                if (up_left_step == Arr[Arr.length - 1]) {
                    up = true;
                    left = true;
                    return;
                }

                if (left_step == Arr[Arr.length - 1]) {
                    left = true;
                    return;
                }

                if (down_left_step == Arr[Arr.length - 1]) {
                    down = true;
                    left = true;
                    return;
                }

                if (down_step == Arr[Arr.length - 1]) {
                    down = true;
                    return;
                }

                if (down_right_step == Arr[Arr.length - 1]) {
                    down = true;
                    right = true;
                    return;
                }
            }
        }
    }



    int step_from_collide(Plane plane, Shell shell){
        int step = 0;

        Shell test_shell = new Shell(shell.x, shell.y);

        for (step = 0; step < 1500; step ++){
            if (plane.getRect().intersects(test_shell.getRect())){
//                System.out.println("will collide in " + step);
                break;
            }

            //step forward
            else {
                test_shell.x += test_shell.speed * Math.cos(test_shell.degree);
                test_shell.y += test_shell.speed * Math.sin(test_shell.degree);
//
                if (test_shell.x < 0 || test_shell.x > Constant.GAME_WIDTH - test_shell.width) {
                    test_shell.degree = Math.PI - test_shell.degree;
                }
                if (test_shell.y < 30 || test_shell.y > Constant.GAME_HEIGHT - test_shell.height) {
                    test_shell.degree = - test_shell.degree;
                }
            }
        }
        return step;
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
