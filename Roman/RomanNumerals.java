package Roman;

import java.util.HashMap;
import java.util.HashSet;

public class RomanNumerals
{

    public HashMap<String, Integer> numerals;
    public HashMap<String, Integer> beforeNumerals;
    public HashMap<String, Integer> values;  //A hashset for making sure D, L, and V can each only appear once.
    public int total;


    public RomanNumerals()
    {
        numerals = new HashMap<>();

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
        values = new HashMap<>();
        int answer = 0;
        if (numeral.length() == 1)
        {
            return numerals.get(numeral);
        } else if (numeral.length() > 0)
        {
            int largestValue = 0;
            String firstNumeral;
            boolean isValid = true;

            for (int i = 0; i < numeral.length() && isValid; i++)
            {
                firstNumeral = numeral.substring(i, i + 1);

                if (!values.containsKey(firstNumeral))
                {
                    values.put(firstNumeral, 1);
                } else
                {
                    values.put(firstNumeral, values.get(firstNumeral) + 1);
                }

                boolean smallerDenominations = checkForSmallerDenominations();

                if (!smallerDenominations) //Has numeral of D, L or V already or smaller denominations exceeds greater denominations
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
                            if (largestValue == 0)//checks to make sure not exceeded by smaller denominations
                            {
                                largestValue = combinedValue;
                            } else if (combinedValue > largestValue)
                            {
                                isValid = false;
                            }
                            total += combinedValue;
                            i++;
                        } else //second value cannot be greater than first
                        {
                            isValid = false;
                        }
                    } else if (compareValue == 0) //Must check for smaller denominations here
                    {
                        int firstValue = numerals.get(firstNumeral);

                        if (largestValue == 0)
                        {
                            largestValue = firstValue;
                        } else if (firstValue > largestValue)
                        {
                            isValid = false;
                        }
                        total += firstValue * 2;
                        i++;

                    } else
                    {
                        total += numerals.get(firstNumeral);
                    }

                } else //end of the string
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

    /**
     * Returns an array, where the first value is the total value added and the second is the array index where the values are no longer equal.
     * Will return -1 as the index if any of the numerals are larger than the largest value or
     * @param numerals
     * @return
     */
    public int[] checkSmallerDenominations(String numerals, int largestValue)
    {
        int[] answer = new int[2];

        for(int i=0; i<numerals.length(); i++)
        {
            String current = numerals.substring(i, i + 1);

            int valueOfNumeral = this.numerals.get(current);

            if (valueOfNumeral > largestValue)
            {
                answer[0] = 0;
                answer[1] = -1;
                return answer;
            }

            if (i + 1 < numerals.length())
            {
                String nextNumeral = numerals.substring(i + 1, i + 2);
                int valueOfSecondNumeral = this.numerals.get(nextNumeral);


            }
        }
        return null;
    }

    public boolean checkForSmallerDenominations()
    {
        Integer vValue = values.get("V");
        if (vValue != null && vValue > 1)
        {
            return false;
        }

        Integer iValue = values.get("I");
        if (iValue != null && iValue > 10)
        {
            return false;
        }

        Integer xValue = values.get("X");
        if (xValue != null && xValue > 5)
        {
            return false;
        }

        Integer lValue = values.get("L");
        if (lValue != null && lValue > 2)
        {
            return false;
        }

        Integer cValue = values.get("C");
        if (cValue != null && cValue > 5)
        {
            return false;
        }

        Integer dValue = values.get("D");
        if (dValue != null && dValue > 2)
        {
            return false;
        }

        return true;
    }

}

class RomanTesters{


    public static void main(String[] agsgs)
    {
        RomanNumerals roman = new RomanNumerals();

        boolean value = roman.isSmaller("X", "V");
    }
}

