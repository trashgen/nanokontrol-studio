package ru.utoplov.vladimir.masic.transport;

import com.bitwig.extension.controller.api.Transport;

public class Rewind implements TransportCommand {
    @Override
    public void execute(Transport transport, boolean useShift) {
        transport.rewind();
    }
}