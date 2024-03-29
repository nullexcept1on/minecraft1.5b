package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;

public class Packet255KickDisconnect extends Packet
{

    public Packet255KickDisconnect()
    {
    }

    public Packet255KickDisconnect(String s)
    {
        reason = s;
    }

    public void readPacketData(DataInputStream datainputstream) throws IOException
    {
        reason = func_27037_a(datainputstream, 100);
    }

    public void writePacketData(DataOutputStream dataoutputstream) throws IOException
    {
        func_27038_a(reason, dataoutputstream);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleKickDisconnect(this);
    }

    public int getPacketSize()
    {
        return reason.length();
    }

    public String reason;
}
