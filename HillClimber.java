/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class HillClimber {
    public HillClimber(){
        // implement maybe
    }
    
    // the basic hill climbing algorithm
    public boolean algorithm1(int func){
        double start=(int)(Math.random()*1000);
        if(Math.random()<.5)
            start*=-1;
        return afalgorithm1(func,start,1.0,0.0);
    }
    
    // the auxillary function for the basic hill climbing algorithm
    public boolean afalgorithm1(int func,double start,double grad,double prev){
        try{
            double right,left,temp;
            right=left=temp=0;
            if(getValue(func,start)<desiredResult(func)+5&&getValue(func,start)>desiredResult(func)-5) 
                return true;
            if(func==1){
                temp=Functions.function1(start);
                right=Functions.function1(start+grad);
                left=Functions.function1(start-grad);
            }
            if(func==2){
                temp=Functions.function2(start);
                right=Functions.function2(start+grad);
                left=Functions.function2(start-grad);
            }
            if(func==3){
                temp=Functions.function3(start);
                right=Functions.function3(start+grad);
                left=Functions.function3(start-grad);
            }
            if(func==4){
                temp=Functions.function4(start);
                right=Functions.function4(start+grad);
                left=Functions.function4(start-grad);
            }
            if(func==5){
                temp=Functions.function5(start);
                right=Functions.function5(start+grad);
                left=Functions.function5(start-grad);
            }
            if(right>left && right>temp){
                if(right==prev)
                    grad/=2;
                return afalgorithm1(func,start+grad,grad,prev);
            }
            if(left>right && left>temp){
                if(left==prev)
                    grad/=2;
                return afalgorithm1(func,start-grad,grad,prev);
            }
            if(temp<left&&temp<right)
                return afalgorithm1(func,start+grad,grad,prev)||afalgorithm1(func,start-grad,grad,prev);
            return false;
        }catch(StackOverflowError e) {return false;}
    }
    
    // hill climbing with random restarts
    public boolean algorithm2(int func){
        double best=Double.MIN_VALUE;
        double start;
        for(int i=0;i<50;i++){
            start=(int)(Math.random()*1000);
            double temp=afalgorithm2(func,start,1.0,0.0,1000);
            if(temp>best)
                best=temp;
            if(getValue(func,best)<desiredResult(func)+5&&getValue(func,best)>desiredResult(func)-5)
                return true;
        }
        return false;
    }
    
    public double desiredResult(int func){
        if(func==1)
            return Functions.MAX_1;
        if(func==2)
            return Functions.MAX_2;
        if(func==3)
            return Functions.MAX_3;
        if(func==4)
            return Functions.MAX_4;
        return Functions.MAX_5;
    }
    
    public double getValue(int func,double x){
        if(func==1)
            return Functions.function1(x);
        if(func==2)
            return Functions.function2(x);
        if(func==3)
            return Functions.function3(x);
        if(func==4)
            return Functions.function4(x);
        return Functions.function5(x);
    }
    
    // auxillary function for hill climbing with random restarts
    public double afalgorithm2(int func,double start,double grad,double prev,int cnt){
        try{
        double right,left,temp;
        right=left=temp=0;
        if(cnt==0) // if time runs out
            return start;
        if(func==1){
            temp=Functions.function1(start);
            right=Functions.function1(start+grad);
            left=Functions.function1(start-grad);
        }
        if(func==2){
            temp=Functions.function2(start);
            right=Functions.function2(start+grad);
            left=Functions.function2(start-grad);
        }
        if(func==3){
            temp=Functions.function3(start);
            right=Functions.function3(start+grad);
            left=Functions.function3(start-grad);
        }
        if(func==4){
            temp=Functions.function4(start);
            right=Functions.function4(start+grad);
            left=Functions.function4(start-grad);
        }
        if(func==5){
            temp=Functions.function5(start);
            right=Functions.function5(start+grad);
            left=Functions.function5(start-grad);
        }
        if(right>left && right>temp){
            if(right==prev)
                grad/=2;
            return afalgorithm2(func,start+grad,grad,prev,cnt-1);
        }
        if(left>right && left>temp){
            if(left==prev)
                grad/=2;
            return afalgorithm2(func,start-grad,grad,prev,cnt-1);
        }
        if(temp<left&&temp<right)
            return Math.max(afalgorithm2(func,start+grad,grad,prev,cnt-1),afalgorithm2(func,start-grad,grad,prev,cnt-1));
        return start;
        }catch(StackOverflowError e) {return start;}
    }
    
    // returns results from algorithms
    @Override
    public String toString(){
        Stopwatch tempWatch=new Stopwatch();
        boolean keep=false;
        boolean keep2=false;
        tempWatch.start();
        for(int i=0;i<100;i++){
            // Normal HillClimbing Function 1
            keep=algorithm1(1);
            StatsDisplay.totalTimeSpentH[0]+=tempWatch.timeInNanoseconds();
            if(keep)
                StatsDisplay.numCorrectH[0]+=1;
            // Normal HillClimbing Function 2
            keep=algorithm1(2);
            StatsDisplay.totalTimeSpentH[1]+=tempWatch.timeInNanoseconds();
            if(keep)
                StatsDisplay.numCorrectH[1]+=1;
            // Normal HillClimbing Function 3
            keep=algorithm1(3);
            StatsDisplay.totalTimeSpentH[2]+=tempWatch.timeInNanoseconds();
            if(keep)
                StatsDisplay.numCorrectH[2]+=1;
            // Normal HillClimbing Function 4
            keep=algorithm1(4);
            StatsDisplay.totalTimeSpentH[3]+=tempWatch.timeInNanoseconds();
            if(keep)
                StatsDisplay.numCorrectH[3]+=1;
            // Normal HillClimbing Function 5
            keep=algorithm1(5);
            StatsDisplay.totalTimeSpentH[4]+=tempWatch.timeInNanoseconds();
            if(keep)
                StatsDisplay.numCorrectH[4]+=1;
            // Improved HillClimbing Function 1
            keep2=algorithm2(1);
            StatsDisplay.totalTimeSpentIH[0]+=tempWatch.timeInNanoseconds();
            if(keep2)
                StatsDisplay.numCorrectIH[0]+=1;
            // Improved HillClimbing Function 2
            keep2=algorithm2(2);
            StatsDisplay.totalTimeSpentIH[1]+=tempWatch.timeInNanoseconds();
            if(keep2)
                StatsDisplay.numCorrectIH[1]+=1;
            // Improved HillClimbing Function 3
            keep2=algorithm2(3);
            StatsDisplay.totalTimeSpentIH[2]+=tempWatch.timeInNanoseconds();
            if(keep2)
                StatsDisplay.numCorrectIH[2]+=1;
            // Improved HillClimbing Function 4
            keep2=algorithm2(4);
            StatsDisplay.totalTimeSpentIH[3]+=tempWatch.timeInNanoseconds();
            if(keep2)
                StatsDisplay.numCorrectIH[3]+=1;
            // Improved HillClimbing Function 5
            keep2=algorithm2(5);
            StatsDisplay.totalTimeSpentIH[4]+=tempWatch.timeInNanoseconds();
            if(keep2)
                StatsDisplay.numCorrectIH[4]+=1;
        }
        return "Hill Climbing Done!";
    }
}