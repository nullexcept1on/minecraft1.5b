package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ItemSlab extends ItemBlock
{

    public ItemSlab(int i)
    {
        super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public int func_27009_a(int i)
    {
        return Block.stairSingle.getBlockTextureFromSideAndMetadata(2, i);
    }

    public int func_21012_a(int i)
    {
        return i;
    }

    public String getItemNameIS(ItemStack itemstack)
    {
        return (new StringBuilder()).append(super.getItemName()).append(".").append(BlockStep.field_22037_a[itemstack.getItemDamage()]).toString();
    }
}
