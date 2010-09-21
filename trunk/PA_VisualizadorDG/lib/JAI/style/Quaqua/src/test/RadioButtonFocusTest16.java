package test;

import ch.randelshofer.quaqua.*;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author: Thomas Singer
 */
public class RadioButtonFocusTest16 implements ChangeListener {

	// Static =================================================================

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new QuaquaLookAndFeel());
				}
				catch (UnsupportedLookAndFeelException ex) {
					ex.printStackTrace();
				}

				final RadioButtonFocusTest16 editors = new RadioButtonFocusTest16();

				final JFrame frame = new JFrame("RadioButton-Focus-Test");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(editors.createPanel());
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

	// Fields =================================================================

	private final JRadioButton radioButton1;
	private final JRadioButton radioButton2;
	private final JRadioButton radioButton3;
	private final JTextField textField1;
	private final JTextField textField2;
	private final JTextField textField3;
	private final JLabel label;

	// Setup ==================================================================

	public RadioButtonFocusTest16() {
		final ButtonGroup group = new ButtonGroup();
		radioButton1 = createRadioButton("1st Option:", group);
		radioButton2 = createRadioButton("2nd Option", group);
		radioButton3 = createRadioButton("3rd Option:", group);
		textField1 = new JTextField("value1");
		textField2 = new JTextField("value3");
		textField3 = new JTextField("other text");
		label = new JLabel("Text:");

		radioButton1.addChangeListener(this);
		radioButton2.addChangeListener(this);
		radioButton3.addChangeListener(this);

		updateEnabledState();
	}

	// Implemented ============================================================

	public void stateChanged(ChangeEvent e) {
		updateEnabledState();
	}

	// Accessing ==============================================================

	public JComponent createPanel() {
		final JPanel panel = new JPanel();
		final GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
						.addGroup(
								layout.createSequentialGroup()
										.addComponent(radioButton1)
										.addComponent(textField1, 50, 100, Short.MAX_VALUE)
						)
						.addComponent(radioButton2)
						.addGroup(
								layout.createSequentialGroup()
										.addComponent(radioButton3)
										.addComponent(textField2, 50, 100, Short.MAX_VALUE)
						)
						.addGroup(
						layout.createSequentialGroup()
								.addComponent(label)
								.addComponent(textField3, 50, 100, Short.MAX_VALUE)
				)
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
						.addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(radioButton1)
										.addComponent(textField1)
						)
						.addComponent(radioButton2)
						.addGroup(
								layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(radioButton3)
										.addComponent(textField2)
						)
						.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label)
								.addComponent(textField3)
				)
		);
		return panel;
	}

	// Utils ==================================================================

	private JRadioButton createRadioButton(String text, ButtonGroup group) {
		final JRadioButton radioButton = new JRadioButton(text);
		if (group.getButtonCount() == 0) {
			radioButton.setSelected(true);
		}
		group.add(radioButton);
		return radioButton;
	}

	private void updateEnabledState() {
            if (true) return;
		setEnabled(textField1, radioButton1);
		setEnabled(textField2, radioButton3);
	}

	private void setEnabled(final JComponent component, final JRadioButton radioButton) {
		final boolean enable = radioButton.isSelected();
		if (component.isEnabled() == enable) {
			return;
		}

		if (enable || !component.isFocusOwner()) {
			component.setEnabled(enable);
			return;
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final AbstractButton focusableButton = getFocusableButton(radioButton);
				if (focusableButton != null) {
					focusableButton.requestFocusInWindow();
				}
				component.setEnabled(enable);
			}
		});
	}

	private AbstractButton getFocusableButton(JRadioButton radioButton) {
		if (radioButton.isFocusable()) {
			return radioButton;
		}

		final ButtonModel model = radioButton.getModel();
		if (model instanceof DefaultButtonModel) {
			final DefaultButtonModel defaultButtonModel = (DefaultButtonModel)model;
			final ButtonGroup group = defaultButtonModel.getGroup();
			if (group != null) {
				for (final Enumeration<AbstractButton> groupButtons = group.getElements(); groupButtons.hasMoreElements();) {
					final AbstractButton groupButton = groupButtons.nextElement();
					if (groupButton.isFocusable()) {
						return groupButton;
					}
				}
			}
		}

		return null;
	}
}