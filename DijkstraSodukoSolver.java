/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
public class DijkstraSodukoSolver{
    private SudokuGrid puzzle; // the puzzle
    private PriorityQueue<SudokuCoordinate> queue; // the priority queue
    private ArrayList<SudokuCoordinate> tempRemoves; // temp removals
    private char[][] initial; // the initial puzzle
    
    public DijkstraSodukoSolver(char[][] param){
        queue=initQueue();
        tempRemoves=new ArrayList<>();
        initial=param;
    }
    
    // this is the method that solves the puzzle
    public void solve(){
        for(SudokuCoordinate point : puzzle.getUnsolved()){
            puzzle.getValuesForCoordinate(point);
            queue.add(point);
        }
        while(!queue.isEmpty()){
            SudokuCoordinate point=queue.poll();
            if(point.getPossibleValues().size()==1){
                point.setValue(point.getPossibleValues().get(0));
                puzzle.recalculateValues(point.getYpos(),point.getXpos());
                queue=redoQueue();
            }else{
                if(puzzle.checkAllForSpot(point))
                    queue=redoQueue();
                else
                    tempRemoves.add(point);
            }
        }
    }
    
    // resets the queue if an object changes within it
    public PriorityQueue<SudokuCoordinate> redoQueue(){
        PriorityQueue<SudokuCoordinate> temp=initQueue();
        while(!queue.isEmpty())
            temp.add(queue.poll());
        while(!tempRemoves.isEmpty())
            temp.add(tempRemoves.remove(0));
        return temp;
    }
    
    // initializes the queue
    public PriorityQueue<SudokuCoordinate> initQueue(){
        return new PriorityQueue<>(81,new Comparator<SudokuCoordinate>(){
            @Override
            public int compare(SudokuCoordinate i,SudokuCoordinate j){
                return (i.getPossibleValues().size()-j.getPossibleValues().size());
            }
        });
    }
    
    // returns the answer of this puzzle
    public String toString(){
        Stopwatch tempWatch=new Stopwatch();
        tempWatch.start();
        for(int i=0;i<100;i++){
            puzzle=new SudokuGrid(initial);
            solve();
            StatsDisplay.totalTimeSpentDS+=tempWatch.timeInNanoseconds();
            if(SudokuChecker.verify(puzzle))
                StatsDisplay.numCorrectDS+=1;
        }
        return "Dijkstras Sudoku Puzzle Completed Successfully";
    }
}