package graphicalUserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import code.Spieler;


public class Gui {

    private int[][] figurenAufFeld = new int[90][2];
    private int angeklicktFigurnummer;
    private int aktuelleWuerfelZahl;

    private JFrame fenster;

    private JPanel spielbereich;
    private JPanel knopfbereich;
    private JPanel wuerfelbereich;
    

    private JButton spielstart;
    private JButton wuerfel;
    
    private JButton anzahlSpielerButton = new JButton();
    private JButton spielerNamenButton = new JButton();
    private JButton figurButtonT = new JButton();

    private JLabel spielernachrichten;
    private JLabel wuerfelnachrichten;
    
    private JLabel spieler1AufFeld = new JLabel();
    private JLabel spieler2AufFeld	= new JLabel();
    private JLabel spieler3AufFeld	= new JLabel();
    private JLabel spieler4AufFeld	= new JLabel();
    
    private JFrame spielerNamen = new JFrame ("Spielernamen");
    private JTextField name1 = new JTextField();
    private JTextField name2 = new JTextField();
    private JTextField name3 = new JTextField();
    private JTextField name4 = new JTextField();
    private JLabel spieler1 = new JLabel("Spieler 1:");
    private JLabel spieler2 = new JLabel("Spieler 2:");
    private JLabel spieler3 = new JLabel("Spieler 3:");
    private JLabel spieler4 = new JLabel("Spieler 4:");
    private JLabel bot1 = new JLabel("Bot");
    private JLabel bot2 = new JLabel("Bot");
    private JLabel bot3 = new JLabel("Bot");
    private JButton okay = new JButton("OK");
    private String[] spielerNamenArray = new String[4];
    
    private JFrame win = new JFrame("Gewinner");
    private JPanel hintergrund = new JPanel();
    private JLabel gewinner = new JLabel();

    int anzahlSpieler;
    private Spieler[] spielerArray;
    private GuiSpielfeld spielfeld = new GuiSpielfeld();

    private JButton[] buttonAufSpielfeld = new JButton[90];
    
    
    Random zufall = new Random();

