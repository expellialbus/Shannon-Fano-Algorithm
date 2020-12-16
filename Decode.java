import java.util.ArrayList ;
import java.util.Scanner ;

public class Decode
{
	// This list keeps frequency list.

    private ArrayList <Node <Character , Integer>> __codes = null ;

    public static void main (String [] args)
    {
        FileOperations operation = new FileOperations () ;
        Decode decode = new Decode () ;
        Scanner reference = operation.openFile ("encoded.txt") ; 
        String line = null ;

        decode.__codes = operation.readCodes ("codes.bin") ;

        /**
         * This loop reads each line from encoded file and passes these lines to function
         * which using for decoding.
         */


        while ((line = operation.readFile (reference)) != null)
        {
            decode.__decoding (line , "") ;
        }

        System.out.println("File was decoded.!");
    }

    private void __decoding (String line , String letters)
    {
        if (line.length () == 0)
        {
            FileOperations operation = new FileOperations () ;

            operation.writeFile (letters , "decoded.txt") ;

            return ;
        }

        int start = 0 ; // this variable keeps begin index for creating substring

        for (int i = 0 ; i < line.length () ; i ++)
        {
            String code = "" ;
            boolean flag = false ; // to break both loops

            /**
             * At this point the codes which read from encoded file are comparing with
             * frequency list and if equality has found, decoded letter is added to
             * letter variable. After that loops are broken and new line passes to the
             * decoding function recursively.
             */

            for (int j = 0 ; j <= i ; j ++)
            {
                code += line.charAt (j) ;
            }

            for (Node <Character , Integer> n : this.__codes)
            {
                if (code.equals (n.getCode ()))
                {
                    letters += n.getKey () ;
                    start = i + 1 ;
                    flag = true ;
                    break ;
                }
            }

            if (flag)
            {
                break ;
            }
        }

        this.__decoding (line.substring (start) , letters) ;
    }
}