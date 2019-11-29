package ru.utoplov.vladimir.masic.transport;

import com.bitwig.extension.controller.api.Transport;

public class Record implements TransportCommand {
    @Override
    public void execute(Transport transport, boolean useShift) {
        transport.record();
    }
}
