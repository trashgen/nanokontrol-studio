package ru.utoplov.vladimir.transport;

import com.bitwig.extension.controller.api.Transport;

public interface TransportCommand {
    void execute(Transport transport, boolean useShift);
}
