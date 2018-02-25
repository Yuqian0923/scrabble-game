package edu.cmu.cs.cs214.hw4.core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Board {
    /**
     * board size.
     */
    private Location[][] locationsList;
    /**
     * word list.
     */
    private ArrayList<Word> wordList;
    /**
     * check if it is first move.
     */
    private boolean isFirstMove = true;

    /**
     * board size.
     */
    private final int boardSize = 15;

    /**
     * constructor.
     */
    public Board() {
        wordList = new ArrayList<>();
        locationsList = new Location[15][15];
        initBoard();
    }

    /**
     * init location to the board.
     */
    private void initBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                locationsList[i][j] = new Location(i, j);
                locationsList[i][j].setSquare(new NormalSquare());
            }
        }
        locationsList[0][0].setSquare(new TripleWord());
        locationsList[0][7].setSquare(new TripleWord());
        locationsList[0][14].setSquare(new TripleWord());
        locationsList[7][0].setSquare(new TripleWord());
        locationsList[7][14].setSquare(new TripleWord());
        locationsList[14][0].setSquare(new TripleWord());
        locationsList[14][7].setSquare(new TripleWord());
        locationsList[14][14].setSquare(new TripleWord());
        locationsList[1][1].setSquare(new DoubleWord());
        locationsList[2][2].setSquare(new DoubleWord());
        locationsList[3][3].setSquare(new DoubleWord());
        locationsList[4][4].setSquare(new DoubleWord());
        locationsList[7][7].setSquare(new DoubleWord());
        locationsList[10][10].setSquare(new DoubleWord());
        locationsList[11][11].setSquare(new DoubleWord());
        locationsList[12][12].setSquare(new DoubleWord());
        locationsList[13][13].setSquare(new DoubleWord());
        locationsList[13][1].setSquare(new DoubleWord());
        locationsList[12][2].setSquare(new DoubleWord());
        locationsList[11][3].setSquare(new DoubleWord());
        locationsList[10][4].setSquare(new DoubleWord());
        locationsList[4][10].setSquare(new DoubleWord());
        locationsList[3][11].setSquare(new DoubleWord());
        locationsList[2][12].setSquare(new DoubleWord());
        locationsList[1][13].setSquare(new DoubleWord());
        locationsList[0][3].setSquare(new DoubleLetter());
        locationsList[0][11].setSquare(new DoubleLetter());
        locationsList[2][6].setSquare(new DoubleLetter());
        locationsList[2][8].setSquare(new DoubleLetter());
        locationsList[3][0].setSquare(new DoubleLetter());
        locationsList[3][7].setSquare(new DoubleLetter());
        locationsList[3][14].setSquare(new DoubleLetter());
        locationsList[6][2].setSquare(new DoubleLetter());
        locationsList[6][6].setSquare(new DoubleLetter());
        locationsList[6][8].setSquare(new DoubleLetter());
        locationsList[6][12].setSquare(new DoubleLetter());
        locationsList[7][3].setSquare(new DoubleLetter());
        locationsList[7][11].setSquare(new DoubleLetter());
        locationsList[8][2].setSquare(new DoubleLetter());
        locationsList[8][6].setSquare(new DoubleLetter());
        locationsList[8][8].setSquare(new DoubleLetter());
        locationsList[8][12].setSquare(new DoubleLetter());
        locationsList[11][0].setSquare(new DoubleLetter());
        locationsList[11][7].setSquare(new DoubleLetter());
        locationsList[11][14].setSquare(new DoubleLetter());
        locationsList[12][6].setSquare(new DoubleLetter());
        locationsList[12][8].setSquare(new DoubleLetter());
        locationsList[14][3].setSquare(new DoubleLetter());
        locationsList[14][11].setSquare(new DoubleLetter());
        locationsList[1][5].setSquare(new TripleLetter());
        locationsList[1][9].setSquare(new TripleLetter());
        locationsList[5][1].setSquare(new TripleLetter());
        locationsList[5][5].setSquare(new TripleLetter());
        locationsList[5][9].setSquare(new TripleLetter());
        locationsList[5][13].setSquare(new TripleLetter());
        locationsList[9][1].setSquare(new TripleLetter());
        locationsList[9][5].setSquare(new TripleLetter());
        locationsList[9][9].setSquare(new TripleLetter());
        locationsList[9][13].setSquare(new TripleLetter());
        locationsList[13][5].setSquare(new TripleLetter());
        locationsList[13][9].setSquare(new TripleLetter());
    }

    public Location[][] getLocationList() {
        return this.locationsList;
    }

    /**
     * check if the given coordinate.
     *
     * @param x coordinate x.
     * @param y coordinate y.
     * @return boolean.
     */

    public boolean isOnBoard(int x, int y) {
        return x < boardSize && y < boardSize && x >= 0 && y >= 0;
    }

    /**
     * check if the given has been occupied by a tile.
     *
     * @param x coordinate x.
     * @param y coordinate y.
     * @return boolean.
     */
    public boolean isOccupied(int x, int y) {
        if (!isOnBoard(x, y)) {
            return false;
        }
        return (getLocation(x, y).isOccupied());
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * get the word from first turn.
     *
     * @param list location list take from user input.
     * @return Word object.
     */

    public Word getFirstWord(Location[] list) {
        StringBuilder str = new StringBuilder();
        Arrays.sort(list);
        ArrayList<Location> locations = new ArrayList<>();

        for (Location loc : list) {
            str.append(getLocation(loc.getX(), loc.getY()).getTile().getLetter());
            locations.add(loc);
        }
        ArrayList<Location> curlist = new ArrayList<>();
        for (int listnum = 0; listnum < curlist.size(); listnum++) {
            curlist.add(list[listnum]);
        }
        return new Word(String.valueOf(str), locations, curlist);
    }


    /**
     * checks if the letter (row) of all the coordinates of a ship are the same.
     *
     * @param list list of Location
     * @return {boolean}
     */
    private boolean sameRow(Location[] list) {
        int comparison = list[0].getY();
        for (int i = 1; i < list.length; i++) {
            if (comparison != list[i].getY()) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks if the letter (col) of all the coordinates of a ship are the same.
     *
     * @param list list of tuple coordinates
     * @return {boolean}
     */
    private boolean sameCol(Location[] list) {
        int comparison = list[0].getX();
        for (int i = 1; i < list.length; i++) {
            if (comparison != list[i].getX()) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks that location:
     * is completely vertical OR completely horizontal
     * has correct size
     * occupies consecutive squares on board
     *
     * @param list {Location[]} list of coordinates
     * @return {boolean}
     */
    public boolean isValidTile(Location[] list) {
        Arrays.sort(list);
        if (list.length < 1) {
            System.out.println("get here 0");
            return false;
        }
        for (Location l : list) {
            if (l == null) {
                System.out.println("location is null");
                //return false;
            }
            if (!isOnBoard(l.getX(), l.getY())) {
                System.out.println("get here 1");
                return false;
            }
           /* if (isOccupied(l.getX(), l.getY())) {
                System.out.println("get here 2");
                return false;
            }*/
        }
        if (!sameCol(list) && !sameRow(list)) {
            System.out.println("get here 3");
            return false;
        }
        if (sameCol(list)) {
            //Location[] sortedList = sortY(list);
            for (int i = 0; i < list.length - 1; i++) {
                int gap = 1;
                while (isOnBoard(list[i].getX(), list[i].getY() + gap) &&
                        getLocation(list[i].getX(), list[i].getY() + gap).isOccupied()) {
                    gap += 1;
                }
                if (list[i].getY() + gap != list[i + 1].getY()) {
                    /*if (!list[i + 1].isOccupied()) {
                        return false;
                    }*/
                }
            }


        } /*else if (sameRow(list)) {
            //Location[] sortedList = sortX(list);
            for (int i = 0; i < list.length - 1; i++) {
                int gap = 1;
                while (isOnBoard(list[i].getX() + gap, list[i].getY()) &&
                        getLocation(list[i].getX() + gap, list[i].getY()).isOccupied()) {
                    gap += 1;
                }
                if (list[i].getX() + gap != list[i + 1].getX()) {
                    System.out.println("get here 4");
                    return false;
                }
            }
        }*/
        return true;
    }

    /**
     * check the input locations if at least one is true than the list is legal
     *
     * @param list the list under checked.
     * @return boolean if the location is valid.
     */
    public boolean isValidLocation(Location[] list) {

        if (!getLocation(7, 7).isOccupied()) {
            return true;
        }
        int mark = 0;
        for (Location loc : list) {
            if (isOnBoard(loc.getX() - 1, loc.getY()) && getLocation(loc.getX() - 1, loc.getY()).isOccupied()) {
                mark++;
                //return true;
            }
            if (isOnBoard(loc.getX() + 1, loc.getY()) && getLocation(loc.getX() + 1, loc.getY()).isOccupied()) {
                mark++;
            }
            if (isOnBoard(loc.getX(), loc.getY() - 1) && getLocation(loc.getX(), loc.getY() - 1).isOccupied()) {
                mark++;
            }
            if (isOnBoard(loc.getX(), loc.getY() + 1) && getLocation(loc.getX(), loc.getY() + 1).isOccupied()) {
                mark++;
            }
        }
        return mark >= 1;
    }

    /**
     * For same column, check if there's new word forms in the adjacent column.
     *
     * @param list location list.
     * @return word list.
     */
    public ArrayList<Word> checkLeftRightCol(Location[] list) {
        ArrayList<Word> leftWords = new ArrayList<>();
        ArrayList<Location> locations = new ArrayList<>();
        locations.addAll(Arrays.asList(list));

        for (Location loc : list) {
            Stack<String> wordList = new Stack<>();
            StringBuilder newWord = new StringBuilder();

            int i = 1;
            if (isOnBoard(loc.getX() - 1, loc.getY()) && (getLocation(loc.getX() - 1, loc.getY()).isOccupied())) {

                while (isOnBoard(loc.getX() - i, loc.getY()) && (getLocation(loc.getX() - i, loc.getY()).isOccupied())) {
                    Location temp = getLocation(loc.getX() - i, loc.getY());
                    wordList.push(temp.getTile().getLetter());
                    i++;
                }

                while (!wordList.empty()) {
                    newWord.append(wordList.pop());
                }


            }
            ///if right column also has letters.
            int j = 1;
            if (isOnBoard(loc.getX() + 1, loc.getY()) && (getLocation(loc.getX() + 1, loc.getY()).isOccupied())) {
                newWord.append(loc.getTile().getLetter());
                while (isOnBoard(loc.getX() + j, loc.getY()) && (getLocation(loc.getX() + j, loc.getY()).isOccupied())) {
                    Location temp = getLocation(loc.getX() + j, loc.getY());
                    newWord.append(temp.getTile().getLetter());
                    j++;
                }
            } else {
                if (i != 1) {
                    newWord.append(loc.getTile().getLetter());
                }
            }
            if (i != 1 || j != 1) {
                ArrayList<Location> curlist = new ArrayList<>();
                for (int listnum = 0; listnum < curlist.size(); listnum++) {
                    curlist.add(list[listnum]);
                }
                leftWords.add(new Word(String.valueOf(newWord), locations, curlist));
            }
        }
        return leftWords;
    }

    /**
     * For same row, check if there's new word form in the adjacent row.
     *
     * @param list location list.
     * @return word list.
     */
    private ArrayList<Word> checkUpDownRow(Location[] list) {
        ArrayList<Word> UpWords = new ArrayList<>();
        int i = 1;
        ArrayList<Location> locations = new ArrayList<>();

        for (Location loc : list) {
            locations.add(loc);
            Stack<String> wordList = new Stack<>();
            StringBuilder newWord = new StringBuilder();
            if (isOnBoard(loc.getX(), loc.getY() - 1) && (getLocation(loc.getX(), loc.getY() - 1).isOccupied())) {

                while (isOnBoard(loc.getX(), loc.getY() - i) && (getLocation(loc.getX(), loc.getY() - i).isOccupied())) {
                    //Location temp = new Location(loc.getX(), loc.getY() - i);
                    Location temp = getLocation(loc.getX(), loc.getY() - i);
                    wordList.push(temp.getTile().getLetter());
                    i++;
                }
                while (!wordList.empty()) {
                    newWord.append(wordList.pop());
                }
            }
            newWord.append(loc.getTile().getLetter());
            int j = 1;
            if (isOnBoard(loc.getX(), loc.getY() + 1) && (getLocation(loc.getX(), loc.getY() + 1).isOccupied())) {

                while (isOnBoard(loc.getX(), loc.getY() + j) && (getLocation(loc.getX(), loc.getY() + j).isOccupied())) {
                    //Location temp = new Location(loc.getX(), loc.getY() + j);
                    Location temp = getLocation(loc.getX(), loc.getY() + j);
                    newWord.append(temp.getTile().getLetter());
                    j++;
                }
            }
            if (i != 1 || j != 1) {
                ArrayList<Location> curlist = new ArrayList<>();
                for (int listnum = 0; listnum < curlist.size(); listnum++) {
                    curlist.add(list[listnum]);
                }
                UpWords.add(new Word(String.valueOf(newWord), locations, curlist));
            }
        }
        return UpWords;
    }


    /**
     * store the words generate from last turn. How to challenge???
     *
     * @param list the valid input list.
     * @return a stack of word from last turn.
     */
    public ArrayList<Word> storeWord(Location[] list) {
        //Arrays.sort(list);
        ArrayList<Word> wordList = new ArrayList<>();
        if (sameCol(list)) {
            wordList.addAll(sameColWord(list));
            if (checkLeftRightCol(list).size() != 0) {
                wordList.addAll(checkLeftRightCol(list));
            }
        } else if (sameRow(list)) {
            wordList.addAll(sameRowWord(list));
            if (checkUpDownRow(list).size() != 0) {
                wordList.addAll(checkUpDownRow(list));
            }
        }
        this.wordList = wordList;
        for (Word word : wordList) {
            System.out.println(word.toString());
        }

        return wordList;
    }

    /**
     * For same column, check if there's new word from the same column.
     *
     * @param list location list.
     * @return word list.
     */
    public ArrayList<Word> sameColWord(Location[] list) {
        Arrays.sort(list);
        ArrayList<Word> wordList = new ArrayList<>();
        StringBuilder newWord = new StringBuilder();
        Stack<Location> locationStack = new Stack<>();
        Stack<String> wordStack = new Stack<>();
        ArrayList<String> after = new ArrayList<>();
        ArrayList<Location> afterloc = new ArrayList<>();
        Location startLocation = list[0];
        Location endLocation = list[list.length - 1];
        ArrayList<Location> locations = new ArrayList<>();
        int i = 1;
        int j = 1;
        //first find out the end of this word.
        // collect the letters after location list.
        if (isOnBoard(endLocation.getX(), endLocation.getY() + 1) && (getLocation(endLocation.getX(),
                endLocation.getY() + 1).isOccupied())) {
            System.out.println("get here");
            while (isOnBoard(endLocation.getX(), endLocation.getY() + i) && (getLocation(endLocation.getX(),
                    endLocation.getY() + i).isOccupied())) {
                Location temp = getLocation(endLocation.getX(), endLocation.getY() + i);
                after.add(temp.getTile().getLetter());
                afterloc.add(getLocation(endLocation.getX(), endLocation.getY() + i));
                i++;
            }
        }
        // Then find out the start of this word.
        // Collect into Stack.
        if (isOnBoard(startLocation.getX(), startLocation.getY() - 1) && (getLocation(startLocation.getX(),
                startLocation.getY() - 1).isOccupied())) {
            while (isOnBoard(startLocation.getX(), startLocation.getY() - j) && (getLocation(startLocation.getX(),
                    startLocation.getY() - j).isOccupied())) {
                //Location temp = new Location(startLocation.getX(), startLocation.getY() - j);
                Location temp = getLocation(startLocation.getX(), startLocation.getY() - j);
                wordStack.push(temp.getTile().getLetter());
                locationStack.push(getLocation(startLocation.getX(), startLocation.getY() - j));
                j++;
            }
        }

        if (wordStack.size() != 0) {
            while (!wordStack.empty()) {
                newWord.append(wordStack.pop());
            }
        }
        for (Location loc : list) {
            newWord.append(loc.getTile().getLetter());
        }
        if (after.size() != 0) {
            for (String str : after) {
                newWord.append(str);
            }
        }

        if (locationStack.size() != 0) {
            while (!locationStack.empty()) {
                locations.add(locationStack.pop());
            }
        }

        for (int k = 0; k < list.length; k++) {
            locations.add(list[k]);
        }

        if (afterloc.size() != 0) {
            for (Location loc: afterloc) {
                locations.add(loc);
            }
        }

        ArrayList<Location> curlist = new ArrayList<>();
        for (int listnum = 0; listnum < curlist.size(); listnum++) {
            curlist.add(list[listnum]);
        }
        wordList.add(new Word(String.valueOf(newWord), locations, curlist));
        System.out.println("Trying to add word");
        System.out.println(newWord.toString());
        for (Location loc : locations) {
            System.out.println(loc.getTile().getLetter());
        }

        return wordList;
    }

    /**
     * For same row, check if there's new word form the same row.
     *
     * @param list location list.
     * @return word list.
     */

    public ArrayList<Word> sameRowWord(Location[] list) {
        Arrays.sort(list);
        Stack<String> wordStack = new Stack<>();
        Stack<Location> locationStack = new Stack<>();
        ArrayList<Word> wordList = new ArrayList<>();
        StringBuilder newWord = new StringBuilder();
        ArrayList<String> after = new ArrayList<>();
        ArrayList<Location> afterloc = new ArrayList<>();
        Location startLocation = list[0];
        Location endLocation = list[list.length - 1];
        // convert version
        ArrayList<Location> locations = new ArrayList<>();
        int i = 1;
        int j = 1;
        if (isOnBoard(endLocation.getX() + 1, endLocation.getY()) && (getLocation(endLocation.getX() + 1,
                endLocation.getY()).isOccupied())) {
            while (isOnBoard(endLocation.getX() + i, endLocation.getY()) && (getLocation(endLocation.getX() + i,
                    endLocation.getY()).isOccupied())) {
                //Location temp = new Location(endLocation.getX() + i, endLocation.getY());
                Location temp = getLocation(endLocation.getX() + i, endLocation.getY());
                afterloc.add(getLocation(endLocation.getX() + i, endLocation.getY()));
                after.add(temp.getTile().getLetter());
                i++;
            }
        }
        if (isOnBoard(startLocation.getX() - 1, startLocation.getY()) && (getLocation(startLocation.getX() - 1,
                startLocation.getY()).isOccupied())) {
            while (isOnBoard(startLocation.getX() - j, startLocation.getY()) && (getLocation(startLocation.getX() - j,
                    startLocation.getY()).isOccupied())) {
                //Location temp = new Location(startLocation.getX() - j, startLocation.getY());
                Location temp = getLocation(startLocation.getX() - j, startLocation.getY());
                locationStack.push(getLocation(startLocation.getX() - j, startLocation.getY()));
                wordStack.push(temp.getTile().getLetter());
                j++;

            }
        }

        if (wordStack.size() != 0) {
            while (!wordStack.empty()) {
                newWord.append(wordStack.pop());
            }
        }

        for (Location loc : list) {
            newWord.append(loc.getTile().getLetter());
        }

        if (after.size() != 0) {
            for (String str : after) {
                newWord.append(str);
            }
        }

        if (locationStack.size() != 0) {
            while (!locationStack.empty()) {
                locations.add(locationStack.pop());
            }
        }

        for (int k = 0; k < list.length; k++) {
            locations.add(list[k]);
        }

        if (afterloc.size() != 0) {
            for (Location loc: afterloc) {
                locations.add(loc);
            }
        }


        ArrayList<Location> curlist = new ArrayList<>();
        for (int listnum = 0; listnum < curlist.size(); listnum++) {
            curlist.add(list[listnum]);
        }
        wordList.add(new Word(String.valueOf(newWord), locations, curlist));
        System.out.println("Trying to add word");
        System.out.println(newWord.toString());
        for (Location loc : locations) {
            System.out.println(loc.getTile().getLetter());
        }
        return wordList;
    }

    /**
     * Add tile to certain location.
     *
     * @param tile location list.
     * @param x    x coordinate.
     * @param y    coordinate.
     */
    public void addTileToLocation(Tile tile, int x, int y) {
        Location loc = this.getLocation(x, y);
        if (loc != null && tile != null) {
            loc.addTile(tile);
        } else {
            System.out.println("The input has null error<1>!");
        }
    }

    /**
     * Remove tile to certain location.
     *
     * @param x x coordinate.
     * @param y coordinate.
     */
    public void removeTileFromLocation(int x, int y) {
        Location loc = this.getLocation(x, y);
        if (loc == null) {
            System.out.println("The input has null!");
        } else {
            loc.removeTile();
        }
    }

    /**
     * check if the first location is on the star.
     *
     * @return boolean.
     */
    public Boolean isOnStar(Location[] list) {
        if (list.length == 0) {
            System.out.println("The input location list is empty");
            return false;
        }
        for (Location loc : list) {

            if (loc.getX() == 7 || loc.getY() == 7) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the input location has been occupied by other tile.
     *
     * @return boolean.
     */
    public boolean isEmpty() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (locationsList[i][j].isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * get the location by coordinate.
     *
     * @param x coordinate x.
     * @param y coordinate y.
     * @return boolean.
     */
    public Location getLocation(int x, int y) {
        if (!isOnBoard(x, y)) {
            System.out.println("The location is not in the board!");
            return null;
        } else {
            return locationsList[x][y];
        }
    }

    public void setSpecialTileToLocation(SpecialTile sp, int x, int y) {
        getLocation(x, y).setSpecialTile(sp);
    }

}