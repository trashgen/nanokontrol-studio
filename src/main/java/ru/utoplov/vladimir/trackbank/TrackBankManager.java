package ru.utoplov.vladimir.trackbank;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.*;
import ru.utoplov.vladimir.NanoKONTROLStudioExtensionDefinition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static ru.utoplov.vladimir.NanoKONTROLStudioExtensionDefinition.*;

public class TrackBankManager {
    private final static String CURSOR_TRACK_ID = "NANO_KONTROL_STUDIO_CURSOR_TRACK_ID";
    private final static String CURSOR_TRACK_NAME = "NANO_CURSOR_TRACK_NAME";

    private final TrackBank trackBank;
    private final CursorTrack cursorTrack;

    private final Map<Integer, TrackBankCommand> handlers = new HashMap<Integer, TrackBankCommand>() {{
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_TRACK_BANK_PREV, new PrevBank());
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_TRACK_BANK_NEXT, new NextBank());

        put(BUTTON_SOLO_1, new Solo(0));
        put(BUTTON_SOLO_2, new Solo(1));
        put(BUTTON_SOLO_3, new Solo(2));
        put(BUTTON_SOLO_4, new Solo(3));
        put(BUTTON_SOLO_5, new Solo(4));
        put(BUTTON_SOLO_6, new Solo(5));
        put(BUTTON_SOLO_7, new Solo(6));
        put(BUTTON_SOLO_8, new Solo(7));

        put(BUTTON_FADER_1, new Fader(0));
        put(BUTTON_FADER_2, new Fader(1));
        put(BUTTON_FADER_3, new Fader(2));
        put(BUTTON_FADER_4, new Fader(3));
        put(BUTTON_FADER_5, new Fader(4));
        put(BUTTON_FADER_6, new Fader(5));
        put(BUTTON_FADER_7, new Fader(6));
        put(BUTTON_FADER_8, new Fader(7));
    }};

    public TrackBankManager(ControllerHost host) {
        trackBank = host.createTrackBank(8, 0, 0);
        cursorTrack = host.createCursorTrack(CURSOR_TRACK_ID, CURSOR_TRACK_NAME, 0, 0, false);

        for (int index = BUTTON_KNOB_1, i = 0; index <= BUTTON_KNOB_8; index++, i++) {
            handlers.put(index, new Knob(i));
        }
        for (int index = BUTTON_MUTE_1, i = 0; index <= BUTTON_MUTE_8; index++, i++) {
            handlers.put(index, new Mute(i));
        }
        for (int index = BUTTON_RECORD_1, i = 0; index <= BUTTON_RECORD_1; index++, i++) {
            handlers.put(index, new Record(i));
        }
        for (int index = BUTTON_SELECT_1, i = 0; index <= BUTTON_SELECT_8; index++, i++) {
            handlers.put(index, new Select(i));
        }

        for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
            Track track = trackBank.getItemAt(i);
            track.mute().markInterested();
            track.solo().markInterested();

            Parameter parameter = trackBank.getItemAt(i).volume();
            parameter.markInterested();
            parameter.setIndication(true);
        }
    }

    public boolean execute(ShortMidiMessage msg, boolean useShift) {
        boolean isCC = Arrays
                .stream(new Integer[]{BUTTON_FADER_1, BUTTON_FADER_2, BUTTON_FADER_3, BUTTON_FADER_4})
                .anyMatch(code -> msg.getData1() == code);
        if (!isCC || msg.getData2() != 0) {
            return false;
        }
        TrackBankCommand command = handlers.get(msg.getData1());
        if (command != null) {
            command.execute(msg, trackBank, useShift);
            return true;
        }
        return false;
    }

    public boolean isValidCommand(ShortMidiMessage msg) {
        return handlers.keySet().stream().anyMatch(code -> msg.getData1() == code);
    }

}
