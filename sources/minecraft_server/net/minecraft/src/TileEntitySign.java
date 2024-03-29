package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class TileEntitySign extends TileEntity
{

    public TileEntitySign()
    {
        lineBeingEdited = -1;
        isEditAble = true;
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setString("Text1", signText[0]);
        nbttagcompound.setString("Text2", signText[1]);
        nbttagcompound.setString("Text3", signText[2]);
        nbttagcompound.setString("Text4", signText[3]);
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        isEditAble = false;
        super.readFromNBT(nbttagcompound);
        for(int i = 0; i < 4; i++)
        {
            signText[i] = nbttagcompound.getString((new StringBuilder()).append("Text").append(i + 1).toString());
            if(signText[i].length() > 15)
            {
                signText[i] = signText[i].substring(0, 15);
            }
        }

    }

    public Packet getDescriptionPacket()
    {
        String as[] = new String[4];
        for(int i = 0; i < 4; i++)
        {
            as[i] = signText[i];
        }

        return new Packet130UpdateSign(xCoord, yCoord, zCoord, as);
    }

    public boolean getIsEditAble()
    {
        return isEditAble;
    }

    public String signText[] = {
        "", "", "", ""
    };
    public int lineBeingEdited;
    private boolean isEditAble;
}
