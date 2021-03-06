package cdapi.bean;

import java.util.ArrayList;

/**
 *
 * @author Gyovanne Cavalcanti
 */
public class Componente {

    private final ArrayList<Object> content;
    private final String title;

    /**
     *
     * @param title
     * @param content
     */
    public Componente(String title, ArrayList<Object> content) {
        this.content = content;
        this.title = title;
    }

    /**
     * Retorna um ArrayList com o conteudo do componente.
     *
     * @return
     */
    public ArrayList<Object> getContent() {
        return content;
    }

    /**
     * Retorna o titulo do componente
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        String cont = "";
        for (int i = 0; i < content.size(); i++) {
            cont += content.get(i).toString() + "\n";
        }
        return title + "\n" + cont;
    }

}
