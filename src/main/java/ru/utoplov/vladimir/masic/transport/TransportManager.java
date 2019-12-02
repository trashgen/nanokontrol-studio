package ru.utoplov.vladimir.masic.transport;

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
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_TRANSPORT_BACKWARD, new Backward());
        put(NanoKONTROLStudioExtensionDefinition.BUTTON_TRANSPORT_FAST_FORWARD, new FastForward());
//        put(NanoKONTROLStudioExtensionDefinition.BUTTON_TRANSPORT_REWIND, new Rewind());
//        put(NanoKONTROLStudioExtensionDefinition.BUTTON_TRANSPORT_STOP, new Stop());
//        put(NanoKONTROLStudioExtensionDefinition.BUTTON_TRANSPORT_PLAY, new Play());
//        put(NanoKONTROLStudioExtensionDefinition.BUTTON_TRANSPORT_RECORD, new Record());
    }};

    public boolean execute(ShortMidiMessage msg, boolean useShift) {
        if (msg.getData2() != 0) {
            return false;
        }
        TransportCommand command = handlers.get(msg.getData1());
        if (command != null) {
            command.execute(transport, useShift);
            return true;
        }
        return false;
    }

    public boolean isValidCommand(ShortMidiMessage msg) {
        return handlers.keySet().stream().anyMatch(code -> msg.getData1() == code);
    }

}
