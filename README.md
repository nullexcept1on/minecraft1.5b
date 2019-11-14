# Minecraft mod creator pack 2.12 for Minecraft 1.5_01

## Что изменено/добавлено

* В папку jars/bin/natives добавлены .so файлы для запуска клиента под linux
* В папке jars/bin заменены файлы jinput.jar, lwjgl_util.jar, lwjgl.jar на идентичные, по названию, из <a href="https://sourceforge.net/projects/java-game-lib/files/Official%20Releases/LWJGL%202.9.3/">lwjgl 2.9.3</a>
* Добавлены папки: jars/resources, mods

## Зависимости
* wine (linux)
* python (протестировано на 3 версии)
* <a href="http://jdk.java.net/java-se-ri/8">Windows 7 i586 Java Development Kit (md5) 88 MB</a> (jdk.java.net/java-se-ri/8)
* <a href="http://jdk.java.net/java-se-ri/8">Oracle Linux x64 Java Development Kit (md5) 164 MB</a> (jdk.java.net/java-se-ri/8)

## Как проходит процесс:
1) декомпиляция в sources (decompile)
2) изменение исходников
3) компиляция (recompile)
4) тестирование (test_game, test_server)
5) реобфускация в final_out
6) добавление классов в minecraft.jar (не в папке jars/bin) и удаление папки META-INF в minecraft.jar

## Использование в среде Linux
```
1) sudo chmod +x *.sh - Позволить запускать скрипты (выдача прав на исполнение)
2) ./decompile.sh - Декомпиляция клиента(jars/bin/minecraft.jar) и сервера(jars/minecraft_server.jar) в папку sources
3) ./recompile.sh - Компиляция из папки source
4) ./test_game.sh и ./test_server.sh - тестирование клиента и сервера
5) ./reobf.sh - Реобфускация в папку final_out
```

## Что делать сейчас не надо: (эти проблемы исправят)
* удалять папку logs. Иначе не будет процесса декомпиляции

Prerequisites:
==============
1.) Install Java SDK Standard Edition (short JDK). 
    Link: http://www.oracle.com/technetwork/java/javase/downloads/

2.) Add the paths to your JDK and JRE bin folders to the Environment Variable PATH. 

    Description where to find the variable:
    http://www.java.com/en/download/help/path.xml

    Example for Windows users of what you have to add to the variable (entries are seperated by ; )
    C:\Program Files\Java\jdk1.6.0_24\bin;C:\Program Files\Java\jre6\bin

How to use: (in update)
===========
1) Prepare the files:
- Copy the "minecraft_server.jar" file into the "jars" folder.
- Copy the folders "bin" and "resources" from your "%APPDATA$\.minecraft" folder into the "jars" folder.

2) Decompilation and patching
- Start the "decompile.bat" script in this folder.

3) Modding
- Modify the sourcecode in the "sources\minecraft" folder or in the "sources\minecraft_server" folder.

4) Compile
- Start the "recompile.bat" script in this folder.

5) Testing
- To test the modified game, start the "test_game.bat" script
- To test the modified server, start the "test_server.bat" script

6) Obfuscation
- Decompile the code, modify and recompile.
- Open "conf\server_obfuscation.txt" and "conf\client_obfuscation.txt".
- Put the name of the classes you want to be obfuscated, one per line. If you create new classes, they have to be specified as well.
  The name of the classes are the clear ones (Block, BlockDoor, etc).
  One example would be :
  BlockDoor
  Block
  Entity
  ChunkProviderGenerate
  MyNewAwesomeClass
- Start "reobf.bat" to start the reobfuscation step.
- Your obfuscated classes are now available in "final_out\minecraft" and "final_out\minecraft_server", ready to be injected in MC.
- Make sure to delete the META-INF folder in minecraft.jar, otherwise the game will just black-screen when you start it.
+ BETA FEATURE : For those willing to experiment, a beta GUI is available in the tool directory. It is called obfuscathonCharmer. 
  Just run it, experiment and give us some feedback on it. The GUI is made in C# and should work on both linux and windows (using
  mono on linux).


WARNINGS:
=========
- Make sure that you backup the modified sources before you run "decompile.bat" again or all changes will be lost!
- The "cleanup.bat" file will delete most of the generated files and sources. Be careful with this one :)

Notes:
======
* Do not use this to release complete packages of minecraft jar, class or java files. They are copyrighted
  material by Notch and mods should only contain small changes to some classes, never complete sets that
  can be used by people who did not buy the game to play it.

* Make sure you use the original minecraft.jar and minecraft_server.jar files. If you have already modded them
  they will NOT work with the patches in these scripts.

* The "test_game.bat" file uses the "Start.class" file to start the game. This will make sure the game will not
  use your "%APPDATA%\.minecraft" folder, but instead use the "jars" folder for all saves. So any bugs in the modified
  game will not corrupt your normal worlds.

* If you have any problems using this toolpack, put the "logs\*.log" files that the scripts generated into a
  zip-file and send it to us (post it in the minecraft forum):
  http://www.minecraftforum.net/viewtopic.php?f=25&t=58464
  
