package modules.escola.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class TipoCurso implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nome;

	public TipoCurso(){}

	public TipoCurso(String nome) { setNome(nome); }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
