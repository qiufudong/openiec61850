/**
 * This class file was automatically generated by jASN1 v1.7.2-SNAPSHOT (http://www.openmuc.org)
 */

package org.openmuc.josistack.internal.presentation.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openmuc.jasn1.ber.BerByteArrayOutputStream;
import org.openmuc.jasn1.ber.BerLength;
import org.openmuc.jasn1.ber.BerTag;

public class FullyEncodedData {

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
    public byte[] code = null;
    private List<PDVList> seqOf = null;

    public FullyEncodedData() {
        seqOf = new ArrayList<>();
    }

    public FullyEncodedData(byte[] code) {
        this.code = code;
    }

    public List<PDVList> getPDVList() {
        if (seqOf == null) {
            seqOf = new ArrayList<>();
        }
        return seqOf;
    }

    public int encode(BerByteArrayOutputStream os) throws IOException {
        return encode(os, true);
    }

    public int encode(BerByteArrayOutputStream os, boolean withTag) throws IOException {

        if (code != null) {
            for (int i = code.length - 1; i >= 0; i--) {
                os.write(code[i]);
            }
            if (withTag) {
                return tag.encode(os) + code.length;
            }
            return code.length;
        }

        int codeLength = 0;
        for (int i = (seqOf.size() - 1); i >= 0; i--) {
            codeLength += seqOf.get(i).encode(os, true);
        }

        codeLength += BerLength.encodeLength(os, codeLength);

        if (withTag) {
            codeLength += tag.encode(os);
        }

        return codeLength;
    }

    public int decode(InputStream is) throws IOException {
        return decode(is, true);
    }

    public int decode(InputStream is, boolean withTag) throws IOException {
        int codeLength = 0;
        int subCodeLength = 0;
        BerTag berTag = new BerTag();
        if (withTag) {
            codeLength += tag.decodeAndCheck(is);
        }

        BerLength length = new BerLength();
        codeLength += length.decode(is);
        int totalLength = length.val;

        while (subCodeLength < totalLength) {
            PDVList element = new PDVList();
            subCodeLength += element.decode(is, true);
            seqOf.add(element);
        }
        if (subCodeLength != totalLength) {
            throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength + " but has "
                    + subCodeLength);

        }
        codeLength += subCodeLength;

        return codeLength;
    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
        BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
        encode(os, false);
        code = os.getArray();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SEQUENCE OF{");

        if (seqOf == null) {
            sb.append("null");
        }
        else {
            Iterator<PDVList> it = seqOf.iterator();
            if (it.hasNext()) {
                sb.append(it.next());
                while (it.hasNext()) {
                    sb.append(", ").append(it.next());
                }
            }
        }

        sb.append("}");

        return sb.toString();
    }

}