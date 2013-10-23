package someguynamed.din.BlockNotifier;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.crash.CallableMinecraftVersion;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import tconstruct.client.TClientTickHandler;
import tconstruct.client.TControls;
import tconstruct.common.TContent;
import tconstruct.common.TProxyCommon;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.Detailing;
import tconstruct.library.crafting.LiquidCasting;
import tconstruct.library.util.CoordTuple;
import tconstruct.library.util.TabTools;
import tconstruct.util.TCraftingHandler;
import tconstruct.util.TEventHandler;
import tconstruct.util.config.PHConstruct;
import tconstruct.util.player.TPlayerHandler;
import tconstruct.worldgen.TBaseWorldGenerator;
import tconstruct.worldgen.TerrainGenEventHandler;
import tconstruct.worldgen.village.ComponentSmeltery;
import tconstruct.worldgen.village.ComponentToolWorkshop;
import tconstruct.worldgen.village.TVillageTrades;
import tconstruct.worldgen.village.VillageSmelteryHandler;
import tconstruct.worldgen.village.VillageToolStationHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

@Mod(modid = "BlockNotifier", name = "BlockNotifier", version = "0.1", dependencies = "required-after:Forge@[8.9,)")
@NetworkMod(serverSideRequired = false, clientSideRequired = true)
public class BlockNotifier {
	static Minecraft mc = Minecraft.getMinecraft();
	public static KeyBinding monitorKey = new KeyBinding("key.bnMonitor", 25);
	static ArrayList<BNCoord>monitorBlocks = new ArrayList<BNCoord>();
	
	/* Instance of this mod, used for grabbing prototype fields */
    @Instance("BlockNotifier")
    public static BlockNotifier instance;
    
    /* Proxies for sides, used for graphics processing */
    @SidedProxy(clientSide = "someguynamed.din.BlockNotifier.proxyClient", serverSide = "someguynamed.din.BlockNotifier.proxyCommon")
    public static proxyClient proxy;
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent event)
    {
        proxy.registerKeys();
        proxy.registerTickHandler();
    }

}
