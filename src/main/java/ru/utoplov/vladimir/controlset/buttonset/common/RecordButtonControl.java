package ru.utoplov.vladimir.controlset.buttonset.common;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class RecordButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 81;

    public RecordButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        cc.transport.record();
        cc.toggleLED(BUTTON_ID, cc.transport.isArrangerRecordEnabled().get());
    }
}
