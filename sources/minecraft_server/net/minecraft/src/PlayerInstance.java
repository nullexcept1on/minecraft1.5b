package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.PrintStream;
import java.util.*;
import net.minecraft.server.MinecraftServer;

class PlayerInstance
{

    public PlayerInstance(PlayerManager playermanager, int i, int j)
    {
        playerManager = playermanager;
        //super();
        players = new ArrayList();
        blocksToUpdate = new short[10];
        numBlocksToUpdate = 0;
        chunkX = i;
        chunkY = j;
        currentChunk = new ChunkCoordIntPair(i, j);
        PlayerManager.getMinecraftServer(playermanager).worldMngr.chunkProvider.loadChunk(i, j);
    }

    public void addPlayer(EntityPlayerMP entityplayermp)
    {
        if(players.contains(entityplayermp))
        {
            throw new IllegalStateException((new StringBuilder()).append("Failed to add player. ").append(entityplayermp).append(" already is in chunk ").append(chunkX).append(", ").append(chunkY).toString());
        } else
        {
            entityplayermp.field_420_ah.add(currentChunk);
            entityplayermp.playerNetServerHandler.sendPacket(new Packet50PreChunk(currentChunk.chunkXPos, currentChunk.chunkZPos, true));
            players.add(entityplayermp);
            entityplayermp.loadedChunks.add(currentChunk);
            return;
        }
    }

    public void removePlayer(EntityPlayerMP entityplayermp)
    {
        if(!players.contains(entityplayermp))
        {
            (new IllegalStateException((new StringBuilder()).append("Failed to remove player. ").append(entityplayermp).append(" isn't in chunk ").append(chunkX).append(", ").append(chunkY).toString())).printStackTrace();
            return;
        }
        players.remove(entityplayermp);
        if(players.size() == 0)
        {
            long l = (long)chunkX + 0x7fffffffL | (long)chunkY + 0x7fffffffL << 32;
            PlayerManager.getPlayerInstances(playerManager).remove(l);
            if(numBlocksToUpdate > 0)
            {
                PlayerManager.getPlayerInstancesToUpdate(playerManager).remove(this);
            }
            PlayerManager.getMinecraftServer(playerManager).worldMngr.chunkProvider.func_374_c(chunkX, chunkY);
        }
        entityplayermp.loadedChunks.remove(currentChunk);
        if(entityplayermp.field_420_ah.contains(currentChunk))
        {
            entityplayermp.playerNetServerHandler.sendPacket(new Packet50PreChunk(chunkX, chunkY, false));
        }
    }

    public void markBlockNeedsUpdate(int i, int j, int k)
    {
        if(numBlocksToUpdate == 0)
        {
            PlayerManager.getPlayerInstancesToUpdate(playerManager).add(this);
            minX = maxX = i;
            minY = maxY = j;
            minZ = maxZ = k;
        }
        if(minX > i)
        {
            minX = i;
        }
        if(maxX < i)
        {
            maxX = i;
        }
        if(minY > j)
        {
            minY = j;
        }
        if(maxY < j)
        {
            maxY = j;
        }
        if(minZ > k)
        {
            minZ = k;
        }
        if(maxZ < k)
        {
            maxZ = k;
        }
        if(numBlocksToUpdate < 10)
        {
            short word0 = (short)(i << 12 | k << 8 | j);
            for(int l = 0; l < numBlocksToUpdate; l++)
            {
                if(blocksToUpdate[l] == word0)
                {
                    return;
                }
            }

            blocksToUpdate[numBlocksToUpdate++] = word0;
        }
    }

    public void sendPacketToPlayersInInstance(Packet packet)
    {
        for(int i = 0; i < players.size(); i++)
        {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)players.get(i);
            if(entityplayermp.field_420_ah.contains(currentChunk))
            {
                entityplayermp.playerNetServerHandler.sendPacket(packet);
            }
        }

    }

    public void onUpdate()
    {
        if(numBlocksToUpdate == 0)
        {
            return;
        }
        if(numBlocksToUpdate == 1)
        {
            int i = chunkX * 16 + minX;
            int l = minY;
            int k1 = chunkY * 16 + minZ;
            sendPacketToPlayersInInstance(new Packet53BlockChange(i, l, k1, PlayerManager.getMinecraftServer(playerManager).worldMngr));
            if(Block.isBlockContainer[PlayerManager.getMinecraftServer(playerManager).worldMngr.getBlockId(i, l, k1)])
            {
                updateTileEntity(PlayerManager.getMinecraftServer(playerManager).worldMngr.getBlockTileEntity(i, l, k1));
            }
        } else
        if(numBlocksToUpdate == 10)
        {
            minY = (minY / 2) * 2;
            maxY = (maxY / 2 + 1) * 2;
            int j = minX + chunkX * 16;
            int i1 = minY;
            int l1 = minZ + chunkY * 16;
            int j2 = (maxX - minX) + 1;
            int l2 = (maxY - minY) + 2;
            int i3 = (maxZ - minZ) + 1;
            sendPacketToPlayersInInstance(new Packet51MapChunk(j, i1, l1, j2, l2, i3, PlayerManager.getMinecraftServer(playerManager).worldMngr));
            List list = PlayerManager.getMinecraftServer(playerManager).worldMngr.getTileEntityList(j, i1, l1, j + j2, i1 + l2, l1 + i3);
            for(int j3 = 0; j3 < list.size(); j3++)
            {
                updateTileEntity((TileEntity)list.get(j3));
            }

        } else
        {
            sendPacketToPlayersInInstance(new Packet52MultiBlockChange(chunkX, chunkY, blocksToUpdate, numBlocksToUpdate, PlayerManager.getMinecraftServer(playerManager).worldMngr));
            for(int k = 0; k < numBlocksToUpdate; k++)
            {
                int j1 = chunkX * 16 + (numBlocksToUpdate >> 12 & 0xf);
                int i2 = numBlocksToUpdate & 0xff;
                int k2 = chunkY * 16 + (numBlocksToUpdate >> 8 & 0xf);
                if(Block.isBlockContainer[PlayerManager.getMinecraftServer(playerManager).worldMngr.getBlockId(j1, i2, k2)])
                {
                    System.out.println("Sending!");
                    updateTileEntity(PlayerManager.getMinecraftServer(playerManager).worldMngr.getBlockTileEntity(j1, i2, k2));
                }
            }

        }
        numBlocksToUpdate = 0;
    }

    private void updateTileEntity(TileEntity tileentity)
    {
        if(tileentity != null)
        {
            Packet packet = tileentity.getDescriptionPacket();
            if(packet != null)
            {
                sendPacketToPlayersInInstance(packet);
            }
        }
    }

    private List players;
    private int chunkX;
    private int chunkY;
    private ChunkCoordIntPair currentChunk;
    private short blocksToUpdate[];
    private int numBlocksToUpdate;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    private int minZ;
    private int maxZ;
    final PlayerManager playerManager; /* synthetic field */
}
