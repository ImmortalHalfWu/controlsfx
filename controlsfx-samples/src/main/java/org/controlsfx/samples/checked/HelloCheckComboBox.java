/**
 * Copyright (c) 2013, ControlsFX
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of ControlsFX, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CONTROLSFX BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.controlsfx.samples.checked;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import org.controlsfx.ControlsFXSample;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.samples.Utils;

public class HelloCheckComboBox extends ControlsFXSample {
    
    private Label checkedItemsLabel;
    
    @Override public String getSampleName() {
        return "CheckComboBox";
    }
    
    @Override public String getJavaDocURL() {
        return Utils.JAVADOC_BASE + "org/controlsfx/control/CheckComboBox.html";
    }
    
    @Override public String getSampleDescription() {
        return "A simple UI control that makes it possible to select zero or "
                + "more items within a ComboBox without the need to set a custom "
                + "cell factory or manually create boolean properties for each "
                + "row - simply use the check model property to request the "
                + "current selection state.";
    }
    
    @Override public Node getPanel(Stage stage) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(30, 30, 0, 30));
        
        int row = 0;
        
        final ObservableList<String> strings = FXCollections.observableArrayList();
        for (int i = 0; i <= 100; i++) {
            strings.add("Item " + i);
        }

        // normal ComboBox
        grid.add(new Label("Normal ComboBox: "), 0, row);
        grid.add(new ComboBox<String>(strings), 1, row++);
        
        // CheckComboBox
        final CheckComboBox<String> checkComboBox = new CheckComboBox<String>(strings);
        checkComboBox.getCheckModel().getSelectedItems().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(ListChangeListener.Change<? extends String> c) {
                updateText(checkedItemsLabel, c.getList());
            }
        });
        grid.add(new Label("CheckComboBox: "), 0, row);
        grid.add(checkComboBox, 1, row++);
        
        return grid;
    }
    
    @Override public Node getControlPanel() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(30, 30, 0, 30));
        
        Label label1 = new Label("Checked items: ");
        label1.getStyleClass().add("property");
        grid.add(label1, 0, 0);
        checkedItemsLabel = new Label();
        grid.add(checkedItemsLabel, 1, 0);
        updateText(checkedItemsLabel, null);
        
        return grid;
    }
    
    protected void updateText(Label label, ObservableList<? extends String> list) {
        final StringBuilder sb = new StringBuilder();
        
        if (list != null) {
            for (int i = 0, max = list.size(); i < max; i++) {
                sb.append(list.get(i));
                if (i < max - 1) {
                    sb.append(", ");
                }
            }
        }
        
        final String str = sb.toString();
        label.setText(str.isEmpty() ? "<empty>" : str);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
