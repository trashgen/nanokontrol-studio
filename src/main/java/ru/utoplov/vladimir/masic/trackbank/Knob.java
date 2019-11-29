package ru.utoplov.vladimir.masic.trackbank;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.TrackBank;

public class Knob implements TrackBankCommand {
    private int index;

    public Knob(int index) {
        this.index = index;
    }

    @Override
    public void execute(ShortMidiMessage msg, TrackBank trackBank, boolean useShift) {
//        trackBank.getItemAt(index).selectInMixer();
    }
}
