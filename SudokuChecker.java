/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class SudokuChecker {
    // returns if the sudoku puzzle was solved successfully and correctly
    public static boolean verify(SudokuGrid puzzle){
        for(int i=0;i<9;i++){
            if(!checkRow(i,puzzle)) // check each row
                return false;
            if(!checkCol(i,puzzle)) // check each column
                return false;
            if(!checkBox(i/3,i%3,puzzle)) // check each box
                return false;
        }
        return true;
    }
    
    // checks to make sure the row is correct
    public static boolean checkRow(int row,SudokuGrid puzzle){
        int[] nums={1,1,1,1,1,1,1,1,1};
        for(int i=0;i<9;i++)
            nums[puzzle.getGrid()[row][i].getValue()-1]--;
        for(int i=0;i<9;i++)
            if(nums[i]!=0)
                return false;
        return true;
    }
    
    // checks to make sure the col is correct
    public static boolean checkCol(int col,SudokuGrid puzzle){
        int[] nums={1,1,1,1,1,1,1,1,1};
        for(int i=0;i<9;i++)
            nums[puzzle.getGrid()[i][col].getValue()-1]--;
        for(int i=0;i<9;i++)
            if(nums[i]!=0)
                return false;
        return true;
    }
    
    // checks to make sure the box is correct
    public static boolean checkBox(int row,int col,SudokuGrid puzzle){
        int[] nums={1,1,1,1,1,1,1,1,1};
        int tlx=col/3*3;
        int tly=row/3*3;
        for(int i=tly;i<tly+3;i++)
            for(int j=tlx;j<tlx+3;j++)
                nums[puzzle.getGrid()[i][j].getValue()-1]--;
        for(int i=0;i<9;i++)
            if(nums[i]!=0)
                return false;
        return true;
    }
}
