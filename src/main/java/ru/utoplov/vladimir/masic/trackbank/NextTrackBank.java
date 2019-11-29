package ru.utoplov.vladimir.masic.trackbank;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.TrackBank;

public class NextTrackBank implements TrackBankCommand {
    @Override
    public void execute(ShortMidiMessage msg, TrackBank trackBank, boolean useShift) {
        trackBank.scrollPageForwards();
    }
}
