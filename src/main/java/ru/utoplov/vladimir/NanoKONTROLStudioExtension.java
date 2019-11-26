package ru.utoplov.vladimir;

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.callback.ShortMidiMessageReceivedCallback;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.Transport;
import com.bitwig.extension.controller.ControllerExtension;

import java.util.HashMap;
import java.util.Map;

public class NanoKONTROLStudioExtension extends ControllerExtension
{
   private Transport transport;
   private Map<String, String> sysexHandlers = new HashMap<String, String>() {{
      put("f042400001370200004f00f7", "Velocity");
      put("f042400001370200004f01f7", "Send 08");
      put("f042400001370200004f02f7", "Send 16");
      put("f042400001370200004f03f7", "Not Used Yet");
      put("f042400001370200004f04f7", "Reserved");
   }};
   protected NanoKONTROLStudioExtension(final NanoKONTROLStudioExtensionDefinition definition, final ControllerHost host)
   {
      super(definition, host);
   }

   @Override
   public void init()
   {
      final ControllerHost host = getHost();      
      transport = host.createTransport();

      host.getMidiInPort(0).setMidiCallback((ShortMidiMessageReceivedCallback)msg -> onMidi0(msg));
      host.getMidiInPort(0).setSysexCallback((String data) -> onSysex0(data));

      host.showPopupNotification("NanoKONTROL Studio Initialized");
   }

   @Override
   public void exit()
   {
      getHost().showPopupNotification("NanoKONTROL Studio Exited");
   }

   @Override
   public void flush()
   {
      // TODO Send any updates you need here.
   }

   /** Called when we receive short MIDI message on port 0. */
   private void onMidi0(ShortMidiMessage msg) 
   {
      getHost().showPopupNotification(String.format("%d [%d] -> [%d]:[%d]", msg.getStatusByte(), msg.getChannel(), msg.getData1(), msg.getData2()));
      if (msg.getData2() != 0) {
         return;
      }
      switch (msg.getData1()) {
         case NanoKONTROLStudioExtensionDefinition.BUTTON_STOP:
            transport.stop();
            break;
         case NanoKONTROLStudioExtensionDefinition.BUTTON_PLAY:
            transport.play();
            break;
      }
   }

   /** Called when we receive sysex MIDI message on port 0. */
   private void onSysex0(final String data) 
   {
      Object handler = sysexHandlers.get(data);
      if (handler != null) {
         getHost().showPopupNotification(String.format("Set mode [%s]", handler));
      }
   }

}
