package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerPos {

    public PlayerPos xyArray[][] = new PlayerPos[10][10];
    public ArrayList<NewPos> sAndLPos = new ArrayList<>(12);
    private int playerXPos;
    private int playerYPos;
    private int playerPos ;
    private int posCir1 = 1;

    private int[] intArray = new int[3];

    public PlayerPos(int playerXPos, int playerYPos, int playerPos) {

        this.playerXPos = playerXPos;
        this.playerYPos = playerYPos;
        this.playerPos = playerPos;
    }

    public int getPlayerXPos() {
        return playerXPos;
    }

    public int getPlayerYPos() {
        return playerYPos;
    }

    public int getPlayerPos() {
        return playerPos;
    }

    public int[] movePlayer( int po) {

        playerPos = po;

        // Array of Player xy coordinates and Player Position
        int p = 100;
        int q = 0;
        int pos = 0;


        for (int i = 0; i < xyArray.length; i++) {
            for (int j = 0; j < xyArray[i].length; j++) {

                if (posCir1 % 2 == 1) {
                    pos = p - j;
                }
                if (posCir1 % 2 == 0) {
                    pos = p + j;
                }

                xyArray[i][j] = new PlayerPos(40 + 80 * j, 40 + 80 * i, pos);
                q = pos;
            }
            posCir1++;
            p = q - 10;
        }

        ArrayList<PlayerPos> list = new ArrayList<>();

        for (int i = 0; i < xyArray.length; i++) {
            for (int j = 0; j < xyArray[i].length; j++) {
                list.add(xyArray[i][j]);
            }
        }

        // Array of Player New Positions because of Snakes and Ladders
        sAndLPos.add(new NewPos(3, 39));
        sAndLPos.add(new NewPos(10, 12));
        sAndLPos.add(new NewPos(27, 53));
        sAndLPos.add(new NewPos(56, 84));
        sAndLPos.add(new NewPos(61, 99));
        sAndLPos.add(new NewPos(72, 90));
        sAndLPos.add(new NewPos(16, 13));
        sAndLPos.add(new NewPos(31, 4));
        sAndLPos.add(new NewPos(47, 25));
        sAndLPos.add(new NewPos(63, 60));
        sAndLPos.add(new NewPos(66, 52));
        sAndLPos.add(new NewPos(97, 75));

        if (playerPos > 100) {
            playerPos = 100;
        }

        // Player 1 new positions due to snake and ladders using Java 8 list and stream API
        List<NewPos> list2 = sAndLPos.stream().filter(m -> m.getOldPos() == playerPos).collect(Collectors.toList());
        if (!list2.isEmpty()) {
            Integer[] array = list2.stream().map(x -> x.getNewPos()).toArray(Integer[]::new);
            System.out.println(array[0]);
            playerPos = array[0];
        }
        // Player 1 xy co ordiantes and position using Java 8 list and stream API
        List<PlayerPos> list1 = list.stream().filter(x -> x.getPlayerPos() == playerPos).collect(Collectors.toList());

        Integer[] xArray = list1.stream().map(z -> z.getPlayerXPos()).toArray(Integer[]::new);
        playerXPos = xArray[0];

        Integer[] yArray = list1.stream().map(z -> z.getPlayerYPos()).toArray(Integer[]::new);
        playerYPos = yArray[0];

        intArray[0] = playerXPos;
        intArray[1] = playerYPos;
        intArray[2] = playerPos;

        return intArray;
    }
}
