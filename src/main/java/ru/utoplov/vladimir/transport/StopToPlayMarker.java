package ru.utoplov.vladimir.transport;

import com.bitwig.extension.controller.api.BeatTimeValue;
import com.bitwig.extension.controller.api.Transport;

public class StopToPlayMarker implements TransportCommand {
    @Override
    public void execute(Transport transport) {
        BeatTimeValue beat = transport.getPosition();

    }
}
