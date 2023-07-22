package com.mygdx.game.consolGame.map;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int row, int col) {
        this.x = row;
        this.y = col;
    }

    public int[] toArray() {
        return new int[]{x, y};
    }

    public void setCoordinates(int row, int col) {
        this.x = row;
        this.y = col;
    }


    @Override
    public String toString() {
        return String.format("x: %d, y: %d", x, y);
    }

    public int getDistance(Coordinates targetPosition) {
        return (int) Math.sqrt(Math.pow(x - targetPosition.toArray()[0], 2) + Math.pow(y - targetPosition.toArray()[1], 2));
    }
    public Directions getDirection(Coordinates otherCoordinates) {
        int[] my = this.toArray();
        int[] other = otherCoordinates.toArray();
        if (Math.abs(my[0] - other[0]) > Math.abs(my[1] - other[1])) {
            if (my[0] > other[0]) {
                return Directions.NORTH;
            } else {
                return Directions.SOUTH;
            }
        } else {
            if (my[1] > other[1]) {
                return Directions.WEST;
            } else {
                return Directions.EAST;
            }
        }
    }

    public boolean isEqual(Coordinates coordinates) {
        return this.x == coordinates.x && this.y == coordinates.y;
    }
}
