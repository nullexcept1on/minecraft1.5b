package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class NetLoginHandler extends NetHandler
{

    public NetLoginHandler(MinecraftServer minecraftserver, Socket socket, String s) throws IOException
    {
        finishedProcessing = false;
        loginTimer = 0;
        username = null;
        field_9004_h = null;
        serverId = "";
        mcServer = minecraftserver;
        netManager = new NetworkManager(socket, s, this);
        netManager.chunkDataSendCounter = 0;
    }

    public void tryLogin()
    {
        if(field_9004_h != null)
        {
            doLogin(field_9004_h);
            field_9004_h = null;
        }
        if(loginTimer++ == 600)
        {
            kickUser("Took too long to log in");
        } else
        {
            netManager.processReadPackets();
        }
    }

    public void kickUser(String s)
    {
        try
        {
            logger.info((new StringBuilder()).append("Disconnecting ").append(getUserAndIPString()).append(": ").append(s).toString());
            netManager.addToSendQueue(new Packet255KickDisconnect(s));
            netManager.serverShutdown();
            finishedProcessing = true;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void handleHandshake(Packet2Handshake packet2handshake)
    {
        if(mcServer.onlineMode)
        {
            serverId = Long.toHexString(rand.nextLong());
            netManager.addToSendQueue(new Packet2Handshake(serverId));
        } else
        {
            netManager.addToSendQueue(new Packet2Handshake("-"));
        }
    }

    public void handleLogin(Packet1Login packet1login)
    {
        username = packet1login.username;
        if(packet1login.protocolVersion != 11)
        {
            if(packet1login.protocolVersion > 11)
            {
                kickUser("Outdated server!");
            } else
            {
                kickUser("Outdated client!");
            }
            return;
        }
        if(!mcServer.onlineMode)
        {
            doLogin(packet1login);
        } else
        {
            (new ThreadLoginVerifier(this, packet1login)).start();
        }
    }

    public void doLogin(Packet1Login packet1login)
    {
        EntityPlayerMP entityplayermp = mcServer.configManager.login(this, packet1login.username);
        if(entityplayermp != null)
        {
            logger.info((new StringBuilder()).append(getUserAndIPString()).append(" logged in with entity id ").append(entityplayermp.entityId).append(" at (").append(entityplayermp.posX).append(", ").append(entityplayermp.posY).append(", ").append(entityplayermp.posZ).append(")").toString());
            ChunkCoordinates chunkcoordinates = mcServer.worldMngr.getSpawnPoint();
            NetServerHandler netserverhandler = new NetServerHandler(mcServer, netManager, entityplayermp);
            netserverhandler.sendPacket(new Packet1Login("", entityplayermp.entityId, mcServer.worldMngr.func_22079_j(), (byte)mcServer.worldMngr.worldProvider.worldType));
            netserverhandler.sendPacket(new Packet6SpawnPosition(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ));
            if(mcServer.worldMngr.func_27068_v())
            {
                netserverhandler.sendPacket(new Packet70Bed(1));
            }
            mcServer.configManager.sendPacketToAllPlayers(new Packet3Chat((new StringBuilder()).append("\247e").append(entityplayermp.username).append(" joined the game.").toString()));
            mcServer.configManager.playerLoggedIn(entityplayermp);
            netserverhandler.teleportTo(entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ, entityplayermp.rotationYaw, entityplayermp.rotationPitch);
            mcServer.networkServer.addPlayer(netserverhandler);
            netserverhandler.sendPacket(new Packet4UpdateTime(mcServer.worldMngr.getWorldTime()));
            entityplayermp.func_20057_k();
        }
        finishedProcessing = true;
    }

    public void handleErrorMessage(String s, Object aobj[])
    {
        logger.info((new StringBuilder()).append(getUserAndIPString()).append(" lost connection").toString());
        finishedProcessing = true;
    }

    public void registerPacket(Packet packet)
    {
        kickUser("Protocol error");
    }

    public String getUserAndIPString()
    {
        if(username != null)
        {
            return (new StringBuilder()).append(username).append(" [").append(netManager.getRemoteAddress().toString()).append("]").toString();
        } else
        {
            return netManager.getRemoteAddress().toString();
        }
    }

    public boolean func_27003_c()
    {
        return true;
    }

    static String getServerId(NetLoginHandler netloginhandler)
    {
        return netloginhandler.serverId;
    }

    static Packet1Login setLoginPacket(NetLoginHandler netloginhandler, Packet1Login packet1login)
    {
        return netloginhandler.field_9004_h = packet1login;
    }

    public static Logger logger = Logger.getLogger("Minecraft");
    private static Random rand = new Random();
    public NetworkManager netManager;
    public boolean finishedProcessing;
    private MinecraftServer mcServer;
    private int loginTimer;
    private String username;
    private Packet1Login field_9004_h;
    private String serverId;

}
