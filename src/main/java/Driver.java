import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class Driver {
  public static void main(String[] args) throws Exception {

    Configuration conf = new Configuration();


    Job job = new Job(conf, "UseCase1");

    job.setMapOutputKeyClass(Text.class);

    job.setMapOutputValueClass(Text.class);

    job.setJarByClass(Driver.class);

    job.setMapperClass(Map.class);

    job.setReducerClass(Reduce.class);

    job.setOutputKeyClass(Text.class);

    job.setOutputValueClass(Text.class);

    job.setInputFormatClass(NotSplittableFileInputFormat.class);

    job.setOutputFormatClass(TextOutputFormat.class);

    DistributedCache.addCacheFile(new Path(args[0]).toUri(), job.getConfiguration());

    Path outputPath = new Path(args[2]);

    FileInputFormat.addInputPath(job, new Path(args[1]));

    FileOutputFormat.setOutputPath(job, outputPath);

    outputPath.getFileSystem(conf).delete(outputPath);

    System.exit(job.waitForCompletion(true) ? 0 : 1);

  }

}
