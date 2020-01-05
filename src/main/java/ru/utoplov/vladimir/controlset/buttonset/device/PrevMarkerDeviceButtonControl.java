package ru.utoplov.vladimir.controlset.buttonset.device;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class PrevMarkerDeviceButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 56;

    public PrevMarkerDeviceButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        cc.cursorDevice.selectPrevious();
        String name = cc.cursorDevice.name().get();
        if (name.equals("FABFILTER ONE")) {
            cc.cursorDevice.hashCode();
        }
    }
}
