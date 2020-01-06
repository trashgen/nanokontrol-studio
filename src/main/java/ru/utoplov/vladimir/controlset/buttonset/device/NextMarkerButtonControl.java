package ru.utoplov.vladimir.controlset.buttonset.device;

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
            cc.cursorDevice.selectFirstInSlot("FX");
            cc.cursorDevice.selectFirstInSlot("Wet FX");
        } else {
            cc.cursorDevice.selectNext();
        }
    }
}
