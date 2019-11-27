package ru.utoplov.vladimir.trackbank;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.NanoKONTROLStudioExtensionDefinition;

import java.util.HashMap;
import java.util.Map;

public class TrackBankManager {
    private final static String CURSOR_TRACK_ID = "NANO_KONTROL_STUDIO_CURSOR_TRACK_ID";
    private final static String CURSOR_TRACK_NAME = "NANO_CURSOR_TRACK_NAME";

    private final TrackBank trackBank;
    private final CursorTrack cursorTrack;

    private final Map<Integer, TrackBankCommand> handlers = new HashMap<Integer, TrackBankCommand>() {{
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_NEXT_TRACK_BANK, new NextTrackBank());
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_PREV_TRACK_BANK, new PrevTrackBank());
    }};

    public TrackBankManager(ControllerHost host) {
        trackBank = host.createTrackBank(8, 0, 0);
        cursorTrack = host.createCursorTrack(CURSOR_TRACK_ID, CURSOR_TRACK_NAME, 0, 0, false);

        for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
            Track track = trackBank.getItemAt(i);
            track.mute().markInterested();
            track.solo().markInterested();

            Parameter parameter = trackBank.getItemAt(i).volume();
            parameter.markInterested();
            parameter.setIndication(true);
        }
    }

    public boolean execute(ShortMidiMessage msg) {
        TrackBankCommand command = handlers.get(msg.getData1());
        if (command != null) {
            command.execute(trackBank);
            return true;
        }
        return false;
    }
}
