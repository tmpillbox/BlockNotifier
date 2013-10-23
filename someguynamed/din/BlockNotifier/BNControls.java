package someguynamed.din.BlockNotifier;

import java.util.EnumSet;

import someguynamed.din.BlockNotifier.BNCoord;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

class BNControls implements ITickHandler {
	private int tickCount = 0;
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		return;
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (BlockNotifier.mc.theWorld == null) return;
		if (tickCount < 2) {
			tickCount++;
			return;
		}
		tickCount = 0;
		
		if (BlockNotifier.mc.theWorld != null && BlockNotifier.monitorKey.pressed) {
			int targetX = BlockNotifier.mc.objectMouseOver.blockX;
			int targetY = BlockNotifier.mc.objectMouseOver.blockY;
			int targetZ = BlockNotifier.mc.objectMouseOver.blockZ;
			
			BNCoord newMonitor = new BNCoord(targetX, targetY, targetZ);
			
			boolean found = false;
			for (BNCoord coord : BlockNotifier.monitorBlocks) {
				if (coord.equals(newMonitor))
				{
					found = true;
					break;
				}
			}
			
			if (!found) {
				BlockNotifier.monitorBlocks.add(newMonitor);
				BlockNotifier.mc.thePlayer.sendChatMessage("Block at [" + targetX + "," + targetY + "," + targetZ + "] has been added to the watch list");
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return null;
	}
}