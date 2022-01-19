package no13readtext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    /**
     * Takes a string and remove all square brackets and the content in between.
     * Example: [3] [Hello world] all being removed.
     *
     * @param s The sentence.
     * @return the modified string.
     */
    private static String removeBracketsFrom (String s) {
        return s.replaceAll("\\[(.*?)]", "");
    }

    /**
     * Search a word in a text by iterating through each sentence, iterating through each word in that sentence,
     * and get the position.
     *
     * @param lines The text that is separated into list of strings.
     * @param word The word that is being searched through the whole text.
     * @return the specific position of each occurrence of the word, or a message if the word is not in the text at all.
     */
    public static String searchWord (ArrayList<String> lines, String word) {
        StringBuilder sb = new StringBuilder();

        lines.forEach(s -> {
            // Remove all punctuations from the sentence and split it into words.
            String[] words = removeBracketsFrom(s).replaceAll("[\\p{P}]", "").split(" ");

            int index = -1;
            for (int i = 0; index == -1 && i < words.length; i++) { // Get the position of the word, if exists.
                if (words[i].equals(word)) index = i;
            }

            if (index == -1) return;

            sb.append(String.format(
                    "The word \"%s\" is at index position %02d of the text and is word No.%02d of the sentence.\n",
                    word, lines.indexOf(s), index + 1));
        });

        return (sb.isEmpty() ? String.format("The word \"%s\" is not in the text.", word) : sb.toString());
    }

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ProgrammingHistory.txt"));

        ArrayList<String> lines = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) // Split the sentence using '. ' as the spliterator.
            lines.addAll(Arrays.asList(removeBracketsFrom(line).split("\\. ")));

        // TEST
        System.out.println(searchWord(lines, "program"));
        System.out.println(searchWord(lines, "in"));
        System.out.println(searchWord(lines, "computer"));
        System.out.println(searchWord(lines, "is"));
        System.out.println(searchWord(lines, "psychology"));
    }
}
