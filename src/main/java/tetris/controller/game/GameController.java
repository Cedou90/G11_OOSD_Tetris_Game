package tetris.controller.game;

import tetris.common.Action;
import tetris.common.GameState;
import tetris.controller.api.IGameController;
import tetris.controller.state.PlayState;
import tetris.controller.state.PlayingState;
import tetris.model.IGameBoard;

public class GameController implements IGameController {
    private final IGameBoard board;
    private PlayState state = new PlayingState();
    private int score = 0;

    public GameController(IGameBoard board) {this.board = board;}

    public void setState(PlayState next) { this.state = next; }
    public PlayState getState() { return state; }

    public int getScore() { return score; }
    
    public void addScore(int points) { 
        score += points; 
    }
    
    public void resetScore() { 
        score = 0; 
    }

    @Override public IGameBoard board() {return board;}
    @Override public GameState state() {return state.uiState();}

    @Override public void handle(Action action) { state.handle(this, action); }
    @Override public void start() { state.start(this); }
    @Override public void togglePause() { state.togglePause(this); }
    @Override public void restart() { state.restart(this); }
    @Override public void reset() { state.reset(this); }
    @Override public void tick() { state.tick(this); }
}
