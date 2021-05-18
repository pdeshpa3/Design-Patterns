package channelpopularity.state.factory;

import channelpopularity.context.ChannelContext;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public interface SimpleStateFactoryI {
    StateI getState(StateName stateName, ChannelContext channelContext);
}
