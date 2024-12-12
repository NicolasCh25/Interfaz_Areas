package example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz {
    private JFrame frame;
    private JComboBox<String> comboFiguras;
    private JPanel panelInputs;
    private JTextField[] inputs;
    private JLabel resultadoLabel;

    public Interfaz() {
        frame = new JFrame("Calculadora de Áreas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Panel superior para selección de figura
        JPanel panelSuperior = new JPanel();
        comboFiguras = new JComboBox<>(new String[]{
                "Círculo", "Cuadrado", "Triángulo", "Rectángulo", "Trapecio"
        });
        panelSuperior.add(new JLabel("Selecciona una figura: "));
        panelSuperior.add(comboFiguras);
        frame.add(panelSuperior, BorderLayout.NORTH);

        // Panel central para los inputs
        panelInputs = new JPanel();
        panelInputs.setLayout(new GridLayout(4, 2));
        inputs = new JTextField[3];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = new JTextField();
            panelInputs.add(new JLabel("Valor " + (i + 1) + ":"));
            panelInputs.add(inputs[i]);
        }
        frame.add(panelInputs, BorderLayout.CENTER);

        // Panel inferior para el botón de cálculo y resultado
        JPanel panelInferior = new JPanel();
        JButton calcularButton = new JButton("Calcular");
        resultadoLabel = new JLabel("Resultado: ");
        panelInferior.add(calcularButton);
        panelInferior.add(resultadoLabel);
        frame.add(panelInferior, BorderLayout.SOUTH);

        // Acción al cambiar la figura seleccionada
        comboFiguras.addActionListener(e -> actualizarInputs());
        actualizarInputs();

        // Acción al presionar el botón "Calcular"
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularArea();
            }
        });

        frame.setVisible(true);
    }

    private void actualizarInputs() {
        String figura = (String) comboFiguras.getSelectedItem();
        for (JTextField input : inputs) {
            input.setText("");
            input.setVisible(false);
        }
        if ("Círculo".equals(figura)) {
            inputs[0].setVisible(true); // Radio
        } else if ("Cuadrado".equals(figura)) {
            inputs[0].setVisible(true); // Lado
        } else if ("Triángulo".equals(figura)) {
            inputs[0].setVisible(true); // Base
            inputs[1].setVisible(true); // Altura
        } else if ("Rectángulo".equals(figura)) {
            inputs[0].setVisible(true); // Base
            inputs[1].setVisible(true); // Altura
        } else if ("Trapecio".equals(figura)) {
            inputs[0].setVisible(true); // Base mayor
            inputs[1].setVisible(true); // Base menor
            inputs[2].setVisible(true); // Altura
        }
        panelInputs.revalidate();
        panelInputs.repaint();
    }

    private void calcularArea() {
        String figura = (String) comboFiguras.getSelectedItem();
        try {
            double resultado = 0;
            if ("Círculo".equals(figura)) {
                double radio = Double.parseDouble(inputs[0].getText());
                resultado = Math.PI * Math.pow(radio, 2);
            } else if ("Cuadrado".equals(figura)) {
                double lado = Double.parseDouble(inputs[0].getText());
                resultado = Math.pow(lado, 2);
            } else if ("Triángulo".equals(figura)) {
                double base = Double.parseDouble(inputs[0].getText());
                double altura = Double.parseDouble(inputs[1].getText());
                resultado = (base * altura) / 2;
            } else if ("Rectángulo".equals(figura)) {
                double base = Double.parseDouble(inputs[0].getText());
                double altura = Double.parseDouble(inputs[1].getText());
                resultado = base * altura;
            } else if ("Trapecio".equals(figura)) {
                double baseMayor = Double.parseDouble(inputs[0].getText());
                double baseMenor = Double.parseDouble(inputs[1].getText());
                double altura = Double.parseDouble(inputs[2].getText());
                resultado = ((baseMayor + baseMenor) * altura) / 2;
            }
            resultadoLabel.setText(String.format("Resultado: %.2f", resultado));
        } catch (NumberFormatException ex) {
            resultadoLabel.setText("Error: Entrada inválida");
        }
    }
}
