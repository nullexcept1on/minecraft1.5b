package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;

public class BiomeGenHell extends BiomeGenBase
{

    public BiomeGenHell()
    {
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 10));
        spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 10));
    }
}
