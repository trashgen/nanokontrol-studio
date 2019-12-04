package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;

public class NextTrackBankButton extends SimpleButton {

    final static int BUTTON_ID = 61;

    NextTrackBankButton(Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
    }

    @Override
    protected void logic() {
        trackBank.scrollPageForwards();
    }
}
