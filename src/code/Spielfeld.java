package code;

public class Spielfeld
{
    private Figur[] spielfelder = new Figur[74];
    private Figur[] homeFelder = new Figur[16];

    private int[] grenzen1 = new int [44];
    private int[] grenzen2 = new int [44];
    private int[] grenzen3 = new int [44];
    private int[] grenzen4 = new int [44];

    public boolean zugPruefen(int spielernummer, int figurennummer, int wuerfelzahl, int figurenPosition, int anzahlDraussen)
    {
        switch (anzahlDraussen) {               	
        
            case 0:
                if(wuerfelzahl==6) {
                	return true;
                }
            
            case 1, 2, 3:
                if (wuerfelzahl == 6)
                {
                	if(figurenPosition<0) {
                		
                		if(spielfelder[spielernummer * 10 - 1] == null){
                			return true;
                		}
                		else if(spielfelder[spielernummer * 10 - 1] != null)
                			if(spielfelder[spielernummer * 10 - 1].getBesitzer() == spielernummer) {
                				return false;
                            }else if(spielfelder[spielernummer * 10 - 1].getBesitzer() != spielernummer) {
                            	return true;
                            }
                	}	
                	else if(figurenPosition==(spielernummer * 10 - 1)){
                		return true;
                	}	
                	else if(figurenPosition!=(spielernummer*10-1)) {
                		return false;
                	}
                }
            
            	if (wuerfelzahl!=6) {
            		if(figurenPosition<0) {
            			return false;
            		}
            		
            		else if(figurenPosition>0) {
            			if((getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl)<=39) {
            				
            				if(spielfelder[(figurenPosition+wuerfelzahl)%40]==null) {
            					return true;
            				}
            				else if(spielfelder[(figurenPosition+wuerfelzahl)%40]!=null) {
            					if(spielfelder[(figurenPosition+wuerfelzahl)%40].getBesitzer()==spielernummer) {
            						return false;
            					}
            					else if(spielfelder[(figurenPosition+wuerfelzahl)%40].getBesitzer()!=spielernummer) {
            						return true;
            					}
            				}
            			}
            												 
            			else if( ((getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl)>39) &&  (((getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl)<=43))){
               				int safeFeldIndex=0;
               				if(spielernummer==1) {
               					safeFeldIndex=grenzen1[getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
               				}
               				else if(spielernummer==2) {
               					safeFeldIndex=grenzen2[getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
               				}	
               				else if(spielernummer==3) {
               					safeFeldIndex=grenzen3[getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
               				}	
               				else if(spielernummer==4) {
               					safeFeldIndex=grenzen4[getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
               				}
            				
            				if(spielfelder[safeFeldIndex]==null) {
            					return true;
            				}
            				else if(spielfelder[safeFeldIndex]!=null) {
            					return false;
            				}            			
            			}
            		}
            	}            	
            
            case 4:      		
    			if((getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl)<=39) {
    				
    				if(spielfelder[(figurenPosition+wuerfelzahl)%40]==null) {
    					return true;
    				}
    				else if(spielfelder[(figurenPosition+wuerfelzahl)%40]!=null) {
    					if(spielfelder[(figurenPosition+wuerfelzahl)%40].getBesitzer()==spielernummer) {
    						return false;
    					}
    					else if(spielfelder[(figurenPosition+wuerfelzahl)%40].getBesitzer()!=spielernummer) {
    						return true;
    					}
    				}
    			}
       			else if( ((getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl)>39) &&  (((getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl)<=43))){
       				int safeFeldIndex=0;
       				if(spielernummer==1) {
       					safeFeldIndex=grenzen1[getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
       				}
       				else if(spielernummer==2) {
       					safeFeldIndex=grenzen2[getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
       				}	
       				else if(spielernummer==3) {
       					safeFeldIndex=grenzen3[getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
       				}	
       				else if(spielernummer==4) {
       					safeFeldIndex=grenzen4[getIndex(spielernummer, figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
       				}
       				
       				if(spielfelder[safeFeldIndex]==null) {           			
       					return true;
       				}
       				else if(spielfelder[safeFeldIndex]!=null) {
       					return false;
       				}            			
       			}
            default:

        }
        return false;
    }

    public void hausBefuellen(Spieler[] SpielerArray){
        for(int i=0; i<4;i++) {
            for(int j=0; j<4; j++){

                homeFelder[(4*(i)+j)]=SpielerArray[i].getFigurArray()[j];
            }
        }
    }

    public void zugAusfuehren(int spielernummer, int figurennummer, int wuerfelZahl, int figurenposition, int anzahlDraussen, Spieler[] spielerObjekte, int[] positionen)
    {
        int[] neuePositionen=positionen;

        if(neuePositionen[1]==1000)
        {
            if(figurenposition>=0){
                spielfelder[neuePositionen[0]]=spielfelder[figurenposition];
                spielfelder[figurenposition]=null;
            }else if(figurenposition<0){
                spielfelder[spielernummer*10-1]=homeFelder[(figurenposition*(-1))-1];
                homeFelder[(figurenposition*(-1))-1]=null;
            }
        }
        else if(neuePositionen[1]!=1000) {
            if (figurenposition >= 0) {
                homeFelder[neuePositionen[1]*(-1)-1] = spielfelder[neuePositionen[0]];
                spielfelder[neuePositionen[0]] = spielfelder[figurenposition];
                spielfelder[figurenposition] = null;
            }
            else if(figurenposition<0)
            {
                homeFelder[neuePositionen[1]*(-1)-1]=spielfelder[neuePositionen[0]];
                spielfelder[neuePositionen[0]]=homeFelder[figurenposition*(-1)-1];
                homeFelder[figurenposition*(-1)-1]=null;
            }       
        }
    }





    public int[] rueckgabe(int spielernummer, int figurennummer, int wuerfelZahl, int figurenposition, int anzahlDraussen, Spieler[]  spielerObjekte) {
        int[] neuePositionen = new int[4];
        neuePositionen[1]=1000;

        if (figurenposition < 0) {
            neuePositionen[0]=spielernummer * 10 - 1;

            if(spielfelder[spielernummer * 10 - 1]!=null)
            {
                neuePositionen[1]=( (-1)* ((4* (spielfelder[spielernummer * 10 - 1].getBesitzer()-1)  + spielfelder[spielernummer * 10 - 1].getFigurNummer())));
                neuePositionen[2]=spielfelder[spielernummer * 10 - 1].getBesitzer();
                neuePositionen[3]=spielfelder[spielernummer * 10 - 1].getFigurNummer();
            }
        }
        
        else if( ((getIndex(spielernummer, figurenposition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelZahl)<=39) ) {
                neuePositionen[0]=(figurenposition+wuerfelZahl)%40;
                
                if( spielfelder[neuePositionen[0]]!=null){
                        neuePositionen[1]=((-1)*((4*(spielfelder[neuePositionen[0]].getBesitzer()-1))+spielfelder[neuePositionen[0]].getFigurNummer()));
                        neuePositionen[2]=spielfelder[neuePositionen[0]].getBesitzer();
                        neuePositionen[3]=spielfelder[neuePositionen[0]].getFigurNummer();          
                }
        }
        
        else if(((getIndex(spielernummer, figurenposition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelZahl)>=39) && ((getIndex(spielernummer, figurenposition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelZahl)<=43))
        {         	    
            if(spielernummer==1) {
            	neuePositionen[0]=grenzen1[getIndex(spielernummer, figurenposition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelZahl];
            } else if(spielernummer==2) {
            	neuePositionen[0]=grenzen2[getIndex(spielernummer, figurenposition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelZahl];
            } else if(spielernummer==3) {
            	neuePositionen[0]=grenzen3[getIndex(spielernummer, figurenposition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelZahl];
            } else if(spielernummer==4) {
            	neuePositionen[0]=grenzen4[getIndex(spielernummer, figurenposition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelZahl];
            }
        }
        return neuePositionen;
    }
    
    
    public boolean spielerGewonnen(int spielerAmZug)
    {
        int figurenImSafeSpace=0;

        for(int i=0;i<4;i++) {
            if(spielfelder[spielerAmZug*10+40+i]!=null){
                figurenImSafeSpace++;
            }
        }
        if(figurenImSafeSpace==4) {
            return true;
        }
        else{
            return false;
        }
    }

    public int kIFigurenAuswahl(int spielerAmZug, int wuerfelzahl, Spieler[] spielerArray){
    	int figurenPosition;
	    if(wuerfelzahl == 6 && spielerArray[spielerAmZug].getAnzahlDraussen()!=4){
	    	for(int i=0;i<4;i++) {
	        	if(spielerArray[spielerAmZug].getFigurArray()[i].getPosition()<0) {
	         		
	         		if(spielfelder[(spielerAmZug+1) * 10 - 1] == null){
	         			return i+1;
	         		} 
	         		else if(spielfelder[(spielerAmZug+1) * 10 - 1].getBesitzer()!=(spielerAmZug+1)) {
	         			return i+1;
	         		}
	         		else {
	         			return spielfelder[(spielerAmZug+1) * 10 - 1].getFigurNummer();
	         		}
	         	}
	        }
    	}
	    for(int i=0;i<4;i++){
	    	figurenPosition = spielerArray[spielerAmZug].getFigurArray()[i].getPosition();
	    	if((getIndex((spielerAmZug+1),figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl)>39 && (getIndex((spielerAmZug+1),figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl)<44){
	
   				int safeFeldIndex=0;
   				if((spielerAmZug+1)==1) {
   					safeFeldIndex=grenzen1[getIndex((spielerAmZug+1), figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
   				}
   				else if((spielerAmZug+1)==2) {
   					safeFeldIndex=grenzen2[getIndex((spielerAmZug+1), figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
   				}	
   				else if((spielerAmZug+1)==3) {
   					safeFeldIndex=grenzen3[getIndex((spielerAmZug+1), figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
   				}	
   				else if((spielerAmZug+1)==4) {
   					safeFeldIndex=grenzen4[getIndex((spielerAmZug+1), figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl];
   				}
	    		
   				if(spielfelder[safeFeldIndex]==null) {
   					return i+1;	
   				}
	    	}	
	    }
	    for(int i=0;i<4;i++){	
		    figurenPosition = spielerArray[spielerAmZug].getFigurArray()[i].getPosition();
		    if((getIndex((spielerAmZug+1),figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl)<=39
		    		&& spielfelder[(figurenPosition+wuerfelzahl)%40]!=null ){
		    	if(spielfelder[(figurenPosition+wuerfelzahl)%40].besitzer!=(spielerAmZug+1)) {
		    			return i+1;
		    	}
		    } 
	    }
	    
	    for(int i=0;i<4;i++){	
		    figurenPosition = spielerArray[spielerAmZug].getFigurArray()[i].getPosition();
		    if((getIndex((spielerAmZug+1),figurenPosition, grenzen1, grenzen2, grenzen3, grenzen4)+wuerfelzahl)<=39
		    	&& spielfelder[(figurenPosition+wuerfelzahl)%40]==null){
		    	return i+1;
		    }
	    }   
	    return 1;
    }   

    public void hilfsArraysErstellen(){

        for(int i=0;i<40;i++){

            grenzen1[i]=(9+i)%40;
            grenzen2[i]=(19+i)%40;
            grenzen3[i]=(29+i)%40;
            grenzen4[i]=(39+i)%40;
            
            grenzen1[40]=40;	grenzen1[41]=41;	grenzen1[42]=42;	grenzen1[43]=43;
            grenzen2[40]=50;	grenzen2[41]=51;	grenzen2[42]=52;	grenzen2[43]=53;
            grenzen3[40]=60;    grenzen3[41]=61;    grenzen3[42]=62;    grenzen3[43]=63;
            grenzen4[40]=70;    grenzen4[41]=71;    grenzen4[42]=72;    grenzen4[43]=73;
        }
    }
    
    public int getIndex(int spielernummer, int figurenPosition, int[] grenzen1, int[] grenzen2, int[] grenzen3, int[] grenzen4){
        int index=100;
        switch (spielernummer) {
        
	        case 1:	
		        for(int i=0;i<44;i++){	        
		            if(figurenPosition==grenzen1[i]){
		                 index = i;
		            }
		        }
		        return index;         
	       
	        case 2:
		        for(int i=0;i<44;i++){	        
		            if(figurenPosition==grenzen2[i]){
		                 index = i;
		            }
		        }
		        return index;  
	        case 3:
		        for(int i=0;i<44;i++){	        
		            if(figurenPosition==grenzen3[i]){
		                 index = i;
		            }
		        }
		        return index;  
	        case 4:
		        for(int i=0;i<44;i++){	        
		            if(figurenPosition==grenzen4[i]){
		                 index = i;
		            }
		        }
		        return index;   	      
		}
        return index;
    }
    
    public void setFigurenSpielfeldTestmodus(int position, int besitzer, int figurnummer){
    	
    	if(besitzer<5) {
    		spielfelder[position] = new Figur(position, besitzer, figurnummer);
    	}
    	else {
    		spielfelder[position] = null;
    	}
    	
    }
    public void setFigurenHomeFeldTestmodus(int position, int besitzer, int figurnummer) {
    	homeFelder[position*(-1)-1]=new Figur(position, besitzer, figurnummer);
    }
    public Figur[] getSpielfelderTestmodus() {
    	return spielfelder;
    }
    public Figur[] getHomefelderTestmodus() {
    	return homeFelder;
    }
    public void resetSpielfelderTestmodus() {
    	for(int i=0; i<74; i++) {
    		spielfelder[i]=null;
    	}
    }
    public void resetHomefelderTestmodus() {
    	for(int i=0; i<16; i++) {
    		homeFelder[i]=null;
    	}
    }
}