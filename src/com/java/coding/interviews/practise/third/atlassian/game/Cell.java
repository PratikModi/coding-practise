package com.java.coding.interviews.practise.third.atlassian.game;

import java.util.Objects;

public class Cell {
    private int row;
    private int column;
    private CellType cellType;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.cellType=CellType.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row && column == cell.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "row=" + row +
                ", column=" + column +
                ", cellType=" + cellType +
                '}';
    }
}
