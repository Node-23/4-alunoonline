package modules.escola.beans;

import modules.escola.dao.CursoDao;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class Curso implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nome;

	@Column(length = 10, unique = true, nullable = false)
	private String codigo;

	@ManyToOne(optional = false)
	private TipoCurso tipo;

	@OneToMany(mappedBy = "curso")
	private List<Turma> turmas;

	@OneToMany(mappedBy = "curso")
	private List<Professor> professores;


	public Curso() {
	}

	public Curso(String codigo, String nome) {
		setCodigo(codigo);
		setNome(nome);
	}

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TipoCurso getTipo() {
		return tipo;
	}

	public void setTipo(TipoCurso tipo) {
		this.tipo = tipo;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	public long getTotalTurmas() {
		return CursoDao.getTotalTurmasPara(this);
	}
	public List<Professor> getProfessores() {return professores;}
	public void setProfessores(List<Professor> professores) {this.professores = professores;}

	public void fillFromForm(Curso cursoForm) {
		this.setNome(cursoForm.getNome());
		this.setCodigo(cursoForm.getCodigo());
		this.setTipo(cursoForm.getTipo());
	}
}
