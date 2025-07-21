package code;

import graphicalUserInterface.Gui;
import testmodus.Testmodus;

import java.util.Scanner;
import java.util.Random;


public class MenschAergerDichNicht  {

    Scanner eingabe = new Scanner(System.in);
    Random zufall = new Random();

    Spieler[] spielerObjekte = new Spieler[4];
    Spielfeld spielfeldObjekt = new Spielfeld();
    boolean spielerGewonnen = false;
    int spielerAmZug=zufall.nextInt(4);
    int ausgewaehlteFigurNummer;
    int wuerfelZahl;
    int[] neuePositionen= new int[4];


    public void ablauf()
    {
    	Testmodus testmodus = new Testmodus();
    	
    	Gui Gui=new Gui();
        
        spielfeldObjekt.hilfsArraysErstellen();
        spielerObjektErstllung(Gui.getAnzahlSpieler(), Gui.getSpielerNamen());
        	
        Gui.setSpielerArray(spielerObjekte);
        spielfeldObjekt.hausBefuellen(spielerObjekte);
        Gui.spielFeldAktualisieren();

        while(spielerGewonnen==false)
        {
        	Gui.resetWuerfelAnzeige();
        	Gui.setAktuelleSpielerAmZugGUI(spielerObjekte[spielerAmZug].getName(), spielerAmZug);       	

            if(spielerObjekte[spielerAmZug].getAnzahlDraussen()==0)
            {
                for(int x=0; x<3; x++){
                    if(spielerObjekte[spielerAmZug].getAnzahlDraussen()==0)
                    {
                        if(spielerObjekte[spielerAmZug].getStatus()==true)
                        {
                            wuerfelZahl= Gui.getWuerfelZahl();
                            Gui.setWuerfelFeld(wuerfelZahl);
                        }
                        else if(spielerObjekte[spielerAmZug].getStatus()==false){
                            wuerfelZahl = zufall.nextInt(6)+1;
                            Gui.setWuerfelFeld(wuerfelZahl);
                        }

                        if(wuerfelZahl==6)
                        {
                            boolean zugLegal=false;

                            if(istEinZugMoeglich()==true) { 
                                while (zugLegal == false) {

                                    if(spielerObjekte[spielerAmZug].getStatus()==false)
                                    {
                                    	ausgewaehlteFigurNummer = spielfeldObjekt.kIFigurenAuswahl(spielerAmZug, wuerfelZahl, spielerObjekte);
                                    }
                                    else if(spielerObjekte[spielerAmZug].getStatus()==true)	
                                    {
                                    	ausgewaehlteFigurNummer = Gui.getAngeklicktFigurnummer(spielerAmZug+1);
                                    }

                                    zugLegal = spielfeldObjekt.zugPruefen(spielerAmZug + 1, ausgewaehlteFigurNummer, wuerfelZahl, spielerObjekte[spielerAmZug].getFigurPosition((ausgewaehlteFigurNummer)), spielerObjekte[spielerAmZug].getAnzahlDraussen());
                                }
                                neuePositionen = spielfeldObjekt.rueckgabe(spielerAmZug + 1, ausgewaehlteFigurNummer, wuerfelZahl, spielerObjekte[spielerAmZug].getFigurPosition(ausgewaehlteFigurNummer), spielerObjekte[spielerAmZug].getAnzahlDraussen(), spielerObjekte);

                                if(spielerObjekte[spielerAmZug].getFigurPosition(ausgewaehlteFigurNummer)<0) {
                                	spielerObjekte[spielerAmZug].anzahlDraussenErhoehen();
                                }   
                                if(neuePositionen[1]!=1000) {
                                	Gui.bewegeButton(neuePositionen[0],neuePositionen[1]*(-1)+73);
                                	spielerObjekte[neuePositionen[2]-1].anzahlDraussenVerringern();
                                }
                                
                                Gui.bewegeButton(berechneAltenIndex(spielerAmZug,spielerObjekte[spielerAmZug].getFigurPosition(ausgewaehlteFigurNummer),neuePositionen),neuePositionen[0]);

                                spielfeldObjekt.zugAusfuehren(spielerAmZug + 1, ausgewaehlteFigurNummer, wuerfelZahl, spielerObjekte[spielerAmZug].getFigurPosition(ausgewaehlteFigurNummer), spielerObjekte[spielerAmZug].getAnzahlDraussen(), spielerObjekte, neuePositionen);
                                spielerfigurenpositionAktualisieren(spielerAmZug, ausgewaehlteFigurNummer, neuePositionen);
                                Gui.spielFeldAktualisieren();
                            }
                        }
                    }
                }
            }
            if(spielerObjekte[spielerAmZug].getAnzahlDraussen()>0) {
                boolean zugLegal = false;
                boolean nochmalWuerfeln = true;      
                while(nochmalWuerfeln==true) {
	                nochmalWuerfeln=false;
                	if(spielerObjekte[spielerAmZug].getStatus()==true)
	                {
	                    wuerfelZahl= Gui.getWuerfelZahl();
                    	Gui.setWuerfelFeld(wuerfelZahl);
	                }
	                else if(spielerObjekte[spielerAmZug].getStatus()==false){
	                    wuerfelZahl = zufall.nextInt(6)+1;
	                    Gui.setWuerfelFeld(wuerfelZahl);
	                }
	                if (istEinZugMoeglich() == true) {
	                    while (zugLegal == false) {
	
	                        if(spielerObjekte[spielerAmZug].getStatus()==false)
	                        {
	                        	ausgewaehlteFigurNummer = spielfeldObjekt.kIFigurenAuswahl(spielerAmZug, wuerfelZahl, spielerObjekte);
	                        }
	                        else if(spielerObjekte[spielerAmZug].getStatus()==true)	
	                        {
	                        	ausgewaehlteFigurNummer = Gui.getAngeklicktFigurnummer(spielerAmZug+1);
	                        }
	                        zugLegal = spielfeldObjekt.zugPruefen(spielerAmZug + 1, ausgewaehlteFigurNummer, wuerfelZahl, spielerObjekte[spielerAmZug].getFigurPosition((ausgewaehlteFigurNummer)), spielerObjekte[spielerAmZug].getAnzahlDraussen());
	                        if(zugLegal==false ){
	                        }
	                    }
	                    neuePositionen = spielfeldObjekt.rueckgabe(spielerAmZug + 1, ausgewaehlteFigurNummer, wuerfelZahl, spielerObjekte[spielerAmZug].getFigurPosition(ausgewaehlteFigurNummer), spielerObjekte[spielerAmZug].getAnzahlDraussen(), spielerObjekte);
	                    
	                    if(spielerObjekte[spielerAmZug].getFigurPosition(ausgewaehlteFigurNummer)<0) {
	                    	spielerObjekte[spielerAmZug].anzahlDraussenErhoehen();
	                    }   
	                    if(neuePositionen[1]!=1000) {
	                    	Gui.bewegeButton(neuePositionen[0],((neuePositionen[1]*(-1))+73));
	                    	spielerObjekte[neuePositionen[2]-1].anzahlDraussenVerringern();	
	                    }
	                    Gui.bewegeButton(berechneAltenIndex(spielerAmZug,spielerObjekte[spielerAmZug].getFigurPosition(ausgewaehlteFigurNummer),neuePositionen),neuePositionen[0]);

	                    spielfeldObjekt.zugAusfuehren(spielerAmZug + 1, ausgewaehlteFigurNummer, wuerfelZahl, spielerObjekte[spielerAmZug].getFigurPosition(ausgewaehlteFigurNummer), spielerObjekte[spielerAmZug].getAnzahlDraussen(), spielerObjekte, neuePositionen);
	                    spielerfigurenpositionAktualisieren(spielerAmZug, ausgewaehlteFigurNummer, neuePositionen);
	                    Gui.spielFeldAktualisieren();
	
	                    if(wuerfelZahl==6) {
	                    	nochmalWuerfeln=true;
	                    	zugLegal=false;
	                    }
	                }
                }   
            }         
            if(spielfeldObjekt.spielerGewonnen(spielerAmZug)==true) {
            	spielerGewonnen=true;
            	Gui.setGewinner(spielerObjekte[spielerAmZug].getName(), spielerAmZug); 	
            }

            spielerAmZug = (spielerAmZug + 1) % 4;
            Gui.spielFeldAktualisieren();                        
        }
    }


