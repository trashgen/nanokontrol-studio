package ru.utoplov.vladimir.masic.trackbank;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.TrackBank;

public class Solo implements TrackBankCommand {
    private int index;

    public Solo(int index) {
        this.index = index;
    }

    @Override
    public void execute(ShortMidiMessage msg, TrackBank trackBank, boolean useShift) {
        trackBank.getItemAt(index).solo().toggle();
    }
}
