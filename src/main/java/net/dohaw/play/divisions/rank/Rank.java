package net.dohaw.play.divisions.rank;

import java.util.Arrays;
import java.util.List;

public enum Rank {

    FRESH_MEAT,
    PROVEN_MEMBER,
    LOYAL,
    ELDER,
    OVERLORD;

    /*
        If rank1 is higher than rank2
        1 - Higher
        0 - Same
        -1 - Lower
     */

    public static int isAHigherRank(Rank rank1, Rank rank2){
        List<Rank> ranks = Arrays.asList(values());
        if(ranks.indexOf(rank1) > ranks.indexOf(rank2)){
            return 1;
        }else if(ranks.indexOf(rank1) < ranks.indexOf(rank2)){
            return -1;
        }else if(ranks.indexOf(rank1) == ranks.indexOf(rank2)){
            return 0;
        }
        return 0;
    }

}