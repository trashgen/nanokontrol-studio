package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.Track;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.ButtonSet;
import ru.utoplov.vladimir.buttons.continuousset.ControlContext;

import java.util.HashMap;
import java.util.Map;

public class SimpleButtonSet implements ButtonSet {

    private final TrackBank trackBank;
    private final Transport transport;
    private final CursorTrack cursorTrack;

    private final Map<Integer, SimpleButton> buttons = new HashMap<>();

    public SimpleButtonSet(Transport transport, TrackBank trackBank, CursorTrack cursorTrack, ControlContext controlContext) {
        this.trackBank = trackBank;
        this.transport = transport;
        this.cursorTrack = cursorTrack;

        for (int i = 0; i < trackBank.getSizeOfBank(); i++) {
            Track track = trackBank.getItemAt(i);
            track.mute().markInterested();
            track.solo().markInterested();
        }

        buttons.put(BUTTON_TRANSPORT_REWIND, new RewindButton(transport, trackBank));
        buttons.put(BUTTON_TRANSPORT_STOP, new StopButton(transport, trackBank, controlContext));
        buttons.put(BUTTON_TRANSPORT_PLAY, new PlayButton(transport, trackBank));
        buttons.put(BUTTON_TRANSPORT_RECORD, new RecordButton(transport, trackBank));

        buttons.put(BUTTON_SEND_BANK_PREV, new PrevSendBankButton(transport, trackBank, cursorTrack));
        buttons.put(BUTTON_SEND_BANK_NEXT, new NextSendBankButton(transport, trackBank, cursorTrack));

        buttons.put(BUTTON_TRACK_BANK_PREV, new PrevTrackBankButton(transport, trackBank));
        buttons.put(BUTTON_TRACK_BANK_NEXT, new NextTrackBankButton(transport, trackBank));

        for (int i = BUTTON_MUTE_1; i <= BUTTON_MUTE_8; i++) {
            buttons.put(i, new MuteButton(transport, trackBank, i - BUTTON_MUTE_1));
        }
        for (int i = BUTTON_SOLO_1; i <= BUTTON_SOLO_8; i++) {
            buttons.put(i, new SoloButton(transport, trackBank, i - BUTTON_SOLO_1));
        }
        // TODO : what the diff with 'Select' ???
//        for (int i = BUTTON_RECORD_1; i <= BUTTON_RECORD_8; i++) {
//            buttons.put(i, new SoloButton(transport, trackBank, cursorTrack, i - BUTTON_RECORD_1));
//        }
        for (int i = BUTTON_SELECT_1; i <= BUTTON_SELECT_8; i++) {
            buttons.put(i, new SelectButton(transport, trackBank, i - BUTTON_SELECT_1));
        }
    }

    @Override
    public boolean isValid(ShortMidiMessage msg) {
        return buttons.keySet().stream().anyMatch(code -> msg.getData1() == code);
    }

    @Override
    public boolean execute(ShortMidiMessage msg) {
        SimpleButton button = buttons.get(msg.getData1());
        button.execute(msg);
        return true;
    }

    private final static int BUTTON_SOLO_1 = 29;
    private final static int BUTTON_SOLO_8 = 36;

    private final static int BUTTON_MUTE_1 = 21;
    private final static int BUTTON_MUTE_8 = 28;

    public final static int BUTTON_RECORD_1 = 38;
    public final static int BUTTON_RECORD_8 = 45;

    private final static int BUTTON_SELECT_1 = 46;
    private final static int BUTTON_SELECT_8 = 53;

    public final static int BUTTON_SEND_BANK_PREV = 56;
    public final static int BUTTON_SEND_BANK_NEXT = 57;

    public final static int BUTTON_TRACK_BANK_PREV = 60;
    public final static int BUTTON_TRACK_BANK_NEXT = 61;

    private final static int BUTTON_TRANSPORT_REWIND = 62;
    private final static int BUTTON_TRANSPORT_STOP = 63;
    private final static int BUTTON_TRANSPORT_PLAY = 80;
    private final static int BUTTON_TRANSPORT_RECORD = 81;

}
