import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Reduce extends

    Reducer<Text, Text, Text, Text> {
  @Override
  public void reduce(Text key, Iterable<Text> values, Context context)

      throws IOException, InterruptedException {

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    for (Text value : values) {
      String stringValue = value.toString();
      stringBuilder.append(stringValue);
      stringBuilder.append(" ");
    }
    stringBuilder.append("]");

    context.write(key, new Text(stringBuilder.toString()));

  }

}