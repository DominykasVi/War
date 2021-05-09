package com.war;

public class PlayingCard extends Card{

    int numberLimit = 15;
    int faceLimit = 5;

    //11 - jack
    //12 - queen
    //13 - king
    //14 - ace
    public PlayingCard() {
    int a;
    }

    public PlayingCard(int value, int face) throws IncorrectValuePassedException {
        setValue(value);
        setFace(face);
    }

    public void setValue(int number) throws  IncorrectValuePassedException{
        try {
            if(number == 1){
                number = 14;
            }
            setValue(number, numberLimit);
        } catch (IncorrectValuePassedException ivpe){
            System.out.println(ivpe.getMessage());
            throw  ivpe;
        }

    }

    public void setFace(int number) throws  IncorrectValuePassedException{
        try {
            setFace(number, faceLimit);
        } catch (IncorrectValuePassedException ivpe){
            System.out.println(ivpe.getMessage());
            throw  ivpe;
        }

    }

    public String getGUIValue(){
        switch (value){
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            case 14:
                return "Ace";
            default:
                return "" + value;
        }
    }


    public void printCard(){
        switch (value){
            case 11:
                System.out.print("Jack of ");
                printFace();
                break;
            case 12:
                System.out.print("Queen of ");
                printFace();
                break;
            case 13:
                System.out.print("King of ");
                printFace();
                break;
            case 14:
                System.out.print("Ace of ");
                printFace();
                break;
            default:
                System.out.print(this.value + " of ");
                printFace();
                break;
        }
    }

    public String getFace(){
        switch (this.face){
            case 1:
                return "Hearts";
            case 2:
                return "Spades";
            case 3:
                return "Diamonds";
            case 4:
                return "Clubs";
        }
        return "No face found";
    }


    public void printFace(){
        switch (this.face){
            case 1:
                System.out.print("Hearts");
                break;
            case 2:
                System.out.print("Spades");
                break;
            case 3:
                System.out.print("Diamonds");
                break;
            case 4:
                System.out.print("Clubs");
                break;
        }

    }

}
