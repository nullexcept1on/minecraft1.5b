package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;

public class Packet25EntityPainting extends Packet
{

    public Packet25EntityPainting()
    {
    }

    public Packet25EntityPainting(EntityPainting entitypainting)
    {
        entityId = entitypainting.entityId;
        xPosition = entitypainting.xPosition;
        yPosition = entitypainting.yPosition;
        zPosition = entitypainting.zPosition;
        direction = entitypainting.direction;
        title = entitypainting.art.title;
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException
    {
        entityId = datainputstream.readInt();
        title = func_27048_a(datainputstream, EnumArt.field_27365_z);
        xPosition = datainputstream.readInt();
        yPosition = datainputstream.readInt();
        zPosition = datainputstream.readInt();
        direction = datainputstream.readInt();
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException
    {
        dataoutputstream.writeInt(entityId);
        func_27049_a(title, dataoutputstream);
        dataoutputstream.writeInt(xPosition);
        dataoutputstream.writeInt(yPosition);
        dataoutputstream.writeInt(zPosition);
        dataoutputstream.writeInt(direction);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.func_21146_a(this);
    }

    public int getPacketSize()
    {
        return 24;
    }

    public int entityId;
    public int xPosition;
    public int yPosition;
    public int zPosition;
    public int direction;
    public String title;
}
