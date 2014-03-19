/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;
public class GeneticAlgorithm{
    private double[] children; // the list of children to keep a track of
    private int generations; // the number of generations it takes to solve
    private double best; // the current best solution
    
    public GeneticAlgorithm(){
        children=new double[10];
    }
    
    public boolean solve(int func){
        generations=0;
        initializePopulation();
        best=Double.MIN_VALUE;
        double desired=desiredResult(func);
        while(best>desired+.001||best<desired-.001){
            for(int i=0;i<10;i++){
                double pi=assessFitness(func,children[i]);
                if(pi>best)
                    best=pi;
            }
            generations++;
            double[] newGen=new double[10];
            //int[] tomutate=new int[10];
            //for(int i=0;i<10;i++)
            //    tomutate[i]=1;
            //for(int i=0;i<5;i++){
            //    int num=(int)(Math.random()*10);
            //    if(tomutate[num]==1)
            //        tomutate[num]=0;
            //    else
            ///        i--;
            //}
            for(int i=0;i<10;i++){
                //if(tomutate[i]==0){
                    double child=mutate(func,children[i]);
                    newGen[i]=child;
                //}
                //else
                //    newGen[i]=children[i];
            }
            children=newGen;
        }
        return true;
    }
    
    // assesses the fitness of a child in the population
    public double assessFitness(int func,double position){
        if(func==1)
            return Functions.function1(position);
        if(func==2)
            return Functions.function2(position);
        if(func==3)
            return Functions.function3(position);
        if(func==4)
            return Functions.function4(position);
        return Functions.function5(position);
    }
    
    // returns the desired result for the respective function
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
    
    // this function tweaks the child in the population
    public double mutate(int func,double position){
        Random r=new Random();
        double first,second,temp;//third;
        double grad1=r.nextDouble()*1.5; // the first mutation
        double grad2=r.nextDouble()*1.5; // the second mutation
        //double grad3=r.nextDouble()*3; // the third mutation
        double signflip=r.nextDouble();
        if(signflip<.5)
            grad1*=-1;
        signflip=r.nextDouble();
        if(signflip<.5)
            grad2*=-1;
        //signflip=r.nextDouble();
        //if(signflip<.5)
        //    grad3*=-1;
        //System.out.println(func);
        temp=assessFitness(func,position);
        first=assessFitness(func,position+grad1); // creates the first child
        second=assessFitness(func,position+grad2); // creates the second child
        //third=assessFitness(func,position+grad3); // creates the third child
        if(first>second && first>temp)
            position+=grad1;
        if(second>first && second>temp)
            position+=grad2;
        if(temp<first&&temp<second)
            position+=grad1;
        //System.out.println(func+"adsfadfasdf"+temp);
        return position;
        //return position+grad1;
    }
    
    // initializes the initial population
    public void initializePopulation(){
        Random r=new Random();
        for(int i=0;i<10;i++){
            children[i]=r.nextDouble()*1000;
            if(r.nextDouble()<.5)
                children[i]*=-1;
        }
        //for(int i=0;i<5;i++)
        //    children[i+5]=r.nextDouble()*-1000;
    }
    
    public String toString(){
        Stopwatch tempWatch=new Stopwatch();
        boolean keep=false;
        tempWatch.start();
        for(int i=0;i<100;i++){
            // Function 1
            keep=solve(1);
            StatsDisplay.totalTimeSpentGH[0]+=tempWatch.timeInNanoseconds();
            StatsDisplay.totalNumberOfGenerations[0]+=generations;
            if(keep)
                StatsDisplay.numCorrectGH[0]+=1;
            // Function 2
            keep=solve(2);
            StatsDisplay.totalTimeSpentGH[1]+=tempWatch.timeInNanoseconds();
            StatsDisplay.totalNumberOfGenerations[1]+=generations;
            if(keep)
                StatsDisplay.numCorrectGH[1]+=1;
            // Function 3
            keep=solve(3);
            StatsDisplay.totalTimeSpentGH[2]+=tempWatch.timeInNanoseconds();
            StatsDisplay.totalNumberOfGenerations[2]+=generations;
            if(keep)
                StatsDisplay.numCorrectGH[2]+=1;
            // Function 4
            keep=solve(4);
            StatsDisplay.totalTimeSpentGH[3]+=tempWatch.timeInNanoseconds();
            StatsDisplay.totalNumberOfGenerations[3]+=generations;
            if(keep)
                StatsDisplay.numCorrectGH[3]+=1;
            // Function 5
            keep=solve(5);
            StatsDisplay.totalTimeSpentGH[4]+=tempWatch.timeInNanoseconds();
            StatsDisplay.totalNumberOfGenerations[4]+=generations;
            if(keep)
                StatsDisplay.numCorrectGH[4]+=1;
        }
        return "Genetic Algorithm Done";
    }
}