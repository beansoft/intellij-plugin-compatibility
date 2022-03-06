package com.beansoft.intellij.ui;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.TextComponentAccessor;

import javax.swing.*;
import java.awt.*;

/**
 * Resolve the IJ ComboboxWithBrowseButton deprecated warning.
 * ComboboxWithBrowseButton 在新版 IDEA 中已经被废弃删除.
 * please use ComboBox with browse extension, see <a href="https://jetbrains.design/intellij/controls/built_in_button/#browse">UI guidelines</a>
 * for details
 * @see com.intellij.ui.ComboboxWithBrowseButton
 * @date 2021-05-18
 */
//@Deprecated
public class ComboboxWithBrowseButton extends ComponentWithBrowseButton<JComboBox> {
  public ComboboxWithBrowseButton() {
    super(new JComboBox(), null);
  }

  public ComboboxWithBrowseButton(JComboBox comboBox) {
    super(comboBox, null);
  }

  public JComboBox getComboBox() {
    return getChildComponent();
  }

  @Override
  public void setTextFieldPreferredWidth(final int charCount) {
    super.setTextFieldPreferredWidth(charCount);
    final Component comp = getChildComponent().getEditor().getEditorComponent();
    Dimension size = comp.getPreferredSize();
    FontMetrics fontMetrics = comp.getFontMetrics(comp.getFont());
    size.width = fontMetrics.charWidth('a') * charCount;
    comp.setPreferredSize(size);
  }

  public void addBrowseFolderListener(Project project, FileChooserDescriptor descriptor) {
    addBrowseFolderListener(null, null, project, descriptor, TextComponentAccessor.STRING_COMBOBOX_WHOLE_TEXT);
  }
}