    public void spielerObjektErstllung(int spielerzahl, String[] spielerNamen){
        String[] farben = new String[4];
        farben[0]="Blau";
        farben[1]="Gelb";
        farben[2]="Gruen";
        farben[3]="Rot";

        for(int i=0;i<spielerzahl;i++){
            spielerObjekte[i]=new Spieler(i+1, spielerNamen[i], farben[i], true);
        }
        if(spielerzahl<4){
            for(int i=spielerzahl; i<4; i++){
                spielerObjekte[i]=new Spieler(i+1, spielerNamen[i], farben[i], false);
            }
        }
    }

    public boolean istEinZugMoeglich()
    {
        int anzahlMoeglicherZuege = 0;
        for(int i=1;i<5;i++)
        {
            if(spielfeldObjekt.zugPruefen(spielerAmZug+1, i,wuerfelZahl,spielerObjekte[spielerAmZug].getFigurPosition(i),spielerObjekte[spielerAmZug].getAnzahlDraussen())==true){
                anzahlMoeglicherZuege++;
            }
        }      
        if(anzahlMoeglicherZuege>0){
            return true;
        }
        else {
            return false;
        }
    }

    public Spieler[] getSpielerObjekte(){
        return spielerObjekte;
    }

    public void spielerfigurenpositionAktualisieren(int spielerAmZug, int figurennummer, int[] neuePositionen){
        
    	spielerObjekte[spielerAmZug].setFigurPosition(figurennummer, neuePositionen[0]);

        if(neuePositionen[1]!=1000) {
            spielerObjekte[neuePositionen[2]-1].setFigurPosition(neuePositionen[3], neuePositionen[1]);
        }
    }

    public int berechneAltenIndex(int spielerAmZug, int ziehFigurenposition, int[] neuePositionen){
    	if(ziehFigurenposition<0){
            return ziehFigurenposition*-1+73;     
        }else if(ziehFigurenposition>=0){
            return ziehFigurenposition;
        }
        return 500;

    }
}