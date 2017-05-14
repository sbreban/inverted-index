import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Map extends Mapper<LongWritable, Text, Text, Text> {

  private Logger logger = Logger.getLogger(Map.class);

  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    String fileName = ((FileSplit) context.getInputSplit()).getPath()
        .getName();

    URI[] stopWordFiles = context.getCacheFiles();
    List<String> stopWords = new ArrayList<String>();
    if (stopWordFiles != null && stopWordFiles.length > 0) {
      for (URI stopWordFile : stopWordFiles) {
        logger.info("Stop words file: " + stopWordFile);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(stopWordFile.toString()));
        String stopWord;
        while ((stopWord = bufferedReader.readLine()) != null) {
          stopWords.add(stopWord);
        }
      }
    } else {
      logger.info("No stop words file.");
    }
    logger.info("Key: " + key.toString());
    logger.info(stopWords+"");
    String line = value.toString();
    String words[] = line.split(" ");
    for (String s : words) {
      if (s.matches("[a-z]+") && !stopWords.contains(s)) {
        context.write(new Text(s), new Text("(" + fileName + "," + key + ")"));
      }
    }
  }
}