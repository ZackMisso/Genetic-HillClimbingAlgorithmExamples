/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class StatsDisplay{
    // Normal Hillclimber
    public static double[] totalTimeSpentH;
    public static int[] numCorrectH;
    // Improved hillclimber
    public static double[] totalTimeSpentIH;
    public static int[] numCorrectIH;
    // Genetic Hillclimber
    public static double[] totalTimeSpentGH;
    public static int[] numCorrectGH;
    public static int[] totalNumberOfGenerations;
    // Dijkstra's Sudoku
    public static double totalTimeSpentDS;
    public static int numCorrectDS;
    // Hill Climbing Sudoku
    public static double totalTimeSpentHS;
    public static int numCorrectHS;
    
    public StatsDisplay(){
        totalTimeSpentH=new double[5];
        numCorrectH=new int[5];
        totalTimeSpentIH=new double[5];
        numCorrectIH=new int[5];
        totalTimeSpentGH=new double[5];
        totalNumberOfGenerations=new int[5];
        numCorrectGH=new int[5];
        totalTimeSpentDS=0.0;
        numCorrectDS=0;
        totalTimeSpentHS=0.0;
        numCorrectHS=0;
    }
    
    public static void outputResult(){
        System.out.println("");
        System.out.println("PROGRAM STATS");
        System.out.println("--Normal Hillclimbing--");
        System.out.println("");
        for(int i=0;i<5;i++){
            System.out.println("Function "+(i+1));
            System.out.println("Avg Time Spent: "+totalTimeSpentH[i]/100);
            System.out.println("Percent correct: "+((double)numCorrectH[i])+"%");
            System.out.println("");
        }
        System.out.println("--Improved HillClimbing--");
        System.out.println("");
        for(int i=0;i<5;i++){
            System.out.println("Function "+(i+1));
            System.out.println("Avg Time Spent: "+totalTimeSpentIH[i]/100);
            System.out.println("Percent correct: "+((double)numCorrectIH[i])+"%");
            System.out.println("");
        }
        System.out.println("--Genetic HillClimbing--");
        System.out.println("");
        for(int i=0;i<5;i++){
            System.out.println("Function "+(i+1));
            System.out.println("Avg Time Spent: "+totalTimeSpentGH[i]/100);
            System.out.println("Avg Num of Generations: "+totalNumberOfGenerations[i]/100);
            System.out.println("Percent correct: "+((double)numCorrectGH[i])+"%");
            System.out.println("");
        }
        System.out.println("--Dijkstra's Sudoku--");
        System.out.println("");
        System.out.println("Avg Time Spent: "+totalTimeSpentDS/100);
        System.out.println("Percent correct: "+((double)numCorrectDS)+"%");
        System.out.println("");
        System.out.println("--HillClimbing Sudoku--");
        System.out.println("");
        System.out.println("Avg Time Spent: "+totalTimeSpentHS/100);
        System.out.println("Percent correct: "+((double)numCorrectHS)+"%");
        System.out.println("");
        System.out.println("Program Executed Successfully");
    }
    
    public String toString(){
        return "Algorithm Initiated\n\nStats Created\n\n";
    }
}