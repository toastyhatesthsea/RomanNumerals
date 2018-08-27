package Roman;

import java.util.HashMap;
import java.util.HashSet;

public class RomanNumerals
{

    public HashMap<String, Integer> numerals;
    public HashMap<String, Integer> beforeNumerals;
    public HashSet<String> values;  //A hashset for making sure D, L, and V can each only appear once.
    public int total;


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

        numerals.put("IX", 9);
        numerals.put("IV", 4);
        numerals.put("XL", 40);
        numerals.put("XC", 90);
        numerals.put("CD", 400);
        numerals.put("CM", 900);
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
            boolean isValid = true;

            for (int i = 0; i < numeral.length() && isValid; i++)
            {
                firstNumeral = numeral.substring(i, i + 1);
                boolean empty = values.add(firstNumeral);

                if (!empty) //Has numeral of D, L or V already
                {
                    isValid = false;
                } else if (i + 1 < numeral.length())
                {
                    String secondNumeral = numeral.substring(i + 1, i + 2);

                    int compareValue = Integer.valueOf(secondNumeral).compareTo(Integer.valueOf(firstNumeral));

                    if (compareValue > 0) //checks if second value is greater
                    {
                        Integer combinedValue = numerals.get(firstNumeral.concat(secondNumeral));
                        if (combinedValue != null)
                        {
                            total += combinedValue;
                            i++;
                        }
                        else //second value cannot be greater than first
                        {
                            isValid = false;
                        }
                    }
                    else if(compareValue == 0)
                    {
                        total += numerals.get(firstNumeral);
                        total += numerals.get(secondNumeral);
                        i++;
                    }

                }
                 else
                {
                    answer += numerals.get(firstNumeral);
                }
            }
            if (!isValid)
            {
                return 0;
            }
        }
        else
        {
            return 0;
        }

    }

    public boolean isSmaller(String numeralOne, String numeralTwo)
    {
        int value = numerals.get(numeralOne).compareTo(numerals.get(numeralTwo));

        if (value > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public int checkSmallerDenominations(String numerals)
    {

    }

}

class RomanTesters{


    public static void main(String[] agsgs)
    {
        RomanNumerals roman = new RomanNumerals();

        boolean value = roman.isSmaller("X", "V");
    }
}

