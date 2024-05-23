package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class pruebaJson {


    interface DateParser {
        LocalDateTime parse(String time);

        void setNextParser(DateParser nextParser);
    }

    static class SpecificDateParser implements DateParser {
        private DateTimeFormatter formatter;
        private DateParser nextParser;

        public SpecificDateParser(String pattern) {
            this.formatter = DateTimeFormatter.ofPattern(pattern);
        }

        public LocalDateTime parse(String time) {
            try {
                return LocalDateTime.parse(time, formatter);
            } catch (Exception e) {
                if (nextParser != null) {
                    return nextParser.parse(time);
                }
                return null;
            }
        }

        public void setNextParser(DateParser nextParser) {
            this.nextParser = nextParser;
        }
    }

    static class IsoDateParser implements DateParser {
        private DateParser nextParser;

        public LocalDateTime parse(String time) {
            try {
                return LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
            } catch (Exception e) {
                if (nextParser != null) {
                    return nextParser.parse(time);
                }
                return null;
            }
        }

        public void setNextParser(DateParser nextParser) {
            this.nextParser = nextParser;
        }
    }

    static class DefaultDateParser implements DateParser {
        public LocalDateTime parse(String time) {
            return LocalDateTime.parse(time);
        }

        public void setNextParser(DateParser nextParser) {
            // No se necesita en este caso, ya que es el último en la cadena.
        }
    }

    public static void main(String[] args) {
        SpecificDateParser specificDateParser1 = new SpecificDateParser("yyyy-MM-dd HH:mm:ss.SSS");
        SpecificDateParser specificDateParser2 = new SpecificDateParser("yyyy-MM-dd HH:mm:ss.S");
        SpecificDateParser specificDateParser3 = new SpecificDateParser("yyyy-MM-dd HH:mm:ss.0000");
        IsoDateParser isoDateParser = new IsoDateParser();
        DefaultDateParser defaultDateParser = new DefaultDateParser();

        specificDateParser1.setNextParser(specificDateParser2);
        specificDateParser2.setNextParser(specificDateParser3);
        specificDateParser3.setNextParser(isoDateParser);
        isoDateParser.setNextParser(defaultDateParser);

        String time = "2023-08-25 12:34:56.7";
        LocalDateTime parsedTime = specificDateParser1.parse(time);
        System.out.println(parsedTime);
    }

}
