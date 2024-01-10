package model;

import java.util.stream.IntStream;

/**
 * La classe ShapeData rappresenta i dati associati a una forma geometrica, compresi l'etichetta,
 * il tipo di forma e gli argomenti specifici della forma.
 */
public record ShapeData(String label, String shape, double[] args) {

    /**
     * Costruttore statico per creare un'istanza di ShapeData da una rappresentazione in forma di stringa.
     *
     * @param elements Un array di stringhe contenente gli elementi dell'istanza ShapeData.
     * @return Un'istanza di ShapeData creata dalla rappresentazione in stringa.
     */
    public static ShapeData fromString(String[] elements) {
        return new ShapeData(elements[0],
                elements[1],
                IntStream.range(2, elements.length).mapToDouble(i -> Double.parseDouble(elements[i + 2])).toArray()
        );
    }
}
