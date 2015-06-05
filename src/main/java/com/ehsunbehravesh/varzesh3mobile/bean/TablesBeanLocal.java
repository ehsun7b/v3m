package com.ehsunbehravesh.varzesh3mobile.bean;

import javax.ejb.Local;

/**
 *
 * @author Ehsun Behravesh
 */
@Local
public interface TablesBeanLocal {

    String iranLeagueTable();

    String spainLeagueTable();

    String franceLeagueTable();

    String englandLeagueTable();

    String germanyLeagueTable();

    String italyLeagueTable();

}
