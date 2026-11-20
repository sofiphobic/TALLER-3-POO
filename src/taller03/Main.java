package taller03;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner menuScanner = new Scanner(System.in);
        Sistema miSistema = new Sistema();
        
        cargarArchivosTxt(miSistema);
        
        if (miSistema.getMonstruos().size() == 0) {
            System.out.println("No hay monstruos validos. Cerrando el programa...");
            return;
        }

        while (true) {
            System.out.println("=========================================");
            System.out.println("             SPOOKY SEASON");
            System.out.println("=========================================");
            
            String nombreUsuario;
            boolean loginExitoso = false;
            
            do {
                System.out.print("Ingrese su nombre de usuario: ");
                nombreUsuario = menuScanner.nextLine();
                loginExitoso = miSistema.iniciarSesion(nombreUsuario);
                
                if (loginExitoso == false) {
                    System.out.println("[!] El nombre no puede estar vacío ni contener punto y coma (;).");
                }
            } while (loginExitoso == false);

            if (miSistema.getEsNuevo() == true) {
                System.out.println("¡Bienvenido, " + nombreUsuario + "! Es tu primera vez jugando.");
            } else {
                System.out.println("¡Bienvenido de vuelta, " + nombreUsuario + "! Tu récord actual es de " + miSistema.getJugadorActual().getPuntajeMaximo() + " puntos.");
            }

            int option;
            do {
                System.out.println("--- MENÚ PRINCIPAL ---");
                System.out.println("1) Jugar un minijuego");
                System.out.println("2) Ver catalogo de monstruos");
                System.out.println("3) Ver historial de la sesion");
                System.out.println("4) Ver ranking de jugadores");
                System.out.println("5) Terminar sesion");
                System.out.println("6) Salir");
                System.out.print("Seleccione: ");
                
                try {
                    option = Integer.parseInt(menuScanner.nextLine());
                } catch (Exception e) {
                    System.out.println("[!] Debe ingresar un numero.");
                    option = 0;
                    continue;
                }

                if (option == 1) {
                    menuJugar(menuScanner, miSistema);
                } else if (option == 2) {
                    verCat(miSistema);
                } else if (option == 3) {
                    verHis(miSistema);
                } else if (option == 4) {
                    verRan(miSistema);
                } else if (option == 5) {
                    cerrarYGuardar(miSistema);
                    break; // recuerda que aca se rompe el ciclo y vuelve a preguntar por el login
                } else if (option == 6) {
                    cerrarYGuardar(miSistema);
                    System.out.println("Chao!!");
                    System.exit(0);
                } else {
                    System.out.println("Opcion incorrecta");
                }
            } while (option != 5 && option != 6);
        }
    }

    private static void cargarArchivosTxt(Sistema sis) {
        System.out.println("Cargando archivos...");
        try {
            File fMonstruos = new File("Monstruos.txt");
            Scanner sMonstruos = new Scanner(fMonstruos);
            int lineaCount = 1;
            int agregados = 0;
            
            while (sMonstruos.hasNextLine()) {
                String linea = sMonstruos.nextLine();
                if (linea.length() > 0) {
                    String[] partes = linea.split(";");
                    boolean exito = sis.agregarMonstruo(partes);
                    if (exito == true) {
                        agregados++;
                    } else {
                        System.out.println(" > Linea " + lineaCount + " descartada.");
                    }
                }
                lineaCount++;
            }
            sMonstruos.close();
            System.out.println(" > " + agregados + " monstruos cargados.");
        } catch (Exception e) {
            System.out.println(" > Archivo Monstruos.txt no encontrado.");
        }

        try {
            File fRegistros = new File("Registros.txt");
            Scanner sRegistros = new Scanner(fRegistros);
            int agregadosJ = 0;
            
            while (sRegistros.hasNextLine()) {
                String linea = sRegistros.nextLine();
                if (linea.length() > 0) {
                    String[] partes = linea.split(";");
                    if (partes.length == 2) {
                        int puntos = Integer.parseInt(partes[1]);
                        sis.agregarJugador(partes[0], puntos);
                        agregadosJ++;
                    }
                }
            }
            sRegistros.close();
            System.out.println(" > " + agregadosJ + " jugadores cargados.");
        } catch (Exception e) {
            System.out.println(" > Archivo Registros.txt no existe, se creara al salir.");
        }
    }

    private static void menuJugar(Scanner scanner, Sistema sis) {
        int optJuego;
        do {
            System.out.println("--- MINIJUEGOS ---");
            Minijuego[] arreglo = sis.getMinijuegos();
            boolean[] jugados = sis.getJugados();

            for (int i = 0; i < 4; i++) {
                String estado = "[PENDIENTE]";
                if (jugados[i] == true) {
                    estado = "[JUGADO]";
                }
                System.out.println((i+1) + ") " + arreglo[i].getNombre() + " " + estado);
            }
            System.out.println("0) Volver");
            System.out.print("Seleccione: ");
            
            try {
                optJuego = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("[!] Debe ingresar un numero.");
                optJuego = -1;
                continue;
            }

            if (optJuego == 0) {
                return;
            } else if (optJuego >= 1 && optJuego <= 4) {
                if (jugados[optJuego-1] == true) {
                    System.out.println("[!] Ya jugaste esto en esta sesion.");
                } else {
                    ejecutarMinijuego(scanner, sis, optJuego - 1);
                    return;
                }
            } else {
                System.out.println("Opcion fuera de rango");
            }
        } while (optJuego != 0);
    }

    private static void ejecutarMinijuego(Scanner scanner, Sistema sis, int indiceJuego) {
        Minijuego elJuego = sis.getMinijuegos()[indiceJuego];
        System.out.println(" " + elJuego.getNombre() + ": " + elJuego.getDescripcion());
        
        ArrayList<Monstruo> listaDisp = new ArrayList<>();
        ArrayList<Monstruo> todos = sis.getMonstruos();
        
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getUsado() == false) {
                listaDisp.add(todos.get(i));
            }
        }

        if (listaDisp.size() == 0) {
            System.out.println("[!] No te quedan monstruos disponibles.");
            return;
        }

        System.out.println(" Elige tu monstruo:");
        for (int i = 0; i < listaDisp.size(); i++) {
            Monstruo m = listaDisp.get(i);
            System.out.println((i+1) + ") " + m.getTipo() + " | Vida " + m.vida + " | Vel " + m.velocidad + " | Fue " + m.fuerza + " | Res " + m.resistencia + " " + m.getAtributosPropios());
        }
        System.out.println("0) Volver");

        int optMons = -1;
        while (optMons < 0 || optMons > listaDisp.size()) {
            System.out.print("Seleccione: ");
            try {
                optMons = Integer.parseInt(scanner.nextLine());
            } catch(Exception e) {
                System.out.println("[!] Debe ingresar un numero.");
            }
        }

        if (optMons == 0) return;
        
        Monstruo mElegido = listaDisp.get(optMons - 1);
        System.out.println("Te comes un dulce...");
        double potencialCalculado = elJuego.calcularPotencial(mElegido);
        System.out.println("Potencial: " + potencialCalculado);

        ArrayList<String> nombresHabilidades = new ArrayList<>();
        if (mElegido instanceof Mordedor) nombresHabilidades.add("Morder");
        if (mElegido instanceof Volador) nombresHabilidades.add("Volar");
        if (mElegido instanceof Regenerable) nombresHabilidades.add("Regenerarse");
        if (mElegido instanceof Furioso) nombresHabilidades.add("Enfurecerse");

        System.out.println("Habilidades:");
        for (int i = 0; i < nombresHabilidades.size(); i++) {
            System.out.println((i+1) + ") " + nombresHabilidades.get(i));
        }
        System.out.println("0) No usar habilidad");

        int optHab = -1;
        while (optHab < 0 || optHab > nombresHabilidades.size()) {
            System.out.print("Seleccione: ");
            try {
                optHab = Integer.parseInt(scanner.nextLine());
            } catch(Exception e) {
                System.out.println("[!] Debe ingresar un numero.");
            }
        }

        String habElegidaStr = "Ninguna";
        boolean fueEfectiva = false;

        if (optHab > 0) {
            habElegidaStr = nombresHabilidades.get(optHab - 1);
            
            if (habElegidaStr.equals("Morder")) System.out.println(((Mordedor)mElegido).morder());
            if (habElegidaStr.equals("Volar")) System.out.println(((Volador)mElegido).volar());
            if (habElegidaStr.equals("Regenerarse")) System.out.println(((Regenerable)mElegido).regenerarse());
            if (habElegidaStr.equals("Enfurecerse")) System.out.println(((Furioso)mElegido).enfurecerse());

            String premioJuego = elJuego.getHabilidadPremiada();
            
            if (habElegidaStr.equals("Morder") && premioJuego.equals("Mordedor")) fueEfectiva = true;
            else if (habElegidaStr.equals("Volar") && premioJuego.equals("Volador")) fueEfectiva = true;
            else if (habElegidaStr.equals("Regenerarse") && premioJuego.equals("Regenerable")) fueEfectiva = true;
            else if (habElegidaStr.equals("Enfurecerse") && premioJuego.equals("Furioso")) fueEfectiva = true;

            if (fueEfectiva == true) {
                System.out.println("Habilidad efectiva! x1.5");
            } else {
                System.out.println("La habilidad no tuvo efecto.");
            }
        }

        sis.guardarJuego(indiceJuego, mElegido, habElegidaStr, potencialCalculado, fueEfectiva);
        System.out.println("Completado con exito!");
    }

    private static void verCat(Sistema sis) {
        System.out.println("--- CATALOGO ---");
        ArrayList<Monstruo> todos = sis.getMonstruos();
        for (int i = 0; i < todos.size(); i++) {
            Monstruo m = todos.get(i);
            String textoUsado = "[Disponible]";
            if (m.getUsado() == true) {
                textoUsado = "[Usado]";
            }
            System.out.println(textoUsado + " " + m.getTipo() + " | V:" + m.vida + " Vel:" + m.velocidad + " F:" + m.fuerza + " R:" + m.resistencia + " " + m.getAtributosPropios());
        }
    }

    private static void verHis(Sistema sis) {
        System.out.println("--- HISTORIAL ---");
        LinkedList<RegistroSesion> historia = sis.getHistorial();
        if (historia.size() == 0) {
            System.out.println("Aun no hay juegos completados.");
        } else {
            for (int i = 0; i < historia.size(); i++) {
                RegistroSesion r = historia.get(i);
                System.out.println((i+1) + ". " + r.nombreMinijuego + " | " + r.tipoMonstruo + " | " + r.habilidadUsada + " | " + r.puntajeObtenido + " pts");
            }
            System.out.println("Puntaje acumulado: " + sis.getTotalAcumulado() + " pts");
        }
    }

    private static void verRan(Sistema sis) {
        sis.ordenarRanking();
        System.out.println("--- RANKING ---");
        ArrayList<Jugador> jug = sis.getJugadores();
        for (int i = 0; i < jug.size(); i++) {
            System.out.println((i+1) + ". " + jug.get(i).getNombre() + " - " + jug.get(i).getPuntajeMaximo());
        }
    }

    private static void cerrarYGuardar(Sistema sis) {
        boolean supero = sis.cerrarSesion();
        
        if (sis.getHistorial().size() == 0) {
            System.out.println("No jugaste nada, no se guarda.");
        } else {
            System.out.println("Puntaje final de la sesion: " + sis.getTotalAcumulado());
            if (supero == true) {
                System.out.println("Felicidades!! Nuevo record.");
            } else {
                System.out.println("No superaste tu record.");
            }
            
            try {
                BufferedWriter escritor = new BufferedWriter(new FileWriter("Registros.txt"));
                ArrayList<Jugador> jugadoresAct = sis.getJugadores();
                for (int i = 0; i < jugadoresAct.size(); i++) {
                    escritor.write(jugadoresAct.get(i).getNombre() + ";" + jugadoresAct.get(i).getPuntajeMaximo());
                    escritor.newLine();
                }
                escritor.close();
                System.out.println("Registros guardados con exito.");
            } catch (Exception e) {
                System.out.println("Error al guardar archivo");
            }
        }
    }
}