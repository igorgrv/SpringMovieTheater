package com.movieTheater.model.form;

import org.hibernate.validator.constraints.NotBlank;

import com.movieTheater.model.Seat;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author igorg
 *
 */
public class SeatForm {
    @NotBlank
    private String row;
    @NotNull
    private Integer line;
    private Integer roomId;

	// -----------------------------------------------------------------
	// Methods
    public Seat toLugar() {
        return new Seat(row, line);
    }    
    
	// -----------------------------------------------------------------
	// Getters and Setters    
    public String getRow() {
        return row;
    }

    public void setRow(String fileira) {
        this.row = fileira;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer posicao) {
        this.line = posicao;
    }

}
