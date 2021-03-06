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

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.openmuc.openiec61850.BdaOctetString;
import org.openmuc.openiec61850.BdaType;
import org.openmuc.openiec61850.clientgui.BasicDataBind;

public class OctetStringDataBind extends BasicDataBind<BdaOctetString> {

    public OctetStringDataBind(BdaOctetString data) {
        super(data, BdaType.OCTET_STRING);
    }

    @Override
    protected JComponent init() {
        byte[] value = data.getValue();
        StringBuilder sb;

        sb = new StringBuilder("OctetString [");
        for (int i = 0; i < value.length; i++) {
            sb.append(Integer.toHexString(value[i] & 0xff));
            if (i != value.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return new JLabel(sb.toString());
    }

    @Override
    protected void resetImpl() {
        // ignore for now
    }

    @Override
    protected void writeImpl() {
        // ignore for now
    }
}
