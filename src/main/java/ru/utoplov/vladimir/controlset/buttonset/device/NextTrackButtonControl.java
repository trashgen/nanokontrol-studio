package ru.utoplov.vladimir.controlset.buttonset.device;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class NextTrackButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 61;

    public NextTrackButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        cc.nextRemoteControls();
    }
}
