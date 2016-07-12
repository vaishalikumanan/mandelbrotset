/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mandelbrotset;

/**
 *
 * @author kumav1176
 */
import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class MandelbrotSet extends JFrame{
    int screenWidth = 1000;
    int screenHeight = 1000;
    double rmin = -3;
    double rmax = 1;
    double imin = -2;
    double imax = 2;
    // 0.0005 looks better, takes longer
    double increment = 0.005;
    //number of cells 
    double numx = (rmax - rmin)/increment + 1;
    double numy = (imax - imin)/increment + 1;
    
    public int screenXValue(double r){
        double xVal = (r - rmin) * (screenWidth/(rmax - rmin));
        return (int) xVal;
    }
    public int screenYValue(double i){
        double yVal = (i - imin) * (screenHeight/(imax - imin));
        return (int) yVal;
    }
    public void paint(Graphics g){
        //set background
        g.setColor(Color.white);
        g.fillRect(0,0,screenWidth,screenHeight);
        
        //real values, x axis
        for(double x = -3; x <= 1; x += increment){
            //imaginary values, y axis
            for(double y = -2; y <= 2; y += increment){
                ComplexNumber c = new ComplexNumber(x,y);
                ComplexNumber z = c;
                int n = 1;
                
                //if it goes over 2, it will go to infinity
                while(z.absValue() < 2 && n < 255){
                    //z = z^2 + c
                    z = z.multiByCN(z);
                    z = z.add(c);
                    //term number
                    n ++;
                }
                //black and white only
                /*if(n == 200){
                    int xVal = screenXValue(c.real);
                    int yVal = screenYValue(c.imaginary);
                    g.setColor(Color.black);
                    //1000/81 ~ 13, 600/81 ~ 8
                    //ceil to round up ^^
                    g.fillRect(xVal,yVal,(int)Math.ceil(screenWidth/numx),
                            (int)Math.ceil(screenHeight/numy));
                }*/
                //black, white and grays
                if(n != 1){
                    int xVal = screenXValue(c.real);
                    int yVal = screenYValue(c.imaginary);
                    Color s = new Color(255-n,255-n,255-n);
                    g.setColor(s);
                    //1000/81 ~ 13, 600/81 ~ 8
                    //ceil to round up ^^
                    g.fillRect(xVal,yVal,(int)Math.ceil(screenWidth/numx),
                                (int)Math.ceil(screenHeight/numy));
                }
                
            }
        }
    }
    public void initializeWindow() {
        setTitle("Mandelbrot");
        setSize(screenWidth, screenHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);//calls paint() for the first time
    }
    public static void main(String[] args) {
       MandelbrotSet myWindow = new MandelbrotSet();
       myWindow.initializeWindow();
    }
}
