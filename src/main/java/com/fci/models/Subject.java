package com.fci.models;

import javax.persistence.*;

/**
 * common error<br>
 * org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL
 * via JDBC Statement in SpringBoot with h2 and JPA<br>
 * the problem<br>
 * Your Entity Field names are matching with SQL reserved keywords example desc
 * so change it to description
 *
 */

@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 60, nullable = false)
	private String name;

//	@Column(columnDefinition = "text")
//	private String description;

	public Subject() {
	}

	public Subject(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subject [id=").append(id).append(", name=").append(name).append("]");
		return builder.toString();
	}

}
