/*
 * Copyright 2011-17 Fraunhofer ISE, energy & meteo Systems GmbH and other contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.openmuc.openiec61850.clientgui.databind;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.openmuc.openiec61850.BdaCheck;
import org.openmuc.openiec61850.BdaType;
import org.openmuc.openiec61850.clientgui.BasicDataBind;

public class CheckDataBind extends BasicDataBind<BdaCheck> {

    private final JCheckBox interlock = new JCheckBox("Interlock");
    private final JCheckBox synchron = new JCheckBox("Synchron");

    public CheckDataBind(BdaCheck data) {
        super(data, BdaType.CHECK);
    }

    @Override
    protected JComponent init() {
        interlock.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel valuePanel = new JPanel();
        valuePanel.setLayout(new BoxLayout(valuePanel, BoxLayout.PAGE_AXIS));
        valuePanel.add(interlock);
        valuePanel.add(synchron);
        return valuePanel;
    }

    @Override
    protected void resetImpl() {
        interlock.setSelected(data.getInterlockCheck());
        synchron.setSelected(data.getSynchrocheck());
    }

    @Override
    protected void writeImpl() {
        data.setInterlockCheck(interlock.isSelected());
        data.setSynchrocheck(synchron.isSelected());
    }
}
