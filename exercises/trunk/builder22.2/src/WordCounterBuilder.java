public class WordCounterBuilder implements Builder {
    private int wordCount = 0;

    @Override
    public void buildSection(String text) {
        wordCount += countWords(text);
    }

    private int countWords(String text) {
        String et = text.replaceAll("[(|)]", "");
        return et.split("\\s+").length;
    }

    @Override
    public void buildSubsection(String text) {
        wordCount += countWords(text);
    }

    @Override
    public void buildParagraph(String text) {
        wordCount += countWords(text);
    }

    public int getWordCount() {
        return wordCount;
    }
}
