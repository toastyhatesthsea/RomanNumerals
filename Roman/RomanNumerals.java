package Roman;

import java.util.HashMap;

public class RomanNumerals
{

    public HashMap<String, Integer> numerals;
    public HashMap<String, Integer> beforeNumerals;


    public RomanNumerals()
    {
        numerals.put("V", 5);
        numerals.put("M", 1000);
        numerals.put("C", 100);
        numerals.put("L", 50);
        numerals.put("X", 10);
        numerals.put("I", 1);
        numerals.put("D", 500);
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
