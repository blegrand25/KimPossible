//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    public Image ronnyPic;
    public Image kimmyPic;
    public Image backgroundPic;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    private KimmyP ronny;

    public KimmyP kimmy;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {
        // randomizing object math.random--> decimal in the range from 0-1
        setUpGraphics();

        backgroundPic = Toolkit.getDefaultToolkit().getImage("buenonacho.png");

        ronnyPic = Toolkit.getDefaultToolkit().getImage("ronstoppable.png");

        ronny = new KimmyP("ronny",10,100); //construct ron
        ronny.dx = -ronny.dx;

        kimmyPic = Toolkit.getDefaultToolkit().getImage("kimmyp2.png");
        kimmy = new KimmyP("kimmy", 800, 70);
        kimmy.ypos = 100;


    }



    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) { // while loop says do this thing until condition is met
            moveThings();  //move all the game objects
            crash();
            render();  // paint the graphics
            pause(40); // sleep for 10 ms
        }
    }
// astro is ron and alien is kimmy
    public void moveThings() {
        //calls the move( ) code in the objects
//        ronny.move();
        ronny.wrap();
        kimmy.bounce();
    }

    public void crash(){
        if (ronny.rec.intersects(kimmy.rec)){
            ronny.dx = -ronny.dx;
            kimmy.dx = -kimmy.dx;
        }

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");
    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);// clear everything on screen and keeps the onld images

        g.drawImage(backgroundPic, 0, 0, WIDTH, HEIGHT, null);

        if (ronny.isAlive == true) {
            g.drawImage(ronnyPic, ronny.xpos, ronny.ypos, ronny.width, ronny.height, null);
        }

        g.drawImage(kimmyPic, kimmy.xpos, kimmy.ypos, kimmy.width, kimmy.height, null);

        g.drawRect(kimmy.rec.x, kimmy.rec.y, kimmy.rec.width, kimmy.rec.height);
        g.drawRect(ronny.rec.x, ronny.rec.y, ronny.rec.width, ronny.rec.height);

        g.dispose();
        bufferStrategy.show();


    }
}