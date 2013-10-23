package someguynamed.din.BlockNotifier;

import java.util.ArrayList;
import java.util.EnumSet;

import someguynamed.din.BlockNotifier.BNCoord;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

class BNTickHandler implements ITickHandler {
	private int tickCount = 0;
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		return;
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (BlockNotifier.mc.theWorld == null) {
			BlockNotifier.monitorBlocks.clear();
			return;
		}
		if (tickCount < 4) {
			tickCount++;
			return;
		}
		tickCount = 0;
		
		ArrayList<BNCoord>tBlocks = new ArrayList<BNCoord>(BlockNotifier.monitorBlocks); 
		BNCoord coord;
		for (int x = tBlocks.size()-1; x >= 0; x--) {
			coord = tBlocks.get(x);
			if (BlockNotifier.mc.theWorld.getChunkFromBlockCoords(coord.x, coord.y).isChunkLoaded) {
				if (BlockNotifier.mc.theWorld.getBlockId(coord.x, coord.y, coord.z) == 0)
				{
					BlockNotifier.mc.thePlayer.sendChatMessage("Block at [" + coord.x + "," + coord.y + "," + coord.z + "] has been destroyed");
					BlockNotifier.monitorBlocks.remove(x);
				}
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}