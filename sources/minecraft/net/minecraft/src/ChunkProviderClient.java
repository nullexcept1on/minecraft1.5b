package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.*;

public class ChunkProviderClient
    implements IChunkProvider
{

    public ChunkProviderClient(World world)
    {
        chunkMapping = new HashMap();
        field_889_c = new ArrayList();
        blankChunk = new EmptyChunk(world, new byte[32768], 0, 0);
        worldObj = world;
    }

    public boolean chunkExists(int i, int j)
    {
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);
        return chunkMapping.containsKey(chunkcoordintpair);
    }

    public void func_539_c(int i, int j)
    {
        Chunk chunk = provideChunk(i, j);
        if(!chunk.func_21167_h())
        {
            chunk.onChunkUnload();
        }
        chunkMapping.remove(new ChunkCoordIntPair(i, j));
        field_889_c.remove(chunk);
    }

    public Chunk func_538_d(int i, int j)
    {
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);
        byte abyte0[] = new byte[32768];
        Chunk chunk = new Chunk(worldObj, abyte0, i, j);
        Arrays.fill(chunk.skylightMap.data, (byte)-1);
        chunkMapping.put(chunkcoordintpair, chunk);
        chunk.isChunkLoaded = true;
        return chunk;
    }

    public Chunk provideChunk(int i, int j)
    {
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);
        Chunk chunk = (Chunk)chunkMapping.get(chunkcoordintpair);
        if(chunk == null)
        {
            return blankChunk;
        } else
        {
            return chunk;
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        return true;
    }

    public boolean func_532_a()
    {
        return false;
    }

    public boolean func_536_b()
    {
        return false;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
    }

    public String toString()
    {
        return (new StringBuilder()).append("MultiplayerChunkCache: ").append(chunkMapping.size()).toString();
    }

    private Chunk blankChunk;
    private Map chunkMapping;
    private List field_889_c;
    private World worldObj;
}
