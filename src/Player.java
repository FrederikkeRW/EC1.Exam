public class Player {

    int health = 100;
    String name;
    public Medic medic;

    public void takeDamage(int damage){
        health -= damage;

    }

    public boolean isAlive() {return health > 0;}

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
     * The player takes the first aid kid from room.
     * @param room
     */

    public void takeFirstAidKidFromRoom(Room room) {
        medic = room.medic;
        room.medic = null;
    }

    /**
     * The player uses the first aid kid, and gets healed.
     */

    public void useMedic(){
        if (medic != null){
            medic.healPlayer(this);
        }
    }
}
