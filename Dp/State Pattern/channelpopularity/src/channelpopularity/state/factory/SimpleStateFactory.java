package channelpopularity.state.factory;

import channelpopularity.context.ChannelContext;
import channelpopularity.state.*;
import channelpopularity.util.Results;


public class SimpleStateFactory implements SimpleStateFactoryI {
    private StateI state;
    private Results results;

    public SimpleStateFactory(Results r){this.results = r;}

    /**
     * This method takes in stateName to create its object and passes the ChannelContext and Results instance to each state
     * @param stateName : stateName for creating its corresponding object
     * @param channelContext : context to send to each state to enable them to change currentState
     * @return : created instance of stateName
     */
    @Override
    public StateI getState(StateName stateName, ChannelContext channelContext) {

        if(stateName.toString().equals("UNPOPULAR")){
            return new Unpopular(channelContext,  results);
        }else if(stateName.toString().equals("MILDLY_POPULAR")){
            return new MildlyPopular(channelContext, results);
        }else if(stateName.toString().equals("HIGHLY_POPULAR")){
            return new HighlyPopular(channelContext, results);
        }else if(stateName.toString().equals("ULTRA_POPULAR")){
            return new UltraPopular(channelContext, results);
        }
        return null;
    }
}
