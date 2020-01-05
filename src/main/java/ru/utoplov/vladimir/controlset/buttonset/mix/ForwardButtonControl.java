package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.SettableBeatTimeValue;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class ForwardButtonControl extends ButtonControl {

    public final static int BUTTON_ID = 59;

    public ForwardButtonControl(ControllerContext cc) {
        super(cc);
    }

    @Override
    protected void logic() {
        if (cc.isSetPressed()) {
            SettableBeatTimeValue beatValue = cc.transport.getOutPosition();
            beatValue.set(cc.transport.getPosition().get());
            cc.ArrangementPosition = cc.transport.getInPosition().get();
            cc.transport.setPosition(cc.transport.getInPosition().get());
        }
    }
}
