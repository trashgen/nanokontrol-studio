package ru.utoplov.vladimir;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;
import ru.utoplov.vladimir.scenes.SceneView;
import ru.utoplov.vladimir.scenes.VelocityView;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NanoKONTROLStudioExtensionDefinition extends ControllerExtensionDefinition
{
   private static final UUID DRIVER_ID = UUID.fromString("dc010196-7101-4519-ad10-ab5634f76da5");

   // Cycle button used as shift. Press'n'Hold to use.
   public final static int BUTTON_SHIFT = 0;

   public final static int BUTTON_SOLO_1 = 0;
   public final static int BUTTON_SOLO_2 = 0;
   public final static int BUTTON_SOLO_3 = 0;
   public final static int BUTTON_SOLO_4 = 0;
   public final static int BUTTON_SOLO_5 = 0;
   public final static int BUTTON_SOLO_6 = 0;
   public final static int BUTTON_SOLO_7 = 0;
   public final static int BUTTON_SOLO_8 = 0;

   public final static int BUTTON_MUTE_1 = 0;
   public final static int BUTTON_MUTE_2 = 0;
   public final static int BUTTON_MUTE_3 = 0;
   public final static int BUTTON_MUTE_4 = 0;
   public final static int BUTTON_MUTE_5 = 0;
   public final static int BUTTON_MUTE_6 = 0;
   public final static int BUTTON_MUTE_7 = 0;
   public final static int BUTTON_MUTE_8 = 0;

   public final static int BUTTON_RECORD_1 = 0;
   public final static int BUTTON_RECORD_2 = 0;
   public final static int BUTTON_RECORD_3 = 0;
   public final static int BUTTON_RECORD_4 = 0;
   public final static int BUTTON_RECORD_5 = 0;
   public final static int BUTTON_RECORD_6 = 0;
   public final static int BUTTON_RECORD_7 = 0;
   public final static int BUTTON_RECORD_8 = 0;

   public final static int BUTTON_SELECT_1 = 0;
   public final static int BUTTON_SELECT_2 = 0;
   public final static int BUTTON_SELECT_3 = 0;
   public final static int BUTTON_SELECT_4 = 0;
   public final static int BUTTON_SELECT_5 = 0;
   public final static int BUTTON_SELECT_6 = 0;
   public final static int BUTTON_SELECT_7 = 0;
   public final static int BUTTON_SELECT_8 = 0;

   public final static int BUTTON_NEXT_TRACK_BANK = 0;
   public final static int BUTTON_PREV_TRACK_BANK = 0;

   public final static int BUTTON_STOP_TO_PLAY_MARKER = 62;
   public final static int BUTTON_STOP = 63;
   public final static int BUTTON_PLAY = 80;

   // Scene button values
   public final static Map<String, SceneView> SysexHandlers = new HashMap<String, SceneView>() {{
      put("f042400001370200004f00f7", new VelocityView());
      put("f042400001370200004f01f7", new VelocityView());
      put("f042400001370200004f02f7", new VelocityView());
      put("f042400001370200004f03f7", new VelocityView());
      put("f042400001370200004f04f7", new VelocityView());
   }};

   public NanoKONTROLStudioExtensionDefinition()
   {
   }

   @Override
   public String getName()
   {
      return "NanoKONTROL Studio";
   }
   
   @Override
   public String getAuthor()
   {
      return "Vladimir Utoplov";
   }

   @Override
   public String getVersion()
   {
      return "1.0";
   }

   @Override
   public UUID getId()
   {
      return DRIVER_ID;
   }
   
   @Override
   public String getHardwareVendor()
   {
      return "Korg";
   }
   
   @Override
   public String getHardwareModel()
   {
      return "NanoKONTROL Studio";
   }

   @Override
   public int getRequiredAPIVersion()
   {
      return 9;
   }

   @Override
   public int getNumMidiInPorts()
   {
      return 1;
   }

   @Override
   public int getNumMidiOutPorts()
   {
      return 1;
   }

   @Override
   public void listAutoDetectionMidiPortNames(final AutoDetectionMidiPortNamesList list, final PlatformType platformType)
   {
      if (platformType == PlatformType.WINDOWS)
      {
         // TODO: Set the correct names of the ports for auto detection on Windows platform here
         // and uncomment this when port names are correct.
         // list.add(new String[]{"Input Port 0"}, new String[]{"Output Port 0"});
      }
      else if (platformType == PlatformType.MAC)
      {
         // TODO: Set the correct names of the ports for auto detection on Windows platform here
         // and uncomment this when port names are correct.
         // list.add(new String[]{"Input Port 0"}, new String[]{"Output Port 0"});
      }
      else if (platformType == PlatformType.LINUX)
      {
         // TODO: Set the correct names of the ports for auto detection on Windows platform here
         // and uncomment this when port names are correct.
         // list.add(new String[]{"Input Port 0"}, new String[]{"Output Port 0"});
      }
   }

   @Override
   public NanoKONTROLStudioExtension createInstance(final ControllerHost host)
   {
      return new NanoKONTROLStudioExtension(this, host);
   }
}
