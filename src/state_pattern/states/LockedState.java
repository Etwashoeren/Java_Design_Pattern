package state_pattern.states;

import state_pattern.ui.Player;

public class LockedState extends State{

    LockedState(Player player){
        super(player);
        player.setPlaying(false);
    }

    @Override
    public String onLock() {
        if(player.isPlaying()){
            player.changeState(new ReadyState(player));
            return "Stop Playing";
        }
        else {
            return "Locked...";
        }
    }

    @Override
    public String onPlay() {
        player.changeState(new ReadyState(player));
        return "Ready";
    }

    @Override
    public String onNext() {
        return "Locked...";
    }

    @Override
    public String onPrevious() {
        return "Locked...";
    }
}
