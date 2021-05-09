package com.war;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class UserInterface extends JFrame implements ActionListener, CardFace {
    JLabel card1, card2;
    JLabel card1Face, card2Face;
    GridBagLayout gridbag = new GridBagLayout();
    JButton button;
    Deck a;
    Deck b;
    CardLayout cards = new CardLayout();

    JPanel mainPanel = new JPanel();
    JPanel firstPanel = new JPanel();
    JPanel secondPanel = new JPanel();

    int aCounter = 0, bCounter = 0;
    int inputCheck = 0;
    //TODO: add deck sizes at top, show who won, stop button from changing position, maybe use grid layout,
    // add images of jack/queen etc, add messages to show when cards were equal, button push to redraw


    public UserInterface(Deck firstDeck, Deck secondDeck) {
        super("War");
        setSize(400, 400);
        setLookAndFeel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.a = firstDeck;
        this.b = secondDeck;

        GridLayout layout = new GridLayout(5, 1);
        firstPanel.setLayout(layout);

        JLabel setup = new JLabel("Welcome to setup", SwingConstants.CENTER);
        JLabel entry = new JLabel("Please enter what is the lowest card you wish to play with", SwingConstants.CENTER);
        JTextField input = new JTextField(SwingConstants.CENTER);
        input.setBounds(200, 200, 30, 30);
        JButton inputButton = new JButton("Continue");
        inputButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int bound;
                try {
                    bound = Integer.parseInt(input.getText());
                    if(bound < 14 && bound > 0){
                        a.removeCardsLowerThan(bound);
                        b.removeCardsLowerThan(bound);
                        setSize(600, 400);
                        revalidate();
                        repaint();
                        cards.show(mainPanel,"2");
                    } else  if(inputCheck == 0){
                        JLabel incorrectInput = new JLabel("Incorrect Input", SwingConstants.CENTER);
                        firstPanel.add(incorrectInput);
                        firstPanel.revalidate();
                        firstPanel.repaint();
                        inputCheck++;
                    }
                } catch (NumberFormatException nfe) {
                    if (inputCheck == 0){
                        JLabel incorrectInput = new JLabel("Incorrect Input", SwingConstants.CENTER);
                        firstPanel.add(incorrectInput);
                        firstPanel.revalidate();
                        firstPanel.repaint();
                        inputCheck++;
                    }
                }

            }
        });

        firstPanel.add(setup);
        firstPanel.add(entry);
        firstPanel.add(input);
        firstPanel.add(inputButton);

        GridBagConstraints constraints;
        secondPanel.setLayout(gridbag);



        // create components
        card1 = new JLabel("" + a.getCard(aCounter).getGUIValue());
        card1Face = new JLabel("" + a.getCard(0).getFace());
        card2 = new JLabel("" + b.getCard(bCounter).getGUIValue());
        card2Face = new JLabel("" + b.getCard(0).getFace());

        card1.setFont(new Font("Times New Roman", Font.PLAIN, 64));
        card1.setPreferredSize(new Dimension(300, 100));
        card1Face.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        card1Face.setPreferredSize(new Dimension(300, 100));

        card2.setFont(new Font("Times New Roman", Font.PLAIN, 64));
        card2.setPreferredSize(new Dimension(300, 100));
        card2Face.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        card2Face.setPreferredSize(new Dimension(300, 100));

        //add components
        addComponent(card1, 0, 0, 1, 1, 50, 100,
                GridBagConstraints.NONE, GridBagConstraints.CENTER);
        addComponent(card1Face, 0, 3, 1, 1, 10, 100,
                GridBagConstraints.NONE, GridBagConstraints.CENTER);

        button = new JButton("Draw cards");
        button.addActionListener(this);
        addComponent(button, 1, 0, 3, 3, 50, 100,
                GridBagConstraints.NONE, GridBagConstraints.CENTER);

        addComponent(card2, 4, 0, 2, 2, 50, 100,
                GridBagConstraints.NONE, GridBagConstraints.CENTER);
        addComponent(card2Face, 5, 3, 1, 1, 10, 100,
                GridBagConstraints.NONE, GridBagConstraints.CENTER);


        mainPanel.setLayout(cards);
        mainPanel.add(firstPanel, "1");
        mainPanel.add(secondPanel, "2");
        cards.show(mainPanel,"1");

        add(mainPanel);


        setVisible(true);
    }

    //make gui a little bit nicer
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        } catch (Exception exc) {
            System.err.println(exc);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //on button push check which bigger and add to another deck
        try{
            cardCheck();
        }
        catch (NullPointerException nfe){
            System.out.println("Error on button push");
        }
    }

    private int cardCheck(){
        int returnValue = 0;
        if (a.getSize() == 0 || b.getSize() == 0){
            JLabel won = new JLabel("You won");
            addComponent(won, 1, 0, 3, 3, 100, 100,
                    GridBagConstraints.NONE, GridBagConstraints.CENTER);
            repaint();
            return returnValue;
        }
        try{
            System.out.println("a:" + aCounter + "B:" + bCounter);
            if(Deck.compareCard(a.getCard(aCounter), b.getCard(bCounter))){//if true a card bigger than b card
                a.insertCard(b.getCard(bCounter), aCounter);
                increaseACounter(2);
                b.removeCard(bCounter);
                returnValue = 1;
                //when bCounter is 4 and size is 4 after removal
                if(bCounter == b.getSize()){
                    bCounter = 0;
                }
            } else {
                b.insertCard(a.getCard(aCounter), bCounter);
                increaseBCounter(2);
                a.removeCard(aCounter);
                returnValue = -1;

                if(aCounter == a.getSize()){
                    aCounter = 0;
                }
            }
            a.printDeck();
            System.out.println();
            b.printDeck();
            System.out.println();
        }catch (SameCardException sce){
            System.out.println("Same cards");
            //DEBUG
//            a.printDeck();
//            System.out.println();
//            b.printDeck();
//            System.out.println();
            //by the original rules, check which card after two is greater and then add it to the deck
            increaseACounter(2);
            increaseBCounter(2);
            //TODO: add button listener here, so after push code executes
            int check = cardCheck();
            if (check == 1){
                decreaseACounter(1);
                decreaseBCounter(2);
                a.insertCard(b.getCard(bCounter), aCounter);
                b.removeCard(bCounter);

                if(bCounter == b.getSize()){
                    bCounter = 0;
                }


                increaseACounter(1);
                a.insertCard(b.getCard(bCounter), aCounter);
                b.removeCard(bCounter);

                if(bCounter == b.getSize()){
                    bCounter = 0;
                }

                increaseACounter(3);
            } else if (check == -1){
                decreaseBCounter(1);
                decreaseACounter(2);
                b.insertCard(a.getCard(aCounter), bCounter);
                a.removeCard(aCounter);

                if(aCounter == a.getSize()){
                    aCounter = 0;
                }

                increaseBCounter(1);
                b.insertCard(a.getCard(aCounter), bCounter);
                a.removeCard(aCounter);

                if(aCounter == a.getSize()){
                    aCounter = 0;
                }

                increaseBCounter(3);
            }
            //DEBUG
//            a.printDeck();
//            System.out.println();
//            b.printDeck();
//            System.out.println();
        }


        card1.setText("" + a.getCard(aCounter).getGUIValue());
        card1Face.setText("" + a.getCard(0).getFace());
        card2.setText("" + b.getCard(bCounter).getGUIValue());
        card2Face.setText("" + b.getCard(0).getFace());

        //DEBUG
        System.out.println("A deck size:" + a.getSize() + " | B deck size:" + b.getSize());
        return returnValue;
    }

    private void increaseACounter(int number){
        for(int i = 0; i < number; i++){
            aCounter++;
            if(aCounter+1 > a.getSize()){
                aCounter = 0;
            }
        }
    }

    private void decreaseACounter(int number){
        for(int i = 0; i < number; i++){
            aCounter--;
            if(aCounter < 0){
                aCounter = a.getSize()-1;
            }
        }
    }

    private void increaseBCounter(int number){
        for(int i = 0; i < number; i++){
            bCounter++;
            if(bCounter+1 > b.getSize()){
                bCounter = 0;
            }
        }
    }

    private void decreaseBCounter(int number){
        for(int i = 0; i < number; i++){
            bCounter--;
            if(bCounter < 0){
                bCounter = b.getSize()-1;
            }
        }
    }

    //adds component in GridBagLayout
    private void addComponent(Component component, int gridx, int gridy,
                              int gridwidth, int gridheight, int weightx, int weighty, int fill,
                              int anchor) {

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.fill = fill;
        constraints.anchor = anchor;
        gridbag.setConstraints(component, constraints);
        secondPanel.add(component);
    }

    @Override
    public void GetFaceObject(String face) {
        //current output method will be replaced, by putting images where JLabels are
        //these functions will be necessary to ensure, that the pictures are taken,
        //and can be displayed, returned (will work out if the return object, or set it later on)
        ImageIcon faceIcon = new ImageIcon(face + ".png");
    }

    @Override
    public void GetValueObject(int value) {
        ImageIcon faceIcon = new ImageIcon(value + ".png");
    }
}
