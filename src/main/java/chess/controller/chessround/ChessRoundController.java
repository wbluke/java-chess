package chess.controller.chessround;

import chess.application.chessround.ChessRoundService;
import chess.domain.chessround.dto.ChessPlayerDTO;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class ChessRoundController {
    public static final String PATH_CHESS_ROUND = "/chess-round";

    public static final Route fetchChessRound = (req, res) -> {
        Map<String, Object> model = new HashMap<>();

        ChessBoard chessBoard = ChessBoard.createEmpty();

        ChessRoundService chessRoundService = ChessRoundService.getInstance();

        ChessPlayerDTO whitePlayerDTO = chessRoundService.fetchWhitePlayer();
        chessBoard.fillWhiteChessPiecesOfPlayer(whitePlayerDTO);

        ChessPlayerDTO blackPlayerDTO = chessRoundService.fetchBlackPlayer();
        chessBoard.fillBlackChessPiecesOfPlayer(blackPlayerDTO);

        model.put("chess-blocks", chessBoard);
        model.put("current-turn", chessRoundService.isWhiteTurn() ? "White" : "Black");
        model.put("white-score", chessRoundService.getWhitePlayerScore());
        model.put("black-score", chessRoundService.getBlackPlayerScore());
        model.put("error-message", chessRoundService.getErrorMessage());
        return new HandlebarsTemplateEngine().render(
                new ModelAndView(model, "index.hbs")
        );
    };

    public static final Route handleChessMove = (req, res) -> {
        String sourceId = req.queryParams("source-id");
        String targetId = req.queryParams("target-id");

        ChessRoundService chessRoundService = ChessRoundService.getInstance();
        chessRoundService.move(sourceId, targetId);

        res.redirect("/chess-round");
        return null;
    };

    private ChessRoundController() {
    }
}
