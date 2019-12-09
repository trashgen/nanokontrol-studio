package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SettableBooleanValue;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class MuteButton extends SimpleButton {

    final static int BUTTON_ID_FIRST = 21;
    final static int BUTTON_ID_LAST = 28;

    private int index;
    private ControllerContext controllerContext;

    MuteButton(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(transport, trackBank, cursorTrack);
        this.index = index;
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        SettableBooleanValue mute = trackBank.getItemAt(index).mute();
        mute.toggle();
        controllerContext.toggleLED(BUTTON_ID_FIRST + index, mute.get());
        if (controllerContext.isSetPressed()) {
            for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
                if ((i - BUTTON_ID_FIRST) != index) {
                    trackBank.getItemAt(i).mute().set(false);
                }
            }
            for (int i = BUTTON_ID_FIRST; i <= BUTTON_ID_LAST; i++) {
                if ((i - BUTTON_ID_FIRST) != index) {
                    controllerContext.ledOFF(i);
                }
            }
        }
    }
}
