package ru.utoplov.vladimir.controlset.buttonset.device;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class CycleStateButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 54;

    public CycleStateButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        cc.cursorDevice.isWindowOpen().toggle();
    }
}
