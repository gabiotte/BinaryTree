import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private Tree tree;  // A árvore será genérica, tipo definido pelo usuário
    private TreePanel treePanel = new TreePanel();  // Painel personalizado para desenhar a árvore
    private JComboBox<String> typeSelector;  // Combobox para escolher o tipo de dado

    public Main() {
        setTitle("Binary Tree Visualizer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Opções de tipos de dados: Integer, String, Double
        String[] dataTypes = {"Integer", "String", "Double"};
        typeSelector = new JComboBox<>(dataTypes);
        JButton setTypeButton = new JButton("Set Type");

        // Campo de texto para inserir o valor
        JTextField inputField = new JTextField(10);
        JButton addButton = new JButton("Add");

        // Campo de texto para remover o valor
        JTextField removeField = new JTextField(10);
        JButton removeButton = new JButton("Remove");

        // Listener do botão de adicionar
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) typeSelector.getSelectedItem();
                try {
                    if (tree == null) {
                        JOptionPane.showMessageDialog(null, "Please select a type before adding elements.");
                        return;
                    }

                    if (selectedType.equals("Integer")) {
                        int value = Integer.parseInt(inputField.getText());
                        tree.add(value);
                    } else if (selectedType.equals("Double")) {
                        double value = Double.parseDouble(inputField.getText());
                        tree.add(value);
                    } else if (selectedType.equals("String")) {
                        String value = inputField.getText();
                        tree.add(value);
                    }
                    treePanel.repaint();  // Re-desenha a árvore
                    inputField.setText("");  // Limpa o campo de texto
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid value for the selected type.");
                }
            }
        });

        // Listener do botão de remover
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) typeSelector.getSelectedItem();
                try {
                    if (tree == null) {
                        JOptionPane.showMessageDialog(null, "Please select a type and add elements first.");
                        return;
                    }

                    if (selectedType.equals("Integer")) {
                        int value = Integer.parseInt(removeField.getText());
                        tree.remove(value);
                    } else if (selectedType.equals("Double")) {
                        double value = Double.parseDouble(removeField.getText());
                        tree.remove(value);
                    } else if (selectedType.equals("String")) {
                        String value = removeField.getText();
                        tree.remove(value);
                    }
                    treePanel.repaint();  // Re-desenha a árvore
                    removeField.setText("");  // Limpa o campo de texto
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid value for the selected type.");
                }
            }
        });

        // Listener do botão de definir o tipo de dado
        setTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) typeSelector.getSelectedItem();
                switch (selectedType) {
                    case "Integer":
                        tree = new Tree<Integer>();
                        break;
                    case "Double":
                        tree = new Tree<Double>();
                        break;
                    case "String":
                        tree = new Tree<String>();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Please select a valid type.");
                }
                treePanel.repaint();
            }
        });

        // Layout da parte superior com campo de texto, botão e seleção de tipo
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select type:"));
        topPanel.add(typeSelector);
        topPanel.add(setTypeButton);
        topPanel.add(new JLabel("Enter value:"));
        topPanel.add(inputField);
        topPanel.add(addButton);
        topPanel.add(new JLabel("Remove value:"));
        topPanel.add(removeField);
        topPanel.add(removeButton);

        // Adicionando o painel superior e o painel da árvore ao JFrame
        add(topPanel, BorderLayout.NORTH);
        add(treePanel, BorderLayout.CENTER);
    }

    // Painel personalizado para desenhar a árvore
    class TreePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (tree != null && tree.getRoot() != null) {
                drawNode(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
            }
        }

        // Método recursivo para desenhar cada nó e suas conexões
        private void drawNode(Graphics g, Node node, int x, int y, int hGap) {
            if (node == null) return;

            g.setColor(Color.BLACK);
            g.fillOval(x - 15, y - 15, 30, 30);  // Desenha o nó como um círculo
            g.setColor(Color.WHITE);
            g.drawString(node.getData().toString(), x - 5, y + 5);  // Desenha o valor do nó

            if (node.getLeft() != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x - hGap, y + 50, x, y);  // Linha para o filho esquerdo
                drawNode(g, node.getLeft(), x - hGap, y + 50, hGap / 2);  // Desenha o filho esquerdo
            }

            if (node.getRight() != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x + hGap, y + 50, x, y);  // Linha para o filho direito
                drawNode(g, node.getRight(), x + hGap, y + 50, hGap / 2);  // Desenha o filho direito
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
