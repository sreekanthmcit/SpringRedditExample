package com.sreekanth.springit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Vote extends Auditable {
	
	@Id
	@GeneratedValue
	private Long id;
	private int vote;
	
	
	
	

}
