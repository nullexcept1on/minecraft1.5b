package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.*;

class GuiLogFormatter extends Formatter
{

    GuiLogFormatter(GuiLogOutputHandler guilogoutputhandler)
    {
        outputHandler = guilogoutputhandler;
        //super();
    }

    public String format(LogRecord logrecord)
    {
        StringBuilder stringbuilder = new StringBuilder();
        Level level = logrecord.getLevel();
        if(level == Level.FINEST)
        {
            stringbuilder.append("[FINEST] ");
        } else
        if(level == Level.FINER)
        {
            stringbuilder.append("[FINER] ");
        } else
        if(level == Level.FINE)
        {
            stringbuilder.append("[FINE] ");
        } else
        if(level == Level.INFO)
        {
            stringbuilder.append("[INFO] ");
        } else
        if(level == Level.WARNING)
        {
            stringbuilder.append("[WARNING] ");
        } else
        if(level == Level.SEVERE)
        {
            stringbuilder.append("[SEVERE] ");
        } else
        if(level == Level.SEVERE)
        {
            stringbuilder.append((new StringBuilder()).append("[").append(level.getLocalizedName()).append("] ").toString());
        }
        stringbuilder.append(logrecord.getMessage());
        stringbuilder.append('\n');
        Throwable throwable = logrecord.getThrown();
        if(throwable != null)
        {
            StringWriter stringwriter = new StringWriter();
            throwable.printStackTrace(new PrintWriter(stringwriter));
            stringbuilder.append(stringwriter.toString());
        }
        return stringbuilder.toString();
    }

    final GuiLogOutputHandler outputHandler; /* synthetic field */
}
