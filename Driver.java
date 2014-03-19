/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Driver{
    // the main method
    public static void main(String[] args){
        System.out.println(new StatsDisplay());
        System.out.println(new HillClimber());
        System.out.println(new GeneticAlgorithm());
        System.out.println(new SudokuSolver());
        System.out.println();
        StatsDisplay.outputResult();
    }
}
