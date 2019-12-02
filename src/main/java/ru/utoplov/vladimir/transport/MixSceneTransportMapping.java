package ru.utoplov.vladimir.transport;

import com.bitwig.extension.controller.api.Transport;

public class MixSceneTransportMapping extends TransportMapping {

    public MixSceneTransportMapping(Transport transport) {
        super(transport);
    }

    @Override
    void onRewind() {
        transport.rewind();
    }

    @Override
    public void onPlay() {
        transport.play();
    }

    @Override
    public void onStop() {
        transport.stop();
    }

    @Override
    public void onRecord() {
        transport.record();
    }

}