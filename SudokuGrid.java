/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
public class SudokuGrid{
    private SudokuCoordinate[][] grid; // the grid of coordinates
    private ArrayList<SudokuCoordinate> unsolved; // unsolved coordinates
    
    // constructor with parameter grid
    public SudokuGrid(char[][] param){
        grid=new SudokuCoordinate[9][9];
        unsolved=new ArrayList<>();
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++){
                SudokuCoordinate temp=new SudokuCoordinate(this,j,i,Integer.parseInt(param[i][j]+""));
                if(param[i][j]=='0')
                    unsolved.add(temp);
                grid[i][j]=temp;
            }
    }
    
    // gets and sets the possible values for the parameter coordinate <=O(7n)
    public void getValuesForCoordinate(SudokuCoordinate point){
        ArrayList<Integer> temp=new ArrayList<>();
        temp.addAll(getAntiValuesForHorizontal(point.getYpos())); // O(n)
        temp.addAll(getAntiValuesForVertical(point.getXpos())); // O(n)
        temp.addAll(getAntiValuesForBox(point.getYpos(),point.getXpos())); // O(n)
        int[] possibilities={1,1,1,1,1,1,1,1,1};
        while(!temp.isEmpty()) // O(3n)
            possibilities[temp.remove(0)-1]=0;
        for(int i=0;i<9;i++) // O(n)
            if(possibilities[i]==1)
                temp.add(Integer.valueOf(i+1));
        //System.out.println("("+point.getXpos()+","+point.getYpos()+")"); // (5,0)
        //for(Integer i:temp)
        //    System.out.print(i+",");
        //System.out.println();
        point.setPossibleValues(temp);
    }
        
    // gets the values that can not be in the row O(n)
    public ArrayList<Integer> getAntiValuesForHorizontal(int row){
        ArrayList<Integer> temp=new ArrayList<>();
        for(int i=0;i<9;i++)
            if(grid[row][i].getValue()!=0)
                temp.add(Integer.valueOf(grid[row][i].getValue()));
        return temp;
    }
    
    // gets the values that can not be in the column O(n)
    public ArrayList<Integer> getAntiValuesForVertical(int col){
        ArrayList<Integer> temp=new ArrayList<>();
        for(int i=0;i<9;i++)
            if(grid[i][col].getValue()!=0)
                temp.add(Integer.valueOf(grid[i][col].getValue()));
        return temp;
    }
    
    // gets the values that can not be in the box O(n)
    public ArrayList<Integer> getAntiValuesForBox(int row,int col){
        ArrayList<Integer> temp=new ArrayList<>();
        // gives top left corner of the box to be searched
        int tlx=col/3*3;
        int tly=row/3*3;
        for(int i=tly;i<tly+3;i++)
            for(int j=tlx;j<tlx+3;j++){
                if(grid[i][j].getValue()!=0)
                    temp.add(Integer.valueOf(grid[i][j].getValue()));
            }
        return temp;
    }
    
    // checks all the directions around a spot for a possible solution
    public boolean checkAllForSpot(SudokuCoordinate point){
        if(checkRowForSingleNumber(point.getYpos()))
            return true;
        if(checkColumnForSingleNumber(point.getXpos()))
            return true;
        if(checkBoxForSingleNumber(point.getYpos(),point.getXpos()))
            return true;
        return false;
    }
    
    // checks the row if there is a single number in one spot O(n)
    public boolean checkRowForSingleNumber(int row){
        int[] possibilities={1,1,1,1,1,1,1,1,1};
        for(int i=0;i<9;i++)
            if(grid[row][i].getValue()==0) // meaning it does not have a value
                for(Integer num : grid[row][i].getPossibleValues())
                    possibilities[num-1]--;
        System.out.println("hadfhasdfhadsf");
        for(int i=0;i<9;i++)
            if(possibilities[i]==0){
                System.out.println("hadfhasdfhadsf");
                grid[row][i].setValue(i+1);
                recalculateValues(row,i);
                return true;
            }   
        return false;
    }
    
    // checks the col if there is a single number in one spot O(n)
    public boolean checkColumnForSingleNumber(int col){ // O(n^3) if works, O(n) if not
        int[] possibilities={1,1,1,1,1,1,1,1,1};
        for(int i=0;i<9;i++)
            if(grid[i][col].getValue()==0)
                for(Integer num : grid[i][col].getPossibleValues())
                    possibilities[num-1]--;
        for(int i=0;i<9;i++)
            if(possibilities[i]==0){
                grid[i][col].setValue(i+1);
                recalculateValues(i,col);
                return true;
            }
        return false;
    }
    
    // checks the box if there is a single number in one spot O(n)
    public boolean checkBoxForSingleNumber(int row,int col){
        int[] possibilities={1,1,1,1,1,1,1,1,1};
        int tlx=col/3*3;
        int tly=row/3*3;
        for(int i=tly;i<tly+3;i++)
            for(int j=tlx;j<tlx+3;j++)
                for(Integer num : grid[i][j].getPossibleValues())
                    possibilities[num-1]--;
        for(int i=tly;i<tly+3;i++)
            for(int j=tlx;j<tlx+3;j++)
                if(possibilities[i*3+j]==0){
                    grid[i][j].setValue(i*3+j+1);
                    recalculateValues(row,col);
                    return true;
                }
        return false;
    }
    
    public void recalculateValues(SudokuCoordinate point){
        recalculateValues(point.getYpos(),point.getXpos());
    }
    
    // recalculates the values of the surrounding points after a value has been calculated
    public void recalculateValues(int row,int col){
        recalculateRow(row);
        recalculateCol(col);
        recalculateBox(row,col);
    }
    
    // recalculates the values of a row
    public void recalculateRow(int row){ // O(7n^2)
        for(int i=0;i<9;i++)
            if(grid[row][i].getValue()==0)
                getValuesForCoordinate(grid[row][i]);
    }
    
    // recalculates the values of a collumn
    public void recalculateCol(int col){ // O(7n^2)
        for(int i=0;i<9;i++)
            if(grid[i][col].getValue()==0)
                getValuesForCoordinate(grid[i][col]);
    }
    
    // recalculates the values of a box
    public void recalculateBox(int row,int col){ // O(7n^2)
        int tlx=col/3*3;
        int tly=row/3*3;
        for(int i=tly;i<tly+3;i++)
            for(int j=tlx;j<tlx+3;j++)
                if(grid[i][j].getValue()==0)
                    getValuesForCoordinate(grid[i][j]);
    }
    
    // a method to return the grid as a string
    @Override
    public String toString(){
        String temp="";
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++)
                temp+=grid[i][j].toString();
            temp+="\n";
        }
        return temp;
    }
    
    // getter methods
    public SudokuCoordinate[][] getGrid(){return grid;}
    public ArrayList<SudokuCoordinate> getUnsolved(){return unsolved;}
    
    // there is no need for setter methods
}