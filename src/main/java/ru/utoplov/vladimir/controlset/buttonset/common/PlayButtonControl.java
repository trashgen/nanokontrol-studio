package ru.utoplov.vladimir.controlset.buttonset.common;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class PlayButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 80;

    public PlayButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        if (cc.isSetPressed()) {
            cc.ArrangementPosition = cc.transport.getPosition().get();
        }
        cc.transport.setPosition(cc.ArrangementPosition);
        cc.transport.play();
        cc.toggleLED(BUTTON_ID, cc.transport.isPlaying().get());
    }

}