/**
 * MazeLocationList.java
 * Created for CSC 115 Assignment Three
 *
 * The list here has fewer operations than the usual List ADT.
 * For our maze pathfinding algorithm, we need only add information
 * on maze square at the end (and also only neeed remove from
 * the end).
 */

/**
 * An interface implemented by all MazeLocationList implementations.
 */
public interface MazeLocationList {
    /** 
     * Examines the MazeLocation list; if there are no items
     * returnst true, otherwise false.
     * @return whether or not the list contains MazeLocation nodes
     */
    public boolean isEmpty();


    /** 
     * Either retrieves or computes the number of MazeLocations
     * currently stored in the list
     * @return number greater than or equal to 0 corresponding
     * to number of items in the list.
     */
    public int  getLength();


    /**
     * If the list has at least one MazeLocation, then the
     * MazeLocation at the end of the list (the tail) is removed.
     * If there are no items in the list, then the value returned
     * is null.
     * @return a MazeLocation object corresponding the the MazeLocation
     * at the tail of the list before removal; possibly null.
     */
    public MazeLocation removeTail();


    /** 
     * Accepts a MazeLocation to be added to the end of the
     * list (i.e., after the current ail.)
     * @param loc MazeLocation to be placed at the end of the list.
     */
    public void insertTail(MazeLocation loc);

    /** 
     * Takes an integer value indicate that the ith MazeLocation
     * in the list is to be returned. When i is 0, the first
     * MazeLocation is returned, when i is 1, the second is
     * returned, etc. If there is no such MazeLocation i, then null
     * is returned. The list is not modified by this operation.
     * @param i indicates the number of the item from the start of
     * of the list which will be the MazeLocation returned
     * @return contents of the ith item in the list; possibly null
     */ 
    public MazeLocation retrieve(int i);
}
