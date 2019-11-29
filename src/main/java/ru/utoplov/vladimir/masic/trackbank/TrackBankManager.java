package ru.utoplov.vladimir.masic.trackbank;

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

    private TrackBank effectTracks;
    private final TrackBank trackBank;
    private final CursorTrack cursorTrack;

    private final Map<Integer, TrackBankCommand> handlers = new HashMap<Integer, TrackBankCommand>() {{
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_TRACK_BANK_PREV, new PrevTrackBank());
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_TRACK_BANK_NEXT, new NextTrackBank());

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
        trackBank = host.createTrackBank(8, 8, 0, true);
        for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
            Track track = trackBank.getItemAt(i);
            track.name().markInterested();
            track.mute().markInterested();
            track.solo().markInterested();
            for (int j = 0; j < track.sendBank().getSizeOfBank(); j++) {
                track.sendBank().getItemAt(j).markInterested();
                track.sendBank().getItemAt(j).setIndication(true);
//                Send send = track.sendBank().getItemAt(j);
//                send.sendMode().
            }

            Parameter parameter = trackBank.getItemAt(i).volume();
            parameter.markInterested();
            parameter.setIndication(true);
        }

        cursorTrack = host.createCursorTrack(CURSOR_TRACK_ID, CURSOR_TRACK_NAME, 0, 0, true);
        cursorTrack.name().markInterested();
        trackBank.followCursorTrack(cursorTrack);
        // TODO : Test this numTracks == 1. Maybe Just one Track Sends are marked ?
        effectTracks = cursorTrack.createEffectTrackBank(1, 8, true);
        for (int i = 0; i < effectTracks.getSizeOfBank(); i++) {
            effectTracks.getItemAt(i).name().markInterested();
        }

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
            handlers.put(index, new Select(host, cursorTrack, i));
        }

        handlers.put(BUTTON_SEND_BANK_NEXT, new NextSendBank(host, effectTracks, cursorTrack));
    }

    public boolean execute(ShortMidiMessage msg, boolean useShift) {
        if (!isCCMessage(msg) && isOnKeyDown(msg)) {
            return false;
        }
        TrackBankCommand command = handlers.get(msg.getData1());
        if (command != null) {
//            if (msg.getData1() == BUTTON_SELECT_1) {
//                SendBank sendBank = effectTracks.getItemAt(0).sendBank();
//                for (int i = 0; i < sendBank.getSizeOfBank(); i++) {
//                    sendBank.getItemAt(i).markInterested();
//                    sendBank.getItemAt(i).setIndication(true);
//                }
//            }
            command.execute(msg, trackBank, useShift);
            return true;
        }
        return false;
    }

    public boolean isValidCommand(ShortMidiMessage msg) {
        return handlers.keySet().stream().anyMatch(code -> msg.getData1() == code);
    }

    private boolean isCCMessage(ShortMidiMessage msg) {
        return Arrays
                .stream(new Integer[]{
                        BUTTON_WHEEL,
                        BUTTON_KNOB_1, BUTTON_KNOB_2, BUTTON_KNOB_3, BUTTON_KNOB_4, BUTTON_KNOB_5, BUTTON_KNOB_6, BUTTON_KNOB_7, BUTTON_KNOB_8,
                        BUTTON_FADER_1, BUTTON_FADER_2, BUTTON_FADER_3, BUTTON_FADER_4, BUTTON_FADER_5, BUTTON_FADER_6, BUTTON_FADER_7, BUTTON_FADER_8})
                .anyMatch(code -> msg.getData1() == code);
    }

    private boolean isOnKeyUp(ShortMidiMessage msg) {
        return msg.getData2() == 0;
    }

    private boolean isOnKeyDown(ShortMidiMessage msg) {
        return msg.getData2() != 0;
    }

}
