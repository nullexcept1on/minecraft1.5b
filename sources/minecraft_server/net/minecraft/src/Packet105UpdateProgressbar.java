package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;

public class Packet105UpdateProgressbar extends Packet
{

    public Packet105UpdateProgressbar()
    {
    }

    public Packet105UpdateProgressbar(int i, int j, int k)
    {
        windowId = i;
        progressBar = j;
        progressBarValue = k;
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.func_20002_a(this);
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException
    {
        windowId = datainputstream.readByte();
        progressBar = datainputstream.readShort();
        progressBarValue = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException
    {
        dataoutputstream.writeByte(windowId);
        dataoutputstream.writeShort(progressBar);
        dataoutputstream.writeShort(progressBarValue);
    }

    public int getPacketSize()
    {
        return 5;
    }

    public int windowId;
    public int progressBar;
    public int progressBarValue;
}
