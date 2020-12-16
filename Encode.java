import java.util.ArrayList ;
import java.util.Scanner ;
import java.util.Collections ;
import java.util.Comparator ;

public class Encode 
{
    private ArrayList<Node <Character, Integer>> __frequencies = new ArrayList<Node <Character, Integer>> () ;

    public static void main(String[] args) 
    {
        Encode encode = new Encode () ;
        FileOperations operation = new FileOperations () ;
        Scanner reference = operation.openFile("file.txt");
        String line = null;

        /**
         * This loop read original file line by line and passes the line to calculateFrequency
         * function to find frequency for each character.
         */

        while ((line = operation.readFile (reference)) != null)
        {
            encode.__calculateFrequency(line);
        }

        encode.__sort();
        encode.__calculatePossibilities();
        encode.__generateCodes (encode.__frequencies) ;

        /**
         * After the generating codes operation encoding function encodes file
         * and write codes to the encoded file.
         */

        reference = operation.openFile ("file.txt") ;

        while ((line = operation.readFile (reference)) != null)
        {
            operation.writeFile (encode.__encoding (line) , "encoded.txt") ;
        }

        // Frequency list is wroten to the codes file as array list.

        operation.writeCodes (encode.__frequencies , "codes.bin") ;

        System.out.println("File was encoded.!");
    }

    private void __calculateFrequency(String line)
    {
        /**
         * These two loops goes through the line and compares each character with
         * the other character in the array list. The character's value is increased
         * if this character in array list already or the character is created and added to the
         * array list if is not in the array list yet.
         */

        for (int i = 0; i < line.length(); i++) 
        {
            boolean flag = true;

            for (Node <Character, Integer> n : this.__frequencies)
             {
                if (n.getKey ().equals (line.charAt (i))) 
                {
                    n.setValue (n.getValue () + 1) ;

                    flag = false;
                    break;
                }
            }

            if (flag) 
            {
                this.__frequencies.add(new Node<Character, Integer>(line.charAt(i), 1));
            }
        }
    }

    private void __sort() 
    {
        Collections.sort
                (
                        this.__frequencies,

                        new Comparator <Node <Character, Integer>> () 
                        {
                            @Override
                            public int compare(Node <Character, Integer> first, Node <Character, Integer> second) 
                            {
                                if (first.getValue () > second.getValue ()) 
                                {
                                    return -1;
                                } 

                                else if (first.getValue () < second.getValue ()) 
                                {
                                    return 1;
                                } 

                                else 
                                {
                                    return 0;
                                }
                            }
                        }
                );
    }

    private void __calculatePossibilities ()
    {
        /**
         * This function calculates possiblity for each character in array list.
         */

        int amount_of_letter = 0;
        boolean counter = true;

        for (int i = 0; i < 2; i++)
        {
            for (Node<Character, Integer> n : this.__frequencies)
            {
                if (counter)
                {
                    amount_of_letter += n.getValue();
                }
                else
                {
                    n.setPossibility((double) n.getValue() / amount_of_letter);
                }
            }

            counter = false;
        }
    }

    private void __generateCodes (ArrayList <Node <Character , Integer>> list)
    {
        /**
         * This function creates coding tree recursively and then generate codes according to
         * each character's possibility.
         */

        if (list.size () > 1)
        {
            ArrayList <Node <Character , Integer>> left = new ArrayList <Node <Character , Integer>> () ;
            ArrayList <Node <Character , Integer>> right = new ArrayList <Node <Character , Integer>> () ;

            class Helper
            {
                public double sum (ArrayList <Node <Character , Integer>> rest_of_list , int start)
                {
                    double result = 0d ;

                    for (int i = start ; i < rest_of_list.size () ; i ++)
                    {
                        result += rest_of_list.get (i).getPossibility () ;
                    }

                    return result ;
                }
            }

            Helper helper = new Helper () ;
            int stop = 1 ;

            while (stop < list.size ())
            {
                double total_possibility = 0d ;

                for (int i = 0 ; i < stop ; i ++)
                {
                    total_possibility += list.get (i).getPossibility () ;
                }

                if (Math.abs (total_possibility - helper.sum (list , stop)) <= 0.2)
                {
                    for (int i = 0 ; i < stop ; i ++)
                    {
                        list.get (i).setCode (list.get (i).getCode () + '0') ;
                        left.add (list.get (i)) ;
                    }

                    for (int i = stop ; i < list.size () ; i ++)
                    {
                        list.get (i).setCode (list.get (i).getCode () + '1') ;
                        right.add (list.get (i)) ;
                    }

                    break ;
                }

                stop ++ ;
            }

            this.__generateCodes (left) ;
            this.__generateCodes (right) ;
        }
    }

    private String __encoding (String line)
    {
        /**
         * This function encodes line according as codes that generated before
         * and return enocded line for writing to the encoded file.
         */

        String encoded = "" ;

        for (int i = 0 ; i < line.length () ; i ++)
        {
            for (Node <Character , Integer> n : this.__frequencies)
            {
                if (line.charAt (i) == n.getKey ())
                {
                    encoded += n.getCode () ;
                }
            }
        }

        return encoded ;
    }
}