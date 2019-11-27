package ru.utoplov.vladimir.transport;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.NanoKONTROLStudioExtensionDefinition;

import java.util.HashMap;
import java.util.Map;

public class TransportManager {
    private final Transport transport;

    public TransportManager(ControllerHost host) {
        transport = host.createTransport();
    }

    private final Map<Integer, TransportCommand> handlers = new HashMap<Integer, TransportCommand>() {{
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_STOP_TO_PLAY_MARKER, new StopToPlayMarker());
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_STOP, new Stop());
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_PLAY, new Play());
    }};

    public boolean execute(ShortMidiMessage msg) {
        if (msg.getData2() != 0) {
            return false;
        }
        TransportCommand command = handlers.get(msg.getData1());
        if (command != null) {
            command.execute(transport);
            return true;
        }
        return false;
    }
}
