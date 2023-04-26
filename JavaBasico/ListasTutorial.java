import java.util.ArrayList;
import java.util.List;

public class ListasTutorial {

  public static void main(String args[]) {
    List<Persona> list_persona = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      Persona per = new Persona();

      per.setCodigo(i);
      per.setNombre("Walter " + i);
      per.setApellido("Reyes " + i);
      per.setEdad(25 + i);

      list_persona.add(per);
    }

    System.out.println("Tamaño de lista: " + list_persona.size());
    System.out.println(
      "Dato lista: " +
      list_persona.get(0).getNombre() +
      " " +
      list_persona.get(0).getApellido()
    );

    for (Persona p : list_persona) {
      System.out.println("Codigo: " + p.getCodigo());
      System.out.println("Nombre: " + p.getNombre());
      System.out.println("Apellido: " + p.getApellido());
      System.out.println("Edad: " + p.getEdad());
      System.out.println("----------------------------------------");
    }
  }
}
