package it.jac.project.dto;

import lombok.Data;

@Data
public class PartecipanteDto {
	
	int idPartecipante;
	int punteggioPartecipante;
	boolean amI;
	
	
    public int compareTo(PartecipanteDto element) {
        int res = 0;
        if (this.punteggioPartecipante < element.getPunteggioPartecipante()) {
            res =- 1;
        }else {
            res = 1;

        }
        return res;
    }
}
