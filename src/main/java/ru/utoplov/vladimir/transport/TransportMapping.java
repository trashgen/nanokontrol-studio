package ru.utoplov.vladimir.transport;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.MappingManager;

import java.util.Arrays;

public abstract class TransportMapping extends MappingManager {

    abstract void onRewind();

    abstract void onPlay();

    abstract void onStop();

    abstract void onRecord();

    protected Transport transport;

    public TransportMapping(Transport transport) {
        super();
        this.transport = transport;
    }

    @Override
    public boolean isValidMessage(ShortMidiMessage msg) {
        return Arrays.stream(new Integer[]{
                BUTTON_TRANSPORT_REWIND,
                BUTTON_TRANSPORT_STOP,
                BUTTON_TRANSPORT_PLAY,
                BUTTON_TRANSPORT_RECORD})
                .anyMatch(code -> code == msg.getData1());
    }

    @Override
    public void execute(ShortMidiMessage msg) {
        // Works only on KeyUp
        if (msg.getData2() != 0) {
            return;
        }
        switch (msg.getData1()) {
            case BUTTON_TRANSPORT_REWIND:
                onRewind();
                break;
            case BUTTON_TRANSPORT_STOP:
                onStop();
                break;
            case BUTTON_TRANSPORT_PLAY:
                onPlay();
                break;
            case BUTTON_TRANSPORT_RECORD:
                onRecord();
                break;
        }
    }

    private final static int BUTTON_TRANSPORT_REWIND = 62;
    private final static int BUTTON_TRANSPORT_STOP = 63;
    private final static int BUTTON_TRANSPORT_PLAY = 80;
    private final static int BUTTON_TRANSPORT_RECORD = 81;

}