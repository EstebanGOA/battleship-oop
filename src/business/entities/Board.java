package business.entities;

import java.awt.*;

public class Board {

    private Tile[][] tiles;
    private int BOARD_START = 0;
    private int BOARD_FINISH = 15;


    public Board() {
        this.tiles = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.tiles[i][j] = new Tile();
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public boolean placeShip(Ship ship) {

        int boardLimit = BOARD_FINISH - ship.getSize();

        int x = ship.getPosition()[0];
        int y = ship.getPosition()[1];

        if (ship.getOrientation().equals("horizontal")) {
            return horizontalInsert(boardLimit, ship, x, y);
        } else if (ship.getOrientation().equals("vertical")) {
            return verticalInsert(boardLimit, ship, x, y);
        } else {
            return false;
        }

    }

    public TileType getTile(int x, int y) {
        if (x >= BOARD_START && x < BOARD_FINISH && y >= BOARD_START && y < BOARD_FINISH) {
            return tiles[y][x].getTileType();
        }
        return null;
    }

    public boolean checkAdjacent(int dx, int dy) {

        if (getTile(dx, dy) != TileType.WATER && getTile(dx, dy) != null ) return false;
        if (getTile(dx + 1, dy) != TileType.WATER && getTile(dx + 1, dy) != null) return false;
        if (getTile(dx - 1, dy) != TileType.WATER && getTile(dx - 1, dy) != null) return false;
        if (getTile(dx, dy + 1) != TileType.WATER && getTile(dx, dy + 1) != null) return false;
        if (getTile(dx, dy - 1) != TileType.WATER && getTile(dx, dy - 1) != null) return false;
        if (getTile(dx + 1, dy + 1) != TileType.WATER && getTile(dx + 1, dy + 1) != null) return false;
        if (getTile(dx - 1, dy - 1) != TileType.WATER && getTile(dx - 1, dy - 1) != null) return false;
        if (getTile(dx + 1, dy - 1) != TileType.WATER && getTile(dx + 1, dy - 1) != null) return false;
        if (getTile(dx - 1, dy + 1) != TileType.WATER && getTile(dx - 1, dy + 1) != null) return false;
        return true;

    }

    public boolean verticalInsert(int boardLimit, Ship ship, int x, int y) {

        boolean result = true;

        if (x >= BOARD_START && x < BOARD_FINISH && y >= BOARD_START && y <= boardLimit ) {
            // Para cada posición del barco comprobamos todos sus bloques adyacentes.
            for (int i = 0; i < ship.getSize(); i++) {
                int dx = x;
                int dy = y + i;
                if (!checkAdjacent(dx, dy)) {
                    return false;
                }
            }

            for (int i = 0; i < ship.getSize(); i++) {
                tiles[y+i][x] = new ShipSegment(ship);
                ship.addSegment((ShipSegment) tiles[y+i][x]);
                tiles[y+i][x].setTileType(TileType.SHIP);
                tiles[y+i][x].changeColor(Color.GREEN);
            }

            return result;

        } else {

            return false;

        }
    }

    public boolean horizontalInsert(int boardLimit, Ship ship, int x, int y) {

        boolean result = true;

        if (x >= BOARD_START && x <= boardLimit && y >= BOARD_START && y < BOARD_FINISH ) {
            // Para cada posición del barco comprobamos todos sus bloques adyacentes.
            for (int i = 0; i < ship.getSize(); i++) {
                int dy = y;
                int dx = x + i;
                if (!checkAdjacent(dx, dy)) {
                    return false;
                }
            }

            for (int i = 0; i < ship.getSize(); i++) {
                tiles[y][x+i] = new ShipSegment(ship);
                ship.addSegment((ShipSegment) tiles[y][x+i]);
                tiles[y][x+i].setTileType(TileType.SHIP);
                tiles[y][x+i].changeColor(Color.GREEN);
            }

            return result;

        } else {

            return false;

        }
    }

    public void remove(Ship ship) {
        int x = ship.getPosition()[0];
        int y = ship.getPosition()[1];
        if (ship.getOrientation().equals("horizontal")) {
            for (int i = 0; i < ship.getSize(); i++) {
                    tiles[y][x+i] = new Tile();
            }
        } else {
            for (int i = 0; i < ship.getSize(); i++) {
                tiles[y+i][x] = new Tile();
            }
        }
    }

    public boolean sendAttack(Player attacker, int x, int y) {

        TileType tileType = tiles[y][x].getTileType();
        tiles[y][x].changeColor(attacker.getColor());

        if (tileType == TileType.HIT || tileType == TileType.MISS) {
            // Casilla donde ya se ha disparado anteriormente.
            return false;
        } else {
            // Si no se ha disparado anteriormente actualizaremos la casilla a HIT, en caso de encontrarse con un barco,
            // si no nos encontramos con un barco actualizaremos la casilla a MISS.
            if (tiles[y][x] instanceof ShipSegment) {
                tiles[y][x].setTileType(TileType.HIT);
                return true;
            } else {
                tiles[y][x].setTileType(TileType.MISS);
                return false;
            }

        }

    }

}
