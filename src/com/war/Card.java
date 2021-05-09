package com.war;

public abstract class Card {

    //Faces:
    //1 - Hearts
    //2 - Spades
    //3 - Diamonds
    //4 - Clubs

    //TODO: face as an object to show symbol
    protected int value = 0;
    protected int face = 0;


    public void setValue(int number, int limit) throws IncorrectValuePassedException{
        if(number > 0 && number < limit){
            this.value = number;
        } else {
            throw new IncorrectValuePassedException("Value of " + number + " is not acceptable");
        }
    }

    public int getValue(){
        return value;
    }

    public void setFace(int number, int limit) throws IncorrectValuePassedException{
        if(number > 0 && number < limit){
            this.face = number;
        } else {
            throw new IncorrectValuePassedException("Face of " + number + " is not acceptable");
        }
    }

    public void printCard(){
        System.out.print(this.value + " of ");
    }

}
