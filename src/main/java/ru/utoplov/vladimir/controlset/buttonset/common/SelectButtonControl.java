package ru.utoplov.vladimir.controlset.buttonset.common;

import com.bitwig.extension.controller.api.SettableEnumValue;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class SelectButtonControl extends ButtonControl {

    public final static int BUTTON_ID_FIRST = 46;
    public final static int BUTTON_ID_LAST = 53;

    private int index;

    public SelectButtonControl(ControllerContext cc, int index) {
        super(cc);
        this.index = index;
    }

    @Override
    protected void logic() {
        if (cc.isSetPressed()) {
            for (int i = 0; i < cc.cursorTrack.sendBank().getSizeOfBank(); i++) {
                SettableEnumValue sendMode = cc.cursorTrack.sendBank().getItemAt(i).sendMode();
                sendMode.set("PRE");
            }
        } else {
            cc.trackBank.getItemAt(index).selectInMixer();
            cc.trackBank.getItemAt(index).selectInEditor();
            cc.ledON(BUTTON_ID_FIRST + index);
            for (int i = BUTTON_ID_FIRST; i <= BUTTON_ID_LAST; i++) {
                if ((i - BUTTON_ID_FIRST) != index) {
                    cc.ledOFF(i);
                }
            }
        }
    }

}
