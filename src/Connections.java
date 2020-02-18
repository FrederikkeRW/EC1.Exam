public class Connections {

    /**
     * the string that should be typed on the keyboard to acccess the next room
     */
    private String keyword;
    private String name;
    private Room room;
    public boolean locked = false;

    /**
     * Constructor setting variables
     * @param _keyword
     * @param _room
     */
    public Connections(String _keyword, Room _room){
        keyword = _keyword;
        name = _keyword;
        room = _room;
    }

    public Connections(String _name, String _keyword, Room _room){
        keyword = _keyword;
        name = _name;
        room = _room;
    }

    /**
     * Getter method to keyword
     * @return String keyword
     */
    public String getKeyword(){ return keyword; }
    public String getNameAndKeyWord(){
        return name+" ("+keyword+")";
    }

    public Room getRoom(){
        return room;
    }

    /**
     * Prepare string to print out variables (keyword and room)
     * @return
     */
    public String toString()
    {
        return name + "("+keyword+") -> "+ room.getRoomTitle();
    }
}
