package ru.utoplov.vladimir.trackbank;

import com.bitwig.extension.controller.api.TrackBank;

public class NextTrackBank implements TrackBankCommand {
    @Override
    public void execute(TrackBank trackBank) {
        trackBank.scrollPageForwards();
    }
}
