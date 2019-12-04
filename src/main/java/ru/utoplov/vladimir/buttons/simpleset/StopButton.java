package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

public class StopButton extends SimpleButton {

    final static int BUTTON_ID = 63;

    private DeviceContext deviceContext;

    StopButton(DeviceContext deviceContext, Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
        this.deviceContext = deviceContext;
    }

    @Override
    protected void logic() {
        transport.stop();
        deviceContext.ArrangementPosition = 0;
        deviceContext.ledOFF(PlayButton.BUTTON_ID);
        deviceContext.ledOFF(RecordButton.BUTTON_ID);
    }
}
