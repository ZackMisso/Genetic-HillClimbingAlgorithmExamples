 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Functions {
    // Hard coded for convenience purposes
    static double MAX_1=6.0; // the maximum of the first function
    static double MAX_2=20.0; // the maximum of the second function
    static double MAX_3=50.0; // the maximum of the third function
    static double MAX_4=192.0391; // the maximum of the fourth function
    static double MAX_5=27.564481; // the maximum of the fifth function
    
    // the first function y=6-x^2
    public static double function1(double x){
        return 6-x*x;
    }
    
    // the second function y= 16+8x-4x^2
    public static double function2(double x){
        return 16+8*x-4*x*x;
    }
    
    // the third function y=50sin(3x/10pi)
    public static double function3(double x){
        return 50 * Math.sin((3*x)/(10*Math.PI));
    }
    
    // the fourth function y=-x^4+8x^3-7x^2-12x+50
    public static double function4(double x){
        return -(x*x*x*x)+8*(x*x*x)-7*(x*x)-12*x+50;
    }
    
    // the fifth function y=(3x^2+120x-17)/(x^2+4.7)
    public static double function5(double x){
        return (3*x*x+120*x-17)/(x*x+4.7);
    }
}