    public Gui() {

        fenster = new JFrame("Mensch Ärgere Dich Nicht");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setResizable(false);
        fenster.setSize(800,1000);
        fenster.setLocationRelativeTo(null);

        spielbereich = new JPanel();
        spielbereich.setBackground(Color.WHITE);
        spielbereich.setPreferredSize(new Dimension(800,800));

        knopfbereich = new JPanel(new GridLayout(1,2));
        knopfbereich.setBackground(Color.WHITE);
        knopfbereich.setPreferredSize(new Dimension(600,175));

        wuerfelbereich = new JPanel();
        wuerfelbereich.setBackground(Color.WHITE);
        wuerfelbereich.setPreferredSize(new Dimension(200,175));
        
        player1();
    	player2();
    	player3();
    	player4();

        spielstart = new JButton("start game");
        spielstart.setBackground(Color.WHITE);
        spielstart.setForeground(Color.BLACK);
        spielstart.setFont(new Font("Arial", Font.BOLD, 25));
        spielstart.setFocusable(false);

        wuerfel = new JButton();

        figurenButtonsErstellen();


		ImageIcon ico = new ImageIcon(getClass().getResource("/images/wuerfel.png"));
        wuerfel.setIcon(ico);
        ico.setImage(ico.getImage().getScaledInstance(255,220,Image.SCALE_DEFAULT));

        spielernachrichten = new JLabel();
        spielernachrichten.setBounds(590, 820, 150, 30);
        fenster.add(spielernachrichten);
        wuerfelnachrichten = new JLabel();
        wuerfelnachrichten.setBounds(550, 880, 200, 30);
        fenster.add(wuerfelnachrichten);

        knopfbereich.add(spielstart);
        knopfbereich.add(wuerfel);
        wuerfel.setEnabled(false);


        fenster.add(knopfbereich,BorderLayout.SOUTH);
        knopfbereich.add(wuerfelbereich,BorderLayout.EAST);
        fenster.add(spielbereich,BorderLayout.CENTER);


        wuerfelnachrichten.setFont(new Font(null,Font.CENTER_BASELINE,17));
        wuerfelnachrichten.setText("               Würfel");
        spielernachrichten.setFont(new Font(null,Font.CENTER_BASELINE,17));

        
        fenster.add(spielfeld);

        spielstart.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource()==spielstart) {
                            popUp();
                        }
                    }

                }
        );

        wuerfel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == wuerfel) {
                        	int zufallZahl=zufall.nextInt(6)+1;
                            wuerfelnachrichten.setFont(new Font(null,Font.CENTER_BASELINE,15));
                            aktuelleWuerfelZahl=zufallZahl;
                            synchronized (wuerfel){
                                wuerfel.notify();
                            }

                        }
                    }
                }
        );

        fenster.setVisible(true);
    }

    public void setSpielerArray(Spieler[] codeSpielerArray){
        spielerArray = codeSpielerArray;
    }

    public void setFeldFigurenInformationen(){
        for(int i=0;i<4;i++){
            int position;
            for(int j=0; j<4;j++){
                position=spielerArray[i].getFigurArray()[j].getPosition();
                if(position<0) {
                    figurenAufFeld[position*(-1)+73][0]=i+1;
                    figurenAufFeld[position*(-1)+73][1]=j+1;
                } else if(position>39 && position <=73) {
                    figurenAufFeld[position][0]=i+1;
                    figurenAufFeld[position][1]=j+1;
                } else if(position>=0 && position<=39){
                    figurenAufFeld[position][0]=i+1;
                    figurenAufFeld[position][1]=j+1;
                }
            }
        }
    }


    public void spielFeldAktualisieren(){
        setFeldFigurenInformationen();


    }
    public int getWuerfelZahl() {

        synchronized(wuerfel){
            try{
                wuerfel.wait();
            }catch(InterruptedException e){
            }
        }

        return aktuelleWuerfelZahl;
    }



    public void figurenButtonsErstellen(){
        for(int i=0; i<16;i++) {

            JButton figurButton = new JButton();
            int xKoord = (int) spielfeld.getXKoord(74 + i);
            int yKoord = (int) spielfeld.getYKoord(74 + i);

            figurButton.setBounds(xKoord, yKoord, 30, 30);

            if(i<4) {
                figurButton.setBackground(Color.BLUE);
            } else if(4<=i && i<8){
                figurButton.setBackground(Color.YELLOW);
            } else if(8<=i && i<12){
                figurButton.setBackground(Color.GREEN);
            } else if(12<=i && i<16){
                figurButton.setBackground(Color.RED);
            }

            figurButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));
            fenster.add(figurButton);

            figurButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() == figurButton) {
                                double[][] buttonKoordinaten = new double[1][2];
                                buttonKoordinaten[0][0]=figurButton.getX();
                                buttonKoordinaten[0][1]=figurButton.getY();
                                angeklicktFigurnummer=figurenAufFeld[spielfeld.buttonIndexFinden(buttonKoordinaten)][1];
                                synchronized (figurButtonT){
                                    figurButtonT.notify();
                                }
                            }
                        }
                    }
            );
            buttonAufSpielfeld[74 + i] = figurButton;
            fenster.add(figurButton);
        }
    }
    public int getAngeklicktFigurnummer(int Spielernummer){
                synchronized(figurButtonT) {
                    try {
                        figurButtonT.wait();
                    } catch (InterruptedException e) {
                    }
                }
        return angeklicktFigurnummer;
    }

    public void bewegeButton(int alterIndex, int neuerIndex)
    {
        int neueXKoord=(int)spielfeld.getXKoord(neuerIndex);
        int neueYKoord=(int)spielfeld.getYKoord(neuerIndex);
        buttonAufSpielfeld[alterIndex].setLocation(neueXKoord, neueYKoord);
        buttonAufSpielfeld[neuerIndex]=buttonAufSpielfeld[alterIndex];
    }
    
