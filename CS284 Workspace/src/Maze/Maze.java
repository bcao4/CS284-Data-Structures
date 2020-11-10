// Brandon Cao

package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
    	if (x < 0 || x > maze.getNCols() - 1 || y < 0 || y > maze.getNRows() - 1 || maze.getColor(x, y) != NON_BACKGROUND) {
    		return false;
    	}
    	if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
    		maze.recolor(x, y, PATH);
    		return true;
    	} else {
    		maze.recolor(x, y, TEMPORARY);
    		if (findMazePath(x, y + 1) || findMazePath(x, y - 1) || findMazePath(x + 1, y) || findMazePath(x - 1, y)) {
    			maze.recolor(x, y, PATH);
    			return true;
    		} else {
    			maze.recolor(x, y, TEMPORARY);
    			return false;
    		}
    	}
    	
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    /* lists of all the solutions to the maze */
    
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	if (x < 0 || x > maze.getNCols() - 1 || y < 0 || y > maze.getNRows() - 1 || maze.getColor(x, y) != NON_BACKGROUND) {
    		return;
    	} else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			ArrayList<PairInt> temp = new ArrayList<PairInt>();
    		PairInt current = new PairInt(x,y);
			trace.push(current);
			temp.addAll(trace);
			result.add(temp);
			trace.pop();
    	} else {
    		PairInt current = new PairInt(x,y);
			trace.push(current);
			maze.recolor(x, y, PATH);
			findMazePathStackBased(x, y + 1, result, trace);
			findMazePathStackBased(x, y - 1, result, trace);
			findMazePathStackBased(x + 1, y, result, trace);
			findMazePathStackBased(x - 1, y, result, trace);
			trace.pop();
			maze.recolor(x, y, NON_BACKGROUND);
    	}
    }

    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	ArrayList < ArrayList < PairInt >> result = new ArrayList < >();
    	Stack < PairInt > trace = new Stack < >();
    	findMazePathStackBased (0 ,0 , result , trace );
    	return result;
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    /* returns the shortest path in the list of paths. */
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
    	ArrayList<ArrayList<PairInt>> result = findAllMazePaths(0, 0);
    	ArrayList<PairInt> temp;
    	int minSize = 0;
    	int i = 0;
    	int minIndex = 0;

    	if(result == null || result.size() == 0)
    		return null;
    	for(i = 0; i < result.size(); i++) {
    		temp = result.get(i);
    		if (minSize == 0) {
    		    minSize = temp.size();
    		}
    		else if(temp.size() < minSize) {
    			minSize = temp.size();
    			minIndex = i;
    		}
    	}
    	return result.get(minIndex);
    }    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
