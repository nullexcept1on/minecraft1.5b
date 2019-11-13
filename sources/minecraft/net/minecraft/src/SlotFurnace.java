package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class SlotFurnace extends Slot
{

    public SlotFurnace(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k)
    {
        super(iinventory, i, j, k);
        field_27011_d = entityplayer;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }

    public boolean func_25014_f()
    {
        return true;
    }

    public void onPickupFromSlot(ItemStack itemstack)
    {
        if(itemstack.itemID == Item.ingotIron.shiftedIndex)
        {
            field_27011_d.addStat(AchievementList.field_27385_k, 1);
        }
        if(itemstack.itemID == Item.fishCooked.shiftedIndex)
        {
            field_27011_d.addStat(AchievementList.field_27380_p, 1);
        }
        super.onPickupFromSlot(itemstack);
    }

    private EntityPlayer field_27011_d;
}
