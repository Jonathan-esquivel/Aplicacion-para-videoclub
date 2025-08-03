import java.util.Scanner;

// Clase base abstracta Producto
abstract class Producto {
    private String nombre;
    private double costoRenta;
    private int diasRenta;
    private boolean rentado;

    public Producto() {
        this("", 0.0, 0, false);
    }

    public Producto(String nombre, double costoRenta, int diasRenta, boolean rentado) {
        this.nombre = nombre;
        this.costoRenta = costoRenta;
        this.diasRenta = diasRenta;
        this.rentado = rentado;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCostoRenta() {
        return costoRenta;
    }

    public void setCostoRenta(double costoRenta) {
        this.costoRenta = costoRenta;
    }

    public int getDiasRenta() {
        return diasRenta;
    }

    public void setDiasRenta(int diasRenta) {
        this.diasRenta = diasRenta;
    }

    public boolean isRentado() {
        return rentado;
    }

    public void setRentado(boolean rentado) {
        this.rentado = rentado;
    }

    // Método abstracto para mostrar información específica
    public abstract String getInfoEspecifica();
    
    @Override
    public String toString() {
        return "Nombre: " + nombre + 
               ", Costo renta: $" + String.format("%.2f", costoRenta) + 
               ", Días: " + diasRenta + 
               ", Rentado: " + (rentado ? "Sí" : "No") +
               ", " + getInfoEspecifica();
    }
}

// Clase Pelicula
class Pelicula extends Producto {
    private String genero;
    private int año;
    private static final String[] GENEROS_VALIDOS = {"acción", "fantasía", "drama", "comedia", "aventura"};

    public Pelicula() {
        this("", 0.0, 0, false, "", 0);
    }

    public Pelicula(String nombre, double costoRenta, int diasRenta, boolean rentado, 
                   String genero, int año) {
        super(nombre, costoRenta, diasRenta, rentado);
        setGenero(genero);
        this.año = año;
    }

    // Getters y Setters con validación
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        for (String g : GENEROS_VALIDOS) {
            if (g.equalsIgnoreCase(genero)) {
                this.genero = genero.toLowerCase();
                return;
            }
        }
        this.genero = "desconocido";
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    @Override
    public String getInfoEspecifica() {
        return "Película - Género: " + genero + ", Año: " + año;
    }
}

// Clase Videojuego
class Videojuego extends Producto {
    private String estilo;
    private String plataforma;
    private static final String[] ESTILOS_VALIDOS = {"acción", "deportes", "aventuras"};
    private static final String[] PLATAFORMAS_VALIDAS = {"xbox one", "playstation", "wii"};

    public Videojuego() {
        this("", 0.0, 0, false, "", "");
    }

    public Videojuego(String nombre, double costoRenta, int diasRenta, boolean rentado, 
                      String estilo, String plataforma) {
        super(nombre, costoRenta, diasRenta, rentado);
        setEstilo(estilo);
        setPlataforma(plataforma);
    }

    // Getters y Setters con validación
    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        for (String e : ESTILOS_VALIDOS) {
            if (e.equalsIgnoreCase(estilo)) {
                this.estilo = estilo.toLowerCase();
                return;
            }
        }
        this.estilo = "desconocido";
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        for (String p : PLATAFORMAS_VALIDAS) {
            if (p.equalsIgnoreCase(plataforma)) {
                this.plataforma = plataforma.toLowerCase();
                return;
            }
        }
        this.plataforma = "desconocida";
    }

    @Override
    public String getInfoEspecifica() {
        return "Videojuego - Estilo: " + estilo + ", Plataforma: " + plataforma;
    }
}

// Clase principal mejorada
public class VideoclubAppMejorada {
    private static Scanner scanner = new Scanner(System.in);
    private static Pelicula[] peliculas;
    private static Videojuego[] videojuegos;

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║  VIDEOCLUB GAMES & VIDEOS - SISTEMA║");
        System.out.println("╚════════════════════════════════════╝");
        
        registrarProductos();
        mostrarMenu();
        
