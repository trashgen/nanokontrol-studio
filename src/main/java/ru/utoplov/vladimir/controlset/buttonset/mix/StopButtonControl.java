package ru.utoplov.vladimir.controlset.buttonset.mix;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class StopButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 63;

    public StopButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        cc.transport.stop();
        cc.ledOFF(PlayButtonControl.BUTTON_ID);
        cc.ledOFF(RecordButtonControl.BUTTON_ID);
        if (cc.isSetPressed()) {
            cc.ArrangementPosition = 0;
        }
    }
}
