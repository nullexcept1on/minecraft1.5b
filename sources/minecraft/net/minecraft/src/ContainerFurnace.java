package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;

public class ContainerFurnace extends Container
{

    public ContainerFurnace(InventoryPlayer inventoryplayer, TileEntityFurnace tileentityfurnace)
    {
        cookTime = 0;
        burnTime = 0;
        itemBurnTime = 0;
        furnace = tileentityfurnace;
        addSlot(new Slot(tileentityfurnace, 0, 56, 17));
        addSlot(new Slot(tileentityfurnace, 1, 56, 53));
        addSlot(new SlotFurnace(inventoryplayer.player, tileentityfurnace, 2, 116, 35));
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
            {
                addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }

        }

        for(int j = 0; j < 9; j++)
        {
            addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }

    public void updateCraftingResults()
    {
        super.updateCraftingResults();
        for(int i = 0; i < field_20121_g.size(); i++)
        {
            ICrafting icrafting = (ICrafting)field_20121_g.get(i);
            if(cookTime != furnace.furnaceCookTime)
            {
                icrafting.func_20158_a(this, 0, furnace.furnaceCookTime);
            }
            if(burnTime != furnace.furnaceBurnTime)
            {
                icrafting.func_20158_a(this, 1, furnace.furnaceBurnTime);
            }
            if(itemBurnTime != furnace.currentItemBurnTime)
            {
                icrafting.func_20158_a(this, 2, furnace.currentItemBurnTime);
            }
        }

        cookTime = furnace.furnaceCookTime;
        burnTime = furnace.furnaceBurnTime;
        itemBurnTime = furnace.currentItemBurnTime;
    }

    public void func_20112_a(int i, int j)
    {
        if(i == 0)
        {
            furnace.furnaceCookTime = j;
        }
        if(i == 1)
        {
            furnace.furnaceBurnTime = j;
        }
        if(i == 2)
        {
            furnace.currentItemBurnTime = j;
        }
    }

    public boolean isUsableByPlayer(EntityPlayer entityplayer)
    {
        return furnace.canInteractWith(entityplayer);
    }

    private TileEntityFurnace furnace;
    private int cookTime;
    private int burnTime;
    private int itemBurnTime;
}
