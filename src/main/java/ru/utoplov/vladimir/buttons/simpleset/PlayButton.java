package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

public class PlayButton extends SimpleButton {

    final static int BUTTON_ID = 80;

    private DeviceContext deviceContext;

    public PlayButton(DeviceContext deviceContext, Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
        this.deviceContext = deviceContext;
    }

    @Override
    protected void logic() {
        transport.setPosition(deviceContext.ArrangementPosition);
        transport.play();
        deviceContext.toggleLED(BUTTON_ID, transport.isPlaying().get());
    }

}