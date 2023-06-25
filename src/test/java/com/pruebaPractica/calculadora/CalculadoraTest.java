package com.PruebaPractica.calculadora;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CalculadoraTest {
    private Calculadora calculadora;

    @Before
    public void setUp() {
        calculadora = new Calculadora();
    }

    @Test
    public void testSumar() {
        int resultado = calculadora.sumar(2, 3);
        Assert.assertEquals(5, resultado);
    }

    @Test
    public void testRestar() {
        int resultado = calculadora.restar(5, 3);
        Assert.assertEquals(2, resultado);
    }

    @Test
    public void testMultiplicar() {
        int resultado = calculadora.multiplicar(4, 3);
        Assert.assertEquals(12, resultado);
    }

    @Test
    public void testDividir() {
        int resultado = calculadora.dividir(10, 2);
        Assert.assertEquals(5, resultado);
    }

    @Test(expected = ArithmeticException.class)
    public void testDividirPorCero() {
        calculadora.dividir(10, 0);
    }
}
