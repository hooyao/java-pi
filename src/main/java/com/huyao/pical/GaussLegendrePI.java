package com.huyao.pical;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @author Hu Yao
 * @version $Revision:0.1 $
 */
public class GaussLegendrePI {

    /**
     * User defined digit length
     */
    public static int LENGTH = 1000000;

    /**
     *
     */
    public static HashMap<String, String> pi_gt = new HashMap<>();

    /**
     *
     */
    public static void init() {
        pi_gt.put("1", "c4ca4238a0b923820dcc509a6f75849b"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("2", "aab3238922bcc25a6f606eb525ffdc56"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("5", "c92b32fbc94e2dff3e5516401d9bb463"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("10", "0c70798ef9bd5bb878f53df3f85774de"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("20", "089b653970518aad3828d6e8630a1ae2"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("50", "d2ac6f2399ea4e94ec2cdfdb99c95ad9"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("100", "954a383ec609b9126d4ba2ddf0bd1b37"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("200", "59433bdec0a3a97f6d2785f855506ab7"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("500", "5140171f33e9c149c97c8f3643f93f3e"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("1000", "5398eb229b2f9b4cdc42681c110707bb"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("2000", "bb1fd8b5786fb4b3c91467e302631533"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("5000", "e1de0b54b87e3dcc846be7489edbdb8f"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("10000", "88d4eda38e94791ab2548d8463144a09"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("20000", "b660e34c926544e0ac174df38829ebd6"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("50000", "b2a057082a245a0a41817f614d95921e"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("100000", "de18e0d8fef70b8f925cb2392c53f145"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("200000", "f22b756816f38eddf86ce210b3210841"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("500000", "59de28f2ce1b2e4a95576e977da64c3e"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("1000000", "e668904c195521a2a2dfef948ac54c8e"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("2000000", "12887cce18c4e1bffd9505c926230a90"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("5000000", "eb2774b75ab2f64f7d65b2d9436491d6"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("10000000", "7f5f4ad06f084278f283a661ffafb379"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("20000000", "13e07d09e200aa0837bf9b6c600b1818"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("50000000", "8c50bb4458cbcc2c896ea50bd32da627"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("100000000", "dfc9f9b7080939885afb5f7bd2e04c4a"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("200000000", "496423ea6297b6455f7d076e2e4e2b56"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("500000000", "a3e9ad8d21dff2ccdc6bc96f369e3176"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("1000000000", "bff5d599c5aa342ab76086392cb73695"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("2000000000", "b2dae9b1569c3818b2ef78a0b6ad9502"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("5000000000", "1d49002df7b62bc64241452d3bf75188"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("10000000000", "a3f0710c37a9e709edbd2de7a8925561"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("20000000000", "240039d1a31f3caf6ec6cc3750b867c8"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("50000000000", "67cb35130aac39f5cb8c79235b23a204"); //$NON-NLS-1$ //$NON-NLS-2$
        pi_gt.put("100000000000", "be5905dc2b9073a9f4c3635a1277b14a"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        init();
        int scale = LENGTH + 1;

        Apfloat ONE = new Apfloat(1, scale);
        Apfloat TWO = new Apfloat(2, scale);
        Apfloat FOUR = new Apfloat(4, scale);

        // TODO Auto-generated method stub
        // init values
        Apfloat a = ONE;
        Apfloat b = ONE.divide(ApfloatMath.sqrt(TWO));
        Apfloat t = ONE.divide(FOUR);
        Apfloat p = ONE;

        long before = System.currentTimeMillis();
        int itrCount = (int) Math.round(Math.log(scale) / Math.log(2.0));
        // iteration
        for (int i = 0; i < itrCount; i++) {
            Apfloat an = a.add(b).divide(TWO);
            b = ApfloatMath.sqrt(a.multiply(b));
            t = t.subtract(p.multiply((a.subtract(an).multiply((a.subtract(an))))));
            p = p.multiply(TWO);
            a = an;
            System.out.println("itr:" + i); //$NON-NLS-1$
        }

        // final
        Apfloat pi = a.add(b).multiply(a.add(b).divide(t).divide(FOUR));
        long after = System.currentTimeMillis();
        double time = (after - before) / 1000.0;
        System.out.println("Time:" + String.format("%1.3f", new Double(time))); //$NON-NLS-1$ //$NON-NLS-2$
        try {
            BufferedWriter io = new BufferedWriter(new FileWriter("z:\\pi.txt")); //$NON-NLS-1$
            io.write(pi.toString());
            io.flush();
            io.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String digits = pi.toString().substring(2);
        try {
            byte[] bytesOfMessage = digits.getBytes("UTF-8"); //$NON-NLS-1$
            MessageDigest md = MessageDigest.getInstance("MD5"); //$NON-NLS-1$
            byte[] thedigest = md.digest(bytesOfMessage);

            String hex = toHex(thedigest);
            System.out.println("MD5:" + hex.toLowerCase()); //$NON-NLS-1$
            String gt_hex = pi_gt.get(String.valueOf(LENGTH));
            if (gt_hex != null) {
                if (gt_hex.equalsIgnoreCase(hex)) {
                    System.out.println("Result is verified."); //$NON-NLS-1$
                } else {
                    System.out.println("Result is invalid."); //$NON-NLS-1$
                }
            } else {
                System.out.println("Result can't be verified."); //$NON-NLS-1$
            }

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Method toHex.
     *
     * @param bytes byte[]
     * @return String
     */
    public static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi); //$NON-NLS-1$ //$NON-NLS-2$
    }

}