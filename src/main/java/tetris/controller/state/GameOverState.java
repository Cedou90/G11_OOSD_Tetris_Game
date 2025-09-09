package tetris.controller.state;

import tetris.common.Action;
import tetris.common.GameState;
import tetris.controller.game.GameController;
import tetris.model.score.HighScoreManager;

public class GameOverState implements PlayState {
    @Override public void start(GameController c) { 
        // Save the score when game over occurs using ScoreController
        int finalScore = c.getCurrentScore();
        if (finalScore > 0) {
            boolean isHighScore = c.submitFinalScore("Player");
            System.out.println("Game Over! Final Score: " + finalScore);
            if (isHighScore) {
                System.out.println("New High Score!");
            }
            System.out.println(HighScoreManager.getInstance());
        }
    }
    @Override public void tick(GameController c)  { /* no tick */ }
    @Override public void handle(GameController c, Action action) { /* ignore */ }
    @Override public void togglePause(GameController c) { /* no-op */ }
    @Override public void restart(GameController c) {
        c.board().reset();
        // Restore the correct player type state
        c.setPlayerType(c.getPlayerType()); // This recreates the appropriate state
        c.getState().start(c);
    }
    @Override public void reset(GameController c) { c.board().reset(); }
    @Override public GameState uiState() { return GameState.GAME_OVER; }
}
