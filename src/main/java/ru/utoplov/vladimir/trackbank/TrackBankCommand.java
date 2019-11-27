package ru.utoplov.vladimir.trackbank;

import com.bitwig.extension.controller.api.TrackBank;

public interface TrackBankCommand {
    void execute(TrackBank trackBank);
}
