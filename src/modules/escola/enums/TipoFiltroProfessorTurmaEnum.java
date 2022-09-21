package modules.escola.enums;

public enum TipoFiltroProfessorTurmaEnum {
    PROFESSOR_SEM_TURMA("Professor sem turma"),
    PROFESSOR_COM_TURMA("Professor com turma");

    private final String rotulo;

    TipoFiltroProfessorTurmaEnum(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getId(){
        return name();
    }
    public String getRotulo() {
        return rotulo;
    }
}
