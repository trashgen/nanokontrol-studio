package ru.utoplov.vladimir.buttons.simpleset;

import com.bitwig.extension.controller.api.SettableBeatTimeValue;
import com.bitwig.extension.controller.api.TrackBank;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.DeviceContext;

public class ForwardButton extends SimpleButton {

    public final static int BUTTON_ID = 59;

    private DeviceContext deviceContext;

    ForwardButton(DeviceContext deviceContext, Transport transport, TrackBank trackBank) {
        super(transport, trackBank);
        this.deviceContext = deviceContext;
    }

    @Override
    protected void logic() {
        if (deviceContext.isSetPressed()) {
            SettableBeatTimeValue beatValue = transport.getOutPosition();
            beatValue.set(transport.getPosition().get());
            deviceContext.ArrangementPosition = transport.getInPosition().get();
            transport.setPosition(transport.getInPosition().get());
        }
    }
}
