public class Medic {

    int healing;
    private int maxUses;

    public int getMaxUses() {
        return maxUses;
    }

    /**
     * Constructor to medic. Setting healing and maxUses.
     * @param _healing
     * @param _maxUses
     */

    public Medic(int _healing, int _maxUses) {
        healing = _healing;
        maxUses = _maxUses;
    }

    /**
     * Healing. Encreases health on player. And decrease maxUses.
     * @param player
     */

    public void healPlayer(Player player) {
        if (maxUses > 0) {
            player.health += healing;
            maxUses--;
        }
    }

    /**
     * Returns true if this medic can be used. False if not.
     * @return
     */

    public boolean hasHealing() {
        if (maxUses > 0) {
            return true;
        }else {
            return false;
        }
    }

}
