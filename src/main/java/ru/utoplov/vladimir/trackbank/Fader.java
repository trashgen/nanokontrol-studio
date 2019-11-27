package ru.utoplov.vladimir.trackbank;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.TrackBank;

public class Fader implements TrackBankCommand {
    private int index;
    private int prevValue;

    public Fader(int index) {
        this.index = index;
    }

    @Override
    public void execute(ShortMidiMessage msg, TrackBank trackBank, boolean useShift) {
        int increment;
        if (prevValue == msg.getData2()) {
            return;
        }
        if (prevValue < msg.getData2()) {
            increment = -1;
        } else {
            increment = 1;
        }
        prevValue = msg.getData2();
        trackBank.getItemAt(index).volume().value().inc(increment, 128);
    }
}
