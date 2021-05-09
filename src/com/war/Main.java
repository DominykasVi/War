package com.war;

public class Main {

    public static void main(String[] args) {
        Deck a = new Deck();
        a.createDeck();
        Deck b = new Deck();
        b.createDeck();
        for(int i =0; i < 5 ;i++){
            a.shuffleDeck();
            b.shuffleDeck();
        }


        //DEBUG, kad ismestu exceptions
//        a.setCard(0, b.getCard(0)); // SameCardException
       //Incorrectvalue exception in deck class, line 22


        //5uzd
        //Deck class saves playingCards objects into array list, so that it is easier to insert and remove cards
        //printDeck uses enhanced for cycle
        //removeCardsLowerThan uses iterator to remove cards lower than upper bound,
        //helpful if players want to play a shorter game with higher cards

        a.printDeck();
        System.out.println();
        b.printDeck();
        System.out.println();

        UserInterface gui = new UserInterface(a, b);
    }
}
