package com.ehsunbehravesh.varzesh3mobile.bean;

import com.ehsunbehravesh.varzesh3mobile.fetch.FetchRankingEngland;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchRankingFrance;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchRankingGermany;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchRankingIran;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchRankingItaly;
import com.ehsunbehravesh.varzesh3mobile.fetch.FetchRankingSpain;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Ehsun Behravesh
 */
@Stateless
public class TablesBean implements TablesBeanLocal {

    private static final long MAX_CACHE_AGE_MINUTES = 10;
    private static final String IRAN_LEAGUE = "iran_league";
    private static final String SPAIN_LEAGUE = "spain_league";
    private static final String FRANCE_LEAGUE = "france_league";
    private static final String ENGLAND_LEAGUE = "england_league";
    private static final String GERMANY_LEAGUE = "germany_league";
    private static final String ITALY_LEAGUE = "italy_league";
    private static ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    private static Date iranLeagueUpdated;
    private static Date spainLeagueUpdated;
    private static Date franceLeagueUpdated;
    private static Date englandLeagueUpdated;
    private static Date germanyLeagueUpdated;
    private static Date italyLeagueUpdated;

    @Override
    public String iranLeagueTable() {
        if (isOld(iranLeagueUpdated)) {
            try {
                String iranTable = fetchIranTable();
                cache.put(IRAN_LEAGUE, iranTable);
                iranLeagueUpdated = new Date();
            } catch (Exception ex) {
                Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
                return "ERROR in fetching Table! ".concat(ex.getMessage());
            }
        }

        return cache.get(IRAN_LEAGUE);
    }

    @Override
    public String spainLeagueTable() {
        if (isOld(spainLeagueUpdated)) {
            try {
                String spainTable = fetchSpainTable();
                cache.put(SPAIN_LEAGUE, spainTable);
                spainLeagueUpdated = new Date();
            } catch (Exception ex) {
                Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
                return "ERROR in fetching Table! ".concat(ex.getMessage());
            }
        }

        return cache.get(SPAIN_LEAGUE);
    }

    private boolean isOld(Date iranLeagueUpdated) {
        if (iranLeagueUpdated == null) {
            return true;
        } else {
            Date now = new Date();
            long diff = now.getTime() - iranLeagueUpdated.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            return diffMinutes > MAX_CACHE_AGE_MINUTES;
        }
    }

    private String fetchIranTable() throws Exception {
        FetchRankingIran fetch = new FetchRankingIran();
        return fetch.fetchHTML();
    }

    private String fetchSpainTable() throws Exception {
        FetchRankingSpain fetch = new FetchRankingSpain();
        return fetch.fetchHTML();
    }

    private String fetchItalyTable() throws Exception {
        FetchRankingItaly fetch = new FetchRankingItaly();
        return fetch.fetchHTML();
    }

    private String fetchEnglandTable() throws Exception {
        FetchRankingEngland fetch = new FetchRankingEngland();
        return fetch.fetchHTML();
    }

    private String fetchFranceTable() throws Exception {
        FetchRankingFrance fetch = new FetchRankingFrance();
        return fetch.fetchHTML();
    }
        
    private String fetchGermanyTable() throws Exception {
        FetchRankingGermany fetch = new FetchRankingGermany();
        return fetch.fetchHTML();
    }

    @Override
    public String franceLeagueTable() {
        if (isOld(franceLeagueUpdated)) {
            try {
                String table = fetchFranceTable();
                cache.put(FRANCE_LEAGUE, table);
                franceLeagueUpdated = new Date();
            } catch (Exception ex) {
                Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
                return "ERROR in fetching Table! ".concat(ex.getMessage());
            }
        }

        return cache.get(FRANCE_LEAGUE);
    }

    @Override
    public String englandLeagueTable() {
        if (isOld(englandLeagueUpdated)) {
            try {
                String table = fetchEnglandTable();
                cache.put(ENGLAND_LEAGUE, table);
                englandLeagueUpdated = new Date();
            } catch (Exception ex) {
                Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
                return "ERROR in fetching Table! ".concat(ex.getMessage());
            }
        }

        return cache.get(ENGLAND_LEAGUE);
    }

    @Override
    public String germanyLeagueTable() {
        if (isOld(germanyLeagueUpdated)) {
            try {
                String table = fetchGermanyTable();
                cache.put(GERMANY_LEAGUE, table);
                englandLeagueUpdated = new Date();
            } catch (Exception ex) {
                Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
                return "ERROR in fetching Table! ".concat(ex.getMessage());
            }
        }

        return cache.get(GERMANY_LEAGUE);
    }

    @Override
    public String italyLeagueTable() {
        if (isOld(italyLeagueUpdated)) {
            try {
                String table = fetchItalyTable();
                cache.put(ITALY_LEAGUE, table);
                italyLeagueUpdated = new Date();
            } catch (Exception ex) {
                Logger.getLogger(TablesBean.class.getName()).log(Level.SEVERE, null, ex);
                return "ERROR in fetching Table! ".concat(ex.getMessage());
            }
        }

        return cache.get(ITALY_LEAGUE);
    }
    
    

}
