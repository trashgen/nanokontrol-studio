package ru.utoplov.vladimir.controlset.buttonset.device;

import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class NextMarkerDeviceButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 57;

    public NextMarkerDeviceButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        cc.cursorDevice.selectNext();
        Boolean isNested = cc.cursorDevice.isNested().get();
        String name = cc.cursorDevice.name().get().toLowerCase();
        if (name.equals("fabfilter one")) {
            cc.cursorDevice.hashCode();
        }
    }
}
