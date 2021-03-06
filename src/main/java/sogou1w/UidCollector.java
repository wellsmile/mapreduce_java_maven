package sogou1w;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class UidCollector {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
//		if (args!=null&&args.length==2) {
			Job job =new Job(new Configuration(),"QuChong");
			job.setJarByClass(UidCollector.class);
			job.setMapperClass(UidMapper.class);
			job.setReducerClass(UidReducer.class);
			//			job.setNumReduceTasks(1);
//			FileInputFormat.setMaxInputSplitSize(job, 10);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(NullWritable.class);
			
			FileInputFormat.addInputPath(job,new Path(args[0]) );
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			System.exit(job.waitForCompletion(true)?0:1);
//		}else {
//			System.out.println("<usage>:UidCollector <input> <output>...");
//		}
	}

}
