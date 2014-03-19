/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
public class SudokuCoordinate{
    private SudokuGrid reference; // the reference to the Sudoku grid
    private ArrayList<Integer> possibleValues; // the possible values for the coordinate
    private int value; // the value for the spot (0 is default)
    private int xpos; // the x position in the grid
    private int ypos; // the y position in the grid
    private boolean update; // if this coordinates possible values need to update
    
    public SudokuCoordinate(){ // default constructor for making copies
        update=false;
        value=0;
        possibleValues=new ArrayList<Integer>();
        reference=null;
    }
    
    // constructor with initial position
    public SudokuCoordinate(SudokuGrid grid,int x,int y){
        this(grid,x,y,0);
    }
    
    // constructor with initial position and value
    public SudokuCoordinate(SudokuGrid grid,int x,int y,int val){
        reference=grid;
        possibleValues=new ArrayList<>();
        value=val;
        xpos=x;
        ypos=y;
        update=false;
    }
    
    // DEPRECIATED
    // gets the list of possible values
    //public ArrayList<Integer> getPossibleValues(){
    //    if(update){
    //        // implement call to update
    //        update=false;
    //    }
    //    return possibleValues;
    //}
    
    // returns a complete copy of this coordinate
    public SudokuCoordinate copy(){
        SudokuCoordinate replica=new SudokuCoordinate();
        replica.setXpos(xpos);
        replica.setYpos(ypos);
        for(Integer in:possibleValues)
            replica.getPossibleValues().add(new Integer(in.intValue()));
        return replica;
    }
    
    // returns the value of this point as a string
    @Override
    public String toString(){
        return ""+value;
    }
    
    public String debug(){
        return xpos+","+ypos+" "+value;
    }
    
    // getter methods
    public SudokuGrid getReference(){return reference;}
    public ArrayList<Integer> getPossibleValues(){return possibleValues;}
    public int getValue(){return value;}
    public int getXpos(){return xpos;}
    public int getYpos(){return ypos;}
    public boolean getUpdate(){return update;}
    
    // setter methods
    public void setReference(SudokuGrid param){reference=param;}
    public void setPossibleValues(ArrayList<Integer> param){possibleValues=param;}
    public void setValue(int param){value=param;}
    public void setXpos(int param){xpos=param;}
    public void setYpos(int param){ypos=param;}
    public void setUpdate(boolean param){update=param;}
}
