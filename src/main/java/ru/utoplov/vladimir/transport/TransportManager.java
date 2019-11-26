package ru.utoplov.vladimir.transport;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.NanoKONTROLStudioExtensionDefinition;

import java.util.HashMap;
import java.util.Map;

public class TransportManager {
    private final Transport transport;

    public TransportManager(Transport transport) {
        this.transport = transport;
    }

    private final Map<Integer, TransportCommand> handlers = new HashMap<Integer, TransportCommand>() {{
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_STOP_TO_PLAY_MARKER, new StopToPlayMarker());
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_STOP, new Stop());
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_PLAY, new Play());
    }};

    public void execute(ShortMidiMessage msg) {
        if (msg.getData2() != 0) {
            return;
        }
        TransportCommand command = handlers.get(msg.getData1());
        if (command != null) {
            command.execute(transport);
        }
    }
}
