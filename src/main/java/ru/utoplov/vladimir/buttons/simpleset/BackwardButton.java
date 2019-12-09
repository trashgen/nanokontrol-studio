package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.SettableBeatTimeValue;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

public class BackwardButton extends SimpleButton {

    public final static int BUTTON_ID = 58;

    private DeviceContext deviceContext;

    BackwardButton(DeviceContext deviceContext, Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
        this.deviceContext = deviceContext;
    }

    @Override
    protected void logic() {
        if (deviceContext.isSetPressed()) {
            SettableBeatTimeValue value = transport.getInPosition();
            value.set(transport.getPosition().get());
        }
    }
}
