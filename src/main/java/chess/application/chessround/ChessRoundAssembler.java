package chess.application.chessround;

import chess.application.chessround.dto.ChessPieceDTO;
import chess.application.chessround.dto.ChessPlayerDTO;
import chess.application.chessround.dto.ChessPointDTO;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspoint.ChessPoint;
import chess.domain.chessround.ChessPlayer;

import java.util.ArrayList;
import java.util.List;

class ChessRoundAssembler {
    private static ChessRoundAssembler chessRoundAssembler = null;

    private ChessRoundAssembler() {
    }

    static ChessRoundAssembler getInstance() {
        if (chessRoundAssembler == null) {
            chessRoundAssembler = new ChessRoundAssembler();
        }
        return chessRoundAssembler;
    }

    ChessPlayerDTO makeChessPlayerDTO(ChessPlayer whitePlayer) {
        List<ChessPieceDTO> chessPieceDTOs = new ArrayList<>();

        for (ChessPoint point : whitePlayer.getAllChessPoints()) {
            ChessPiece chessPiece = whitePlayer.get(point);
            ChessPieceDTO chessPieceDTO = makeChessPieceDTO(point, chessPiece);
            chessPieceDTOs.add(chessPieceDTO);
        }
        return new ChessPlayerDTO(chessPieceDTOs);
    }

    ChessPieceDTO makeChessPieceDTO(ChessPoint point, ChessPiece piece) {
        return new ChessPieceDTO(point.getRow(), point.getColumn(), piece.getName());
    }

    ChessPointDTO makeChessPointDTO(ChessPoint point) {
        return new ChessPointDTO(point.getRow(), point.getColumn());
    }
}
