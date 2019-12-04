package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;

public class NextSendBankButton extends SimpleButton {

    final static int BUTTON_ID = 57;

    private CursorTrack cursorTrack;

    NextSendBankButton(Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank);
        this.cursorTrack = cursorTrack;
    }

    @Override
    protected void logic() {
        cursorTrack.sendBank().scrollPageForwards();
    }
}
