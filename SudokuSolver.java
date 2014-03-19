/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ZackMisso
 */
public class SudokuSolver{
     // dijkstras sudoku puzzel 1
    private final char[][] ds1={{'8','2','0','0','3','0','4','0','0'}, //829137465
        {'7','4','0','6','0','9','0','0','0'},                         //745689231
        {'3','1','0','0','2','5','9','0','0'},                         //316425978
        {'0','0','1','0','0','0','0','9','6'},                         //271548396
        {'5','0','0','0','0','6','0','8','7'},                         //534296187
        {'0','6','8','0','0','1','5','0','2'},                         //968371542
        {'1','5','7','9','0','4','8','0','0'},                         //157964823
        {'0','0','3','0','0','2','7','5','0'},                         //693812754
        {'4','0','0','0','0','3','0','1','0'}};                        //482753619
    private char[][] ds2; // dijkstras sudoku puzzel 2
    private char[][] ds3; // dijkstras sudoku puzzel 3
    private char[][] hs1; // hill climbing sudoku puzzel 1
    private char[][] hs2; // hill climbing sudoku puzzel 2
    private char[][] hs3; // hill climbing sudoku puzzel 3
    private char[][] rs1; // random sudoku puzzel 1
    private char[][] rs2; // random sudoku puzzel 2
    private char[][] rs3; // random sudoku puzzel 3
    
    public SudokuSolver(){
        // implement the puzzels
    }
    
    // returns the answer to the Sudoku problem
    public String toString(){
        DijkstraSodukoSolver temp=new DijkstraSodukoSolver(ds1);
        HillClimbingSudokuSolver temp2=new HillClimbingSudokuSolver(ds1);
        return temp.toString()+"\n\n"+temp2.toString();
    }
}
