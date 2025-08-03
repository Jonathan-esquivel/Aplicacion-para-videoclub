## 🏗️ Diagrama de Clases del Sistema

```mermaid
classDiagram
    %% Clase Abstracta Base
    class Producto {
        <<abstract>>
        -String nombre
        -double costoRenta
        -int diasRenta
        -boolean rentado
        +getNombre() String
        +setNombre(String) void
        +getCostoRenta() double
        +setCostoRenta(double) void
        +isRentado() boolean
        +setRentado(boolean) void
        +toString() String
        <<abstract>> +getInfoEspecifica() String
    }

    %% Subclases
    class Pelicula {
        -String genero
        -int añoProduccion
        +getGenero() String
        +setGenero(String) void
        +getAñoProduccion() int
        +setAñoProduccion(int) void
        +getInfoEspecifica() String
    }

    class Videojuego {
        -String estilo
        -String plataforma
        +getEstilo() String
        +setEstilo(String) void
        +getPlataforma() String
        +setPlataforma(String) void
        +getInfoEspecifica() String
    }

    %% Relaciones
    Producto <|-- Pelicula
    Producto <|-- Videojuego
```Diagrama de clases
