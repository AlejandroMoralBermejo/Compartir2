import java.util.ArrayList;
import java.util.regex.*; // uso esta libreria para la expresion regular del dni
public class Cliente {
  private String nombre;
  private String dni;
  private int edad;
  private String correo;
  private TipoEntrada tipoEntrada;
  private static ArrayList<Cliente> menores = new ArrayList<>(); // Lista donde guardo todas las instancias que son menores de edad
  private static ArrayList<Cliente> mayores = new ArrayList<>();

  //Constructores

  Cliente(String nombre, String dni, int edad, String correo, int tipoEntrada){
    setNombre(nombre);
    if(comprobarDni(dni)){
      this.dni = dni;
    }else{
      this.dni = "12345678A";
    }
    setEdad(edad);
    setCorreo(correo);
    // Las entradas las pediremos por numero, la entrada 1 es la basica, la 2 es la intermedia y la 3 es la avanzada, en caso de que el usuario ponga otro numero ponfré por defecto la intermedia
    TipoEntrada entrada;
    entrada = elegirEntrada(tipoEntrada);
    setTipoEntrada(entrada);
  }

  //Getters
  public String getNombre(){return this.nombre;}
  public String getDni(){return this.dni;}
  public int getEdad(){return this.edad;}
  public String getCorreo(){return this.correo;}
  public TipoEntrada getTipoEntrada(){return this.tipoEntrada;}
  public ArrayList<Cliente> getListaMenores(){return this.menores;}
  public ArrayList<Cliente> getListaMayores(){return this.mayores;}
  //Setters
  public void setNombre(String nombre){this.nombre=nombre;}
  public boolean setCorreo(String correo){
    if (comprobarCorreo(correo)){
      // En caso de que el correo sea correcto lo cambiamos y devolvemos true
      this.correo = correo;
      return true;
    }else{
      // En caso de que el correo no sea correcto devolvemos false
      return false;
    }
  }
  public boolean setEdad(int edad){
    if (comprobarEdad(edad)){
      // Si la edad es correcta la cambiamos y devolvemos true
      this.edad = edad;
      if(!esMenor()){
        // En caso de que el objeto sea mayor de edad y esté dentro de la lista de menores se eliminará
        if(menores.contains(this)){
          menores.remove(this);
        }
        // En caso de que sea mayord de edad y no contenga a la lista de mayores de edad se le anadirá
        if(!mayores.contains(this)){
          mayores.add(this);
        }
      }else{
        // En caso de que sea menor y esté dentro de la lista de mayores será eliminado
        if(mayores.contains(this)){
          menores.remove(this);
        }
        // En caso de que sea menor y no pertenezca a la lista se le anadirá
        if(!menores.contains(this)){
          menores.add(this);
        }
      }
      return true;
    }else{
      // en caso de que no sea correcta devolvemos false
      return false;
    }
  }
  public boolean setTipoEntrada(TipoEntrada tipoEntrada){
    // En este caso si la entrada elegida no es ninguna de las tres debuelve false y no cambia el tipo de entrada
    if (tipoEntrada == TipoEntrada.BASICA || tipoEntrada == TipoEntrada.INTERMEDIA || tipoEntrada == TipoEntrada.AVANZADA){
      this.tipoEntrada = tipoEntrada;
      return true;
    }
    else{
      return false;
    }
  }
  // Otros metodos
  public String toString(){return "Nombre: "+this.nombre+"\nDNI: "+this.dni+"\nEdad: "+this.edad+"\nCorreo: "+this.correo+"\nTipo de entrada: "+this.tipoEntrada;}
  public void cumplirAnios(){this.edad++;}
  // Utilizo este metodo para comprobar que los valores que nos proporciona el usuario están dentro del rango 0-150
  public boolean comprobarEdad(int edad){
    if (edad > 0 && edad < 150)
      return true; // si la edad es correcta devuelvo true
    else
      return false;
  }
  // Utilizo este metodo para comprobar si el dni es correcto
  public boolean comprobarDni(String dni){
    Pattern patronDni = Pattern.compile("[0-9]{8}[A-Z a-z]"); // creo el patrón para el dni que se compone de numeros del 0 al 9 [0-9] de los cuales hay 8 y una letra
    Matcher matDni = patronDni.matcher(dni);
    if (matDni.matches())
      return true;
    else
      return false;
  }
  // Utilizo este metodo para comprobar que el correo es correcto segun la expresion regular
  public boolean comprobarCorreo(String correo){
    Pattern patronCorreo = Pattern.compile("[A-Z a-z 0-9 . _]+@[A-Z a-z 0-9]+\\.[A-Z a-z]+");
    Matcher matCorreo = patronCorreo.matcher(correo);
    if (matCorreo.matches())
      // si el correo es correcto devuelvo true
      return true;
    else
      return false;
  }
  // En este metodo devuelvo los beneficios que tiene cada tipo de entrada
  public String beneficiosEntrada(){
    switch (this.tipoEntrada) {
      case BASICA:
        return "Solo la entrada";
      case AVANZADA:
        return "Barra libre";
      default: // Como default tengo la entrada intermediaen caso de que el valor dado no sea valido
        return "Entrada + 3 bebidas gratis";
    }
  }
  // En este metodo devuelvo si el objeto es menor de edad o no
  public boolean esMenor(){
    if(this.edad < 18) return true;
    else return false;
  }
  // En este metodo permito al main establecer el tipo de entrada en función de números ya que son más usados para la comunicación con el usuario
  public TipoEntrada elegirEntrada(int entrada){
    switch (entrada) {
      case 1:
        return TipoEntrada.BASICA;
      case 3:
        return TipoEntrada.AVANZADA;
      default:
        return TipoEntrada.INTERMEDIA;
    }
  }

}
