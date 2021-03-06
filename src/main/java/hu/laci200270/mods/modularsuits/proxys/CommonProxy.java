package hu.laci200270.mods.modularsuits.proxys;

import hu.laci200270.mods.modularsuits.api.IModItem;
import hu.laci200270.mods.modularsuits.common.Reference;
import hu.laci200270.mods.modularsuits.common.tile.TileConstructingTable;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	public void registerBasicItemRenderStuff(ArrayList<Item> list){
		
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		switch (ID) {
		case 0:
			BlockPos pos=new BlockPos(x, y, z);
	        TileEntity tile = world.getTileEntity(pos);
	        if(tile==null){
	        	Reference.logger.logWhenDebug("null");
	        }
	        if (tile != null && tile instanceof TileConstructingTable)
	        {
	            Reference.logger.logWhenDebug("instanceof");
	        	return ((TileConstructingTable) tile).getGuiContainer(player.inventory, world, x, y, z);
	        }
			
			
			
			
			
			

		default:
			return null;
			
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}
	public void registerBlocksRenderer(ArrayList<Block> blocks){
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		for (Block block : blocks) {
			
		
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MODID + ":" + ((IModItem) block).getName(), "inventory"));
		}
		}
	
}
