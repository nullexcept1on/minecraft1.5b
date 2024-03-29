package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;

public class WorldInfo
{

    public WorldInfo(NBTTagCompound nbttagcompound)
    {
        randomSeed = nbttagcompound.getLong("RandomSeed");
        spawnX = nbttagcompound.getInteger("SpawnX");
        spawnY = nbttagcompound.getInteger("SpawnY");
        spawnZ = nbttagcompound.getInteger("SpawnZ");
        worldTime = nbttagcompound.getLong("Time");
        lastTimePlayed = nbttagcompound.getLong("LastPlayed");
        sizeOnDisk = nbttagcompound.getLong("SizeOnDisk");
        levelName = nbttagcompound.getString("LevelName");
        saveVersion = nbttagcompound.getInteger("version");
        field_27403_m = nbttagcompound.getInteger("rainTime");
        field_27404_l = nbttagcompound.getBoolean("raining");
        field_27401_o = nbttagcompound.getInteger("thunderTime");
        field_27402_n = nbttagcompound.getBoolean("thundering");
        if(nbttagcompound.hasKey("Player"))
        {
            playerTag = nbttagcompound.getCompoundTag("Player");
            dimension = playerTag.getInteger("Dimension");
        }
    }

    public WorldInfo(long l, String s)
    {
        randomSeed = l;
        levelName = s;
    }

    public WorldInfo(WorldInfo worldinfo)
    {
        randomSeed = worldinfo.randomSeed;
        spawnX = worldinfo.spawnX;
        spawnY = worldinfo.spawnY;
        spawnZ = worldinfo.spawnZ;
        worldTime = worldinfo.worldTime;
        lastTimePlayed = worldinfo.lastTimePlayed;
        sizeOnDisk = worldinfo.sizeOnDisk;
        playerTag = worldinfo.playerTag;
        dimension = worldinfo.dimension;
        levelName = worldinfo.levelName;
        saveVersion = worldinfo.saveVersion;
        field_27403_m = worldinfo.field_27403_m;
        field_27404_l = worldinfo.field_27404_l;
        field_27401_o = worldinfo.field_27401_o;
        field_27402_n = worldinfo.field_27402_n;
    }

    public NBTTagCompound getNBTTagCompound()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        updateTagCompound(nbttagcompound, playerTag);
        return nbttagcompound;
    }

    public NBTTagCompound getNBTTagCompoundWithPlayer(List list)
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        EntityPlayer entityplayer = null;
        NBTTagCompound nbttagcompound1 = null;
        if(list.size() > 0)
        {
            entityplayer = (EntityPlayer)list.get(0);
        }
        if(entityplayer != null)
        {
            nbttagcompound1 = new NBTTagCompound();
            entityplayer.writeToNBT(nbttagcompound1);
        }
        updateTagCompound(nbttagcompound, nbttagcompound1);
        return nbttagcompound;
    }

    private void updateTagCompound(NBTTagCompound nbttagcompound, NBTTagCompound nbttagcompound1)
    {
        nbttagcompound.setLong("RandomSeed", randomSeed);
        nbttagcompound.setInteger("SpawnX", spawnX);
        nbttagcompound.setInteger("SpawnY", spawnY);
        nbttagcompound.setInteger("SpawnZ", spawnZ);
        nbttagcompound.setLong("Time", worldTime);
        nbttagcompound.setLong("SizeOnDisk", sizeOnDisk);
        nbttagcompound.setLong("LastPlayed", System.currentTimeMillis());
        nbttagcompound.setString("LevelName", levelName);
        nbttagcompound.setInteger("version", saveVersion);
        nbttagcompound.setInteger("rainTime", field_27403_m);
        nbttagcompound.setBoolean("raining", field_27404_l);
        nbttagcompound.setInteger("thunderTime", field_27401_o);
        nbttagcompound.setBoolean("thundering", field_27402_n);
        if(nbttagcompound1 != null)
        {
            nbttagcompound.setCompoundTag("Player", nbttagcompound1);
        }
    }

    public long getRandomSeed()
    {
        return randomSeed;
    }

    public int getSpawnX()
    {
        return spawnX;
    }

    public int getSpawnY()
    {
        return spawnY;
    }

    public int getSpawnZ()
    {
        return spawnZ;
    }

    public long getWorldTime()
    {
        return worldTime;
    }

    public long getSizeOnDisk()
    {
        return sizeOnDisk;
    }

    public NBTTagCompound getPlayerNBTTagCompound()
    {
        return playerTag;
    }

    public int getDimension()
    {
        return dimension;
    }

    public void setSpawnX(int i)
    {
        spawnX = i;
    }

    public void setSpawnY(int i)
    {
        spawnY = i;
    }

    public void setSpawnZ(int i)
    {
        spawnZ = i;
    }

    public void setWorldTime(long l)
    {
        worldTime = l;
    }

    public void setSizeOnDisk(long l)
    {
        sizeOnDisk = l;
    }

    public void setPlayerNBTTagCompound(NBTTagCompound nbttagcompound)
    {
        playerTag = nbttagcompound;
    }

    public void setSpawn(int i, int j, int k)
    {
        spawnX = i;
        spawnY = j;
        spawnZ = k;
    }

    public String getWorldName()
    {
        return levelName;
    }

    public void setWorldName(String s)
    {
        levelName = s;
    }

    public int getSaveVersion()
    {
        return saveVersion;
    }

    public void setSaveVersion(int i)
    {
        saveVersion = i;
    }

    public long getLastTimePlayed()
    {
        return lastTimePlayed;
    }

    public boolean func_27396_m()
    {
        return field_27402_n;
    }

    public void func_27398_a(boolean flag)
    {
        field_27402_n = flag;
    }

    public int func_27400_n()
    {
        return field_27401_o;
    }

    public void func_27399_e(int i)
    {
        field_27401_o = i;
    }

    public boolean func_27397_o()
    {
        return field_27404_l;
    }

    public void func_27394_b(boolean flag)
    {
        field_27404_l = flag;
    }

    public int func_27393_p()
    {
        return field_27403_m;
    }

    public void func_27395_f(int i)
    {
        field_27403_m = i;
    }

    private long randomSeed;
    private int spawnX;
    private int spawnY;
    private int spawnZ;
    private long worldTime;
    private long lastTimePlayed;
    private long sizeOnDisk;
    private NBTTagCompound playerTag;
    private int dimension;
    private String levelName;
    private int saveVersion;
    private boolean field_27404_l;
    private int field_27403_m;
    private boolean field_27402_n;
    private int field_27401_o;
}
