package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;

public class PrevTrackBankButton extends SimpleButton {

    PrevTrackBankButton(Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
    }

    @Override
    protected void logic() {
        trackBank.scrollPageBackwards();
    }
}