JFrame startSpieler = new JFrame("Wie viele Spieler");
    
    public void popUp() {
    	startSpieler.setLayout(new FlowLayout());;
    	startSpieler.setSize(300,110);
    	JButton eins = new JButton("1");
    	eins.setFocusable(false);
    	JButton zwei = new JButton("2");
    	zwei.setFocusable(false);
    	JButton drei = new JButton("3");
    	drei.setFocusable(false);
    	JButton vier = new JButton("4");
    	vier.setFocusable(false);
    	
    	startSpieler.add(eins);
    	startSpieler.add(zwei);
    	startSpieler.add(drei);
    	startSpieler.add(vier);
    	
    	eins.addActionListener(
    			new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==eins) {
							anzahlSpieler=1;
							startSpieler.dispose();
							spielstart.setEnabled(false);
							wuerfel.setEnabled(true);
                            synchronized (anzahlSpielerButton){
                            	anzahlSpielerButton.notify();
                            }
                            spielerNamenEins();
						}
					}
    			}
    	);
    	zwei.addActionListener(
    			new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==zwei) {
							anzahlSpieler=2;
							startSpieler.dispose();
							spielstart.setEnabled(false);
							wuerfel.setEnabled(true);
							synchronized (anzahlSpielerButton){
                            	anzahlSpielerButton.notify();
                            }
							spielerNamenZwei();
						}
					}
    			}
    	);
    	drei.addActionListener(
    			new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==drei) {
							anzahlSpieler=3;
							startSpieler.dispose();
							spielstart.setEnabled(false);
							wuerfel.setEnabled(true);
							synchronized (anzahlSpielerButton){
                            	anzahlSpielerButton.notify();
							}
                           	spielerNamenDrei();                      
						}
					}
    			}
    	);
    	vier.addActionListener(
    			new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==vier) {
							anzahlSpieler=4;
							startSpieler.dispose();
							spielstart.setEnabled(false);
							wuerfel.setEnabled(true);
							synchronized (anzahlSpielerButton){
                            	anzahlSpielerButton.notify();
							}	
							spielerNamenVier();
							
                            
						}
					}
    			}
    	);
    	
    	startSpieler.setLocationRelativeTo(null);
    	startSpieler.setVisible(true);
    }
    
    public void player1()  {
    	spieler1AufFeld.setBounds(50, 600, 80, 30);
    	fenster.add(spieler1AufFeld);
    }
     public void player2()  {
    	spieler2AufFeld.setBounds(50, 150, 80, 30);
    	fenster.add(spieler2AufFeld);
    }
    public void player3()  {
    	spieler3AufFeld.setBounds(690, 150, 80, 30);
    	fenster.add(spieler3AufFeld);
    }
    public void player4()  {
    	spieler4AufFeld.setBounds(690, 600, 80, 30);
    	fenster.add(spieler4AufFeld);
    }

    
    public void spielerNamenAnzeigen(String name1, String name2, String name3, String name4){
    	
    }
    
    public void resetWuerfelAnzeige()
    {
    	wuerfelnachrichten.setText( " ");
    }
    public void setAktuelleSpielerAmZugGUI(String name, int spielerAmZug) {
    	spielernachrichten.setText( name +" ist am Zug");
    	switch(spielerAmZug) {
    	case 0:
    		spielernachrichten.setForeground(Color.BLUE);
    		break;
    	case 1:
    		spielernachrichten.setForeground(Color.YELLOW);
    		break;
    	case 2:
    		spielernachrichten.setForeground(Color.GREEN);
    		break;
    	case 3:
    		spielernachrichten.setForeground(Color.RED);
    		break;
    	}

    }
    
    public void spielerNamenEins() {
    	spielerNamen.setSize(260,250);
    	spielerNamen.setLayout(null);
    	
    	spieler1.setBounds(10, 10, 60, 30);
    	spieler2.setBounds(10, 50, 60, 30);
    	spieler3.setBounds(10, 90, 60, 30);
    	spieler4.setBounds(10, 130, 60, 30);
    	
    	name1.setBounds(70, 10, 160, 30);
    	bot1.setBounds(70, 50, 200, 30);
    	bot2.setBounds(70, 90, 200, 30);
    	bot3.setBounds(70, 130, 200, 30);
    	
    	okay.setBounds(90, 170, 70, 30);
    	
    	okay.addActionListener(
    			new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==okay) {
							spielerNamenArray[0] = name1.getText();		
							spielerNamenArray[1] = "Bot 1";
							spielerNamenArray[2] = "Bot 2";
							spielerNamenArray[3] = "Bot 3";
							spieler1AufFeld.setText(spielerNamenArray[0]);
							spieler2AufFeld.setText(spielerNamenArray[1]);
							spieler3AufFeld.setText(spielerNamenArray[2]);
							spieler4AufFeld.setText(spielerNamenArray[3]);
							synchronized (spielerNamenButton){
                            	spielerNamenButton.notify();
                            }
							spielerNamen.dispose();
						}
					}
    			}
    	);
    	
    	spielerNamen.add(spieler1);
    	spielerNamen.add(spieler2);
    	spielerNamen.add(spieler3);
    	spielerNamen.add(spieler4);
    	
    	spielerNamen.add(name1);
    	
    	spielerNamen.add(bot1);
    	spielerNamen.add(bot2);
    	spielerNamen.add(bot3);
    	
        spielerNamen.add(okay);
        
    	spielerNamen.setResizable(false);
    	spielerNamen.setLocationRelativeTo(null);
    	spielerNamen.setVisible(true);
    }
    public void spielerNamenZwei() {
    	spielerNamen.setSize(260,250);
    	spielerNamen.setLayout(null);
    	
    	spieler1.setBounds(10, 10, 60, 30);
    	spieler2.setBounds(10, 50, 60, 30);
    	spieler3.setBounds(10, 90, 60, 30);
    	spieler4.setBounds(10, 130, 60, 30);
    	
    	name1.setBounds(70, 10, 160, 30);
    	name2.setBounds(70, 50, 160, 30);
    	bot1.setBounds(70, 90, 200, 30);
    	bot2.setBounds(70, 130, 200, 30);
    	
    	okay.setBounds(90, 170, 70, 30);
    	
    	okay.addActionListener(
    			new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==okay) {
							spielerNamenArray[0] = name1.getText();
							spielerNamenArray[1] = name2.getText();
							spielerNamenArray[2] = "Bot 1";
							spielerNamenArray[3] = "Bot 2";
							spieler1AufFeld.setText(spielerNamenArray[0]);
							spieler2AufFeld.setText(spielerNamenArray[1]);
							spieler3AufFeld.setText(spielerNamenArray[2]);
							spieler4AufFeld.setText(spielerNamenArray[3]);
							synchronized (spielerNamenButton){
                            	spielerNamenButton.notify();
                            }
							spielerNamen.dispose();
						}
					}
    			}
    	);
    	
    	spielerNamen.add(spieler1);
    	spielerNamen.add(spieler2);
    	spielerNamen.add(spieler3);
    	spielerNamen.add(spieler4);
    	
    	spielerNamen.add(name1);
    	spielerNamen.add(name2);
    	
    	spielerNamen.add(bot1);
    	spielerNamen.add(bot2);
    	
        spielerNamen.add(okay);
        
    	spielerNamen.setResizable(false);
    	spielerNamen.setLocationRelativeTo(null);
    	spielerNamen.setVisible(true);
    }
    public void spielerNamenDrei() {
    	spielerNamen.setSize(260,250);
    	spielerNamen.setLayout(null);
    	
    	spieler1.setBounds(10, 10, 60, 30);
    	spieler2.setBounds(10, 50, 60, 30);
    	spieler3.setBounds(10, 90, 60, 30);
    	spieler4.setBounds(10, 130, 60, 30);
    	
    	name1.setBounds(70, 10, 160, 30);
    	name2.setBounds(70, 50, 160, 30);
    	name3.setBounds(70, 90, 160, 30);
    	bot1.setBounds(70, 130, 200, 30);
    	
    	okay.setBounds(90, 170, 70, 30);
    	
    	okay.addActionListener(
    			new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==okay) {
							spielerNamenArray[0] = name1.getText();
							spielerNamenArray[1] = name2.getText();
							spielerNamenArray[2] = name3.getText();
							spielerNamenArray[3] = "Bot 1";
							spieler1AufFeld.setText(spielerNamenArray[0]);
							spieler2AufFeld.setText(spielerNamenArray[1]);
							spieler3AufFeld.setText(spielerNamenArray[2]);
							spieler4AufFeld.setText(spielerNamenArray[3]);
							synchronized (spielerNamenButton){
                            	spielerNamenButton.notify();
                            }
							spielerNamen.dispose();
						}
					}
    			}
    	);
    	
    	spielerNamen.add(spieler1);
    	spielerNamen.add(spieler2);
    	spielerNamen.add(spieler3);
    	spielerNamen.add(spieler4);
    	
    	spielerNamen.add(name1);
    	spielerNamen.add(name2);
    	spielerNamen.add(name3);
    	
    	spielerNamen.add(bot1);
    	
        spielerNamen.add(okay);
        
    	spielerNamen.setResizable(false);
    	spielerNamen.setLocationRelativeTo(null);
    	spielerNamen.setVisible(true);
    }
    public void spielerNamenVier() {
    	spielerNamen.setSize(260,250);
    	spielerNamen.setLayout(null);
    	
    	spieler1.setBounds(10, 10, 60, 30);
    	spieler2.setBounds(10, 50, 60, 30);
    	spieler3.setBounds(10, 90, 60, 30);
    	spieler4.setBounds(10, 130, 60, 30);
    	
    	name1.setBounds(70, 10, 160, 30);
    	name2.setBounds(70, 50, 160, 30);
    	name3.setBounds(70, 90, 160, 30);
    	name4.setBounds(70, 130, 160, 30);
    	
    	okay.setBounds(90, 170, 70, 30);
    	
    	okay.addActionListener(
    			new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==okay) {
							spielerNamenArray[0] = name1.getText();
							spielerNamenArray[1] = name2.getText();
							spielerNamenArray[2] = name3.getText();
							spielerNamenArray[3] = name4.getText();
							spieler1AufFeld.setText(spielerNamenArray[0]);
							spieler2AufFeld.setText(spielerNamenArray[1]);
							spieler3AufFeld.setText(spielerNamenArray[2]);
							spieler4AufFeld.setText(spielerNamenArray[3]);
							synchronized (spielerNamenButton){
                            	spielerNamenButton.notify();
                            }
							spielerNamen.dispose();
						}
					}
    			}
    	);

    	spielerNamen.add(spieler1);
    	spielerNamen.add(spieler2);
    	spielerNamen.add(spieler3);
    	spielerNamen.add(spieler4);

    	spielerNamen.add(name1);
    	spielerNamen.add(name2);
    	spielerNamen.add(name3);
    	spielerNamen.add(name4);


        spielerNamen.add(okay);

    	spielerNamen.setResizable(false);
    	spielerNamen.setLocationRelativeTo(null);
    	spielerNamen.setVisible(true);
    }

    public int getAnzahlSpieler() {
        synchronized(anzahlSpielerButton) {
            try {
                anzahlSpielerButton.wait();
            } catch (InterruptedException e) {
            }
        }
    	return anzahlSpieler; 
    }
    public String[] getSpielerNamen(){
        synchronized(spielerNamenButton) {
            try {
            	spielerNamenButton.wait();
            } catch (InterruptedException e) {
            }
        }
    	return spielerNamenArray;
    }
    public void setWuerfelFeld(int wuerfelZahl) {
    	wuerfelnachrichten.setText("Es wurde eine " + wuerfelZahl + " gewuerfelt");
    }
    
    public void gewinner() {
        
        win.setSize(300,200);
        win.setLayout(null);
        
        hintergrund.setBounds(0, 0, 300, 200);

        JLabel gewonnen = new JLabel("hat gewonnen");
        gewonnen.setBounds(60, 90, 250, 30);
        gewonnen.setFont(new Font("Arial", Font.BOLD, 25));

        gewinner.setBounds(60, 30, 250, 40);
        gewinner.setFont(new Font("Arial", Font.BOLD, 30));

        win.add(gewonnen);
        win.add(gewinner);
        win.add(hintergrund);
        win.setResizable(false);
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }
    
    public void setGewinner(String name, int spielerNummer)
    {
    	switch (spielerNummer){
    		case 0:
    			gewinner.setText(name);
    			hintergrund.setBackground(Color.BLUE);
    			gewinner();
    			break;
    		case 1:
    			gewinner.setText(name);
    			hintergrund.setBackground(Color.YELLOW);
    			gewinner();
    			break;
    		case 2:
    			gewinner.setText(name);
    			hintergrund.setBackground(Color.GREEN);
    			gewinner();
    			break;
    		case 3:
    			gewinner.setText(name);
    			hintergrund.setBackground(Color.RED);
    			gewinner();
    			break;
    	}
    }
}
