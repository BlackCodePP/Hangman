package juegoAhorcadoConsola;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author BlackCodePP
 */

/**
 * Ejecuta el juego del ahorcado
 */
public class Ahorcado {

    private final String palabraElegida = selectRandom();
    private final char[] palabraGuiones = palabraGuion(palabraElegida);
    private int vidas = 10;

    /**
     * Este es le método principal del programa del ahorcado, el cual ejecuta el resto de métodos
     */
    protected void play() {

        do {
            boolean letraAcertada = false;
            System.out.println(palabraGuiones);
            char letra = pedirLetra();

            letraAcertada = comprobarEntrada(palabraElegida, letra, palabraGuiones, letraAcertada);

            if (!letraAcertada) {
                vidas = restarVidas(letra, vidas);
            } else {
                boolean finJuego = comprobarVictoria(palabraGuiones);
                if (finJuego) {
                    victoria(palabraElegida);
                    break;
                }
            }

        } while (vidas > 0);

        comprobarDerrota(vidas, palabraElegida);

    }

    /**
     * Selecciona la palabra del ahorcado de forma aleatoria del string palabras
     *
     * @return La palabra seleccionada
     */
    private static String selectRandom() {

        String[] palabras = {"futbol", "baloncesto", "golf", "tenis", "judo", "ajedrez", "badminton", "balonmano", "beisbol", "boxeo", "ciclismo", "hockey", "curling", "esqui", "atletismo", "esgrima", "voleibol", "padel", "natacion", "karate"};
        Random r = new Random();
        int n = r.nextInt(palabras.length);

        return palabras[n];
    }

    /**
     * Transforma cada carácter de la palabra elegida en un guion
     *
     * @param palabraElegida Es la palabra seleccionada del ahorcado con la que jugamos
     * @return Un guion por cada carácter que contenga la palabra seleccionada
     */
    private char[] palabraGuion(String palabraElegida) {

        int numLetrasPalabraElegida = palabraElegida.length();
        char[] guiones = new char[numLetrasPalabraElegida];

        for (int i = 0; i < guiones.length; i++) {
            guiones[i] = '-';
        }
        return guiones;
    }

    /**
     * Solicita una letra al usuario por teclado y comprueba que la entrada se realiza correctamente
     *
     * @return La letra introducida por el usuario
     */
    private static char pedirLetra() {

        Scanner lector = new Scanner(System.in);
        char letra;

        do {
            System.out.println("Introduce una letra");
            String input = lector.next();
            if (input.length() == 1) {
                letra = input.charAt(0);
                if (Character.isLetter(letra)) {
                    break;
                } else {
                    System.out.println(letra + " No es un caracter valido");
                }
            } else {
                System.out.println("Entrada invalida. Introduce una sola letra");
            }
        } while (true);

        return letra;
    }

    /**
     * Comprueba si la letra introducida está en alguna posición de la palabra y si es así cambia el guion de su posición por la letra
     *
     * @param palabraElegida Es la palabra seleccionada del ahorcado con la que jugamos
     * @param letra La letra introducida por el usuario
     * @param palabraGuiones La palabra seleccionada transformada en guiones
     * @param letraAcertada El resultado de la comprobación de la letra introducida por el usuario
     * @return La letra acertada en su posición de la palabra correspondiente
     */
    private static boolean comprobarEntrada(String palabraElegida, char letra, char[] palabraGuiones, boolean letraAcertada) {
        for (int i = 0; i < palabraElegida.length(); i++) {

            if (letra == palabraElegida.charAt(i)) {
                palabraGuiones[i] = letra;
                letraAcertada = true;

            }
        }
        return letraAcertada;
    }

    /**
     * Muestra que la palabra no contiene la letra introducida y resta 1 vida para luego mostrar el total de vidas que quedan
     *
     * @param letra La letra introducida por el usuario
     * @param vidas La cantidad de vidas que tiene el usuario
     * @return La cantidad de vidas que le quedan al usuario después de quitarle una
     */
    private int restarVidas(char letra, int vidas) {
        System.out.println("La letra " + letra + " no coincide");
        vidas--;
        System.out.println("Te quedan " + vidas + " intentos");
        return vidas;
    }

    /**
     * Muestra un mensaje de victoria y la palabra elegida
     *
     * @param palabraElegida Es la palabra seleccionada del ahorcado con la que jugamos
     */
    private static void victoria(String palabraElegida) {
        System.out.println("VICTORIA");
        System.out.println("La palabra era: " + palabraElegida);
    }

    /**
     * Comprueba si en la palabra aún hay guiones para saber si se ha finalizado o no la partida
     *
     * @param comprobacion Es el estado actual de la palabra en la partida, en base a los aciertos del usuario
     * @return El resultado de la comprobación
     */
    private boolean comprobarVictoria(char[] comprobacion) {
        for (char l : comprobacion) {
            if (l == '-') {
                return false;
            }

        }
        return true;
    }

    /**
     * Comprueba si el usuario se ha quedado sin vidas para finalizar el juego
     *
     * @param vidas La cantidad de vidas que tiene el usuario
     * @param palabraElegida Es la palabra seleccionada del ahorcado con la que jugamos
     */
    private void comprobarDerrota(int vidas, String palabraElegida) {
        if (vidas <= 0) {
            System.out.println("La palabra era: " + palabraElegida);
            System.out.println("GAME OVER");
            System.exit(0);
        }

    }

}
