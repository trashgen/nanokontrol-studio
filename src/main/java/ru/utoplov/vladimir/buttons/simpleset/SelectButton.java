package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class SelectButton extends SimpleButton {

    final static int BUTTON_ID_FIRST = 46;
    final static int BUTTON_ID_LAST = 53;

    private int index;
    private ControllerContext controllerContext;

    SelectButton(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(transport, trackBank, cursorTrack);
        this.index = index;
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        trackBank.getItemAt(index).selectInMixer();
        controllerContext.ledON(BUTTON_ID_FIRST + index);
        for (int i = BUTTON_ID_FIRST; i <= BUTTON_ID_LAST; i++) {
            if ((i - BUTTON_ID_FIRST) != index) {
                controllerContext.ledOFF(i);
            }
        }
    }

}
