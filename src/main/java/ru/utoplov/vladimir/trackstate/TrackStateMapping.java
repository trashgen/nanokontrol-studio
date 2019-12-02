package ru.utoplov.vladimir.trackstate;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.CursorTrack;
import com.bitwig.extension.controller.api.TrackBank;
import ru.utoplov.vladimir.MappingManager;

import java.util.Arrays;

public abstract class TrackStateMapping extends MappingManager {

    protected TrackBank trackBank;
    protected CursorTrack cursorTrack;

    protected abstract void onSolo(int index);

    protected abstract void onMute(int index);

    protected abstract void onRecord(int index);

    protected abstract void onSelect(int index);

    public TrackStateMapping(TrackBank trackBank, CursorTrack cursorTrack) {
        super();
        this.trackBank = trackBank;
        this.cursorTrack = cursorTrack;
    }

    @Override
    public boolean isValidMessage(ShortMidiMessage msg) {
        return Arrays.stream(new Integer[]{
                BUTTON_SOLO_1,
                BUTTON_MUTE_1,
                BUTTON_RECORD_1,
                BUTTON_SELECT_1})
                .anyMatch(code -> code == msg.getData1());
    }

    @Override
    public void execute(ShortMidiMessage msg) {
        // Works only on KeyUp
        if (msg.getData2() != 0) {
            return;
        }
        switch (msg.getData1()) {
            case BUTTON_SOLO_1:
                onSolo(0);
                break;
            case BUTTON_MUTE_1:
                onMute(0);
                break;
            case BUTTON_RECORD_1:
                onRecord(1);
                break;
            case BUTTON_SELECT_1:
                onSelect(1);
                break;
        }
    }

    public final static int BUTTON_SOLO_1 = 29;
    public final static int BUTTON_SOLO_2 = 30;
    public final static int BUTTON_SOLO_3 = 31;
    public final static int BUTTON_SOLO_4 = 33;
    public final static int BUTTON_SOLO_5 = 33;
    public final static int BUTTON_SOLO_6 = 34;
    public final static int BUTTON_SOLO_7 = 35;
    public final static int BUTTON_SOLO_8 = 36;

    public final static int BUTTON_MUTE_1 = 21;
    public final static int BUTTON_MUTE_2 = 22;
    public final static int BUTTON_MUTE_3 = 23;
    public final static int BUTTON_MUTE_4 = 24;
    public final static int BUTTON_MUTE_5 = 25;
    public final static int BUTTON_MUTE_6 = 26;
    public final static int BUTTON_MUTE_7 = 27;
    public final static int BUTTON_MUTE_8 = 28;

    public final static int BUTTON_RECORD_1 = 38;
    public final static int BUTTON_RECORD_2 = 39;
    public final static int BUTTON_RECORD_3 = 40;
    public final static int BUTTON_RECORD_4 = 41;
    public final static int BUTTON_RECORD_5 = 42;
    public final static int BUTTON_RECORD_6 = 43;
    public final static int BUTTON_RECORD_7 = 44;
    public final static int BUTTON_RECORD_8 = 45;

    public final static int BUTTON_SELECT_1 = 46;
    public final static int BUTTON_SELECT_2 = 47;
    public final static int BUTTON_SELECT_3 = 48;
    public final static int BUTTON_SELECT_4 = 49;
    public final static int BUTTON_SELECT_5 = 50;
    public final static int BUTTON_SELECT_6 = 51;
    public final static int BUTTON_SELECT_7 = 52;
    public final static int BUTTON_SELECT_8 = 53;

}
