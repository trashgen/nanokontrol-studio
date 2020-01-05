package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.SettableBeatTimeValue;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class BackwardButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 58;

    public BackwardButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        if (cc.isSetPressed()) {
            SettableBeatTimeValue value = cc.transport.getInPosition();
            value.set(cc.transport.getPosition().get());
        }
    }
}
