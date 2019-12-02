package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;

public class SelectButton extends SimpleButton {

    private int index;

    SelectButton(Transport transport, TrackBank trackBank, int index) {
        super(transport, trackBank);
        this.index = index;
    }

    @Override
    protected void logic() {
        trackBank.getItemAt(index).selectInMixer();
    }
}
