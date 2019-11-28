package ru.utoplov.vladimir.trackbank;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.TrackBank;

public class Fader implements TrackBankCommand {
    private int index;

    public Fader(int index) {
        this.index = index;
    }

    @Override
    public void execute(ShortMidiMessage msg, TrackBank trackBank, boolean useShift) {
        trackBank.getItemAt(index).volume().value().set(msg.getData2(), 128);
    }
}
