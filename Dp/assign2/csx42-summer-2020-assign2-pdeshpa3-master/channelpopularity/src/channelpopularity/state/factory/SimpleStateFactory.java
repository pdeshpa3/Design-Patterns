package channelpopularity.state.factory;

import channelpopularity.state.*;

public class SimpleStateFactory implements SimpleStateFactoryI {

    StateI state = null;

    /**
     * Creates instances
     * @param stateName : state name to create its object
     * @return
     */
    @Override
    public StateI create(StateName stateName) {

        if(StateName.UNPOPULAR.equals(stateName))
        {
            state = new UnpopularState();
        }
        else if(StateName.MILDLY_POPULAR.equals(stateName))
        {
            state = new MildlyPopularState();
        }
        else if(StateName.HIGHLY_POPULAR.equals(stateName))
        {
            state = new HighlyPopularState();
        }
        else if(StateName.ULTRA_POPULAR.equals(stateName))
        {
            state = new UltraPopularState();
        }

        return state;
    }
}
