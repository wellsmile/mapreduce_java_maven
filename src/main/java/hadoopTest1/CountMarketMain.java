package hadoopTest1;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class CountMarketMain {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		String input=null;
		String output=null;
		if (args!=null&&args.length==2) {
			input =args[0];
			output=args[1];
			Job job =new Job(new Configuration(),"MarketCount");
			job.setJarByClass(CountMarketMain.class);
			job.setMapperClass(CountMarketMapper.class);
			job.setReducerClass(CountMarketReducer.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			FileInputFormat.addInputPath(job,new Path(input) );
			FileOutputFormat.setOutputPath(job, new Path(output));
			
			System.exit(job.waitForCompletion(true)?0:1);
		}else {
			System.out.println("<usage>:MarketCount <input> <output>...");
		}
		
	}

}
