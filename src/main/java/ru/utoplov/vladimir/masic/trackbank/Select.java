package ru.utoplov.vladimir.masic.trackbank;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;

public class Select implements TrackBankCommand {
    private int index;
    ControllerHost host;
    CursorTrack cursorTrack;

    public Select(ControllerHost host, CursorTrack cursorTrack, int index) {
        this.host = host;
        this.index = index;
        this.cursorTrack = cursorTrack;
    }

    @Override
    public void execute(ShortMidiMessage msg, TrackBank trackBank, boolean useShift) {
        host.showPopupNotification(cursorTrack.name().get());
        Track track = trackBank.getItemAt(index);
        track.selectInMixer();
        cursorTrack.setName("PIZDEC");
    }
}

