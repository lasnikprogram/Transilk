package net.transilk.util.parser;

import net.transilk.dictionary.EntryHolder;

public class ParserUtil {
        /*
        example entry:
        Aalmolche /ˈɑːlmɔlçə/ <pl>
        amphiuma salamanders, amphiumas, congo eels [coll.] , congo snakes [coll.]
         Note: zoological genus
           Synonym: {Fischmolche}
           see: {Aalleiter}, {Aaltreppe}, {Aalpass}
        */

    public static EntryHolder parseStringToEntry(String string) {
        EntryHolder entryHolder = new EntryHolder();
        String[] lines = string.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (i == 0) {
                entryHolder.setLeft(line);
            } else if (i == 1) {
                entryHolder.setRight(line);
            } else if (line.startsWith(" ")) {
                String trimmedLine = line.trim();
                if (trimmedLine.startsWith("Synonym")) {
                    entryHolder.setSynonym(trimmedLine);
                } else if (trimmedLine.startsWith("see")) {
                    entryHolder.setSee(trimmedLine);
                }
            }
        }
        return entryHolder;
    }
}