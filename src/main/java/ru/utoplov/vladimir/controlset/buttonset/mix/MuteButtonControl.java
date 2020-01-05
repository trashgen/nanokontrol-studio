package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.SettableBooleanValue;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class MuteButtonControl extends ButtonControl {

    public final static int BUTTON_ID_FIRST = 21;
    public final static int BUTTON_ID_LAST = 28;

    private int index;

    public MuteButtonControl(ControllerContext cc, int index) {
        super(cc);
        this.index = index;
    }

    @Override
    protected void logic() {
        SettableBooleanValue mute = cc.trackBank.getItemAt(index).mute();
        mute.toggle();
        cc.toggleLED(BUTTON_ID_FIRST + index, mute.get());
        if (cc.isSetPressed()) {
            for (int i = 0; i < cc.trackBank.getSizeOfBank(); i++) {
                if ((i - BUTTON_ID_FIRST) != index) {
                    cc.trackBank.getItemAt(i).mute().set(false);
                }
            }
            for (int i = BUTTON_ID_FIRST; i <= BUTTON_ID_LAST; i++) {
                if ((i - BUTTON_ID_FIRST) != index) {
                    cc.ledOFF(i);
                }
            }
        }
    }
}
