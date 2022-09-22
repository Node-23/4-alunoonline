package modules.escola.beans;

import install.escola.Alunos;
import modules.escola.dao.AlunoDao;
import modules.escola.dao.TurmaDao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table
public class Turma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 10, unique = true, nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(optional = false)
	private TipoTurma tipo;

    @OneToMany(mappedBy="turma")
    private List<Aluno> alunos;

    @ManyToOne
    private Aluno representante;

    @ManyToOne
    private Professor professor;

    @ManyToOne
    private Curso curso;

    public Turma() {
    }

    public Turma(String codigo, String nome) {
        setCodigo(codigo);
        setNome(nome);
    }

    public Turma(String codigo, String nome, Aluno representante){
        setCodigo(codigo);
        setNome(nome);
        setRepresentante(representante);
    }

    public Turma(String codigo, String nome, Aluno representante, Professor professor) {
        this.codigo = codigo;
        this.nome = nome;
        this.representante = representante;
        this.professor = professor;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

	public TipoTurma getTipo() {
		return tipo;
	}

	public void setTipo(TipoTurma tipo) {
		this.tipo = tipo;
	}

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Aluno> getAlunos() {
        return AlunoDao.listByTurmaId(this.getId());
    }

    public void setRepresentante(Aluno representante) {
        this.representante = representante;
    }

    public Aluno getRepresentante() {
        return representante;
    }
    public Professor getProfessor() {return professor;}
    public void setProfessor(Professor professor) {this.professor = professor;}

    public long getTotalAlunos() {
        return TurmaDao.getTotalAlunosPara(this);
    }

    public void RemoverProfessor(){
        this.professor = null;
    }

    public void fillFromForm(Turma turmaForm) {
        this.setNome(turmaForm.getNome());
        this.setCodigo(turmaForm.getCodigo());
        this.setTipo(turmaForm.getTipo());
        this.setProfessor(turmaForm.getProfessor());
        this.setRepresentante(turmaForm.getRepresentante());
        this.setCurso(turmaForm.getCurso());
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
