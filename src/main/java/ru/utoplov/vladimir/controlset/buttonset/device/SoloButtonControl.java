package ru.utoplov.vladimir.controlset.buttonset.device;

import com.bitwig.extension.controller.api.SoloValue;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class SoloButtonControl extends ButtonControl {

    public final static int BUTTON_ID_FIRST = 29;
    public final static int BUTTON_ID_LAST = 36;

    private int index;

    public SoloButtonControl(ControllerContext cc, int index) {
        super(cc);
        this.index = index;
    }

    @Override
    protected void logic() {
        SoloValue solo = cc.trackBank.getItemAt(index).solo();
        if (cc.isSetPressed()) {
            solo.toggle(true);
            for (int i = BUTTON_ID_FIRST; i <= BUTTON_ID_LAST; i++) {
                if ((i - BUTTON_ID_FIRST) != index) {
                    cc.ledOFF(i);
                }
            }
        } else {
            solo.toggle();
        }
        boolean val = solo.get();
        cc.toggleLED(BUTTON_ID_FIRST + index, val);
    }

}
