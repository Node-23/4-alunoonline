package modules.escola.beans;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Professor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 10, unique = true, nullable = false)
    private String matricula;
    @Column(nullable = false)
    private String nomeCompleto;
    @OneToMany(mappedBy="professor")
    private List<Turma> turmas;
    @ManyToOne
    private Curso curso;

    public Professor(){}

    public Professor(String matricula, String nomeCompleto) {
        this.matricula = matricula;
        this.nomeCompleto = nomeCompleto;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getMatricula() {return matricula;}
    public void setMatricula(String matricula) {this.matricula = matricula;}
    public String getNomeCompleto() {return nomeCompleto;}
    public void setNomeCompleto(String nomeCompleto) {this.nomeCompleto = nomeCompleto;}
    public List<Turma> getTurmas() {return turmas;}
    public void setTurmas(List<Turma> turmas) {this.turmas = turmas;}
    public Curso getCurso() {return curso;}
    public void setCurso(Curso curso) {this.curso = curso;}
    public String ListarTurmas(){
        if(this.turmas == null || this.turmas.isEmpty()){
            return "Sem turmas cadastradas!";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < turmas.size(); i++) {
            if(i == turmas.size()-1){
                builder.append(turmas.get(i).getNome());
                break;
            }
            builder.append(turmas.get(i).getNome()).append(", ");
        }
        return builder.toString();
    }

    public void fillFromUpdateForm(Professor professor) {
        this.setNomeCompleto(professor.getNomeCompleto());
        this.setMatricula(professor.getMatricula());
        this.setCurso(professor.getCurso());
    }
}
