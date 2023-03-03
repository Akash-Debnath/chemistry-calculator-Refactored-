package ChemistryCalculator.frontend;

import java.lang.foreign.GroupLayout;

public interface setComponentLayout {

    GroupLayout Layout = new GroupLayout(this);

    @Override
    boolean equals(Object obj)
        this.setLayout(Layout);
        Layout.setHorizontalGroup(
                Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Layout.createSequentialGroup()
                                .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(ansPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(Layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addComponent(errorMessagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(Layout.createSequentialGroup()
                                                                        .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(labelForReactantsTextfield, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(labelForProductsTextfield, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addGap(18, 18, 18)
                                                                        .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(reactantsTextfield, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                                                                                .addComponent(productsTextfield))))
                                                        .addGroup(Layout.createSequentialGroup()
                                                                .addComponent(balanceButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(historyButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(clearButton, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 134, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        Layout.setVerticalGroup(
                Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(errorMessagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(reactantsTextfield, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                        .addComponent(labelForReactantsTextfield, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(productsTextfield, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                        .addComponent(labelForProductsTextfield, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(35, 35, 35)
                                .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(balanceButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(clearButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(historyButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                                .addComponent(ansPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
}
