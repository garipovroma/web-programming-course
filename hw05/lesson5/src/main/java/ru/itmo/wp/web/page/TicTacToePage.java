package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
    private final static int fieldSize = 3;

    private void action(HttpServletRequest request, Map<String, Object> view) {
        State state = null;
        if (request.getSession().getAttribute("state") == null) {
            request.getSession().setAttribute("state", new State(fieldSize));
        }
        state = (State) request.getSession().getAttribute("state");
        view.put("state", state);
    }
    private void newGame(HttpServletRequest request, Map<String, Object> view) {
        request.getSession().setAttribute("state", new State(fieldSize));
        view.put("state", request.getSession().getAttribute("state"));
    }
    private void onMove(HttpServletRequest request, Map<String, Object> view) {
        State state = (State) request.getSession().getAttribute("state");
        if (!state.getPhase().equals("RUNNING")) {
            view.put("state", state);
            return;
        }
        boolean moveFound = false;
        Map<String, String[]> parametersMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parametersMap.entrySet()) {
            if (entry.getKey().startsWith("cell_")) {
                String curMove = entry.getKey();
                state.setCell(curMove);
                break;
            }
        }
        view.put("state", state);
    }

    public static class State {
        private final int size;
        private int movesCount;
        private Cell[][] cells;
        private String phase;
        private boolean crossesMove;
        public State(int size) {
            this.size = size;
            this.movesCount = 0;
            this.cells = new Cell[size][size];
            this.phase = "RUNNING";
            this.crossesMove = true;
        }

        public int getSize() {
            return size;
        }

        public Cell[][] getCells() {
            return cells;
        }

        public String getPhase() {
            return phase;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }

        private void setCell(String cellCoordinates) {
            if (!phase.equals("RUNNING")) {
                throw new IllegalStateException("Game over, players cant move");
            }
            int coordinatesLength = (cellCoordinates.length() - 5) / 2;
            int l1 = 5, r1 = 5 + coordinatesLength, l2 = r1, r2 = cellCoordinates.length();
            int x = Integer.parseInt(cellCoordinates.substring(l1, r1)),
                    y = Integer.parseInt(cellCoordinates.substring(l2, r2));
            if (cells[x][y] != null) {
                return;
            }
            cells[x][y] = (crossesMove ? Cell.X : Cell.O);
            movesCount++;
            boolean gameOver = checkPosition(x, y);
            if (!gameOver && movesCount == size * size) {
                phase = "DRAW";
            } else if (gameOver) {
                phase = (crossesMove ? "WON_X" : "WON_O");
            } else {
                crossesMove ^= true;
            }
        }
        private boolean checkPosition(int x, int y) {
            boolean res = false;
            res |= go(x, y, x - size + 1, y, 1, 0);
            res |= go(x, y, x, y - size + 1, 0, 1);
            res |= go(x, y, x - size + 1,  y - size + 1, 1, 1);
            res |= go(x, y, x + size - 1, y - size + 1, -1, 1);
            return res;
        }
        private boolean inField(int x, int y) {
            return (0 <= x && x <= size - 1) && (0 <= y && y <= size - 1);
        }
        private boolean go(int x, int y, int begX, int begY, int dx, int dy) {
            boolean res = false;
            int count = 0;
            for (int i = 0; i < 2 * size - 1; i++, begX += dx, begY += dy) {
                if (inField(begX, begY)) {
                    if (cells[begX][begY] == cells[x][y]) {
                        count++;
                    } else {
                        count = 0;
                    }
                    if (count == size) {
                        res = true;
                        break;
                    }
                } else {
                    count = 0;
                }
            }
            return res;
        }
    }
    public enum  Cell {
        X, O;
    }
}
