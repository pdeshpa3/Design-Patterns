package channelpopularity.driver;

import channelpopularity.Exceptions.InvalidInputException;
import channelpopularity.context.ChannelContext;
import channelpopularity.entitiy.Metrics;
import channelpopularity.entitiy.Video;
import channelpopularity.operation.Operation;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class InputParser {

     private ChannelContext context;
     private String line[];

    /**
     * This method accepts input and output files and invokes parsing
     * @param input :input file
     * @param outputFile : outputfile
     */
     void getStarted(String input, String outputFile){
         try {
             parseInput(input, outputFile);
         }catch (IOException | InvalidInputException e){

             e.printStackTrace();
         }
     }

    /**
     * This method parses input by calling FileProcessor, creates a ChannelContext and writes to Results
     * @param input : inputfile
     * @param outputFile : outputfile
     * @throws IOException : FileProcessor may throw IOException
     * @throws InvalidInputException : performop(), checkop() may detect invalid input
     */
     private void parseInput(String input, String outputFile)throws IOException, InvalidInputException{


            FileProcessor fp = new FileProcessor(input);
            List<StateName> stateNameList = new ArrayList<>();

            for(StateName stateName: StateName.values()){
                stateNameList.add(stateName);
            }

            Results results = new Results();
            context = new ChannelContext(new SimpleStateFactory(results), stateNameList);

            String inputLine;
            line = new String[2];
            int op = -1;


            while(true){

                inputLine = fp.poll();

                if(inputLine == null)break;
                if(inputLine.trim().equals(""))break; //reached end of file
                line = inputLine.split("::");
                if(line.length<2)throw new InvalidInputException("");
                op = checkOperation(line[0]);
                if(op == -1) throw new InvalidInputException("No such operation");
                performOp(op);

            }

            results.writeToStdout();
            results.writeToFile(outputFile);

    }

    /**
     * This method performs the operation by delegating the call to context
     * @param op : operation to be performed - add/remove video or metrics update or add request
     * @throws InvalidInputException : Invalid input on parsing
     */
    private void performOp(int op) throws InvalidInputException{
        Video video;
         switch(op){
             case 0: //ADD_VIDEO::video1
                 video = context.getVideo(line[1]);
                 if(video!=null){
                    throw new InvalidInputException("Video already exists");
                 }
                 context.addVideo(new Video(line[1]));
                 break;
             case 1: //REMOVE_VIDEO::video2
                 video = context.getVideo(line[1]);
                 if(video!=null)
                 context.removeVideo(video);
                 else{
                     throw new InvalidInputException("Video does not exist");
                 }
                 break;
             case 2://AD_REQUEST__video1::LEN=8
                 video = context.getVideo(line[0].split("__")[1]);
                 int len = getInt(line[1].split("=")[1]);
                 if(len<0)throw new InvalidInputException("length of add cannot be negative");
                 if(video!=null){
                     context.addReq(video, len);
                 }else{
                     throw new InvalidInputException("Video does not exist for add req");
                 }
                 break;
             case 3://METRICS__video2::[VIEWS=2000,LIKES=400,DISLIKES=20]
                 video = context.getVideo(line[0].split("__")[1]);
                 if(video !=null){
                     Metrics metrics = createMetrics(line[1], video);
                     context.calculateMetrics(metrics, video);
                 }else{
                     throw new InvalidInputException("Video does not exist");
                 }
                 break;
             default:
                 break;
         }
    }

    /**
     * This method checks the operation that needs to be performed on input
     * @param op : operation to be performed
     * @return : corresponding integer code
     */
    private int checkOperation(String op)  {
         if(op.contains(Operation.ADD_VIDEO.toString())){
             return 0;
         }else if(op.contains(Operation.REMOVE_VIDEO.toString())){
             return 1;
         }else if(op.contains(Operation.AD_REQUEST__.toString())){
             return 2;
         }else if(op.contains(Operation.METRICS__.toString())){
             return 3;
         }
         return -1;
    }


    /**
     * This method creates a metrics object
     * @param input : input
     * @param video : video on which to create/update metrics
     * @return : returns the created metrics object
     * @throws InvalidInputException : metrics object may throw Exception if metrics parameters don't fall within valid range
     */
    private Metrics createMetrics(String input, Video video)throws InvalidInputException{
         //[VIEWS=2000,LIKES=400,DISLIKES=20]
        try {
            input = input.substring(1, input.length() - 1);
            String info[] = new String[3];
            info = input.split(",");

            int views = getInt(info[0].split("=")[1]);
            int likes = getInt(info[1].split("=")[1]);
            int dislikes = getInt(info[2].split("=")[1]);


            Metrics metrics = new Metrics(views,likes,dislikes);
            return metrics;

        }catch (Exception e){
            throw new InvalidInputException("Incorrect format");
        }
     }

    /**
     * This method is a utility method to convert String to int
     * @param num : number to convert to int
     * @return : number
     * @throws InvalidInputException : Input may not be a number
     */
    private int getInt(String num)throws InvalidInputException{
         try{
             return Integer.parseInt(num);
         }catch(NumberFormatException e){
             throw new InvalidInputException("Input is not a valid number");
         }
    }


    //invalid operation, anything not an integer, incorrect format, video does not exist/alrdy exist
     //views <0, likess dislikes falls below 0
     //need to check VIEWS, LIKES, DISLIKES for format; empty file

}
