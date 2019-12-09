package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.SoloValue;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

public class SoloButton extends SimpleButton {

    final static int BUTTON_ID_FIRST = 29;
    final static int BUTTON_ID_LAST = 36;

    private int index;
    private ControllerContext controllerContext;

    SoloButton(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack, int index) {
        super(transport, trackBank, cursorTrack);
        this.index = index;
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        SoloValue solo = trackBank.getItemAt(index).solo();
        if (controllerContext.isSetPressed()) {
            solo.toggle(true);
            for (int i = BUTTON_ID_FIRST; i <= BUTTON_ID_LAST; i++) {
                if ((i - BUTTON_ID_FIRST) != index) {
                    controllerContext.ledOFF(i);
                }
            }
        } else {
            solo.toggle();
        }
        controllerContext.toggleLED(BUTTON_ID_FIRST + index, solo.get());
    }

}
