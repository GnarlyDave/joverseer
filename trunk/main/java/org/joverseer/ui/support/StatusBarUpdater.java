package org.joverseer.ui.support;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.richclient.application.Application;
import org.joverseer.ui.LifecycleEventsEnum;
import org.joverseer.game.Game;
import org.joverseer.support.GameHolder;
import org.joverseer.metadata.GameMetadata;


public class StatusBarUpdater implements ApplicationListener {
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof JOverseerEvent) {
            JOverseerEvent e = (JOverseerEvent)applicationEvent;
            if (e.getEventType().equals(LifecycleEventsEnum.GameChangedEvent.toString())) {
                refreshGameInfo();
            } else if (e.getEventType().equals(LifecycleEventsEnum.SelectedTurnChangedEvent.toString())) {
                refreshGameInfo();
            }
        }
    }

    private void refreshGameInfo() {
        String msg = null;
        Game game = ((GameHolder)Application.instance().getApplicationContext().getBean("gameHolder")).getGame();
        if (game == null) {
            msg = "No active game.";
        } else {
            GameMetadata gm = game.getMetadata();
            msg = "Game %s (%s), %s, Turn %s";
            msg = String.format(msg, gm.getGameNo(), gm.getGameType().toString(), gm.getNationByNum(gm.getNationNo()).getName(), game.getCurrentTurn());
            if (game.getTurn() != null && game.getTurn().getSeason() != null) {
                msg = String.format("%1$s, %2$s, %3$td %3$tb %3$tY", msg, game.getTurn().getSeason(), game.getTurn().getTurnDate());
            }
        }
        //Application.instance().getActiveWindow().getStatusBar().setMessage(msg);
        Application.instance().getActiveWindow().getControl().setTitle("JOverseer - " + msg);
    }
}
