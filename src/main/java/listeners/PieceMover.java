package listeners;

import chessmodel.Board;
import chessmodel.piece.Piece;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import view.Window;

import java.awt.Point;

public class PieceMover implements EventHandler<MouseEvent> {

    private Board b;
    private Piece p;

    public PieceMover(Board b){
        this.b = b;
    }

    @Override
    public void handle(MouseEvent mE) {
        Point pos;
        switch (mE.getEventType()+""){
            case "MOUSE_PRESSED":
                pos = Window.toCoordinates(new Point((int) mE.getX(),(int) mE.getY()));
                p =  b.getPiece(pos);
                break;
            case "MOUSE_RELEASED":
                pos = Window.toCoordinates(new Point((int) mE.getX(),(int) mE.getY()));
                if (p != null)
                    b.movePiece(p, pos);
                break;
            case "MOUSE_DRAGGED":
                break;
                default:

        }
    }



}
