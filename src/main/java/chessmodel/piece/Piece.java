package chessmodel.piece;

import chessmodel.Board;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import view.Window;

import java.awt.Point;
import java.util.ArrayList;


/**
 * A class that represents a chess piece. The colour is represented by an integer, so that it can also be used
 * for moving purposes e.g using pos.y + colour to determine how a pawn would move.
 * @author Owen Salvage
 */
public abstract class Piece {

    private Colour colour;
    private Point pos;
    private ImageView image;
    private boolean removed;
    private boolean moved;

    public Piece(Colour colour, Image src){
        this(colour, null, src);
    }

    public Piece(Colour colour, Point pos, Image src){
        moved = false;
        removed = false;
        this.colour = colour;
        image = new ImageView(src);
        image.setFitWidth(view.Window.TILE_SIZE);
        image.setFitHeight(view.Window.TILE_SIZE);
        setPos(pos);
        //image.relocate(100,100);
    }

    /**
     * Returns a list of all possible moves
     * @param board the position of all pieces in play
     * @param p the position of the piece
     * @return the list of possible moves
     */
    public abstract ArrayList<Point> getMoves(Piece[][] board, Point p);

    /**
     * Returns true if this piece can move to the specified position. Is only called after both points are verified
     * @param board the class holding information about the pieces
     * @param p the position to move to
     * @return true if it can make the move, false otherwise
     */
    public  abstract boolean canMove(Board board, Point p);

    /**
     * Returns true if the point supplied is empty or is occupied by an enemy piece.
     * SHOULD NOT BE USED FOR THE PAWN CLASS WHEN MOVING FORWARD.
     * @param board the board class containing the information of the other pieces
     * @param p the point to check
     * @return true if it can safely move there, false otherwise
     */
    protected boolean validEndPos(Board board, Point p){
        return board.isEmptySpace(p) || board.getColour(p) != getColour();
    }

    /**
     * Checks if all the spaces between the two points are empty ((he final point can also be occupied by an opposing piece)
     * @param board the Board containing all of the Piece information
     * @param p1 the start point
     * @param p2 the end point
     * @return true if it's clear, false otherwise
     */
    protected boolean emptyStraight(Board board, Point p1, Point p2) {
        int xDiff = p2.x - p1.x;
        int yDiff = p2.y - p1.y;
        int xDir;
        int yDir;
        if (xDiff == 0){
            xDir = 0;
        } else {
            xDir = xDiff / Math.abs(xDiff);
        }
        if (yDiff == 0) {
            yDir = 0;
        } else {
            yDir = yDiff / Math.abs(yDiff);
        }

        int max = Math.max( Math.abs(xDiff),  Math.abs(yDiff));
        for (int i = 1; i < max; i++) { // Checks all spaces in between are empty
            if (!board.isEmptySpace(p1.x + i * xDir, p1.y + i * yDir))
                return false;
        }
        return validEndPos(board, p2);
    }

    /**
     * Sets the position of the piece
     * @param p the position
     */
    public void setPos(Point p){
        pos = p;
        if (p != null)
            image.relocate(p.x * Window.TILE_SIZE, p.y * Window.TILE_SIZE);
    }

    /**
     * Returns the colour of the piece
     * @return the colour
     */
    public Colour getColour(){
        return colour;
    }

    /**
     * Returns the position of the piece
     * @return the position
     */
    public Point getPos(){
        return pos;
    }

    /**
     * Returns the image that depicts the piece
     * @return the image
     */
    public ImageView getImage(){
        return image;
    }

    /**
     * Sets this piece to be 'removed
     */
    public void remove(){
        removed = true;
    }

    /**
     * Returns whether this piece has been removed or not
     * @return true if it has been removed
     */
    public boolean isRemoved(){
        return removed;
    }

    /**
     * Returns whether this piece has been moved yet
     * @return true if it has been moved
     */
    public boolean hasMoved(){
        return moved;
    }

    /**
     * Sets whether this piece has moved or not
     * @param m true if it has moved
     */
    public void setMoved(boolean m){
        moved = m;
    }

}
