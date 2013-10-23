package someguynamed.din.BlockNotifier;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class proxyClient extends proxyCommon {
	static BNControls controlInstance;
    /* Keybindings */
    public void registerKeys() {
        controlInstance = new BNControls();
        TickRegistry.registerTickHandler(controlInstance, Side.CLIENT);
    }
    
    public void registerTickHandler ()
    {
        TickRegistry.registerTickHandler(new BNTickHandler(), Side.CLIENT);
    }

}