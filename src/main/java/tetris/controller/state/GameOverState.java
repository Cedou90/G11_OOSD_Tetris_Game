package tetris.controller.state;

import tetris.common.Action;
import tetris.common.GameState;
import tetris.controller.game.GameController;
import tetris.score.HighScoreManager;

public class GameOverState implements PlayState {
    @Override public void start(GameController c) { 
        // Save the score when game over occurs
        int finalScore = c.getScore();
        HighScoreManager.getInstance().addScore("Player", finalScore);
        System.out.println("Game Over! Final Score: " + finalScore);
        System.out.println(HighScoreManager.getInstance());
    }
    @Override public void tick(GameController c)  { /* no tick */ }
    @Override public void handle(GameController c, Action action) { /* ignore */ }
    @Override public void togglePause(GameController c) { /* no-op */ }
    @Override public void restart(GameController c) {
        c.board().reset();
        c.resetScore();
        c.setState(new PlayingState());
        c.getState().start(c);
    }
    @Override public void reset(GameController c) { c.board().reset(); }
    @Override public GameState uiState() { return GameState.GAME_OVER; }
}
