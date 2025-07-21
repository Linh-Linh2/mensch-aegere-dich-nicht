package graphicalUserInterface;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;


public class GuiSpielfeld extends JPanel  {
    private byte[][] feld = null;
    private double[][] koordinatenVomFeld = new double[90][2];

    public GuiSpielfeld() {
        this.setBackground(new Color(255, 255, 158));

        this.feld = new byte[][]{
                {2, 2, 0, 0, 5, 5, 3, 0, 0, 3, 3},
                {2, 2, 0, 0, 5, 3, 5, 0, 0, 3, 3},
                {0, 0, 0, 0, 5, 3, 5, 0, 0, 0, 0},
                {0, 0, 0, 0, 5, 3, 5, 0, 0, 0, 0},
                {2, 5, 5, 5, 5, 3, 5, 5, 5, 5, 5},
                {5, 2, 2, 2, 2, 0, 4, 4, 4, 4, 5},
                {5, 5, 5, 5, 5, 1, 5, 5, 5, 5, 4},
                {0, 0, 0, 0, 5, 1, 5, 0, 0, 0, 0},
                {0, 0, 0, 0, 5, 1, 5, 0, 0, 0, 0},
                {1, 1, 0, 0, 5, 1, 5, 0, 0, 4, 4},
                {1, 1, 0, 0, 1, 5, 5, 0, 0, 4, 4}};

        koordinatenVomFeld[0][0]=((800/11)*9); koordinatenVomFeld[0][1]=((800/11)*6);
        koordinatenVomFeld[1][0]=((800/11)*8);  koordinatenVomFeld[1][1]=((800/11)*6);
        koordinatenVomFeld[2][0]=((800/11)*7);  koordinatenVomFeld[2][1]=((800/11)*6);

        koordinatenVomFeld[3][0]=((800/11)*6);  koordinatenVomFeld[3][1]=((800/11)*6);

        koordinatenVomFeld[4][0]=((800/11)*6);  koordinatenVomFeld[4][1]=((800/11)*7);
        koordinatenVomFeld[5][0]=((800/11)*6);  koordinatenVomFeld[5][1]=((800/11)*8);
        koordinatenVomFeld[6][0]=((800/11)*6);  koordinatenVomFeld[6][1]=((800/11)*9);

        koordinatenVomFeld[7][0]=((800/11)*6);  koordinatenVomFeld[7][1]=((800/11)*10);
        koordinatenVomFeld[8][0]=((800/11)*5);  koordinatenVomFeld[8][1]=((800/11)*10);
        koordinatenVomFeld[9][0]=((800/11)*4);  koordinatenVomFeld[9][1]=((800/11)*10);

        koordinatenVomFeld[10][0]=((800/11)*4);  koordinatenVomFeld[10][1]=((800/11)*9);
        koordinatenVomFeld[11][0]=((800/11)*4);  koordinatenVomFeld[11][1]=((800/11)*8);
        koordinatenVomFeld[12][0]=((800/11)*4);  koordinatenVomFeld[12][1]=((800/11)*7);

        koordinatenVomFeld[13][0]=((800/11)*4);  koordinatenVomFeld[13][1]=((800/11)*6);

        koordinatenVomFeld[14][0]=((800/11)*3);  koordinatenVomFeld[14][1]=((800/11)*6);
        koordinatenVomFeld[15][0]=((800/11)*2);  koordinatenVomFeld[15][1]=((800/11)*6);
        koordinatenVomFeld[16][0]=((800/11)*1);  koordinatenVomFeld[16][1]=((800/11)*6);

        koordinatenVomFeld[17][0]=((800/11)*0);  koordinatenVomFeld[17][1]=((800/11)*6);
        koordinatenVomFeld[18][0]=((800/11)*0);  koordinatenVomFeld[18][1]=((800/11)*5);
        koordinatenVomFeld[19][0]=((800/11)*0);  koordinatenVomFeld[19][1]=((800/11)*4);

        koordinatenVomFeld[20][0]=((800/11)*1);  koordinatenVomFeld[20][1]=((800/11)*4);
        koordinatenVomFeld[21][0]=((800/11)*2);  koordinatenVomFeld[21][1]=((800/11)*4);
        koordinatenVomFeld[22][0]=((800/11)*3);  koordinatenVomFeld[22][1]=((800/11)*4);

        koordinatenVomFeld[23][0]=((800/11)*4);  koordinatenVomFeld[23][1]=((800/11)*4);

        koordinatenVomFeld[24][0]=((800/11)*4);  koordinatenVomFeld[24][1]=((800/11)*3);
        koordinatenVomFeld[25][0]=((800/11)*4);  koordinatenVomFeld[25][1]=((800/11)*2);
        koordinatenVomFeld[26][0]=((800/11)*4);  koordinatenVomFeld[26][1]=((800/11)*1);

        koordinatenVomFeld[27][0]=((800/11)*4);  koordinatenVomFeld[27][1]=((800/11)*0);
        koordinatenVomFeld[28][0]=((800/11)*5);  koordinatenVomFeld[28][1]=((800/11)*0);
        koordinatenVomFeld[29][0]=((800/11)*6);  koordinatenVomFeld[29][1]=((800/11)*0);

        koordinatenVomFeld[30][0]=((800/11)*6);  koordinatenVomFeld[30][1]=((800/11)*1);
        koordinatenVomFeld[31][0]=((800/11)*6);  koordinatenVomFeld[31][1]=((800/11)*2);
        koordinatenVomFeld[32][0]=((800/11)*6);  koordinatenVomFeld[32][1]=((800/11)*3);

        koordinatenVomFeld[33][0]=((800/11)*6);  koordinatenVomFeld[33][1]=((800/11)*4);

        koordinatenVomFeld[34][0]=((800/11)*7);  koordinatenVomFeld[34][1]=((800/11)*4);
        koordinatenVomFeld[35][0]=((800/11)*8);  koordinatenVomFeld[35][1]=((800/11)*4);
        koordinatenVomFeld[36][0]=((800/11)*9);  koordinatenVomFeld[36][1]=((800/11)*4);

        koordinatenVomFeld[37][0]=((800/11)*10);  koordinatenVomFeld[37][1]=((800/11)*4);
        koordinatenVomFeld[38][0]=((800/11)*10);  koordinatenVomFeld[38][1]=((800/11)*5);
        koordinatenVomFeld[39][0]=((800/11)*10);  koordinatenVomFeld[39][1]=((800/11)*6);

        koordinatenVomFeld[40][0]=((800/11)*5);  koordinatenVomFeld[40][1]=((800/11)*9);
        koordinatenVomFeld[41][0]=((800/11)*5);  koordinatenVomFeld[41][1]=((800/11)*8);
        koordinatenVomFeld[42][0]=((800/11)*5);  koordinatenVomFeld[42][1]=((800/11)*7);
        koordinatenVomFeld[43][0]=((800/11)*5);  koordinatenVomFeld[43][1]=((800/11)*6);

        koordinatenVomFeld[50][0]=((800/11)*1);  koordinatenVomFeld[50][1]=((800/11)*5);
        koordinatenVomFeld[51][0]=((800/11)*2);  koordinatenVomFeld[51][1]=((800/11)*5);
        koordinatenVomFeld[52][0]=((800/11)*3);  koordinatenVomFeld[52][1]=((800/11)*5);
        koordinatenVomFeld[53][0]=((800/11)*4);  koordinatenVomFeld[53][1]=((800/11)*5);

        koordinatenVomFeld[60][0]=((800/11)*5);  koordinatenVomFeld[60][1]=((800/11)*1);
        koordinatenVomFeld[61][0]=((800/11)*5);  koordinatenVomFeld[61][1]=((800/11)*2);
        koordinatenVomFeld[62][0]=((800/11)*5);  koordinatenVomFeld[62][1]=((800/11)*3);
        koordinatenVomFeld[63][0]=((800/11)*5);  koordinatenVomFeld[63][1]=((800/11)*4);

        koordinatenVomFeld[70][0]=((800/11)*9); koordinatenVomFeld[70][1]=((800/11)*5);
        koordinatenVomFeld[71][0]=((800/11)*8);  koordinatenVomFeld[71][1]=((800/11)*5);
        koordinatenVomFeld[72][0]=((800/11)*7);  koordinatenVomFeld[72][1]=((800/11)*5);
        koordinatenVomFeld[73][0]=((800/11)*6);  koordinatenVomFeld[73][1]=((800/11)*5);

        koordinatenVomFeld[74][0]=((800/11)*0);  koordinatenVomFeld[74][1]=((800/11)*9);
        koordinatenVomFeld[75][0]=((800/11)*1);  koordinatenVomFeld[75][1]=((800/11)*9);
        koordinatenVomFeld[76][0]=((800/11)*1);  koordinatenVomFeld[76][1]=((800/11)*10);
        koordinatenVomFeld[77][0]=((800/11)*0);  koordinatenVomFeld[77][1]=((800/11)*10);

        koordinatenVomFeld[78][0]=((800/11)*0);   koordinatenVomFeld[78][1]=((800/11)*0);
        koordinatenVomFeld[79][0]=((800/11)*1);   koordinatenVomFeld[79][1]=((800/11)*0);
        koordinatenVomFeld[80][0]=((800/11)*1);   koordinatenVomFeld[80][1]=((800/11)*1);
        koordinatenVomFeld[81][0]=((800/11)*0);   koordinatenVomFeld[81][1]=((800/11)*1);

        koordinatenVomFeld[82][0]=((800/11)*9);  koordinatenVomFeld[82][1]=((800/11)*0);
        koordinatenVomFeld[83][0]=((800/11)*10);  koordinatenVomFeld[83][1]=((800/11)*0);
        koordinatenVomFeld[84][0]=((800/11)*10);  koordinatenVomFeld[84][1]=((800/11)*1);
        koordinatenVomFeld[85][0]=((800/11)*9);  koordinatenVomFeld[85][1]=((800/11)*1);

        koordinatenVomFeld[86][0]=((800/11)*9);   koordinatenVomFeld[86][1]=((800/11)*9);
        koordinatenVomFeld[87][0]=((800/11)*10);   koordinatenVomFeld[87][1]=((800/11)*9);
        koordinatenVomFeld[88][0]=((800/11)*10);   koordinatenVomFeld[88][1]=((800/11)*10);
        koordinatenVomFeld[89][0]=((800/11)*9);   koordinatenVomFeld[89][1]=((800/11)*10);
    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        int seite = this.getWidth()/11;
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < this.feld.length; i++) {
            for (int j = 0; j < feld[i].length; j++) {
                Color farbe = felderFarbe(feld[i][j]);
                if (farbe == null) continue;
                g2D.setColor(farbe);
                g2D.fillOval(j*seite+seite/10,i*seite+seite/10,seite-seite/5,seite-seite/5);
                g2D.setColor(Color.BLACK);
                g2D.drawOval(j*seite+seite/10,i*seite+seite/10,seite-seite/5,seite-seite/5);
            }
        }
    }

    private Color felderFarbe(int i) {
        Color farbe = null;
        switch(i){
            case 0:
                break;
            case 1:
                farbe = Color.BLUE;
                break;    
            case 2:
                farbe = Color.YELLOW;
                break;
            case 3:
                farbe = Color.GREEN;
                break;
            case 4:
                farbe = Color.RED;
                break;
            case 5:
            default:
                farbe = Color.WHITE;
                break;
        }
        return farbe;
    }
    
    public int buttonIndexFinden(double[][] gedrueckterButtonKoordinaten){
        for(int i=0;i<90;i++){
            if( (i<44 || i >49) && (i<54 || i >59) && (i<64 || i >69)
                 && gedrueckterButtonKoordinaten[0][0]==koordinatenVomFeld[i][0] && gedrueckterButtonKoordinaten[0][1]==koordinatenVomFeld[i][1] ){
                return i;
            }
        }
        return 1000;
    }

    public double getXKoord(int index)
    {
        return koordinatenVomFeld[index][0];
    }
    public double getYKoord(int index)
    {
        return koordinatenVomFeld[index][1];
    }
}
