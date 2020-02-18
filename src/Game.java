import java.util.Scanner;

public class Game {

    boolean playingGame = true;
    Room firstRoom;
    Room currentRoom;
    Room lastRoom;
    Scanner keyboard = new Scanner(System.in);

    public void startGame(){

        firstRoom = createCastle();
        lastRoom = firstRoom;
        currentRoom = firstRoom;
        Player player = new Player();
        System.out.println("Welcome to THE ABANDONED CASTLE! In this game, you will walk around in the castle, and try to find the HIDDEN TREASURE! But watch out! There will be dangerous challenges, along the way\n");

        while (playingGame) {

            boolean goToNewRoom = true;
            System.out.println("You are now standing in " + currentRoom.getRoomTitle());

            /**
             * Treasure handling.
             */
            if (currentRoom.hasTreasure){
                System.out.println("The treasure is in this room! if you get to it, you will win the game!");
            }

            /**
             * Medic handling.
             */
            if (currentRoom.hasMedic()){
                System.out.println("There is a first aid kid in this room! You will carrie it with you for the rest of the game. You can heal yourself "+ currentRoom.medic.getMaxUses() + " times, and it will give you " + currentRoom.medic.healing + " healing every time. Use it carefully!");
                player.takeFirstAidKidFromRoom(currentRoom);
            }

            /**
             * Monster handling.
             */
            if (currentRoom.hasMonster()) {
                Monster monster = currentRoom.monster;
                if (monster.isAlive()){
                    System.out.println("There is a monster in this room! You have to kill it to continue");
                    System.out.println(monster.toString());
                    System.out.println("\tYour health is: " +player.health);
                    System.out.println("Do you wanna fight the monster: yes or no? ");
                    String fightOrNot;
                    /**
                     * The fight is on.
                     */
                    boolean fighting = false;
                    boolean doAsk = true;
                    while(doAsk) {
                        fightOrNot = keyboard.nextLine();
                        if (fightOrNot.equalsIgnoreCase("yes")) {
                            fighting = true;
                            doAsk = false;
                        } else if (fightOrNot.equalsIgnoreCase("no")) {
                            /**
                             * PLayer dont want to fight anymore. Send him back to the room he came from.
                             */
                            fighting = false;
                            currentRoom = lastRoom;
                            goToNewRoom = false;
                            doAsk = false;
                        } else {
                            System.out.println(fightOrNot + " is not an option. Do you wanna fight the monster: yes or no? ");
                        }
                    }

                    while (fighting){
                        monster.strike(player);
                        if (!monster.isAlive()){
                            /**
                             * The monster is dead. Continue to the next room.
                             */
                            fighting = false;
                            System.out.println("YES! you have killed " + monster.name + " the monster!");
                        } else {
                            /**
                             * The monster is still alive
                             */
                            if (player.isAlive()){
                                /**
                                 * Both the monster and player is still alive
                                 */
                                if(player.hasMedic()){
                                    System.out.println("The monsters health is now "+ monster.health + " and your health is now " + player.health + ". Remember you have a first aid kid, do you want to use it now? Yes or no: ");
                                    String medicOrNot = keyboard.nextLine();
                                    /**
                                     * Use medic.
                                     */
                                    if (medicOrNot.equalsIgnoreCase("yes")){
                                        boolean takeMedic = true;
                                        while (takeMedic) {
                                            player.useMedic();
                                            if (player.medic.hasHealing()) {
                                                System.out.println("Your health is now " + player.health + " do you want to heal more? yes or no");
                                                medicOrNot = keyboard.nextLine();
                                                /**
                                                 * Dont use medic.
                                                 */
                                                if (medicOrNot.equalsIgnoreCase("no")){
                                                    takeMedic = false;
                                                }
                                            }else {
                                                takeMedic = false;
                                            }
                                        }
                                    }
                                }
                                System.out.println("The monsters health is now: " + monster.health + " And your health is now: " + player.health);
                                System.out.println("Do you still want to fight the monster? Yes or no");
                                fightOrNot = keyboard.nextLine();
                                if (fightOrNot.equalsIgnoreCase("yes")){
                                    fighting = true;
                                }else {
                                    /**
                                     * PLayer dont want to fight anymore. Send him back to the room he came from
                                     */
                                    fighting = false;
                                    currentRoom = lastRoom;
                                    goToNewRoom = false;
                                }
                            } else{
                                /**
                                 * Player was killed by monster. End of game
                                 */
                                fighting = false;
                                System.out.println(monster.name + " killed you!");
                                endOfGame(false);
                            }
                        }
                    } // while fighting.
                }else{
                    System.out.println("There is a dead monster on the floor!");
                }
            }
            if (goToNewRoom) {

                /**
                 * Monster handling over.
                 * If player is dead, end game.
                 * If not, select a new connection, and enter a new room.
                 */
                if(!player.isAlive()) {
                    endOfGame(false);
                } else if (currentRoom.connections == null) {
                    endOfGame(true);
                } else {
                    System.out.println("You now have the following options: " + currentRoom.getConnectionsString());

                    String playerChoice = keyboard.nextLine();
                    Room nextRoom = currentRoom.selectedPlayerChoice(playerChoice);

                    if (nextRoom == null) {
                        System.out.println(playerChoice + " is not an option.");
                    } else {
                        lastRoom = currentRoom;
                        currentRoom = nextRoom;
                    }
                }
            }
        } // While playing game.
        System.out.println("Thank you for visiting THE ABANDONED CASTLE!");
    }

