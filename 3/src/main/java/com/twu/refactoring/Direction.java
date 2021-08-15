package com.twu.refactoring;


import java.util.Objects;

public class Direction {

    private final Type type;

    public  Direction(char value) {
        this.type = Type.valueOf(String.valueOf(value));
    }



    public Direction turnRight() {
        switch (type) {
            case N:
                return new Direction('E');
            case S:
                return new Direction('W');
            case E:
                return new Direction('N');
            case W:
                return new Direction('S');
            default:
                throw new IllegalArgumentException("this direction not exist");
        }
    }

    public Direction turnLeft() {
        switch (type) {
            case N:
                return new Direction('W');
            case S:
                return new Direction('E');
            case E:
                return new Direction('N');
            case W:
                return new Direction('S');
            default:
                throw new IllegalArgumentException("this direction not exist");
        }
    }


    public enum Type {
        N("North"),
        E("East"),
        W("West"),
        S("South");

        private final String description;

        Type(String description) {

            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direction direction = (Direction) o;
        return type == direction.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Direction{direction=" + type.description + '}';
    }
}
