package hu.laci200270.mods.modularsuits.common.network.packets;

import hu.laci200270.mods.modularsuits.api.IArmorElement;
import hu.laci200270.mods.modularsuits.common.Reference;
import hu.laci200270.mods.modularsuits.common.tile.TileConstructingTable;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustom.IServerPacketHandler;
public class PacketHandler implements IServerPacketHandler{

public static final Object channel = "constructiontable";


@Override
public void handlePacket(PacketCustom packet, EntityPlayerMP player, INetHandlerPlayServer netHandler) {
 switch (packet.getType()) {
 case 1:
  Integer target=packet.readInt();
  NBTTagCompound nbttag= packet.readNBTTagCompound();
  BlockPos tilePos=new BlockPos(nbttag.getInteger("x"),nbttag.getInteger("y") , nbttag.getInteger("z"));
  TileEntity tile = player.getEntityWorld().getTileEntity(tilePos);
  World world=player.getEntityWorld();
  
  if(tile==null){
  	Reference.logger.logWhenDebug("null");
  }
  if (tile != null && tile instanceof TileConstructingTable)
  {
	 // Reference.logger.logWhenDebug("instanceof");
  	InventoryPlayer invplayer=player.inventory;
    Boolean okay=true;
    IArmorElement[] modules=(IArmorElement[]) Reference.armorElements.toArray();
    ItemStack[] recipe=modules[target].recipe();
    for (int i = 0; i < recipe.length; i++) {
		if(!Reference.consumeItems(player.inventory, recipe[i].getItem(), recipe[i].stackSize)){
			okay=false;
		}
	}
    if(!okay){
    	player.inventory=invplayer;
    }
    Reference.logger.logWhenDebug("Status: "+okay);
  }
  break;
 }
}}
