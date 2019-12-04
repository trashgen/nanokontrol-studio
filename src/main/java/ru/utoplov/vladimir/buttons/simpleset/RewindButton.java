package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;

public class RewindButton extends SimpleButton {

    final static int BUTTON_ID = 62;

    RewindButton(Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
    }

    @Override
    protected void logic() {
        transport.rewind();
    }
}
