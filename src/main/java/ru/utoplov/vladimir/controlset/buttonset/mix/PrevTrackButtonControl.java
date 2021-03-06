package ru.utoplov.vladimir.controlset.buttonset.mix;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class PrevTrackButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 60;

    public PrevTrackButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        if (cc.isSetPressed()) {
            cc.trackBank.scrollPageBackwards();
        } else {
            cc.trackBank.scrollBackwards();
        }
    }
}
