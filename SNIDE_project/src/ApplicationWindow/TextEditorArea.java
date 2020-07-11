package ApplicationWindow;

import Back.Position;
import Back.Utils;
import Constants.Consts;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextEditorArea extends TextArea
{
    private ArrayList<String> keywordsPool;

    public TextEditorArea()
    {
        this.keywordsPool = Utils.getWordsFromFile(Consts.JAVA_KEYWORDS_PATH);

        //Highlights key words
//        Platform.runLater(
//                () ->
//                {
//                    try
//                    {
//                        Thread.sleep(1000); //TODO change time
//
//                    }
//                    catch (InterruptedException e) { e.printStackTrace(); }
//                }
//        );
//

        //fixme debug
//        Text text = new Text("Text");
//        text.setFill(Color.RED);

        //paintKeyWords();
    }

    private void paintKeyWords()
    {
        String textAreaContent = this.getText();
        ArrayList<Position> positions = getKeywordsPositions();


    }

    private ArrayList<Position> getKeywordsPositions()
    {
        ArrayList<Position> keywordPositions = new ArrayList<>();

        //Creates regex
        StringBuilder keyWordsRegex = new StringBuilder();

        keywordsPool.stream()
                .forEach(str ->
                        {
                            keyWordsRegex.append(str + "|");
                        }
                );

        //Finds positions
        Pattern pattern = Pattern.compile(keyWordsRegex.toString());
        Matcher matcher = pattern.matcher(this.getText());

        while (matcher.find())
        {
            keywordPositions.add(new Position(matcher.start(), matcher.end()));
        }

        //fixme debug
        keywordPositions.stream().forEach(s -> System.out.println(s));

        return keywordPositions;
    }
}