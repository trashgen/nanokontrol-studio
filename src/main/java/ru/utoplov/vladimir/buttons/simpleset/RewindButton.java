package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ControllerContext;

import static ru.utoplov.vladimir.NanoKONTROLStudioExtensionDefinition.KEY_SCENE_MIX;

public class RewindButton extends SimpleButton {

    final static int BUTTON_ID = 62;

    private ControllerContext controllerContext;

    RewindButton(ControllerContext controllerContext, Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank, cursorTrack);
        this.controllerContext = controllerContext;
    }

    @Override
    protected void logic() {
        transport.rewind();
        controllerContext.sendSysex(KEY_SCENE_MIX);
    }
}
