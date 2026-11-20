# Taller 03 — Spooky Season

## Descripción del proyecto
Simulación en consola desarrollada en Java utilizando Programación Orientada a Objetos (POO). Permite a distintos jugadores transformarse en monstruos (Zombi, Vampiro, Momia u Hombre Lobo) para competir en 4 minijuegos, aplicando herencia, polimorfismo, interfaces de habilidades, manejo de colecciones (`ArrayList` y `LinkedList`) y persistencia de récords en archivos de texto.

## Integrantes
- **Maria Sofia Escalona Ramos** - RUT: 26.965.672-7 - GitHub: `@sofiphobic`


## Estructura del proyecto
El código fuente se encuentra dentro del paquete `taller03` en la carpeta `src/`:

- **Clases principales (Arquitectura Main / Sistema):**
  - `Main.java`: Manejo de consola, menús, lectura de archivos y guardado en `Registros.txt`.
  - `Sistema.java`: Lógica de negocio, validaciones, gestión de sesiones, cálculo de puntajes y ordenamiento manual del ranking.
- **Modelo y jerarquía de monstruos:**
  - `Monstruo.java` (clase abstracta) y sus subclases `Zombi.java`, `Vampiro.java`, `Momia.java`, `HombreLobo.java`.
  - `Jugador.java` y `RegistroSesion.java`.
- **Interfaces de habilidades:**
  - `Mordedor.java`, `Volador.java`, `Regenerable.java`, `Furioso.java`.
- **Minijuegos:**
  - `Minijuego.java` (interfaz) y sus implementaciones `CarreraCementerio.java`, `CazaCalabazas.java`, `LanzamientoLapidas.java`, `SobrevivirAmanecer.java`.

## Instrucciones de ejecución y testeo

### 1. Clonación del repositorio
```bash
git clone [https://github.com/sofiphobic/TALLER-3-POO.git](https://github.com/TU_USUARIO/TU_REPOSITORIO.git)