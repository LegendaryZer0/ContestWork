package ru.company.util;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Extractor {
    private boolean scriptFlag;
    private boolean styleFlag;
    private int whenFlag1 = 0;
    private int whenFlag2 = 0;

    private boolean franceQuotesFlag;
    private String[] list;
    private final StringBuilder finalLine;
    private final String[] wrongStrings = new String[]{"/", "s", "c", "r", "i", "p", "t"};
    private final String[] moreWrongStrings = new String[]{"/", "s", "t", "y", "l", "e"};
    private int j = 1;
    private int k = 1;

    public Extractor() {
        finalLine = new StringBuilder();
    }

    public Map<String, Long> extract(String line) {


        list = line.split("");

        for (int i = 0; i < list.length; i++) {
            if (list[i].equals("\\n")) {
                continue;
            }

            if (franceQuotesFlag && (whenFlag1 > 0 || whenFlag2 > 0)) {
                if (list[i].equals(wrongStrings[j])) {
                    if (j == 6) {
                        scriptFlag = !scriptFlag;
                        j = -1;
                        whenFlag1 = 2;
                    } else {
                        whenFlag1 = 1;
                    }

                    j++;
                } else {
                    if (whenFlag1 != 2) {
                        whenFlag1 = -1;
                        j = 1;
                    } else {
                        j = 0;

                    }


                }


                if (list[i].equals(moreWrongStrings[k])) {
                    if (k == 5) {
                        styleFlag = !styleFlag;
                        k = -1;
                        whenFlag2 = 2;
                    } else {

                        whenFlag2 = 1;
                    }
                    k++;


                } else {
                    if (whenFlag2 != 2) {
                        whenFlag2 = -1;
                        k = 1;
                    } else {
                        k = 0;
                    }


                }
            }

            if (list[i].equals("<")) {
                franceQuotesFlag = true;
                whenFlag1 = 1;
                whenFlag2 = 1;
            }


            if (!franceQuotesFlag && !styleFlag && !scriptFlag) {
                finalLine.append(list[i]);
            }

            if (list[i].equals(">")) {

                if (styleFlag || scriptFlag) {

                } else {
                    franceQuotesFlag = false;
                }

            }


        }
        return Arrays.stream(finalLine.toString().toUpperCase().split("[,?!\\s;:\"\\[\\]{}]")).collect(Collectors.toMap(Function.identity(), v -> 1L, Long::sum));
    }
}



