package ru.utoplov.vladimir.controlset.buttonset.device;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class RewindDeviceButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 62;

    public RewindDeviceButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        cc.cursorDevice.isWindowOpen().toggle();
    }

}
