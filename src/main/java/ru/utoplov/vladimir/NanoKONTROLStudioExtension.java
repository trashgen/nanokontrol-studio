package ru.utoplov.vladimir;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.callback.ShortMidiMessageReceivedCallback;
import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.api.Arranger;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.Transport;
import ru.utoplov.vladimir.scenes.SceneView;
import ru.utoplov.vladimir.transport.TransportManager;

public class NanoKONTROLStudioExtension extends ControllerExtension {
    private Arranger arranger;
    private Transport transport;
    private TransportManager transportManager;

    protected NanoKONTROLStudioExtension(final NanoKONTROLStudioExtensionDefinition definition, final ControllerHost host) {
        super(definition, host);
    }

    @Override
    public void init() {
        final ControllerHost host = getHost();
        arranger = host.createArranger();
        transport = host.createTransport();
        transportManager = new TransportManager(transport);

        host.getMidiInPort(0).setMidiCallback((ShortMidiMessageReceivedCallback) msg -> onMidi0(msg));
        host.getMidiInPort(0).setSysexCallback((String data) -> onSysex0(data));

        host.showPopupNotification("NanoKONTROL Studio Initialized");
    }

    /**
     * Called when we receive short MIDI message on port 0.
     */
    private void onMidi0(ShortMidiMessage msg) {
        getHost().showPopupNotification(String.format("%d [%d] -> [%d]:[%d]", msg.getStatusByte(), msg.getChannel(), msg.getData1(), msg.getData2()));
        transportManager.execute(msg);
    }

    @Override
    public void exit() {
        getHost().showPopupNotification("NanoKONTROL Studio Exited");
    }

    @Override
    public void flush() {
        // TODO Send any updates you need here.
    }

    /**
     * Called when we receive sysex MIDI message on port 0.
     */
    private void onSysex0(final String data) {
        SceneView view = NanoKONTROLStudioExtensionDefinition.SysexHandlers.get(data);
        if (view != null) {
            getHost().showPopupNotification(String.format("Set mode [%s]", view.getName()));
        }
    }

}
