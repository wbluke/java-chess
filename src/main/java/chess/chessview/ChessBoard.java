package chess.chessview;

import chess.application.chessround.dto.ChessPieceDTO;
import chess.application.chessround.dto.ChessPlayerDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChessBoard implements Iterable<RowOfChessBlocks> {
    static final int LENGTH_OF_CHESS_BOARD_SIDE = 8;
    private List<RowOfChessBlocks> chessRows;

    private ChessBoard(List<RowOfChessBlocks> chessRows) {
        this.chessRows = chessRows;
    }

    public static ChessBoard createEmpty() {
        List<RowOfChessBlocks> chessRows = new ArrayList<>();
        for (int blockRow = 0; blockRow < 8; blockRow++) {
            chessRows.add(RowOfChessBlocks.createEmptyOf(blockRow));
        }
        return new ChessBoard(chessRows);
    }

    public void fillWhiteChessPiecesOfPlayer(ChessPlayerDTO chessPlayerDTO) {
        fillChessPiecesOfPlayer(chessPlayerDTO, true);
    }

    public void fillBlackChessPiecesOfPlayer(ChessPlayerDTO chessPlayerDTO) {
        fillChessPiecesOfPlayer(chessPlayerDTO, false);
    }

    private void fillChessPiecesOfPlayer(ChessPlayerDTO chessPlayerDTO, boolean isWhitePlayer) {
        for (ChessPieceDTO chessPieceDTO : chessPlayerDTO.getChessPieceDTOs()) {
            fillChessPieceTo(chessPieceDTO, isWhitePlayer);
        }
    }

    private void fillChessPieceTo(ChessPieceDTO chessPieceDTO, boolean isWhitePlayer) {
        int row = chessPieceDTO.getRow();

        RowOfChessBlocks chessRow = chessRows.get(remapBlockRowFrom(row));
        chessRow.fillChessPieceTo(chessPieceDTO, isWhitePlayer);
    }

    private int remapBlockRowFrom(int row) {
        return LENGTH_OF_CHESS_BOARD_SIDE - row;
    }

    @Override
    public Iterator<RowOfChessBlocks> iterator() {
        return chessRows.iterator();
    }
}
