package Roman;

import java.util.HashMap;

public class Solution
{

    public HashMap<String, Integer> numerals;
    public HashMap<String, Integer> values;  //A hashset for making sure D, L, and V can each only appear once.

    public Solution()
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

    public int romanToInt(String numeral)
    {
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
                boolean canAdd = checkForSmallerDenominations(firstNumeral);

                if (canAdd)
                {
                    int valueToAdd = values.getOrDefault(firstNumeral, 0);

                    if (valueToAdd == 0)
                    {
                        values.put(firstNumeral, 1);
                    }
                    else
                    {
                        values.put(firstNumeral, valueToAdd + 1);
                    }

                    if (i + 1 < numeral.length())
                    {
                        String secondNumeral = numeral.substring(i + 1, i + 2);

                        int compareValue = numerals.get(secondNumeral).compareTo(numerals.get(firstNumeral));

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
                                else
                                {
                                    largestValue = combinedValue;
                                }
                                answer += combinedValue;
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
                            else
                            {
                                largestValue = firstValue;
                            }
                            answer += firstValue;

                        } else
                        {
                            int value = numerals.get(firstNumeral);

                            if (largestValue == 0)
                            {
                                largestValue = value;
                            } else if (value > largestValue)
                            {
                                isValid = false;
                            }
                            else
                            {
                                largestValue = value;
                            }
                            answer += value;

                        }

                    } else //end of the string
                    {
                        int value = numerals.get(firstNumeral);

                        if (largestValue == 0)
                        {
                            largestValue = value;
                        } else if (value > largestValue)
                        {
                            isValid = false;
                        }
                        else
                        {
                            largestValue = value;
                        }
                        answer += value;
                    }
                }
                else
                {
                    isValid = false;
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
        return answer;
    }

    /**
     * Checks to make sure no roman numeral exceeds the limit rules, such as 'No more than one V in String'
     * @param aNumeral String
     * @return boolean
     */
    public boolean checkForSmallerDenominations(String aNumeral)
    {
        if (aNumeral.equals("V"))
        {
            Integer vValue = values.get("V");

            if (vValue != null)
            {
                return false;
            }
            return true;
        } else if (aNumeral.equals("I"))
        {
            Integer iValue = values.get("I");

            if (iValue != null && iValue >= 9)
            {
                return false;
            }
            return true;
        } else if (aNumeral.equals("X"))
        {
            Integer xValue = values.get("X");
            if (xValue != null && xValue >= 4)
            {
                return false;
            }
            return true;

        } else if (aNumeral.equals("L"))
        {
            Integer lValue = values.get("L");
            if (lValue != null)
            {
                return false;
            }
            return true;

        } else if (aNumeral.equals("C"))
        {
            Integer cValue = values.get("C");
            if (cValue != null && cValue >= 4)
            {
                return false;
            }
            return true;

        } else if (aNumeral.equals("D"))
        {
            Integer dValue = values.get("D");
            if (dValue != null)
            {
                return false;
            }
            return true;

        } else if (aNumeral.equals("M"))
        {
            return true;
        } else
        {
            return false;
        }
    }

}

class RomanTesters{


    public static void main(String[] agsgs)
    {
        Solution roman = new Solution();

        //boolean value = roman.isSmaller("X", "V");

        //int total = roman.romanToInt("XIIIIII");

        int total2 = roman.romanToInt("MCMXCIV"); //<--Should fail

        //Add code to check for smaller denominations
    }
}

