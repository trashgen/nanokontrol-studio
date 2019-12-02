package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;

public class PrevSendBankButton extends SimpleButton {

    private CursorTrack cursorTrack;

    PrevSendBankButton(Transport transport, TrackBank trackBank, CursorTrack cursorTrack) {
        super(transport, trackBank);
        this.cursorTrack = cursorTrack;
    }

    @Override
    protected void logic() {
        cursorTrack.sendBank().scrollPageBackwards();
    }
}
