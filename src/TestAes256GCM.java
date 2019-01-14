public class TestAes256GCM {

    public static void main(String[] args) {
            //Generate and dump KEY so we could use again
            //System.out.println(AesGcm256.toHex(AesGcm256.NewKey()));

            //Generate and dump IV so we could use again
            //System.out.println(AesGcm256.toHex(AesGcm256.NewIv()));

            //Console.ReadKey();


            //using above code these key and iv was generated
            String hexKey = "2192B39425BBD08B6E8E61C5D1F1BC9F428FC569FBC6F78C0BC48FCCDB0F42AE";
            String hexIV = "E1E592E87225847C11D948684F3B070D";

            String plainText = "Test encryption and decryption";
            System.out.println("Plain Text: " + plainText);

            //encrypt - result base64 encoded string
            String encryptedText = AesGcm256.encrypt(plainText, AesGcm256.HexToByte(hexKey), AesGcm256.HexToByte(hexIV));
            System.out.println("Encrypted base64 encoded: " + encryptedText);

            //decrypt - result plain string
            String decryptedText = AesGcm256.decrypt(encryptedText, AesGcm256.HexToByte(hexKey), AesGcm256.HexToByte(hexIV));
            System.out.println("Decrypted Text: " + decryptedText);

            if (plainText.equals(decryptedText))
            {
                System.out.println("Test Passed");
            }
            else
            {
                System.out.println("Test Failed");
            }

            /* Console Output
            Plain Text: Test encryption and decryption
            Encrypted base64 encoded: A/boAixWJKflKviHp2cfDl6l/xn1qw2MsHcKFkrOfm2XOVmawIFct4fS1w7wKw==
            Decrypted Text: Test encryption and decryption
            Test Passed
            Press any key to continue . . .
            */

            // Lets take encrypted string to Java and Decrypt it.
            int[] arrival = {2,8,4,5,3,7,4};
            System.out.println("Maximum wait time is: " + maxWait(arrival, 3, 15, 14));
    }
    


    private static int maxWait(int[] arrival, int X, int Y, int Z)
    {
        int[] service = {X, Y, Z};
        
        int maxWaitTime = 0;
        int index = 0;
        int waitTime = 0;
        
        int[]finishTime = new int[arrival.length];

        for(int i=0; i<arrival.length;i++)
        {
            int startTime;

            if(index == 0)
            {
                 startTime = arrival[index];
                 //System.out.println(startTime);
            }
            else
            {
                startTime = Math.max(arrival[i],finishTime[i-1]);
            }
            finishTime[i] = startTime + service[index];
            waitTime = finishTime[i] - service[index] - arrival[index];

            if(waitTime > maxWaitTime)
            {
                maxWaitTime = waitTime;
            }
            ++index;
            if(index>service.length-1) index=0;
        }
        return maxWaitTime;
    }

    
}
