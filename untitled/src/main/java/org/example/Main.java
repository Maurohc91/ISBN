package org.example;

import java.io.CharArrayReader;
import java.nio.channels.Pipe;
import java.sql.SQLOutput;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.println("Introduce el ISBN de tu libro: ");
        String ISBN = entrada.nextLine();

        int tamanyo = ISBN.length();

        while (tamanyo != 10) {
            System.out.println("Por favor,introduce un ISBN válido");

            ISBN = entrada.nextLine();
            tamanyo = ISBN.length();
        }

        int interrogante = ISBN.indexOf('?');
        String modo = " ";

        if (interrogante != -1) {
            modo = "interrogacion";
        } else {
            modo = "normal";
        }

        switch (modo) {
            case "interrogacion":
                int suma = 0;

                for (int i = 0; i < 10; i++) {
                    if (i == interrogante) {
                        continue;
                    }
                    char digito = ISBN.charAt(i);
                    int valor = 0;

                    switch (digito) {
                        case 'X':
                            valor = 10;
                            break;
                        default:
                            if (Character.isDigit(digito)) {
                                valor = Character.getNumericValue(digito);
                            } else {
                                System.out.println("ISBN inválido.");
                                break;
                            }
                    }
                    for (int j = 10; j > 0; j--) {
                        if (j == 10 - i) {
                            suma += valor * j;
                            break;
                        }
                    }
                }
                int resto = suma % 11;
                int numeroOculto = 0;
                if (resto == 0) {
                    numeroOculto = 0;
                } else {
                    numeroOculto = 11 - resto;
                }

                switch (numeroOculto) {
                    case 10:
                        if (interrogante == 9) {
                            System.out.println("El digito que falta es: X");
                        } else {
                            System.out.println("El digito que falta es: " + numeroOculto);
                        }
                        break;
                    default:
                        System.out.println("El digito que falta es: " + numeroOculto);
                        break;

                }
                break;
            case "normal":
                suma = 0;
                boolean isbnValido = true;

                for (int i = 0; i < 10; i++) {
                    char digito = ISBN.charAt(i);
                    int valor = 0;

                    switch (i) {
                        case 9:
                            if (digito == 'X') {
                                valor = 10;
                            } else if (Character.isDigit(digito)) {
                                valor = Character.getNumericValue(digito);
                            } else {
                                System.out.println("ISBN inválido.");
                                isbnValido = false;
                            }
                            break;
                        default:
                            if (Character.isDigit(digito)) {
                                valor = Character.getNumericValue(digito);
                            } else {
                                System.out.println("ISBN inválido.");
                                isbnValido = false;
                            }
                            break;
                    }


                    for (int j = 10; j > 0; j--) {
                        if (j == 10 - i) {
                            suma += valor * j;
                            break;
                        }
                    }
                }
                if (isbnValido == true) {

                    if (suma % 11 == 0) {
                        System.out.println("El ISBN es válido.");

                    } else {
                        System.out.println("El ISBN es inválido.");
                    }
                }
                break;
            default:
                System.out.println("Modo no reconocido.");
                break;
        }
    }
}
