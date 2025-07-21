package code;

public class Figur {

    private
    int position;
    int besitzer;
    int figurNummer;

    Figur(int position, int besitzer, int figurNummer)
    {
        this.besitzer=besitzer;
        this.position=position;
        this.figurNummer=figurNummer;
    }
    public void setPostition(int neuePosition)
    {
        position=neuePosition;
    }

    public int getPosition()
    {
        return position;
    }
    public int getBesitzer()
    {
        return besitzer;
    }

    public int getFigurNummer()
    {
        return figurNummer;
    }

}