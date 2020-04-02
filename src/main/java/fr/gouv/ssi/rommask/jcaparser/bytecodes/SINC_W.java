package fr.gouv.ssi.rommask.jcaparser.bytecodes;

/*-
 * #%L
 * Java Card RomMask Generator
 * %%
 * Copyright (C) 2020 National Cybersecurity Agency of France (ANSSI)
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import fr.gouv.ssi.rommask.jcaparser.Bytecode;
import fr.gouv.ssi.rommask.jcaparser.Instruction;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * sinc_w instructions class
 *
 * @author Guillaume Bouffard
 */
public class SINC_W extends Instruction {

    /**
     * Default class constructor
     *
     * @param parameters instruction parameter
     */
    public SINC_W(ArrayList<String> parameters) {
        super(Bytecode.SINC_W, parameters);
    }

    @Override
    protected ArrayList<Byte> generateParametersList(ArrayList<String> parameters) throws InvalidParameterException {
        ArrayList<Byte> params = new ArrayList<>();

        if (parameters.size() != 2) {
            throw new InvalidParameterException
                    (this.getOpcode() + " has invalid parameters size.");
        }

        byte index = (byte) Integer.parseInt(parameters.get(0));
        short value = (byte) Integer.parseInt(parameters.get(1));

        params.add(index);
        params.add((byte) (value >> 8));
        params.add((byte) value);

        return params;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        ArrayList<Byte> params = this.getParameters();

        out.append(super.toString() + " ");

        out.append((params.get(0) & 0x00FF) + " ");

        short value = (short) (((params.get(1) & 0x00FF) << 8) | (params.get(2) & 0x00FF));
        out.append(value);

        return out.toString();
    }

}
