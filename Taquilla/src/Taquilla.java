
import java.util.*;
public class Taquilla{
  public static void main(String[] args) {
    Scanner escaner = new Scanner(System.in);
    ArrayList<Cliente> listClientes = new ArrayList<>();

    for (Cliente d : Registrar(listClientes)) {
      if (!listClientes.contains(d)) {
        listClientes.add(d); // En caso de que el objeto d no se encuentre ya en la lista se añade
      }
    }

    boolean salir = false;
    int eleccion ;
    do {
      System.out.println("Dígame qué quiere hacer ahora:\n1 --- Añadir nuevos clientes.\n2 --- Eliminar clientes.\n3 --- Filtrar clientes.\n4 --- Salir.");
      eleccion = escaner.nextInt();
      switch (eleccion) {
        case 1:
          Registrar(listClientes);
          break;
        case 2:
          Eliminar(listClientes);
          break;
        case 3:
          Filtros(listClientes);
          break;
        case 4:
          salir = true;
          System.out.println("¡Hasta la próxima! :)");
          break;
        default:
          System.out.println("Error al leer respuesta.");
      }
    } while (!salir);

    escaner.close();
  }


  public static ArrayList<Cliente> Eliminar(ArrayList<Cliente> listCliente){

    Scanner scann = new Scanner(System.in);

    String dni;
    System.out.println("Digame el DNI de la persona que quiere eliminar");
    dni = scann.nextLine();
    for(Cliente d : listCliente){
      if(dni == d.getDni()){ // En caso de que el dni coincida lo eliminará
        listCliente.remove(d);
        System.out.println(d + "\nEliminado con éxito");
      }
    }

    scann.close();
    return listCliente;
  }

  /**
   * Metodo en el que pregunto al usuario por la cantidad de clientes que quiere anadir
   *
   * @param listClientes
   */
  public static ArrayList<Cliente> Registrar(ArrayList<Cliente> listClientes){
    Scanner scann = new Scanner(System.in);
    int numeroClientes;
    System.out.print("Dime el numero de asistentes : ");
    numeroClientes = scann.nextInt();
    scann.nextLine();
    String nombre, dni, correo;
    int edad, elegirEntrada;

    // Utilizo este bucle for para conseguir la información de todos los usuarios
    for (int i = 0; i < numeroClientes; i++){
      System.out.print("Dime el nombre del usuario " + (i+1) + " :");
      nombre = scann.nextLine();
      System.out.print("Dime el dni de " + nombre + ": ");
      dni = scann.nextLine();
      System.out.print("Dime el correo de " + nombre + ": ");
      correo = scann.nextLine();
      System.out.print("Dime la edad de " + nombre + ": ");
      edad = scann.nextInt();
      scann.nextLine();
      System.out.println("Dime el tipo de entrada de " + nombre + ": ");
      System.out.print("Los tipos de entrada son (escribe el numero correspondiente):");
      // Utilizo el bucle for para mostrar todos los valores del enum TipoEntrada, la variable j me sirve para poner delante de cada uno un número
      int j = 1;
      for(TipoEntrada d: TipoEntrada.values()){
        System.out.print(j + " " + d.toString()+" - ");
        j ++ ;
      }
      elegirEntrada = scann.nextInt();
      scann.nextLine();
      listClientes.add(new Cliente(nombre, dni, edad, correo, elegirEntrada));
    }
    scann.close();
    return listClientes;
  }

  /**
   * Metodo en el que pregunto por filtros y muestro en funcion de los mismos
   * @param listClientes
   */
  public static void Filtros(ArrayList<Cliente> listClientes){
    Scanner scann = new Scanner(System.in);
    TipoEntrada filtarEntrada;
    int filtrarEdad, elegirEntrada;
    // Primero pregunto por el tipo de entrada
    System.out.println("Digame el tipo de entrada por la cual quiere filtrar (introduce el numero correspondiente a cada entrada).");
    int j = 1; // utilizo la variable j como contador para que le sea más sencillo al usuario identificar cada tipo
    // Muestro los tipos de entrada
    for(TipoEntrada d: TipoEntrada.values()) {
      System.out.print(j + " " + d.toString() + " - ");
      j++;
    }
    elegirEntrada = scann.nextInt();
    scann.nextLine();
    filtarEntrada = listClientes.get(0).elegirEntrada(elegirEntrada);
    // Pregunto por la edad para filtrar
    System.out.print("Dime si quires que muestre los menores(1) o mayores de edad(2): ");
    filtrarEdad = scann.nextInt();
    scann.nextLine();

    if(filtrarEdad == 1){
      for(Cliente d : listClientes.get(0).getListaMenores()){ // Bucle que recorre toda la lista de menores de edad
        if(d.getTipoEntrada() == filtarEntrada){
          System.out.println(d);
        }
      }
    }else{
      for(Cliente d : listClientes.get(0).getListaMayores()){ // Bucle que recorre toda la lista de mayores de edad
        if(d.getTipoEntrada() == filtarEntrada){
          System.out.println(d);
        }
      }
    }
    scann.close();
  }
}