* This version of the mod creator package uses a deobfuscator to change all field and method names in the sources.
  Look in the "conf\minecraft.rgs" and "conf\minecraft_server.rgs" files for a complete mapping of the names.

* There are currently no known bugs in the recompiled game or server, except those that were already in the original
  game :) The known bugs, like missing sound effects or the backspace bug in the text entry gui, are fixed with this
  release.

* If your reobfuscated classes cause a black screen in Minecraft, make sure that you've deleted the META-INF folder
  in the minecraft.jar file.

Credits:
========
Searge
* Creator of MCP
* Fixes all compile errors in the decompiled sourcecode
* Created the MCP Mod System and API

ProfMobius
* Creator of the renaming codes and re-obfuscation procedures
* Helped to port scripts to Linux
* Developer and maintainer of the MCP chan bot
* Is now bald after working too much with java constant pool and re-obfuscation

IngisKahn
* Creator of the bytecode compare tool that helps us to update the name mappings 
quickly for new minecraft versions
* Contributed to the de-obfuscation spreadsheet

Generic
* Works on improving IngisKahn's bytecode compare tool
* Added some important features to retroguard

Fesh0r
* php/sql code monkey
* MCP 2.6/2.7 class mappings, patches, and general release work
* Has Searge's approval to make official MCP releases ;)

fotoply
* Helped to improve the batch files

Cadde
* Community manager and Wiki manager
* Works on the de-obfuscation spreadsheet
* Mod support (making old mods work with MCP)
* All round handyman

Vaprtek
* Works on the de-obfuscation spreadsheet
* Knows how to make pet creepers

gronk
* Script support

n00bish
* Linux script maintenance

Sage Pourpre
* His thread in the forums inspired me (Searge) to create this toolpack in the first place

Tei
* Supported the MCP project since the first version was released

spec10
* The new linux scripts guy

Head
* Wiki contributor / Administrator
* Explains classes and their members on the Wiki

MissLil
* Various scripting stuff
* Lots of reverse engineering
* OpenGL constants annoting

ScottyDoesKnow
* obfuscathonCharmer, the obfuscathon GUI

Chase
* MCP Launcher Work
* External jar loading
* Scrollable mod list.

303
* Wiki contributor
* Tries to help out newbies in the IRC channels
* Created some scripts for mod loader support

ZeuX
* Helps out in the IRC channels, constantly fails at solving modloader issues.
* Named most new classes for MCP 2.9
* Did server patches for the most recent versions - if you run into any (patch-related) problems, it's his fault

Risugami
* The guy who created the first mods I (Searge) ever used in Minecraft
* The creator of modloader who gave us permission to include files from his system in MCP

and of course:
- Everybody who contributed to the great google spreadsheet or who created some mods (I've got them all :).
- NOTCH for creating a game that is just awesome, I hope he does not feel offended by our decompiling efforts.

MCP log:
========
2.12 - Updated to support Minecraft 1.5_01 and MinecraftServer 1.5_02<br>
2.11 - Updated to support Minecraft 1.4_01 and MinecraftServer 1.4_01<br>
2.10 - Updated to support Minecraft 1.4 and MinecraftServer 1.4<br>
2.9a - Added MCP Mod System for 1.3_01, added mod loader support, updated mappings<br>
2.9  - Updated to support Minecraft 1.3_01 and MinecraftServer 1.3<br>
2.8  - Added the MCP mod systemHistory SDK and support for OSX<br>
2.7  - Updated to support Minecraft 1.2_02 and MinecraftServer 1.2_01<br>
2.6  - Updated to support Minecraft 1.1_02 and MinecraftServer 1.1_02<br>
2.5  - Updated to support Minecraft 1.2.6 and MinecraftServer 0.2.8<br>
2.4  - Updated to support Minecraft 1.2.5 and MinecraftServer 0.2.7<br>
2.3  - Updated to support Minecraft 1.2.3_04 and MinecraftServer 0.2.5_02. Linux support beta.<br>
2.2a - Bugfix release to improve the re-obfuscation tools<br>
2.2  - The reobfuscation beta test release. Still for Minecraft 1.2.2<br>
2.1  - Updated to support Minecraft 1.2.2<br>
2.0a - Bugfix release<br>
2.0  - Major updates to MCP and support for post-Halloween versions of Minecraft<br>
1.6  - All classes have meaningful names now, the class name mappings and the field name mappings are applied<br>
1.5  - Extend the scripts to also support decompiling, recompiling and testing the minecraft_server.jar file<br>
1.4  - Using a deobfuscator to rename all fields and methods and jadretro to fix some decompile bugs<br>
1.3  - Added upgrade scripts to decompile and recompile Minecraft.class, MinecraftApplet.class and MinecraftServer.class<br>
1.2  - Redirect output of all tools to a logfile<br>
1.1  - Fixed TNT bug<br>
1.0  - First release

Roadmap:
========
3.0  - An improved set of tools and an updated version of the MCP Mod System<br>
3.1+ - New awesome features, improvements and updates :)