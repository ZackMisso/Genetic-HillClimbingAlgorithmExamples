/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Stopwatch{
    private long startTime;
    private long stopTime;
    private long beginTime;
    private static final double NANOS_PER_SEC=1000000000.0;
    
    public void start(){
        System.gc();
        startTime=System.nanoTime();
        beginTime=startTime;
    }
    
    public void stop(){
        stopTime=System.nanoTime();
    }
    
    public double time(){
        long temp=startTime;
        stop();
        startTime=stopTime;
        return (stopTime-temp)/NANOS_PER_SEC;
    }
    
    public long timeInNanoseconds(){
        long temp=startTime;
        stop();
        startTime=stopTime;
        return (stopTime-temp);
    }
}
