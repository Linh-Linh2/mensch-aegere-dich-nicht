package code;

import java.util.Random;

public class Spieler {

    static Random zufall = new Random();
    private  Figur[] Figuren = new Figur[4];

    private
    String name;
    String farbe;
    int spielerNummer;
    int startpunkt;
    int anzahlDraussen = 0;
    boolean statusIstSpieler;

    public Spieler(int spielerNummer, String name, String farbe, boolean statusIstSpieler)
    {
        this.name=name;
        this.spielerNummer=spielerNummer;
        this.farbe=farbe;
        this.startpunkt=spielerNummer*10-1;
        this.statusIstSpieler=statusIstSpieler;
        for(int i=1; i<=4; i++)
        {
            Figuren[i-1]=new Figur((-1)*((4*(spielerNummer-1))+i), spielerNummer, i);
        }
    }

    public String getName() {
    	return name;
    }
    
    public boolean getStatus(){
        return statusIstSpieler;
    }

    public int ziehen(int figurenNummer, int würfelNummer)
    {
        int neuePosition=0;
        return neuePosition;
    }

    public int getFigurPosition(int Figurennummer)
    {
        return Figuren[Figurennummer-1].getPosition();
    }

    public void setFigurPosition(int figurennummer, int neuePosition)
    {
        Figuren[figurennummer-1].setPostition(neuePosition);
    }

    public int auswaehlen()
    {
        return 0;
    }

    public void setName(String name)
    {
        this.name=name;
    }
    
    public void setFarbe(String farbe)
    {
        this.farbe=farbe;
    }
    
    public int getAnzahlDraussen()
    {
        return anzahlDraussen;
    }
    
    public void anzahlDraussenErhoehen()
    {
        this.anzahlDraussen=anzahlDraussen+1;
    }
    
    public void anzahlDraussenVerringern()
    {
    	 this.anzahlDraussen=anzahlDraussen-1;
    }

    public Figur[] getFigurArray()
    {
        return Figuren;
    }
}