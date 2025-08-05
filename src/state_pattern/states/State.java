package state_pattern.states;

import state_pattern.ui.Player;

/*
공통 상태 인터페이스
 */
public abstract class State {
    Player player;

    /*
    Context는 자기 자신을 State 생성자에게 전달.
    이렇게 하면, State가 필요할 때 Context로부터 유용한 데이터를 가져올 수 있다.
     */
    State(Player player) {
        this.player = player;
    }

    public abstract String onLock();
    public abstract String onPlay();
    public abstract String onNext();
    public abstract String onPrevious();
}
