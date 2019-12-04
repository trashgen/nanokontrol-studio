package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

public class RecordButton extends SimpleButton {

    final static int BUTTON_ID = 81;

    private DeviceContext deviceContext;

    RecordButton(DeviceContext deviceContext, Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
        this.deviceContext = deviceContext;
    }

    @Override
    protected void logic() {
        transport.record();
        deviceContext.toggleLED(BUTTON_ID, transport.isArrangerRecordEnabled().get());
    }
}
