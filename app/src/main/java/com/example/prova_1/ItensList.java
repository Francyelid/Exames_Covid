package com.example.prova_1;

/**Classe para objetos do tipo ItensList, onde serão contidos, valores e métodos para o mesmo.
 * @author Laris
 */
public class ItensList {
    private String Name = null;
    private int YearsOld = 0;
    private String Sex = null;
    private Boolean Igg = true;

    /** Método para retorno do nome do paciente
     * @return String - Nome do paciente*/
    public String getName() {
        return Name;
    }

    /** Método para definir o Nome do paciente*/
    public void setName(String name) {
        Name = name;
    }

    /** Método para retorno da idade do paciente
     * @return String - Idade do paciente*/
    public int getYearsOld() {
        return YearsOld;
    }

    /** Método para definir a idade do paciente*/
    public void setYearsOld(int yearsOld) {
        YearsOld = yearsOld;
    }

    /** Método para retorno do sexo do paciente
     * @return String - Sexo do paciente*/
    public String getSex() {
        return Sex;
    }

    /** Método para definir o Sexo do paciente*/
    public void setSex(String sex) {
        Sex = sex;
    }

    /** Método para retorno do IGG do paciente
     * @return String - IGG do paciente*/
    public Boolean getIgg() {
        return Igg;
    }

    /** Método para definir o IGG do paciente*/
    public void setIgg(Boolean igg) {
        Igg = igg;
    }
}
