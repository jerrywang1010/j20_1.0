package cn.jerry.j20;

import java.awt.*;

public class Shell extends GameObject {
    double degree;

    public Shell(){
        x = 200;
        y = 200;
        width = 10;
        height = 10;
        speed = 5;

        degree = Math.random()*Math.PI*2;
    }

    @Override
    public void drawSelf(Graphics g) {
        super.drawSelf(g);
        Color c = g.getColor();
        g.setColor(Color.YELLOW);

        g.fillOval((int) x, (int) y, width, height);
        g.setColor(c);

        x += speed*Math.cos(degree);
        y += speed*Math.sin(degree);

        if (x < 0 || x > Constant.GAME_WIDTH - width){
            degree = Math.PI - degree;
        }
        if (y < 30 || y > Constant.GAME_HEIGHT - height){
            degree = -degree;
        }
    }
}
