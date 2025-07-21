package testmodus;
import code.Spieler;
import code.Spielfeld;

public class Testmodus extends Spielfeld{

    public Testmodus(){
    	Spielfeld testmodusSpielfeld = new Spielfeld();
    	testmodusSpielfeld.hilfsArraysErstellen();
    	int[] figurenPositionen = new int[4];
    	int wuerfelzahl;
    	int testVariable=0;
    	Spieler[] temp1 = new Spieler[4];
    	
    	
    	System.out.println("\nFall 1: 0 Figuren draussen, bei !6 Wuerfelzahl darf man nicht aus Home -> Zug ueberspringen.");
    	wuerfelzahl=2;
    	for(int i=0; i<4; i++) {
    		figurenPositionen[i]=(-1*(i+1));
    		if(testmodusSpielfeld.zugPruefen(1,i+1, wuerfelzahl, figurenPositionen[i],0)==false) {
    			testVariable++;
    		}
    	}
    	if(testVariable==4) {
    		System.out.println("Fall 1: true");
    	} else {
    		System.out.println("Fall 1: false");
    	}
    	
    	
    	System.out.println("\nFall 2: 0 Figuren draussen, bei Wuerfelzahl 6 muss man aus Home, egal welche. Danach darf man nochmal Wuerfeln.");
    	wuerfelzahl=6;
    	testVariable=0;
    	for(int i=0; i<4; i++) {
    		testmodusSpielfeld.setFigurenHomeFeldTestmodus(figurenPositionen[i], 1,(i+1));
    		if(testmodusSpielfeld.zugPruefen(1,i+1, wuerfelzahl, figurenPositionen[i],0)==true){
    			testmodusSpielfeld.zugAusfuehren(1,i+1,wuerfelzahl,figurenPositionen[i],0,temp1, testmodusSpielfeld.rueckgabe(1, i+1, wuerfelzahl, figurenPositionen[i], 0, temp1));
    			if(testmodusSpielfeld.getSpielfelderTestmodus()[9]!=null && testmodusSpielfeld.getHomefelderTestmodus()[figurenPositionen[i]*(-1)-1]==null) {
    				testVariable++;
    				testmodusSpielfeld.setFigurenSpielfeldTestmodus(9, 5, i);
    			}
    		}
    	}
    	if(testVariable==4) {
    		System.out.println("Fall 2: true");
    	} else {
    		System.out.println("Fall 2: false");
    	}
    	
    	
    	System.out.println("\nFall 3: 0<x<4 Figur draussen, bei Wuerfelzahl 6 muss man aus Home, egal welche. Danach darf man nochmal Wuerfeln.");
    	wuerfelzahl=6;
    	testVariable=0;
    	for(int i=0; i<4; i++) {
    		testmodusSpielfeld.setFigurenHomeFeldTestmodus(figurenPositionen[i], 1,(i+1));
    		if(testmodusSpielfeld.zugPruefen(1,i+1, wuerfelzahl, figurenPositionen[i],1)==true){
    			testmodusSpielfeld.zugAusfuehren(1,i+1,wuerfelzahl,figurenPositionen[i],1,temp1, testmodusSpielfeld.rueckgabe(1, i+1, wuerfelzahl, figurenPositionen[i], 1, temp1));
    			if(testmodusSpielfeld.getSpielfelderTestmodus()[9]!=null && testmodusSpielfeld.getHomefelderTestmodus()[figurenPositionen[i]*(-1)-1]==null) {
    				testVariable++;
    				testmodusSpielfeld.setFigurenSpielfeldTestmodus(9, 5, i);
    			}
    		}
    	}
    	if(testVariable==4) {
    		System.out.println("Fall 3: true");
    	} else {
    		System.out.println("Fall 3: false");
    	}
    	
    	
    	System.out.println("\nFall 4: 1 draussen, Figur normal ziehen.");
    	testmodusSpielfeld.resetSpielfelderTestmodus();
    	testmodusSpielfeld.resetHomefelderTestmodus();
    	wuerfelzahl=5;
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(29, 1, 2);
    	if(testmodusSpielfeld.zugPruefen(1,2, wuerfelzahl, 29, 1 )==true) {
    		testmodusSpielfeld.zugAusfuehren(1, 2, wuerfelzahl, 29, 1, temp1, testmodusSpielfeld.rueckgabe(1, 2, wuerfelzahl, 29, 1, temp1));
    		if(testmodusSpielfeld.getSpielfelderTestmodus()[29]==null && testmodusSpielfeld.getSpielfelderTestmodus()[34]!=null 
    				&& testmodusSpielfeld.getSpielfelderTestmodus()[34].getBesitzer()==1 && testmodusSpielfeld.getSpielfelderTestmodus()[34].getFigurNummer()==2)
    		{
    			System.out.println("Fall 4: true");
    		}
    	} else {
    		System.out.println("Fall 4: false");
    	}
    	
    	
    	System.out.println("\nFall 5: Gegnerfigur wird von eigener Figur zurueck in sein Haus geschlagen.");
    	testmodusSpielfeld.resetSpielfelderTestmodus();
    	testmodusSpielfeld.resetHomefelderTestmodus();
    	testmodusSpielfeld.hilfsArraysErstellen();
    	wuerfelzahl=3;
    	testVariable=0;
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(26, 1, 3);    	
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(29, 2, 1); 
    	if(testmodusSpielfeld.zugPruefen(1,3, wuerfelzahl, 26, 1)==true) {
    		testmodusSpielfeld.zugAusfuehren(1, 3, wuerfelzahl, 26, 1, temp1, testmodusSpielfeld.rueckgabe(1, 3, wuerfelzahl, 26, 1, temp1));
    		if(testmodusSpielfeld.getSpielfelderTestmodus()[26]==null && testmodusSpielfeld.getSpielfelderTestmodus()[29]!=null 
    				&& testmodusSpielfeld.getSpielfelderTestmodus()[29].getBesitzer()==1 && testmodusSpielfeld.getSpielfelderTestmodus()[29].getFigurNummer()==3
    				&& testmodusSpielfeld.getHomefelderTestmodus()[4]!=null && testmodusSpielfeld.getHomefelderTestmodus()[4].getBesitzer()==2
    				&& testmodusSpielfeld.getHomefelderTestmodus()[4].getFigurNummer()==1){
    			System.out.println("Fall 5: true");
    		}
    	} else {
    		System.out.println("Fall 5: false");
    	}

    	
    	System.out.println("\nFall 6: Eigene Figur zu schlagen ist Illegal.");
    	testmodusSpielfeld.resetSpielfelderTestmodus();
    	testmodusSpielfeld.resetHomefelderTestmodus();
    	wuerfelzahl=3;
    	testVariable=0;
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(26, 1, 3);    	
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(29, 1, 1); 
    	if(testmodusSpielfeld.zugPruefen(1,3, wuerfelzahl, 26, 1)==false) {
    		System.out.println("Fall 6: true");
    	}else {
    		System.out.println("Fall 6: false");
    	}
    	
  	 
    	System.out.println("\nFall 7: 0<x<4 Figur draussen, bei Wuerfelzahl 6, wenn Startfeld von eigener Figur besetzt, muss man diese anstelle Ziehen.");
    	testmodusSpielfeld.resetSpielfelderTestmodus();
    	testmodusSpielfeld.resetHomefelderTestmodus();
    	wuerfelzahl=6;
    	testVariable=0;

    	testmodusSpielfeld.setFigurenHomeFeldTestmodus(-1, 1, 1);
    	testmodusSpielfeld.setFigurenHomeFeldTestmodus(-2, 1, 2);
    	testmodusSpielfeld.setFigurenHomeFeldTestmodus(-3, 1, 3);
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(9, 1, 4);
    	
    	for(int i=0; i<3; i++) {
    		if(testmodusSpielfeld.zugPruefen(1,i+1, wuerfelzahl, (-1)*(i+1),1)==false) {
    			testVariable++;
    		}
    	}
    	if(testVariable==3 && testmodusSpielfeld.zugPruefen(1,4, wuerfelzahl, 9,1)==true) {
    		testmodusSpielfeld.zugAusfuehren(1, 4, wuerfelzahl, 9, 1, temp1, testmodusSpielfeld.rueckgabe(1, 4, wuerfelzahl, 9, 1, temp1));
    		if(testmodusSpielfeld.getSpielfelderTestmodus()[9]==null && testmodusSpielfeld.getSpielfelderTestmodus()[15]!=null 
    				&& testmodusSpielfeld.getSpielfelderTestmodus()[15].getBesitzer()==1 && testmodusSpielfeld.getSpielfelderTestmodus()[15].getFigurNummer()==4){
    			System.out.println("Fall 7: true");
    		}
    	} else {
    		System.out.println("Fall 7: false");
    	}
    	

    	System.out.println("\nFall 8: Figur in safeFeld ziehen.");
    	testmodusSpielfeld.resetSpielfelderTestmodus();
    	testmodusSpielfeld.resetHomefelderTestmodus();
    	wuerfelzahl=3;
    	testVariable=0;
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(8, 1, 4);
    	if(testmodusSpielfeld.zugPruefen(1,4, wuerfelzahl, 8, 1)==true) {
    		testmodusSpielfeld.zugAusfuehren(1, 4, wuerfelzahl, 8, 1, temp1, testmodusSpielfeld.rueckgabe(1, 4, wuerfelzahl, 8, 1, temp1));
    		if(testmodusSpielfeld.getSpielfelderTestmodus()[8]==null && testmodusSpielfeld.getSpielfelderTestmodus()[42]!=null
    				&& testmodusSpielfeld.getSpielfelderTestmodus()[42].getBesitzer()==1 && testmodusSpielfeld.getSpielfelderTestmodus()[42].getFigurNummer()==4) {
    			System.out.println("Fall 8: true");
    		}
    	} else {
    		System.out.println("Fall 8: true");
    	}
    	

    	System.out.println("\nFall 9: Wenn kein Zug moeglich ist wird der Spieler ubersprungen.");
    	testmodusSpielfeld.resetSpielfelderTestmodus();
    	testmodusSpielfeld.resetHomefelderTestmodus();

    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(8, 1, 1);    
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(3, 1, 2); 
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(38, 1, 3); 
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(33, 1, 4); 
    	
    	wuerfelzahl=5;
    	testVariable=0;
    	
    	for(int i=0; i<4; i++)
    	{
    		if(testmodusSpielfeld.zugPruefen(1, i+1, wuerfelzahl, (33+5*i)%40, 4)==false) {
    			testVariable++;
    		}
    	}
    	if(testVariable==4) {
    		System.out.println("Fall 9: true");
    	} else {
    		System.out.println("Fall 9: false");
    	}
    	
    	
    	System.out.println("\nFall 10: Gewinner feststellen.");
        testmodusSpielfeld.resetSpielfelderTestmodus();
        testmodusSpielfeld.resetHomefelderTestmodus();
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(40, 1, 1);    
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(41, 1, 2); 
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(42, 1, 3); 
    	testmodusSpielfeld.setFigurenSpielfeldTestmodus(43, 1, 4); 
    	testVariable=0;
    	
        for(int i=0;i<4;i++) {
        	if(testmodusSpielfeld.getSpielfelderTestmodus()[(1-1)*10+40+i]!=null){
        		testVariable++;
            }
        }
        	if(testVariable==4) {
                System.out.println("Fall 10: true");
            }
            else{
            	System.out.println("Fall 10: false");
            }
        

    	System.out.println("\nFall 11 (Bot): Prioritaet 1. 0 Figuren draussen, bei Wuerfelzahl 6 eine Figur aus Home ausgewaehlt werden, egal welche.");
    	Spieler[] spielerArray = new Spieler[4];
    	Spieler botSpieler = new Spieler(4, "SpielerBot1", "rot", false);
        
    	spielerArray[3]=botSpieler;
    	testmodusSpielfeld.resetSpielfelderTestmodus();
        testmodusSpielfeld.resetHomefelderTestmodus();
    	wuerfelzahl=6;
    	if(1<=(testmodusSpielfeld.kIFigurenAuswahl(3, wuerfelzahl, spielerArray)) && (testmodusSpielfeld.kIFigurenAuswahl(3, wuerfelzahl, spielerArray))<=4) {
    		System.out.println("Fall 11: true");
    	} else {
    		System.out.println("Fall 11: false");
    	}
    
    	
    	System.out.println("\nFall 12 (Bot): Prioritaet 1. 1<=x<=4 Figuren draussen, bei Wuerfelzahl 6 soll eine Figur aus dem Home ausgewaehlt werden.");
    	testmodusSpielfeld.resetSpielfelderTestmodus();
        testmodusSpielfeld.resetHomefelderTestmodus();
        testmodusSpielfeld.setFigurenSpielfeldTestmodus(11, 1, 1);
        testmodusSpielfeld.setFigurenSpielfeldTestmodus(5, 4, 1);
        testmodusSpielfeld.setFigurenSpielfeldTestmodus(36, 4, 2);
    	botSpieler.setFigurPosition(1, 5);
    	botSpieler.setFigurPosition(2,36);
    	spielerArray[3]=botSpieler;
    	wuerfelzahl=6;
    	
    	if(2<(testmodusSpielfeld.kIFigurenAuswahl(3, wuerfelzahl, spielerArray)) && (testmodusSpielfeld.kIFigurenAuswahl(3, wuerfelzahl, spielerArray))<=4) {
    		System.out.println("Fall 12: true");
    	} else {
    		System.out.println("Fall 12: false");
    	}
    	
    	
		System.out.println("\nFall 13 (Bot): Prioritaet 2. Figur in das safeFeld ziehen.");
		testmodusSpielfeld.resetSpielfelderTestmodus();
		testmodusSpielfeld.resetHomefelderTestmodus();
		testmodusSpielfeld.setFigurenSpielfeldTestmodus(5, 4, 1);
		testmodusSpielfeld.setFigurenSpielfeldTestmodus(38, 4, 2);
		testmodusSpielfeld.setFigurenSpielfeldTestmodus(3, 4, 3);
		testmodusSpielfeld.setFigurenHomeFeldTestmodus(-16,4, 4);
		testmodusSpielfeld.setFigurenSpielfeldTestmodus(8, 1, 1);
		botSpieler.setFigurPosition(2, 38);
		botSpieler.setFigurPosition(3, 2);
		spielerArray[3]=botSpieler;
		wuerfelzahl=3;
		
		if(testmodusSpielfeld.kIFigurenAuswahl(3, wuerfelzahl, spielerArray)==2) {
			if(testmodusSpielfeld.zugPruefen(4,2, wuerfelzahl, 38, 3)==true) {
				testmodusSpielfeld.zugAusfuehren(4, 2, wuerfelzahl, 38, 3, temp1, testmodusSpielfeld.rueckgabe(4, 2, wuerfelzahl, 38, 3, temp1));
				if(testmodusSpielfeld.getSpielfelderTestmodus()[72]!=null
					&& testmodusSpielfeld.getSpielfelderTestmodus()[72].getBesitzer()==4 && testmodusSpielfeld.getSpielfelderTestmodus()[72].getFigurNummer()==2){
					System.out.println("Fall 13: true");
				}
			}
		} 
		else {
		System.out.println("Fall 13: false");
		}
		
		
		System.out.println("\nFall 14 (Bot): Prioritaet 3. gegnerische Figur schlagen.");
		testmodusSpielfeld.resetSpielfelderTestmodus();
		testmodusSpielfeld.resetHomefelderTestmodus();
		testmodusSpielfeld.setFigurenSpielfeldTestmodus(5, 4, 1);
		testmodusSpielfeld.setFigurenSpielfeldTestmodus(33, 4, 2);
		testmodusSpielfeld.setFigurenSpielfeldTestmodus(3, 4, 3);
		testmodusSpielfeld.setFigurenHomeFeldTestmodus(-16, 4, 4);
		testmodusSpielfeld.setFigurenSpielfeldTestmodus(8, 1, 1);
		botSpieler.setFigurPosition(2, 33);
		botSpieler.setFigurPosition(3, 2);
		spielerArray[3]=botSpieler;
		wuerfelzahl=3;
		
		if(testmodusSpielfeld.kIFigurenAuswahl(3, wuerfelzahl, spielerArray)==1) {
			if(testmodusSpielfeld.zugPruefen(4, 1, wuerfelzahl, 5, 3)==true) {
				testmodusSpielfeld.zugAusfuehren(4, 1, wuerfelzahl, 5, 3, temp1, testmodusSpielfeld.rueckgabe(4, 1, wuerfelzahl, 5, 3, temp1));
				if(testmodusSpielfeld.getSpielfelderTestmodus()[5]==null && testmodusSpielfeld.getSpielfelderTestmodus()[8]!=null 
						&& testmodusSpielfeld.getSpielfelderTestmodus()[8].getBesitzer()==4 && testmodusSpielfeld.getSpielfelderTestmodus()[8].getFigurNummer()==1
						&& testmodusSpielfeld.getHomefelderTestmodus()[0].getBesitzer()==1 && testmodusSpielfeld.getHomefelderTestmodus()[0].getFigurNummer()==1) {
					System.out.println("Fall 14: true");				
				}
			}
		}else {
		System.out.println("Fall 14: false");
		}
		
		
		System.out.println("\nFall 15 (Bot): Prioritaet 4. Figur auf leeres Feld ziehen.");
		testmodusSpielfeld.resetSpielfelderTestmodus();
		testmodusSpielfeld.resetHomefelderTestmodus();
		testmodusSpielfeld.setFigurenSpielfeldTestmodus(5, 4, 1);
		botSpieler.setFigurPosition(1, 5);
		wuerfelzahl=5;
		if(testmodusSpielfeld.zugPruefen(4, 1, wuerfelzahl, 5, 3)==true) {
			testmodusSpielfeld.zugAusfuehren(4, 1, wuerfelzahl, 5, 1, temp1, testmodusSpielfeld.rueckgabe(4, 1, wuerfelzahl, 5, 1, temp1));
			if(testmodusSpielfeld.getSpielfelderTestmodus()[10]!=null && testmodusSpielfeld.getSpielfelderTestmodus()[5]==null) {
				if(testmodusSpielfeld.getSpielfelderTestmodus()[10].getBesitzer()==4 && testmodusSpielfeld.getSpielfelderTestmodus()[10].getFigurNummer()==1) {
					System.out.println("Fall 15: true");
				}
			}
		}else {
			System.out.println("Fall 15: false");
		}
    }
}