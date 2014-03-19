/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class RandomSodukoSolver{
    private SudokuGrid puzzle; // this is the puzzles current state
    
    public RandomSodukoSolver(char[][] param){
        puzzle=new SudokuGrid(param);
        solve();
    }
    
    // this is the method that solves the puzzle
    public void solve(){
        // implement
    }
    
    // returns the answer of this puzzel
    @Override
    public String toString(){
        String temp=puzzle.toString();
        return temp;
    }
}
