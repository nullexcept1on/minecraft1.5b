package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class ChunkCoordIntPair
{

    public ChunkCoordIntPair(int i, int j)
    {
        chunkXPos = i;
        chunkZPos = j;
    }

    public static int func_22011_a(int i, int j)
    {
        return (i >= 0 ? 0 : 0x80000000) | (i & 0x7fff) << 16 | (j >= 0 ? 0 : 0x8000) | j & 0x7fff;
    }

    public int hashCode()
    {
        return func_22011_a(chunkXPos, chunkZPos);
    }

    public boolean equals(Object obj)
    {
        ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)obj;
        return chunkcoordintpair.chunkXPos == chunkXPos && chunkcoordintpair.chunkZPos == chunkZPos;
    }

    public final int chunkXPos;
    public final int chunkZPos;
}
