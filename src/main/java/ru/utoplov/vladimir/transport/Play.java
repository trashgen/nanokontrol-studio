package ru.utoplov.vladimir.transport;

import com.bitwig.extension.controller.api.Transport;

public class Play implements TransportCommand {
    @Override
    public void execute(Transport transport, boolean useShift) {
        transport.play();
    }
}
