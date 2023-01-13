import java.awt.*;

public class KimmyP {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;               //name of the hero
    public int xpos;                  //the x position
    public int ypos;                  //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;                 //the width of the hero image
    public int height;                //the height of the hero image
    public boolean isAlive;           //a boolean to denote if the hero is alive or dead
    public Rectangle rec; // also able to do astro.red now
    public boolean isCrashing = false;
    public boolean left;
    public boolean up;
    public boolean right;
    public boolean down;


    //This is a constructor that takes 3 parameters.
    // This allows us to specify the hero's name and position when we build it.
    public KimmyP(String pName, int pXpos, int pYpos) { // KimmyP Constructor
        name = pName;
        xpos = (int)(Math.random()*400+100);
        ypos = pYpos;
//        ypos = (int)(Math.random()*150 + 50);//150 the amount of possible number
        dx = 6;
        dy = 6;
        width = 90;
        height = 90;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);


    }


    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

    } // end move

    public void bounce(){

        if (left == true){
            dx = -5;
            dy=0;
        }

        if (right == true){
            dx = 5;
            dy=0;
        }

        if (down == true){
            dx = 0;
            dy=-5;
        }

        if (up == true){
            dx = 0;
            dy=5;
        }




        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos >= 1000-width || xpos <=0){
            dx = -dx;
        }

        if (ypos >= 700-height || ypos <= 0){
            dy=-dy;
        }

        rec = new Rectangle (xpos, ypos, width, height);

    }

    public void wrap(){
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos >= 1000 && dx > 0){// right wall
            xpos = - width;
        }

        if (xpos <= -width && dx < 0){// left wall
            xpos = 1000;
        }

        if(ypos <= -height && dy < 0){// top wall
            ypos = 600;
        }

        if (ypos >= 600 && dy > 0){// bottom wall
            ypos = -height;
        }

        rec = new Rectangle(xpos, ypos, width, height);
    }



}






