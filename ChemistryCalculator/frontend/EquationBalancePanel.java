package ChemistryCalculator.frontend;

import ChemistryCalculator.backend.EquationBalancer;
import ChemistryCalculator.backend.History;
import ChemistryCalculator.backend.InvalidAtomException;
import ChemistryCalculator.backend.InvalidEquationException;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EquationBalancePanel extends JPanel {
    private static final Font SEGOE_UI = new Font("Segoe UI", 1, 14);
    private static final Color MAIN_COLOR = new Color(64, 43, 100);
    private static final Color MAIN_COLOR_LITE = new Color(85, 65, 118);
    private static final Color GRAY = new Color(204, 204, 204);

    private final JLabel labelForReactantsTextfield = new JLabel();
    private final JTextField reactantsTextfield = new JTextField();
    private final JLabel labelForProductsTextfield = new JLabel();
    private final JTextField productsTextfield = new JTextField();

    private final JButton balanceButton = new JButton();
    private final JButton historyButton = new JButton();
    private final JButton clearButton = new JButton();

    private final JPanel errorMessagePanel = new JPanel();
    private final JLabel errorMessageLabel = new JLabel();

    private final JPanel ansPanel = new JPanel();
    private final JLabel labelForBalancedEquation = new JLabel();
    private final JLabel balancedEquationLabel = new JLabel();
    private final JScrollPane balancedEquationScrollPane = new JScrollPane();
    private final JLabel labelForGivenEquation = new JLabel();
    private final JLabel givenEquationLabel = new JLabel();
    private final JScrollPane givenEquationScrollPane = new JScrollPane();
    public EquationBalancePanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
}

public EquationBalancePanel() {
        initComponent();
        // setComponentLayout();
    }

    private void initComponent() {

        extracted();

        extracted2();

        extracted3();

        extracted4();

        ansPanel.setBackground(MAIN_COLOR_LITE);
        ansPanel.setVisible(false);

        extracted5();

        extracted6();

        extracted7();


    }

private void extracted7() {
        givenEquationScrollPane.setBackground(MAIN_COLOR_LITE);
        givenEquationScrollPane.setBorder(null);
        givenEquationScrollPane.setForeground(MAIN_COLOR_LITE);
        givenEquationScrollPane.setAutoscrolls(true);

        givenEquationLabel.setBackground(MAIN_COLOR_LITE);
        givenEquationLabel.setFont(SEGOE_UI); // NOI18N
        givenEquationLabel.setForeground(Color.white);
        givenEquationLabel.setOpaque(true);
        givenEquationScrollPane.setViewportView(givenEquationLabel);
}

private void extracted6() {
        balancedEquationScrollPane.setBackground(MAIN_COLOR_LITE);
        balancedEquationScrollPane.setBorder(null);
        balancedEquationScrollPane.setForeground(MAIN_COLOR_LITE);
        balancedEquationScrollPane.setAutoscrolls(true);

        balancedEquationLabel.setBackground(MAIN_COLOR_LITE);
        balancedEquationLabel.setFont(SEGOE_UI); // NOI18N
        balancedEquationLabel.setForeground(Color.white);
        balancedEquationLabel.setOpaque(true);
        balancedEquationScrollPane.setViewportView(balancedEquationLabel);
}

private void extracted5() {
        labelForBalancedEquation.setFont(SEGOE_UI); // NOI18N
        labelForBalancedEquation.setForeground(GRAY);
        labelForBalancedEquation.setText("Balanced Equation :");

        labelForGivenEquation.setFont(SEGOE_UI); // NOI18N
        labelForGivenEquation.setForeground(GRAY);
        labelForGivenEquation.setText("Given Equation :");
}

private void extracted4() {
        historyButton.setBackground(MAIN_COLOR);
        historyButton.setFont(SEGOE_UI);
        historyButton.setForeground(GRAY);
        historyButton.setText("History");
        historyButton.setAutoscrolls(true);
        historyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        historyButton.addActionListener(this::historyButtonActionPerformed);

        clearButton.setBackground(MAIN_COLOR);
        clearButton.setFont(SEGOE_UI); // NOI18N
        clearButton.setForeground(GRAY);
        clearButton.setText("Clear");
        clearButton.setAutoscrolls(true);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clearButton.addActionListener(this::clearButtonActionPerformed);
}

private void extracted3() {
        balanceButton.setBackground(MAIN_COLOR);
        balanceButton.setFont(SEGOE_UI);
        balanceButton.setForeground(GRAY);
        balanceButton.setText("Balance");
        balanceButton.setAutoscrolls(true);
        balanceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        balanceButton.addActionListener(this::balanceButtonActionPerformed);
}

private void extracted2() {
        errorMessagePanel.setBackground(Color.red);
        errorMessagePanel.setVisible(false);

        errorMessageLabel.setFont(SEGOE_UI);
        errorMessageLabel.setForeground(Color.white);
        errorMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
}

private void extracted() {
        labelForReactantsTextfield.setFont(SEGOE_UI);
        labelForReactantsTextfield.setForeground(MAIN_COLOR);
        labelForReactantsTextfield.setText("Reactants : ");


        labelForProductsTextfield.setFont(SEGOE_UI);
        labelForProductsTextfield.setForeground(MAIN_COLOR);
        labelForProductsTextfield.setText("Products  :");
}

    /**
 * 
 */




    private void balanceButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String reactants = reactantsTextfield.getText();
        String products = productsTextfield.getText();
        if (!reactants.isEmpty() || !products.isEmpty()) {
            String balancedEquation;
            EquationBalancer balancer = new EquationBalancer(reactants, products);

            try {
                balancedEquation = balancer.balance();
                errorMessagePanel.setVisible(false);
                givenEquationLabel.setText(Formater.formatEquation(reactants) + " = " + Formater.formatEquation(products));
                balancedEquationLabel.setText(Formater.formatEquation(balancedEquation));
                ansPanel.setVisible(true);

                new History() {
                }.add(balancedEquation);

            } catch (InvalidAtomException | InvalidEquationException | FileNotFoundException e) {
                errorMessageLabel.setText(e.getMessage());
                errorMessagePanel.setVisible(true);
                ansPanel.setVisible(false);
            }
        } else {
            errorMessageLabel.setText("Both fields are required !");
            errorMessagePanel.setVisible(true);
            ansPanel.setVisible(false);
        }

    }


    private void historyButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        HistoryFrame history;
        try {
            history = new HistoryFrame();

        } catch (IOException e) {
            errorMessageLabel.setText("History panel is empty");
            errorMessagePanel.setVisible(true);
            ansPanel.setVisible(false);
            return;
        }

        history.toFront();
        history.requestFocus();
    }

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        reactantsTextfield.setText(null);
        productsTextfield.setText(null);
        errorMessagePanel.setVisible(false);
        ansPanel.setVisible(false);

    }

}
