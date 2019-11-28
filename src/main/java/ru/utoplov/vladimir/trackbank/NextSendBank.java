package ru.utoplov.vladimir.trackbank;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;

public class NextSendBank implements TrackBankCommand {
    private ControllerHost host;
    private TrackBank effectTracks;
    private CursorTrack cursorTrack;

    public NextSendBank(ControllerHost host, TrackBank effectTracks, CursorTrack cursorTrack) {
        this.host = host;
        this.cursorTrack = cursorTrack;
        this.effectTracks = effectTracks;
    }

    @Override
    public void execute(ShortMidiMessage msg, TrackBank trackBank, boolean useShift) {
        host.showPopupNotification(effectTracks.getItemAt(0).name().get());
        effectTracks.scrollPageForwards();
//        host.showPopupNotification(cursorTrack.name().get());
    }
}
