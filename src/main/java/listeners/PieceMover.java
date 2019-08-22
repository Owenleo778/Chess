package listeners;

import chessmodel.Board;
import chessmodel.piece.Piece;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.Window;

import java.awt.Point;

public class PieceMover implements EventHandler<MouseEvent> {

    private Window w;
    private Piece p;

    public PieceMover(Window w){
        this.w = w;
    }

    @Override
    public void handle(MouseEvent mE) {
        Point pos;
        switch (mE.getEventType()+""){
            case "MOUSE_PRESSED":
                pos = Window.toCoordinates(new Point((int) mE.getX(),(int) mE.getY()));
                p =  w.getBoard().getPiece(pos);
                if (p != null)
                    w.setClosedHand();
                break;
            case "MOUSE_RELEASED":
                pos = Window.toCoordinates(new Point((int) mE.getX(),(int) mE.getY()));
                if (p != null && Board.inRange(pos)) {
                    Piece p2 = null;
                    if (!w.getBoard().isEmptySpace(pos))
                        p2 = w.getBoard().getPiece(pos);

                    if (w.getBoard().movePiece(p, pos)){
                        w.nextTurn();
                        if (p2 != null)
                            w.removePiece(p2);
                    }

                    p = null;
                    w.setOpenHand();
                }
                break;
            case "MOUSE_DRAGGED": // to make it look pretty
                break;
            case "MOUSE_ENTERED":
                if (p == null)
                    w.setOpenHand();
                break;
                default:

        }
    }



}
