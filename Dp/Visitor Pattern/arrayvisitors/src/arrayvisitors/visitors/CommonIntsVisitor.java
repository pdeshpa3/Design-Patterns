package arrayvisitors.visitors;

import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.adt.MyArrayListI;
import arrayvisitors.util.FileDisplayInterface;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.Results;

import java.util.*;

public class CommonIntsVisitor implements VisitorI {

        private Results results;

        public CommonIntsVisitor(Results results){
                MyLogger.writeMessage("CommonIntsVisitor Constructor", MyLogger.DebugLevel.CONSTRUCTOR);
                this.results = results;
        }

        @Override
        public void visit(MyArrayI myArray) {

        }

        /**
         * This method takes a MyArrayListI and finds common elements from its arrays; appends to result
         * @param arrayList : contains instances of MyArrayI
         */
        @Override
        public void visit(MyArrayListI arrayList){

                MyLogger.writeMessage("CommonInts Processing visit", MyLogger.DebugLevel.COMMON_INTS);

                StringBuilder sb = results.getSb();
                sb.append("Printing Common Ints\n");
                MyArrayI[] array = arrayList.getElements();
                Set<Integer> commons = new HashSet<>();


                MyArrayI smaller = array[0].getLength()<=array[1].getLength()? array[0]: array[1];
                MyArrayI larger =  array[0].getLength()>array[1].getLength()? array[0]: array[1];

                int arraySmall[] = smaller.getElements();
                int arrayLarge[] = larger.getElements();

                for(int i=0; i<arraySmall.length; i++){
                        for(int j=0; j<arrayLarge.length; j++){
                              if(arraySmall[i] == arrayLarge[j]){
                                      if(!commons.contains(arraySmall[i])){
                                              commons.add(arraySmall[i]);
                                              if(arraySmall[i]%10 == arraySmall[i]){
                                                      sb.append("0"+arraySmall[i]+"\n");
                                              }else{
                                                      sb.append(arraySmall[i]+"\n");
                                              }

                                      }
                              }
                        }
                }


        }

}
