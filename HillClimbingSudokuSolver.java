/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
public class HillClimbingSudokuSolver {
    private SudokuGrid puzzle; // this is the puzzles current state
    private PriorityQueue<SudokuCoordinate> queue; // the queue of unsolved positions
    private ArrayList<SudokuCoordinate> initialState; // the initial state of the coorinates
    private char[][] initial; // the initial puzzle
    
    public HillClimbingSudokuSolver(char[][] param){
        queue=initQueue();
        //puzzle=new SudokuGrid(param);
        initialState=new ArrayList<>();
        initial=param;
    }
    
    // this is the method that solves the puzzle
    public void solve(){
        for(SudokuCoordinate point : puzzle.getUnsolved()){
            puzzle.getValuesForCoordinate(point);
            initialState.add(point.copy());
            queue.add(point);
        }
        while(!queue.isEmpty()){
            checkForAbsoluteAddition();
            pickRandomPossibility();
            errorCheckWithPossibleRestart();
        }
    }
    
    // checks for an error, and if one is found does a random restart
    private void errorCheckWithPossibleRestart(){
        if(queue.isEmpty())
            return;
        SudokuCoordinate point=queue.poll();
        queue.add(point);
        if(point.getPossibleValues().isEmpty()){
            queue.clear();
            restartUnsolved();
        }
    }
    
    // takes a random possible tweak and makes it happen
    private void pickRandomPossibility(){
        if(queue.isEmpty())
            return;
        SudokuCoordinate point=queue.poll();
        int possibilities=point.getPossibleValues().size();
        if(possibilities==0){ // safe checking
            queue.add(point);
            return;
        }
        int chosenIndex=(int)(Math.random()*possibilities);
        point.setValue(point.getPossibleValues().get(chosenIndex));
        puzzle.recalculateValues(point);
        queue=redoQueue();
    }
    
    // adds more objects to the grid while they have a priority value of one
    private void checkForAbsoluteAddition(){
        SudokuCoordinate point=queue.poll();
        while(point.getPossibleValues().size()==1&&!queue.isEmpty()){
            point.setValue(point.getPossibleValues().get(0));
            puzzle.recalculateValues(point);
            point=queue.poll();
        }
        queue.add(point);
        queue=redoQueue();
    }
    
    // initializes the queue
    private PriorityQueue<SudokuCoordinate> initQueue(){
        return new PriorityQueue<>(81,new Comparator<SudokuCoordinate>(){
            @Override
            public int compare(SudokuCoordinate i,SudokuCoordinate j){
                return (i.getPossibleValues().size()-j.getPossibleValues().size());
            }
        });
    }
    
    // resets the queue if an object changes within it
    private PriorityQueue<SudokuCoordinate> redoQueue(){
        PriorityQueue<SudokuCoordinate> temp=initQueue();
        while(!queue.isEmpty())
            temp.add(queue.poll());
        return temp;
    }
    
    // reimplements the unsolved objects from the temp list
    private void restartUnsolved(){
        puzzle.getUnsolved().clear();
        for(SudokuCoordinate object:initialState){
            object.setReference(puzzle);
            puzzle.getGrid()[object.getYpos()][object.getXpos()]=object;
            puzzle.getUnsolved().add(object);
            queue.add(object);
        }
    }
    
    // returns the answer of this puzzel
    @Override
    public String toString(){
        Stopwatch tempWatch=new Stopwatch();
        tempWatch.start();
        for(int i=0;i<100;i++){
            puzzle=new SudokuGrid(initial);
            solve();
            StatsDisplay.totalTimeSpentHS+=tempWatch.timeInNanoseconds();
            if(SudokuChecker.verify(puzzle))
                StatsDisplay.numCorrectHS+=1;
        }
        return "Hill Climbing Sudoku Done";
    }
}