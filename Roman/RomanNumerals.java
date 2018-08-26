package Roman;

import java.util.HashMap;
import java.util.HashSet;

public class RomanNumerals
{

    public HashMap<String, Integer> numerals;
    public HashMap<String, Integer> beforeNumerals;
    public HashSet<String> values;  //A hashset for making sure D, L, and V can each only appear once.


    public RomanNumerals()
    {
        numerals = new HashMap<>();
        values = new HashSet<>();

        numerals.put("V", 5);
        numerals.put("M", 1000);
        numerals.put("C", 100);
        numerals.put("L", 50);
        numerals.put("X", 10);
        numerals.put("I", 1);
        numerals.put("D", 500);
    }

    public int convert(String numeral)
    {
        int answer = 0;
        if (numeral.length() == 1)
        {
            return numerals.get(numeral);
        } else if (numeral.length() > 0)
        {
            String firstNumeral;

            for (int i = 0; i < numeral.length(); i++)
            {
                String secondNumeral = numeral.substring(i, i + 1);
                if (i + 1 < numeral.length())
                {
                    firstNumeral = numeral.substring(i + 1, i + 2);
                } else
                {

                }
            }
        }
        else
        {
            return answer;
        }

    }

    public boolean isSmaller(String numeralOne, String numeralTwo)
    {
        int value = numerals.get(numeralOne).compareTo(numerals.get(numeralTwo));

        if (value == 0 || value > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}

class RomanTesters{


    public static void main(String[] agsgs)
    {
        RomanNumerals roman = new RomanNumerals();

        boolean value = roman.isSmaller("X", "V");
    }
}

