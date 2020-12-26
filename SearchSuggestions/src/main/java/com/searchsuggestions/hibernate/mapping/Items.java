package com.searchsuggestions.hibernate.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ITEMS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Items {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	private String itemName;

	// ...
	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

	// getters and setters
}