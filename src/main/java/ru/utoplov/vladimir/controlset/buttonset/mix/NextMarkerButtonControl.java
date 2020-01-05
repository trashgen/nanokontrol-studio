package ru.utoplov.vladimir.controlset.buttonset.mix;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class NextMarkerButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 57;

    public NextMarkerButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        if (cc.isSetPressed()) {
            cc.cursorTrack.sendBank().scrollForwards();
        } else {
            cc.cursorTrack.sendBank().scrollPageForwards();
        }
    }
}
