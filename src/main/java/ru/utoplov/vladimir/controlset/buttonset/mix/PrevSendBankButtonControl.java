package ru.utoplov.vladimir.controlset.buttonset.mix;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class PrevSendBankButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 56;

    public PrevSendBankButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        if (cc.isSetPressed()) {
            cc.cursorTrack.sendBank().scrollBackwards();
        } else {
            cc.cursorTrack.sendBank().scrollPageBackwards();
        }
    }
}
