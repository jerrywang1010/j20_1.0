package cn.jerry.j20;

public class Waigua extends Shell {
    int shell_ID;
    double time;
    double distance;
    double direction;
    int speed;


    Waigua (int ID, double time, double distance, double direction, int speed){
        this.shell_ID = ID;
        this.time = time;
        this.distance = distance;
        this.direction = direction;
        this.speed = speed;
    }

    Waigua(){
    }
}
