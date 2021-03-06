package hu.laci200270.mods.modularsuits.proxys;

import hu.laci200270.mods.modularsuits.api.IModItem;
import hu.laci200270.mods.modularsuits.client.gui.GuiConstructionTable;
import hu.laci200270.mods.modularsuits.common.Reference;
import hu.laci200270.mods.modularsuits.common.tile.TileConstructingTable;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerBasicItemRenderStuff(ArrayList<Item> list) {
		
		for (Item item : list) {
			System.out.println("Registering render stuff for :"+item.getUnlocalizedName());
		
			RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
			renderItem.getItemModelMesher().register(item, new ItemMeshDefinition()
            {
                public ModelResourceLocation getModelLocation(ItemStack stack)
               {
                	
                	
                  return new ModelResourceLocation(Reference.MODID+":"+stack.getUnlocalizedName().substring(5), "inventory");
               }
              }    );
			
			
		}
		
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		switch (ID) {
		case 0:
			Reference.logger.logWhenDebug("case 0");
			if(world.getTileEntity(new BlockPos(x, y, z)) instanceof TileConstructingTable){
				Reference.logger.logWhenDebug("instanceof");
				TileConstructingTable tileEntity=(TileConstructingTable) world.getTileEntity(new BlockPos(x*1.0, y*1.0, z*1.0));
			
			return new GuiConstructionTable(player,player.inventory, (TileConstructingTable) tileEntity, world, x, y, z);
			}
			
			return null;
			

		default:
			return null;
			
		}
	}
	@Override
	public void registerBlocksRenderer(ArrayList<Block> blocks){
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		for (Block block : blocks) {
			
		
		renderItem.getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MODID + ":" + ((IModItem) block).getName(), "inventory"));
		}
		}
	
}