        scanner.close();
    }

    private static void registrarProductos() {
        System.out.println("\n=== REGISTRO DE PRODUCTOS ===");
        
        // Registrar películas
        System.out.print("Ingrese cuántas películas desea registrar: ");
        int numPeliculas = leerEnteroPositivo();
        peliculas = new Pelicula[numPeliculas];
        
        for (int i = 0; i < numPeliculas; i++) {
            System.out.println("\nRegistro de Película #" + (i + 1));
            peliculas[i] = crearPelicula();
        }
        
        // Registrar videojuegos
        System.out.print("\nIngrese cuántos videojuegos desea registrar: ");
        int numVideojuegos = leerEnteroPositivo();
        videojuegos = new Videojuego[numVideojuegos];
        
        for (int i = 0; i < numVideojuegos; i++) {
            System.out.println("\nRegistro de Videojuego #" + (i + 1));
            videojuegos[i] = crearVideojuego();
        }
    }

    private static Pelicula crearPelicula() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Costo de renta: $");
        double costo = leerDoublePositivo();
        
        System.out.print("Días de renta: ");
        int dias = leerEnteroPositivo();
        
        System.out.print("¿Está rentado? (S/N): ");
        boolean rentado = leerSiNo();
        
        System.out.print("Género (acción, fantasía, drama, comedia, aventura): ");
        String genero = scanner.nextLine();
        
        System.out.print("Año de producción: ");
        int año = leerEnteroPositivo();
        
        return new Pelicula(nombre, costo, dias, rentado, genero, año);
    }

    private static Videojuego crearVideojuego() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Costo de renta: $");
        double costo = leerDoublePositivo();
        
        System.out.print("Días de renta: ");
        int dias = leerEnteroPositivo();
        
        System.out.print("¿Está rentado? (S/N): ");
        boolean rentado = leerSiNo();
        
        System.out.print("Estilo (acción, deportes, aventuras): ");
        String estilo = scanner.nextLine();
        
        System.out.print("Plataforma (Xbox One, Playstation, Wii): ");
        String plataforma = scanner.nextLine();
        
        return new Videojuego(nombre, costo, dias, rentado, estilo, plataforma);
    }

    private static void mostrarMenu() {
        char opcion;
        do {
            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║            MENÚ PRINCIPAL          ║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("║ T/t: Mostrar todos los productos   ║");
            System.out.println("║ P/p: Mostrar solo películas        ║");
            System.out.println("║ V/v: Mostrar solo videojuegos      ║");
            System.out.println("║ S/s: Buscar película por nombre    ║");
            System.out.println("║ J/j: Buscar videojuego por nombre  ║");
            System.out.println("║ C/c: Películas rentadas            ║");
            System.out.println("║ X/x: Videojuegos para Xbox One     ║");
            System.out.println("║ A/a: Productos por género/estilo   ║");
            System.out.println("║ R/r: Productos rentados            ║");
            System.out.println("║ D/d: Productos disponibles         ║");
            System.out.println("║ U/u: Salir del sistema             ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextLine().toLowerCase().charAt(0);
            
            switch (opcion) {
                case 't':
                    mostrarTodosProductos();
                    break;
                case 'p':
                    mostrarPeliculas();
                    break;
                case 'v':
                    mostrarVideojuegos();
                    break;
                case 's':
                    buscarPelicula();
                    break;
                case 'j':
                    buscarVideojuego();
                    break;
                case 'c':
                    contarPeliculasRentadas();
                    break;
                case 'x':
                    contarVideojuegosXboxOne();
                    break;
                case 'a':
                    filtrarPorCategoria();
                    break;
                case 'r':
                    mostrarProductosRentados();
                    break;
                case 'd':
                    mostrarProductosDisponibles();
                    break;
                case 'u':
                    System.out.println("\nGracias por usar el sistema. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente nuevamente.");
            }
            
            if (opcion != 'u') {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcion != 'u');
    }

    // Métodos para las opciones del menú
    private static void mostrarTodosProductos() {
        System.out.println("\n=== TODOS LOS PRODUCTOS ===");
        System.out.println("--- PELÍCULAS (" + peliculas.length + ") ---");
        for (Pelicula p : peliculas) {
            System.out.println(p);
        }
        System.out.println("\n--- VIDEOJUEGOS (" + videojuegos.length + ") ---");
        for (Videojuego v : videojuegos) {
            System.out.println(v);
        }
    }

    private static void mostrarPeliculas() {
        System.out.println("\n=== PELÍCULAS REGISTRADAS ===");
        for (Pelicula p : peliculas) {
            System.out.println(p);
        }
    }

    private static void mostrarVideojuegos() {
        System.out.println("\n=== VIDEOJUEGOS REGISTRADOS ===");
        for (Videojuego v : videojuegos) {
            System.out.println(v);
        }
    }

    private static void buscarPelicula() {
        System.out.print("\nIngrese el nombre de la película a buscar: ");
        String nombre = scanner.nextLine();
        boolean encontrada = false;
        
        for (Pelicula p : peliculas) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("\nINFORMACIÓN DE LA PELÍCULA:");
                System.out.println(p);
                encontrada = true;
                break;
            }
        }
        
        if (!encontrada) {
            System.out.println("No se encontró la película '" + nombre + "'");
        }
    }

    private static void buscarVideojuego() {
        System.out.print("\nIngrese el nombre del videojuego a buscar: ");
        String nombre = scanner.nextLine();
        boolean encontrado = false;
        
        for (Videojuego v : videojuegos) {
            if (v.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("\nINFORMACIÓN DEL VIDEOJUEGO:");
                System.out.println(v);
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            System.out.println("No se encontró el videojuego '" + nombre + "'");
        }
    }

    private static void contarPeliculasRentadas() {
        int contador = 0;
        for (Pelicula p : peliculas) {
            if (p.isRentado()) contador++;
        }
        System.out.println("\nTotal de películas rentadas: " + contador + "/" + peliculas.length);
    }

    private static void contarVideojuegosXboxOne() {
        int contador = 0;
        for (Videojuego v : videojuegos) {
            if (v.getPlataforma().equalsIgnoreCase("xbox one")) contador++;
        }
        System.out.println("\nTotal de videojuegos para Xbox One: " + contador + "/" + videojuegos.length);
    }

    private static void filtrarPorCategoria() {
        System.out.println("\nFiltrar por:");
        System.out.println("1. Género de película");
        System.out.println("2. Estilo de videojuego");
        System.out.print("Seleccione opción (1-2): ");
        int opcion = leerEnteroEnRango(1, 2);
        
        if (opcion == 1) {
            System.out.print("Ingrese género a filtrar: ");
            String genero = scanner.nextLine().toLowerCase();
            System.out.println("\nPelículas de género '" + genero + "':");
            
            for (Pelicula p : peliculas) {
                if (p.getGenero().equals(genero)) {
                    System.out.println(p);
                }
            }
        } else {
            System.out.print("Ingrese estilo a filtrar: ");
            String estilo = scanner.nextLine().toLowerCase();
            System.out.println("\nVideojuegos de estilo '" + estilo + "':");
            
            for (Videojuego v : videojuegos) {
                if (v.getEstilo().equals(estilo)) {
                    System.out.println(v);
                }
            }
        }
    }

    private static void mostrarProductosRentados() {
        System.out.println("\n=== PRODUCTOS RENTADOS ===");
        System.out.println("--- PELÍCULAS ---");
        for (Pelicula p : peliculas) {
            if (p.isRentado()) System.out.println(p);
        }
        System.out.println("\n--- VIDEOJUEGOS ---");
        for (Videojuego v : videojuegos) {
            if (v.isRentado()) System.out.println(v);
        }
    }

    private static void mostrarProductosDisponibles() {
        System.out.println("\n=== PRODUCTOS DISPONIBLES ===");
        System.out.println("--- PELÍCULAS ---");
        for (Pelicula p : peliculas) {
            if (!p.isRentado()) System.out.println(p);
        }
        System.out.println("\n--- VIDEOJUEGOS ---");
        for (Videojuego v : videojuegos) {
            if (!v.isRentado()) System.out.println(v);
        }
    }

    // Métodos auxiliares para validación de entrada
    private static int leerEnteroPositivo() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= 0) return valor;
                System.out.print("Ingrese un valor positivo: ");
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    private static int leerEnteroEnRango(int min, int max) {
        while (true) {
            int valor = leerEnteroPositivo();
            if (valor >= min && valor <= max) return valor;
            System.out.printf("Ingrese un valor entre %d y %d: ", min, max);
        }
    }

    private static double leerDoublePositivo() {
        while (true) {
            try {
                double valor = Double.parseDouble(scanner.nextLine());
                if (valor >= 0) return valor;
                System.out.print("Ingrese un valor positivo: ");
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número válido: ");
            }
        }
    }

    private static boolean leerSiNo() {
        while (true) {
            String respuesta = scanner.nextLine().toLowerCase();
            if (respuesta.equals("s")) return true;
            if (respuesta.equals("n")) return false;
            System.out.print("Ingrese S o N: ");
        }
    }
}