package ru.utoplov.vladimir.controlset.buttonset.mix;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SettableEnumValue;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.controlset.buttonset.ButtonControl;
import ru.utoplov.vladimir.core.ControllerContext;

public class SelectButtonControl extends ButtonControl {

    public final static int BUTTON_ID_FIRST = 46;
    public final static int BUTTON_ID_LAST = 53;

    private int index;
    private ControllerContext controllerContext;

    public SelectButtonControl(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(transport, trackBank, cursorTrack);
        this.index = index;
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        if (controllerContext.isSetPressed()) {
            for (int i = 0; i < cursorTrack.sendBank().getSizeOfBank(); i++) {
                SettableEnumValue sendMode = cursorTrack.sendBank().getItemAt(i).sendMode();
                sendMode.set("PRE");
            }
        } else {
            trackBank.getItemAt(index).selectInMixer();
            trackBank.getItemAt(index).selectInEditor();
            controllerContext.ledON(BUTTON_ID_FIRST + index);
            for (int i = BUTTON_ID_FIRST; i <= BUTTON_ID_LAST; i++) {
                if ((i - BUTTON_ID_FIRST) != index) {
                    controllerContext.ledOFF(i);
                }
            }
        }
    }

}
