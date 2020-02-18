public class Room {

    private String roomTitle;
    public Connections[] connections;
    public Monster monster;
    public Medic medic;
    public boolean hasTreasure = false;
    public String endOfGameText;


    /**
     * Constructor for setting variable
     *
     * @param _roomTitle
     */
    public Room(String _roomTitle) {roomTitle = _roomTitle;}


    /**
     * Getter method to roomTitle
     *
     * @return String roomTitle
     */
    public String getRoomTitle() {
        return roomTitle;
    }

    /**
     *
     * Returns a string: " , " between options.--
     * @return
     */
    public String getConnectionsString(){
        String result = new String();
        for(int i = 0; i < connections.length; i++){
            if(i > 0){
                result += ", ";
            }
          result += connections[i].getNameAndKeyWord();

        }
        return result;
    }

    /**
     * Which room does the player choose?
     * @param playerChoice
     * @return
     */
    public Room selectedPlayerChoice(String playerChoice){
        for(int i = 0; i < connections.length; i++){
            if(playerChoice.equalsIgnoreCase(connections[i].getKeyword())){
                return connections[i].getRoom();
            }
        }
        return null;
    }

    /**
     * Return true if room has a monster, or false if not.
     * @return
     */
    public boolean hasMonster(){
        if (monster == null){
            return false;
        } else{
            return true;
        }
    }

    /**
     * Return true if room has medic, or false if not.
     * @return
     */
    public boolean hasMedic(){
        if (medic == null){
            return false;
        } else{
            return true;
        }
    }

    /**
     * Prepare string to print out variables (roomTitle and options)
     *
     * @return
     */
    public String toString() {
        String result = roomTitle + " options: (";
        if (connections != null) {
            for (int i = 0; i < connections.length; i++) {
                if (i > 0) {
                    result += ", ";
                }
                result += connections[i].toString();
            }
        }
        result += ") ";
        return result;
    }
}
