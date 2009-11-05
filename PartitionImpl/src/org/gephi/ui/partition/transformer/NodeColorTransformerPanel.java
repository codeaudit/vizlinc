/*
Copyright 2008 WebAtlas
Authors : Mathieu Bastian, Mathieu Jacomy, Julian Bilcke
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gephi.ui.partition.transformer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import net.java.dev.colorchooser.ColorChooser;
import org.gephi.partition.api.Part;
import org.gephi.partition.api.Partition;
import org.gephi.partition.api.Transformer;
import org.gephi.partition.transformer.NodeColorTransformer;
import org.gephi.ui.utils.BusyUtils;
import org.gephi.ui.utils.PaletteUtils;

/**
 *
 * @author Mathieu Bastian
 */
public class NodeColorTransformerPanel extends javax.swing.JPanel {

    public NodeColorTransformerPanel() {
        initComponents();
    }

    public void setup(Partition partition, Transformer transformer) {
        removeAll();
        final NodeColorTransformer nodeColorTransformer = (NodeColorTransformer) transformer;
        if (nodeColorTransformer.getMap().isEmpty()) {
            List<Color> colors = PaletteUtils.getSequenceColors(partition.getPartsCount());
            int i = 0;
            for (Part p : partition.getParts()) {
                nodeColorTransformer.getMap().put(p.getValue(), colors.get(i));
                i++;
            }
        }

        //JPanel gridPanel = new JPanel(new GridLayout(partition.getPartsCount(), 2, 2, 2));
        for (final Part p : partition.getParts()) {
            JLabel partLabel = new JLabel(p.getDisplayName());
            add(partLabel);
            final ColorChooser colorChooser = new ColorChooser(nodeColorTransformer.getMap().get(p.getValue()));
            colorChooser.setPreferredSize(new Dimension(16, 16));
            colorChooser.setMaximumSize(new Dimension(16, 16));
            colorChooser.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    nodeColorTransformer.getMap().put(p.getValue(), colorChooser.getColor());
                }
            });
            add(colorChooser, "wrap");
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        net.miginfocom.swing.MigLayout migLayout2 = new net.miginfocom.swing.MigLayout();
        migLayout2.setColumnConstraints("[pref!]20[grow,fill]");
        setLayout(migLayout2);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}