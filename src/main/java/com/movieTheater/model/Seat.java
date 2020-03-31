package com.movieTheater.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author igorg
 */
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String row;

    @NotNull
    private Integer line;

    /**
     * @deprecated hibernate only
     */
    public Seat() {
    }

    public Seat(String nickname, Integer line) {
        this.row = nickname;
        this.line = line;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String nickname) {
        this.row = nickname;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((row == null) ? 0 : row.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((line == null) ? 0 : line.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Seat other = (Seat) obj;
        if (row == null) {
            if (other.row != null)
                return false;
        } else if (!row.equals(other.row))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (line == null) {
            if (other.line != null)
                return false;
        } else if (!line.equals(other.line))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.row + this.line;
    }

}
