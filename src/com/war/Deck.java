package com.war;

import java.util.*;

public class Deck {
    private int size = 52;
    //array list
    ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
    //PlayingCard deck1[] =  new PlayingCard[(size*2)+1];

    private int numberOfHearts = size/4;
    private int numberOfSpades = size/4;
    private int numberOfDiamonds = size/4;
    private int numberOfClubs = size/4;

    public int getSize(){
        return  size;
    }

    public void setSize(int newSize){
       this.size = newSize;
    }

    public void createDeck(){
        try{
            //DEBUG IncorrectValuePassedException
//            deck[0] = new PlayingCard();
//            deck[0].setValue(15);
            for(int i = 0; i < this.size; i++){
                deck.add(new PlayingCard());
                deck.get(i).setFace((i/13)+1);
                deck.get(i).setValue((i%13)+1);
            }
        } catch (IncorrectValuePassedException ivpe){
            System.out.println("Unable to create Deck. Adjust division");
            System.exit(-1);
        }
    }

    //DEBUG
    public void setCard(int position, PlayingCard card){
        this.deck.set(position, card);
    }

    public PlayingCard getCard(int position){
        return this.deck.get(position);
    }


    public void shuffleDeck(){
        int random = (int) (Math.random( )*60);
        for(int i = 0; i < random; i++){
            int cardOne = (int) (Math.random( )*this.size);
            int cardTwo = (int) (Math.random( )*this.size);

            PlayingCard temp = this.deck.get(cardOne);

            this.deck.set(cardOne, this.deck.get(cardTwo));
            this.deck.set(cardTwo, temp);

        }
    }

    public void printDeck(){
        //for each
        for(PlayingCard card: this.deck){
            card.printCard();
            System.out.println();
        }
    }

    public static boolean compareCard(PlayingCard a, PlayingCard b) throws SameCardException{
        if(a.getValue() > b.getValue()){
            return true;
        } else  if (a.getValue() == b.getValue()){
            throw  new SameCardException("Two equal values");
        } else {
            return false;
        }
    }

    //TODO error when only two cards in deck
    public void insertCard(PlayingCard newCard, int insertPosition){
        this.size += 1;
        this.deck.add(insertPosition, newCard);
    }

    public void removeCard(int removePosition){
        this.size -= 1;
        this.deck.remove(removePosition);
    }

    public void removeCardsLowerThan(int upperBound){
        Iterator<PlayingCard> it = deck.iterator();
        while (it.hasNext()){
            PlayingCard temp = it.next();
            if(temp.getValue() < upperBound){
                it.remove();
            }
        }
    }
}
