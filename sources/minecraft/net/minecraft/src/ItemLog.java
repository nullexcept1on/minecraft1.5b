package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ItemLog extends ItemBlock
{

    public ItemLog(int i)
    {
        super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public int func_27009_a(int i)
    {
        return Block.wood.getBlockTextureFromSideAndMetadata(2, i);
    }

    public int func_21012_a(int i)
    {
        return i;
    }
}