    private Room createCastle2(){
        Room kitchen = new Room("Kitchen");

        Connections[] connections = new Connections[1];
        //connections[0] = new Connections("1","first floor",firstFloor);
        return kitchen;
    }

    private Room createCastle(){
        /**
         * Room and connected rooms
         */
        Room hallway = new Room("the hallway");

        Room theStairs = new Room("the staircase");
        Medic medic = new Medic(20, 3);
        theStairs.medic = medic;

        Room livingRoom = new Room("the living room");
        Monster monster = new Monster("Grimeteeth", 10, 100);
        livingRoom.monster = monster;

        Room firstFloor = new Room("first floor");
        Room secondFloor = new Room("second floor");
        Room thirdFloor = new Room("third floor");
        monster = new Monster("Voodoobug", 30, 100);
        thirdFloor.monster = monster;

        Room balcony = new Room("balcony");
        balcony.endOfGameText = "You fall out of the balcony, and die";
        Room chamber = new Room("Chamber");
        monster = new Monster ("Smogstrike", 50, 100);
        chamber.monster = monster;
        chamber.hasTreasure = true;
        chamber.endOfGameText = "YEEES! You won the game, and found the treasure!";

        Room bedchamber = new Room("bedchamber");
        bedchamber.endOfGameText = "You fall in a deep sleep, and will never wake up!";


        /**
         * Options connecting to the rooms hallway,theStairs,sale,livingRoom variable
         */
        Connections[] hallwayConnections = new Connections[2];
        hallwayConnections[0] = new Connections("go left","l", theStairs);
        hallwayConnections[1] = new Connections("go right","r", livingRoom);
        hallway.connections = hallwayConnections;

        Connections[] stairsConnections = new Connections[3];
        stairsConnections[0] = new Connections("first floor","1", firstFloor);
        stairsConnections[1] = new Connections("second floor","2", secondFloor);
        stairsConnections[2] = new Connections("third floor","3", thirdFloor);
        theStairs.connections = stairsConnections;

        Connections[] firstFloorConnections = new Connections[3];
        firstFloorConnections[0] = new Connections("blue door","b",hallway);
        firstFloorConnections[1] = new Connections("red door","r",balcony);
        firstFloorConnections[2] = new Connections("yellow door","y",chamber);
        firstFloor.connections = firstFloorConnections;

        Connections[] secondFloorConnections = new Connections[1];
        secondFloorConnections[0] = new Connections("small door","s",chamber);
        secondFloor.connections = secondFloorConnections;

        Connections[] thridConnections = new Connections[2];
        thridConnections[0] = new Connections("big door","bd",balcony);
        thridConnections[1] = new Connections("elevator","e",firstFloor);
        thirdFloor.connections = thridConnections;

        Connections[] livingRoomConnections = new Connections[2];
        livingRoomConnections[0] = new Connections("iron door","i",hallway);
        livingRoomConnections[1] = new Connections("tree door","t",bedchamber);
        livingRoom.connections = livingRoomConnections;

        return hallway;
    }
    private void endOfGame(boolean win){
        System.out.println("GAME OVER!");
        if(win) {
            System.out.println(currentRoom.endOfGameText);
        }else {
            System.out.println("You lost the game!");
        }
        /**
         * Start over, yes or no.
         */
        System.out.println("Do you wanna visit the THE ABANDONED CASTLE again?: Yes or no?");
        String playAgain = keyboard.nextLine();
        if (playAgain.equalsIgnoreCase("yes")){
            firstRoom = createCastle();
            lastRoom = firstRoom;
            currentRoom = firstRoom;
        } else{
            playingGame = false;
        }
    }
}
