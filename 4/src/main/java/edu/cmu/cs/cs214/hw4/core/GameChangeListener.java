package edu.cmu.cs.cs214.hw4.core;

public interface GameChangeListener {


    void updateBoardPanel();


    void updatePlayerCommandPanel();

    void updateAll();

    void setTrue();

    void setGameMsgLabel(String var1);

    void removeSettingPanel();

    void setPlacementInstruction(int var1, int var2, int var3, int var4);


}
