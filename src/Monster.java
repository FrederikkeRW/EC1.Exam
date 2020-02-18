import java.util.Random;
public class Monster {

    public String name;
    int strength;
    int health;

    /**
     * Constructor setting:
     * @param _name
     * @param _strengh
     * @param _health
     */
    public Monster(String _name, int _strengh, int _health){
        name = _name;
        strength = _strengh;
        health = _health;
    }

    /**
     * The fight.
     * The monster takes damage, and gives damage to player.
     * @param player
     */

    public void strike(Player player) {
        Random random = new Random();
        takeDamage(random.nextInt(100));
        if(isAlive()){
            player.takeDamage(strength);

        }

    }

    /**
     * If monster still alive, return true.
     * Monster is alive if health > 0.
     * @return
     */

    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Decrease health, with damage.
     * @param damage
     */

    private void takeDamage(int damage){ health -= damage;}

    @Override
    /**
     * Introduce monsters.
     */
    public String toString() {
      return "\tThe monsters name is: " + name + "\n\tThe strengh is: "+ strength + "\n\tThe health is: " + health;
    }
}




